package org.bolodev.mv.ui.panels;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

/**
 * Panel to display and image
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class ImagePanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 2807659675600167955L;
	private JScrollPane pnScroll;
	private JLabel lblImage;
	
	/**
	 * Constructor
	 */
	public ImagePanel(){
		initialise();
	}
	
	/**
	 * Initialise the UI components
	 */
	private void initialise(){
		setBorder(new EtchedBorder());
		setLayout(new BorderLayout());
		lblImage = new JLabel();
		pnScroll = new JScrollPane(lblImage);
		add(pnScroll, BorderLayout.CENTER);
	}
	
	/**
	 * Set the image to display
	 * @param anImage the image to display
	 */
	public void addImage(BufferedImage anImage){
		lblImage.setIcon(new ImageIcon(anImage));
	}

}
