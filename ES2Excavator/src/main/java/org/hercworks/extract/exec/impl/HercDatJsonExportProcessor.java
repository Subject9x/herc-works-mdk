package org.hercworks.extract.exec.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.hercworks.core.data.file.dat.sim.HercSimDat;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.io.transform.dbsim.HercSimDataTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.GenericJsonProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.transfer.dto.file.sim.HercSimDatDTO;
import org.hercworks.transfer.svc.impl.HercSimDataDTOServiceImpl;

import com.fasterxml.jackson.databind.SerializationFeature;

public class HercDatJsonExportProcessor extends GenericJsonProcessor {
	
	private List<HercLUT> specialCases = Arrays.asList(HercLUT.RAZOR, HercLUT.SKIMMER, HercLUT.SPIDER);
	
	@Override
	public void init(ExcavatorCmdLine cmdLine, Logger logger) throws IOException  {
		super.init(cmdLine, logger);
		setObjectMapper(cmdLine.getJsonMapper());
		getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}
	
	@Override
	public boolean filterFile(FileItem file) {
		boolean filter = false;
		for(HercLUT herc : HercLUT.values()) {
			if(file.getName().toLowerCase().contains(herc.getName().toLowerCase()+".dat")) {
				filesToProcess.add(file);
				filter = true;
			}
		}
		return filter;
	}
	
	@Override
	public void processFiles() {
		for(FileItem file : filesToProcess) {
			//Razor is an edge case
			if(!checkCase(file.getName())) {
				exportJson(file.getName(), new HercSimDataTransformer(), HercSimDat.class, new HercSimDataDTOServiceImpl(), HercSimDatDTO.class);
				return;
			}
			else{
				getLogger().consoleDebug("RAZOR.DAT parsing todo");
			}
		}
	}
	
	private boolean checkCase(String fileName) {
		
		for(HercLUT entry : specialCases) {
			if(fileName.toLowerCase().contains(entry.getName().toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
