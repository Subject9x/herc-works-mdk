package org.hercworks.transfer.dto.struct.dbsim;

import org.hercworks.core.data.struct.ProjectileType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("proj")
public class ProjectileEntryDTO {

	@JsonProperty(value = "id", index = 0)
	private int id;
	
	@JsonProperty(value = "type", index = 1)
	private ProjectileType type;
	
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

	public int getId() {
		return id;
	}

	public ProjectileType getType() {
		return type;
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
	
	public void setId(int id) {
		this.id = id;
	}

	public void setType(ProjectileType type) {
		this.type = type;
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
