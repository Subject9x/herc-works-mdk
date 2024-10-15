package org.hercworks.transfer.svc.impl.dbsim;

import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dbsim.WeaponPaperDiagram;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.WpnPDGDTO;
import org.hercworks.transfer.dto.struct.dbsim.WeapnPDGDTOItem;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class WeapnPDGDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		WpnPDGDTO dto = new WpnPDGDTO();
		WeaponPaperDiagram data = (WeaponPaperDiagram)source;
		
		LinkedHashMap<Integer, WeapnPDGDTOItem> items = new LinkedHashMap<Integer, WeapnPDGDTOItem>();
		
		for(int i = 0; i < data.getEntries().length; i++) {
			WeapnPDGDTOItem item = new WeapnPDGDTOItem();
			
			item.setX(data.getEntries()[i].getX());
			item.setY(data.getEntries()[i].getY());
			
			items.put(i, item);
		}
		
		dto.setEntries(items);
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		WpnPDGDTO dto =(WpnPDGDTO)source;
		WeaponPaperDiagram data = new WeaponPaperDiagram();
		
		data.setFileName(dto.getFileName());
		data.setDir(FileType.PDG);
		data.setExt(FileType.PDG);
		
		WeaponPaperDiagram.Entry[] entries = new WeaponPaperDiagram.Entry[dto.getEntries().size()];
		
		for(int i = 0; i < data.getEntries().length; i++) {
			WeaponPaperDiagram.Entry entry = data.newEntry();
			entry.setX(dto.getEntries().get(i).getX());
			entry.setY(dto.getEntries().get(i).getY());
			entries[i] = entry;
		}
		data.setEntries(entries);
		
		return data;
	}

}
