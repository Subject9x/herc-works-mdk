package com.mech.works.data.file.dat.sim;

import com.mech.works.data.ref.files.DataFile;

/**
 * 	FILE
 * 		/DBSIM/DAT/BULLETS.DAT
 * 	0- UINT16 - total count
 * 	
 * 	SEQ0 - 14-byte segments
 * 		S0_0- UINT16 - model ID - 0x01 is the ES1 ATC model!?
 * 		S1_2- UINT16 - projectile timer - in milliseconds
 * 		S2-4- UINT16 - 
 *  	S3_6- UINT16 - unknown flag value - EMP proj ID's have 256, all else 0
 *  					tried 256 on non-EMP Id crashes sim.
 *  	S4_8- UINT16 - SFX id when fired.
 *  	S5_10- UINT16 - ?
 *  	S6_12- UINT616 - ?
 */
public class Bullets extends DataFile {

	private short total;
	private Entry[] data;
	
	public Bullets() {}
	
	public Bullets(short total) {
		this.total = total;
		this.data = new Entry[total];
	}
	
	
	public class Entry{
		
		private short modelId;
		private short lifetime;
		private short unk1_uint16;
		private short unk2_flag;
		private short sfxId_Fire;
		private short unk3_uint16;
		private short unk4_uint16;
		
		public Entry() {}

		public short getModelId() {
			return modelId;
		}

		public short getLifetime() {
			return lifetime;
		}

		public short getUnk1_uint16() {
			return unk1_uint16;
		}

		public short getUnk2_flag() {
			return unk2_flag;
		}

		public short getSfxId_Fire() {
			return sfxId_Fire;
		}

		public short getUnk3_uint16() {
			return unk3_uint16;
		}

		public short getUnk4_uint16() {
			return unk4_uint16;
		}

		public void setModelId(short modelId) {
			this.modelId = modelId;
		}

		public void setLifetime(short lifetime) {
			this.lifetime = lifetime;
		}

		public void setUnk1_uint16(short unk1_uint16) {
			this.unk1_uint16 = unk1_uint16;
		}

		public void setUnk2_flag(short unk2_flag) {
			this.unk2_flag = unk2_flag;
		}

		public void setSfxId_Fire(short sfxId_Fire) {
			this.sfxId_Fire = sfxId_Fire;
		}

		public void setUnk3_uint16(short unk3_uint16) {
			this.unk3_uint16 = unk3_uint16;
		}

		public void setUnk4_uint16(short unk4_uint16) {
			this.unk4_uint16 = unk4_uint16;
		}
	}
	
	public short getTotal() {
		return total;
	}

	public Entry[] getData() {
		return data;
	}

	public void setTotal(short total) {
		this.total = total;
	}

	public void setData(Entry[] data) {
		this.data = data;
	}
}
