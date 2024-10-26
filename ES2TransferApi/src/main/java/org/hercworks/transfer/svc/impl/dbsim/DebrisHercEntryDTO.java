package org.hercworks.transfer.svc.impl.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class DebrisHercEntryDTO {
	
	@JsonProperty
	private int unk1_val; //possible spacer
	
	@JsonProperty
	private boolean spawnDebris;
	
	@JsonProperty
	private int meshGroupId;
	
	@JsonProperty
	private int unk4_0A;
	
	@JsonProperty
	private int unk5_03;
	
	@JsonProperty
	private int[] throwDir = new int[3];

	@JsonProperty
	private int mass;
	
	public DebrisHercEntryDTO() {}

	public int getUnk1_val() {
		return unk1_val;
	}

	public boolean getSpawnFlag() {
		return spawnDebris;
	}

	public int getMeshGroupId() {
		return meshGroupId;
	}

	public int getUnk4_0A() {
		return unk4_0A;
	}

	public int getUnk5_03() {
		return unk5_03;
	}

	public int[] getThrowDir() {
		return throwDir;
	}

	public int getMass() {
		return mass;
	}

	public void setUnk1_val(int unk1_val) {
		this.unk1_val = unk1_val;
	}

	public void setSpawnFlag(boolean spawnDebris) {
		this.spawnDebris = spawnDebris;
	}

	public void setMeshGroupd(int meshGroupId) {
		this.meshGroupId = meshGroupId;
	}

	public void setUnk4_0A(int unk4_0a) {
		unk4_0A = unk4_0a;
	}

	public void setUnk5_03(int unk5_03) {
		this.unk5_03 = unk5_03;
	}
	
	public void setThrowDir(int[] throwDir) {
		this.throwDir = throwDir;
	}
	
	public void setMass(int mass) {
		this.mass = mass;
	}
}
