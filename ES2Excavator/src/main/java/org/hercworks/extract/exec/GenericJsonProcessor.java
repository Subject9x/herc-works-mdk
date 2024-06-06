package org.hercworks.extract.exec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine.OptionArgs;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class GenericJsonProcessor extends FileProcessor{

	private ObjectMapper objectMapper;
	
	
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void importJson(String file, ThreeSpaceByteTransformer transformerClass, Class<? extends DataFile> dataClass, GeneralDTOService dtoService, Class<? extends TransferObject> dtoClass) {
		try {
			getLogger().console("--------------------------IMPORTING " + file + " -----------------------------------");
			
			
			TransferObject dto = objectMapper.readValue(loadFileBytes(getAppPath() + file), dtoClass);
			
			dtoClass.cast(dto);
			
			if(dto == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			DataFile convert = dtoService.fromDTO(dto);
			convert = dataClass.cast(convert);
		
			((DataFile)convert).setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				((DataFile)convert).assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
				
				//TODO : make configurable by user
				String exportModDir = makeExportPath(targDirPath+"/MOD/" + ((DataFile)convert).getExt().val().toUpperCase() +"/");
				
				if(exportModDir != null) {
					targDirPath = exportModDir;
				}
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path("+ targDirPath + ") was null.\n rootPath=[" + getAppPath() + "]");
			}

			//make file name accessible to the transformer
			String fileName = ((DataFile)convert).getFileName();
			String cleanFileName = new String(fileName.substring(0,
					fileName.lastIndexOf(".")));
			
			byte[] data = transformerClass.objectToBytes(convert);
			
			
			String fullImportPath = targDirPath + "/" + cleanFileName + "." + ((DataFile)convert).getExt().val().toUpperCase();
			
			File out = new File(fullImportPath);
			
			FileOutputStream foss = new FileOutputStream(out);
			foss.write(data);
			foss.close();
			
			getLogger().console("+ Compilation complete for [" + out.getAbsolutePath() +"]");
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
		getLogger().console("------------------------------------------------------------------------------------");
	}
	
	public void exportJson(String file, ThreeSpaceByteTransformer transformerClass, Class<? extends DataFile> dataClass, GeneralDTOService dtoService, Class<? extends TransferObject> dtoClass) {
		try {
			getLogger().console("--------------------------EXPORTING " + file + " -----------------------------------");
			
			DataFile exportDat = transformerClass.bytesToObject(loadFileBytes(getAppPath() + file));
			exportDat = dataClass.cast(exportDat);
			
			if(exportDat == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			((DataFile)exportDat).setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				((DataFile)exportDat).assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				
				String exportJsonDir = "";
				
				targDirPath = makeExportPath(this.unpackPath + "/" + ((DataFile)exportDat).getDir().name() + "/");
				
				
				
				
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}
			
			File targDir = new File(targDirPath);
			if(!targDir.exists()) {
				if(!targDir.mkdir()) {
					getLogger().console("Error: could not make dir [" + targDirPath + "], exiting process");
					return;
				}
			}

			Object dto = dtoService.convertToDTO(((DataFile)exportDat));
			
			dto = dtoClass.cast(dto);
			
			String fullExportPath = targDirPath + "/" + ((DataFile)exportDat).getFileName() + "." + ((DataFile)exportDat).getDir().name() + ".json";
			
			File json = new File(fullExportPath);
			json.setWritable(true);
			objectMapper.writeValue(json, dto);
			
			getLogger().console("+ Write complete to [" + fullExportPath +"]");
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
			return;
		}
		
		getLogger().console("------------------------------------------------------------------------------------");
	}
	
}
