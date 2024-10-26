package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/DBSIM/DAT/BEAM.DAT
 * 	0- UINT16 - Total Beam count
 * 
 * 	SEQ0 - Beam struct
 * 		SEQ0_0 - UINT16 - Beam Width
 * 		SEQ0_2 - UINT16 - color index id, but only active on ELF weapons for some reason.
 * 		SEQ0_4 - UINT16 - BEAMTEX.DBA frame number!
 * 
 * STOCK BEAM ORDER - this maps to {@linkplain ProjectileData} "missile_id" when "type" == "BEAM"
 * 0 PBW I
 * 1 ELF I
 * 2 ?
 * 3 LAS100
 * 4 LAS200, LAS400
 * 5 LAS300, LAS500
 * 6 PBW II
 * 7 ELF II
 * 8 ???
 * 9 ???
 */
public class BeamData extends DataFile {

	private short total;
	
	private Entry[] data;
	
	public BeamData() {}
	
	public BeamData(short total) {
		this.total = total;
		this.data = new Entry[total];
	}
	
	public Entry newEntry(short width, short colorId, short dbaFrameNum) {
		return new Entry(width, colorId, dbaFrameNum);
	}
	
	public class Entry{
		
		private short width;
		private short colorId;
		private short dbaFrameNum;
		
		public Entry(short width, short colorId, short dbaFrameNum) {
			this.width = width;
			this.colorId = colorId;
			this.dbaFrameNum = dbaFrameNum;
		}

		public short getWidth() {
			return width;
		}

		public short getColorId() {
			return colorId;
		}
		
		public void setWidth(short width) {
			this.width = width;
		}

		public void setColorId(short colorId) {
			this.colorId = colorId;
		}

		public short getDBAFrameNum() {
			return dbaFrameNum;
		}

		public void setDBAFrameNum(short dbaFrameNum) {
			this.dbaFrameNum = dbaFrameNum;
		}
	}

	public short getTotal() {
		return total;
	}

	public void setTotal(short total) {
		this.total = total;
	}

	public Entry[] getData() {
		return data;
	}

	public void setData(Entry[] data) {
		this.data = data;
	}
}
