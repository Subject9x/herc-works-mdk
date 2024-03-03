package com.mech.works.data.file.dat.sim;

import com.mech.works.data.ref.files.DataFile;

/**
 * 	FILE - dat\\[herc].dat
 * 	0 - UINT16 - SPEED\TURNING
 *  2 - INT16  - SPEED\REVERSE
 *  4 - UINT16 - SPEED\FORWARD
 *  6 - UINT16 - ? - making it negative causes unbounded accelerating reverse direction
 *  8-	UINT16 - SPEED\TURNING\DECELERATION
 *  10-	UINT16 - CAMERA\BONE_ID
 *  12- UINT16 - Some kind of input flags, Razor has different value, but all hercs have 0x0001
 *  14- UINT16 - ? - animation related maybe
 *  16- UINT16 - ? - animation related maybe
 *  18- UINT16 - ? - animation / speed binding related
 *  20- UINT16 - ? - Colossus has 400, other hercs have 0
 *  22- UINT16 - ? - 750 or 1000, razor has 0x0000
 *  24- UINT16 - AI - aiming offset - this affects AI accuracy, high numbers make AI auto-miss. 
 *  26- UINT16 - BLANK BYTES
 *  28- UINT16 - TORSO\TWIST\ speed
 *  30- UINT16 - ? - All units have 1000 except raptor2 @ 250
 *  32- UINT16 - TORSO\TWIST\ range, is 14000, or 140deg expressed as 14000
 *  34- UINT16 - TORSO\FLAGS\INPUT - always 5 | razor has 65531, disables torso input for hercs if 65531
 *  36- UINT16 - TORSO\PITCH\ rate max - as deg/sec
 *  38- UINT16 - TORSO\PITCH\ rate  -  deg/sec
 *  40- UINT16 - TORSO\PITCH\MAX - is 14000, or 140deg expressed as 14000
 *  42- INT16  - TORSO\PITCH\MIN - is -14000, or 140deg expressed as 14000
 *  44- UINT16  - Move Speed binding to leg animation somehow
 *  
 *  46-65 - BONE ID for LoD mesh? , 255 is used as a terminator
 *  
 *  66- UINT16 - always 1000
 *  
 *  68- UINT16 - ? - Hercs 7, Pitbull 2, Spider 1
 *  70- UINT16 - ?
 *  72- UINT16 - LEGS\CRIT\FLAG1 - 0=ignore leg destruction, 1=lose 1 leg and move, 2=lose all move, 3+=crash 
 *  74- UINT16 - MODEL\DEBRIS\FLAG1 - 0 leave debris, 1 - leave no debris
 *  
 *  76- UINT16 - ? - ARMOR val?
 *  78- UINT16 - MOVE\FLIGHT_MODEL\FLAG - Razor has 1, all other units 0
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
 *  116- UINT16  - ? - all units have 3839, Spider has 255, 
 *  118- UINT16  - ? - all units 3087, spider has 0, Pitbull has 5647
 *  
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
 *  152- INT16  - seemingly negative values if bytes paired as UINT16
 *  154- UINT16 - most units are 50 but Colossus/Apoca is 75 for some reason.
 *  156- UINT16 - light units have 400 | heavy have 800
 *  
 *  158 - 169 - BLANK BYTES
 *  
 *  170- INT16  - ? each herc has a different value.
 *  172- INT16  - ? each herc has a negative different value.
 *  174- UINT16 - ? - light units have 250 | heavy have 275
 *  
 *  176 - 189 - BLANK BYTES
 *  
 *  190- UINT16 - ? - every unit has 3500
 *  192- UINT16 - ? - player hercs have a value: 30, 35, 45, 65, 70, 265 for razor
 *  194- UINT16 - SPEED\FRICTION - 0 for razor, but large values for other hercs, 4700 for outlaw example.
 *  196- UINT16 - SPEED\ACCELERATION\FACTOR - most hercs are 8 or 10 (8 or 0A) 
 *  
 *  198 - 203 - BLANK BYTES
 *  
 *  204...216- STRING - UNIT\DEBRIS_FILE
 */
