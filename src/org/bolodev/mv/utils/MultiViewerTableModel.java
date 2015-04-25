package org.bolodev.mv.utils;

import javax.swing.table.DefaultTableModel;

/**
 * Extended DefaultTableModel, adapated to remove editing of the table cells
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class MultiViewerTableModel extends DefaultTableModel {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -3553263039912089058L;
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int column){
		return false;
	}

}
