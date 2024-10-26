package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;

/***
 * 	FILE
 * 		/DBSIM/DAT/{herc}_DEB.DAT
 * 
 * 	0- UINT16 - 
 */
public class DebrisHerc extends DataFile {
	
	private Entry[] data;
	
	public DebrisHerc() {}
	
	public Entry newEntry() {
		return new Entry();
	}
	
	public class Entry{
		
		private short unk1_val; //possible spacer
		private short spawnDebris;
		private short meshGroupId;
		private short unk4_0A;
		private short unk5_03;
		private short[] throwDir = new short[3];
		private short mass;
		
		public Entry() {}

		public short getUnk1_val() {
			return unk1_val;
		}

		public short setSpawnDebrisFlag() {
			return spawnDebris;
		}

		public short getMeshGroupId() {
			return meshGroupId;
		}

		public short getUnk4_0A() {
			return unk4_0A;
		}

		public short getUnk5_03() {
			return unk5_03;
		}

		public short[] getThrowDir() {
			return throwDir;
		}
		
		public short getMass() {
			return mass;
		}

		public void setUnk1_val(short unk1_val) {
			this.unk1_val = unk1_val;
		}

		public void setSpawnDebrisFlag(short spawnDebris) {
			this.spawnDebris = spawnDebris;
		}

		public void setMeshGroupId(short meshGroupId) {
			this.meshGroupId = meshGroupId;
		}

		public void setUnk4_0A(short unk4_0a) {
			unk4_0A = unk4_0a;
		}

		public void setUnk5_03(short unk5_03) {
			this.unk5_03 = unk5_03;
		}
		

		public void setThrowDir(short[] throwDir) {
			this.throwDir = throwDir;
		}

		public void setMass(short mass) {
			this.mass = mass;
		}
	}

	public Entry[] getData() {
		return data;
	}

	public void setData(Entry[] data) {
		this.data = data;
	}
}
