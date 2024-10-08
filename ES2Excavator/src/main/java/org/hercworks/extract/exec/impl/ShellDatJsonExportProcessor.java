package org.hercworks.extract.exec.impl;

import java.io.IOException;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.file.dat.shell.CareerMissions;
import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.file.dat.shell.InitHerc;
import org.hercworks.core.data.file.dat.shell.HardpointOverlayConfig;
import org.hercworks.core.data.file.dat.shell.RprHerc;
import org.hercworks.core.data.file.dat.shell.TrainingHercs;
import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.core.io.transform.common.PlayerSaveTransform;
import org.hercworks.core.io.transform.shell.ArmHercTransformer;
import org.hercworks.core.io.transform.shell.ArmWeapTransformer;
import org.hercworks.core.io.transform.shell.CareerDataTransformer;
import org.hercworks.core.io.transform.shell.HercInfoTransformer;
import org.hercworks.core.io.transform.shell.HercsStartTransformer;
import org.hercworks.core.io.transform.shell.InitHercTransformer;
import org.hercworks.core.io.transform.shell.HardpointOverlayTransformer;
import org.hercworks.core.io.transform.shell.RprHercTransform;
import org.hercworks.core.io.transform.shell.TrainingHercsTransform;
import org.hercworks.core.io.transform.shell.WeaponsDatTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.GenericJsonProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.extract.util.ShellFileMatch;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;
import org.hercworks.transfer.dto.file.shell.CareerMissionsDTO;
import org.hercworks.transfer.dto.file.shell.HercInfDTO;
import org.hercworks.transfer.dto.file.shell.InitHercDTO;
import org.hercworks.transfer.dto.file.shell.HardpointOverlayDTO;
import org.hercworks.transfer.dto.file.shell.RepairHercDTO;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.file.shell.TrainingHercsDTO;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.dto.file.sys.sav.PlayerSaveFileDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.transfer.svc.impl.PlayerSaveDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.ArmHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.ArmWeapDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.CareerMissionsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.HercInfoDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.InitHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.RepairHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.HardpointOverlayDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.StartingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.TrainingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.WeaponsDatShellDTOServiceImpl;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

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
public class ShellDatJsonExportProcessor extends GenericJsonProcessor{
	
	@Override
	public void init(ExcavatorCmdLine cmdLine, Logger logger) throws IOException  {
		super.init(cmdLine, logger);
		setObjectMapper(cmdLine.getJsonMapper());
		getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}
	
	@Override
	public boolean filterFile(FileItem file) {

		String fileName = file.getName().toLowerCase();
		if(fileName.contains(".json")) {
			return false;
		}
		
		if(ShellFileMatch.getByPattern(file.getName()) != null
				|| fileName.contains(FileType.GAM.name().toLowerCase())) {
			filesToProcess.add(file);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void processFiles() {
		for(FileItem file : filesToProcess) {
			ShellFileMatch match = ShellFileMatch.getByPattern(file.getName()); 
			if(match != null) {
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
					case HARDPOINT_OVERLAY:
						exportJson(file.getName(), new HardpointOverlayTransformer(), HardpointOverlayConfig.class, new HardpointOverlayDTOServiceImpl(), HardpointOverlayDTO.class);
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
						exportJson(file.getName(), new CareerDataTransformer(), CareerMissions.class, new CareerMissionsDTOServiceImpl(), CareerMissionsDTO.class);
						break;
					case TRAINING_HERCS:
						exportJson(file.getName(), new TrainingHercsTransform(), TrainingHercs.class, new TrainingHercsDTOServiceImpl(), TrainingHercsDTO.class);
						break;
					case SAVE_FILE:
						exportJson(file.getName(), new PlayerSaveTransform(), PlayerSave.class, new PlayerSaveDTOServiceImpl(), PlayerSaveFileDTO.class);
						break;
					default:
						break;
				}
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
}
