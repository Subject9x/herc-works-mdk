package org.hercworks.core.data.file.dat.shell;

import org.hercworks.voln.DataFile;
/**
 * 	FILE
 * 		/SHELL/GAM/RPR_HOTS.DAT
 * 
 * 	Configures the 'clickable area' for Herc sections in the REPAIR view.
 * 
 * 	0- UINT16 - total entries
 * 	SEQ_0 - array of segments
 * 		0_0 - UINT16 - Segment ID
 * 		0_2 - UINT16 - total chunks
 * 		SEQ_1 - array of UINT32
 * 			1_X - UINT32 values 
 * 
 * 
 */
public class HardpointOverlayConfig extends DataFile {

	private Herc[] entries;
	
	public HardpointOverlayConfig() {}
	
	public Herc newEntry() {
		return new Herc();
	}
	
	public class Herc{
		private short uid;
		private OverlayArea[] area;
		
		public Herc() {}
		
		public Herc(short uid, int coordSize){
			this.uid = uid;
			this.area = new OverlayArea[coordSize];
		}
		
		public OverlayArea newSegment() {
			return new OverlayArea();
		}

		public class OverlayArea{
			
			private int id;
			private int x;
			private int y;
			private int width;
			private int height;
			
			public OverlayArea() {}
			
			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getX() {
				return x;
			}

			public int getY() {
				return y;
			}

			public int getWidth() {
				return width;
			}

			public int getHeight() {
				return height;
			}

			public void setX(int x) {
				this.x = x;
			}

			public void setY(int y) {
				this.y = y;
			}

			public void setWidth(int width) {
				this.width = width;
			}

			public void setHeight(int height) {
				this.height = height;
			}
		}
		
		public short getHercId() {
			return uid;
		}

		public OverlayArea[] getAreas() {
			return area;
		}

		public void setHercId(short uid) {
			this.uid = uid;
		}

		public void setAreas(OverlayArea[] hercAreas) {
			this.area = hercAreas;
		}
	}

	public Herc[] getEntries() {
		return entries;
	}

	public void setEntries(Herc[] entries) {
		this.entries = entries;
	}
}
