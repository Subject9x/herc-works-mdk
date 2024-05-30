package org.hercworks.extract.exec.impl;

import java.io.IOException;

import org.hercworks.core.data.file.dbsim.HercSimDamage;
import org.hercworks.core.io.transform.dbsim.HercDamageFileTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.GenericJsonProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.transfer.dto.file.sim.HercDmgDTO;
import org.hercworks.transfer.svc.impl.HercSimDmgDTOServiceImpl;
import org.hercworks.voln.FileType;

import com.fasterxml.jackson.databind.SerializationFeature;

public class HercDamageExportProcessor extends GenericJsonProcessor {
	
	@Override
	public void init(ExcavatorCmdLine cmdLine, Logger logger) throws IOException  {
		super.init(cmdLine, logger);
		setObjectMapper(cmdLine.getJsonMapper());
		getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}
	
	@Override
	public boolean filterFile(FileItem file) {
		if(file.getName().toLowerCase().contains(".json")) {
			return false;
		}
		if(file.getName().toLowerCase().contains("."+FileType.DMG.name().toLowerCase())) {
			filesToProcess.add(file);
			return true;
		}
		return false;
	}

	@Override
	public void processFiles() {
		for(FileItem file : filesToProcess) {
			exportJson(file.getName(), new HercDamageFileTransformer(), HercSimDamage.class, new HercSimDmgDTOServiceImpl(), HercDmgDTO.class);
		}
	}

}
