package org.bolodev.mv.core;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.bolodev.mv.mvc.AbstractController;
import org.bolodev.mv.ui.frames.PListViewerFrame;

/**
 * Application controller class
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class PListViewerController extends AbstractController {

	/**
	 * Constructor, instantiates Model and View
	 */
	public PListViewerController(){
		setModel(new PListViewerModel());
		setView(new PListViewerFrame(getModel(), this));
	}
	
	/**
	 * Start the UI
	 */
	public void run(){
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
		      ((PListViewerFrame)getView()).initialise("MultiViewer", 600, 800);
		  	  ((PListViewerFrame)getView()).setVisible(true);
	       }
	    });
	}
	
	/**
	 * Show a confirm dialog to exit the application
	 */
	public void exitApplication(){
	    int exit = JOptionPane.showConfirmDialog((PListViewerFrame)getView(), "Quit Application?", "Quit", JOptionPane.YES_NO_OPTION);
	    if (exit == JOptionPane.YES_OPTION) {
	      System.exit(0);
	    }
	}
	
}
