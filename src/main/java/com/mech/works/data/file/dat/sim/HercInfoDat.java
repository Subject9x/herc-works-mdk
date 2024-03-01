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
 *  44- UINT8  - Move Speed binding to leg animation somehow
 *  45- UINT8  - BLANK?
 *  46- UINT8  - always 1
 *  47- UINT8  - always 2
 *  48- UINT8  - always 3
 *  49- UINT8  - always 4
 *  50- UINT8  - always 5
 *  51- UINT8  - always 12
 *  52- UINT8  - always 13
 *  53- UINT8  - always 14
 *  54- UINT8  - always 15
 *  55- UINT8  - always 16
 *  56- UINT8  - always 17
 *  57- UINT8  - always 255
 *  58-65 - BLANK BYTES
 *  66- UINT16 - always 1000
 *  68- UINT16 - always 7
 *  70- UINT16 - always 2
 *  72- UINT16 - always 2
 *  74- UINT16 - EMPTY BYTES
 *  76- UINT16 - ? - ARMOR val?
 *  78- UINT16 - Hercs have 0, Razor has 1
 *  80- UINT16 - ? - each herc has a different value - POSSIBLY HUD RELATED
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
	
	//blank bytes at 0x45
	
	private static byte unk46_Val01 = (byte)0x01;
	private static byte unk47_Val02 = (byte)0x02;
	private static byte unk48_Val03 = (byte)0x03;
	private static byte unk49_Val04 = (byte)0x04;
	private static byte unk50_Val05 = (byte)0x05;
	private static byte unk51_Val12 = (byte)0x0C;
	private static byte unk52_Val13 = (byte)0x0D;
	private static byte unk53_Val14 = (byte)0x0E;
	private static byte unk54_Val15 = (byte)0x0F;
	private static byte unk55_Val16 = (byte)0x10;
	private static byte unk56_Val17 = (byte)0x11;
	private static byte unk57_Val255 = (byte)0xFF;
	
	//blank bytes at 0x58-0x65
	
	private static short unk66_Val1000;
	private static short unk68_Val7;
	private static short unk70_Val2;
	private static short unk72_Val2;
	
	//blank bytes at 0x74	
	
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

	@Override
	public byte[] transformToByte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bytesToFile() {
		// TODO Auto-generated method stub
		
	}
	
}
