# Change Log #

## Version 1.5 ##

2014/03/17: Updated of the UserGuide TermSuite 1.5

## 1. New features ##

  * New GUI, improved wording and software architecture.
  * Improved scalabilty, TermSuite can handle large corpora of several millions of words.
  * Moved to gradle build tool.
  * Moved to git based repository.

### 1.1  Spotter ###

  * Added TSV output as an option
  * List of occurrences with frequencies in TBX output
  * Added menu for loading existing processed data on the spotter results view.


## 2. Bug fixes/enhancements ##

  * Several small bugfixes and enhancements.
  * New tabbed menus for parameters.

### 2.1  Indexer ###

  * Several menus to distinguish between different parameters
  * Variant detection parameters were separated for better comprehension

### 2.2  Aligner ###

  * Separation of parameters in basic and advanced options.
  * The different alignment methods were clearly separated in different options.

## Version 1.4 ##

## 1. New features ##

  * TSV output for spotter, indexer, aligner
  * File parameters for input of CLI
  * User manual (pdf format) to be downloaded
  * Java program to prepare the directory of terms to be translated as input of the Aligner

### 1.1  Indexer ###

  * Pilot form in TBX output
  * List of occurrences with frequencies in TBX output

### 1.2  Aligner ###
  * compositional and semi-compositional methods added for MWT alignment


## 2. Bug fixes/enhancements ##

### 2.1  Indexer ###

  * Added the Chinese general lexicon required for specificity calculation
  * Enhanced MWT recognition rules for Ru/Lv/Es
  * Enhanced MWT conflation rules for Ru/Lv/Es



## Version 1.3 ##

## 1. New features ##

  * langSet identifier added in the XMI files

### 1.1  Indexer ###
  * Dissociation of XMI files (input of the aligner) and TBX files (output of the monolingual terminology extraction)
  * Detection of graphic variants for MWTs (Ignore Diacritics in Conflation settings)
  * New diacritics-insensitive edit distance in Conflation settings
  * Specifity score in TBX output
  * Verbs and other categories removed from TBX output (unless Keep verbs and others is specified)
  * Statistical filtering of monolingual candidates: by cut-off rank (e.g. top-100, top-250, etc.) or threshold.
  * TBX candidates are ranked according to the filter criteria, or alphabetically if no filtering is done.

### 1.2  Aligner ###
  * Bilingual TBX output following specifications
  * Cut-off rank for translation candidates in bilingual output


## 2. Bug fixes/enhancements ##

  * Progress bar color forced to green
  * Windows shell CLI fixed

### 2.1  Indexer ###
  * Parameter group support for indexer settings (Advanced settings/TBX settings)
  * Added the Latvian and Spanish general lexicons required for specificity calculation (the Latvian lexicon needs to be cleaned)
  * Enhanced MWT recognition rules for En/Fr/De
  * Enhanced MWT conflation rules for En/Fr/De
  * Fail-fast edit distances


## Version 1.2 ##

  * graphical interface improved
  * Latvian processing improved
  * Chinese processing improved

## Version 1.1 ##

  * compound and multi-word alignment compositional method improved
  * term conflation improved (no more extensions)
  * result displays refactorized in tabbed panels

## Version 1.0 ##

  * no more CPE and CR, only AE
  * reshaping in Spotter, Indexer and Aligner

## Version 1.0-rc9 ##

  * added Relater for computing similarity distances between context vectors of a monolingual contextual terminology
  * fixed [issue 7](https://code.google.com/p/ttc-project/issues/detail?id=7)

## Version 1.0-rc8 ##

  * Danish support for Tagger and Termer
  * XML format for resources (tokenizer, tree-tagger, stop-word, rule-based term detection)
  * neoclassical compound alignment

## Version 1.0-rc7 ##

  * refactoring taggers: a tagger button and one tagger engine per tab
  * refactoring converters: a converter button and one converter engine per tab

## Version 1.0-rc6 ##

  * bug fix: the Indexer annotator was running as many times as there are index listeners and not only once
  * bug fix: removing hapax both by filtering raw terms and by filtering their lemma
  * bug fix: enabling term indexation according to their annotation types


## Version 1.0-rc5 ##

  * adding a Converter launcher for converting XMI files into other formats e.g. TSV
  * sorting the terminology view of the Termer tool by frequencies

## Version 1.0-rc4 ##

  * adding a Stemmer for English, French, German, Russian and Spanish into the TreeTagger analysis engine

## Version 1.0-rc3 ##

  * adding Tilde Tagger for processing Latvian
  * adding a term context result viewer for the Contextualizer tool

## Version 1.0-rc2 ##

  * splitting Ziggurat in 2 tools: Contextualizer and Aligner
  * renaming Acabit in Termer

## Version 1.0-rc1 ##

  * CPE workflow split into 3 respectively called TreeTagger, Acabit and Ziggurat
  * GUI refactoring: 1 tool by CPE
  * version presented at IJCNLP

## Version 0.9.1 ##

  * TBX export bug fix

## Version 0.9.0 ##

  * Term Bank XMI serialization and deserialization removed after a "out of memory" exception thrown.
  * Term Bank binary serialization and deserialization added instead.

## Version 0.8.2 ##

  * Term Context Indexer added.

## Version 0.8.1 ##

  * multi-word rules for Spanish, Latvian and Russian added.

## Version 0.8.0 ##

  * initial release of the new TTC TermSuite GUI and CLI interfaces