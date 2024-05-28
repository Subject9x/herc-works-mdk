package org.hercworks.extract.exec.impl;

import java.io.IOException;

import org.hercworks.core.data.file.dbsim.GunLayout;
import org.hercworks.core.io.transform.dbsim.GunLayoutTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.GenericJsonProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.transfer.dto.file.sim.GunLayoutDTO;
import org.hercworks.transfer.svc.impl.GunLayoutDTOServiceImpl;
import org.hercworks.voln.FileType;

import com.fasterxml.jackson.databind.SerializationFeature;

public class GunLayoutExportProcessor extends GenericJsonProcessor {

	@Override
	public void init(ExcavatorCmdLine cmdLine, Logger logger) throws IOException  {
		super.init(cmdLine, logger);
		setObjectMapper(cmdLine.getJsonMapper());
		getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}
	
	@Override
	public boolean filterFile(FileItem file) {
		if(file.getName().toLowerCase().contains("/gl/")
				&& file.getName().toLowerCase().contains("."+FileType.GL.name().toLowerCase())){
			filesToProcess.add(file);
			return true;
		}		
		return false;
	}

	@Override
	public void processFiles() {
		for(FileItem file : filesToProcess) {
			exportJson(file.getName(), new GunLayoutTransformer(), GunLayout.class, new GunLayoutDTOServiceImpl(), GunLayoutDTO.class);
		}
	}
}
