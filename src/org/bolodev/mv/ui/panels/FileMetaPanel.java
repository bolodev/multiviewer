package org.bolodev.mv.ui.panels;

import java.text.DecimalFormat;
import java.text.FieldPosition;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Panel to display details of the selected file
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @since 0.1
 */
public class FileMetaPanel extends JPanel {

  /**
   * Eclipse UID
   */
  private static final long serialVersionUID = -6976859280641377246L;
  
  /**
   * Constant for file name
   */
  private static final String FILE_NAME = " File name: ";
  
  /**
   * Constant for file size
   */
  private static final String FILE_SIZE = " File size: ";
  
  /**
   * Constant for number format (formats thousands with comma seperator)
   */
  private static final String NUM_FORMAT = "#,##0";
  
  /**
   * Constant for file bytes
   */
  private static final String FILE_BYTES = " bytes";
  
  /**
   * Label for file name
   */
  private JLabel lblFileName = null;
  
  /**
   * Label for file size
   */
  private JLabel lblFileSize = null;
  
  /**
   * Constructor method
   */
  public FileMetaPanel(){
    super();
    intialise();
  }

  /**
   * Initialise the UI components
   */
  private void intialise(){
    setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    lblFileName = new JLabel(FILE_NAME);
    lblFileSize = new JLabel(FILE_SIZE);
    
    add(lblFileName);
    add(lblFileSize);
  }
  
  /**
   * Sets the text for the selected file details
   * @param aName the file name
   * @param aSize the file size in bytes
   */
  public void setFileMeta(String aName, long aSize){
    lblFileName.setText(FILE_NAME + aName);
    DecimalFormat format = new DecimalFormat(NUM_FORMAT);
    FieldPosition f = new FieldPosition(0);
    StringBuffer s = new StringBuffer();
    s.append(FILE_SIZE);
    format.format(aSize, s, f);
    s.append(FILE_BYTES);
    lblFileSize.setText(s.toString());
  }
  
}
