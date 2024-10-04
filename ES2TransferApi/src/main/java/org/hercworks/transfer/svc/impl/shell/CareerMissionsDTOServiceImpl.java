package org.hercworks.transfer.svc.impl.shell;

import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dat.shell.CareerMissions;
import org.hercworks.core.data.struct.MissionSector;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.CareerMissionsDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class CareerMissionsDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		CareerMissions data = (CareerMissions)source;
		CareerMissionsDTO dto = new CareerMissionsDTO();
		
		LinkedHashMap<String, int[]> sectors = new LinkedHashMap<String, int[]>();
		
		for(MissionSector m : data.getSectors().keySet()) {
			sectors.put(m.val(), data.getSectors().get(m));
		}
		dto.setSectors(sectors);
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		CareerMissionsDTO dto = (CareerMissionsDTO)source;
		CareerMissions data = new CareerMissions();
		data.setFileName(dto.getFileName());
		data.setExt(FileType.typeFromVal(dto.getFileExt()));
		data.setDir(FileType.typeFromVal(dto.getDir()));
		
		LinkedHashMap<MissionSector, int[]> sectors = new LinkedHashMap<MissionSector, int[]>();
		
		for(String s : dto.getSectors().keySet()) {
			
			sectors.put(MissionSector.valueOf(s), dto.getSectors().get(s));
		}
		
		data.setSectors(sectors);
		
		return data;
	}

}
