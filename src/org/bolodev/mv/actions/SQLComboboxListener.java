package org.bolodev.mv.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.bolodev.mv.core.PListViewerModel;
import org.bolodev.mv.mvc.Model;

/**
 * Listener for the SQLite table list
 * @author bolodev
 * @version 0.1 initial cut
 * @since 0.1
 */
public class SQLComboboxListener implements ActionListener {
	
	private PListViewerModel model;
	
	/**
	 * Constructor
	 * @param aModel interface of the application model
	 */
	public SQLComboboxListener(Model aModel){
		model = (PListViewerModel) aModel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		model.setSQLiteQueryTable((String) cb.getModel().getSelectedItem());
	}

}
