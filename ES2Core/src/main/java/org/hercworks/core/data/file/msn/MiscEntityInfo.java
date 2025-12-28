package org.hercworks.core.data.file.msn;

import java.util.Arrays;

import org.hercworks.core.data.struct.herc.MiscEntityLUT;

/**
 * 62 byte segment
 * 
 * This seems to include Buildings and Vehicles only, so far.
 */
public class MiscEntityInfo {

	private short indexId;
	
	private short[] headerFlags = new short[3];
	
	private MiscEntityLUT id;
	
	private short[] spawnflags = new short[25];
	
	private short healthModAdjust;
	
	public MiscEntityInfo() {}

	public short getIndexId() {
		return indexId;
	}

	public short[] getHeaderFlags() {
		return headerFlags;
	}

	public MiscEntityLUT getId() {
		return id;
	}

	public short[] getSpawnflags() {
		return spawnflags;
	}

	public short getHealthModAdjust() {
		return healthModAdjust;
	}

	public void setIndexId(short indexId) {
		this.indexId = indexId;
	}

	public void setHeaderFlags(short[] headerFlags) {
		this.headerFlags = headerFlags;
	}

	public void setId(MiscEntityLUT id) {
		this.id = id;
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
		sb.append("{\n");
		sb.append("		indxId = ").append(getIndexId()).append("\n");
		sb.append("		hdr flags =").append(Arrays.toString(getHeaderFlags())).append("\n");
		sb.append("		entity = ").append(getId() == null ? "null" : getId()).append("\n");
		sb.append("		flags = ").append(Arrays.toString(getSpawnflags())).append("\n");
		sb.append("		hp mod = ").append(getHealthModAdjust()).append("\n");
		sb.append("}\n");
		return sb.toString();
		
		
	}
}
