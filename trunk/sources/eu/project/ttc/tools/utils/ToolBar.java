package eu.project.ttc.tools.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ToolBar {
	
	private final Dimension dimension = new Dimension(66,33);
	private final Insets insets = new Insets(0,0,0,0);
	private final Color color = new Color(0,0,0,0);

	private JButton quit;
	
	private void setQuit() {
		this.quit = new JButton("Quit");
		this.quit.setActionCommand("quit");
		this.quit.setEnabled(true);
		this.quit.setPreferredSize(dimension);
		this.quit.setMargin(insets);
		this.quit.setBackground(color);
		this.quit.setBorderPainted(false);
	}
	
	public JButton getQuit() {
		return this.quit;
	}
	
	private JButton about;
	
	private void setAbout() {
		this.about = new JButton("About");
		this.about.setActionCommand("about");
		this.about.setEnabled(true);
		this.about.setPreferredSize(dimension);
		this.about.setMargin(insets);
		this.about.setBackground(color);
		this.about.setBorderPainted(false);
	}
	
	public JButton getAbout() {
		return this.about;
	}
	private JButton save;
	
	private void setSave() {
		this.save = new JButton("Save");
		this.save.setActionCommand("save");
		this.save.setEnabled(true);
		this.save.setPreferredSize(dimension);
		this.save.setMargin(insets);
		this.save.setBackground(color);
		this.save.setBorderPainted(false);
	}
	
	public JButton getSave() {
		return this.save;
	}

	private JButton run;
	
	private void setRun() {
		this.run = new JButton("Run");
		this.run.setActionCommand("run");
		this.run.setEnabled(true);
		this.run.setPreferredSize(dimension);
		this.run.setMargin(insets);
		this.run.setBackground(color);
		this.run.setBorderPainted(false);
	}
	
	public JButton getRun() {
		return this.run;
	}
	
	private JButton pause;
	
	private void setPause() {
		this.pause = new JButton("Pause");
		this.pause.setActionCommand("pause");
		this.pause.setEnabled(false);
		this.pause.setPreferredSize(dimension);
		this.pause.setMargin(insets);
		this.pause.setBackground(color);
		this.pause.setBorderPainted(false);
	}
	
	public JButton getPause() {
		return this.pause;
	}
	
	private JButton stop;
	
	private void setStop() {
		this.stop = new JButton("Stop");
		this.stop.setActionCommand("stop");
		this.stop.setEnabled(false);
		this.stop.setPreferredSize(dimension);
		this.stop.setMargin(insets);
		this.stop.setBackground(color);
		this.stop.setBorderPainted(false);
	}
	
	public JButton getStop() {
		return this.stop;
	}
	
	private JProgressBar progressBar;
	
	private void setProgressBar() {
		this.progressBar = new JProgressBar();
		this.progressBar.setPreferredSize(new Dimension(333,33));
		this.progressBar.setStringPainted(true);
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	private JPanel component;
	
	private void setComponent() {
		this.component = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 10, 0, 3);
		c.weightx = 0.0; 
		c.gridx = 0; 
		c.gridy = 0; 
		c.gridwidth = 1; 
		this.component.add(this.getAbout(), c);
		c.gridx = 1; 
		c.insets = new Insets(0, 3, 0, 3);
		c.weightx = 0.0; 
		this.component.add(this.getRun(), c);
		c.gridx = 2; 
		c.insets = new Insets(0, 3, 0, 3);
		c.weightx = 0.0; 
		this.component.add(this.getPause(), c);
		c.gridx = 3; 
		c.insets = new Insets(0, 3, 0, 3);
		c.weightx = 0.0; 
		this.component.add(this.getStop(), c);
		c.insets = new Insets(0, 3, 0, 10);
		c.weightx = 1.0; 
		c.gridx = 4; 
		c.gridwidth = 5; 
		c.fill = GridBagConstraints.BOTH; 
		this.component.add(this.getProgressBar(), c);
		c.gridx = 9;  
		c.insets = new Insets(0, 3, 0, 3);
		c.weightx = 0.0;
		c.gridwidth = 1; 
		this.component.add(this.getSave(), c);
		c.gridx = 10;  
		c.insets = new Insets(0, 3, 0, 3);
		c.weightx = 0.0;
		c.gridwidth = 1; 
		this.component.add(this.getQuit(), c);
	}
	
	public JPanel getComponent(){
		return this.component;
	}
	
	public void enableListeners(ActionListener listener) {
		this.getQuit().addActionListener(listener);
		this.getAbout().addActionListener(listener);
		this.getSave().addActionListener(listener);
		this.getStop().addActionListener(listener);
		this.getPause().addActionListener(listener);
		this.getRun().addActionListener(listener);
	}
	
	public ToolBar() {
		this.setQuit();
		this.setAbout();
		this.setSave();
		this.setRun();
		this.setPause();
		this.setStop();
		this.setProgressBar();
		this.setComponent();
	}
	
}
