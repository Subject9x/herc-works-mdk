package org.hercworks.core.data.file.dat.sim;

import org.hercworks.voln.DataFile;

/**
 * 	FILE
 * 	dat\\[herc].dat
 * 	0 - UINT16 - SPEED\TURNING
 *  2 - INT16  - SPEED\REVERSE
 *  4 - UINT16 - SPEED\FORWARD
 *  6 - UINT16 - SPEED\ACCEL_DECEL - both values
 *  8-	UINT16 - SPEED\TURNING\DECELERATION
 *  10-	UINT16 - CAMERA\BONE_ID
 *  12- UINT16 - MODEL\ANIM\ID\WALK
 *  14- UINT16 - MODEL\ANIM\ID\RUN
 *  16- UINT16 - MODEL\ANIM\ID\STOP
 *  18- UINT16 - MODEL\ANIM\ID\TORSOPITCH
 *  20- UINT16 - MODEL\OFS\ADJ_ORIGIN_Y up/down adjust units
 *  22- UINT16 - ? - 750 or 1000, razor has 0x0000
 *  24- UINT16 - AI\AIM\TARG_OFS - this affects AI accuracy, high numbers make AI auto-miss. 
 *  26- UINT16 - INPUT\FLAG\TORSO\RAZR_OR_HERC
 *  28- UINT16 - TORSO\TWIST\SPEED
 *  30- UINT16 - ? - All units have 1000 except raptor2 @ 250
 *  32- UINT16 - TORSO\TWIST\ range, is 14000, or 140deg expressed as 14000
 *  34- UINT16 - INPUT\FLAG\TORSO - always 5 | razor has 65531, disables torso input for hercs if 65531
 *  36- UINT16 - TORSO\PITCH\ rate max - as deg/sec
 *  38- UINT16 - TORSO\PITCH\ rate  -  deg/sec
 *  40- UINT16 - TORSO\PITCH\MAX - is 14000, or 140deg expressed as 14000
 *  42- INT16  - TORSO\PITCH\MIN - is -14000, or 140deg expressed as 14000
 *  44- UINT16  - Move Speed binding to leg animation somehow
 *  
 *  46-65 - BONE ID for LoD mesh? , 255 is used as a terminator
 *  
 *  66- UINT16 - always 1000
 *  68- UINT16 - LEGS\CRIT\FLAGS1 - Hercs 7, Pitbull 2, Spider 1, 2=model doesnt fall when 1 leg blown
 *  70- UINT16 - LEGS\CRIT\FLAGS2
 *  72- UINT16 - MODEL\LEGS\TOTAL - 0=ignore leg destruction, 1=lose 1 leg and move, 2=lose all move, 3+=crash 
 *  74- UINT16 - MODEL\DEBRIS\FLAG1 - 0 leave debris, 1 - leave no debris
 *  76- UINT16 - ? - ARMOR val?
 *  78- UINT16 - INPUT\FLAG\FLIGHT_MODEL - Razor has 1, all other units 0
 *  80- UINT16 - HUD\PALETTE\ID - changing this alters the colors of the HUD DBM's
 *  82- UINT16 - ? - different values, 1500, 1024, 800
 *  84- UINT16  - sometimes 0 but mostly 1
 *   
 *  86...97- STRING - UNIT\NAME
 *  
 *  98-  UINT16 - CAMERA\Y_ADJ - fixed-point value 100 -> 1.00, 0 for cybrids, how far forward to place camera
 *  100- UINT16 - CAMERA\X_ADJ - fixed-point value 100 -> 1.00, 0 for cybrids, how far forward to place camera
 *  
 *  102- BLANK BYTES
 *  
 *  104- UINT16 - EXT_CAMERA\OFFSET\ORIGIN - fixedpoint value, how high the external camera focus point is from herc origin
 *  
 *  106- BLANK BYTES
 *  
 *  108- INT16 - EXT_CAMERA\ ? Always negative, with a different number
 *  110- UINT16 - EXT_CAMERA\ ? always 750, Together these affect rendering'
 *  
 *  //somehow model flags work together to produce shadow effects
 *  112- UINT16 - MODEL\FLAGS\SHADOW - 0xFFFF = no shadow, 0 = normal shadow, 512 = funky,4096= basic shadow + right foot, 32768=right foot only, 1024,2048,8192,16384- crash, 
 *  114- UINT16 - MODEL\FLAGS\SHADOW - 0&256=big blue octagon, 0&512=both feet, normal shadow,  0&1024,0&2048=crash, 0&4096=both feet, foot pattern in center too, 0&8192, 0&16384=crash,  
 *  
 *  116- UINT16 - ? - all units have 3839, Spider has 255, 
 *  118- UINT16 - ? - all units 3087, spider has 0, Pitbull has 5647
 *  120- UINT16 - ? - all hercs have 0, Pitbull has 23
 *  122- UINT16 - MODEL\ANIM\? - all hercs have 6, Pitbull/Spider have 1
 *  
 *  //these values seem to have 0 effect on the game, -10000, 500000, no visible effect
 *  124- UINT16 - all units have 500
 *  126- UINT16 - all units have 500
 *  128- UINT16 - all units have 500
 *  130- UINT16 - all units have 500
 *  132- UINT16 - all units have 500
 *  134- UINT16 - all units have 500
 *  136- UINT16 - all units have 500
 *  138- UINT16 - all units have 500
 *  140- UINT16 - all units have 500
 *  142- UINT16 - all units have 500
 *  144- UINT16 - all units have 500
 *  146- UINT16 - all units have 500
 *  
 *  148- UINT16 - MODEL\SKIN\ID - OTL-00, 'New Hercs'-06, 'old hercs' - 02, Cybrids-03
 *  150- UINT16 - speed again?
 *  152- INT16  - negative, usually -300 or -350
 *  154- UINT16 - most units are 50 but Colossus/Apoca is 75 for some reason.
 *  156- UINT16 - light units have 400 | heavy have 800
 *  
 *  158 - 169 - BLANK BYTES
 *  
 *  170- UINT16  - ? each herc has a different value.
 *  172- UINT16  - ? each herc has a negative different value.
 *  174- INT16 - ? - light units have 250 | heavy have 275
 *  
 *  176 - 189 - BLANK BYTES
 *  
 *  190- UINT16 - SHIELD\MAX - every unit has 3500
 *  192- UINT16 - ? - player hercs have a value: 30, 35, 45, 65, 70, 265 for razor
 *  194- UINT16 - SPEED\FRICTION - 0 for razor, but large values for other hercs, 4700 for outlaw example. Fixed-int
 *  196- UINT16 - SPEED\ACCELERATION\FACTOR - most hercs are 8 or 10 (8 or 0A) 
 *  
 *  198 - 203 - BLANK BYTES
 *  
 *  204...216- STRING - UNIT\DEBRIS_FILE
 */
