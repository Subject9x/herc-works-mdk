package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/DBSIM/DAT/ROCKETS.DAT
 * 	0- UINT16 - total count
 * 	
 * 	SEQ0 - 14-byte segments
 * 		S0_0- UINT16 - model ID - 0x01 is the ES1 ATC model!?
 * 		S1_2- UINT16 - projectile timer - in milliseconds
 * 		S2-4- UINT16 - projectile speed.
 *  	S3_6- UINT16 - ? unknown
 *  	S4_8- UINT16 - unknown flag value - EMP and rockets have 256
 *  	S5_10- UINT16 - ?
 *  	S6_12- UINT16 - SFX id when fired.
 */
public class Rockets extends DataFile{
	
	private short total;
	private Entry[] data;
	
	public Rockets() {}
	
	public Rockets(short total) {
		this.total = total;
		this.data = new Entry[total];
	}
	
	public class Entry{
		
		private short modelId;
		private short lifetime;
		private short speed;
		private short unk1_uint16;
		private short unk2_flag;
		private short unk3_uint16;
		private short sfxId_Fire;
		
		public Entry() {}

		public short getModelId() {
			return modelId;
		}

		public short getLifetime() {
			return lifetime;
		}

		public short getSpeed() {
			return speed;
		}

		public short getUnk1_uint16() {
			return unk1_uint16;
		}

		public short getUnk2_flag() {
			return unk2_flag;
		}

		public short getUnk3_uint16() {
			return unk3_uint16;
		}

		public short getSfxId_Fire() {
			return sfxId_Fire;
		}

		public void setModelId(short modelId) {
			this.modelId = modelId;
		}

		public void setLifetime(short lifetime) {
			this.lifetime = lifetime;
		}

		public void setSpeed(short speed) {
			this.speed = speed;
		}

		public void setUnk1_uint16(short unk1_uint16) {
			this.unk1_uint16 = unk1_uint16;
		}

		public void setUnk2_flag(short unk2_flag) {
			this.unk2_flag = unk2_flag;
		}

		public void setUnk3_uint16(short unk3_uint16) {
			this.unk3_uint16 = unk3_uint16;
		}

		public void setSfxId_Fire(short sfxId_Fire) {
			this.sfxId_Fire = sfxId_Fire;
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
