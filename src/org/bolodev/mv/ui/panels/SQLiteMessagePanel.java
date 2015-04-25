package org.bolodev.mv.ui.panels;


import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Panel for displaying messages about the current SQLite database
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class SQLiteMessagePanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 3261109453830311420L;
	
	/**
	 * Constructor
	 */
	public SQLiteMessagePanel(){
		initialise();
	}
	
	/**
	 * Initialise the UI components
	 */
	private void initialise(){
		setBorder(new EtchedBorder());
	}

}
