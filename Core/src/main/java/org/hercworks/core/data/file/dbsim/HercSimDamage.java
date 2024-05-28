package org.hercworks.core.data.file.dbsim;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.hercworks.core.data.struct.herc.HercInternals;
import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		dmg\\[herc].DMG
 * 	Contains armor, critical component HP, and probably more damage-related data for every unit.
 * 	Tied to each unit most likely by the unit's NAME field in the corresponding .DAT file.
 *	NOTE - the following are an array of values, the Skimmer only has 1 crit
 *		2-leg Hercs: 9 internals, terminates with a 0x32(50)
 *      Pitbull: 12 internals no 0x32(50) terminator
 *      
 *  
 *  0- UINT16 - Internals count, most hercs 22, skimmer  1, 
 *  UINT16 - SERVO\LEG\LEFT - Hitpoints
 *  UINT16 - SERVO\LEG\RIGHT - Hitpoints
 *  UINT16 - SENSOR ARRAY
 *  UINT16 - TARGETING COMPUTER
 *  UINT16 - SHIELD GENERATOR
 *  UINT16 - ENGINE
 *  UINT16 - HYDRAULICS
 *  UINT16 - STABILIZERS
 *  UINT16 - LIFE SUPPORT
 *  UINT16 - 0x32 value on bipedal hercs, pilot HP possibly
 *  20- 44 - UINT16 - slots for critical components, PITBULL has more components (4 legs and turret vs normal herc setup)
 *  
 *  XX- UINT16 - 32 - always ends with 50, Either this is "PILOT" HP, array terminator, or both.
 *  
 *  UINT16 - ? - Hercs have 29, setting to 1 crashes game
 *  UINT16 - External part HP (starting with cockpit)
 *  	UINT16 - MODEL\FLAGS\TORSO\DEBRIS - 
 *  		0xFFFF = -1 = No flame, no torso mesh debris thrown.
 *  		0x0000 = 0 = Yes flame, no torso mesh debris, mesh removed.
 *  		0x0607 = 1798 = yes flame, somehow knows to throw torso mesh
 *  			note - other values remove other mesh pieces
 *  				256 = LEG_LEFT_CALF
 *  				512 = LEG_RIGHT_CALF
 *  	        	768 = LEG_LEFT_THIGH
 *  		   	1024 = LEG_RIGHT_THIGH
 *             	1280 = LEG_RIGHT_FOOT
 *             	1536 = LEG_LEFT_FOOT
 *              1798 = somehow is TORSO_CENTER
 *     UINT8 - MODEL\BONE_ID ? maybe
 *     UINT8 - unknown byte val
 *     
 *     UINT16 - Child HercInternals count
 *       Array
 *       	UINT16 - 0x14 (20) unknown use
 *          UINT16 - HercInternals ID
 *          
 *  Component list:
 *  	COCKPIT\FRONT
 *  	COCKPIT\REAR
 *  	SHOULDER\LEFT
 *  	SHOULDER\RIGHT
 *  	WEPN_BRACK\LEFT
 *  	WEPN_BRACK\RIGHT
 *  	TORSO
 *  	LEG\LEFT\UPPER
 *  	LEG\RIGHT\UPPER
 *  	LEG\LEFT\LOWER
 *  	LEG\RIGHT\LOWER
 *  	FOOT\LEFT
 *  	FOOT\RIGHT
 *  	
 *  
 */
public class HercSimDamage extends DataFile{

	private short internalsTotal;
	private LinkedHashMap<HercInternals, Short> internals = new LinkedHashMap<HercInternals, Short>();
	
	private short unk_val41 = (short)41;
	
	private Set<HercPiece> componentData = new HashSet<HercSimDamage.HercPiece>();
	
	private ExternalComponentEntry[] hardpoints = new ExternalComponentEntry[16];
	
	public HercSimDamage() {}
	
	public HercPiece newHercPiece() {
		return new HercPiece();
	}
	
