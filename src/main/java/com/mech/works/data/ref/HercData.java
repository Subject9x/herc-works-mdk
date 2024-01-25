package com.mech.works.data.ref;

import at.favre.lib.bytes.Bytes;

public class HercData {
    
	private Bytes unk1; //- all units hvae this

	private Bytes speedReverse;
	private Bytes speedForward;

	private Bytes unk2;
	private Bytes unk3;

	private Bytes cameraBone;

	private Bytes constVal01;
	  //any other value allows turning WHEN full reveresed but 0 movement in any direction, and no animation plays.

	private Bytes throttle;
	private Bytes throttleReverse;
	private Bytes pitch;

	private Bytes hvyflag; //something colossus has but samson and thawk dont, set to 400
	
	
	
	public HercData() {}
	
	
}
