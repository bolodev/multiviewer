package org.bolodev.mv.ui.panels;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.bolodev.mv.mvc.Model;

/**
 * Panel to display contents of a SQLite table
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class SQLitePanel extends JPanel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = 279331019924319148L;
	private SQLiteSelectTablePanel pnlTableSelect;
	private JTable tblData;
	private JScrollPane pnScroll;
	private SQLiteMessagePanel pnlMessage;

	/**
	 * Contructor
	 * @param aModel passed to initialise for the select list
	 */
	public SQLitePanel(Model aModel){
		initialise(aModel);
	}
	
	/**
	 * Initialise the UI components
	 * @param aModel passed to initialise for the select list
	 */
	private void initialise(Model aModel){
		setBorder(new EtchedBorder());
		setLayout(new BorderLayout());
		
		pnlTableSelect = new SQLiteSelectTablePanel(aModel);
		tblData = new JTable();
		pnlMessage = new SQLiteMessagePanel();
		
		pnScroll = new JScrollPane(tblData);
		add(pnlTableSelect, BorderLayout.NORTH);
		add(pnScroll, BorderLayout.CENTER);
		add(pnlMessage, BorderLayout.SOUTH);
	}
	
	/**
	 * Set the names of the tables of the current SQLite database
	 * @param aListOfTables the tables of the current SQLite database
	 */
	public void setTableList(DefaultComboBoxModel aListOfTables){
		pnlTableSelect.setTableList(aListOfTables);
	}
	
	
	/**
	 * Set the model of the selected table
	 * @param aTableModel the model of the selected table
	 */
	public void setTableModel(DefaultTableModel aTableModel){
		tblData.setModel(aTableModel);
	}

}
