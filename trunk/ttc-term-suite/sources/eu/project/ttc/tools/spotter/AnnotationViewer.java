package eu.project.ttc.tools.spotter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class AnnotationViewer {
	
	private MutableAttributeSet attributes;
	
	private void setAttributes() {
		this.attributes = new SimpleAttributeSet();
	}
	
	private MutableAttributeSet getAttributes() {
		return this.attributes;
	}

	private DefaultStyledDocument document;
	
	private void setDocument() {
		this.document = new DefaultStyledDocument();
		try {
			this.document.insertString(0,"",this.getAttributes());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private DefaultStyledDocument getDocument() {
		return this.document;
	}
	
	private JTextPane text;
	
	private void setText() {
		this.text = new JTextPane();
		this.text.setEditable(false);
		this.text.setPreferredSize(new Dimension(620, 400));
	    this.text.setMinimumSize(new Dimension(200, 100));
	    this.text.setDocument(this.getDocument());
	}
	
	private JTextPane getText() {
		return this.text;
	}
	
	private JScrollPane component;
	
	private void setComponent() {
		this.component = new JScrollPane(this.getText());
	}
	
	public JScrollPane getComponent() {
		return this.component;
	}
	
	public AnnotationViewer() {
		this.setAttributes();
		this.setDocument();
		this.setText();
		this.setComponent();
	}
	
	public void doGo() {
		this.getText().setCaretPosition(this.getDocument().getLength());
	}
	
	public void doGo(int position) {
		this.getText().setCaretPosition(position);
	}
	
	public void doUpdate(String text) throws Exception {
		this.getDocument().remove(0, this.getDocument().getLength());
		this.getDocument().insertString(0, text, this.getAttributes());
	}
	
	public void doEnable(boolean enabled) {
		this.getText().setEnabled(enabled);
		this.getComponent().setEnabled(enabled);
	}

	public void doClear() throws Exception {
		int length = this.getDocument().getLength();
		String text = this.getDocument().getText(0, length);
		this.getDocument().replace(0, length, text, this.getAttributes());
	}
	
	private Integer begin;
	private Integer end;
	
	private void doHide() throws Exception {
		MutableAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setBold(attributes, false);	
		this.getDocument().setCharacterAttributes(this.begin, this.end - this.begin, attributes, false);
	}
	
	public void doShow(int begin, int end) throws Exception {
		if (this.begin != null && this.end != null) {
			this.doHide();
		}
		this.begin = new Integer(begin);
		this.end = new Integer(end);
		this.doSwitch(begin, end);
	}
	
	private void doSwitch(int begin, int end) throws Exception {
		MutableAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setBold(attributes, true);	
		this.getDocument().setCharacterAttributes(begin, end - begin, attributes, false);
	}
	
	public void doSelect(int begin, int end, Color color) throws Exception {
		this.doSelect(begin, end, color, false);
	}
	
	private void doSelect(int begin, int end,Color color,boolean replace) throws Exception {
		MutableAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setBackground(attributes, color);	
		this.getDocument().setCharacterAttributes(begin, end - begin, attributes, replace);
	}

}
