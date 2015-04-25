package org.bolodev.mv.ui.frames;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import org.bolodev.mv.actions.FrameViewAdapter;
import org.bolodev.mv.actions.TreeListener;
import org.bolodev.mv.core.PListViewerModel;
import org.bolodev.mv.mvc.Controller;
import org.bolodev.mv.mvc.JFrameView;
import org.bolodev.mv.mvc.Model;
import org.bolodev.mv.mvc.ModelEvent;
import org.bolodev.mv.ui.menus.MainMenubar;
import org.bolodev.mv.ui.panels.DiskTreePanel;
import org.bolodev.mv.ui.panels.HexPanel;
import org.bolodev.mv.ui.panels.ImagePanel;
import org.bolodev.mv.ui.panels.MainPanel;
import org.bolodev.mv.ui.panels.SQLitePanel;
import org.bolodev.mv.ui.panels.StatusPanel;
import org.bolodev.mv.utils.KaufholdTreeModel;

/**
 * Main UI frame
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.1.1 added INFO event
 * @since 0.1
 */
public class PListViewerFrame extends JFrameView {

	/**
	 * Eclipse UID
	 */
	private static final long serialVersionUID = -9170230032374145787L;
	private MainMenubar brMenu;
	private JSplitPane pnSplit;
	private DiskTreePanel pnlDiskTree;
	private MainPanel pnlMain;
	private StatusPanel pnlStatus;
	
	private JTabbedPane pnTabs;
	private SQLitePanel pnlSQLite;
	private ImagePanel pnlImage;
	private HexPanel pnlHex;
	
    /**
     * Constructor
     * @param model
     * @param controller
     */
	public PListViewerFrame(Model model, Controller controller) {
		super(model, controller);
		initialise(controller);
	}
	
	/**
	 * Initialise the UI components
	 * @param controller passed to components
	 */
	private void initialise(Controller controller){
		setDefaultCloseOperation(JFrameView.DO_NOTHING_ON_CLOSE);
		addWindowListener(new FrameViewAdapter(controller));
		
		brMenu = new MainMenubar(controller);
		pnSplit = new JSplitPane();
		pnTabs = new JTabbedPane();
		pnlDiskTree = new DiskTreePanel(new TreeListener(controller));
		pnlMain = new MainPanel();
		pnlSQLite = new SQLitePanel(getModel());
		pnlImage = new ImagePanel();
		pnlHex = new HexPanel();
		
		pnTabs.add(pnlMain, "Plist");
		pnTabs.add(pnlSQLite, "SQLite");
		pnTabs.add(pnlImage, "Image");
		pnTabs.add(pnlHex, "Hex");
		
		pnSplit.setLeftComponent(pnlDiskTree);
		pnSplit.setRightComponent(pnTabs);
		pnSplit.setDividerSize(5);
		
		pnlStatus = new StatusPanel();
		
		setJMenuBar(brMenu);
		getContentPane().add(pnSplit, BorderLayout.CENTER);
		getContentPane().add(pnlStatus, BorderLayout.SOUTH);
	}
	
	/**
	 * Display basic hex view of decoded Base64 text
	 * @param anObject byte[] from ModelEvent.getObject()
	 */
	private void showBase64Decoded(Object anObject){
		HexFrame b64Window = new HexFrame((byte[]) anObject);
		b64Window.setVisible(true);
	}

	/**
	 * Get the text selected in the JTextArea
	 * @return the text selected in the JTextArea
	 */
	public String getSelectedText(){
		return pnlMain.getSelectedText();
	}
	
	/**
	 * Display an error message
	 * @param anExceptionMessage the error message to display
	 */
	public void showExceptionMessage(String anExceptionMessage){
		this.errorMessage(anExceptionMessage);
	}
	
	/* (non-Javadoc)
	 * @see org.lamora.plv.mvc.ModelListener#modelChanged(org.lamora.plv.mvc.ModelEvent)
	 */
	@Override
	public void modelChanged(ModelEvent event) {
		if(event.getActionCommand().equals("Directory Tree")){
			pnlDiskTree.setNewFileTree((KaufholdTreeModel) event.getObject());
		}
		if(event.getActionCommand().equals("Metadata")){
			Object[] objs = (Object[])event.getObject();
			pnlDiskTree.setFileMeta((String)objs[0], ((Long)objs[1]).longValue());
		}
		if(event.getActionCommand().equals("Parsed")){
			pnlMain.setText((String) event.getObject());
			pnlStatus.setLabelText(((PListViewerModel)getModel()).getFile().getAbsolutePath());
			pnTabs.setSelectedIndex(0);
		}
		if(event.getActionCommand().equals("Decoded")){
			showBase64Decoded(event.getObject());
		}
		if(event.getActionCommand().equals("SQLITELIST")){
			pnlSQLite.setTableList((DefaultComboBoxModel) event.getObject());
			pnlStatus.setLabelText(((PListViewerModel)getModel()).getFile().getAbsolutePath());
			pnTabs.setSelectedIndex(1);
		}
		if(event.getActionCommand().equals("SQLITETABLE")){
			pnlSQLite.setTableModel((DefaultTableModel) event.getObject());
		}
		if(event.getActionCommand().equals("IMAGE")){
			pnlImage.addImage((BufferedImage) event.getObject());
			pnlStatus.setLabelText(((PListViewerModel)getModel()).getFile().getAbsolutePath());
			pnTabs.setSelectedIndex(2);
		}
		if(event.getActionCommand().equals("EXCEPTION")){
			showExceptionMessage((String) event.getObject());
		}
		if(event.getActionCommand().equals("HEX")){
			pnlHex.setBytes((byte[]) event.getObject());
			pnlStatus.setLabelText(((PListViewerModel)getModel()).getFile().getAbsolutePath());
			pnTabs.setSelectedIndex(3);
		}
		if(event.getActionCommand().equals("INFO")){
			infoMessage((String) event.getObject());
		}
	}

}
