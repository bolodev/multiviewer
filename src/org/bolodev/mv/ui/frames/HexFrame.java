package org.bolodev.mv.ui.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import at.HexLib.library.HexLib;

/**
 * Simplistic frame to display a Hex Editor
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class HexFrame extends JFrame {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 3265998346508624772L;
	private HexLib hl;
	
	/**
	 * Constructor
	 * @param hexToDisplay the byte array to display
	 */
	public HexFrame(byte[] hexToDisplay){
		initialise(hexToDisplay);
	}
	
	/**
	 * Set up the UI components
	 * @param hexToDisplay
	 */
	private void initialise(byte[] hexToDisplay){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Decoded Base64");
		setSize(600, 600);
		
		hl = new HexLib(hexToDisplay);
		add(hl, BorderLayout.CENTER);
	}
	
}
