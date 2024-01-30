package com.mech.works.io.read;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mech.works.data.ref.files.DataFile;
import com.mech.works.vol.data.Voln;
import com.mech.works.vol.data.Voln.FileType;

import at.favre.lib.bytes.Bytes;

public final class VolFileReader {
	
//	private static int offsetVolHeader = 0;
	
	private static int offsetVolExe1 = 4;
	
	private static int offsetVolExe2 = 5;
	
	private static int offsetUNKValue = 8;	//its set to 05 in every file
	
	private static int offsetDirCount = 9;
	
	private static int offsetDirSize = 10;
	
	private static int offsetDirListStart = 12;
	
	private static int dirBytesLength = 5;
	
	public VolFileReader() {}
	
	public static Voln parseVolFile(String volPath) throws FileNotFoundException{
		
		File f = new File(volPath);
		Voln volFile = null;
		
		try(FileInputStream fizz = new FileInputStream(f); 
				ByteArrayInputStream bizz = new ByteArrayInputStream(fizz.readAllBytes())){
		
			volFile = new Voln.VolnBuilder()
					.setFileName(f.getName())
					.setRawBytes(bizz.readAllBytes())
					.build();

			Bytes volData = Bytes.from(volFile.getRawBytes());
			
			if(volData.byteAt(offsetVolExe1) == 1 && volData.byteAt(offsetVolExe2) == 0) {
				volFile.setExeUse1(Voln.ExeUse.DBSIM);
			}
			else {
				volFile.setExeUse1(Voln.ExeUse.VSHELL);
			}
			
			volFile.setDirCount(volData.byteAt(offsetDirCount));
			
			volFile.setDirSize(volData.byteAt(offsetDirSize));
			
			HashMap<FileType, List<DataFile>> directory = new HashMap<Voln.FileType, List<DataFile>>();
			
			
			
			for(int i = 0; i < volFile.getDirCount(); i++) {
				Bytes dirName = Bytes.from(volData.array(), offsetDirListStart + (i * dirBytesLength), dirBytesLength);
				
				//debug
				System.out.println(new String(dirName.toCharArray()).substring(0, 3));
				
				directory.put(FileType.valueOf(new String(dirName.toCharArray()).substring(0, 3)), new ArrayList<DataFile>());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return volFile;
	}
	
}
