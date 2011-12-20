package eu.project.ttc.tools.converter.tbx;

import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.ConfigurationParameterDeclarations;

import eu.project.ttc.tools.converter.ConverterSettings;
import eu.project.ttc.tools.utils.Parameters;

public class TbxXmiConverterSettings extends Parameters implements ConverterSettings {	
	
	public TbxXmiConverterSettings(String resource) {
		super(resource);
	}

	protected void setMetaData(ConfigurationParameterDeclarations declarations) {
		this.addParameter(declarations, "InputFile", ConfigurationParameter.TYPE_STRING, false, true);
		this.addParameter(declarations, "OutputFile", ConfigurationParameter.TYPE_STRING, false, true);
	}
		
}