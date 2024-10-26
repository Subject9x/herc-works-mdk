package org.hercworks.transfer.svc.impl.dbsim;

import org.hercworks.core.data.file.dat.sim.DebrisHerc;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.DebrisHercDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class DebrisHercDTOServiceImpl implements GeneralDTOService {

	
	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		DebrisHerc data = (DebrisHerc)source;
		DebrisHercDTO dto = new DebrisHercDTO();
		
		if(data.getData().length > 0) {
			DebrisHercEntryDTO[] entries = new DebrisHercEntryDTO[data.getData().length];
			
			for(int i = 0; i < entries.length; i++) {
				DebrisHercEntryDTO item = new DebrisHercEntryDTO();
				DebrisHerc.Entry entry = data.getData()[i];
				
				item.setUnk1_val(entry.getUnk1_val());
				item.setSpawnFlag(entry.setSpawnDebrisFlag() == 1 ? true : false);
				item.setMeshGroupd(entry.getMeshGroupId());
				item.setUnk4_0A(entry.getUnk4_0A());
				item.setUnk5_03(entry.getUnk5_03());
				item.getThrowDir()[0] = entry.getThrowDir()[0]; 
				item.getThrowDir()[1] = entry.getThrowDir()[1]; 
				item.getThrowDir()[2] = entry.getThrowDir()[2]; 
				item.setMass(entry.getMass());
				
				entries[i] = item;
			}
			dto.setPieces(entries);
		}
		else {
			dto.setPieces(new DebrisHercEntryDTO[0]);
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		DebrisHercDTO dto = (DebrisHercDTO)source;
		DebrisHerc data = new DebrisHerc();
		data.setFileName(dto.getFileName());
		data.setExt(FileType.DAT);
		data.setDir(FileType.DAT);
		
		if(dto.getPieces().length > 0) {
			DebrisHerc.Entry[] entries = new DebrisHerc.Entry[dto.getPieces().length];
			
			for(int i = 0; i < entries.length; i++) {
				DebrisHercEntryDTO item = dto.getPieces()[i];
				DebrisHerc.Entry entry = data.newEntry();
				
				entry.setUnk1_val((short)item.getUnk1_val());
				entry.setSpawnDebrisFlag((short)(item.getSpawnFlag() == true ? 1 : 0));
				entry.setMeshGroupId((short)item.getMeshGroupId());
				entry.setUnk4_0A((short)item.getUnk4_0A());
				entry.setUnk5_03((short)item.getUnk5_03());
				entry.getThrowDir()[0] = (short)item.getThrowDir()[0]; 
				entry.getThrowDir()[1] = (short)item.getThrowDir()[1]; 
				entry.getThrowDir()[2] = (short)item.getThrowDir()[2]; 
				entry.setMass((short)item.getMass());
				
				entries[i] = entry;
			}
			data.setData(entries);
		}
		else {
			data.setData(new DebrisHerc.Entry[0]);
		}
		return data;
	}
}
