package org.hercworks.transfer.dto.file.sim;

import org.hercworks.transfer.dto.file.TransferObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("simdata")
public class HercSimDatDTO extends TransferObject {

	@JsonProperty(value = "speed_turn", index = 1)
	private int speedTurn;
	
	@JsonProperty(value = "speed_reverse", index = 2)
	private int speedReverse;
	
	@JsonProperty(value = "speed_forward", index = 3)
	private int speedForward;

	@JsonProperty(value = "unk6_val30", index = 4)
	private int unk6_Val30 = 30;

	@JsonProperty(value = "turn_decel_rate", index = 5)
	private int decelTurning;

	@JsonProperty(value = "camera_boneId", index = 6)
	private int cameraBoneId;

	@JsonProperty(value = "input_throttle_hercFlag", index = 7)
	private int inputThrottleHercFlag = 1;
	
	@JsonProperty(value = "unk14_anim_leg1", index = 8)
	private int unk14_ValAnim1 = 2;

	@JsonProperty(value = "unk14_anim_leg2", index = 9)
	private int unk16_ValAnim2 = 3;

	@JsonProperty(value = "unk14_anim_leg3", index = 10)
	private int unk18_ValAnim3 = 4;

	@JsonProperty(value = "offset_y_adjust", index = 11)
	private int unitOffsetYAdjust;

	@JsonProperty(value = "unk22_Val100Razor0", index = 12)
	private int unk22_Val750Razor0;

	@JsonProperty(value = "ai_aim_targ_offset", index = 13)
	private int aiAimTargOffset;

	@JsonProperty(value = "input_torso_razrFlag", index = 14)
	private int inputTorsoRazrFlag = 0;

	@JsonProperty(value = "torsoTwistSpeed", index = 15)
	private int torsoTwistSpeed;

	@JsonProperty(value = "unk30_Val1000", index = 16)
	private int unk30_Val1000 = 1000;

	@JsonProperty(value = "torsoTwistDegreeMax", index = 17)
	private int torsoTwistDegreeMax;
	
	@JsonProperty(value = "inputFlagsTorso", index = 18)
	private int inputFlagsTorso;

	@JsonProperty(value = "torsoPitchMaxRate", index = 19)
	private int torsoPitchMaxRate;
	
	@JsonProperty(value = "torsoPitchRate", index = 20)
	private int torsoPitchRate;

	@JsonProperty(value = "torsoPitchMax", index = 21)
	private int torsoPitchMax;
	
	@JsonProperty(value = "torsoPitchMin", index = 22)
	private int torsoPitchMin;

	@JsonProperty(value = "unk44_move_anim_rate", index = 23)
	private int unk44MoveAnimRate;
	
	@JsonIgnore
	private int modelLodArrOFs = 46;
	
	@JsonProperty(value = "model_lod_boneIds", index = 24)
	private int[] modelLoDBoneIds = new int[20];
	
	@JsonProperty(value = "unk66_Val1000", index = 25)
	private int unk66_Val1000 = 1000;

	@JsonProperty(value = "legsCritFlags1", index = 26)
	private int legsCritFlags1;

	@JsonProperty(value = "legsCritFlags2", index = 27)
	private int legsCritFlags2;

	@JsonProperty(value = "modelLegsTotal", index = 28)
	private int modelLegsTotal;

	@JsonProperty(value = "modelFlagNoDebris", index = 29)
	private int modelFlagNoDebris;

	@JsonProperty(value = "unk76_Val", index = 30)
	private int unk76_Val;

	@JsonProperty(value = "inputFlagFlyer", index = 31)
	private int inputFlagFlyer;	//hercs have 0, razor has 1

	@JsonProperty(value = "maybe_hud_id", index = 32)
	private int unk80_ValHudId;	//possibly HUD id or even palette ID?

	@JsonProperty(value = "unk82_val", index = 33)
	private int unk82_val;	//different per herc, usually 1024, 1500, or 800

