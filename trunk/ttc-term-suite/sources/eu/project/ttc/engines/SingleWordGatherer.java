/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package eu.project.ttc.engines;

import eu.project.ttc.metrics.EditDistance;
import eu.project.ttc.tools.indexer.IndexerBinding;
import eu.project.ttc.tools.utils.MultiWordAsSimpleGatherer;
import eu.project.ttc.types.SingleWordTermAnnotation;
import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import uima.sandbox.mapper.resources.Mapping;

import java.util.*;

public class SingleWordGatherer extends JCasAnnotator_ImplBase {

	/** Gathers multiword terms without considering diacritics */
	private MultiWordAsSimpleGatherer extraGatherer;

	private Mapping mapping;

	private void setMapping(Mapping conversion) {
		this.mapping = conversion;
	}

	private Mapping getMapping() {
		return this.mapping;
	}

	private EditDistance editDistance;

	private void setEditDistance(String name) throws Exception {
		if (this.editDistance == null) {
			Class<?> cl = Class.forName(name);
			Object obj = cl.newInstance();
			if (obj instanceof EditDistance) {
				this.editDistance = (EditDistance) obj;
				UIMAFramework.getLogger().log(
						Level.INFO,
						"Setting Edit Distance: "
								+ this.editDistance.getClass().getSimpleName());
			} else {
				throw new ClassCastException("Class name '" + name
						+ "' doesn't comply "
						+ EditDistance.class.getCanonicalName());
			}
		}
	}

	private EditDistance getEditDistance() {
		return this.editDistance;
	}

	private boolean enable;

	private void enable(boolean enabled) {
		this.enable = enabled;
	}

	public boolean isEnabled() {
		return enable;
	}

	private Float threshold;

	// Is this really used ?
	private Integer ngrams;

