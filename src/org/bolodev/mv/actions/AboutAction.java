package org.bolodev.mv.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.bolodev.mv.core.PListViewerController;
import org.bolodev.mv.mvc.Controller;
import org.bolodev.mv.ui.frames.PListViewerFrame;

/**
 * Handler for About Dialog
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.2 updated version number for display
 * @since 0.1
 */
public class AboutAction extends AbstractAction {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -1332496683037740513L;
	private PListViewerController controller;
	private String about = "MultiViewer\nVersion: 0.2\n" +
			"Author: bolodev (c)2013\n\n" +
			"Uses:\n" +
			"Daniel Dreibrodt's Plist library from http://code.google.com/p/plist\n" +
			"Leo Forge's HexLib library from http://hexedit-lib.sourceforge.net/\n" +
			"KaufholdTreeModel from http://www.chka.de/swing/tree/FileTreeModel.html\n" +
			"Robert Futrell's RSyntaxPane from http://www.fifesoft.com/rsyntaxtextarea/\n" +
			"xerial.org SQLite driver from https://bitbucket.org/xerial/sqlite-jdbc";
	
	
	/**
	 * Constructor
	 * @param aController Controller to pass to actionPerformed
	 */
	public AboutAction(Controller aController){
		super("About");
		controller = (PListViewerController)aController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		((PListViewerFrame)controller.getView()).infoMessage(about);
	}

}