	@JsonProperty(value = "unk84_val", index = 34)
	private int unk84_val; //usually 1

	@JsonProperty(value = "name", index = 35)
	private String nameStr; //0x86 - 0x97

	@JsonProperty(value = "cam_adj_ofs_y", index = 36)
	private int cameraYAxisAdj;
	
	@JsonProperty(value = "cam_adj_ofs_x", index = 37)
	private int cameraXAxisAdj;

	@JsonProperty(value = "cameraExtOrgOffset", index = 38)
	private int cameraExtOrgOffset;

	@JsonProperty(value = "unk108_camExtVal1", index = 39)
	private int unk108_camExtVal1;
	
	@JsonProperty(value = "unk110_camExtVal2", index = 40)
	private int unk110_camExtVal2;

	@JsonProperty(value = "modelFlagsShadow1", index = 41)
	private int modelFlagsShadow1;

	@JsonProperty(value = "modelFlagsShadow2", index = 42)
	private int modelFlagsShadow2;

	@JsonProperty(value = "unk116_val", index = 43)
	private int unk116_val;

	@JsonProperty(value = "unk118_val", index = 44)
	private int unk118_val;

	@JsonProperty(value = "unk120_val", index = 45)
	private int unk120_val;

	@JsonProperty(value = "unk122_mdlFlagVal", index = 46)
	private int unk122_mdlFlagVal;
	
	private static int unk124_range = 12;

	@JsonProperty(value = "unk124_all500", index = 47)
	private int[] unk124_all500;

	@JsonProperty(value = "modelSkinId", index = 48)
	private int modelSkinId;
	
	@JsonProperty(value = "unk150_val", index = 50)
	private int unk150_val;

	@JsonProperty(value = "unk152_val", index = 51)
	private int unk152_val;

	@JsonProperty(value = "unk154_fixedVal", index = 52)
	private int unk154_fixedVal;

	@JsonProperty(value = "unk156_400or800", index = 53)
	private int unk156_400or800;

	@JsonProperty(value = "unk170_val", index = 54)
	private int unk170_val;

	@JsonProperty(value = "unk172_val", index = 55)
	private int unk172_val;

	@JsonProperty(value = "unk174_250or275", index = 56)
	private int unk174_250or275;

	@JsonProperty(value = "shieldMaxTotal", index = 57)
	private int shieldMaxTotal;

	@JsonProperty(value = "unk192_val", index = 58)
	private int unk192_val;

	@JsonProperty(value = "physicsFrictionCoef", index = 59)
	private int physicsFrictionCoef;

	@JsonProperty(value = "physicsFrctionAccel", index = 60)
	private int physicsFrctionAccel;

	@JsonProperty(value = "debrisFile", index = 61)
	private String debrisFile;
	
	public HercSimDatDTO() {}

	public int getSpeedTurn() {
		return speedTurn;
	}

	public int getSpeedReverse() {
		return speedReverse;
	}

	public int getSpeedForward() {
		return speedForward;
	}

	public int getUnk6_Val30() {
		return unk6_Val30;
	}

	public int getDecelTurning() {
		return decelTurning;
	}

	public int getCameraBoneId() {
		return cameraBoneId;
	}

	public int getInputThrottleHercFlag() {
		return inputThrottleHercFlag;
	}

	public int getUnk14_ValAnim1() {
		return unk14_ValAnim1;
	}

	public int getUnk16_ValAnim2() {
		return unk16_ValAnim2;
	}

	public int getUnk18_ValAnim3() {
		return unk18_ValAnim3;
	}

	public int getUnitOffsetYAdjust() {
		return unitOffsetYAdjust;
	}

	public int getUnk22_Val750Razor0() {
		return unk22_Val750Razor0;
	}

	public int getAiAimTargOffset() {
		return aiAimTargOffset;
	}

	public int getInputTorsoRazrFlag() {
		return inputTorsoRazrFlag;
	}

