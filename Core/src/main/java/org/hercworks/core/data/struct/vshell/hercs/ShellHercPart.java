package org.hercworks.core.data.struct.vshell.hercs;

import java.util.Arrays;

import org.hercworks.core.data.file.sav.PlayerSave;

/**
 * As observed in {@linkplain PlayerSave} files, and possible .MEC files.
 * The values stored are not sequential - example
 * 	Left and right torsos list their Front-Armor value first, then rear armor:
 * 		Left Torso front
 *      Right Torso front
 *      Left Torso rear
 *      Right Torso rear
 * 
 * likewise, internal components only have a single HP value for the entire component,
 * 	legs EACH have 3 values.
 */
public class ShellHercPart {

	private short id;
	private String label;
	private short[] values = new short[4];
	
	public ShellHercPart() {}
	
	public ShellHercPart(short id, String label) {
		this.id = id;
		this.label = label;
	}

	public short getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public void setId(short id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public short[] getValues() {
		return values;
	}

	public void setValues(short[] values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "ShellHercPart [id=" + id + ", label=" + label + ", values=" + Arrays.toString(values) + "]";
	}
}
