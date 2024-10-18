package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/DBSIM/DAT/BEAM.DAT
 * 	0- UINT16 - Total Beam count
 * 
 * 	SEQ0 - Beam struct
 * 		SEQ0_0 - UINT16 - Beam Width
 * 		SEQ0_2 - UINT16 - possible color id
 * 		SEQ0_4 - UINT16 - possible Render Flags, but all are 0, and should be kept 0
 * 
 * BEAM ORDER
 * 	PBW-I
 *  ELF-I
 *  ?
 *  LASER
 *  ?
 *  ?
 *  PBW-II
 *  ELF-II
 * 
 * 
 * 	2- UINT16 - PBW I - beam width
 * 	4- UINT16 - ?
 *  
 *  6-7 spacer
 * 	
 *  8- UINT16 - ELF I - beam width
 * 	10- UINT16 - ELF I - beam color
 *  
 *  12-13  spacer
 * 	
 *  14- UINT16 - ?
 * 	16- UINT16 - ?
 *  
 *  18-19  spacer
 * 	
 *  20- UINT16 - LASER - beam width
 *  22- UINT16 - ?
 *  
 *  24-25  spacer
 *  
 *  26- UINT16 - ?
 *  28- UINT16 - ?
 *  
 *  30-31  spacer
 *  
 *  32- UINT16 - ?
 *  34- UINT16 - ?
 *  
 *  36-37  spacer
 *  
 *  38- UINT16 - PBW II - beam width
 *  40- UINT16 - ?
 *  
 *  42-43  spacer 
 *  
 *  44- UINT16 - ELF II - beam width
 *  46- UINT16 - ELF II - beam color
 *  
 *  48-49  spacer
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