	@Override
	public void initialize(UimaContext context)
			throws ResourceInitializationException {
		super.initialize(context);
		extraGatherer = null;
		try {
			Boolean enabled = (Boolean) context
					.getConfigParameterValue("Enable");
			this.enable(enabled == null ? false : enabled.booleanValue());
			if (this.isEnabled() && this.getEditDistance() == null) {
				String editDistance = (String) context
						.getConfigParameterValue("EditDistanceClassName");
				this.setEditDistance(editDistance);

				if (threshold == null) {
					threshold = (Float) context
							.getConfigParameterValue("Threshold");
					if (this.editDistance.isFailFast())
						this.editDistance.setFailThreshold(threshold);
				}
				if (ngrams == null) {
					ngrams = (Integer) context
							.getConfigParameterValue("Ngrams");
				}

				if (Boolean.TRUE
						.equals(context
								.getConfigParameterValue(IndexerBinding.PRM.IGNOREDIACRITICS.getParameter())))
					extraGatherer = new MultiWordAsSimpleGatherer();

			}

			if (this.getAnnotations() == null) {
				this.setAnnotations();
			}
			if (this.getMapping() == null) {
				Mapping mapping = (Mapping) context
						.getResourceObject("Mapping");
				this.setMapping(mapping);
			}
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}

	private Map<String, List<SingleWordTermAnnotation>> annotations;

	private void setAnnotations() {
		this.annotations = new HashMap<String, List<SingleWordTermAnnotation>>();
	}

	private void setAnnotations(
			Map<String, List<SingleWordTermAnnotation>> annotations) {
		Map<String, List<SingleWordTermAnnotation>> a = new TreeMap<String, List<SingleWordTermAnnotation>>();
		a.putAll(annotations);
		this.annotations = a;
	}

	private Map<String, List<SingleWordTermAnnotation>> getAnnotations() {
		return this.annotations;
	}

	private String getKey(Annotation annotation) {
		String coveredText = annotation.getCoveredText().toLowerCase();
		char ch = coveredText.charAt(0);
		if (Character.isLetter(ch)) {
			String key = Character.toString(ch);
			String value = this.getMapping().get(key);
			if (value == null) {
				return key;
			} else {
				return value;
			}
		} else {
			return null;
		}
	}

    // TODO : move to external db
	private void index(JCas cas) {
		Set<SingleWordTermAnnotation> remove = new HashSet<SingleWordTermAnnotation>();
		AnnotationIndex<Annotation> index = cas
				.getAnnotationIndex(SingleWordTermAnnotation.type);
		FSIterator<Annotation> iterator = index.iterator();
		while (iterator.hasNext()) {
			SingleWordTermAnnotation annotation = (SingleWordTermAnnotation) iterator
					.next();
			String key = this.getKey(annotation);
			if (key == null) {
				remove.add(annotation);
			} else {
				List<SingleWordTermAnnotation> list = this.getAnnotations()
						.get(key);
				if (list == null) {
					list = new ArrayList<SingleWordTermAnnotation>();
					this.getAnnotations().put(key, list);
				}
				list.add(annotation);
			}
		}
		for (SingleWordTermAnnotation r : remove) {
			r.removeFromIndexes();
		}
	}

	private void clean() {
		Set<String> keys = new HashSet<String>();
		for (String key : this.getAnnotations().keySet()) {
			List<SingleWordTermAnnotation> list = this.getAnnotations()
					.get(key);
			if (list.size() < 2) {
				keys.add(key);
			}
		}
		for (String key : keys) {
			this.getAnnotations().remove(key);
		}
	}

	private void sort() {
		this.setAnnotations(this.getAnnotations());
	}

	private void gather(JCas cas) {
		UIMAFramework.getLogger().log(
				Level.INFO,
				"Edit-distance gathering over " + this.getAnnotations().size()
						+ " term classes");
		for (String key : this.getAnnotations().keySet()) {
			List<SingleWordTermAnnotation> list = this.getAnnotations()
					.get(key);
			UIMAFramework.getLogger().log(
					Level.INFO,
					"Edit-distance gathering over the '" + key
							+ "' term class of size " + list.size());
			for (int i = 0; i < list.size(); i++) {
				SingleWordTermAnnotation sourceAnnotation = list.get(i);
				if (sourceAnnotation.getNeoclassical() || sourceAnnotation.getCompound())
					continue;
				String source = sourceAnnotation.getCoveredText();
				for (int j = i + 1; j < list.size(); j++) {
					SingleWordTermAnnotation targetAnnotation = list.get(j);
					if (sourceAnnotation.getNeoclassical() || sourceAnnotation.getCompound())
						continue;
					String target = targetAnnotation.getCoveredText();
					int distance = editDistance.compute(source, target);
					double norm = editDistance.normalize(distance, source,
							target);
					if (norm >= threshold.doubleValue()) {
						this.annotate(cas, list, i, j);
					}
				}
			}
            System.gc(); // Aggressive memory collection
		}
	}

	private void annotate(JCas cas, List<SingleWordTermAnnotation> list, int i,
			int j) {
		SingleWordTermAnnotation base = list.get(i);
		SingleWordTermAnnotation variant = list.get(j);
		if (base.getFrequency() < variant.getFrequency()) {
			SingleWordTermAnnotation tmp = base;
			base = variant;
			variant = tmp;
		}
		FSArray array = base.getVariants();
		if (array == null) {
			array = new FSArray(cas, 1);
		} else {
			SingleWordTermAnnotation[] fs = new SingleWordTermAnnotation[array
					.size() + 1];
			array.copyToArray(0, fs, 0, array.size());
			array = new FSArray(cas, fs.length);
			array.copyFromArray(fs, 0, 0, fs.length - 1);
		}
		base.setVariants(array);
		base.setVariants(array.size() - 1, variant);
	}

	private void display(JCas cas) {
		AnnotationIndex<Annotation> index = cas
				.getAnnotationIndex(SourceDocumentInformation.type);
		FSIterator<Annotation> iterator = index.iterator();
		if (iterator.hasNext()) {
			SourceDocumentInformation sdi = (SourceDocumentInformation) iterator
					.next();
			this.getContext()
					.getLogger()
					.log(Level.INFO,
							"Gathering single-words of " + sdi.getUri());
		}
	}

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		if (this.enable) {
			this.display(cas);
			this.index(cas);
			this.clean();
			this.sort();
			this.gather(cas);
			System.gc();

			if (extraGatherer != null)
				extraGatherer.gather(cas, mapping, threshold);
		}
	}
}
