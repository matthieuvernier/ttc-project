# Reference Guide #

## Contents ##



## UIMA Type System ##

The [TermSuite Type System](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/types/TermSuiteTypeSystem.xml) define how information is structured by any Term Suite components and applications.

There exist 3 main annotation types that are respectively:
  * the type _WordAnnotation_ identifies a word with Multext-compliant  grammatical features;
  * the type _TermAnnotation_ is used as well term occurrence detection as for term entry indexation;
  * the type _TranslationCandidateAnnotation_ receives every alignment results for a given term to translate.

**Note:** every annotation types should be prefixed by the path _eu.project.ttc.types._; such prefix is here removed for shortening their notations.

Moreover, annotation types such as _TranslationCandidateAnnotation_ are referred below as _Translation Candidate Annotation_ for readability reasons.

### Term Component Annotation ###

The annotation type _Term Component Annotation_ defines the required features for multi-word term components as well as for single-word compounds:
  * the String feature _category_ corresponds to the Multext grammatical category normalized and expansed value i.e. it ranges over noun, verb, adjective, pronoun, determiner, article, adposition, conjunction, abbreviation (see [Multext](http://aune.lpl.univ-aix.fr/projects/multext/LEX/LEX.Specification.html))
  * the String feature _lemma_ corresponds to the TreeTagger lemma
  * the String feature _stem_ corresponds to the stem provided by Porter's algorithm

### Word Annotation ###

The annotation type _Word Annotation_ extends that of _Term Component Annotation_ by these following features:
  * the String feature _tag_ corresponds to the TreeTagger original tag
  * the String feature _subCategory_ corresponds to the sub-category of the Multext category
  * the String feature _mood_ corresponds to the mood of the Multext category verb
  * the String feature _tense_ corresponds to the mood of the Multext category verb
  * the String feature _number_ corresponds to the number of the Multext categories noun, verb, adjective, pronoun, determiner, article and numeral
  * the String feature _gender_ corresponds to the gender of the Multext categories noun, verb, adjective, pronoun, determiner, article and numeral
  * the String feature _case_ corresponds to the case of the Multext categories noun, adjective, pronoun, determiner, article and numeral
  * the String feature _person_ corresponds to the case of the Multext categories verb, pronoun, determiner and article
  * the String feature _possessor_ corresponds to the case of the Multext categories pronoun and determiner
  * the String feature _degree_ corresponds to the case of the Multext category adverb
  * the String feature _formation_ corresponds to the case of the Multext category adposition

### Term Annotation ###

The annotation type _Term Annotation_ is used both for term instances in corpus documents and term entries of the corpus terminology. It owns the following features:
  * the _String_ feature _category_ corresponds to the identifier of the syntactic pattern which such term instances or term entries has been
detected from
  * the _String_ feature _langset_ corresponds to langset identifier after the TermAnnotation has been saved for the first time in TBX format
  * the _Double_ feature _lemma_ corresponds to the lemmatized for of such terms i.e. the lemma for a simple term or the lemma sequence of complex term components
  * the _Integer_ feature _occurrences_ corresponds to the absolute frequency of term entries
  * the _Double_ feature _frequency_ corresponds to the relative frequency of term entries
  * the _Double_ feature _specificity_ corresponds to the domain specificity of term entries i.e. the quotient of such term relative frequency against the relative frequency of the same term in a general language corpus
  * the array of _String_ feature _forms_ lists every term instance forms this term entry is indexed from
  * the array of _Term Annotation_ feature _variants_ references the term variants of term entries
  * the array of _Term Annotation_ feature _context_ references term annotations in the contextual window of term instances

#### Single-Word Term Annotation ####

The annotation type _Single Word Term Annotation_ extends that of _Term Annotation_ by 2 boolean features:
  * a feature _compound_ meaning that this single-word term is e compound one;
  * another feature _neoclassical_ meaning that this single-word term is a neoclassical compound one.

Term components of single-word compounds are obtained by the _subiterator_ call over _Term Component Annotation_ index and the given term annotation: it retrieves every annotations of type _Term Component Annotation_ that are covered by the given single-word term.

#### Multi-Word Term Annotation ####

The annotation type _Multi Word Term Annotation_ extends that of _Term Annotation_ by an optional feature _components_ that is an array of _Term Component Annotation_. The latter may contain every components of the given term this feature belongs to. But term components could also be retrieved using a _subiterator_ call.

### Translation Annotation ###

Annotations of type _Translation Annotation_ are used within the Aligner tool for evaluation purpose i.e. it defines the gold standard for a term to translate. Such annotations are defined by these 2 features:
  * the String feature _language_ that sets the target language of such translation
  * the String feature _term_ that defines the right translation to the term such annotations cover.

### Translation Candidate Annotation ###

The annotation type _Translation Candidate Annotation_ corresponds to the alignment results as they are defined by these 3 features:
  * the String feature _translation_ defines the translation candidate for the term such annotations cover
  * the Double feature _score_ specifies the confidence into this translation candidate accuracy
  * the Integer feature _rank_ consists in the top-rank of this translation candidate among all other candidates.

### Document Annotation ###

This annotation type is a built-in type provided by the UIMA framework.
It has 2 main features:
  * a String feature _text_ that owns all the text content to process;
  * a String feature _language_ that specifies the language of the previous feature value.

### Source Document Information ###

The annotation type _Source Document Information_ is directly provided by the Apache UIMA framework. The features that are used with Term Suite:
  * the String feature _uri_ that specifies the standardized identifier of the source document
  * the Boolean feature _lastSegment_ that specifies if this document is the last one to process in a given corpora.

## UIMA Analysis Engines ##

There are 210 XML descriptors of UIMA Analysis Engines within Term Suite.
All of these analysis engines share the TTC type system previously presented.
UIMA analysis engines can be:
  * either primitive ones i.e. they are defined by a Java class that corresponds to a UIMA annotator;
  * or aggregate ones i.e. they are defined by a list of analysis engines (primitives or aggregates).
UIMA annotators are defined by a first method at initialization for configuration needs and
a second method for processing data.

Every analysis engines can be configured (1) by some typed parameter values and (2) by some resources.
Within the UIMA framework, a resource has only one instance, a resource is shared between every instances
of an analysis engine and it can be shared between several different (instances of) analysis engines
(if an aggregate analysis engine declares such a resource and binds it to resources
to several of its delegated analysis engines).

Processed data within the UIMA framework are called Common Analysis Structures and can be seen as a structure equipped by:
  * a text
  * an annotation index
  * a type system
such that every annotation type in the index belongs to the type system
and inf. and sup. limits of annotations correspond to the text offsets that this annotation applies to.
UIMA analysis engine behaviour consists in modifying the annotation index.

That's why UIMA analysis engines involved within Term Suite are described below
according to their configuration settings from their parameters and their resources
and according to their behaviour in the case of primitive analysis engines
or their analysis engine hierarchy in the case of aggregates ones.

UIMA analysis engines involved within Term Suite are abstract or generic ones.
In fact, some of them are called _Language Something_ where _Something_ should
explain its functionality for the best and where _Language_ ranges over languages supported
by Term Suite i.e. _English_, _French_, _German,_Spanish_,_Russian_,_Danish_,_Latvian_and_Chinese_.
In fact, every UIMA analysis engines within Term Suite behave the same way
although they are configured by different parameters and resources._

These UIMA analysis engines are divided into 3 main groups: the first one
corresponds to those that are involved in the Spotter tool and they are called _Language Spotter_;
the second group corresponds to analysis engines involved in the Index tool and are called
_Language Indexer_; the last group merely called _Aligner_ corresponds to analysis engines
realizing the Aligner tool.

### _Language_ Spotter ###

The _Language Spotter_ is an aggregate analysis engine made of the following ones:
  * _Language Word Tokenizer_ that splits a text into lexical units or tokens;
  * _Language TreeTagger_ that performs part-of-speech tagging and lemmatization thabnks to TreeTagger;
  * _Language Normalizer_ that rewrites TreeTagger tags in every language tag-sets into a unique tag-set compliant with Multext;
  * _Language Stemmer_ that applies Porter's algorithm over every tokens;
  * _Language Term Spotter_ that detects (single and multi-word) term occurrences;
  * _Language Filter_ that filters out stop-word terms;
  * _Language Contextualizer\_that computes contextual window of every single-word term occurrences;
  *_Language Writer_that stores analysis results into XMI files._

#### _Language_ Word Tokenizer ####

UIMA analysis engines that correspond to the _Language Analysis Engine_
are primitive ones. They are all realized by the Java class _[Lexer](http://code.google.com/p/ttc-project/source/browse/modules/uima-tokenizer/sources/uima/sandbox/lexer/engines/Lexer.java)_
but the Latvian one implemented by the Java class [\_TildeTokenizer\_](http://code.google.com/p/ttc-project/source/browse/tags/1.1/sources/eu/project/ttc/engines/TildeTokenizer.java)
and the Chinese one implemented by the Java class [\_ChineseSegmenter\_](http://code.google.com/p/ttc-project/source/browse/modules/uima-chinese-segmenter/sources/fr/univnantes/lina/uima/engines/ChineseSegmenter.java)

Such tokenizers require one resource that populate 3 character trees which branches
corresponds either to initial segments to cut off from tokens,
or final segments to cut off too, or compound segments.
For example, the resource of the French tokenizer is hold by this [resource](http://code.google.com/p/ttc-project/source/browse/tags/1.1/resources/eu/project/ttc/french/resources/french-segment-bank.xml).

In fact, such tokenizers behave in a 3-pass process.
  1. it splits text into tokens separated by white-spaces
  1. it cuts off initial and final segments of every previously detected tokens
  1. it detects compound tokens
For example, the French compound token "d'une part" is processed as follows:
  1. firstly, two tokens are detected "d'une" and "part" because of the white-space
  1. secondly, three tokens are detected "d'" "une" and "part" because of the initial segment "d'"
  1. finally, only one token is detected because of the compound segment "d'une part".
The latter is the final result.

This analysis engine creates one annotation of type _Word Annotation_ per final token.

#### _Language_ Tree-Tagger ####

This analysis engine merely consists in applying the external executable 'tree-tagger' onto
the token sequence previously detected.

In fact, it fills the feature _tag_ and _lemma_ of annotations of type _Word Annotation_
from the output of TreeTagger.

They are implemented by the Java class _[TreeTaggerWrapper](http://code.google.com/p/ttc-project/source/browse/modules/uima-tree-tagger-wrapper/sources/fr/univnantes/lina/uima/engines/TreeTaggerWrapper.java)_.

Such analysis engines require a simple configuration file that specifies
the parameter file (language model) and its encoding that the tree-tagger executable
should used. For instance, see the [French TreeTagger configuration file](http://code.google.com/p/ttc-project/source/browse/tags/1.1/resources/eu/project/ttc/french/resources/french-treetagger.xml).

This analysis engine depends on the String parameter _TreeTagger Home Directory_
in order to locate both the executable and its parameter files on the user operating system.

#### _Language_ Normalizer ####

_Language Normalizer_ normalizes TreeTagger tags from different tag-sets in different languages
by tags in a unique tag-set: Multext.

UIMA analysis engines _Language Normalizer_ are aggregate ones. They are composed of the following ones:
  * _Language Category Normalizer_ that extracts the Multext category from TreeTagger tags;
  * _Language Subcategory Normalizer_ that extracts the Multext subcategory from TreeTagger tags;
  * _Language Tense Normalizer_ that extracts the Multext tense from TreeTagger verb tags;
  * _Language Mood Normalizer_ that extracts the Multext mood from TreeTagger verb tags;
  * _Language Gender Normalizer_ that extracts the Multext gender from TreeTagger tags;
  * _Language Number Normalizer_ that extracts the Multext number from TreeTagger tags;
  * _Language Case Normalizer_ that extracts the Multext case from TreeTagger tags.

All these aggregated analysis engines are primitives ones that are implemented by
the Jaca class _[Mapper](http://code.google.com/p/ttc-project/source/browse/modules/uima-mapper/sources/uima/sandbox/mapper/engines/Mapper.java)_.
The latter depends on a given tag mapping resource. For example, have a look to the [French category mapping](http://code.google.com/p/ttc-project/source/browse/tags/1.1/resources/eu/project/ttc/french/resources/french-category-mapping.xml)

Indeed, they respectively fill the features _category_, _subCategory_, _tense_, _mood_, _gender_, _number_ and  _case_ of
_word Annotation_ typed annotations.

#### _Language_ Stemmer ####

_Language Stemmer_ analysis engines are primitive ones that are implemented by the Java class _[Stemmer](http://code.google.com/p/ttc-project/source/browse/modules/uima-stemmer/sources/fr/free/rocheteau/jerome/engines/Stemmer.java)_.

It applies Porter's algorithm according to the Tartus Snowball implementation.
It only depends on a String _Language_ parameter.

It provides the feature _stem_ value for any _word annotations_.

#### _Language_ Term Spotter ####

_Language Term Spotter_ are aggregate analysis engines composed of the an analysis engine
that detects single-word term occurrences and another that detects multi-word term occurrences.

Both of them are implemented by the same Java class _[TermSpotter](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/TermSpotter.java)_. Thus, they use the same kind of resources:
regular expressions over annotations.

However, they require different resources.

##### Single-Word Term Spotter #####

In one hand, the UIMA analysis engine _Single-Word Term Spotter_ is language independent
as its [resource](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/all/resources/all-single-word-rule-system.xml) consists in simple regular expressions that define
a single-word term occurrence as a noun, an adjective or a verb.

##### _Language_ Multi-Word Term Spotter #####

In the other hand, UIMA analysis engines _Language Multi-Word Term Spotter_ depends on
a language-oriented resource. In fact, multi-word terms in French and English do not
share the same syntactic patterns. This is a rule-based term occurrence detection algorithm.

For exemple, see the [French syntactic patterns](http://code.google.com/p/ttc-project/source/browse/tags/1.1/resources/eu/project/ttc/french/resources/french-multi-word-rule-system.xml).

#### _Language_ Filter ####

UIMA analysis engines _Language Filter_ load language-dependent stop-words as resources and
remove _term annotations_ either if their covered text or their lemma belongs to this stop-word list.

It is implemented by the _[so-called Java class](http://code.google.com/p/ttc-project/source/browse/modules/uima-filter/sources/uima/sandbox/filter/engines/Filter.java)._

This is an aggregate analysis engine made of the 2 following ones:
  * _Language_ Term Filter that remove term annotations according to their covered text;
  * _Language_ Lemma Filter that remove term annotations according to their lemma.

Stop-words are mainly drawn out from [this site](http://www.ranks.nl/resources/stopwords.html).
For instance, see [those of French](http://code.google.com/p/ttc-project/source/browse/tags/1.1/resources/eu/project/ttc/french/resources/french-stop-word-filter.xml).

#### Contextualizer ####

The _Contextualizer_ analysis engine creates contextual windows for every term annotations previously detected.
It merely depends on an Integer parameter _Size_ set to 7 i.e. 3 term occurrences before and 3 after a given term occurrence.

It is a primitive analysis engine that is implemented by the [so-called Java class](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/Contextualizer.java).

#### Writer ####

The _Writer_ analysis engine stores the previous analysis into XMI files.
It is a primitive one implemented by [the so-called Java class](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/Writer.java).

It require a String parameter _Directory_ to be set that specifies where to write such XMI files.

Within the _Language Spotter_ analysis engine, it outputs 1 XMI file per input text file.

### _Language_ Indexer ###

The UIMA analysis engines _Language Indexer_ provides the monolingual terminologies
from previously analyzed documents of a given corpus.

They are aggregate analysis engines that are composed of:
  * _Term Indexer_ that indexes every term occurrences into term entries;
  * _Language Term Frequency Computer_ that computes the term relative frequencies and their domain specificity;
  * _Language Compound Indexer_ that detects compound term among single-word term entries;
    * _Language Compound Splitter_ that detects word concatenation compounds;
    * _Language Prefix Splitter_ that detects compound by their prefix components;
    * _Language Neoclassical Splitter_ that detects compounds by their neoclassical elements;
  * _Language Term Gatherer_ that conflates terms according to graphical, morphological and syntactical variation rules;
    * Single-Word Term Gatherer_that conflates terms according to graphical variation rules from edit-distance-based similarity;
    *_Language Multi-Word Term Gatherer_that conflates terms according to morpho-syntactic variation rules from conflation patterns;
  *_Language Term Cleaner_that removes hapax from terminologies;
  *_Writer_that stores terminologies in XMI format;
  *_TermBaseXchanger_that stores terminologies in TBX format._

This analysis engine depends on the following mandatory parameters:
  * the String parameter _Language_ that specifies the terminology language;
  * the String parameter _Input Directory_ that specifies where to find XMI input files;
  * the String parameter _Output Directory_ that specifies where to writes the XMI and TBX files.

#### Term Indexer ####

The UIMA analysis engine _Term Indexer_ is a primitive one that is implemented by
[the so-called Java class](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/TermIndexer.java).

This class corresponds to a special UIMA annotator as it output only 1 common analysis structure after
having processed all of the input ones. The latter corresponds to every analysed documents in XMI format
and the output stands for the monolingual terminology.

This analysis engine merely computes term entries from their occurrences.
Occurrences which lemmas are identical are indexed by the same entry.
One annotation _Term Annotation_ is created per entry according to their complexity (single-word vs multi-word).
Absolute frequency is then computed for every term annotations and then fills the Integer feature _occurrences_.

#### _Language_ Term Frequency Computer ####

_Language Term Frequency Computer_ computes both term relative frequency and term domain specificity.
The first computing is language independent whereas the second one depends on a term absolute frequency list
compiled from a general language corpus.

This analysis engine firstly computes relative frequency by dividing each term annotation absolute frequency
by the sum of every term absolute frequencies. The feature _frequency_ of term annotations
are then set.

This analysis engine secondly computes domain specificity by dividing term annotation relative frequency
by the relative frequency of the same term entry within a general corpus frequency list.

#### _Language_ Compound Indexer ####

The _Language Compound Indexer_ is an aggregate analysis engine that detects compounds among single-word terms.
It is composed of the following primitive analysis engines:
  * _Language Compound Splitter_ that detects compounds made of several simplest terms;
  * _Language Prefix Splitter_ that detects compounds made of a prefix and a term;
  * _Language Neoclassical Splitter_ that detects compounds made of neoclassical elements and terms.

##### _Language_ Compound Splitter #####

The _Language Compound Splitter_ is a primitive analysis engine that is implemented by the Java class
_[CompoundSplitter](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/CompoundSplitter.java)_.

It merely consists in detecting single-word term components that are separated by dashes.
It should be enriched by algorithms able to detects those that are not separated by dashes
but that are concatenated together with possible inflections.

##### _Language_ Prefix Splitter #####

The _Language Prefix Splitter_ is a primitive analysis engine that is implemented by the Java class
_[AffixCompoundSplitter](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/AffixCompoundSplitter.java)_.

This analysis engine is configured by a given resource that contains 2 character trees.
Branches of the first correspond to prefix or initial segments, branches of the second correspond to suffix or final ones.
Here, only the first tree is defined by resources of such analysis engines.
For example, see the [French prefix resource](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/Prefix.French).

##### _Language_ Neoclassical Splitter #####

The _Language Neoclassical Splitter_ is a primitive analysis engine that is implemented by the same Java class
_[AffixCompoundSplitter](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/AffixCompoundSplitter.java)_.

Here, this analysis engine resource defines both the first and the second character trees
that are made of neoclassical roots with Greek or Latin origin.
For example, see the [French neoclassical resource](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/RootBank.French).

#### _Language_ Term Gatherer ####

The analysis engine _Language Term Gatherer_ is an aggregate one composed of:
  * _Single-Word Term Gatherer_ that conflates single-word terms together according to a edit-distance-based similarity;
  * _Language Multi-Word Term Gatherer_ that conflates compounds and multi-word terms together according to morpho-syntactic rules.

##### Single-Word Term Gatherer #####

The analysis engine _Single-Word Term Gatherer_ is a primitive analysis engine that is implemented by the Java class
_[SingleWordGatherer](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/SingleWordGatherer.java)_.

The latter is configured by 5 parameters:
  * a Boolean parameter _Enable_ that specifies to enable or not term conflation
  * a String parameter _Edit Distance Class Name_ that specifies on which edit distance the similarity is based
  * a Float parameter _Edit Distance Threshold_ that defines over which values term are conflated together (this value should range from 0 to 1)
  * an Integer parameter _Edit Distance Ngrams_ that defines the length of data for the similarity i.e. unigrams, bigrams or trigrams.
  * a Boolean parameter _Ignore Diacritics In Multiword Terms_ that enables conflation of multiword graphic variants in a diacritic-insensitive manner using the same threshold used for single word terms. If false, no conflation of graphics variants in MWTs is performed at all.

This analysis engine is also configured by a resource that stands for a character equivalence table.
In fact, single-word term annotations are indexed by their first letter up to this equivalence table.
This is implemented as [this mapping resource](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/all/resources/all-character-mapping.xml).
This makes possible to index under the same key term annotations like "énergie" and "energie" as the character 'é'
is rewritten into the character 'e' by this equivalence table.

If the similarity over a couple of terms exceeds the threshold, then it fills the feature _variants_
of the most frequent term annotation of the two terms by the less frequent one.

##### _Language_ Multi-Word Term Gatherer #####

The analysis engine _Language Multi-Word Term Gatherer_ is a primitive one implemented by the Java class
_[MultiWordTermGatherer](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/MultiWordTermGatherer.java)_.

The latter is merely configured by a resource that contains a list of rules for
conflating a couple of terms. Such resources describe under which logical conditions
over their feature values two terms can be conflated together.
For example, see the [French variation rule resource](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-variant-rule-system.xml).

Such analysis engines behave as follows:

(1) they splits the term annotation index in term spaces made of terms indexed by the same key.
These keys are generated by the lemma and stems of term components.
For instance, the French (lemmatized) term "énergie éolien" is only indexed by the key "énergie+éol"
i.e. the lemma of the first component and the stem of the second component.
The order between term components is defined by the lexicographical order.
That's why "énergie" is less than "éolien", hence the key "énergie+éol".
By the way, the other French term "énergie de origine éolien" is indexed by the 3 following keys:
"énergie+éol", "origine+énergi" and "origine+éol".

(2) they applies every variation rules over every couples of terms in every term spaces
indexed as previously described. Such indexes reduce the number of comparisons between term pairs
but preserve term variation specifications as every term variants share at least two components together
up to their lemmas and stems.

(3) If a rule match a couple of terms, then it fills the feature _variants_
of the most frequent term annotation by the less frequent one.

#### Term Cleaner ####

_Term Cleaner_ is a primitive analysis engine that is implemented by the so-called Java class _[TermCleaner](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/TermCleaner.java)_.

It merely consists in removing term annotations from index which absolute frequency
does not reach a threshold provided by a Integer parameter _Threshold_.

#### Writer ####

The _Writer_ analysis engine stores the previous analysis into XMI files.
It is a primitive one implemented by [the so-called Java class](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/Writer.java).

It require a String parameter _Directory_ to be set that specifies where to write such XMI files.

Within the _Language Indexer_ analysis engine, it outputs 1 XMI file for the monolingual terminology.

#### Term Base eXchanger ####

The _Term Base eXchanger_ analysis engine stores the previous analysis into XMI files.
It is a primitive one implemented by [the so-called Java class](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/TermBaseXchanger.java).

It requires by 4 parameters:
  * a String parameter _Directory_ to be set that specifies where to write the TBX ouput
  * a Boolean parameter _KeepVerbsAndOthers_ that specifies whether to include verbs in the TBX output. By defaults it only includes nouns and adjectives
  * a String parameter _FilterRule_ that can be used to select output terms. It can take one of the following values:
    1. None: No filtering
    1. OccurrenceThreshold: Filter by number of occurrence
    1. FrequencyThreshold: Filter by relative frequency
    1. SpecificityThreshold: Filter by specificity
    1. TopNByOccurrence: Filter by cut-off rank in descending occurrence order
    1. TopNByFrequency: Filter by cut-off rank in descending relative frequence order
    1. TopNBySpecificity: Filter by cut-off rank in descending specificity order
  * a Float parameter _FilterRuleThreshold_ that that is used along with the _FilterRule_ and can be either a threshold or a cut-off rank.


Within the _Language Indexer_ analysis engine, it outputs 1 TBX file for the monolingual terminology.

### Aligner ###

The _Aligner_ is an aggregate analysis engine composed of 3 primitive analysis engines:
  * _Term Dispatcher_ that prepare the term to translate and its eventual correct translations
  * _Term Aligner_ that process alignment methods on terms to translate from source and target terminologies previously generated and produced its translation candidates
  * _Writer_ that outputs a XMI file per term to translate with its translation candidates

#### Term Dispatcher ####

The analysis engine _Term Dispatcher_ is a primitive analysis engine that initializes the term to translate.
It is implemented by the Java class _[TermPopulater](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/TermPopulater.java)_.

It reads texts made of only 1 line and with tabulation-separated values as follows:
```
  wind energy	énergie éolien
```

It then create 1 annotation of type _Term Annotation_ that covers the first element
e.g. "wind energy" in the example above and it creates 1 annotation of type _Translation Annotation_
per elements after the first one (e.g. "énergie éolien" in this case).

The term annotation corresponds to the term to translate whereas the translation annotations
can be useful for evaluating the alignment according to precision measure.

It is merely configured by 2 parameters: a String parameter _Source Language_ and a String parameter _Target Language_
used for setting feature values of translation annotations and document annotations.

#### Term Aligner ####

The _Term Aligner_ analysis engine is a primitive one. It is implemented by the Java class
_[TermAligner](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/TermAligner.java)_.

It processes alignment using both the distributional method or the compositional one, then writes the result as a bilingual TBX file.

It is configured by 2 so-called Boolean parameters _Distributional Method_ and _Compositional Method_.

This analysis engine is also configured by 2 String parameters _Source Terminology File_
and _Target Terminology File_ that should refer to the XMI files that respectively stand for
the terminologies in source and target language.

This also can (and should) be configured by a String parameter _Dictionary File_ that
provide single-word term alignments in the following format:
```
  energy	énergie
  energy	force
  energy	puissance
  ...
```
Dictionaries comply with a 1-tabulation separated values format
which left item is a term in the source language and which right item
is one of its possible translations.

Moreover, the similarity distance used within the distributional method can be configured
by the String parameter _Similarity Distance Class Name_ which value should range over
_Jaccard_ for the weighted Jaccard similarity and _Cosine_ for the Cosine one.

Finally, it requires a String parameter _AlignerOutputDirectory_ that will contain the output TBX file, and an Integer parameter _MaxTranslationCandidates_ that will restrict the number of translation candidates to a fixed number in the bilingual TBX output.

This analysis engine behaves as follows:

(1) It retrieves the term to translate from the source terminology.

(2) If the compositional method is enabled and if the term to translate corresponds
either to a compound single-word term or a multi-word term, then a basic compositional method is launched:
  * every term components are retrieved
  * every term component are translated using the dictionary
  * every term component translation combinations are generated
  * every possible combination permutations are generated
  * every permutations are flattened in order to produce translation candidates
  * every translation candidates are filtered in according to the target terminology
  * a translation candidate annotation is created per retained translation candidate

(3) if the distributional method is enabled and if the term to translate is a single-word one
then the standard approach is launched by:
  * the context vector of the term is retrieved from the source terminology
  * every term in this context vector is translated by the help of the dictionary
  * the translated context vector is compared against every term context vectors of the target terminology
  * the translation candidates are sorted according to the decreasing order of their scores
  * the 100 best-score translation candidates are annotated by translation candidate annotations

#### Writer ####

The _Writer_ analysis engine stores the previous analysis into XMI files.
It is a primitive one implemented by [the so-called Java class](http://code.google.com/p/ttc-project/source/browse/trunk/sources/eu/project/ttc/engines/Writer.java).

It require a String parameter _Directory_ to be set that specifies where to write such XMI files.

Within the _Aligner_ analysis engine, it outputs 1 XMI file per input term-to-translate file.

## Resource XSD ##

This section lists every UIMA component resources and provide links to the corresponding format (XSD files) and a example (XML files).

This is useful if you intend to extend or configure Term Suite with your own resources.

### Language Word Tokenizer ###

  * [XSD definition](http://code.google.com/p/ttc-project/source/browse/modules/uima-tokenizer/resources/segments.xsd)
  * [XML example](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-segment-bank.xml)

### Language Tree Tagger ###

  * [XSD definition](http://java.sun.com/dtd/properties.dtd)
  * [XML example](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-treetagger.xml)

### Language Normalizer ###

  * [XSD definition](http://code.google.com/p/ttc-project/source/browse/modules/uima-mapper/resources/mapping.xsd)
  * [XML example](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-category-mapping.xml)

### Language Term Spotter ###

  * [XSD definition](http://code.google.com/p/ttc-project/source/browse/modules/uima-pattern-matcher/resources/patterns.xsd)
  * [XML example](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-multi-word-rule-system.xml)

### Language Filter ###

  * [XSD definition](http://code.google.com/p/ttc-project/source/browse/modules/uima-filter/resources/filter.xsd)
  * [XML example](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-stop-word-filter.xml)

### Language Term Frequency Computer ###

  * such resources are text files with 3 items per line that should be separated by 2 colons and where the first corresponds to the term and the last to its absolute frequency in a general language corpus
  * [text example](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/GeneralLanguage.French)

### Language Compound Splitter ###

  * such resources are text files with only one item per line that should either start by a dash or end with a dash
  * [text example](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/RootBank.French)

### Language Term Gatherer ###

  * [XSD definition](http://code.google.com/p/ttc-project/source/browse/modules/uima-catcher/resources/rules.xsd)
  * [XML example](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-variant-rule-system.xml)