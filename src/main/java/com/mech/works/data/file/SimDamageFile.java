package com.mech.works.data.file;

import com.mech.works.data.ref.files.DataFile;


/**
 * 	FILE
 * 		dmg\\[herc].DMG
 * 	Contains armor, critical component HP, and probably more damage-related data for every unit.
 * 	Tied to each unit most likely by the unit's NAME field in the corresponding .DAT file.
 *	NOTE - the following are an array of values, the Skimmer only has 1 crit
 *  
 *  0- UINT16 - Always 22, might be array size of critical components because...
 *  2- UINT16 - SERVO\LEG\LEFT - Hitpoints
 *  4- UINT16 - SERVO\LEG\RIGHT - Hitpoints
 *  6- UINT16 - SENSOR ARRAY
 *  8- UINT16 - TARGETING COMPUTER
 *  10- UINT16 - SHIELD GENERATOR
 *  12- UINT16 - ENGINE
 *  14- UINT16 - HYDRAULICS
 *  16- UINT16 - STABILIZERS
 *  18- UINT16 - LIFE SUPPORT
 *  
 *  20- 44 - UINT16 - slots for critical components, PITBULL has more components (4 legs and turret vs normal herc setup)
 *  
 *  XX- UINT16 - 32 - always ends with 50, Either this is "PILOT" HP, array terminator, or both.
 *  
 *  46- UINT16 - ? - Hercs have 29, setting to 1 crashes game
 *  48- UINT16 - ARMOR\COCKPIT\FRONT
 *  50- UINT16 - ARMOR\COCKPIT\FRONT\SPILLOVER
 *  
 *  80- UINT16 - ARMOR\COCKPIT\REAR
 *  82- UINT16 - ARMOR\COCKPIT\REAR\SPILLOVER
 *  
 *  108- UINT16 - ARMOR\
 *  110- UINT16 - ARMOR\ \
 *  
 *  124- UINT16 - ARMOR\
 *  126- UINT16 - ARMOR\
 *  
 *  136- UINT16 - ARMOR\
 *  138- UINT16 - ARMOR\ \
 *  
 *  
 */
public class SimDamageFile extends DataFile{

}
