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
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			byte[] data = transformerClass.objectToBytes(convert);
			
			File out = new File(targDirPath + "/" + ((DataFile)convert).getFileName() + "." + ((DataFile)convert).getExt().val());
			
			FileOutputStream foss = new FileOutputStream(out);
			foss.write(data);
			foss.close();
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
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
				targDirPath = makeExportPath(this.unpackPath);
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			Object dto = dtoService.convertToDTO(((DataFile)exportDat));
			
			dto = dtoClass.cast(dto);
			

			File json = new File(targDirPath + "/" + ((DataFile)exportDat).getFileName() + ".json");
			json.setWritable(true);
			objectMapper.writeValue(json, dto);
//			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
			return;
		}
		getLogger().console("+ Write complete.");
		getLogger().console("------------------------------------------------------------------------------------");
	}
	
}
