package org.hercworks.transfer.svc.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.hercworks.core.data.file.msn.MapCoord;
import org.hercworks.core.data.file.msn.MiscEntityInfo;
import org.hercworks.core.data.file.msn.MissionFile;
import org.hercworks.core.data.file.msn.UnitInfo;
import org.hercworks.core.data.file.msn.UnkEntity102Bytes;
import org.hercworks.core.data.file.msn.UnkEntity164Bytes;
import org.hercworks.core.data.file.msn.UnkEntity22Byte;
import org.hercworks.core.data.file.msn.UnkEntity58Byte;
import org.hercworks.core.data.file.msn.UnkHeaderEntry;
import org.hercworks.core.data.struct.WeaponLUT;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.msn.MapCoordDTO;
import org.hercworks.transfer.dto.file.msn.MapCoordRefDTO;
import org.hercworks.transfer.dto.file.msn.MapObjectDTO;
import org.hercworks.transfer.dto.file.msn.MiscEntityInfoDTO;
import org.hercworks.transfer.dto.file.msn.MissionFileDTO;
import org.hercworks.transfer.dto.file.msn.UnitInfoDTO;
import org.hercworks.transfer.dto.file.msn.UnkEntity102BytesDTO;
import org.hercworks.transfer.dto.file.msn.UnkEntity164BytesDTO;
import org.hercworks.transfer.dto.file.msn.UnkEntity22ByteDTO;
import org.hercworks.transfer.dto.file.msn.UnkEntity58ByteDTO;
import org.hercworks.transfer.dto.file.msn.UnkHeaderEntryDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;

public class MissionFileDTOServiceImpl implements GeneralDTOService{
	
	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		
		MissionFile msn = (MissionFile)source;
		
		MissionFileDTO dto = new MissionFileDTO();
		
		dto.setUnknownFileId(msn.getUnknownFileId());
		
		UnkHeaderEntryDTO[] hdrDTO = new UnkHeaderEntryDTO[msn.getHeaderEntries().length];
		for(int h=0; h < msn.getHeaderEntries().length; h++) {
			UnkHeaderEntry e = msn.getHeaderEntries()[h];
			UnkHeaderEntryDTO hdr = new UnkHeaderEntryDTO();
			hdr.setIndexId(e.getIndexId());
			hdr.setUnkFlag1(e.getStartFrameIndexNum());
			hdr.setUnkFlag2(e.getUnkFlag2());
			hdr.setUnkFlag3(e.getUnkFlag3());
			hdr.setUnkValue1(e.getUnkValue1());
			hdr.setUnkValue2(e.getUnkValue2());
			hdr.setUnkValue3(e.getUnkValue3());
			hdrDTO[h] = hdr;
		}
		dto.setHeaderEntries(hdrDTO);
		
		dto.setUnkVal_is2(msn.getUnkVal_is2());
		dto.setUnkFlag_isFFFF(msn.getUnkFlag_isFFFF());
		dto.setWorldId(msn.getWorldId());
		dto.setZoneNumber(msn.getZoneNumber());
		
		HashMap<Short, MapObjectDTO> markedObjects = new HashMap<Short, MapObjectDTO>();
		
		MapCoordDTO[] coords = new MapCoordDTO[msn.getMapCoordHeader().length];
		for(int c=0; c < msn.getMapCoordHeader().length; c++) {
			MapCoordDTO coordDTO = new MapCoordDTO();
			MapCoord coord = msn.getMapCoordHeader()[c];
			
			coordDTO.setId(coord.getId());
			coordDTO.setUnkFlag1(coord.getUnkFlag1());
			coordDTO.setUnkFlag2(coord.getUnkFlag2());
			coordDTO.setUnkFlag3(coord.getUnkFlag3());
			coordDTO.setUnkFlag4(coord.getUnkFlag4());
			coordDTO.setX(MapCoord.fixedInt(coord.getX()));
			coordDTO.setY(MapCoord.fixedInt(coord.getY()));
			coordDTO.setZ(MapCoord.fixedInt(coord.getZ()));
			
			coords[c] = coordDTO;
		}
		dto.setMapCoordHeader(coords);
		
		UnitInfoDTO[] mapUnits = new UnitInfoDTO[msn.getMapUnits().length];
		for(int u=0; u < mapUnits.length; u++) {
			UnitInfo unit = msn.getMapUnits()[u];
			UnitInfoDTO unitDto = new UnitInfoDTO();
			unitDto.setGUID(unit.getGUID());
			
			if(unit.getMapCoordId() != -1) {
				unitDto.setMapCoord(MapCoordRefDTO.init(dto.getMapCoordHeader()[unit.getMapCoordId()]));
			}
			
			unitDto.setHeaderFlags(unit.getHeaderFlags());
			unitDto.setUnitId(unit.getUnitId() == null ? "null" : unit.getUnitId().name());
			
			for(int s=0; s < unit.getWeapons().length; s++) {
				unitDto.getWeapons()[s] = unit.getWeapons()[s] == -1 ? WeaponLUT.NONE.getName() : WeaponLUT.getById((int)unit.getWeapons()[s]).name();
			}
			
			
			unitDto.setUnkFlags(unit.getUnkFlags());
			unitDto.setHealthModAdjust(unit.getHealthModAdjust());
			
			if(unitDto.getGUID() != -1) {
				markedObjects.put(unitDto.getGUID(), unitDto);
			}
			
			mapUnits[u] = unitDto;
		}
//		dto.setMapUnits(mapUnits);
		
		
		UnkEntity102BytesDTO[] unk102s = new UnkEntity102BytesDTO[msn.getUnk102ByteEnts().length];
		for(int u=0; u < unk102s.length; u++) {
			
			UnkEntity102Bytes ent102 = msn.getUnk102ByteEnts()[u];
			UnkEntity102BytesDTO ent102dto = new UnkEntity102BytesDTO();
			
			ent102dto.setGUID(ent102.getGUID());
			ent102dto.setFlags(Arrays.copyOf(ent102.getFlags(), ent102.getFlags().length));
			ent102dto.setUnkVal_100(ent102.getUnkVal_100());
			
			
			if(ent102dto.getGUID() != -1) {
				markedObjects.put(ent102dto.getGUID(), ent102dto);
			}
			
			List<MapObjectDTO> links = new ArrayList<MapObjectDTO>();
			for(short id : ent102dto.getFlags()) {
				links.add(markedObjects.get(id));
			}
			ent102dto.setLinkedObjects(links);
			
			unk102s[u] = ent102dto;
			
		}
//		dto.setUnk102ByteEnts(unk102s);
		
