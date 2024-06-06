package org.hercworks.core.data.struct.dbsim;


/**
 * This struct seems shared by both
 * 		BULLETS.DAT and ROCKETS.DAT
 *  
 * for some reason BULLETS.DAT uses sfxFireIdBullets
 *  and ROCKETS.DAT uses sfxFireIdMissiles.
 *  
*/
public class ProjMissileDatEntry {
	
	private short modelId;
	private short lifetime;
	private short clipRadius;
	private short unk2_flag;
	private short sfxFireIdBullets;
	private short unk3_uint16;
	private short sfxFireIdMissiles;
	
	public ProjMissileDatEntry() {}

	public short getModelId() {
		return modelId;
	}

	public short getLifetime() {
		return lifetime;
	}

	public short getClipRadius() {
		return clipRadius;
	}

	public short getUnk2_flag() {
		return unk2_flag;
	}

	public short getSfxFireIdBullets() {
		return sfxFireIdBullets;
	}

	public short getUnk3_uint16() {
		return unk3_uint16;
	}

	public short getSfxFireIdMissiles() {
		return sfxFireIdMissiles;
	}

	public void setModelId(short modelId) {
		this.modelId = modelId;
	}

	public void setLifetime(short lifetime) {
		this.lifetime = lifetime;
	}

	public void setClipRadius(short clipRadius) {
		this.clipRadius = clipRadius;
	}

	public void setUnk2_flag(short unk2_flag) {
		this.unk2_flag = unk2_flag;
	}

	public void setSfxFireIdBullets(short sfxFireIdBullets) {
		this.sfxFireIdBullets = sfxFireIdBullets;
	}

	public void setUnk3_uint16(short unk3_uint16) {
		this.unk3_uint16 = unk3_uint16;
	}

	public void setSfxFireIdMissiles(short sfxFireIdMissiles) {
		this.sfxFireIdMissiles = sfxFireIdMissiles;
	}
}
