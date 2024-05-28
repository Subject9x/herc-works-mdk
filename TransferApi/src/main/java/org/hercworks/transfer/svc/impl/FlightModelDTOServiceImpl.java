package org.hercworks.transfer.svc.impl;

import org.hercworks.core.data.file.dbsim.FlightModel;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.FlightModelDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class FlightModelDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		FlightModel srcData = (FlightModel)source;
		
		FlightModelDTO dto = new FlightModelDTO();
		
		dto.setPitchRate(srcData.getPitchRate());
		dto.setRollRate(srcData.getRollRate());
		dto.setRudderForce(srcData.getRudderForce());
		dto.setPitchForce(srcData.getPitchForce());
		dto.setRollForce(srcData.getRollForce());
		dto.setThrustFactor(srcData.getThrustFactor());
		
		dto.setRollMax(srcData.getRollMax());
		
		dto.setUnk22_val16(srcData.getUnk22_val16());
		
		dto.setUnk26_5or6(srcData.getUnk26_5or6());
		
		dto.setRollForce(srcData.getRollForce());
		
		dto.setAltitudeMax(srcData.getAltitudeMax());
		dto.setUnk38_val6000(srcData.getUnk38_val6000());
		dto.setAirSpeedMax(srcData.getAirSpeedMax());
		dto.setAirSpeedMin(srcData.getAirSpeedMin());
		dto.setRollAccel(srcData.getRollAccel());
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		FlightModel fm = new FlightModel();
		fm.setExt(FileType.FM);
		
		FlightModelDTO dto = (FlightModelDTO)source;
		
		fm.setPitchRate(dto.getPitchRate());
		fm.setRollRate(dto.getRollRate());
		fm.setRudderForce(dto.getRudderForce());
		fm.setPitchForce(dto.getPitchForce());
		fm.setRollForce(dto.getRollForce());
		fm.setThrustFactor(dto.getThrustFactor());
		
		fm.setRollMax(dto.getRollMax());
		
		fm.setUnk22_val16(dto.getUnk22_val16());
		
		fm.setUnk26_5or6(dto.getUnk26_5or6());
		
		fm.setRollForce(dto.getRollForce());
		
		fm.setAltitudeMax(dto.getAltitudeMax());
		fm.setUnk38_val6000(dto.getUnk38_val6000());
		fm.setAirSpeedMax(dto.getAirSpeedMax());
		fm.setAirSpeedMin(dto.getAirSpeedMin());
		fm.setRollAccel(dto.getRollAccel());
		
		return fm;
	}

}
