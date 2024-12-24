package org.hercworks.core.data.file.dbsim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SIMVOL0/VUE/(herc).VUE
 * 
 * 	This mostly just defines the 3D viewport sizes and offsets for each player herc.
 *  
 * 
 * 0- UINT32 - Total Viewport entries
 * 4 - SEQ_0 - INT32
 * 	SEQ_0_0 - Origin x
 * 	SEQ_0_4 - Origin y
 *  SEQ_0_8 - Width
 *  SEQ_0_12 - Height
 *  SEQ_0_16 - Unk ofs x
 *  SEQ_0_20 - Unk ofs y
 *  SEQ_0_24 - unk ofs w
 *  SEQ_0_28 - unk ofs h
 * 
 * todo - finish
 */
public class Vue extends DataFile {

	private int totalViewports;
	
	private Entry[] entries;
	
	public Vue() {}
	
	public class Entry{
		private int originX;
		private int originY;
		private int widthMax;
		private int heightMax;
		
		private int unkOfsX;
		private int unkOfsY;
		private int unkOfsW;
		private int unkOfsH;
		
		public Entry() {}

		public int getOriginX() {
			return originX;
		}

		public int getOriginY() {
			return originY;
		}

		public int getWidthMax() {
			return widthMax;
		}

		public int getHeightMax() {
			return heightMax;
		}

		public int getUnkOfsX() {
			return unkOfsX;
		}

		public int getUnkOfsY() {
			return unkOfsY;
		}

		public int getUnkOfsW() {
			return unkOfsW;
		}

		public int getUnkOfsH() {
			return unkOfsH;
		}

		public void setOriginX(int originX) {
			this.originX = originX;
		}

		public void setOriginY(int originY) {
			this.originY = originY;
		}

		public void setWidthMax(int widthMax) {
			this.widthMax = widthMax;
		}

		public void setHeightMax(int heightMax) {
			this.heightMax = heightMax;
		}

		public void setUnkOfsX(int unkOfsX) {
			this.unkOfsX = unkOfsX;
		}

		public void setUnkOfsY(int unkOfsY) {
			this.unkOfsY = unkOfsY;
		}

		public void setUnkOfsW(int unkOfsW) {
			this.unkOfsW = unkOfsW;
		}

		public void setUnkOfsH(int unkOfsH) {
			this.unkOfsH = unkOfsH;
		}
	}
	
	public Entry newEntry() {
		return new Entry();
	}

	public int getTotalViewports() {
		return totalViewports;
	}

	public Entry[] getEntries() {
		return entries;
	}

	public void setTotalViewports(int totalViewports) {
		this.totalViewports = totalViewports;
	}

	public void setEntries(Entry[] entries) {
		this.entries = entries;
	}
}
