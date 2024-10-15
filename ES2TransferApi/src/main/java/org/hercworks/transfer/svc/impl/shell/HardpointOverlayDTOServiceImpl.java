package org.hercworks.transfer.svc.impl.shell;

import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dat.shell.HardpointOverlayConfig;
import org.hercworks.core.data.struct.herc.HercLUT;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.HardpointOverlayDTO;
import org.hercworks.transfer.dto.struct.shell.HardpointOverlayDTOHerc;
import org.hercworks.transfer.dto.struct.shell.HardpointOverlayDTOSegment;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class HardpointOverlayDTOServiceImpl implements GeneralDTOService {
	
	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		HardpointOverlayDTO dto = new HardpointOverlayDTO();
		HardpointOverlayConfig hercOverlay = (HardpointOverlayConfig)source;
		
		HardpointOverlayDTOHerc[] entries = new HardpointOverlayDTOHerc[hercOverlay.getEntries().length];
		
		for(int i = 0; i < hercOverlay.getEntries().length; i++) {
			HardpointOverlayConfig.Herc entry = hercOverlay.getEntries()[i];
			HardpointOverlayDTOHerc item = new HardpointOverlayDTOHerc();
			
			item.setHerc(HercLUT.getById(entry.getHercId()));
			LinkedHashMap<String, HardpointOverlayDTOSegment> list = new LinkedHashMap<String, HardpointOverlayDTOSegment>();
			
			
			for(int c=0; c < entry.getAreas().length; c++) {
				HardpointOverlayDTOSegment dtoHercPart = new HardpointOverlayDTOSegment();
				HardpointOverlayConfig.Herc.OverlayArea  seg = entry.getAreas()[c];
				
				String id = "";
				if(source.getFileName().toLowerCase().contains("rpr")){
					id = HardpointOverlayDTO.hercPartIds[c];
				}
				else if(source.getFileName().toLowerCase().contains("arm")) {
					id = HardpointOverlayDTO.hardpointId + c;
				}
				
				dtoHercPart.setX(seg.getX());
				dtoHercPart.setY(seg.getY());
				dtoHercPart.setWidth(seg.getWidth());
				dtoHercPart.setHeight(seg.getHeight());
				
				list.put(id, dtoHercPart);
			}
			item.setPartAreas(list);
			entries[i] = item;
		}
		dto.setEntries(entries);
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		HardpointOverlayDTO dto = (HardpointOverlayDTO)source;
		HardpointOverlayConfig repairHercOverlay = new HardpointOverlayConfig();
		
		repairHercOverlay.setFileName(dto.getFileName());
		repairHercOverlay.setDir(FileType.GAM);
		repairHercOverlay.setExt(FileType.DAT);
		
		HardpointOverlayConfig.Herc[] entries = new HardpointOverlayConfig.Herc[dto.getEntries().length];
		
		for(int i = 0; i < dto.getEntries().length; i++) {
			HardpointOverlayDTOHerc entry = dto.getEntries()[i];
			HardpointOverlayConfig.Herc item = repairHercOverlay.newEntry();
			
			int size = entry.getPartAreas().values().size();
			
			item.setHercId(entry.getHerc().getId());
			HardpointOverlayConfig.Herc.OverlayArea[] segments = new HardpointOverlayConfig.Herc.OverlayArea[size];
			
			int idx = 0;
			for(HardpointOverlayDTOSegment dtoPart : entry.getPartAreas().values()) {
				HardpointOverlayConfig.Herc.OverlayArea seg = item.newSegment();
				
				seg.setId(idx);
				seg.setX(dtoPart.getX());
				seg.setY(dtoPart.getY());
				seg.setWidth(dtoPart.getWidth());
				seg.setHeight(dtoPart.getHeight());
				
				segments[idx] = seg;
				
				idx += 1;
			}
			item.setAreas(segments);
			entries[i] = item;
		}
		repairHercOverlay.setEntries(entries);
		
		return repairHercOverlay;
	}

}
