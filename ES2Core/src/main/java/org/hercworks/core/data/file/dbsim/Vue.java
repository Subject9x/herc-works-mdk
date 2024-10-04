package org.hercworks.core.data.file.dbsim;

/**
 * 	FILE
 * 		/SIMVOL0/VUE/(herc).VUE
 * 
 * 	This mostly just defines the 3D viewport sizes and offsets for each player herc.
 *  
 * 
 * 0- UINT16 - some kind of counter
 * 2- UINT16 - must be 0, don't touch
 * 4- UINT16 - Projection offset angle
 * 6- UINT16 - must be 0, positive numbers cause crash, negative number affects UI facing angle?
 * 8- UINT16 - 3D view starting pitch offset
 * 10- UINT16 - must be 0, unknown
 * 12- INT32 - 3D HUD Viewport Width Max
 * 16- INT32 - 3D HUD viewport Height max;
 * 20- INT32 - 3D HUD centerpoint x? 
 * 24- INT32 - 3D HUD viewport pitch offset
 * 
 * todo - finish
 */
public class Vue {

	
	private short unk0_counter;
	
	private static short unk2_val = (short)0;
	
	private short viewProjectonOfsAngl;
	
	private short unk6_val = (short)0;
	
	private short initialViewPitchOfs;
	
	private short unk10_val;
	
	private short viewportWidthMax;
	
	private short viewportHeightMax;
	
	
	
	
	
	
}
