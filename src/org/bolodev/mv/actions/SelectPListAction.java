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
 * Handler for selecting a PList file - defunct
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class SelectPListAction extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -2208433810245763710L;
	private PListViewerController controller;
	
	/**
	 * Constructor
	 * @param aController Controller to pass to actionPerformed
	 */
	public SelectPListAction(Controller aController){
		super("Select PList");
		controller = (PListViewerController)aController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		File currentFile = ((PListViewerModel)controller.getModel()).getFile();
		if (currentFile != null) {
			jfc.setCurrentDirectory(currentFile.getParentFile());
		}
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = jfc.showDialog(((PListViewerFrame)controller.getView()), "Select PList File");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			 try {
				((PListViewerModel)controller.getModel()).setFile(jfc.getSelectedFile());
			} catch (Exception e1) {
				((PListViewerFrame)controller.getView()).errorMessage("Exception Parsing PList:\n" + e1.getMessage() + "\n\nCause:\n" + e1.getCause());
			}
		}

	}

}
