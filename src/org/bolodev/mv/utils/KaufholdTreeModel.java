package org.bolodev.mv.utils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
/** 
 * Example of a simple static TreeModel. It contains a (java.io.File) directory structure.
 * @author (C) 2001 Christian Kaufhold (ch-kaufhold@gmx.de)
 * @version 0.00 original code from http://www.chka.de/swing/tree/FileTreeModel.html<br />
 * @version 0.1 Added generics for type safety<br />
 * @version 0.2 Created KaufholdTreeModel from FileTreeModel, javadoc'ed <br />
 * @version 0.3 refactored constructor to take a String instead of a file as the parameter<br />
 * @since 0.1
 */
public class KaufholdTreeModel implements TreeModel, Serializable, Cloneable{

  /**
   * Added to comply with Serializable
   */
  private static final long serialVersionUID = 1848891493816111387L;

  /**
   * Event listener list for TreeModel
   */
  protected EventListenerList listeners;

  /**
   * Leaf object for the TreeModel
   */
  private static final Object LEAF = new Serializable() {

    /**
     * Added to comply with Serializable 
     */
    private static final long serialVersionUID = -4949276268699308611L; 
  };

  /**
   * Map of the file system
   */
  private Map<FileNode, Object> map;

  /**
   * Root file of the file system being mapped
   */
  private FileNode root;

  /**
   * Constructor method
   * @param aFileRoot the root file object
   */
  public KaufholdTreeModel(String aFileRoot){
    this.root = new FileNode(aFileRoot);
    
    if (!root.isDirectory()){
      map.put(new FileNode(aFileRoot), LEAF);
    }
    
    this.listeners = new EventListenerList();

    this.map = new HashMap<FileNode, Object>();
  }
  
  /**
   * Returns the current Map of the file system 
   * @return returns the Map of FileNode objects
   */
  public Map<FileNode, Object> getNodes(){
    return map;
  }

  /**
   * Implements the TreeModel method
   * @return
   */
  public Object getRoot(){
    return root;
  }

  /**
   * Implements the TreeModel method
   * @return
   */
  public boolean isLeaf(Object node){
    return map.get(node) == LEAF;
  }

  /**
   * Implements the TreeModel method
   * @return
   */
  @SuppressWarnings("rawtypes")
  public int getChildCount(Object node){
    List children = children(node);
    if (children == null){
      return 0;
    }
    return children.size();
  }

  /**
   * Implements the TreeModel method
   * @return
   */
  public Object getChild(Object parent, int index){
    return children(parent).get(index);
  }

  /**
   * Implements the TreeModel method
   * @return
   */
  public int getIndexOfChild(Object parent, Object child){
    return children(parent).indexOf(child);
  }

  /**
   * Returns a list of the root's children nodes, note unchecked warnings have been suppressed
   * @param node the root
   * @return the list of children
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected List<Object> children(Object node){
    FileNode f = (FileNode)node;
    Object value = map.get(f);
    if (value == LEAF){
      return null;
    }
    
    List children = (List)value;
    if (children == null){
      File[] c = f.listFiles();

      if (c != null){
        children = new ArrayList<Object>(c.length);

        for (int len = c.length, i = 0; i < len; i++){
          children.add(new FileNode(c[i].getAbsolutePath()));
          if (!c[i].isDirectory()){
            map.put(new FileNode(c[i].getAbsolutePath()), LEAF);
            //System.out.println("" + c[i].getName() + " is a LEAF");
          }
        }
      }
      else{
        children = new ArrayList<Object>(0);
      }

      map.put(f, children);       
    }
    return children;
  }

  /**
   * Implements the TreeModel method - not implemented in this class
   */
  public void valueForPathChanged(TreePath path, Object value){ }
    
  /**
   * Implements the TreeModel method
   */
  public void addTreeModelListener(TreeModelListener l){
    listeners.add(TreeModelListener.class, l);
  }

  /**
   * Implements the TreeModel method
   */
  public void removeTreeModelListener(TreeModelListener l){
    listeners.remove(TreeModelListener.class, l);
  }

  /**
   * Overrides clone method
   * @return
   */
  public Object clone(){
    try{
      KaufholdTreeModel clone = (KaufholdTreeModel)super.clone();
      clone.listeners = new EventListenerList();
      clone.map = new HashMap<FileNode, Object>(map);
      return clone;
    }
    catch (CloneNotSupportedException e){
      // ESCA-JAVA0150:
      throw new InternalError();
    }
  }
}