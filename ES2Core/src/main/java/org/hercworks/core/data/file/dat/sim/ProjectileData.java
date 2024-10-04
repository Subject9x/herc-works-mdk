package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 		/DBSIM/DAT/PROJ.DAT
 * 
 * 	0- UINT16 - total weapons
 * 
 * 	Entries are in Weapon ID order!
 * 	SEQ0 - 36 bytes per segment
 * 		S0_0- INT16 - unknown
 * 		S1_2- INT16 - ID\BULLETS or ID\ROCKETS
 * 		S2_4- UINT16 - DMG\SHIELD
 * 		S3_6- UINT16 - DMG\ARMOR
 * 		S4_8- UINT16 - ?
 * 		S5_10- UINT16 - SPEED in fixed point. 5000 -> 500.0
 * 
 * 		S6_12- INT16 - IMPACT\SHIELD\0
 * 		S7_14- INT16 - IMPACT\SHIELD\1
 * 		S8_16- INT16 - IMPACT\SHIELD\2
 * 		S9_18- INT16 - IMPACT\SHIELD\3
 * 
 * 		S10_20- INT16 - IMPACT\GROUND\0
 * 		S11_22- INT16 - IMPACT\GROUND\1
 * 		S12_24- INT16 - IMPACT\GROUND\2
 * 		S13_26- INT16 - IMPACT\GROUND\3
 * 
 * 		S14_28- INT16 - IMPACT\ARMOR\0
 * 		S15_30- INT16 - IMPACT\ARMOR\1
 * 		S16_32- INT16 - IMPACT\ARMOR\2
 * 		S17_34- INT16 - IMPACT\ARMOR\3
 */
public class ProjectileData extends DataFile {

	private short total;
	
	private Projectile[] data;
	
	public ProjectileData() {}
	
	public short getTotal() {
		return total;
	}

	public Projectile[] getData() {
		return data;
	}

	public void setTotal(short total) {
		this.total = total;
	}

	public void setData(Projectile[] data) {
		this.data = data;
	}

	public Projectile newProjectile() {
		return new Projectile();
	}
	
	public class Projectile {
		
		private short unk1_val;
		private short missileId;
		private short damageShield;
		private short damageArmor;
		private short unk2_val;
		private short speed;
		private short[] impactFXShield = new short[4];
		private short[] impactFXArmor = new short[4];
		private short[] impactFXGround = new short[4];
		
		public Projectile() {}

		public short getUnk1_val() {
			return unk1_val;
		}

		public short getMissileId() {
			return missileId;
		}

		public short getDamageShield() {
			return damageShield;
		}

		public short getDamageArmor() {
			return damageArmor;
		}

		public short getUnk2_val() {
			return unk2_val;
		}

		public short getSpeed() {
			return speed;
		}

		public short[] getImpactFXShield() {
			return impactFXShield;
		}

		public short[] getImpactFXArmor() {
			return impactFXArmor;
		}

		public short[] getImpactFXGround() {
			return impactFXGround;
		}

		public void setUnk1_val(short unk1_val) {
			this.unk1_val = unk1_val;
		}

		public void setMissileId(short missileId) {
			this.missileId = missileId;
		}

		public void setDamageShield(short damageShield) {
			this.damageShield = damageShield;
		}

		public void setDamageArmor(short damageArmor) {
			this.damageArmor = damageArmor;
		}

		public void setUnk2_val(short unk2_val) {
			this.unk2_val = unk2_val;
		}

		public void setSpeed(short speed) {
			this.speed = speed;
		}

		public void setImpactFXShield(short[] impactFXShield) {
			this.impactFXShield = impactFXShield;
		}

		public void setImpactFXArmor(short[] impactFXArmor) {
			this.impactFXArmor = impactFXArmor;
		}

		public void setImpactFXGround(short[] impactFXGround) {
			this.impactFXGround = impactFXGround;
		}
	}
}