	public InternalsTarget newInternalsTarget() {
		return new InternalsTarget();
	}
	
	public ExternalComponentEntry newExternalComponentEntry() {
		return new ExternalComponentEntry();
	}
	
	public class HercPiece {
		private short armor;
		private short debrisFlags;
		private byte boneId;
		private byte unk_val;
		private InternalsTarget[] mappedInternals;
		
		public HercPiece() {}
		public HercPiece(short boneCount) {
			mappedInternals = new InternalsTarget[boneCount];
		}
		public short getArmor() {
			return armor;
		}
		public short getDebrisFlags() {
			return debrisFlags;
		}
		public byte getBoneId() {
			return boneId;
		}
		public byte getUnk_val() {
			return unk_val;
		}
		public InternalsTarget[] getMappedInternals() {
			return mappedInternals;
		}
		public void setArmor(short armor) {
			this.armor = armor;
		}
		public void setDebrisFlags(short debrisFlags) {
			this.debrisFlags = debrisFlags;
		}
		public void setBoneId(byte boneId) {
			this.boneId = boneId;
		}
		public void setUnk_val(byte unk_val) {
			this.unk_val = unk_val;
		}
		public void setMappedInternals(InternalsTarget[] mappedInternals) {
			this.mappedInternals = mappedInternals;
		}
	}
	
	public class InternalsTarget {
		private short unk_val14 = 20;	//defaults to 0x14(20) in every known example
		private HercInternals internalsId;
		
		public InternalsTarget() {}
		public InternalsTarget(short unk_val14, HercInternals internalsId) {
			this.unk_val14 = unk_val14;
			this.internalsId = internalsId;
		}
		public short getUnk_val14() {
			return unk_val14;
		}
		public HercInternals getInternalsId() {
			return internalsId;
		}
		public void setUnk_val14(short unk_val14) {
			this.unk_val14 = unk_val14;
		}
		public void setId(HercInternals internalsId) {
			this.internalsId = internalsId;
		}
	}

	public class ExternalComponentEntry{
		private short unk_first;
		private short unk_flag1;
		private short debrisOrgBoneId; //-1 = don't throw weapon
		private short possibleSpacer;
		
		public ExternalComponentEntry() {}

		public short getUnk_first() {
			return unk_first;
		}

		public short getUnk_flag1() {
			return unk_flag1;
		}

		public short getDebrisOrgBoneId() {
			return debrisOrgBoneId;
		}

		public short getPossibleSpacer() {
			return possibleSpacer;
		}

		public void setUnk_first(short unk_first) {
			this.unk_first = unk_first;
		}

		public void setUnk_flag1(short unk_flag1) {
			this.unk_flag1 = unk_flag1;
		}

		public void setDebrisOrgBoneId(short debrisOrgBoneId) {
			this.debrisOrgBoneId = debrisOrgBoneId;
		}

		public void setPossibleSpacer(short possibleSpacer) {
			this.possibleSpacer = possibleSpacer;
		}
	}
	
	public short getInternalsTotal() {
		return internalsTotal;
	}

	public LinkedHashMap<HercInternals, Short> getInternals() {
		return internals;
	}

	public void setInternalsTotal(short internalsTotal) {
		this.internalsTotal = internalsTotal;
	}

	public void setInternals(LinkedHashMap<HercInternals, Short> internals) {
		this.internals = internals;
	}

	public short getUnk_val41() {
		return unk_val41;
	}

	public void setUnk_val41(short unk_val41) {
		this.unk_val41 = unk_val41;
	}

	public Set<HercPiece> getComponentData() {
		return componentData;
	}

	public void setComponentData(Set<HercPiece> componentData) {
		this.componentData = componentData;
	}

	public ExternalComponentEntry[] getHardpoints() {
		return hardpoints;
	}

	public void setHardpoints(ExternalComponentEntry[] hardpoints) {
		this.hardpoints = hardpoints;
	}
}
