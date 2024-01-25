package com.mech.works.data.file.cfg;

import java.util.HashMap;

import com.mech.works.data.ref.files.DataFile;

/**
 * FILE
 * 		[ROOT]/DATA/KEYJOY.CFG
 */
/**
 	[Keyjoy]

	Tilt = Default
	;; Default: cursorUp (keypad 8) tilts turret up
	;; Reverse: cursorUp (keypad 8) tilts turret down
	
	Backturn = Default
	;; Default: cursorDownRight (keypad 3) backs up and turns to left
	;; Reverse: cursorDownRight (keypad 3) backs up and turns to right
	
	Missile = Default
	;; Default: cursorUp (keypad 8) steers missile up
	;; Reverse: cursorUp (keypad 8) steers missile down
	
	Rudder = Default
	;; Default: left pedal forward turns right
	;; Reverse: left pedal forward turns left
	
	 
	 
 */
public class Keyjoy  extends DataFile{
	
	private enum KeyJoyLabel{
		TILT("Tilt"),
		BACKTURN("Backturn"),
		MISSILE("Missile"),
		RUDDER("Rudder");
		
		private String val;
		
		private KeyJoyLabel(String val) {
			this.val =  val;
		}
		
		public String val() {
			return this.val;
		}
	}
	
	public HashMap<KeyJoyLabel, String> values = new HashMap<KeyJoyLabel, String>();
	
	public Keyjoy() {
		super("KEYJOY.CFG", "DATA/");
		values.put(KeyJoyLabel.TILT, "");
		values.put(KeyJoyLabel.BACKTURN, "");
		values.put(KeyJoyLabel.MISSILE, "");
		values.put(KeyJoyLabel.RUDDER, "");
	}
	
	public void setValues(HashMap<KeyJoyLabel, String> vals) {
		this.values = vals;
	}
	
	public HashMap<KeyJoyLabel, String> getValues(){
		return values;
	}
	
	
}
