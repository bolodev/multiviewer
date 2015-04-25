package org.bolodev.mv.ui.panels;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.bolodev.mv.utils.KaufholdTreeModel;

/**
 * Panel to hold a JTree of the machine's file system 
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.2 removed local TreeSelectionListener and refactored constructor to pass one in from the controller<br />
 * @version 0.3 added meta panel<br />
 * @version 0.4 added Single selection mode to tree model
 * @since 0.1
 */
public class DiskTreePanel extends JPanel {

  /**
   * Eclipse UID
   */
  private static final long serialVersionUID = 1620775786286886010L;
  
  /**
   * Initial value for the tree
   */
  private static final Object[] NO_DIR = {"No directory selected"};
  
  /**
   * JTree to display the file system tree
   */
  private JTree diskTree = null;
  
  /**
   * Scrollpane for the JTree
   */
  private JScrollPane scroller = null;
  
  /**
   * Panel to show file data
   */
  private FileMetaPanel meta = null;

  /**
   * Constructor method
   * @param aListener the application listener
   */
  public DiskTreePanel(TreeSelectionListener aListener){
    super();
    intitialise(aListener);
  }
  
  /**
   * Initialise the UI components
   * @param aListener the application listener
   */
  private void intitialise(TreeSelectionListener aListener){
    setBorder(new EtchedBorder(EtchedBorder.RAISED));
    setLayout(new BorderLayout());
    diskTree = new JTree(NO_DIR);
    diskTree.setRootVisible(true);
    diskTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    diskTree.addTreeSelectionListener(aListener);
    diskTree.addMouseListener((MouseListener) aListener);
    scroller = new JScrollPane(diskTree);
    meta = new FileMetaPanel();
    add(scroller, BorderLayout.CENTER);
    add(meta, BorderLayout.SOUTH);
  }
  
  /**
   * Sets the tree model to one selected as the directory to be hashed
   * @param aModel the tree model to one selected as the directory to be hashed
   */
  public void setNewFileTree(KaufholdTreeModel aModel){
    diskTree.setModel(aModel);
  }
  
  /**
   * Sets new tree in tree display
   * @param aNode
   */
  public void setNewFileTree(TreeNode aNode){
    diskTree.setModel(new DefaultTreeModel(aNode));
  }
  
  /**
   * Calls the meta panel to update file details
   * @param aName the file name
   * @param aSize the file size
   */
  public void setFileMeta(String aName, long aSize){
    meta.setFileMeta(aName, aSize);
  }
  
}
