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
import org.hercworks.voln.FileType;

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
public class ShellDatJsonImportProcessor extends GenericJsonProcessor{
	
	@Override
	public void init(ExcavatorCmdLine cmdLine, Logger logger) throws IOException  {
		super.init(cmdLine, logger);
		setObjectMapper(cmdLine.getJsonMapper());
		getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}
	
	@Override
	public boolean filterFile(FileItem file) {

		String fileName = file.getName().toLowerCase();
		if(!fileName.contains(".json")) {
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
						
			switch(match) {
				case WEAPONS:
					importJson(file.getName(), new WeaponsDatTransformer(), WeaponsDat.class, new WeaponsDatShellDTOServiceImpl(), WeaponsDatDTO.class);
					break;
				case ARM_HERC:
					importJson(file.getName(), new ArmHercTransformer(), ArmHerc.class, new ArmHercDTOServiceImpl(), ArmHercDTO.class);
					break;
				case ARM_WEAP:
					importJson(file.getName(), new ArmWeapTransformer(), ArmWeap.class, new ArmWeapDTOServiceImpl(), ArmWeapDTO.class);
					break;
				case RPR_HERC:
					if(file.getName().toUpperCase().contains("RPR_HOTS")) {
						importJson(file.getName(), new HardpointOverlayTransformer(), HardpointOverlayConfig.class, new HardpointOverlayDTOServiceImpl(), HardpointOverlayDTO.class);	
					}
					else {
						importJson(file.getName(), new RprHercTransform(), RprHerc.class, new RepairHercDTOServiceImpl(), RepairHercDTO.class);
					}
					break;
				case INI_HERC:
					importJson(file.getName(), new InitHercTransformer(), InitHerc.class, new InitHercDTOServiceImpl(), InitHercDTO.class);
					break;
				case HERCS:
					importJson(file.getName(), new  HercsStartTransformer(), Hercs.class, new StartingHercsDTOServiceImpl(), StartHercsDTO.class);
					break;
				case HERC_INF:
					importJson(file.getName(), new  HercInfoTransformer(), HercInf.class, new HercInfoDTOServiceImpl(), HercInfDTO.class);
					break;
				case CAREER:
					importJson(file.getName(), new CareerDataTransformer(), CareerMissions.class, new CareerMissionsDTOServiceImpl(), CareerMissionsDTO.class);
					break;
				case TRAINING_HERCS:
					importJson(file.getName(), new TrainingHercsTransform(), TrainingHercs.class, new TrainingHercsDTOServiceImpl(), TrainingHercsDTO.class);
					break;
				case SAVE_FILE:
					importJson(file.getName(), new PlayerSaveTransform(), PlayerSave.class, new PlayerSaveDTOServiceImpl(), PlayerSaveFileDTO.class);
					break;
				default:
					break;
			}
		}
	}
}