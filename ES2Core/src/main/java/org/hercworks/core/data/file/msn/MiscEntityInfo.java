package org.hercworks.core.data.file.msn;

import java.util.Arrays;

import org.hercworks.core.data.struct.herc.MiscEntityLUT;

/**
 * 62 byte segment
 * 
 * This seems to include Buildings and Vehicles only, so far.
 */
public class MiscEntityInfo extends MapObject{
	
	private short[] headerFlags = new short[3];
	
	private MiscEntityLUT miscEntityId;
	
	private short[] spawnflags = new short[25];
	
	private short healthModAdjust;
	
	public MiscEntityInfo() {}

	public short[] getHeaderFlags() {
		return headerFlags;
	}

	public MiscEntityLUT getMiscEntityId() {
		return miscEntityId;
	}

	public short[] getSpawnflags() {
		return spawnflags;
	}

	public short getHealthModAdjust() {
		return healthModAdjust;
	}

	public void setHeaderFlags(short[] headerFlags) {
		this.headerFlags = headerFlags;
	}

	public void setMiscEntityId(MiscEntityLUT miscEntityId) {
		this.miscEntityId = miscEntityId;
	}

	public void setSpawnflags(short[] spawnflags) {
		this.spawnflags = spawnflags;
	}

	public void setHealthModAdjust(short healthModAdjust) {
		this.healthModAdjust = healthModAdjust;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n{\n");
		sb.append("	indxId = ").append(getGUID()).append("\n");
		sb.append("	hdr flags =").append(Arrays.toString(getHeaderFlags())).append("\n");
		sb.append("	entity = ").append(getMiscEntityId() == null ? "null" : getMiscEntityId()).append("\n");
		sb.append("	flags = ").append(Arrays.toString(getSpawnflags())).append("\n");
		sb.append("	hp mod = ").append(getHealthModAdjust()).append("\n");
		sb.append("}\n");
		
		return sb.toString();
	}
}
