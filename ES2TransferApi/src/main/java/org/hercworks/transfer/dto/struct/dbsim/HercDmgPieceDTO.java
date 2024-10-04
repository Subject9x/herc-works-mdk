package org.hercworks.transfer.dto.struct.dbsim;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class HercDmgPieceDTO {

	@JsonProperty(value = "armor", index = 0)
	private int armor;

	@JsonProperty(value = "debris_flag", index = 1)
	private int debrisFlags;

	@JsonProperty(value = "bone_id", index = 2)
	private int boneId;

	@JsonProperty(value = "unk1_val", index = 3)
	private int unk1_val;
	
	@JsonProperty(value = "linked_internals", index = 4)
	private Map<String, Float> internals = new HashMap<String, Float>();
	
	public HercDmgPieceDTO() {}

	public int getArmor() {
		return armor;
	}

	public int getDebrisFlags() {
		return debrisFlags;
	}

	public int getBoneId() {
		return boneId;
	}

	public int getUnk1_val() {
		return unk1_val;
	}

	public Map<String, Float> getInternals() {
		return internals;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public void setDebrisFlags(int debrisFlags) {
		this.debrisFlags = debrisFlags;
	}

	public void setBoneId(int boneId) {
		this.boneId = boneId;
	}

	public void setUnk1_val(int unk1_val) {
		this.unk1_val = unk1_val;
	}

	public void setInternals(Map<String, Float> internals) {
		this.internals = internals;
	}
}
