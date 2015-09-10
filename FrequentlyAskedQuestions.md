# Frequently Asked Questions #



If you have any questions, please check if it has not already been asked.
Otherwise, please let a comment or add an issue [here](http://code.google.com/p/ttc-project/issues/list).

## User Questions ##

### What do I need to install TermSuite? ###

You only need:
  * to install a Java Runtime Environment 7.0 or later (see [OpenJDK](http://openjdk.java.net/install/))
  * to install [TreeTagger](http://www.ims.uni-stuttgart.de/projekte/corplex/TreeTagger/)
  * to download [TermSuite](http://ttc-project.googlecode.com/files/ttc-term-suite-1.5.jar) on your computer

That's all!

### How can I test TermSuite? ###

Please, have a look to the GettingStarted wiki page.

### Does TermSuite run on Windows? ###

It has been successfully tested under Windows XP 2002 SP3.
For other Window releases, we recommand to use CLI.
An example of a CLI for windows: http://ttc-project.googlecode.com/files/TermSuite1.4CLI-Fr-en.sh

### Does TermSuite run on Linux? ###

TermSuite has been successfully tested under Linux 11.4 and 11.10 with OpenJDK (1.6.0-23) etiher with the GUI or the CLI interface.

### Can I launch TermSuite without in CLI only? ###

YES but you need one command line for the spotter, one for the indexer and one for the aligner.
In fact, Term Suite GUI makes possible to launch different
UIMA analysis engines from the same control panel.
An example of a CLI bash file to execute French to English bilingula terminology: http://ttc-project.googlecode.com/files/TermSuite1.4CLI-Fr-en.sh

However, **every UIMA analysis engines can be launched in CLI**
by the help of the inner devoted Java class _TermSuiteRunner_.
For example, the French Spotter can be launched over UTF-8 text files s follows:

Notice that every UIMA analysis engines can be launched by other launchers like [Apache UIMA RunAE](http://svn.apache.org/viewvc/uima/uimaj/trunk/uimaj-tools/src/main/java/org/apache/uima/tools/RunAE.java?view=markup), [Apache UIMA Document Analyzer](http://svn.apache.org/viewvc/uima/uimaj/trunk/uimaj-tools/src/main/java/org/apache/uima/tools/docanalyzer/DocumentAnalyzer.java?view=markup), [UIMA SandBox Runner](http://code.google.com/p/uima-sandbox/source/browse/trunk/runner/sources/uima/sandbox/runner/gui/Runner.java), etc.


### Can I process HTML, PDF or DOC files? ###

NO. The spotter works on UTF8 text files.

### Can I process ASCII, LATIN-1 encoded files? ###

NO. TermSuite works only on UTF-8.
To check the encoding use the command:  file -i textfile.txt

### I can't see buttons or some options in the parameters settings. What can I do? ###
You can maximize the window in order to rearrange the layout and see the missing options or a scroll bar. This issue is pretty much dependent on OS and window managers.

## Error Messages ##

The error messages appear in the terminal from which you launch TermSuite.

For any questions about errors from TermSuite, please check that it has not been previously declared in this [bug tracker](http://code.google.com/p/ttc-project/issues/list). If not, please create a new issue.



### I've got an error with the Spotter ###

One issue has been reported: the [issue 9](https://code.google.com/p/ttc-project/issues/detail?id=9).
It comes from the Apache UIMA 2.3.1 library.

But the same version of TermSuite over the same corpus
doesn't lead to the same error on other boxes
with the same operating system.

### I've got an error with the Indexer ###

### I've got an error with the Aligner ###

## Advanced User Questions ##

Advanced user questions involve to modify Term Suite source code and to recompile and repackage Term Suite.

In order to modify and package Term Suite from sources, we strongly suggest you to use [Eclipse IDE](http://www.eclipse.org/) with [Subversion](http://www.eclipse.org/subversive/), [Maven](http://www.eclipse.org/m2e/) and [UIMA](http://uima.apache.org/downloads.cgi) plugins.

### How to add, remove or modify a term detection rule? ###

Assume that I would like to add a term detection rule in French.
So I have to edit the XML resource file [resources/eu/project/ttc/french/resources/french-multi-word-rule-system.xml](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-multi-word-rule-system.xml) accoridngly to its XML schema definition ([XSD](http://code.google.com/p/ttc-project/source/browse/modules/uima-pattern-matcher/resources/patterns.xsd)).

Once this is achieved, just change the version number and repackage Term Suite as follows:
```
  sed -i 's/version>1.2/version>1.2-mine.1/' pom.xml
  mvn clean package
```

### How to add, remove or modify a term/variant conflation rule? ###

Assume that I would like to add a term detection rule in French.
So I have to edit the XML resource file [resources/eu/project/ttc/french/resources/french-variant-rule-system.xml](http://code.google.com/p/ttc-project/source/browse/trunk/resources/eu/project/ttc/french/resources/french-variant-rule-system.xml) accoridngly to its XML schema definition ([XSD](http://code.google.com/p/ttc-project/source/browse/modules/uima-catcher/resources/rules.xsd)).

Once this is achieved, just change the version number and repackage Term Suite as follows:
```
  sed -i 's/version>1.2/version>1.2-mine.2/' pom.xml
  mvn clean package
```

### How to add another language in TermSuite? ###

Suppose that I would like to extend TermSuite to Italian.

The main point is that there already exists en [Italian parameter file](ftp://ftp.ims.uni-stuttgart.de/pub/corpora/italian-par-linux-3.1.bin.gz) for TreeTagger! And its [tag-set](ftp://ftp.ims.uni-stuttgart.de/pub/corpora/italian-tagset.txt) is pretty similar as that of French!

First of all, modify the version in the file pom.xml; for example from _1.2_ to _1.2+italian_ and the add Italian to language selection lists in the GUI:
```
  sed -i 's/version>1.2/version>1.2+italian/' pom.xml
  sed -i 's/values:en|fr|de|es/values:en|fr|de|es|it/g' sources/eu/project/ttc/tools/spotter/SpotterSettings.java sources/eu/project/ttc/tools/indexer/IndexerSettings.java sources/eu/project/ttc/tools/aligner/AlignerSettings.java
```

Secondly, go to the directory resources/eu/project/ttc/. Then create the italian UIMA analysis engines and resources by the help of the given GNU Make program as follows:
```
  cd resources/eu/project/ttc/
  make copy target=Italian code=it
  make plug target=Italian code=it
```

Finally, the big job consists in modifying every language resource files in the directory italian/resources in order to fit Italian processing.

### How to modify a general language corpus term frequency list? ###

such resources are text files with 3 items per line that should be separated by 2 colons and where the first corresponds to the term and the last to its absolute frequency in a general language corpus. They look like that:
```
wind energy::noun::15
energy::noun::145
...
```

Just edit such resource files and repackage Term Suite by the help of maven:
```
mvn clean package
```

### How can I change the size of the co-occurrence sliding window ? ###

By default, the size set of the sliding window is 3 : 3 term occurrences before and 3 after a given term occurrence. This calculation in done in the spotter engine at the document level.

This size can be changed by setting the parameter `Size` to your preferred value in the `eu.project.ttc.all.engines.spotter.Contextualizer.xml` descriptor, the application needs to be rebuilt and repackaged in order to changes to be effective.

See http://code.google.com/p/ttc-project/wiki/ReferenceGuide#Contextualizer.

### How can I change the size of the context vectors ? ###

Context vectors are used by the aligner. For the time being, the size of these vectors cannot be changed. The vectors are accumulated globally during indexation.

### What is the association measure for context vectors ? ###

The association measure is used to weight entries of the context vectors, this parameter can be set in the Indexer under the `Context vectors` tab.

### What is the similarity score for context vectors ? ###

The similarity score is used to compare context vectors and determines the ranking of translation candidates during alignment.
This parameter can be set in the Aligner under the `Advanced` parameters tab.