	public int getTorsoTwistSpeed() {
		return torsoTwistSpeed;
	}

	public int getUnk30_Val1000() {
		return unk30_Val1000;
	}

	public int getTorsoTwistDegreeMax() {
		return torsoTwistDegreeMax;
	}

	public int getInputFlagsTorso() {
		return inputFlagsTorso;
	}

	public int getTorsoPitchMaxRate() {
		return torsoPitchMaxRate;
	}

	public int getTorsoPitchRate() {
		return torsoPitchRate;
	}

	public int getTorsoPitchMax() {
		return torsoPitchMax;
	}

	public int getTorsoPitchMin() {
		return torsoPitchMin;
	}

	public int getUnk44MoveAnimRate() {
		return unk44MoveAnimRate;
	}

	public int getModelLodArrOFs() {
		return modelLodArrOFs;
	}

	public int[] getModelLoDBoneIds() {
		return modelLoDBoneIds;
	}

	public int getUnk66_Val1000() {
		return unk66_Val1000;
	}

	public int getLegsCritFlags1() {
		return legsCritFlags1;
	}

	public int getLegsCritFlags2() {
		return legsCritFlags2;
	}

	public int getModelLegsTotal() {
		return modelLegsTotal;
	}

	public int getModelFlagNoDebris() {
		return modelFlagNoDebris;
	}

	public int getUnk76_Val() {
		return unk76_Val;
	}

	public int getInputFlagFlyer() {
		return inputFlagFlyer;
	}

	public int getUnk80_ValHudId() {
		return unk80_ValHudId;
	}

	public int getUnk82_val() {
		return unk82_val;
	}

	public int getUnk84_val() {
		return unk84_val;
	}

	public String getNameStr() {
		return nameStr;
	}

	public int getCameraYAxisAdj() {
		return cameraYAxisAdj;
	}

	public int getCameraXAxisAdj() {
		return cameraXAxisAdj;
	}

	public int getCameraExtOrgOffset() {
		return cameraExtOrgOffset;
	}

	public int getUnk108_camExtVal1() {
		return unk108_camExtVal1;
	}

	public int getUnk110_camExtVal2() {
		return unk110_camExtVal2;
	}

	public int getModelFlagsShadow1() {
		return modelFlagsShadow1;
	}

	public int getModelFlagsShadow2() {
		return modelFlagsShadow2;
	}

	public int getUnk116_val() {
		return unk116_val;
	}

	public int getUnk118_val() {
		return unk118_val;
	}

	public int getUnk120_val() {
		return unk120_val;
	}

	public int getUnk122_mdlFlagVal() {
		return unk122_mdlFlagVal;
	}

	public static int getUnk124_range() {
		return unk124_range;
	}

	public int[] getUnk124_all500() {
		return unk124_all500;
	}

	public int getModelSkinId() {
		return modelSkinId;
	}

	public int getUnk150_val() {
		return unk150_val;
	}

	public int getUnk152_val() {
		return unk152_val;
	}

	public int getUnk154_fixedVal() {
		return unk154_fixedVal;
	}

	public int getUnk156_400or800() {
		return unk156_400or800;
	}

	public int getUnk170_val() {
		return unk170_val;
	}

	public int getUnk172_val() {
		return unk172_val;
	}

	public int getUnk174_250or275() {
		return unk174_250or275;
	}

	public int getShieldMaxTotal() {
		return shieldMaxTotal;
	}

	public int getUnk192_val() {
		return unk192_val;
	}

	public int getPhysicsFrictionCoef() {
		return physicsFrictionCoef;
	}

	public int getPhysicsFrctionAccel() {
		return physicsFrctionAccel;
	}

	public String getDebrisFile() {
		return debrisFile;
	}

	public void setSpeedTurn(int speedTurn) {
		this.speedTurn = speedTurn;
	}

