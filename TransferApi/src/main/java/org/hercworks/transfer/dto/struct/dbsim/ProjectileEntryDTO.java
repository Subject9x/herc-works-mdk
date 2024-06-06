package org.hercworks.transfer.dto.struct.dbsim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("proj")
public class ProjectileEntryDTO {

	@JsonProperty(value = "proj_id", index = 0)
	private int projId;
	
	@JsonProperty(value = "unk1_val", index = 1)
	private int unk1_val;
	
	@JsonProperty(value = "missile_id", index = 2)
	private int missileId;
	
	@JsonProperty(value = "dmg_shield", index = 3)
	private int damageShield;

	@JsonProperty(value = "dmg_armor", index = 4)
	private int damageArmor;

	@JsonProperty(value = "unk2_val", index = 5)
	private int unk2_val;

	@JsonProperty(value = "speed", index = 6)
	private int speed;

	@JsonProperty(value = "impact_effect_ids", index = 7)
	private ImpactEffectIds impactEffects;
	
	public ProjectileEntryDTO() {}

	public ImpactEffectIds newEffectsObj() {
		return new ImpactEffectIds();
	}
	
	@JsonRootName("")
	public class ImpactEffectIds {
		
		@JsonProperty(value = "shields", index = 0)
		private int[] shields = new int[4];

		@JsonProperty(value = "ground", index = 1)
		private int[] ground = new int[4];
		
		@JsonProperty(value = "armor", index = 2)
		private int[] armor = new int[4];
		
		public ImpactEffectIds() {}

		public int[] getShields() {
			return shields;
		}

		public int[] getArmor() {
			return armor;
		}

		public int[] getGround() {
			return ground;
		}

		public void setShields(int[] shields) {
			this.shields = shields;
		}

		public void setArmor(int[] armor) {
			this.armor = armor;
		}

		public void setGround(int[] ground) {
			this.ground = ground;
		}
	}

	public int getProjId() {
		return projId;
	}

	public int getUnk1_val() {
		return unk1_val;
	}

	public int getMissileId() {
		return missileId;
	}

	public int getDamageShield() {
		return damageShield;
	}

	public int getDamageArmor() {
		return damageArmor;
	}

	public int getUnk2_val() {
		return unk2_val;
	}

	public int getSpeed() {
		return speed;
	}

	public ImpactEffectIds getImpactEffects() {
		return impactEffects;
	}
	
	public void setProjId(int projId) {
		this.projId = projId;
	}

	public void setUnk1_val(int unk1_val) {
		this.unk1_val = unk1_val;
	}

	public void setMissileId(int missileId) {
		this.missileId = missileId;
	}

	public void setDamageShield(int damageShield) {
		this.damageShield = damageShield;
	}

	public void setDamageArmor(int damageArmor) {
		this.damageArmor = damageArmor;
	}

	public void setUnk2_val(int unk2_val) {
		this.unk2_val = unk2_val;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setImpactEffects(ImpactEffectIds impactEffects) {
		this.impactEffects = impactEffects;
	}
}
