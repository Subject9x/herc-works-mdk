package com.mech.works.data.file.dat;

import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * 	FILE
 * 	/SHELL/GAM/INI_[herc].DAT
 * 	
 * 	presume it deals with initializing herc stats for...runtime?
 * 	UINT16 - herc id
 *  UINT16 - '100' - purpose unknown atm.
 *  UINT16 - 2-byte spacer
 *	UINT16 - Hardpoint count
 *		<hardpoint schema>
 *		[01 00] [0A 00] [64 00] [05 00] - hard point data
 *		 id      unk1     unk2    unk3
 */
public class Ini_Herc_Dat extends DataFile{

	public static Bytes header = Bytes.from("661FAF55", StandardCharsets.UTF_8);
	private short hercId;
	private Bytes unknown2_100;
	
	private short hardpointCount;
	
	private LinkedHashSet<Hardpoint> hardpoints;
	
	public Ini_Herc_Dat() {}
	
	public Ini_Herc_Dat(String fileName, String dirPath) {
		super(fileName, dirPath);
	}
	
	public class Hardpoint{
		private short id;
		private short uint16_1;
		private short uint16_2;
		private short uint16_3;
		
		public Hardpoint() {}
		
		public Hardpoint(short id, short val1, short val2, short val3) {
			this.id = id;
			this.uint16_1 = val1;
			this.uint16_2 = val2;
			this.uint16_3 = val3;
		}

		public short getId() {
			return id;
		}

		public void setId(short id) {
			this.id = id;
		}

		public short getUint16_1() {
			return uint16_1;
		}

		public void setUint16_1(short uint16_1) {
			this.uint16_1 = uint16_1;
		}

		public short getUint16_2() {
			return uint16_2;
		}

		public void setUint16_2(short uint16_2) {
			this.uint16_2 = uint16_2;
		}

		public short getUint16_3() {
			return uint16_3;
		}

		public void setUint16_3(short uint16_3) {
			this.uint16_3 = uint16_3;
		}
		
		public byte[] byteArray() {
			
			return Bytes.from(getId(), getUint16_1(), getUint16_2(), getUint16_3()).byteOrder(ByteOrder.BIG_ENDIAN).array();
		}
	}
	
	public void initHardpoints() {
		setHardpoints(new LinkedHashSet<Hardpoint>());
	}
	
	public Hardpoint addHardpoint(short id) {
		Hardpoint hardpoint = new Hardpoint();
		
		hardpoint.setId(id);
		getHardpoints().add(hardpoint);
		
		return hardpoint;
	}
	
	public short getHercId() {
		return hercId;
	}

	public void setHercId(short hercId) {
		this.hercId = hercId;
	}

	public Bytes getUnknown2_100() {
		return unknown2_100;
	}

	public void setUnknown2_100(Bytes unknown2_100) {
		this.unknown2_100 = unknown2_100;
	}

	public short getHardpointCount() {
		return hardpointCount;
	}

	public void setHardpointCount(short hardpointCount) {
		this.hardpointCount = hardpointCount;
	}

	public LinkedHashSet<Hardpoint> getHardpoints() {
		return hardpoints;
	}

	public void setHardpoints(LinkedHashSet<Hardpoint> hardpoints) {
		this.hardpoints = hardpoints;
	}
}

