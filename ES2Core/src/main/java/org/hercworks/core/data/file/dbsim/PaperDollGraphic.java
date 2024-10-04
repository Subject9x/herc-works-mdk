package org.hercworks.core.data.file.dbsim;

import java.awt.Point;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/SIMVOL0/PDG/{herc}.PDG
 * 		These files define the HUD wireframe graphics for targets, player herc, and squadmates.
 * 		The herc/flyers get a .PDG file, which defines 3 views and each view uses a form of 'drawSubImage(x,y,x1,y1)'.
 * 			These use the .DBA files in /SIMVOL0/DBA/{herc}.DBA which house the 3 view forms of wireframes.
 * 
 * 0- UINT32 - 3 Image views - Structure / Internals / F5-HUD View - based on 3 DBA frames, and 1 frame is recolored.
 * SEQ0 - View Struct
 * 	SEQ0_1 - UINT32 - IMAGE\ORIGIN\X
 * 
 * 	SEQ0_0 - Regions
 * 		SEQ0_0- UINT32 - index
 * 		SEQ0_1- UINT32 - OFS\LEFT
 * 		SEQ0_2- UINT32 - OFS\TOP
 *  	SEQ0_3- UINT32 - OFS\RIGHT
 *  	SEQ0_4- UINT32 - OFS\BOTTOM
 *  	SEQ0_5- UINT32 - unknown
 *      SEQ0_6- UINT32 - unknown / spacer
 * 
 * 
 *  UINT32 - hardpoint count
 * 	Hardpoint/weapon entry
 * 	SEQ0_0 -
 * 		UINT32 - X offset from top-left of panel
 * 		UINT32 - Y offset from top-left of panel
 *      UINT16 - DBA frame Id _offset_ ?
 *      UINT16 - possible DBA RFlag value
 *      UINT32 - unknown
 *      UINT32 - unknown
 * 
 */
public class PaperDollGraphic extends DataFile {

	private int totalViews;
	private ViewEntry[] entries;
	private HardpointEntry[] hardpoints;
	
	public PaperDollGraphic() {}
	
	
	public int getTotalViews() {
		return totalViews;
	}

	public ViewEntry[] getEntries() {
		return entries;
	}

	public void setTotalViews(int totalViews) {
		this.totalViews = totalViews;
	}

	public void setEntries(ViewEntry[] entries) {
		this.entries = entries;
	}

	public HardpointEntry[] getHardpoints() {
		return hardpoints;
	}

	public void setHardpoints(HardpointEntry[] hardpoints) {
		this.hardpoints = hardpoints;
	}

	public ViewEntry newViewEntry() {
		return new ViewEntry();
	}
	
	public ViewRegion newViewRegion() {
		return new ViewRegion();
	}
	
	public HardpointEntry newHardpointEntry() {
		return new HardpointEntry();
	}
	
	public class ViewEntry {
		
		private Point origin;
		private Point size;
		private ViewRegion[] regions;
		
		public ViewEntry() {}

		public Point getOrigin() {
			return origin;
		}

		public Point getSize() {
			return size;
		}

		public ViewRegion[] getRegions() {
			return regions;
		}

		public void setOrigin(Point origin) {
			this.origin = origin;
		}

		public void setSize(Point size) {
			this.size = size;
		}

		public void setRegions(ViewRegion[] regions) {
			this.regions = regions;
		}
	}
	
	public class ViewRegion {
		private int index;
		private Point topLeft;
		private Point bottomRight;
		private int unk_val;
		private int spacer;
		
		public ViewRegion() {}

		public int getIndex() {
			return index;
		}

		public Point getTopLeft() {
			return topLeft;
		}

		public Point getBottomRight() {
			return bottomRight;
		}

		public int getUnk_val() {
			return unk_val;
		}

		public int getSpacer() {
			return spacer;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public void setTopLeft(Point topLeft) {
			this.topLeft = topLeft;
		}

		public void setBottomRight(Point bottomRight) {
			this.bottomRight = bottomRight;
		}

		public void setUnk_val(int unk_val) {
			this.unk_val = unk_val;
		}

		public void setSpacer(int spacer) {
			this.spacer = spacer;
		}
	}
	
	public class HardpointEntry{
		
		private Point origin;
		private int unk1;
		private int unk2;
		private int spacer;
		
		public HardpointEntry() {
			
		}

		public Point getOrigin() {
			return origin;
		}

		public int getUnk1() {
			return unk1;
		}

		public int getUnk2() {
			return unk2;
		}

		public int getSpacer() {
			return spacer;
		}

		public void setOrigin(Point origin) {
			this.origin = origin;
		}

		public void setUnk1(int unk1) {
			this.unk1 = unk1;
		}

		public void setUnk2(int unk2) {
			this.unk2 = unk2;
		}

		public void setSpacer(int spacer) {
			this.spacer = spacer;
		}
	}
}
