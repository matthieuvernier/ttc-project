package eu.project.ttc.tools.aligner;

import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ConfigurationParameterDeclarations;

import eu.project.ttc.tools.TermSuiteSettings;
import eu.project.ttc.tools.utils.Parameters;

public class AlignerSettings extends Parameters implements TermSuiteSettings {	
	
	public AlignerSettings(String resource) {
		super(resource);
	}

	protected void setMetaData(ConfigurationParameterDeclarations declarations) {
		this.addParameter(declarations, "Directory", ConfigurationParameter.TYPE_STRING, false, true);
		this.addParameter(declarations, "SourceLanguage", ConfigurationParameter.TYPE_STRING, false, true, "values:en|fr|de|es|ru|da|lv|zh");
		this.addParameter(declarations, "SourceTerminologyFile", ConfigurationParameter.TYPE_STRING, false, true);
		this.addParameter(declarations, "TargetLanguage", ConfigurationParameter.TYPE_STRING, false, true, "values:en|fr|de|es|ru|da|lv|zh");
		this.addParameter(declarations, "TargetTerminologyFile", ConfigurationParameter.TYPE_STRING, false, true);
		this.addParameter(declarations, "DictionaryFile", ConfigurationParameter.TYPE_STRING, false, false);
	}
		
}
