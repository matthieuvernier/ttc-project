package fr.free.rocheteau.jerome.dunamis.fields;

import java.awt.Component;

public interface Field {

	public void setName(String name);
	
	public String getName();
	
	public void setValue(Object value);
	
	public Object getValue();
	
	public boolean isModified();

	public Component getComponent();
	
	public void setListener(Field f1, Field f2, Field f3);

}
