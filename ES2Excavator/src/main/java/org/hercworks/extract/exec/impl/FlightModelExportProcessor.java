package org.hercworks.extract.exec.impl;

import java.io.IOException;

import org.hercworks.core.data.file.FlightModel;
import org.hercworks.core.io.transform.common.FlightModelTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.GenericJsonProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.extract.util.FileMatch;
import org.hercworks.transfer.dto.file.sim.FlightModelDTO;
import org.hercworks.transfer.svc.impl.FlightModelDTOServiceImpl;
import org.hercworks.voln.FileType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class FlightModelExportProcessor extends GenericJsonProcessor {
	
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
		if(file.getName().toLowerCase().contains("."+FileType.FM.val())) {
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
			exportJson(file.getName(), new FlightModelTransformer(), FlightModel.class, new FlightModelDTOServiceImpl(), FlightModelDTO.class);
		}
	}
}
