package org.bolodev.mv.actions;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.bolodev.mv.core.PListViewerController;
import org.bolodev.mv.mvc.Controller;

/**
 * Class to handle window closing event
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @since 0.1
 */
public class FrameViewAdapter extends WindowAdapter {

	private PListViewerController controller;
		
	/**
	 * Constructor
	 * @param aController Controller to pass to actionPerformed
	 */
	public FrameViewAdapter(Controller aController){
		controller = (PListViewerController)aController;
	}
	
    /**
     * Pass Window closing event to the AppCore to handle
     */
    public void windowClosing(WindowEvent we){
        controller.exitApplication();
    }
  
}