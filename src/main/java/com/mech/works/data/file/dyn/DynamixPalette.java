package com.mech.works.data.file.dyn;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mech.works.data.ref.files.DataFile;
import com.mech.works.data.struct.ColorBytes;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DPL
 * 		contains palette colors per-game object, some real optimization right there.
 */
public class DynamixPalette extends DataFile {

	public static Bytes header = Bytes.from("0F002800", StandardCharsets.UTF_8);
	
	private int colorCount;
	
	private Bytes rawIndexBytes;
	private LinkedHashMap<String, ColorBytes> colors;
	
	public DynamixPalette() {
		super();
		colors = new LinkedHashMap<String, ColorBytes>();
	}

	/**
	 * 
	 * @return
	 */
	public int[] toColorMap() {
		
		int[] cmap = new int[256];
		int i = 0;
		for(String shade : getColors().keySet()) {
			cmap[i] =  getColors().get(shade).getColor().getRGB();
			i++;
		}
//		int grayIncr = 256/(256-i);
//		
//		int gray = grayIncr*8;
//		for(; i< 256; i++) {
//            cmap[i] = (gray<<16)|(gray<<8)|gray;
//            gray += grayIncr;
//		}
		
		return cmap;
	}
	
	public byte[] toByteArray() {
		Bytes index = Bytes.allocate(0);
		
		for(String shade : getColors().keySet()) {
			index = index.append(Bytes.from(getColors().get(shade).getArray(), 0, 3).array());
		}
		return index.array();
	}
	
	public int getColorCount() {
		return colorCount;
	}

	public void setColorCount(int colorCount) {
		this.colorCount = colorCount;
	}

	public Map<String, ColorBytes> getColors() {
		return colors;
	}

	public void setColors(LinkedHashMap<String, ColorBytes> colors) {
		this.colors = colors;
	}

	public Bytes getRawIndexBytes() {
		return rawIndexBytes;
	}

	public void setRawIndexBytes(Bytes rawIndexBytes) {
		this.rawIndexBytes = rawIndexBytes;
	}
}
