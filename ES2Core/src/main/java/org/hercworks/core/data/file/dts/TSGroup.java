package org.hercworks.core.data.file.dts;

import java.util.Arrays;

import org.hercworks.core.data.struct.Vec3Short;

public class TSGroup extends TSBasePart {

	private short[] indexes;
	
	private Vec3Short[] points;
	
	private TSShapeColor[] colors;
	
	private TSChunk[] items;
	
	public TSGroup() {
		super(TSChunkHeader.TS_GROUP);
	}
	
	public TSGroup(TSChunkHeader hdr) {
		super(hdr);
	}

	public short[] getIndexes() {
		return indexes;
	}

	public void setIndexes(short[] indexes) {
		this.indexes = indexes;
	}

	public Vec3Short[] getPoints() {
		return points;
	}

	public void setPoints(Vec3Short[] points) {
		this.points = points;
	}

	public TSShapeColor[] getColors() {
		return colors;
	}

	public void setColors(TSShapeColor[] colors) {
		this.colors = colors;
	}

	public TSChunk[] getItems() {
		return items;
	}

	public void setItems(TSChunk[] items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));
		str.append("\"transform\" : ").append(getTransform()).append(",\n");
		str.append("\"IDNumber\" : ").append(getIDNumber()).append(",\n");
		str.append("\"radius\" : ").append(getRadius()).append(",\n");
		str.append("\"center\" : ").append(getCenter().toString()).append(",\n");
		str.append("\"indexes\" : ").append(Arrays.toString(getIndexes())).append(",\n");
		str.append("\"points\" : [\n");
		for(int s=0; s < getPoints().length; s++) {
			str.append(getPoints()[s].toString());
			if(s < getPoints().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"colors\" : [\n");		
		for(int c=0; c < getColors().length; c++) {
			str.append(Arrays.toString(getColors()[c].getRgba()));
			if(c < getColors().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		
		str.append("\"parts\" : [\n");
		for(int s=0; s < getItems().length; s++) {
			str.append(getItems()[s].toString());
			if(s < getItems().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]\n");
		
		str.append("}\n");
		
		return str.toString();
	}
}
