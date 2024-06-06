package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * 
 */
@JsonRootName("")
public class ProjMissileDatDTO {

	@JsonProperty(value = "id", index = 0)
	private int id;
	
	@JsonProperty(value = "model_id", index = 1)
	private int modelId;
	
	@JsonProperty(value = "lifetime", index = 2)
	private int lifetime;
	
	@JsonProperty(value = "clip_radius", index = 3)
	private int clipRadius;
	
	@JsonProperty(value = "unk2_flag", index = 4)
	private int unk2_flag;
	
	@JsonProperty(value = "sfx_fire_bullet", index = 5)
	private int sfxIdFireBullet;
	
	@JsonProperty(value = "unk3", index = 6)
	private int unk3_uint16;
	
	@JsonProperty(value = "sfx_fire_missile", index = 7)
	private int sfxIdFireMissile;
	
	public ProjMissileDatDTO() {}

	public int getId() {
		return id;
	}

	public int getModelId() {
		return modelId;
	}

	public int getLifetime() {
		return lifetime;
	}

	public int getClipRadius() {
		return clipRadius;
	}

	public int getUnk2_flag() {
		return unk2_flag;
	}

	public int getSfxIdFireBullet() {
		return sfxIdFireBullet;
	}

	public int getUnk3_uint16() {
		return unk3_uint16;
	}

	public int getSfxIdFireMissile() {
		return sfxIdFireMissile;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	public void setClipRadius(int clipRadius) {
		this.clipRadius = clipRadius;
	}

	public void setUnk2_flag(int unk2_flag) {
		this.unk2_flag = unk2_flag;
	}

	public void setSfxIdFireBullet(int sfxIdFireBullet) {
		this.sfxIdFireBullet = sfxIdFireBullet;
	}

	public void setUnk3_uint16(int unk3_uint16) {
		this.unk3_uint16 = unk3_uint16;
	}

	public void setSfxIdFireMissile(int sfxIdFireMissile) {
		this.sfxIdFireMissile = sfxIdFireMissile;
	}
}
