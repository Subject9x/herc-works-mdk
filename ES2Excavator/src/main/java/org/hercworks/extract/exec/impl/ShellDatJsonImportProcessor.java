package org.hercworks.extract.exec.impl;

import java.io.IOException;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.file.dat.shell.HercInf;
import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.file.dat.shell.InitHerc;
import org.hercworks.core.data.file.dat.shell.RprHerc;
import org.hercworks.core.data.file.dat.shell.TrainingHercs;
import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.io.transform.shell.ArmHercTransformer;
import org.hercworks.core.io.transform.shell.ArmWeapTransformer;
import org.hercworks.core.io.transform.shell.HercInfoTransformer;
import org.hercworks.core.io.transform.shell.HercsStartTransformer;
import org.hercworks.core.io.transform.shell.InitHercTransformer;
import org.hercworks.core.io.transform.shell.RprHercTransform;
import org.hercworks.core.io.transform.shell.TrainingHercsTransform;
import org.hercworks.core.io.transform.shell.WeaponsDatTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.GenericJsonProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.extract.util.FileMatch;
import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;
import org.hercworks.transfer.dto.file.shell.HercInfDTO;
import org.hercworks.transfer.dto.file.shell.InitHercDTO;
import org.hercworks.transfer.dto.file.shell.RepairHercDTO;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.file.shell.TrainingHercsDTO;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.svc.impl.ArmHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.ArmWeapDTOServiceImpl;
import org.hercworks.transfer.svc.impl.HercInfoDTOServiceImpl;
import org.hercworks.transfer.svc.impl.InitHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.RepairHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.StartingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.TrainingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.WeaponsDatShellDTOServiceImpl;

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
					importJson(file.getName(), new WeaponsDatTransformer(), WeaponsDat.class, new WeaponsDatShellDTOServiceImpl(), WeaponsDatDTO.class);
					break;
				case ARM_HERC:
					importJson(file.getName(), new ArmHercTransformer(), ArmHerc.class, new ArmHercDTOServiceImpl(), ArmHercDTO.class);
					break;
				case ARM_WEAP:
					importJson(file.getName(), new ArmWeapTransformer(), ArmWeap.class, new ArmWeapDTOServiceImpl(), ArmWeapDTO.class);
					break;
				case RPR_HERC:
					importJson(file.getName(), new RprHercTransform(), RprHerc.class, new RepairHercDTOServiceImpl(), RepairHercDTO.class);
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
					
					break;
				case TRAINING_HERCS:
					importJson(file.getName(), new TrainingHercsTransform(), TrainingHercs.class, new TrainingHercsDTOServiceImpl(), TrainingHercsDTO.class);
					break;
				default:
					break;
			}
		}
	}
}