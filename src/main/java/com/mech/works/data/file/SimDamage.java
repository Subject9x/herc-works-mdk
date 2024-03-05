package com.mech.works.data.file;

import com.mech.works.data.ref.files.DataFile;


/**
 * 	FILE
 * 		dmg\\[herc].dat
 * 	Contains armor, critical component HP, and probably more damage-related data for every unit.
 * 	Tied to each unit most likely by the unit's NAME field in the corresponding .DAT file.
 * 
 *  0 - UINT16 - Always 22, might be array size of critical components because...
 *  2 - UINT16 - SERVO\LEG\LEFT - Hitpoints
 *  4 - UINT16 - SERVO\LEG\RIGHT - Hitpoints
 *  6 - UINT16 - SENSOR ARRAY
 *  8 - UINT16 - TARGETING COMPUTER
 *  10 - UINT16 - SHIELD GENERATOR
 *  12 - UINT16 - ENGINE
 *  14 - UINT16 - HYDRAULICS
 *  16 - UINT16 - STABILIZERS
 *  18 - UINT16 - LIFE SUPPORT
 *  20 - 44 - UINT16 - slots for critical components, PITBULL has more components (4 legs and turret vs normal herc setup)
 *  
 *  XX - UINT16 - 32 - always ends with 50, Either this is "PILOT" HP, array terminator, or both.
 *  
 */
public class SimDamage extends DataFile{

}