public class HercSimDat extends DataFile {

	private short speedTurn;
	private short speedReverse;
	private short speedForward;
	private short speedAccelDecel;
	
	private short decelTurning;
	
	private short cameraBoneId;
	
	private short animId_walk;
	private short animId_run = 2;
	private short animId_stopMove = 3;
	private short animTorsoPitchId = 4;
	private short unitOffsetYAdjust;
	
	private short unk22_Val750Razor0;
	
	private short aiAimTargOffset;
	
	private short inputTorsoRazrFlag;
	private short torsoTwistSpeed;
	private short torsoRotateAccel;
	private short torsoTwistDegreeMax;
	private short inputFlagsTorso;
	private short torsoPitchMaxRate;
	private short torsoPitchRate;
	private short torsoPitchMax;
	private short torsoPitchMin;
	
	private short unk44_MoveAnimRate;
	
	private static int modelLodArrOFs = 46;
	private byte[] modelLoDBoneIds = new byte[20];
	
	//0x58-0x65 - extra boneId space in array, as seen in PITBULL.DAT
	
	private short unk66_Val1000 = (short)1000;
	
	private short legsCritFlags1;
	private short legsCritFlags2;
	private short modelLegsTotal;
	private short modelFlagNoDebris;
	
	private short unk76_Val;
	
	private short inputFlagFlyer;	//hercs have 0, razor has 1
	
	private short unk80_ValHudId;	//possibly HUD id or even palette ID?
	
	private short unk82_val;	//different per herc, usually 1024, 1500, or 800
	
	private short unk84_val; //usually 1
	
	private byte[] nameBytes; //0x86 - 0x97
	
	private short cameraYAxisAdj;
	private short cameraXAxisAdj;
	
	//blank bytes 0x102
	
	private short cameraExtOrgOffset;
	
	//blank bytes 0x106
	
	private short unk108_camExtVal1;
	private short unk110_camExtVal2;
	
	private short modelFlagsShadow1;
	private short modelFlagsShadow2;
	
	private short unk116_val;
	private short unk118_val;
	private short unk120_val;
	private short unk122_mdlFlagVal;
	
