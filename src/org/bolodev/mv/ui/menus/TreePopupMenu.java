package org.bolodev.mv.ui.menus;

import javax.swing.JPopupMenu;

import org.bolodev.mv.actions.ExportSelectedAction;
import org.bolodev.mv.mvc.Controller;

/**
 * Popup menu for the file tree
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.2
 */
public class TreePopupMenu extends JPopupMenu {

	/**
	 * Eclipse UID 
	 */
	private static final long serialVersionUID = 3117319508369484679L;
	
	/**
	 * Constructor
	 * @param aController application controller
	 */
	public TreePopupMenu(Controller aController){
		initialise(aController);
	}
	
	/**
	 * Initialise UI components
	 * @param aController for the Export menu item
	 */
	private void initialise(Controller aController){
		add(new ExportSelectedAction(aController));
	}

}
