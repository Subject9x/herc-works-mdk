package org.hercworks.core.data.file.cfg;

import java.util.HashMap;

import org.hercworks.voln.DataFile;

/**
 * FILE
 * 	[ROOT]/DATA/SOUND.CFG
 * 
 */

/**
[Sound]

Driver = MME
;; MME:           Initialize sound using Multimedia drivers.
;; DirectSound:   Initialize sound using DirectSound drivers.
;; MME is generally faster. DirectSound is generally more responsive.
;; Default value: MME

Buffers = 5
;; This only applies to MME initialization (above).
;; Set this value higher if sounds break up.
;; Set this value lower if sounds are delayed.
;; Value must be in 1 - 64 range.
;; Default value: 5

Rate = 22
;; Set this value to 11, if necessary.
;; Default value: 22

Width = Stereo
;; Set this to Mono, if necessary.
;; Default value: Stereo


 */
public class SoundCfg extends DataFile{

	private enum SoundCfgLabel{
		
		DRIVER("Driver"),
		BUFFERS("Buffers"),
		RATE("Rate"),
		WIDTH("Width");
		
		private String val;
		
		private SoundCfgLabel(String v) {
			this.val = v;
		}
		
		public String val() {
			return val;
		}
	}
	
	private HashMap<SoundCfgLabel, String> values = new HashMap<SoundCfg.SoundCfgLabel, String>();
	
	public SoundCfg() {
		super("SOUND.CFG", "DATA/");
		values.put(SoundCfgLabel.DRIVER, "");
		values.put(SoundCfgLabel.BUFFERS, "");
		values.put(SoundCfgLabel.RATE, "");
		values.put(SoundCfgLabel.WIDTH, "");
	}
	
	public HashMap<SoundCfgLabel, String> getValues(){
		return this.values;
	}
}
