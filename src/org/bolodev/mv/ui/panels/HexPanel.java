package org.bolodev.mv.ui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import at.HexLib.library.HexLib;

/**
 * Panel to display a hex view of a file
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class HexPanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 6003757318102352855L;
	private HexLib hl;
	
	/**
	 * Constructor
	 */
	public HexPanel(){
		initialise();
	}
	
	/**
	 * Initialise the UI components
	 */
	private void initialise(){
		setBorder(new EtchedBorder());
		setLayout(new BorderLayout());
		
		hl = new HexLib();
		hl.setHexBeanEditable(false);
		add(hl, BorderLayout.WEST);
	}
	
	/**
	 * Set the bytes to be displayed
	 * @param aByteArray the byte array to display
	 */
	public void setBytes(byte[] aByteArray){
		hl.setByteContent(aByteArray);
	}

}
