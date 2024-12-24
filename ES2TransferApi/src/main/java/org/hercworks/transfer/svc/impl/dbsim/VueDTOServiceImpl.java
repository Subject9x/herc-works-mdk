package org.hercworks.transfer.svc.impl.dbsim;

import org.hercworks.core.data.file.dbsim.Vue;
import org.hercworks.core.data.file.dbsim.Vue.Entry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.VueDTO;
import org.hercworks.transfer.dto.struct.dbsim.VueRegionDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class VueDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		Vue vue = (Vue)source;
		VueDTO dto = new VueDTO();
		
		dto.setRegions(new VueRegionDTO[vue.getEntries().length]);
		
		for(int i=0; i < vue.getEntries().length; i++) {
			Entry entry = vue.getEntries()[i];
			VueRegionDTO region = new VueRegionDTO();
			
			region.setOriginX(entry.getOriginX());
			region.setOriginY(entry.getOriginY());
			region.setWidth(entry.getWidthMax());
			region.setHeight(entry.getHeightMax());
			
			region.setUnkOriginX(entry.getUnkOfsX());
			region.setUnkOriginY(entry.getUnkOfsY());
			region.setUnkWidth(entry.getWidthMax());
			region.setUnkHeight(entry.getHeightMax());
			
			dto.getRegions()[i] = region;
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		VueDTO dto = (VueDTO)source;
		Vue vue = new Vue();
		vue.setFileName(dto.getFileName());
		vue.setDir(FileType.VUE);
		vue.setExt(FileType.VUE);
		
		vue.setEntries(new Entry[dto.getRegions().length]);
		
		for(int i=0; i < dto.getRegions().length; i++) {
			VueRegionDTO region = dto.getRegions()[i];
			Entry entry = vue.newEntry();
			
			entry.setOriginX(region.getOriginX());
			entry.setOriginY(region.getOriginY());
			entry.setWidthMax(region.getWidth());
			entry.setHeightMax(region.getHeight());
			
			entry.setUnkOfsX(region.getUnkOriginX());
			entry.setUnkOfsY(region.getUnkOriginY());
			entry.setUnkOfsW(region.getUnkWidth());
			entry.setUnkOfsH(region.getUnkHeight());
			
			vue.getEntries()[i] = entry;
		}
		
		return vue;
	}

}
