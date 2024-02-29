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
 *  82- BLANK BYTE
 *  83- UINT8  - always 4
 *  84- UINT8  - sometimes 0 but mostly 1 
 *  85- BLANK BYTE
 * 
 *  86...97- STRING - UNIT\NAME
 *  
 *  98- UINT16 - ? each herc has a different value.
 *  100- UINT16 - ? each herc has a different value.
 *  102- BLANK BYTES
 *  104- UINT16 - ? each herc has a different value, razor is 0. light herc 1400 | heavy 1600
 *  106- BLANK BYTES
 *  108- UINT16 - a negative value, each herc has a different value
 *  110- UINT16 - all hercs this is 750
 *  
 *  112- BLANK BYTES
 *  
 *  114- UINT16 - all units have -254
 *  116- UINT8  - all units have 255
 *  117- UINT8  - all units have 14
 *  118- UINT8  - all units have 15
 *  119- UINT8  - all units have 12
 *  
 *  120- BLANK BYTES
 *  
 *  122- UINT8  - hercs have 6 | razor has 250
 *  123- UINT8  - hercs have 0 | razor has 255
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

	private short speedTurn;
	private short speedReverse;
	private short speedForward;
	
//	private short unk1;
	
	private short decelTurning;
	
	private short cameraBoneId;
	
//	private short unk2;
	
	private String name;
	
	
	
	
	
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
