package eu.project.ttc.tools.spotter;

import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

/**
 * This interface declares the parameters as well as the associated methods
 * (accessor and listeners) that should be available by any view or model
 * related to the Spotter tool.
 *
 * @author Fabien Poulard <fpoulard@dictanova.com>
 * @date 15/08/13
 */
public interface SpotterBinding {

    /** Prefix of the Spotter properties */
    public final static String EVT_PREFIX = "spotter.";
    /** Name of the property linked to the language parameter */
    public final static String EVT_LANGUAGE = "spotter.language";
    /** Name of the property linked to the input directory parameter */
    public final static String EVT_INPUT    = "spotter.inputdirectory";
    /** Name of the property linked to the output directory parameter */
    public final static String EVT_OUTPUT   = "spotter.outputdirectory";
    /** Name of the property linked to the treetagger home parameter */
    public final static String EVT_TTGHOME  = "spotter.ttgdirectory";

    ///////////////////////////////////////////////////////////// LANGUAGE

    /**
     * Setter for the language parameter value.
     * @param language  two letters language code
     */
    void setLanguage(String language);

    /**
     * Accessor to the language parameter value.
     */
    String getLanguage();

    /**
     * Listener to a change regarding the language parameter.
     */
    void addLanguageChangeListener(PropertyChangeListener listener);

    ///////////////////////////////////////////////////////////// INPUT DIRECTORY

    /**
     * Setter for the input directory parameter value.
     * @param inputDirectory  path to the directory to use
     */
    void setInputDirectory(String inputDirectory);

    /**
     * Accessor to the input directory parameter value.
     */
    String getInputDirectory();

    /**
     * Listener to a change regarding the input directory parameter.
     */
    void addInputDirectoryChangeListener(PropertyChangeListener listener);

    ///////////////////////////////////////////////////////////// OUTPUT DIRECTORY

    /**
     * Setter for the output directory parameter value.
     * @param outputDirectory  path to the directory to use
     */
    void setOutputDirectory(String outputDirectory);

    /**
     * Accessor to the output directory parameter value.
     */
    String getOutputDirectory();

    /**
     * Listener to a change regarding the output directory parameter.
     */
    void addOutputDirectoryChangeListener(PropertyChangeListener listener);

    ///////////////////////////////////////////////////////////// TREETAGGER DIRECTORY

    /**
     * Setter for the treetagger directory parameter value.
     * @param treetaggerHome    path to the directory to use
     */
    void setTreetaggerHome(String treetaggerHome);

    /**
     * Accessor to the treetagger directory parameter value.
     */
    String getTreetaggerHome();

    /**
     * Listener to a change regarding the treetagger directory parameter.
     */
    void addTtgDirectoryChangeListener(PropertyChangeListener listener);

}