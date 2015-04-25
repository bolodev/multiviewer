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
 * Handler for export popup menu
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class ExportSelectedAction extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -7791999923717745295L;
	private PListViewerController controller;
	
	public ExportSelectedAction(Controller aController){
		super("Export Selected");
		controller = (PListViewerController) aController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		File currentFile = ((PListViewerModel)controller.getModel()).getLastExportDirectory();
		if (currentFile != null) {
			jfc.setCurrentDirectory(currentFile);
		}
		int returnVal = jfc.showDialog(((PListViewerFrame)controller.getView()), "Export To Directory");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			((PListViewerModel)controller.getModel()).setLastExportDirectory(jfc.getSelectedFile());
			((PListViewerModel)controller.getModel()).exportSelectedFile();
		}
	}

}
