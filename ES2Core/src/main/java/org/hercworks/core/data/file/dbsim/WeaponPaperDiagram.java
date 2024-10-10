package org.hercworks.core.data.file.dbsim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SIMVOL0/PDG/WEAPONS.PDG
 * 
 * 	Cannot confirm if this is even used?
 * 
 * 	0 - UINT32 - total count
 * 	SEQ_0
 * 		0_0 - UINT32 - possible x coord
 * 		0_4 - UINT32 - possible y coord
 * 
 */
public class WeaponPaperDiagram extends DataFile {

	private Entry[] entries;
	
	public WeaponPaperDiagram() {}
	
	public class Entry{
		
		private int x;
		private int y;
		
		public Entry() {}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
	
	public Entry newEntry() {
		return new Entry();
	}

	public Entry[] getEntries() {
		return entries;
	}

	public void setEntries(Entry[] entries) {
		this.entries = entries;
	}
}
