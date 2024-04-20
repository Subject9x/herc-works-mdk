package org.hercworks.extract.exec.impl;

import java.io.File;
import java.io.IOException;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.file.dat.shell.InitHerc;
import org.hercworks.core.data.file.dat.shell.RprHerc;
import org.hercworks.core.data.file.dat.shell.TrainingHercs;
import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.core.io.transform.common.PlayerSaveTransform;
import org.hercworks.core.io.transform.shell.ArmHercTransformer;
import org.hercworks.core.io.transform.shell.ArmWeapTransformer;
import org.hercworks.core.io.transform.shell.HercInfoTransformer;
import org.hercworks.core.io.transform.shell.HercsStartTransformer;
import org.hercworks.core.io.transform.shell.InitHercTransformer;
import org.hercworks.core.io.transform.shell.RprHercTransform;
import org.hercworks.core.io.transform.shell.TrainingHercsTransform;
import org.hercworks.core.io.transform.shell.WeaponsDatTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.ExcavatorCmdLine.OptionArgs;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.FileProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.extract.util.FileMatch;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;
import org.hercworks.transfer.dto.file.shell.HercInfDTO;
import org.hercworks.transfer.dto.file.shell.InitHercDTO;
import org.hercworks.transfer.dto.file.shell.RepairHercDTO;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.file.shell.TrainingHercsDTO;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.transfer.svc.impl.ArmHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.ArmWeapDTOServiceImpl;
import org.hercworks.transfer.svc.impl.HercInfoDTOServiceImpl;
import org.hercworks.transfer.svc.impl.InitHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.RepairHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.StartingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.TrainingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.WeaponsDatShellDTOServiceImpl;
import org.hercworks.voln.DataFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JSON exporter for the .DAT files, 
 * 	TODO - currently only supports very specifc DAT files.
 * 		SHELL/GAM/ WEAPONS.DAT, HERCS.DAT, HERC_INF.DAT, ARM_[herc].DAT, ARM_WEAP.DAT
 * 		
 * 		both VSHELL and DBSIM have many different DAT files, and this processor might become more crowded, a generic json will need to be
 * 		implemented.
 * 
 */
public class JsonExportProcessor extends FileProcessor{

	private ObjectMapper objectMapper;
	
	@Override
	public void init(ExcavatorCmdLine cmdLine, Logger logger) throws IOException  {
		super.init(cmdLine, logger);
		objectMapper = cmdLine.getJsonMapper();
		objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	
	}
	
	@Override
	public boolean filterFile(FileItem file) {
		boolean filter = false;
		if(file.getName().contains(".DAT") || file.getName().contains(".sav")) {
			if(FileMatch.getByPattern(file.getName()) != null) {
				filesToProcess.add(file);
				filter = true;
			}
		}
		return filter;
	}
	
	@Override
	public void processFiles() {
		for(FileItem file : filesToProcess) {
			FileMatch match = FileMatch.getByPattern(file.getName()); 
						
			switch(match) {
				case WEAPONS:
					exportJson(file.getName(), new WeaponsDatTransformer(), WeaponsDat.class, new WeaponsDatShellDTOServiceImpl(), WeaponsDatDTO.class);
					break;
				case ARM_HERC:
					exportJson(file.getName(), new ArmHercTransformer(), ArmHerc.class, new ArmHercDTOServiceImpl(), ArmHercDTO.class);
					break;
				case ARM_WEAP:
					exportJson(file.getName(), new ArmWeapTransformer(), ArmWeap.class, new ArmWeapDTOServiceImpl(), ArmWeapDTO.class);
					break;
				case RPR_HERC:
					exportJson(file.getName(), new RprHercTransform(), RprHerc.class, new RepairHercDTOServiceImpl(), RepairHercDTO.class);
					break;
				case INI_HERC:
					exportJson(file.getName(), new InitHercTransformer(), InitHerc.class, new InitHercDTOServiceImpl(), InitHercDTO.class);
					break;
				case HERCS:
					exportJson(file.getName(), new  HercsStartTransformer(), Hercs.class, new StartingHercsDTOServiceImpl(), StartHercsDTO.class);
					break;
				case HERC_INF:
					exportJson(file.getName(), new  HercInfoTransformer(), HercInf.class, new HercInfoDTOServiceImpl(), HercInfDTO.class);
					break;
				case CAREER:
					
					break;
				case TRAINING_HERCS:
					exportJson(file.getName(), new TrainingHercsTransform(), TrainingHercs.class, new TrainingHercsDTOServiceImpl(), TrainingHercsDTO.class);
					break;
				case SAVE_FILE:
					exportTest(file.getName(), new PlayerSaveTransform(), PlayerSave.class, null, null);
					break;
				default:
					break;
			}
		}
	}
	
	public void exportTest(String file, ThreeSpaceByteTransformer transformerClass, Class<? extends DataFile> dataClass, GeneralDTOService dtoService, Class<? extends TransferObject> dtoClass) {
		try {
			getLogger().console("--------------------------EXPORTING " + file + " -----------------------------------");
			
			DataFile exportDat = transformerClass.bytesToObject(loadFileBytes(getAppPath() + file));
			exportDat = dataClass.cast(exportDat);
			
			if(exportDat == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			((DataFile)exportDat).setFileName(getCleanFileName(fileNoExt(file)));
			
//			String targDirPath = null;
//			if(cmdLine.checkOption(OptionArgs.SRC)) {
//				getLogger().consoleDebug("--keeping DAT export to source directory.");
//				((DataFile)exportDat).assignDir(getAppPath());
//				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
//			}
//			else {
//				targDirPath = makeExportPath(this.unpackPath);
//			}
//			
//			if(targDirPath == null) {
//				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
//			}
//
//			Object dto = dtoService.convertToDTO(((DataFile)exportDat));
//			
//			dto = dtoClass.cast(dto);
//			
//			File json = new File(targDirPath + "/" + ((DataFile)exportDat).getFileName() + ".json");
//			json.setWritable(true);
//			objectMapper.writeValue(json, dto);
//			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
			return;
		}
		getLogger().console("+ Write complete.");
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
