package org.bolodev.mv.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Check file type by byte signature
 * @author bolodev
 * @version 0.1 initial cut<br />
 * @version 0.1.1 added extra TIFF definition
 * @since 0.1
 */
public class FileTypeChecker {

	private static final short[] SQLITE = new short[]{0x53, 0x51, 0x4C, 0x69, 0x74, 0x65, 0x20, 0x66, 0x6F, 0x72, 0x6D, 0x61, 0x74, 0x20, 0x33, 0x00};
	private static final short[] JPEG1 = new short[]{0xFF, 0xD8, 0xFF};
	private static final short[] GIF = new short[]{0x47, 0x49, 0x46, 0x38};
	private static final short[] PNG = new short[]{0x89, 0x50, 0x4E, 0x47};
	private static final short[] TIFF1 = new short[]{0x49, 0x20, 0x49};
	private static final short[] TIFF2 = new short[]{0x49, 0x49, 0x2A};
	private static final short[] TIFF3 = new short[]{0x49, 0x49, 0x00};
	private static final short[] BMP = new short[]{0x42, 0x4D};
	
	public static FileTypeEnum getFileType(File aFileToTest) throws IOException{
		RandomAccessFile raf = new RandomAccessFile(aFileToTest, "r");
		byte[] sig = new byte[20];
		if(raf.length() > 20L){
			raf.seek(0);
			raf.read(sig);
			raf.close();
			switch(sig[0]){
				case (byte)0x42: //BMP
					if(sig[1] == (byte)BMP[1]){
						return FileTypeEnum.BMP;
					}
					break;
				case (byte)0x47: //GIF
					if(sig[1] == (byte)GIF[1] && sig[2] == (byte)GIF[2] && sig[3] == (byte)GIF[3]){
						return FileTypeEnum.GIF;
					}
					break;
				case (byte)0x49: //TIFF
					if(sig[1] == (byte)TIFF1[1] && sig[2] == (byte)TIFF1[2]){
						return FileTypeEnum.TIFF;
					}
					if(sig[1] == (byte)TIFF2[1] && sig[2] == (byte)TIFF2[2]){
						return FileTypeEnum.TIFF;
					}
					if(sig[1] == (byte)TIFF3[1] && sig[2] == (byte)TIFF3[2]){
						return FileTypeEnum.TIFF;
					}
					break;
				case (byte)0x53: //SQLITE
					if(sig[1] == (byte)SQLITE[1] && sig[2] == (byte)SQLITE[2] && sig[3] == (byte)SQLITE[3] && sig[4] == (byte)SQLITE[4] && sig[5] == (byte)SQLITE[5] && sig[6] == (byte)SQLITE[6]
							 && sig[7] == (byte)SQLITE[7] && sig[8] == (byte)SQLITE[8] && sig[9] == (byte)SQLITE[9] && sig[10] == (byte)SQLITE[10]){
						return FileTypeEnum.SQLITE;
					}
					break;
				case (byte) 0x89: //PNG
					if(sig[1] == (byte)PNG[1] && sig[2] == (byte)PNG[2] && sig[3] == (byte)PNG[3]){
						return FileTypeEnum.PNG;
					}
					break;
				case (byte) 0xFF: //JPEG
					if(sig[1] == (byte)JPEG1[1] && sig[2] == (byte)JPEG1[2]){
						return FileTypeEnum.JPEG;
					}
					break;
				default:
					break;
			}
		}
		else{
			return FileTypeEnum.UNKNOWN;
		}
		
		return FileTypeEnum.UNKNOWN;
	}
	
}
