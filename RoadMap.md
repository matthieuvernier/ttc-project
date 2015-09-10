# Road Map #

## To Do ##

**morphological splitting of compounds (De/Ru)** graphical variants to be solved at the Spotter phase


## Done ##

### Version 1.5 ###

  * gui: new architecture and model handling
  * indexer: scale to bigger corpora using an embedded DB
  * indexer: separate variant detection for gui and processing
  * aligner: separate alignment methods for gui and processing
  * spotter: TSV output as an option
  * spotter: load already processed results

### Version 1.4 ###

  * aligner: compositional method add for MWT
  * indexer: TBX output add of pilot term
  * indexer: TBX output add of list of occurrences
  * indexer: specificity computation for Chinese
  * spotter: TSV output
  * indexer: TSV output
  * aligner: TSV output
  * CLI:  argument of spotter/indexer/aligner could be a file with all the
> > settings of parameters

### Version 1.3 ###

  * indexer: graphical interface improved
  * indexer: spanish and latvian specificity implemented
  * CLI tested for windows
  * aligner: TBX output added

### Version 1.2 ###

  * graphical interface improved
  * Latvian processing improved
  * Chinese processing improved

### Version 1.1 ###

  * compound & multi-word alignment compositional method improved
  * term conflating improved (no more extensions)
  * result display improved and refactorized as tabbed panel

### Version 1.0 ###

  * tokenization
  * part-of-speech tagging
  * lemmatization
  * tag multext normalization
  * stemming
  * simple and complex term spotting
  * stop-word filtering
  * term contextual window computing
  * term indexation
  * term context indexation
  * term frequency and specificty computing
  * simple and complex term variation conflation
  * hapax deletion
  * distributional simple word alignment
  * compositional (neoclassical) compound term alignment
  * compositional multi-word term alignment