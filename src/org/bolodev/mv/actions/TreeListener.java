package org.bolodev.mv.actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.bolodev.mv.core.PListViewerController;
import org.bolodev.mv.core.PListViewerModel;
import org.bolodev.mv.mvc.Controller;
import org.bolodev.mv.ui.menus.TreePopupMenu;
import org.bolodev.mv.utils.FileNode;

/**
 * Listener for file tree
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.2 removed ModelEvent from ClassCastException, added MouseAdapter to handle right-click popup menu in tree file items
 * @since 0.1
 */
public class TreeListener extends MouseAdapter implements TreeSelectionListener {

	private PListViewerController controller;
	private PListViewerModel model;

	/**
	 * Constructor
	 * @param aController the application controller
	 */
	public TreeListener(Controller aController) {
		controller = (PListViewerController) aController;
		model = (PListViewerModel) aController.getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		FileNode check = null;
		try {
			check = (FileNode) e.getPath().getLastPathComponent();
			if (!check.isDirectory() && check.length() != 0) {
				model.setMetadata(check.getName(), check.length());
				model.setFile(check);
			} else {
				model.setMetadata(check.getName(), 0L);
			}
		} catch (ClassCastException cce) {
			// swallow exception, it is either a valid node or not
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent mevt){
		if(mevt.isPopupTrigger()){
			try{
				FileNode fileNode = (FileNode) ((JTree)mevt.getComponent()).getLastSelectedPathComponent();
				if(fileNode.isFile()){
					TreePopupMenu tpm = new TreePopupMenu(controller);
					tpm.show(mevt.getComponent(), mevt.getX(), mevt.getY());
				}
			}
			catch(NullPointerException npe){
				// swallow exception, happens if no directory tree displays
			}
			catch(ClassCastException cce){
				// swallow exception, happens if no directory tree displays
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent mevt){
		if(mevt.isPopupTrigger()){
			try{
				FileNode fileNode = (FileNode) ((JTree)mevt.getComponent()).getLastSelectedPathComponent();
				if(fileNode.isFile()){
					TreePopupMenu tpm = new TreePopupMenu(controller);
					tpm.show(mevt.getComponent(), mevt.getX(), mevt.getY());
				}
			}
			catch(NullPointerException npe){
				// swallow exception, happens if no directory tree displays
			}
			catch(ClassCastException cce){
				// swallow exception, happens if no directory tree displays
			}
		}
	}

}