	private static int unk124_range = 12;
	private short[] unk124_all500;
	
	private short modelSkinId;
	
	private short unk150_val;
	private short unk152_val;
	private short unk154_fixedVal;
	private short unk156_400or800;
	
	//158 - 169 - BLANK BYTES
	
	private short unk170_val;
	private short unk172_val;
	private short unk174_250or275;
	
	// 176 - 189 - BLANK BYTES
	
	private short shieldMaxTotal;
	private short unk192_val;
	private short physicsFrictionCoef;
	private short physicsFrctionAccel;
	
	//198 - 203 - BLANK BYTES
	
	private byte[] debrisFile;
	
	public HercSimDat() {}

	public short getSpeedTurn() {
		return speedTurn;
	}

	public short getSpeedReverse() {
		return speedReverse;
	}

	public short getSpeedForward() {
		return speedForward;
	}

	public short getSpeedAccelDecel() {
		return speedAccelDecel;
	}

	public short getDecelTurning() {
		return decelTurning;
	}

	public short getCameraBoneId() {
		return cameraBoneId;
	}

	public short getAnimId_Walk() {
		return animId_walk;
	}

	public short getAnimId_Run() {
		return animId_run;
	}

	public short animId_StopMove() {
		return animId_stopMove;
	}

	public short getAnimId_TorsoPitch() {
		return animTorsoPitchId;
	}

	public short getUnitOffsetYAdjust() {
		return unitOffsetYAdjust;
	}

	public short getUnk22_Val750Razor0() {
		return unk22_Val750Razor0;
	}

	public short getAiAimTargOffset() {
		return aiAimTargOffset;
	}

	public short getInputTorsoRazrFlag() {
		return inputTorsoRazrFlag;
	}

	public short getTorsoTwistSpeed() {
		return torsoTwistSpeed;
	}

	public short getTorsoRotateAccel() {
		return torsoRotateAccel;
	}

	public short getTorsoTwistDegreeMax() {
		return torsoTwistDegreeMax;
	}

	public short getInputFlagsTorso() {
		return inputFlagsTorso;
	}

	public short getTorsoPitchMaxRate() {
		return torsoPitchMaxRate;
	}

	public short getTorsoPitchRate() {
		return torsoPitchRate;
	}

	public short getTorsoPitchMax() {
		return torsoPitchMax;
	}

	public short getTorsoPitchMin() {
		return torsoPitchMin;
	}

	public short getUnk44_MoveAnimRate() {
		return unk44_MoveAnimRate;
	}

	public static int getModelLodArrOFs() {
		return modelLodArrOFs;
	}

	public byte[] getModelLoDBoneIds() {
		return modelLoDBoneIds;
	}

	public short getUnk66_Val1000() {
		return unk66_Val1000;
	}

	public short getLegsCritFlags1() {
		return legsCritFlags1;
	}

	public short getLegsCritFlags2() {
		return legsCritFlags2;
	}

	public short getModelLegsTotal() {
		return modelLegsTotal;
	}

	public short getModelFlagNoDebris() {
		return modelFlagNoDebris;
	}

	public short getUnk76_Val() {
		return unk76_Val;
	}

	public short getInputFlagFlyer() {
		return inputFlagFlyer;
	}

	public short getUnk80_ValHudId() {
		return unk80_ValHudId;
	}

	public short getUnk82_val() {
		return unk82_val;
	}

	public short getUnk84_val() {
		return unk84_val;
	}

	public byte[] getNameBytes() {
		return nameBytes;
	}

	public short getCameraYAxisAdj() {
		return cameraYAxisAdj;
	}

	public short getCameraXAxisAdj() {
		return cameraXAxisAdj;
	}

	public short getCameraExtOrgOffset() {
		return cameraExtOrgOffset;
	}

	public short getUnk108_camExtVal1() {
		return unk108_camExtVal1;
	}

	public short getUnk110_camExtVal2() {
		return unk110_camExtVal2;
	}

	public short getModelFlagsShadow1() {
		return modelFlagsShadow1;
	}

	public short getModelFlagsShadow2() {
		return modelFlagsShadow2;
	}

	public short getUnk116_val() {
		return unk116_val;
	}

	public short getUnk118_val() {
		return unk118_val;
	}

	public short getUnk120_val() {
		return unk120_val;
	}

	public short getUnk122_mdlFlagVal() {
		return unk122_mdlFlagVal;
	}

	public static int getUnk124_range() {
		return unk124_range;
	}

