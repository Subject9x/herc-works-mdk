package org.hercworks.transfer.svc.impl;

import org.hercworks.core.data.file.dbsim.GunLayout;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.GunLayoutDTO;
import org.hercworks.transfer.dto.struct.dbsim.GunLayoutEntryDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class GunLayoutDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		GunLayout src = (GunLayout)source;
		
		GunLayoutDTO dto = new GunLayoutDTO();
		
		GunLayoutEntryDTO[] hardpoints = new GunLayoutEntryDTO[src.getTotalGuns()];
		
		for(int i=0; i < src.getTotalGuns(); i++) {
			GunLayoutEntryDTO entry = new GunLayoutEntryDTO();
			GunLayout.HardpointEntry hardpoint = src.getHardpoints()[i];
			
			entry.setBoneId(hardpoint.getBoneId());
			entry.setUnk1_val(hardpoint.getUnk1_val());
			entry.setUnk2_val(hardpoint.getUnk2_val());
			entry.setAngleDirOption(hardpoint.getAngleDirOption());
			entry.setFireChainNumber(hardpoint.getFireChainNumber());
			entry.setUnk3_0or_Neg5000(hardpoint.getUnk3_0or_Neg5000());
			entry.setUnk4_0or_5000(hardpoint.getUnk4_0or_5000());
			entry.setUnk5_Neg8000(hardpoint.getUnk5_Neg8000());
			entry.setUnk6_16000(hardpoint.getUnk6_16000());
			entry.getOffset()[0] = hardpoint.getOffset()[0];
			entry.getOffset()[1] = hardpoint.getOffset()[1];
			entry.getOffset()[2] = hardpoint.getOffset()[2];
			entry.setUnk7_val(hardpoint.getUnk7_val());
			entry.setHardpointId(hardpoint.getHardpointId());
			entry.setUnk8_val(hardpoint.getUnk8_val());
			
			hardpoints[i] = entry;
		}
		
		dto.setHardpoints(hardpoints);
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		GunLayoutDTO dto = (GunLayoutDTO)source;
		
		GunLayout data = new GunLayout();
		data.setExt(FileType.GL);
		
		data.setTotalGuns((short)dto.getHardpoints().length);
		
		GunLayout.HardpointEntry[] hardpoints = new GunLayout.HardpointEntry[data.getTotalGuns()];
		
		for(int i=0; i < data.getTotalGuns(); i++) {
			
			GunLayout.HardpointEntry entry = data.newEntry();
			GunLayoutEntryDTO hardpoint = dto.getHardpoints()[i];
			
			entry.setBoneId((short)hardpoint.getBoneId());
			entry.setUnk1_val((short)hardpoint.getUnk1_val());
			entry.setUnk2_val((short)hardpoint.getUnk2_val());
			entry.setAngleDirOption((byte)hardpoint.getAngleDirOption());
			entry.setFireChainNumber((byte)hardpoint.getFireChainNumber());
			entry.setUnk3_0or_Neg5000((short)hardpoint.getUnk3_0or_Neg5000());
			entry.setUnk4_0or_5000((short)hardpoint.getUnk4_0or_5000());
			entry.setUnk5_Neg8000((short)hardpoint.getUnk5_Neg8000());
			entry.setUnk6_16000((short)hardpoint.getUnk6_16000());
			entry.getOffset()[0] = (short)hardpoint.getOffset()[0];
			entry.getOffset()[1] = (short)hardpoint.getOffset()[1];
			entry.getOffset()[2] = (short)hardpoint.getOffset()[2];
			entry.setUnk7_val((byte)hardpoint.getUnk7_val());
			entry.setHardpointId((byte)hardpoint.getHardpointId());
			entry.setUnk8_val((short)hardpoint.getUnk8_val());
			
			hardpoints[i] = entry;
		}
		
		data.setHardpoints(hardpoints);
		
		return data;
	}
}
