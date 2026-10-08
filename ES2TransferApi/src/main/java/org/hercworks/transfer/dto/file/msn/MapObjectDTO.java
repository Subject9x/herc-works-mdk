package org.hercworks.transfer.dto.file.msn;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * possibly top-level abstract class for a certain set of observed map objects.
 * Some map objects have a GUID that seems to be counting up.
 */
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
@JsonSubTypes({ 
	  @Type(value = MiscEntityInfoDTO.class, name = "MiscEntityInfo"),
	  @Type(value = UnitInfoDTO.class, name = "UnitInfo"),
	  @Type(value = UnkEntity102BytesDTO.class, name = "UnkEntity102Bytes"),
	  @Type(value = UnkEntity164BytesDTO.class, name = "UnkEntity164Bytes"),
	  @Type(value = UnkEntity22ByteDTO.class, name = "UnkEntity22Byte"),
	  @Type(value = UnkEntity58ByteDTO.class, name = "UnkEntity58Byte"),
})
public abstract class MapObjectDTO {

	private short guid;

	private List<MapObjectDTO> linkedObjects;
	
	
	public short getGUID() {
		return guid;
	}

	public void setGUID(short guid) {
		this.guid = guid;
	}

	public List<MapObjectDTO> getLinkedObjects() {
		return linkedObjects;
	}

	public void setLinkedObjects(List<MapObjectDTO> linkedObjects) {
		this.linkedObjects = linkedObjects;
	}
}