	public void setSpeedReverse(int speedReverse) {
		this.speedReverse = speedReverse;
	}

	public void setSpeedForward(int speedForward) {
		this.speedForward = speedForward;
	}

	public void setUnk6_Val30(int unk6_Val30) {
		this.unk6_Val30 = unk6_Val30;
	}

	public void setDecelTurning(int decelTurning) {
		this.decelTurning = decelTurning;
	}

	public void setCameraBoneId(int cameraBoneId) {
		this.cameraBoneId = cameraBoneId;
	}

	public void setInputThrottleHercFlag(int inputThrottleHercFlag) {
		this.inputThrottleHercFlag = inputThrottleHercFlag;
	}

	public void setUnk14_ValAnim1(int unk14_ValAnim1) {
		this.unk14_ValAnim1 = unk14_ValAnim1;
	}

	public void setUnk16_ValAnim2(int unk16_ValAnim2) {
		this.unk16_ValAnim2 = unk16_ValAnim2;
	}

	public void setUnk18_ValAnim3(int unk18_ValAnim3) {
		this.unk18_ValAnim3 = unk18_ValAnim3;
	}

	public void setUnitOffsetYAdjust(int unitOffsetYAdjust) {
		this.unitOffsetYAdjust = unitOffsetYAdjust;
	}

	public void setUnk22_Val750Razor0(int unk22_Val750Razor0) {
		this.unk22_Val750Razor0 = unk22_Val750Razor0;
	}

	public void setAiAimTargOffset(int aiAimTargOffset) {
		this.aiAimTargOffset = aiAimTargOffset;
	}

	public void setInputTorsoRazrFlag(int inputTorsoRazrFlag) {
		this.inputTorsoRazrFlag = inputTorsoRazrFlag;
	}

	public void setTorsoTwistSpeed(int torsoTwistSpeed) {
		this.torsoTwistSpeed = torsoTwistSpeed;
	}

	public void setUnk30_Val1000(int unk30_Val1000) {
		this.unk30_Val1000 = unk30_Val1000;
	}

	public void setTorsoTwistDegreeMax(int torsoTwistDegreeMax) {
		this.torsoTwistDegreeMax = torsoTwistDegreeMax;
	}

	public void setInputFlagsTorso(int inputFlagsTorso) {
		this.inputFlagsTorso = inputFlagsTorso;
	}

	public void setTorsoPitchMaxRate(int torsoPitchMaxRate) {
		this.torsoPitchMaxRate = torsoPitchMaxRate;
	}

	public void setTorsoPitchRate(int torsoPitchRate) {
		this.torsoPitchRate = torsoPitchRate;
	}

	public void setTorsoPitchMax(int torsoPitchMax) {
		this.torsoPitchMax = torsoPitchMax;
	}

	public void setTorsoPitchMin(int torsoPitchMin) {
		this.torsoPitchMin = torsoPitchMin;
	}

	public void setUnk44MoveAnimRate(int unk44MoveAnimRate) {
		this.unk44MoveAnimRate = unk44MoveAnimRate;
	}

	public void setModelLodArrOFs(int modelLodArrOFs) {
		this.modelLodArrOFs = modelLodArrOFs;
	}

	public void setModelLoDBoneIds(int[] modelLoDBoneIds) {
		this.modelLoDBoneIds = modelLoDBoneIds;
	}

	public void setUnk66_Val1000(int unk66_Val1000) {
		this.unk66_Val1000 = unk66_Val1000;
	}

	public void setLegsCritFlags1(int legsCritFlags1) {
		this.legsCritFlags1 = legsCritFlags1;
	}

	public void setLegsCritFlags2(int legsCritFlags2) {
		this.legsCritFlags2 = legsCritFlags2;
	}

	public void setModelLegsTotal(int modelLegsTotal) {
		this.modelLegsTotal = modelLegsTotal;
	}

	public void setModelFlagNoDebris(int modelFlagNoDebris) {
		this.modelFlagNoDebris = modelFlagNoDebris;
	}

