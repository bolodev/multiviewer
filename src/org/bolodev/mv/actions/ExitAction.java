package org.bolodev.mv.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.bolodev.mv.core.PListViewerController;
import org.bolodev.mv.mvc.Controller;

/**
 * Handler for Exiting PListViewer application
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class ExitAction extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -6641857833362771547L;
	private PListViewerController controller;
	
	/**
	 * Constructor
	 * @param aController Controller to pass to actionPerformed
	 */
	public ExitAction(Controller aController){
		super("Exit");
		controller = (PListViewerController)aController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.exitApplication();
	}

}
