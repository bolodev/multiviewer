package org.bolodev.mv.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;

import org.bolodev.mv.mvc.AbstractModel;
import org.bolodev.mv.mvc.ModelEvent;
import org.bolodev.mv.utils.FileTypeChecker;
import org.bolodev.mv.utils.KaufholdTreeModel;
import org.bolodev.mv.utils.MultiViewerTableModel;

import com.dd.plist.Base64;
import com.dd.plist.PropertyListParser;

/**
 * Application Model class
 * 
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.1.1 Added txt, xml, json, js handling to direct to text view rather than the hex view
 * @version 0.2 Added file export logic.
 * @since 0.1
 */
public class PListViewerModel extends AbstractModel {

	private File plistFile;
	private File directory;
	private KaufholdTreeModel ktm;
	private MultiViewerTableModel mdlTable;
	private DefaultComboBoxModel mdlTableCombo;
	private String sqliteDb = "";
	private File lastExportDirectory;
	
	/**
	 * Set up table model for table selected in SQLite tab
	 * @param aTableName name of the table selected
	 */
	public void setSQLiteQueryTable(String aTableName){
		String sqlQuery = "SELECT * FROM " + aTableName;
		mdlTable = new MultiViewerTableModel();
		try {
			Class.forName("org.sqlite.JDBC");

			Connection connection = null;
			try {
				String connectString = "jdbc:sqlite:" + sqliteDb;
				connection = DriverManager.getConnection(connectString);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sqlQuery);
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int colCount = rsmd.getColumnCount();
				Vector<String> colNames = new Vector<String>(colCount);
				for(int i = 1 ; i < colCount+1 ; i++){
					colNames.addElement(rsmd.getColumnLabel(i));
				}
				mdlTable.setColumnCount(colCount);
				mdlTable.setColumnIdentifiers(colNames);
				Vector<Vector<String>> tableData = new Vector<Vector<String>>();
				while (rs.next()) {
					Vector<String> rowData = new Vector<String>(colCount);
					for(int i = 1 ; i < colCount+1 ; i++){
						rowData.addElement(rs.getString(i));
					}
					tableData.addElement(rowData);
				}
				mdlTable.setDataVector(tableData, colNames);
			} catch (SQLException e) {
				ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
				notifyChanged(me);
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
					notifyChanged(me);
				}
			}
		} catch (ClassNotFoundException e) {
			ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
			notifyChanged(me);
		}
		
		ModelEvent me = new ModelEvent(this, 1000, "SQLITETABLE", mdlTable);
		notifyChanged(me);
	}

	/**
	 * Set up the list model for the combobox on the SQLite tab
	 * @param aDbFile the database file to query
	 */
	private void setSQLiteDB(File aDbFile) {
		mdlTableCombo = new DefaultComboBoxModel();
		sqliteDb = aDbFile.getAbsolutePath();
		try {
			Class.forName("org.sqlite.JDBC");

			Connection connection = null;
			try {
				String connectString = "jdbc:sqlite:" + sqliteDb;
				connection = DriverManager.getConnection(connectString);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table'");
				while (rs.next()) {
					mdlTableCombo.addElement(rs.getString("name"));
				}
			} catch (SQLException e) {
				ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
				notifyChanged(me);
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
					notifyChanged(me);
				}
			}
		} catch (ClassNotFoundException e) {
			ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
			notifyChanged(me);
		}

		ModelEvent me = new ModelEvent(this, 1000, "SQLITELIST", mdlTableCombo);
		notifyChanged(me);
		setSQLiteQueryTable((String) mdlTableCombo.getElementAt(0));
	}

	/**
	 * Read in file and create a BufferedImage to display in the Image tab
	 * @param anImageFile the image file to display
	 */
	private void setImageFile(File anImageFile) {
		try {
			BufferedImage bi = ImageIO.read(anImageFile);
			ModelEvent me = new ModelEvent(this, 1000, "IMAGE", bi);
			notifyChanged(me);
		} catch (IOException e) {
			ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
			notifyChanged(me);
		}
	}
	
	/**
	 * For unknown/undisplayable files (i.e. binary files) read the bytes of the file and pass to the hex viewer in the Hex tab
	 * @param aFile the file to read
	 */
	private void setHexView(File aFile){
		try {
			long fileLength = aFile.length();
			RandomAccessFile raf = new RandomAccessFile(aFile, "r");
			byte[] fileBytes = new byte[(int) fileLength];
			raf.read(fileBytes);
			raf.close();
			ModelEvent me = new ModelEvent(this, 1000, "HEX", fileBytes);
			notifyChanged(me);
		} catch (FileNotFoundException e) {
			ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
			notifyChanged(me);
		} catch (IOException e) {
			ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
			notifyChanged(me);
		}
		
	}

	/**
	 * Set the PList file object and parse it to an XML String, or if it is not
	 * a plist then handle SQLite, JPEG, PNG, GIF, TIFF with another view tab,
	 * everything else try and strip the strings with a scanner.
	 * 
	 * @param aFile
	 * @throws Exception
	 */
	public void setFile(File aFile) {
		plistFile = aFile;
		String fileName = plistFile.getName();
		try {
			if(fileName.endsWith(".xml") || fileName.endsWith(".json") || fileName.endsWith("txt") || fileName.endsWith(".js") || fileName.endsWith(".html")){
				try {
					String text = new Scanner(plistFile).useDelimiter("\\A").next();
					setFile(text);
				} catch (FileNotFoundException e1) {
					ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e1.getLocalizedMessage());
					notifyChanged(me);
				} catch (NoSuchElementException nsee) {
					// swallow exception, nothing more to be done
				}
			}
			else{
				getXMLFromPList();
			}
		} catch (Exception e) {
			try {
				switch (FileTypeChecker.getFileType(plistFile)) {
				case SQLITE:
					setSQLiteDB(aFile);
					break;
				case JPEG:
					setImageFile(aFile);
					break;
				case PNG:
					setImageFile(aFile);
					break;
				case GIF:
					setImageFile(aFile);
					break;
				case TIFF:
					setImageFile(aFile);
					break;
				case BMP:
					setImageFile(aFile);
					break;
				case UNKNOWN:
					setHexView(aFile);
					break;
				default:
					try {
						String text = new Scanner(plistFile).useDelimiter("\\A").next();
						setFile(text);
					} catch (FileNotFoundException e1) {
						ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e1.getLocalizedMessage());
						notifyChanged(me);
					} catch (NoSuchElementException nsee) {
						// swallow exception, nothing more to be done
					}
					break;
				}
			} catch (IOException e1) {
				ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e1.getLocalizedMessage());
				notifyChanged(me);
			}
		}
	}

	/**
	 * Dump file contents to console, used for non-plist files
	 * 
	 * @param aFileContents
	 *            the contents of the file to display
	 */
	public void setFile(String aFileContents) {
		ModelEvent me = new ModelEvent(this, 1000, "Parsed", aFileContents);
		notifyChanged(me);
	}

	/**
	 * Get the current PList file
	 * 
	 * @return the current plist file
	 */
	public File getFile() {
		return plistFile;
	}

	/**
	 * @param aFile  the selected directory
	 */
	public void setDirectory(File aFile) {
		directory = aFile;
		ktm = new KaufholdTreeModel(directory.getAbsolutePath());
		ModelEvent me = new ModelEvent(this, 50, "Directory Tree", ktm);
		notifyChanged(me);
	}

	/**
	 * @return the selected directory
	 */
	public File getDirectory() {
		return directory;
	}

	/**
	 * Convert the PList file contents to readable XML string, notify the View
	 * with a model event
	 * 
	 * @throws Exception
	 *             from dd-plist library
	 */
	private void getXMLFromPList() throws Exception {
		String plist = PropertyListParser.parse(plistFile).toXMLPropertyList();
		ModelEvent me = new ModelEvent(this, 1000, "Parsed", plist);
		notifyChanged(me);
	}

	/**
	 * Decode input
	 * 
	 * @param aStringToDecode
	 *            the text to decode
	 */
	public void decodeBase64Text(String aStringToDecode) throws IOException {
		byte[] bytes = Base64.decode(aStringToDecode);
		ModelEvent me = new ModelEvent(this, 1001, "Decoded", bytes);
		notifyChanged(me);
	}

	/**
	 * Set name and bytes in panel below tree
	 * 
	 * @param aName
	 *            name of the file
	 * @param aSize
	 *            size in bytes
	 */
	public void setMetadata(String aName, long aSize) {
		ModelEvent me = new ModelEvent(this, 51, "Metadata", new Object[] {aName, new Long(aSize) });
		notifyChanged(me);
	}

	/**
	 * Set a directory as working export directory
	 * @param aFile a directory as working export directory
	 */
	public void setLastExportDirectory(File aFile){
		lastExportDirectory = aFile;
	}
	
	/**
	 * Get the last selected export directory
	 * @return the last selected export directory
	 */
	public File getLastExportDirectory(){
		return lastExportDirectory;
	}
	
	/**
	 * Export the currently selected file
	 * @param aView the application UI, used as a parent for the JFileChooser
	 */
	public void exportSelectedFile(){
		final File outputFile = new File(lastExportDirectory + File.separator + plistFile.getName());
		try {
			FileInputStream fis = new FileInputStream(plistFile);
			FileOutputStream fos = new FileOutputStream(outputFile);
			byte[] buffer = new byte[1024];
			int length;
			while((length = fis.read(buffer)) > 0){
				fos.write(buffer, 0, length);
			}
			if(fis != null){
				fis.close();
			}
			if(fos != null){
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
			notifyChanged(me);
		} catch (IOException e) {
			ModelEvent me = new ModelEvent(this, 1000, "EXCEPTION", e.getLocalizedMessage());
			notifyChanged(me);
		}
		ModelEvent me = new ModelEvent(this, 1000, "INFO", outputFile.getAbsolutePath() + " written");
		notifyChanged(me);
	}
}
