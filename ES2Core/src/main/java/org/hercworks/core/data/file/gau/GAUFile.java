package org.hercworks.core.data.file.gau;

import java.awt.Point;

/**
 * 	FILE
 * 		/SIMVOL0/GAU/(herc).GAU
 * 
 * Appears to be the GUI config file for HUDS.
 * 
 * 0- PANEL\ROOT 
 * 16- UINT32 - hud weapon list total
 * 20- Array
 * 		BUTTON\WEAPON\LIST_ITEM 1 to 10
 * 		"Null" weapons are set to "64 00 00 00 8C 00 00 00 9B 00 00 00 92 00 00 00"
 * 
 * 180-467(287) Null data, consistent across all herc.gau files
 * 
 * 468- PANEL\
 * 	484- BUTTON\WEAPON\CHAIN Select  4x UINT32
 * 	500- BUTTON\WEAPON\LINK 4x UINT32
 * 	516- BUTTON\AUTOTRACK\TOGGLE 4x UINT32
 * 	532- NULL WIDGET ENTRY?
 * 	548- NULL WIDGET ENTRY?
 * 	564- METER\ENERGY
 * 	580- NULL WIDGET ENTRY?
 * 	596- NULL WIDGET ENTRY?
 * 612- NULL WIDGET ENTRY?
 * 628- UINT32 - spacer?
 * 632- unknown widget
 * 648- unknown widget
 * 664- LABEL\SHIELD\FRONT\VAL 
 * 680- LABEL\SHIELD\REAR\VAL
 * 
 * empty bytes
 * 
 * 952- PANEL\MFD
 * 968- null / empty widget
 * 984- null / empty widget
 * 1016- SLIDER\THROTTLE\
 *  1032- unknown widget
 *  1048- unknown widget
 *  1064- SLIDER\THROTTLE\SLIDE_DIR
 * 1080- UINT32 spacer
 * 1084- UINT32 spacer
 * 1088- PANEL\NAVBAR
 * 	1104- INDICATOR\TORSO_TWIST
 *  1120- unknown widget
 * 
 * 1136- RETICLE
 *  
 * 1152- unknown widget
 * 
 * 1168-
 */
public class GAUFile {

	private Point hudOrigin;
	private Point hudScreenSize;
	
	private HPanel rootPanel;
	private HWeaponPanelItem[] weapons;
	
}
