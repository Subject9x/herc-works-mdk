package org.hercworks.extract.exec.impl;

import java.io.IOException;

import org.hercworks.core.data.file.dat.sim.BeamData;
import org.hercworks.core.data.file.dat.sim.HercSimDat;
import org.hercworks.core.data.file.dat.sim.MissileDatFile;
import org.hercworks.core.data.file.dat.sim.ProjectileData;
import org.hercworks.core.data.file.dbsim.FlightModel;
import org.hercworks.core.data.file.dbsim.GunLayout;
import org.hercworks.core.data.file.dbsim.HercSimDamage;
import org.hercworks.core.data.file.dbsim.PaperDollGraphic;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.core.io.transform.dbsim.BeamDatFileTransformer;
import org.hercworks.core.io.transform.dbsim.FlightModelTransformer;
import org.hercworks.core.io.transform.dbsim.GunLayoutTransformer;
import org.hercworks.core.io.transform.dbsim.HercDamageFileTransformer;
import org.hercworks.core.io.transform.dbsim.HercSimDataTransformer;
import org.hercworks.core.io.transform.dbsim.MissileDatFileTransformer;
import org.hercworks.core.io.transform.dbsim.PaperDiagramGraphTransformer;
import org.hercworks.core.io.transform.dbsim.ProjectileDataTransformer;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.GenericJsonProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.extract.util.SimFileNames;
import org.hercworks.extract.util.impl.SimFileMatcher;
import org.hercworks.transfer.dto.file.sim.BeamDatDTO;
import org.hercworks.transfer.dto.file.sim.FlightModelDTO;
import org.hercworks.transfer.dto.file.sim.GunLayoutDTO;
import org.hercworks.transfer.dto.file.sim.HercDmgDTO;
import org.hercworks.transfer.dto.file.sim.HercSimDatDTO;
import org.hercworks.transfer.dto.file.sim.MissileDatDTO;
import org.hercworks.transfer.dto.file.sim.PaperDollDTO;
import org.hercworks.transfer.dto.file.sim.ProjectileDataDTO;
import org.hercworks.transfer.svc.impl.dbsim.BeamDatDTOServiceImpl;
import org.hercworks.transfer.svc.impl.dbsim.FlightModelDTOServiceImpl;
import org.hercworks.transfer.svc.impl.dbsim.GunLayoutDTOServiceImpl;
import org.hercworks.transfer.svc.impl.dbsim.HercSimDataDTOServiceImpl;
import org.hercworks.transfer.svc.impl.dbsim.HercSimDmgDTOServiceImpl;
import org.hercworks.transfer.svc.impl.dbsim.MissileDatDTOServiceImpl;
import org.hercworks.transfer.svc.impl.dbsim.PaperDollDTOServiceImpl;
import org.hercworks.transfer.svc.impl.dbsim.ProjectileDatDTOServiceImpl;
import org.hercworks.voln.FileType;

import com.fasterxml.jackson.databind.SerializationFeature;

public class SimDatExportProcessor extends GenericJsonProcessor{

	private SimFileMatcher fileMatcher = new SimFileMatcher();
	
	@Override
	public void init(ExcavatorCmdLine cmdLine, Logger logger) throws IOException  {
		super.init(cmdLine, logger);
		setObjectMapper(cmdLine.getJsonMapper());
		getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}
	
	@Override
	public boolean filterFile(FileItem file) {
		if(fileMatcher.matchFile(file.getName())) {
			filesToProcess.add(file);
			return true;
		}
		return false;
	}

	@Override
	public void processFiles() {
		for(FileItem file : filesToProcess) {
			SimFileNames match = SimFileNames.getByPattern(file.getName());
			if(match == null) {
//				boolean processed = false;
				for(HercLUT herc : HercLUT.values()) {
					if(file.getName().toLowerCase().contains(herc.getAbbrevDat().toLowerCase())) {
						if(file.getName().toLowerCase().contains(FileType.DMG.name().toLowerCase())) {
							exportJson(file.getName(), new HercDamageFileTransformer(), HercSimDamage.class, new HercSimDmgDTOServiceImpl(), HercDmgDTO.class);
//							processed = true;
							break;
						}
						else if(file.getName().toLowerCase().contains(FileType.DAT.name().toLowerCase())) {
							if(file.getName().toLowerCase().contains(HercLUT.RAZOR.getAbbrevDat().toLowerCase())){
								getLogger().console("WARN: not export RAZOR data at this time.");
							}
							else {
								exportJson(file.getName(), new HercSimDataTransformer(), HercSimDat.class, new HercSimDataDTOServiceImpl(), HercSimDatDTO.class);
							}
//							processed = true;
							break;
						}
						else if(file.getName().toLowerCase().contains(FileType.GL.name().toLowerCase())) {
							exportJson(file.getName(), new GunLayoutTransformer(), GunLayout.class, new GunLayoutDTOServiceImpl(), GunLayoutDTO.class);
							break;
						}
						else if(file.getName().toLowerCase().contains(FileType.PDG.name().toLowerCase())) {
							exportJson(file.getName(), new PaperDiagramGraphTransformer(), PaperDollGraphic.class, new PaperDollDTOServiceImpl(), PaperDollDTO.class);
							break;
						}
					}
				}
			}
			else {
				switch(match) {
				case BASES:
					break;
				case BASE_COL:
					break;
				case BEAM:
					exportJson(file.getName(), new BeamDatFileTransformer(), BeamData.class, new BeamDatDTOServiceImpl(), BeamDatDTO.class);
					break;
				case BFORMS:
					break;
				case BULLETS:
					exportJson(file.getName(), new MissileDatFileTransformer(), MissileDatFile.class, new MissileDatDTOServiceImpl(), MissileDatDTO.class);
					break;
				case COLORS:
					break;
				case DEBRIS:
					break;
				case FFORMS:
					break;
				case FIRE:
					break;
				case FLIGHT_MODEL:
					exportJson(file.getName(), new FlightModelTransformer(), FlightModel.class, new FlightModelDTOServiceImpl(), FlightModelDTO.class);
					break;
				case MAT0:
					break;
				case MFORMS:
					break;
				case PROJECTILE:
					exportJson(file.getName(), new ProjectileDataTransformer(), ProjectileData.class, new ProjectileDatDTOServiceImpl(), ProjectileDataDTO.class);
					break;
				case ROCKETS:
					exportJson(file.getName(), new MissileDatFileTransformer(), MissileDatFile.class, new MissileDatDTOServiceImpl(), MissileDatDTO.class);
					break;
				case SHADES:
					break;
				case STYLES:
					break;
				case WEAPONS:
					break;
				default:
					break;
				}
			}
		}
	}
}
