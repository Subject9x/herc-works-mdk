package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class GunLayoutEntryDTO {

	@JsonProperty(value = "bone_id", index = 1)
	private int boneId;

	@JsonProperty(value = "unk1_val", index = 2)
	private int unk1_val;

	@JsonProperty(value = "unk2_val", index = 3)
	private int unk2_val;

	@JsonProperty(value = "option_angle_dir", index = 4)
	private int angleDirOption;

	@JsonProperty(value = "fire_chain_val", index = 5)
	private int fireChainNumber;

	@JsonProperty(value = "unk3_0or_Neg5000", index = 6)
	private int unk3_0or_Neg5000;

	@JsonProperty(value = "unk4_0or_5000", index = 7)
	private int unk4_0or_5000;

	@JsonProperty(value = "unk5_Neg8000", index = 8)
	private int unk5_Neg8000;

	@JsonProperty(value = "unk6_16000", index = 9)
	private int unk6_16000;

	@JsonProperty(value = "offset_vector", index = 10, defaultValue = "[0,0,0]")
	private String[] offset = new String[3];

	@JsonProperty(value = "unk7_val", index = 11)
	private int unk7_val;

	@JsonProperty(value = "hardpoint_id", index = 12)
	private int hardpointId;

	@JsonProperty(value = "unk8_val", index = 13)
	private int unk8_val;
	
	public GunLayoutEntryDTO() {}
	
	public int getBoneId() {
		return boneId;
	}

	public int getUnk1_val() {
		return unk1_val;
	}

	public int getUnk2_val() {
		return unk2_val;
	}

	public int getAngleDirOption() {
		return angleDirOption;
	}

	public int getFireChainNumber() {
		return fireChainNumber;
	}

	public int getUnk3_0or_Neg5000() {
		return unk3_0or_Neg5000;
	}

	public int getUnk4_0or_5000() {
		return unk4_0or_5000;
	}

	public int getUnk5_Neg8000() {
		return unk5_Neg8000;
	}

	public int getUnk6_16000() {
		return unk6_16000;
	}

	public String[] getOffset() {
		return offset;
	}

	public int getUnk7_val() {
		return unk7_val;
	}

	public int getHardpointId() {
		return hardpointId;
	}

	public int getUnk8_val() {
		return unk8_val;
	}

	public void setBoneId(int boneId) {
		this.boneId = boneId;
	}

	public void setUnk1_val(int unk1_val) {
		this.unk1_val = unk1_val;
	}

	public void setUnk2_val(int unk2_val) {
		this.unk2_val = unk2_val;
	}

	public void setAngleDirOption(int angleDirOption) {
		this.angleDirOption = angleDirOption;
	}

	public void setFireChainNumber(int fireChainNumber) {
		this.fireChainNumber = fireChainNumber;
	}

	public void setUnk3_0or_Neg5000(int unk3_0or_Neg5000) {
		this.unk3_0or_Neg5000 = unk3_0or_Neg5000;
	}

	public void setUnk4_0or_5000(int unk4_0or_5000) {
		this.unk4_0or_5000 = unk4_0or_5000;
	}

	public void setUnk5_Neg8000(int unk5_Neg8000) {
		this.unk5_Neg8000 = unk5_Neg8000;
	}

	public void setUnk6_16000(int unk6_16000) {
		this.unk6_16000 = unk6_16000;
	}

	public void setOffset(String[] offset) {
		this.offset = offset;
	}

	public void setUnk7_val(int unk7_val) {
		this.unk7_val = unk7_val;
	}

	public void setHardpointId(int hardpointId) {
		this.hardpointId = hardpointId;
	}

	public void setUnk8_val(int unk8_val) {
		this.unk8_val = unk8_val;
	}
	
}
