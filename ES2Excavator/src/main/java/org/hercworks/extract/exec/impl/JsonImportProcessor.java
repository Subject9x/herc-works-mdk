package org.hercworks.extract.exec.impl;

import java.io.File;
import java.io.FileOutputStream;
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
 * JSON import for the .DAT files, 
 * 	TODO - currently only supports very specifc DAT files.
 * 		SHELL/GAM/ WEAPONS.DAT, HERCS.DAT, HERC_INF.DAT, ARM_[herc].DAT, ARM_WEAP.DAT
 * 		
 * 		both VSHELL and DBSIM have many different DAT files, and this processor might become more crowded, a generic json will need to be
 * 		implemented.
 * 
 */
public class JsonImportProcessor extends FileProcessor{
	
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
		if(file.getName().toLowerCase().contains(".json")) {
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
					importWeaponsDat(file.getName());
					break;
				case ARM_HERC:
					importArmHerc(file.getName());
					break;
				case ARM_WEAP:
					importArmWeap(file.getName());
					break;
				case RPR_HERC:
					
					break;
				case INI_HERC:
					
					break;
				case HERCS:
					importHercsDat(file.getName());
					break;
				case HERC_INF:
					importHercInf(file.getName());
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
	
	public void importWeaponsDat(String file) {
		try {
			getLogger().console("--------------------------IMPORTING /GAM/WEAPONS.DAT-----------------------------------");
			
			
			WeaponsDatDTO dto = objectMapper.readValue(loadFileBytes(getAppPath() + file), WeaponsDatDTO.class);
			
			if(dto == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			WeaponsDatShellDTOService dtoService = new WeaponsDatShellDTOServiceImpl();
			WeaponsDat weapons = dtoService.fromDTO(dto);
			
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

			WeaponsDatTransformer weaponsConvert = new WeaponsDatTransformer();
			byte[] data = weaponsConvert.objectToBytes(weapons);
			
			
			File out = new File(targDirPath + "/" + weapons.getFileName() + "."+weapons.getExt().val());
			
			FileOutputStream foss = new FileOutputStream(out);
			foss.write(data);
			foss.close();
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
		getLogger().console("+ Write complete.");
		getLogger().console("------------------------------------------------------------------------------------");
	}

	public void importArmHerc(String file) {
		try {
			getLogger().console("--------------------------IMPORTING /GAM/ARM_<herc>.DAT-----------------------------------");
			
			
			ArmHercDTO dto = objectMapper.readValue(loadFileBytes(getAppPath() + file), ArmHercDTO.class);
			
			if(dto == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			ArmHercDTOService dtoService = new ArmHercDTOServiceImpl();
			ArmHerc armHerc = dtoService.fromDTO(dto);
			
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

			ArmHercTransformer transform = new ArmHercTransformer();
			byte[] data = transform.objectToBytes(armHerc);
			
			
			File out = new File(targDirPath + "/" + armHerc.getFileName() + "."+armHerc.getExt().val());
			
			FileOutputStream foss = new FileOutputStream(out);
			foss.write(data);
			foss.close();
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
		getLogger().console("+ Write complete.");
		getLogger().console("------------------------------------------------------------------------------------");
		
	}

	public void importHercInf(String file) {
		try {
			getLogger().console("--------------------------IMPORTING /GAM/HERC_INF.DAT-----------------------------------");
			
			
			HercInfDTO dto = objectMapper.readValue(loadFileBytes(getAppPath() + file), HercInfDTO.class);
			
			if(dto == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			HercInfDTOService dtoService = new HercInfoDTOServiceImpl();
			HercInf hercInf = dtoService.fromDTO(dto);
			
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

			HercInfoTransformer transform = new HercInfoTransformer();
			byte[] data = transform.objectToBytes(hercInf);
			
			
			File out = new File(targDirPath + "/" + hercInf.getFileName() + "." + hercInf.getExt().val());
			
			FileOutputStream foss = new FileOutputStream(out);
			foss.write(data);
			foss.close();
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
		getLogger().console("+ Write complete.");
		getLogger().console("------------------------------------------------------------------------------------");
	}
	
	public void importHercsDat(String file) {
		try {
			getLogger().console("--------------------------IMPORTING /GAM/HERCS.DAT-----------------------------------");
			
			
			StartHercsDTO dto = objectMapper.readValue(loadFileBytes(getAppPath() + file), StartHercsDTO.class);
			
			if(dto == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			StartingHercsDTOService dtoService = new StartingHercsDTOServiceImpl();
			Hercs hercs = dtoService.fromDTO(dto);
			
			hercs.setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				hercs.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			HercsStartTransformer transform = new HercsStartTransformer();
			byte[] data = transform.objectToBytes(hercs);
			
			
			File out = new File(targDirPath + "/" + hercs.getFileName() + "." + hercs.getExt().val());
			
			FileOutputStream foss = new FileOutputStream(out);
			foss.write(data);
			foss.close();
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
		getLogger().console("+ Write complete.");
		getLogger().console("------------------------------------------------------------------------------------");
	}
	
	public void importArmWeap(String file) {
		try {
			getLogger().console("--------------------------IMPORTING /GAM/ARM_WEAP.DAT-----------------------------------");
			
			
			ArmWeapDTO dto = objectMapper.readValue(loadFileBytes(getAppPath() + file), ArmWeapDTO.class);
			
			if(dto == null) {
				throw new Exception("ERROR - failed to convert [" + file + "] to File Object.");
			}
			
			ArmWeapDTOService dtoService = new ArmWeapDTOServiceImpl();
			ArmWeap armWeap = dtoService.fromDTO(dto);
			
			armWeap.setFileName(getCleanFileName(fileNoExt(file)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DAT export to source directory.");
				armWeap.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(file));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			ArmWeapTransformer transform = new ArmWeapTransformer();
			byte[] data = transform.objectToBytes(armWeap);
			
			
			File out = new File(targDirPath + "/" + armWeap.getFileName() + "." + armWeap.getExt().val());
			
			FileOutputStream foss = new FileOutputStream(out);
			foss.write(data);
			foss.close();
			
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
		getLogger().console("+ Write complete.");
		getLogger().console("------------------------------------------------------------------------------------");
	}
}