	public short[] getUnk124_all500() {
		return unk124_all500;
	}

	public short getModelSkinId() {
		return modelSkinId;
	}

	public short getUnk150_val() {
		return unk150_val;
	}

	public short getUnk152_val() {
		return unk152_val;
	}

	public short getUnk154_fixedVal() {
		return unk154_fixedVal;
	}

	public short getUnk156_400or800() {
		return unk156_400or800;
	}

	public short getUnk170_val() {
		return unk170_val;
	}

	public short getUnk172_val() {
		return unk172_val;
	}

	public short getUnk174_250or275() {
		return unk174_250or275;
	}

	public short getShieldMaxTotal() {
		return shieldMaxTotal;
	}

	public short getUnk192_val() {
		return unk192_val;
	}

	public short getPhysicsFrictionCoef() {
		return physicsFrictionCoef;
	}

	public short getPhysicsFrctionAccel() {
		return physicsFrctionAccel;
	}

	public byte[] getDebrisFile() {
		return debrisFile;
	}

	public void setSpeedTurn(short speedTurn) {
		this.speedTurn = speedTurn;
	}

	public void setSpeedReverse(short speedReverse) {
		this.speedReverse = speedReverse;
	}

	public void setSpeedForward(short speedForward) {
		this.speedForward = speedForward;
	}

	public void setSpeedAccelDecel(short speedAccelDecel) {
		this.speedAccelDecel = speedAccelDecel;
	}

	public void setDecelTurning(short decelTurning) {
		this.decelTurning = decelTurning;
	}

	public void setCameraBoneId(short cameraBoneId) {
		this.cameraBoneId = cameraBoneId;
	}

	public void setAnimId_Walk(short animId_walk) {
		this.animId_walk = animId_walk;
	}

	public void setAnimId_Run(short animId_run) {
		this.animId_run = animId_run;
	}

	public void setAnimId_StopMove(short animId_stopMove) {
		this.animId_stopMove = animId_stopMove;
	}

	public void setAnimId_TorsoPitch(short animTorsoPitchId) {
		this.animTorsoPitchId = animTorsoPitchId;
	}

	public void setUnitOffsetYAdjust(short unitOffsetYAdjust) {
		this.unitOffsetYAdjust = unitOffsetYAdjust;
	}

	public void setUnk22_Val750Razor0(short unk22_Val750Razor0) {
		this.unk22_Val750Razor0 = unk22_Val750Razor0;
	}

	public void setAiAimTargOffset(short aiAimTargOffset) {
		this.aiAimTargOffset = aiAimTargOffset;
	}

	public void setInputTorsoRazrFlag(short inputTorsoRazrFlag) {
		this.inputTorsoRazrFlag = inputTorsoRazrFlag;
	}

	public void setTorsoTwistSpeed(short torsoTwistSpeed) {
		this.torsoTwistSpeed = torsoTwistSpeed;
	}

	public void setTorsoRotateAccel(short torsoRotateAccel) {
		this.torsoRotateAccel = torsoRotateAccel;
	}

	public void setTorsoTwistDegreeMax(short torsoTwistDegreeMax) {
		this.torsoTwistDegreeMax = torsoTwistDegreeMax;
	}

	public void setInputFlagsTorso(short inputFlagsTorso) {
		this.inputFlagsTorso = inputFlagsTorso;
	}

	public void setTorsoPitchMaxRate(short torsoPitchMaxRate) {
		this.torsoPitchMaxRate = torsoPitchMaxRate;
	}

	public void setTorsoPitchRate(short torsoPitchRate) {
		this.torsoPitchRate = torsoPitchRate;
	}

	public void setTorsoPitchMax(short torsoPitchMax) {
		this.torsoPitchMax = torsoPitchMax;
	}

	public void setTorsoPitchMin(short torsoPitchMin) {
		this.torsoPitchMin = torsoPitchMin;
	}

	public void setUnk44_MoveAnimRate(short unk44_MoveAnimRate) {
		this.unk44_MoveAnimRate = unk44_MoveAnimRate;
	}

	public static void setModelLodArrOFs(int modelLodArrOFs) {
		HercSimDat.modelLodArrOFs = modelLodArrOFs;
	}

	public void setModelLoDBoneIds(byte[] modelLoDBoneIds) {
		this.modelLoDBoneIds = modelLoDBoneIds;
	}

	public void setUnk66_Val1000(short unk66_Val1000) {
		this.unk66_Val1000 = unk66_Val1000;
	}

