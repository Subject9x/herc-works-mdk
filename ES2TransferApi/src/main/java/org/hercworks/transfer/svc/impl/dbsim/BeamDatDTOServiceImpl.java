package org.hercworks.transfer.svc.impl.dbsim;

import org.hercworks.core.data.file.dat.sim.BeamData;
import org.hercworks.core.data.file.dat.sim.BeamData.Entry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.BeamDatDTO;
import org.hercworks.transfer.dto.struct.dbsim.BeamDatEntryDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class BeamDatDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		BeamData data = (BeamData)source;
		BeamDatDTO dto = new BeamDatDTO(data.getData().length);
		
		for(int b=0; b < data.getData().length; b++) {
			Entry entry = data.getData()[b];
			BeamDatEntryDTO beam = new BeamDatEntryDTO();
			
			beam.setId(b);
			beam.setWidth(entry.getWidth());
			beam.setColorId(entry.getColorId());
			
			dto.getBeams()[b] = beam;
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		BeamDatDTO dto = (BeamDatDTO)source;
		
		BeamData data = new BeamData((short)dto.getBeams().length);
		
		data.setFileName(dto.getFileName());
		data.setExt(FileType.DAT);
		data.setDir(FileType.DAT);
		
		for(int b=0; b < data.getData().length; b++) {
			BeamDatEntryDTO beam = dto.getBeams()[b];
			Entry entry = data.newEntry((short)beam.getWidth(), (short)beam.getColorId());
			
			data.getData()[b] = entry;
		}
		return data;
	}

}
