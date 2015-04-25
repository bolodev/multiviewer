package org.bolodev.mv.ui.panels;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;

/**
 * UI display for parsed PList text
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class MainPanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -3161940378970565400L;
	private JScrollPane pnScroll;
	private RSyntaxTextArea txtArea;
	
	/**
	 * Constructor
	 */
	public MainPanel(){
		initialise();
	}
	
	/**
	 * Initialise the UI components
	 */
	private void initialise(){
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setLayout(new BorderLayout());
		
		txtArea = new RSyntaxTextArea();
		txtArea.setEditable(false);
		txtArea.setWrapStyleWord(false);
		txtArea.setHighlightCurrentLine(false);
		try {
			Theme theme = Theme.load(getClass().getResourceAsStream("/theme/dark.xml"));
			theme.apply(txtArea);
		} catch (IOException e) {
			// swallow this exception, cannot find theme xml file
		}
		
		txtArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
		pnScroll = new JScrollPane(txtArea);
		
		add(pnScroll, BorderLayout.CENTER);
	}
	
	/**
	 * Set text string in the JTextArea
	 * @param aString
	 */
	public void setText(String aString){
		txtArea.setText(aString);
		txtArea.setCaretPosition(0);
	}
	
	/**
	 * The text selected by a user
	 * @return string of selected text from JTextArea
	 */
	public String getSelectedText(){
		return txtArea.getSelectedText();
	}
	
	/**
	 * Clear the text in the JTextArea
	 */
	public void clear(){
		txtArea.setText("");
	}

}
