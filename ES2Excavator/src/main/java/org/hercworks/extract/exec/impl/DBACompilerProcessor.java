package org.hercworks.extract.exec.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hercworks.core.data.file.dyn.DynamixBitmap;
import org.hercworks.core.io.transform.common.DynamixBitmapTransformer;
import org.hercworks.extract.exec.FileProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.voln.FileType;


/**
 * TODO - hold off on this one for now, but keep code for ref.
 * TODO - build_dba.txt file to specify file order
 * 
 */
public class DBACompilerProcessor extends FileProcessor{

	private DynamixBitmapTransformer dbmTransformer = new DynamixBitmapTransformer();
		
	@Override
	public boolean filterFile(FileItem file) {
		boolean filter = false;
//		if(file.getName().toLowerCase().contains(FileType.CDBA.name())) {
//			filesToProcess.add(file);
//			filter = true;
//		}
		return filter;
	}

	@Override
	public void processFiles() {
		for(FileItem fileItem : filesToProcess) {
			
			getLogger().console("--------------------------COMPILING DBA " + fileItem.getName() + " -----------------------------------");
			
			/* TODO -  <dba name>.cdba
			 * 
			 * use filename as target dba file name
			 * 
			 * 
			 */
			
			String srcDir = getAppPath() + fileItem.getName();
			srcDir = srcDir.substring(0, srcDir.lastIndexOf("/")+1);
			
			File controlFile = new File(getAppPath() + fileItem.getName());
			if(!controlFile.exists()) {
				getLogger().console("Error: ["+controlFile.getPath() +"] doesn't exist, skipping process.");
				return;
			}
			
			Set<String> dbmFileNames = null;
			try(BufferedReader buffer = new BufferedReader(new FileReader(controlFile))){
				 dbmFileNames = buffer.lines().collect(Collectors.toSet());
			} catch (FileNotFoundException e) {
				getLogger().console(e.getLocalizedMessage());
				
			} catch (IOException e) {
				getLogger().console(e.getLocalizedMessage());
			}
			
			if(dbmFileNames == null || dbmFileNames.size() == 0){
				getLogger().console("Error: build_dba.txt control file was empty, ending process.");
				return;
			}
			
			
			for(String dbmName : dbmFileNames) {
				
			}
			
			
			Set<String> dbmFiles = Stream.of(new File(srcDir).listFiles())
					.filter(file->!file.isDirectory())
					.peek(file->getLogger().console(file.getName()))
					.filter(file->file.getName().toLowerCase().contains(FileType.DBM.name()))
					.peek(file->getLogger().console(file.getName()))
					.map(File::getName)
					.collect(Collectors.toSet());
			
			if(dbmFiles.isEmpty() || dbmFiles.size() <= 0) {
				getLogger().console("Warning: no .DBM files found for provided path, skipping process!");
				return;
			}
			
			DynamixBitmap[] dbmArray = new DynamixBitmap[dbmFileNames.size()];
			int cnt = 0;
			for(String dbmName : dbmFileNames) {
				byte[] bytes = loadFileBytes(getAppPath() + fileItem.getName() + "/" + dbmName);
				
				if(bytes != null && bytes.length > 0) {
					dbmTransformer.resetIndex();
					
					DynamixBitmap dbm = (DynamixBitmap)dbmTransformer.bytesToObject(bytes);
					if(dbm != null) {
						dbmArray[cnt] = dbm;
						
					}
				}
				else{
					getLogger().console("Warning: ["+dbmName+"] not found at given path, skipping.");
				}
				
				cnt+=1;
			}
			
			
		}
	}

}
