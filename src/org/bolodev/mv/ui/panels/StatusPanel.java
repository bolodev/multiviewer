package org.bolodev.mv.ui.panels;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Status bar, displays open file name
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class StatusPanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 7413423766840604306L;
	private JLabel lblOpenFile;
	
	/**
	 * Constructor
	 */
	public StatusPanel(){
		initialise();
	}
	
	/**
	 * Initialise the UI components
	 */
	private void initialise(){
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setLayout(new BorderLayout());
		lblOpenFile = new JLabel("File: ");
		add(lblOpenFile, BorderLayout.WEST);
	}
	
	/**
	 * Set the label in the status panel
	 * @param aString string to display
	 */
	public void setLabelText(String aString){
		lblOpenFile.setText("File: " + aString);
	}

}
