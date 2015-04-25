package org.bolodev.mv.ui.menus;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.EtchedBorder;

import org.bolodev.mv.actions.AboutAction;
import org.bolodev.mv.actions.DecodeBase64Action;
import org.bolodev.mv.actions.ExitAction;
import org.bolodev.mv.actions.SelectDirectoryAction;
import org.bolodev.mv.mvc.Controller;

/**
 * UI menu bar
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class MainMenubar extends JMenuBar {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 6283273741404734792L;
	private JMenu mnFile;
	private JMenu mnHelp;
	
	/**
	 * Constructor
	 * @param aController passes controller to initialise Menu items
	 */
	public MainMenubar(Controller aController){
		initialise(aController);
	}

	/**
	 * Initialise the Menu items
	 * @param aController passes controller to initialise Menu items
	 */
	private void initialise(Controller aController){
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		mnFile = new JMenu("File");
		mnFile.add(new SelectDirectoryAction(aController));
		mnFile.add(new DecodeBase64Action(aController));
		mnFile.addSeparator();
		mnFile.add(new ExitAction(aController));
		
		mnHelp = new JMenu("Help");
		mnHelp.add(new AboutAction(aController));
		
		add(mnFile);
		add(mnHelp);
	}
	
}
