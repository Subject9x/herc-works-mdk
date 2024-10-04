package org.hercworks.core.data.file.dts;

import java.util.Vector;

import at.favre.lib.bytes.Bytes;

public class TSGroup extends TSBasePart implements DTSSegment {

	private short[] indexes;
	private Vector<Short>[] points;
	private int[] colors;
	private TSPartList[] items;
	
	public TSGroup() {}

	public short[] getIndexes() {
		return indexes;
	}

	public Vector<Short>[] getPoints() {
		return points;
	}

	public int[] getColors() {
		return colors;
	}

	public TSPartList[] getItems() {
		return items;
	}

	public void setIndexes(short[] indexes) {
		this.indexes = indexes;
	}

	public void setPoints(Vector<Short>[] points) {
		this.points = points;
	}

	public void setColors(int[] colors) {
		this.colors = colors;
	}

	public void setItems(TSPartList[] items) {
		this.items = items;
	}
	
	@Override
	public Bytes getSegmentType() {
		return Bytes.from("0x140014".toCharArray());
	}
}
