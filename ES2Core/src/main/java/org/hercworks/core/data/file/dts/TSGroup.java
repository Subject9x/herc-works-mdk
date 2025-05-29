package org.hercworks.core.data.file.dts;

import java.util.Arrays;

import org.hercworks.core.data.struct.Vec3Short;

public class TSGroup extends TSBasePart {

	private short[] indexes;
	
	private Vec3Short[] points;
	
	private TSSurfaceEntry[] surfaces;
	
	private TSObject[] polys;
	
	public TSGroup() {
		super(TSObjectHeader.TS_GROUP);
	}
	
	public TSGroup(TSObjectHeader hdr) {
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

	public TSSurfaceEntry[] getSurfaces() {
		return surfaces;
	}

	public void setSurfaces(TSSurfaceEntry[] colors) {
		this.surfaces = colors;
	}

	public TSObject[] getPolys() {
		return polys;
	}

	public void setPolys(TSObject[] items) {
		this.polys = items;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName() + "_" + getListIndex()));
		
		str = jsonString(str);
		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}
	
	@Override
	public StringBuilder jsonString(StringBuilder str) {

		str = super.jsonString(str);
		
		str.append(",\n");
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
		
		str.append("\"surfaces\" : [\n");		
		for(int c=0; c < getSurfaces().length; c++) {
			
			str.append(getSurfaces()[c].toString());
			
			if(c < getSurfaces().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
//		str.append("\"colors\" : ").append(Arrays.toString(getColors())).append(",\n");
		
		
		str.append("\"polys\" : [\n");
		for(int s=0; s < getPolys().length; s++) {
			str.append(getPolys()[s].toString());
			if(s < getPolys().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]");
		
		return str;
	}
}
