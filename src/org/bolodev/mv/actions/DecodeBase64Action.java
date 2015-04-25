package org.bolodev.mv.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;

import org.bolodev.mv.core.PListViewerController;
import org.bolodev.mv.core.PListViewerModel;
import org.bolodev.mv.mvc.Controller;
import org.bolodev.mv.ui.frames.PListViewerFrame;

/**
 * Action for DecodeBase64 item in file menu
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class DecodeBase64Action extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -6488775514316612128L;
	private PListViewerController controller;
	
	/**
	 * Constructor
	 * @param aController Controller to pass to actionPerformed
	 */
	public DecodeBase64Action(Controller aController){
		super("Decode Base64");
		controller = (PListViewerController)aController;
	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String selected = ((PListViewerFrame)controller.getView()).getSelectedText();
		if(selected != null){
			try {
				((PListViewerModel)controller.getModel()).decodeBase64Text(selected);
			} catch (IOException e1) {
				((PListViewerFrame)controller.getView()).errorMessage("IO Error: " + e1 + "\nCause: " + e1.getCause());
			}
		}
		else{
			((PListViewerFrame)controller.getView()).infoMessage("No text selected.");
		}
	}

}
