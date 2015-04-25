package org.bolodev.mv.utils;

import java.io.File;
/**
 * Class to hold a file object and string, used as Nodes on the KaufholdTreeModel, enables only the file names to be displayed on the tree and not the fullt path and name
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.2 refactored to subclass java.io.File - override of toString to return the file name with no path<br />
 * @since 0.1
 */
public class FileNode extends File{
  
  /**
   * Eclipse UID
   */
  private static final long serialVersionUID = -2316487963811935178L;
  
  /**
   * Constructor method
   * @param aFileName the file to be held in the Node
   */
  public FileNode(String aFileName){
    super(aFileName);
  }
  
  /**
   * Overrides the default Object.toString to return the name of the file object held in the node
   * @return 
   */
  public String toString(){
    return this.getName();
  }
 
}