public class HercInfoDat extends DataFile {

	private int speedTurn;
	private int speedReverse;
	private int speedForward;
	
	private static short unk6_Val30 = (short)30;
	
	private int decelTurning;
	
	private int cameraBoneId;
	
	private short unk12_ValFlag05;
	
	private short unk14_ValAnim1;
	private short unk16_ValAnim2;
	private short unk18_ValAnim3;
	private short unk20_Val400;
	private short unk22_Val750Razor0;
	
	private short aiAimOffset;
	
	//blank bytes at 0x26
	
	private int torsoTwistSpeed;
	
	private short unk30_Val1000;
	
	private int torsoTwistDegreeMax;
	private short torsoFlagsInput;
	
	private int torsoPitchMaxRate;
	private int torsoPitchRate;
	
	private int torsoPitchMax;
	private int torsoPitchMin;
	
	private short unk44_MoveAnim;
	
	private static int modelLodArrOFs = 46;
	private byte[] modelLODId = new byte[20];
	
	//blank bytes at 0x58-0x65
	
	private static short unk66_Val1000;
	
	private short unk68_AIAimVal1;
	private short unk70_AIAimVal2;
	private short unk72_AIAimVal3;
	private short unk74_AIAimVal4;
	
	
	
	private short unk76_Val;
	
	private short unk78_ValRazor1;	//hercs have 0, razor has 1
	
	private short unk80_ValHudId;	//possibly HUD id or even palette ID?
	
	private short unk82_ValUnk;	//different per herc, usually 1024, 1500, or 800
	
	private short unk84_ValUnk; //usually 1
	
	private String nameBytes; //0x86 - 0x97
	
	private short cameraYAxisAdj;
	private short cameraXAxisAdj;
	
	//blank bytes 0x102
	
	private short cameraExtOrgOffset;
	
	
	
	private String debrisFile;
	
	public HercInfoDat() {}

	public int getSpeedTurn() {
		return speedTurn;
	}

	public int getSpeedReverse() {
		return speedReverse;
	}

	public int getSpeedForward() {
		return speedForward;
	}

	public int getDecelTurning() {
		return decelTurning;
	}

	public int getCameraBoneId() {
		return cameraBoneId;
	}

	public short getUnk12_ValFlag05() {
		return unk12_ValFlag05;
	}

	public short getUnk14_ValAnim1() {
		return unk14_ValAnim1;
	}

	public short getUnk16_ValAnim2() {
		return unk16_ValAnim2;
	}

	public short getUnk18_ValAnim3() {
		return unk18_ValAnim3;
	}

	public short getUnk20_Val400() {
		return unk20_Val400;
	}

	public short getUnk22_Val750Razor0() {
		return unk22_Val750Razor0;
	}

	public short getAiAimOffset() {
		return aiAimOffset;
	}

	public int getTorsoTwistSpeed() {
		return torsoTwistSpeed;
	}

	public short getUnk30_Val1000() {
		return unk30_Val1000;
	}

	public int getTorsoTwistDegreeMax() {
		return torsoTwistDegreeMax;
	}