		MiscEntityInfoDTO[] miscEnts = new MiscEntityInfoDTO[msn.getMapMiscEntities().length];
		for(int m=0; m < miscEnts.length; m++) {
			
			MiscEntityInfo ent = msn.getMapMiscEntities()[m];
			MiscEntityInfoDTO miscdto = new MiscEntityInfoDTO();
			
			miscdto.setGUID(ent.getGUID());
			miscdto.setHeaderFlags(Arrays.copyOf(ent.getHeaderFlags(), ent.getHeaderFlags().length));
			
			if(ent.getMiscEntityId() == null) {
				miscdto.setId(null);
			}
			else {
				miscdto.setId(ent.getMiscEntityId());
			}
			
			miscdto.setSpawnflags(Arrays.copyOf(ent.getSpawnflags(), ent.getSpawnflags().length));
			miscdto.setHealthModAdjust(ent.getHealthModAdjust());
			
			miscEnts[m] = miscdto;
			
			if(miscdto.getGUID() != -1) {
				markedObjects.put(miscdto.getGUID(), miscdto);
			}
		}
//		dto.setMapMiscEntities(miscEnts);
		
		UnkEntity22ByteDTO[] unk22s = new UnkEntity22ByteDTO[msn.getUnk22ByteEnts().length];
		for(int u=0; u < unk22s.length; u++) {
			
			UnkEntity22Byte ent = msn.getUnk22ByteEnts()[u];
			UnkEntity22ByteDTO unk22 = new UnkEntity22ByteDTO();
			
			unk22.setGUID(ent.getGUID());
			unk22.setFlags(Arrays.copyOf(ent.getFlags(), ent.getFlags().length));
			
			
			if(unk22.getGUID() != -1) {
				markedObjects.put(unk22.getGUID(), unk22);
			}
			List<MapObjectDTO> links = new ArrayList<MapObjectDTO>();
			for(short id : unk22.getFlags()) {
				links.add(markedObjects.get(id));
			}
			unk22.setLinkedObjects(links);
			
			unk22s[u] = unk22;
			
		}
//		dto.setUnk22ByteEnts(unk22s);
		
		UnkEntity164BytesDTO[] unk164s = new UnkEntity164BytesDTO[msn.getUnk164ByteEnts().length];
		for(int u=0; u < unk164s.length; u++) {
			
			UnkEntity164Bytes ent = msn.getUnk164ByteEnts()[u];
			UnkEntity164BytesDTO unk164 = new UnkEntity164BytesDTO();
			
			unk164.setGUID(ent.getGUID());
			unk164.setStartFrameIndexNum(ent.getStartFrameIndexNum());
			unk164.setUnkFlag2(ent.getUnkFlag2());
			unk164.setUnkFlag3(ent.getUnkFlag3());
			unk164.setValues(Arrays.copyOf(ent.getValues(), ent.getValues().length));
			
			
			markedObjects.put(unk164.getGUID(), unk164);
			
			if(unk164.getGUID() != -1) {
				markedObjects.put(unk164.getGUID(), unk164);
			}

			List<MapObjectDTO> links = new ArrayList<MapObjectDTO>();
			for(short id : unk164.getValues()) {
				links.add(markedObjects.get(id));
			}
			unk164.setLinkedObjects(links);
			
			unk164s[u] = unk164;
			
			
		}
//		dto.setUnk164ByteEnts(unk164s);
		
		UnkEntity58ByteDTO[] unk58s = new UnkEntity58ByteDTO[msn.getUnk58ByteEnts().length];
		for(int u=0; u < unk58s.length; u++) {
			
			UnkEntity58Byte ent = msn.getUnk58ByteEnts()[u];
			UnkEntity58ByteDTO unk58 = new UnkEntity58ByteDTO();
			
			unk58.setGUID(ent.getGUID());
			unk58.setFlags(Arrays.copyOf(ent.getFlags(), ent.getFlags().length));
			
			if(unk58.getGUID() != -1) {
				markedObjects.put(unk58.getGUID(), unk58);
			}
			
			List<MapObjectDTO> links = new ArrayList<MapObjectDTO>();
			for(short id : unk58.getFlags()) {
				links.add(markedObjects.get(id));
			}
			unk58.setLinkedObjects(links);
			
			unk58s[u] = unk58;
		}
//		dto.setUnk58ByteEnts(unk58s);
		
		dto.setMarkedObjects(markedObjects);
		

		
		return dto;
	}
	
	
	@Override
	public DataFile fromDTO(TransferObject source) {
		// TODO Auto-generated method stub
		return null;
	}

}