	public void setUnk76_Val(int unk76_Val) {
		this.unk76_Val = unk76_Val;
	}

	public void setInputFlagFlyer(int inputFlagFlyer) {
		this.inputFlagFlyer = inputFlagFlyer;
	}

	public void setUnk80_ValHudId(int unk80_ValHudId) {
		this.unk80_ValHudId = unk80_ValHudId;
	}

	public void setUnk82_val(int unk82_val) {
		this.unk82_val = unk82_val;
	}

	public void setUnk84_val(int unk84_val) {
		this.unk84_val = unk84_val;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public void setCameraYAxisAdj(int cameraYAxisAdj) {
		this.cameraYAxisAdj = cameraYAxisAdj;
	}

	public void setCameraXAxisAdj(int cameraXAxisAdj) {
		this.cameraXAxisAdj = cameraXAxisAdj;
	}

	public void setCameraExtOrgOffset(int cameraExtOrgOffset) {
		this.cameraExtOrgOffset = cameraExtOrgOffset;
	}

	public void setUnk108_camExtVal1(int unk108_camExtVal1) {
		this.unk108_camExtVal1 = unk108_camExtVal1;
	}

	public void setUnk110_camExtVal2(int unk110_camExtVal2) {
		this.unk110_camExtVal2 = unk110_camExtVal2;
	}

	public void setModelFlagsShadow1(int modelFlagsShadow1) {
		this.modelFlagsShadow1 = modelFlagsShadow1;
	}

	public void setModelFlagsShadow2(int modelFlagsShadow2) {
		this.modelFlagsShadow2 = modelFlagsShadow2;
	}

	public void setUnk116_val(int unk116_val) {
		this.unk116_val = unk116_val;
	}

	public void setUnk118_val(int unk118_val) {
		this.unk118_val = unk118_val;
	}

	public void setUnk120_val(int unk120_val) {
		this.unk120_val = unk120_val;
	}

	public void setUnk122_mdlFlagVal(int unk122_mdlFlagVal) {
		this.unk122_mdlFlagVal = unk122_mdlFlagVal;
	}

	public static void setUnk124_range(int unk124_range) {
		HercSimDatDTO.unk124_range = unk124_range;
	}

	public void setUnk124_all500(int[] unk124_all500) {
		this.unk124_all500 = unk124_all500;
	}

	public void setModelSkinId(int modelSkinId) {
		this.modelSkinId = modelSkinId;
	}

	public void setUnk150_val(int unk150_val) {
		this.unk150_val = unk150_val;
	}

	public void setUnk152_val(int unk152_val) {
		this.unk152_val = unk152_val;
	}

	public void setUnk154_fixedVal(int unk154_fixedVal) {
		this.unk154_fixedVal = unk154_fixedVal;
	}

	public void setUnk156_400or800(int unk156_400or800) {
		this.unk156_400or800 = unk156_400or800;
	}

	public void setUnk170_val(int unk170_val) {
		this.unk170_val = unk170_val;
	}

	public void setUnk172_val(int unk172_val) {
		this.unk172_val = unk172_val;
	}

	public void setUnk174_250or275(int unk174_250or275) {
		this.unk174_250or275 = unk174_250or275;
	}

	public void setShieldMaxTotal(int shieldMaxTotal) {
		this.shieldMaxTotal = shieldMaxTotal;
	}

	public void setUnk192_val(int unk192_val) {
		this.unk192_val = unk192_val;
	}

	public void setPhysicsFrictionCoef(int physicsFrictionCoef) {
		this.physicsFrictionCoef = physicsFrictionCoef;
	}

	public void setPhysicsFrctionAccel(int physicsFrctionAccel) {
		this.physicsFrctionAccel = physicsFrctionAccel;
	}

	public void setDebrisFile(String debrisFile) {
		this.debrisFile = debrisFile;
	}
}
