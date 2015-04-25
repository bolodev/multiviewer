package org.bolodev.mv.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import org.bolodev.mv.core.PListViewerController;
import org.bolodev.mv.core.PListViewerModel;
import org.bolodev.mv.mvc.Controller;
import org.bolodev.mv.ui.frames.PListViewerFrame;

/**
 * Action for Select Directory item in file menu
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class SelectDirectoryAction extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -5534171838253402833L;
	private PListViewerController controller;

	/**
	 * Constructor
	 * @param aController Controller to pass to actionPerformed
	 */
	public SelectDirectoryAction(Controller aController){
		super("Select Directory");
		controller = (PListViewerController)aController;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		File currentFile = ((PListViewerModel)controller.getModel()).getDirectory();
		if (currentFile != null) {
			jfc.setCurrentDirectory(currentFile);
			jfc.setSelectedFile(currentFile);
		}
		int returnVal = jfc.showDialog(((PListViewerFrame)controller.getView()), "Select Directory");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			((PListViewerModel)controller.getModel()).setDirectory(jfc.getSelectedFile());
		}
	}

}