	public short getTorsoFlagsInput() {
		return torsoFlagsInput;
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

	public short getUnk44_MoveAnim() {
		return unk44_MoveAnim;
	}
	
	public void setModelLODId(byte[] modelLODId) {
		this.modelLODId = modelLODId;
	}

	public short getUnk76_Val() {
		return unk76_Val;
	}

	public short getUnk78_ValRazor1() {
		return unk78_ValRazor1;
	}

	public short getUnk80_ValHudId() {
		return unk80_ValHudId;
	}

	public short getUnk82_ValUnk() {
		return unk82_ValUnk;
	}

	public short getUnk84_ValUnk() {
		return unk84_ValUnk;
	}

	public String getNameBytes() {
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

	public void setDecelTurning(int decelTurning) {
		this.decelTurning = decelTurning;
	}

	public void setCameraBoneId(int cameraBoneId) {
		this.cameraBoneId = cameraBoneId;
	}

	public void setUnk12_ValFlag05(short unk12_ValFlag05) {
		this.unk12_ValFlag05 = unk12_ValFlag05;
	}

	public void setUnk14_ValAnim1(short unk14_ValAnim1) {
		this.unk14_ValAnim1 = unk14_ValAnim1;
	}

	public void setUnk16_ValAnim2(short unk16_ValAnim2) {
		this.unk16_ValAnim2 = unk16_ValAnim2;
	}

	public void setUnk18_ValAnim3(short unk18_ValAnim3) {
		this.unk18_ValAnim3 = unk18_ValAnim3;
	}

	public void setUnk20_Val400(short unk20_Val400) {
		this.unk20_Val400 = unk20_Val400;
	}

	public void setUnk22_Val750Razor0(short unk22_Val750Razor0) {
		this.unk22_Val750Razor0 = unk22_Val750Razor0;
	}

	public void setAiAimOffset(short aiAimOffset) {
		this.aiAimOffset = aiAimOffset;
	}

	public void setTorsoTwistSpeed(int torsoTwistSpeed) {
		this.torsoTwistSpeed = torsoTwistSpeed;
	}

	public void setUnk30_Val1000(short unk30_Val1000) {
		this.unk30_Val1000 = unk30_Val1000;
	}

	public void setTorsoTwistDegreeMax(int torsoTwistDegreeMax) {
		this.torsoTwistDegreeMax = torsoTwistDegreeMax;
	}

	public void setTorsoFlagsInput(short torsoFlagsInput) {
		this.torsoFlagsInput = torsoFlagsInput;
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

	public void setUnk44_MoveAnim(short unk44_MoveAnim) {
		this.unk44_MoveAnim = unk44_MoveAnim;
	}
	
	public void setUnk68_AIAimVal1(short unk68_AIAimVal1) {
		this.unk68_AIAimVal1 = unk68_AIAimVal1;
	}

	public void setUnk70_AIAimVal2(short unk70_AIAimVal2) {
		this.unk70_AIAimVal2 = unk70_AIAimVal2;
	}

	public void setUnk72_AIAimVal3(short unk72_AIAimVal3) {
		this.unk72_AIAimVal3 = unk72_AIAimVal3;
	}

	public void setUnk74_AIAimVal4(short unk74_AIAimVal4) {
		this.unk74_AIAimVal4 = unk74_AIAimVal4;
	}

	public void setUnk76_Val(short unk76_Val) {
		this.unk76_Val = unk76_Val;
	}

	public void setUnk78_ValRazor1(short unk78_ValRazor1) {
		this.unk78_ValRazor1 = unk78_ValRazor1;
	}

	public void setUnk80_ValHudId(short unk80_ValHudId) {
		this.unk80_ValHudId = unk80_ValHudId;
	}

	public void setUnk82_ValUnk(short unk82_ValUnk) {
		this.unk82_ValUnk = unk82_ValUnk;
	}

	public void setUnk84_ValUnk(short unk84_ValUnk) {
		this.unk84_ValUnk = unk84_ValUnk;
	}

	public void setNameBytes(String nameBytes) {
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

	public void setDebrisFile(String debrisFile) {
		this.debrisFile = debrisFile;
	}

	public static short getUnk6_Val30() {
		return unk6_Val30;
	}

	public static int getModelLodArrOFs() {
		return modelLodArrOFs;
	}

	public byte[] getModelLODId() {
		return modelLODId;
	}

	public static short getUnk66_Val1000() {
		return unk66_Val1000;
	}

	public short getUnk68_AIAimVal1() {
		return unk68_AIAimVal1;
	}

	public short getUnk70_AIAimVal2() {
		return unk70_AIAimVal2;
	}

	public short getUnk72_AIAimVal3() {
		return unk72_AIAimVal3;
	}

	public short getUnk74_AIAimVal4() {
		return unk74_AIAimVal4;
	}

	
}
