package org.hercworks.extract.exec.impl;

import java.io.File;
import java.io.IOException;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.io.transform.shell.ArmHercTransformer;
import org.hercworks.core.io.transform.shell.ArmWeapTransformer;
import org.hercworks.core.io.transform.shell.HercInfoTransformer;
import org.hercworks.core.io.transform.shell.HercsStartTransformer;
import org.hercworks.core.io.transform.shell.WeaponsDatTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.ExcavatorCmdLine.OptionArgs;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.FileProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.extract.util.FileMatch;
import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;
import org.hercworks.transfer.dto.file.shell.HercInfDTO;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.svc.ArmHercDTOService;
import org.hercworks.transfer.svc.ArmWeapDTOService;
import org.hercworks.transfer.svc.HercInfDTOService;
import org.hercworks.transfer.svc.StartingHercsDTOService;
import org.hercworks.transfer.svc.WeaponsDatShellDTOService;
import org.hercworks.transfer.svc.impl.ArmHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.ArmWeapDTOServiceImpl;
import org.hercworks.transfer.svc.impl.HercInfoDTOServiceImpl;
import org.hercworks.transfer.svc.impl.StartingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.WeaponsDatShellDTOServiceImpl;

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
		if(file.getName().contains(".DAT")) {
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
					exportWeaponsDat(file.getName());
					break;
				case ARM_HERC:
					exportArmHerc(file.getName());
					break;
				case ARM_WEAP:
					exportArmWeap(file.getName());
					break;
				case RPR_HERC:
					
					break;
				case INI_HERC:
					
					break;
				case HERCS:
					exportHercsDat(file.getName());
					break;
				case HERC_INF:
					exportHercInf(file.getName());
					break;
				case CAREER:
					
					break;
				case TRAINING_HERCS:
					
					break;
				default:
					break;
			}
		}
	}

	public void exportWeaponsDat(String file) {
		try {
			getLogger().console("--------------------------EXPORTING /GAM/WEAPONS.DAT-----------------------------------");
			
			WeaponsDatTransformer weaponsConvert = new WeaponsDatTransformer();
			WeaponsDat weapons = (WeaponsDat)weaponsConvert.bytesToObject(loadFileBytes(getAppPath() + file));
			
			
			if(weapons == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			weapons.setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				weapons.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			WeaponsDatShellDTOService dtoService = new WeaponsDatShellDTOServiceImpl();
			WeaponsDatDTO dto = dtoService.convertToDTO(weapons);
			
			File json = new File(targDirPath + "/" + weapons.getFileName() + ".json");
			json.setWritable(true);
			objectMapper.writeValue(json, dto);
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
	}

	public void exportArmHerc(String file) {
		try {
			getLogger().console("--------------------------EXPORTING /GAM/ARM_<herc>.DAT-----------------------------------");
			
			ArmHercTransformer armHercTransform = new ArmHercTransformer();
			ArmHerc armHerc = (ArmHerc)armHercTransform.bytesToObject(loadFileBytes(getAppPath() + file));
			
			if(armHerc == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			armHerc.setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				armHerc.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			ArmHercDTOService dtoService = new ArmHercDTOServiceImpl();
			ArmHercDTO dto = dtoService.convertToDTO(armHerc);
			
			File json = new File(targDirPath + "/" + armHerc.getFileName() + ".json");
			json.setWritable(true);
			objectMapper.writeValue(json, dto);
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
	}

	public void exportHercInf(String file) {
		try {
			getLogger().console("------------------------EXPORTING /GAM/HERC_INF.DAT-----------------------------------");
			
			HercInfoTransformer transformer = new HercInfoTransformer();
			HercInf hercInf = (HercInf)transformer.bytesToObject(loadFileBytes(getAppPath() + file));
			
			if(hercInf == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			hercInf.setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				hercInf.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			HercInfDTOService dtoService = new HercInfoDTOServiceImpl();
			HercInfDTO dto = dtoService.convertToDTO(hercInf);
			
			File json = new File(targDirPath + "/" + hercInf.getFileName() + ".json");
			json.setWritable(true);
			objectMapper.writeValue(json, dto);
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
	}
	
	public void exportHercsDat(String file) {
		try {
			getLogger().console("------------------------EXPORTING /GAM/HERCS.DAT-----------------------------------");
			
			HercsStartTransformer transformer = new HercsStartTransformer();
			Hercs data = (Hercs)transformer.bytesToObject(loadFileBytes(getAppPath() + file));
			
			if(data == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			data.setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				data.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			StartingHercsDTOService dtoService = new StartingHercsDTOServiceImpl();
			StartHercsDTO dto = dtoService.convertToDTO(data);
			
			File json = new File(targDirPath + "/" + data.getFileName() + ".json");
			json.setWritable(true);
			objectMapper.writeValue(json, dto);
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
	}
	
	public void exportArmWeap(String file) {
		try {
			getLogger().console("------------------------EXPORTING /GAM/ARM_WEAP.DAT-----------------------------------");
			
			ArmWeapTransformer transformer = new ArmWeapTransformer();
			ArmWeap data = (ArmWeap)transformer.bytesToObject(loadFileBytes(getAppPath() + file));
			
			if(data == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			data.setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				data.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			ArmWeapDTOService dtoService = new ArmWeapDTOServiceImpl();
			ArmWeapDTO dto = dtoService.convertToDTO(data);
			
			File json = new File(targDirPath + "/" + data.getFileName() + ".json");
			json.setWritable(true);
			objectMapper.writeValue(json, dto);
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
	}
}
