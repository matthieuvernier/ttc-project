# User Guide #

TTC TermSuite is the name of the tool provided [here](http://ttc-project.googlecode.com/files/ttc-term-suite-1.4.jar) as an executable Java archive.

This user guide page explains how to install and use it.

**NEW** : Download TermSuite User Manual
[pdf version](http://ttc-project.googlecode.com/files/TermSuite-userguide.pdf)

## Contents ##



## Installation ##

### Requirements ###

Before installing Term Suite on your computer,
you have to install a Java Runtime Environment
and the TreeTagger part-of-speech tagger.

Notice that you don't have to install neither the Java library Apache UIMA, nor the Java library tt4j as the TTC TermSuite Java archive already contains them.

#### Java Runtime Environment ####

Several JREs exist: for instance that of [Sun's](http://www.java.com/fr/download/) or [OpenJDK](http://openjdk.java.net/install/).

Just check that the Java program is installed on your computer by running this following command line:

```
  java -version
```

#### Tree Tagger ####

Notice that the TreeTagger executable called _tree-tagger_ on Unix-based systems or _tree-tagger.exe_ on Windows ones is expected in the _bin/_ sub-directory of the TreeTagger home directory.

Moreover, the language model parameter files for TreeTagger are expected to stand in the _models/_ sub-directory of the TreeTagger home one.

If parameter files lie in the _lib/_ subdirectory, please create a symbolic link to this directory as follows:

```
  cd /path/to/tree-tagger-home-directory
  ln -s lib models
```

I order to check if TreeTagger is correctly installed,
please launch this command line:

```
  cd tree-tagger-home-directory
  ./bin/tree-tagger ./models/english.par
```

Exit the program by the keyboard short-cut Ctrl+D.

##### Warnings #####

Make sure that the TreeTagger parameter file that corresponds to the selected language of the Spotter exists in _models/_ subdirectory of the TreeTagger home one. Otherwise, Term Suite will exit abnormally.

Moreover, make sure that these parameter file have the following encoding although TreeTagger lemmatization will return unexpected values. The table above explains for each language which parameter file is expected in the TreeTagger _models/_ subdirectory and its encoding.

| Language | Parameter File | Encoding |
|:---------|:---------------|:---------|
| English  | english.par    | iso8859-1 |
| French   | french.par     | iso8859-1 |
| German   | german.par     | iso8859-1 |
| Spanish  | spanish.par    | iso8859-1 |
| Russian  | russian.par    | utf-8    |
| Danish   | danish.par     | iso8859-1 |
| Latvian  |                |          |
| Chinese  | zh.par         | utf-8    |


**Notice** that you have to rename some of these parameter files!
For example, the French parameter file original name is
> 'french-par-linux-3.2.bin' need to be rename as 'french.par'

### Term Suite ###

As Term Suite is provided as an executable Java archive,
you merely have to download it and put it
wherever you want on your computer.

There is no any packages of Term Suite for any distributions.

Otherwise, you can regenerate the Java archive from
Term Suite source code. You then need both Subversion and Maven2
respectively for getting the source code and for packaging it.

#### Download the binary ####

Just launch this command line or follow its target link:

```
 wget http://ttc-project.googlecode.com/files/ttc-term-suite-1.4.jar
```

#### Compilation from Sources ####

In order to regenerate the Term Suite package,
you have to download the source code from
the Subversion repository hosted by Google Code.
Then, you can generate the Term Suite Java archive.
In few words, just launch these command lines:

```
 svn checkout http://ttc-project.googlecode.com/svn/trunk ttc-term-suite
 cd ttc-term-suite/
 mvn package
```

In order to install Subversion and Maven2 on Unix boxes, please
run this command line as root:
```
  sudo apt-get install subversion maven2
```

## Execution ##

You merely have to launch the following command line:

```
  java -Xms1g -Xmx4g -jar ttc-term-suite-1.4.jar
```

You are strongly suggested to set the JVM options -Xms1g
that allocate 1Go of RAM memory for this program
as terminology compilation and terminology alignment
each requires a certain amount of memory even for corpora
which sizes are around 300.000 tokens.

## Description ##

Having launched the previous command line, you face the Term Suite graphical user interface:

![http://ttc-project.googlecode.com/files/TermSuite-Spotter-Edit.png](http://ttc-project.googlecode.com/files/TermSuite-Spotter-Edit.png)

It is basically made of a tool bar and 3 tabbed panels
respectively called: Spotter, Indexer and Aligner.

### Tool Bar ###

The tool bar contains 5 buttons and a progress bar which acts as follows when activated:

  * the button About pops up a dialog with a tiny description of Term Suite.
  * the button Run executes the UIMA component corresponding to the selected tab
  * the button Stop is enabled when a UIMA component is under processing and interrupts the latter.
  * the progress bar displays the progress of the execution task.
  * the button Save makes possible to store the parameter values of every tabbed panels.
  * the button Quit pops up a confirm dialog for exiting Term Suite.

### Spotter ###

The Spotter tabbed panel has 2 embedded tabbed panels itself.
The first one called Edit makes possible to configure the UIMA component
that can be executed. The View panel displays its processing results.

#### Features ####

In fact, UIMA components that can be executed by the Spotter performs:
  * word tokenization,
  * part-of-speech tagging and lemmatization thanks to TreeTagger,
  * tag normalization from the different language-dependent tagsets of TreeTagger according to the Multext standard,
  * stemming using Porter's algorithm,
  * single-word and multi-word term occurrences detection,
  * stop-word filtering,
  * contextual window computing.

#### Inputs & Outputs ####

It needs UTF-8 text files as inputs

It generates a XMI file in the output directory per each text file in the input directory.

#### Settings ####

The Spotter requires the following parameters to be set:
  * the parameter _Language_ should be one of the languages given in the selection list,
  * the parameter _Input Directory_ should refer to the directory where the text files are,
  * the parameter _Output Directory_ should refer to the folder where  the XMI  files will be written,
  * the parameter _TreeTagger Home Directory_ should refer to the directory where TreeTagger has been installed on your computer.

#### Warnings ####

Make sure that the TreeTagger parameter file that corresponds to the selected language of the Spotter exists in _models/_ subdirectory of the TreeTagger home one. Otherwise, Term Suite will exit abnormally.

Moreover, make sure that these parameter file have the following encoding although TreeTagger lemmatization will return unexpected values. The table above explains for each language which parameter file is expected in the TreeTagger _models/_ subdirectory and its encoding.

| Language | Parameter File | Encoding |
|:---------|:---------------|:---------|
| English  | english.par    | iso8859-1 |
| French   | french.par     | iso8859-1 |
| German   | german.par     | iso8859-1 |
| Spanish  | spanish.par    | iso8859-1 |
| Russian  | russian.par    | utf-8    |
| Danish   | danish.par     | iso8859-1 |
| Latvian  |                |          |
| Chinese  | zh.par         | utf-8    |


#### Latvian ####

There is no TreeTagger for Latvian.  Latvian processing in Term Suite starts
from tagged and lemmatized text files.
Tagged and lemmatized files come from the output of [Tilde's services](https://www.tilde.com/tagger/Service.asmx/) in
a TreeTagger like output:
```
Vējdzirnavas    N       vējdzirnavas    N-fpn---------n-----------f-
ir      V       būt     Vp----3--i----------7-----l-
vismaz  R       vismaz  R----p---------------g----l-
4000    D       4000    D--pg-----------------------
gadu    N       gads    N-mpg---------n-----------l-
vecas   A       vecs    A-fsgp-n------------------l-
.       SENT    .       T--------------------------.
```

To run the spotter for Latvian, fill the txt directory with the tagged and lemmatized files. Give the treetagger directory path even if Treetagger will not be used. You will obtain the xmi  files that could be used for the indexer.

### Indexer ###

The Indexer tabbed panel provides 2 embedded tabbed panels too;
one called Edit for settings parameter values of the corresponding
UIMA components, the other for displaying the monolingual terminology extraction.


![http://ttc-project.googlecode.com/files/TermSuite-Indexer-Edit.png](http://ttc-project.googlecode.com/files/TermSuite-Indexer-Edit.png)

![http://ttc-project.googlecode.com/files/TermSuite-Indexer-Edit2.png](http://ttc-project.googlecode.com/files/TermSuite-Indexer-Edit2.png)


#### Features ####

It makes possible to perform:
  * single-word and multi-word term indexation,
  * term context building and normalization according to an association measure,
  * number of occurrences, relative frequency and domain specificity computing,
  * classical and neoclassical compound detection over single-word terms,
  * graphical syntactical term variant conflating,
  * filtering over the TBX output
    * Threshold: superior or equal to the number choosen by the user
    * TOP: any number of candidate terms inferior to 2000  (variants will be added)


#### Inputs & Outputs ####

It requires as inputs the XMI files previously generated by the Spotter for the corresponding language. It then generates 2 files in the given output directory:
  * a XMI file _language_-terminology.xmi that corresponds to the compiled monolingual terminology,
  * a TBX file _language_-terminology.tbx following the TBX specifications described in TTC  D.3.2


#### Settings ####

The Indexer requires the following parameters to be set:
  * the parameter _Language_ should define defines the language of the XMI files in the input directory,
  * the parameter _Input Directory_ should refer the directory where the XMI files are,
  * the parameter _Output Directory_ should refer to the directory where the XMI and TBX files will be written,
  * the parameter _Hapax Filtering Threshold_ should define a natural integer which value specifies the maximum term occurrences of hapax,
  * the parameter _Association Rate Class Name_ defines the implementation that realizes the association rate: its value could either be either LogLikelihood or MutualInformation,
  * the parameter _Enable Term Conflating_ makes possible to perform term conflation or not,
  * the parameter _Edit Distance Class Name_ defines the implementation that realzes the edit distance based similarity measure: it could either be Levenshtein or LongestCommonSubsequence,
  * the parameter _Edit Distance Threshold_ specifies the minimum value for conflating terms together using the edit-distance approach: it should be a value between 0 and 1,
  * the parameter _Edit Distance Ngrams_ defines the numbers of charcters taken in account while measuring the edit distance between two terms: it should be an integer between 1 and 3.


### Indexer view ###

**Warning:** On the contrary on the spotter and the aligner, to view the results of the indexer, you need to load the language-terminology.xmi file. Select the View tab panel. Press the right mouse touch and select "Load from file". Once the terminology is loaded, you can sort the results alphabetically, or by frequency or by specificity. Press the right mouse touch and select the sort.

![http://ttc-project.googlecode.com/files/termSuiteImage.jpg](http://ttc-project.googlecode.com/files/termSuiteImage.jpg)


### Aligner ###

The Aligner tabbed panel has 2 embedded tabbed panels itself: the first called Edit makes possible to configure the UIMA component that performs terminology alignment, the second called View enables translation and evaluation display.

![http://ttc-project.googlecode.com/files/TermSuite-Aligner-Edit.png](http://ttc-project.googlecode.com/files/TermSuite-Aligner-Edit.png)

![http://ttc-project.googlecode.com/files/TermSuite-Aligner-Edit2.png](http://ttc-project.googlecode.com/files/TermSuite-Aligner-Edit2.png)


#### Features ####

The Aligner performs:
  * single-word term alignment using the distributional method,
  * classical and neoclassical compounds alignment using the compositional method,
  * multi-word term alignment using the compositional method.

#### Inputs ####

It requires several kind of files as inputs.
  * a source-target directory that contains the text files with the terms to be translated (see subsection below),
  * two XMI files that contain the terminology in the source and the target languages computed by the indexer
  * a bilingual dictionary (source-target)

#### Outputs ####

TermSuite aligner generates 2 files in the given output directory:
  * a XMI file that contains the terminology in the target language,
  * a TBX file _sourceLanguage_-_targetLanguage_-terminology.tbx that contains for each source term, candidate translations ranked

There is one XMI file per text file (or term to be translated) in the source-target directory.


##### Source-target directory #####
The source-target directory contains as files as terms to translate.
Such files merely contains one term that is the lemma of a candidate term proposed by the indexer. As an example, the English multi-word term:
```
  renewable energy
```

You can use the Java program

http://ttc-project.googlecode.com/files/Aligner-source-target-directory-builder.zip

that is designed to build from one text file listing all the terms to be translated a the source-target directory with one file by term.

**Note:** These term files should be UTF-8 encoded and all entries should be in lower case letters!

The aligner can also be used in an evaluation mode. If you want to know at which rank the good translation appears in the list of ranked candidate translations for a given source term, you can add in the text file with the  source term its translations. The translation should be in a the lemma form and should be proposed as a candidate term by the indexer in the target language.  The source term and the translations should be separated by tabulations. As an example, for an English translation in French of the term renewable energy, two valid translations are given:
```
  renewable energy	énergie renouvelable	énergie de origine renouvelable
```


**Warning:** Notice that the target language is implicit
and users should pay attention to set the right source and target terminologies according to these input term files.



##### Bilingual Dictionary #####

A Bilingual dictionary is a tabulation-separated value file where
the source entry stands in first and its translation in second.
Here is an chunk of the English-French bilingual dictionary.
```
  energy	énergie
  energy	tonus
  renewable	renouvelable
  power	énergie
```
A source entry can have more than one target entries.

**Note:** These dictionaries should be UTF-8 encoded and all entries should be in lower case letters!

We provide bilingual dictionaries of general language compiled from the web for some pairs of languages:
http://ttc-project.googlecode.com/files/BilingualDicosForTermSuiteAligner.zip
Quality is not guaranteed!

#### Settings ####

The Aligner

The Aligner requires the following parameters to be set:
  * the parameter _Directory_ should refer to the directory where the term files are,
  * the parameter _Source Language_ specifies the source language,
  * the parameter _Source Terminology File_ should refer to the XMI file that stands for the terminology in the source language,
  * the parameter _Target Language_ specifies the target language,
  * the parameter _Target Terminology File_ should refer to the XMI file that stands for the terminology in the target language,
  * the parameter _Similarity Distance Class Name_ defines the implementation that realizes this similarity distance: it could either be Jaccard or Cosine,
  * the parameter _Dictionary File_ may refer to the bilingual dictionary,
  * the parameter _Distributional Method_ enables the distributional method if checked,
  * the parameter _Compositional Method_ enables the compositional method if checked.

After running the aligner, the results are display by clicking on the "VIEW" tab.