	public void setLegsCritFlags1(short legsCritFlags1) {
		this.legsCritFlags1 = legsCritFlags1;
	}

	public void setLegsCritFlags2(short legsCritFlags2) {
		this.legsCritFlags2 = legsCritFlags2;
	}

	public void setModelLegsTotal(short modelLegsTotal) {
		this.modelLegsTotal = modelLegsTotal;
	}

	public void setModelFlagNoDebris(short modelFlagNoDebris) {
		this.modelFlagNoDebris = modelFlagNoDebris;
	}

	public void setUnk76_Val(short unk76_Val) {
		this.unk76_Val = unk76_Val;
	}

	public void setInputFlagFlyer(short inputFlagFlyer) {
		this.inputFlagFlyer = inputFlagFlyer;
	}

	public void setUnk80_ValHudId(short unk80_ValHudId) {
		this.unk80_ValHudId = unk80_ValHudId;
	}

	public void setUnk82_val(short unk82_val) {
		this.unk82_val = unk82_val;
	}

	public void setUnk84_val(short unk84_val) {
		this.unk84_val = unk84_val;
	}

	public void setNameBytes(byte[] nameBytes) {
		this.nameBytes = nameBytes;
	}

	public void setCameraYAxisAdj(short cameraYAxisAdj) {
		this.cameraYAxisAdj = cameraYAxisAdj;
	}

	public void setCameraXAxisAdj(short cameraXAxisAdj) {
		this.cameraXAxisAdj = cameraXAxisAdj;
	}

	public void setCameraExtOrgOffset(short cameraExtOrgOffset) {
		this.cameraExtOrgOffset = cameraExtOrgOffset;
	}

	public void setUnk108_camExtVal1(short unk108_camExtVal1) {
		this.unk108_camExtVal1 = unk108_camExtVal1;
	}

	public void setUnk110_camExtVal2(short unk110_camExtVal2) {
		this.unk110_camExtVal2 = unk110_camExtVal2;
	}

	public void setModelFlagsShadow1(short modelFlagsShadow1) {
		this.modelFlagsShadow1 = modelFlagsShadow1;
	}

	public void setModelFlagsShadow2(short modelFlagsShadow2) {
		this.modelFlagsShadow2 = modelFlagsShadow2;
	}

	public void setUnk116_val(short unk116_val) {
		this.unk116_val = unk116_val;
	}

	public void setUnk118_val(short unk118_val) {
		this.unk118_val = unk118_val;
	}

	public void setUnk120_val(short unk120_val) {
		this.unk120_val = unk120_val;
	}

	public void setUnk122_mdlFlagVal(short unk122_mdlFlagVal) {
		this.unk122_mdlFlagVal = unk122_mdlFlagVal;
	}

	public static void setUnk124_range(int unk124_range) {
		HercSimDat.unk124_range = unk124_range;
	}

	public void setUnk124_all500(short[] unk124_all500) {
		this.unk124_all500 = unk124_all500;
	}

	public void setModelSkinId(short modelSkinId) {
		this.modelSkinId = modelSkinId;
	}

	public void setUnk150_val(short unk150_val) {
		this.unk150_val = unk150_val;
	}

	public void setUnk152_val(short unk152_val) {
		this.unk152_val = unk152_val;
	}

	public void setUnk154_fixedVal(short unk154_fixedVal) {
		this.unk154_fixedVal = unk154_fixedVal;
	}

	public void setUnk156_400or800(short unk156_400or800) {
		this.unk156_400or800 = unk156_400or800;
	}

	public void setUnk170_val(short unk170_val) {
		this.unk170_val = unk170_val;
	}

	public void setUnk172_val(short unk172_val) {
		this.unk172_val = unk172_val;
	}

	public void setUnk174_250or275(short unk174_250or275) {
		this.unk174_250or275 = unk174_250or275;
	}

	public void setShieldMaxTotal(short shieldMaxTotal) {
		this.shieldMaxTotal = shieldMaxTotal;
	}

	public void setUnk192_val(short unk192_val) {
		this.unk192_val = unk192_val;
	}

	public void setPhysicsFrictionCoef(short physicsFrictionCoef) {
		this.physicsFrictionCoef = physicsFrictionCoef;
	}

	public void setPhysicsFrctionAccel(short physicsFrctionAccel) {
		this.physicsFrctionAccel = physicsFrctionAccel;
	}

	public void setDebrisFile(byte[] debrisFile) {
		this.debrisFile = debrisFile;
	}
	
}
