package com.mech.works.data.file.dyn;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import com.mech.works.data.ref.files.DataFile;

import at.favre.lib.bytes.Bytes;

/**
 * FILE
 * 		VOL file, .DPL
 * 		contains palette colors per-game object, some real optimization right there.
 */
public class DynamixPalette extends DataFile {

	public static Bytes header = Bytes.from("0F002800", StandardCharsets.UTF_8);
	
	private int colorCount;
	
	private Set<Color> colors;
	
	public DynamixPalette() {
		super();
		colors = new HashSet<Color>();
	}

	public int getColorCount() {
		return colorCount;
	}

	public void setColorCount(int colorCount) {
		this.colorCount = colorCount;
	}

	public Set<Color> getColors() {
		return colors;
	}

	public void setColors(Set<Color> colors) {
		this.colors = colors;
	}
}
