package org.hercworks.transfer.svc.impl.shell;

import java.util.ArrayList;
import java.util.List;

import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.data.struct.MissileType;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.dto.struct.shell.WeaponsDatItem;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

import at.favre.lib.bytes.Bytes;

public class WeaponsDatShellDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		WeaponsDat srcData = (WeaponsDat)source;
		
		WeaponsDatDTO dto = new WeaponsDatDTO();
		
		dto.setTotal(srcData.getTotalCount());
		
		WeaponsDatItem[] entries = new WeaponsDatItem[srcData.getTotalCount()];
		for(int i=0; i < srcData.getTotalCount(); i++) {
			WeaponsDatItem item = new WeaponsDatItem();
			WeaponsDat.Entry entry = srcData.getData()[i];
			
			item.setId(entry.getId());
			String name = new String(Bytes.from(entry.getName(), 0, entry.getNameLen()-1).array());
			item.setName(name);
			item.setSalvageCost(entry.getSalvageCost());
			item.setStartUnlock(entry.getStartUnlock());
			item.setAutobuildPriority(entry.getAutobuildPriority());
			entries[i] = item;
		}
		dto.setWeapons(entries);
		
		List<String[]> startList = new ArrayList<String[]>();
		for(int i=0; i < srcData.getStartWeaponTotal(); i++) {
			UiWeaponEntry entry = srcData.getStartingWeapons()[i];
			
			String[] line = new String[3];
			line[0] = WeaponLUT.getById(entry.getItemId()).getName();
			line[1] = String.valueOf(entry.getHealthPercent());
			line[2] = String.valueOf(entry.getMissileType());
			startList.add(line);
		}
		dto.setStartingList(startList);
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		
		WeaponsDatDTO srcDto = (WeaponsDatDTO)source;
		
		WeaponsDat data = new WeaponsDat(srcDto.getTotal());
		
		data.setExt(FileType.DAT);
		
		for(int i=0; i < srcDto.getTotal(); i++) {
			WeaponsDat.Entry entry = data.addEntry(i);
			WeaponsDatItem item = srcDto.getWeapons()[i];
			
			entry.setId(item.getId());
			entry.setNameLen((byte)(item.getName().length()+1));
			entry.setName(Bytes.from(item.getName().getBytes()).append((byte)0).array());
			entry.setSalvageCost(item.getSalvageCost());
			entry.setStartUnlock(item.getStartUnlock());
			entry.setAutobuildPriority(item.getAutobuildPriority());
		}
		
		data.setStartingWeapons(new UiWeaponEntry[srcDto.getStartingList().size()]);
		for(int i=0; i < srcDto.getStartingList().size(); i++) {
			String[] line = srcDto.getStartingList().get(i);
			
			UiWeaponEntry entry = new UiWeaponEntry();
			
			entry.setItemId((short)WeaponLUT.valueOf(line[0]).getId());
			entry.setHealthPercent(Short.valueOf(line[1]));
			entry.setMissileType(MissileType.valueOf(line[2]));
			
			data.getStartingWeapons()[i] = entry;
		}

		data.setStartWeaponTotal((short)srcDto.getStartingList().size());
		return data;
	}

}
