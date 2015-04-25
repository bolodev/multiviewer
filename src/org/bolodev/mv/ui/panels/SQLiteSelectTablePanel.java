package org.bolodev.mv.ui.panels;


import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.bolodev.mv.actions.SQLComboboxListener;
import org.bolodev.mv.mvc.Model;

/**
 * Panel to display ComboBox of table names
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class SQLiteSelectTablePanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 3261109453830311420L;
	private JLabel lblTableSelect;
	private JComboBox bxTableSelect;
	
	/**
	 * Constructor
	 * @param aModel passed to initialise for the ComboBox listener
	 */
	public SQLiteSelectTablePanel(Model aModel){
		initialise(aModel);
	}
	
	/**
	 * Initialise the UI components
	 * @param aModel passed to initialise for the ComboBox listener
	 */
	private void initialise(Model aModel){
		setBorder(new EtchedBorder());
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		lblTableSelect = new JLabel("Tables: ");
		bxTableSelect = new JComboBox();
		bxTableSelect.addActionListener(new SQLComboboxListener(aModel));
		add(lblTableSelect);
		add(bxTableSelect);
	}
	
	/**
	 * Set the list model of table names
	 * @param aListOfTables the list model of table names
	 */
	public void setTableList(DefaultComboBoxModel aListOfTables){
		bxTableSelect.setModel(aListOfTables);
	}

}
