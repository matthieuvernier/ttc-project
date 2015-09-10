# ReadMe #

## Description ##

TermSuite is an UIMA application for monolingual and bilingual term extraction
from comparable corpora.

Languages: English, French, German, Spanish, Latvian, Chinese and Russian.

Fp7 European project [TTC](http://www.ttc-project.eu/index.php) _Terminology Extraction, Translation Tools and Comparable Corpora_.


## Dependencies ##

TermSuite is powered by:
  * Java Runtime Environment 7.0 or later (see OpenJDK) ,
  * the framework [Apache UIMA](http://uima.apache.org/),
  * the part-of-speech tagger and lemmatizer [TreeTagger](http://www.ims.uni-stuttgart.de/projekte/corplex/TreeTagger/),
  * the Java library [tt4j](http://code.google.com/p/tt4j/) for embedding TreeTagger in Java.

TermSuite requires that Java 1.7 and TreeTagger are already installed on your computer.

TermSuite embeds The Apache UIMA and tt4j libraries as their licences make it possible.

## Requirements ##

Please check that are installed on your computer:
  * a Java 7.0 Runtime Environment ([Sun](http://www.java.com/fr/download/) or [OpenJDK](http://openjdk.java.net/install/)),
  * the part-of-speech tagger and lemmatizer [TreeTagger](http://www.ims.uni-stuttgart.de/projekte/corplex/TreeTagger/).

**Warning**: TermSuite expects to find TreeTagger's parameter files in the _lib/_ subdirectory of the TreeTagger home directory i.e. the folder where TreeTagger is installed on your computer. By default, these parameter files belong to the _lib/_ subdirectory. Please check that and create a link from  _models/_ to _lib/_ if necessary.

All linguistic resources (corpus, dictionary) should be encoded in UTF8 for all languages.

## Installation ##

- Download the [TTC Term Suite](http://ttc-project.googlecode.com/files/ttc-term-suite-1.5.jar) executable Java archive

- Run it with the command line:

java -Xms1g -Xmx2g -jar ttc-term-suite-1.5.jar

for big corpora increase the memory:

java -Xmx4096m -XX:+UseG1GC -XX:NewRatio=9  -jar ttc-term-suite-1.5.jar

- Have a look to the documentation: GettingStart and UserGuide TermSuite 1.5. (pdf documents to be downloaded)

## Resources ##

- For Monolingual term extraction, Term Suite requires one corpus encoded in UTF8

- For bilingual term extraction, Term Suite requires encoded in UTF8
  * 2 corpora: one in source language and in target language;
  * 1 list of  source terms to translate;
  * a general language bilingual dictionary.

=== Corpora ===[TTC Term Suite](http://ttc-project.googlecode.com/files/ttc-term-suite-1.5.jar)

A monolingual corpora is a set of text files that should be in one directory.
The corpora directory should not contain any subdirectories nor other text files.

**Warning**: TermSuite 1.5 is now abble to treat corpora up to several  million tokens.

### Source terms to translate ###

The list of terms to translate is provided by text files in a directory: one file by term to translate that contains only the term to translate. Such file should look like as: term1.txt
```
  renewable energy
```


Optionally, for evaluation purpose, you can provide one or more correct translations of the given term
by separating the term and each translation by tabulations as below:
```
  énergie	energy	power
```


This can also be used as well for simple terms as for complex terms:
```
  énergie renouvelable	renewable energy
```

### Dictionary ###

Bilingual dictionaries for some pairs of languages are provided that were built from the web [BILINGUAL DICTIONARIES](http://ttc-project.googlecode.com/files/BilingualDicosForTermSuiteAligner.zip)
However, you are strongly recommended to enrich it by your own dictionary. The latter should comply the following format:
a source entry per line separated by a tabulation with
In other words, dictionaries should look like that:
```
  ...
  énergie	energy
  énergie	power
  énergie	vigueur
  ...
  puissance	power
  ...
```