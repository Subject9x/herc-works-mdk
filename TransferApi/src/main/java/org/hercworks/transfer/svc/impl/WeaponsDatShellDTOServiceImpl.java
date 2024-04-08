package org.hercworks.transfer.svc.impl;

import java.util.ArrayList;
import java.util.List;

import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.core.data.struct.vshell.hercs.UiWeaponEntry;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.dto.shell.struct.WeaponsDatItem;
import org.hercworks.transfer.svc.WeaponsDatShellDTOService;

public class WeaponsDatShellDTOServiceImpl implements WeaponsDatShellDTOService {

	@Override
	public WeaponsDatDTO convertToDTO(WeaponsDat source) {
		
		WeaponsDatDTO dto = new WeaponsDatDTO();
		
		dto.setTotal(source.getTotalCount());
		
		WeaponsDatItem[] entries = new WeaponsDatItem[source.getTotalCount()];
		for(int i=0; i < source.getTotalCount(); i++) {
			WeaponsDatItem item = new WeaponsDatItem();
			WeaponsDat.Entry entry = source.getData()[i];
			
			item.setId(entry.getId());
			String name = entry.getName();
			name = name.substring(0, name.length() - 1);
			item.setName(name);
			item.setSalvageCost(entry.getSalvageCost());
			item.setStartUnlock(entry.getStartUnlock());
			item.setUnk2(entry.getUnk2());
			entries[i] = item;
		}
		dto.setWeapons(entries);
		
		List<String[]> startList = new ArrayList<String[]>();
		for(int i=0; i < source.getStartWeaponTotal(); i++) {
			UiWeaponEntry entry = source.getStartingWeapons()[i];
			
			String[] line = new String[3];
			line[0] = WeaponLUT.getById(entry.getItemId()).getName();
			line[1] = String.valueOf(entry.getHealthPercent());
			line[2] = String.valueOf(entry.getMissileEnum());
			startList.add(line);
		}
		dto.setStartingList(startList);
		
		return dto;
	}

	@Override
	public WeaponsDat fromDTO(WeaponsDatDTO source) {
		
		WeaponsDat data = new WeaponsDat(source.getTotal());
		for(int i=0; i < source.getTotal(); i++) {
			WeaponsDat.Entry entry = data.addEntry(i);
			WeaponsDatItem item = source.getWeapons()[i];
			
			entry.setId(item.getId());
			entry.setNameLen((byte)item.getName().length());
			entry.setName(item.getName()+" ");
			entry.setSalvageCost(item.getSalvageCost());
			entry.setStartUnlock(item.getStartUnlock());
			entry.setUnk2(item.getUnk2());
		}
		
		data.setStartingWeapons(new UiWeaponEntry[source.getStartingList().size()]);
		for(int i=0; i < source.getStartingList().size(); i++) {
			String[] line = source.getStartingList().get(i);
			
			UiWeaponEntry entry = new UiWeaponEntry();
			
			entry.setItemId((short)WeaponLUT.valueOf(line[0]).getId());
			entry.setHealthPercent(Short.valueOf(line[1]));
			entry.setMissileEnum(Short.valueOf(line[2]));
			
			data.getStartingWeapons()[i] = entry;
		}

		data.setStartWeaponTotal((short)source.getStartingList().size());
		return data;
	}

}
