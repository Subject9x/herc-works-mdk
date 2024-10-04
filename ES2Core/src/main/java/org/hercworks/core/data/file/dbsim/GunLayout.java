package org.hercworks.core.data.file.dbsim;

import org.hercworks.voln.DataFile;

/**
 * initially mapped by: crow!
 * 
 * 	FILE
 * 		/SIMVOL0/GL/{herc}.GL
 * 	'Gun Layout' 
 * 
 * 0- UINT16 - total weapon count
 * 	Weapon Entries - 26 bytes per-weapon
 * 		0x00 - BoneID (UInt16)
 * 		0x02 - Unknown: 0xFF
 * 		0x04 - Unknown: 0xFF
 * 		0x06 - Weapon Orientation (UInt8): 0 = on top, 1 = underneath, 2 = left side, 3 = right side, 4 = invisible
 * 		0x07 - Weapon position within firing chain (UInt8): 0 is shown as Weapon 1 in HUD
 * 		0x08 - Unknown: -5000 or 0 (Int16)
 * 		0x0A - Unknown: 5000 or 0 (Int16)
 * 		0x0C - Unknown: -8000 (Int16)
 * 		0x0E - Unknown: 16000 (Int16)
 * 		0x10 - Mount Point Offset X (Int16)
 * 		0x12 - Mount Point Offset Y (Int16)
 * 		0x14 - Mount Point Offset Z (Int16)
 * 		0x16 - Unknown (Int8): -1, 0, or 1
 * 		0x17 - Weapon ID (Int8): Same order as in VSHELL, but starting at 0, not 2
 * 		0x18 - Unknown (Int16 or UInt16): Usually divisible by 1000
 * 
 */
public class GunLayout extends DataFile {

	private short totalGuns;
	
	private HardpointEntry[] hardpoints;

	public GunLayout() {}
	
	public GunLayout(short total) {
		this.totalGuns = total;
		this.hardpoints = new HardpointEntry[total];
	}
	
	public HardpointEntry newEntry() {
		return new HardpointEntry();
	}
	
	public class HardpointEntry{
		
		private short boneId;
		private short unk1_val;
		private short unk2_val;
		private byte angleDirOption;
		private byte fireChainNumber;
		private short unk3_0or_Neg5000;
		private short unk4_0or_5000;
		private short unk5_Neg8000;
		private short unk6_16000;
		private short[] offset = new short[3];
		private byte unk7_val;
		private byte hardpointId;
		private short unk8_val;
		
		public HardpointEntry() {}

		public short getBoneId() {
			return boneId;
		}

		public short getUnk1_val() {
			return unk1_val;
		}

		public short getUnk2_val() {
			return unk2_val;
		}

		public byte getAngleDirOption() {
			return angleDirOption;
		}

		public byte getFireChainNumber() {
			return fireChainNumber;
		}

		public short getUnk3_0or_Neg5000() {
			return unk3_0or_Neg5000;
		}

		public short getUnk4_0or_5000() {
			return unk4_0or_5000;
		}

		public short getUnk5_Neg8000() {
			return unk5_Neg8000;
		}

		public short getUnk6_16000() {
			return unk6_16000;
		}

		public byte getUnk7_val() {
			return unk7_val;
		}

		public byte getHardpointId() {
			return hardpointId;
		}

		public short getUnk8_val() {
			return unk8_val;
		}

		public void setBoneId(short boneId) {
			this.boneId = boneId;
		}

		public void setUnk1_val(short unk1_val) {
			this.unk1_val = unk1_val;
		}

		public void setUnk2_val(short unk2_val) {
			this.unk2_val = unk2_val;
		}

		public void setAngleDirOption(byte angleDirOption) {
			this.angleDirOption = angleDirOption;
		}

		public void setFireChainNumber(byte fireChainNumber) {
			this.fireChainNumber = fireChainNumber;
		}

		public void setUnk3_0or_Neg5000(short unk3_0or_Neg5000) {
			this.unk3_0or_Neg5000 = unk3_0or_Neg5000;
		}

		public void setUnk4_0or_5000(short unk4_0or_5000) {
			this.unk4_0or_5000 = unk4_0or_5000;
		}

		public void setUnk5_Neg8000(short unk5_Neg8000) {
			this.unk5_Neg8000 = unk5_Neg8000;
		}

		public void setUnk6_16000(short unk6_16000) {
			this.unk6_16000 = unk6_16000;
		}

		public void setUnk7_val(byte unk7_val) {
			this.unk7_val = unk7_val;
		}

		public void setHardpointId(byte hardpointId) {
			this.hardpointId = hardpointId;
		}

		public void setUnk8_val(short unk8_val) {
			this.unk8_val = unk8_val;
		}

		public short[] getOffset() {
			return offset;
		}

		public void setOffset(short[] offset) {
			this.offset = offset;
		}
	}


	public short getTotalGuns() {
		return totalGuns;
	}

	public HardpointEntry[] getHardpoints() {
		return hardpoints;
	}

	public void setTotalGuns(short totalGuns) {
		this.totalGuns = totalGuns;
	}

	public void setHardpoints(HardpointEntry[] hardpoints) {
		this.hardpoints = hardpoints;
	}
	
	
}
