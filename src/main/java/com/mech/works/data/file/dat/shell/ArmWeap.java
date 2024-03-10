package com.mech.works.data.file.dat.shell;

import java.util.HashMap;
import java.util.List;

import com.mech.works.data.ref.files.DataFile;


/**
 * 	FILE
 * 		/SHELL/GAM/ARM_WEAP.DAT
 * 	Configures image render position offset in /UI/ARMING/Weapon_panel.
 * 		origin here is the top-left corner of the arming weapon panel image.
 * 
 * 	0- UINT16 - total weapons
 *  SEQ - 12 byte segments
 *  	UINT16 - weapon id
 *  	UINT32 - x offset to weapon panel top-left.
 *  	UINT32 - y offset to weapon panel top-left.
 *  	UINT16 - end byte value
 *  
 */
public class ArmWeap extends DataFile{

	private short totalWeapons;
	
	private HashMap<Integer, List<RowEntry>> entries;
	
	public ArmWeap() {}
	
	
	public class RowEntry{
		private short id;
		private int offsetX;
		private int offsetY;
		private short frameId;
		
		public RowEntry(short id, int ofsX, int ofsY, short dbaFrameId) {
			this.id = id;
			this.offsetX = ofsX;
			this.offsetY = ofsY;
			this.frameId = dbaFrameId;
		}

		public short getId() {
			return id;
		}

		public int getOffsetX() {
			return offsetX;
		}

		public int getOffsetY() {
			return offsetY;
		}

		public short getFrameId() {
			return frameId;
		}
	}
}
