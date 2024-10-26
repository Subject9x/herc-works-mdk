package org.hercworks.core.data.file.dts;

import java.util.LinkedList;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.hercworks.core.data.struct.ColorBytes;

public class Group extends BasePart implements DTSSegment {

	private LinkedList<Short> indexes = new LinkedList<Short>();
	private LinkedList<Vector3D> points = new LinkedList<Vector3D>();
	private LinkedList<ColorBytes> colors = new LinkedList<ColorBytes>();
	private LinkedList<DTSObject> items = new LinkedList<DTSObject>();
	
	public Group() {
	}

	public LinkedList<Short> getIndexes() {
		return indexes;
	}

	public LinkedList<Vector3D> getPoints() {
		return points;
	}

	public LinkedList<ColorBytes> getColors() {
		return colors;
	}

	public LinkedList<DTSObject> getItems() {
		return items;
	}

	public void setIndexes(LinkedList<Short> indexes) {
		this.indexes = indexes;
	}

	public void setPoints(LinkedList<Vector3D> points) {
		this.points = points;
	}

	public void setColors(LinkedList<ColorBytes> colors) {
		this.colors = colors;
	}

	public void setItems(LinkedList<DTSObject> items) {
		this.items = items;
	}
	
	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.GROUP;
	}
}
