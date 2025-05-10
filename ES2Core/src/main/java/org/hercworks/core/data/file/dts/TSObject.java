package org.hercworks.core.data.file.dts;

/**
 * Root ThreeSpace2 3D object, most of the DTS segments inherit from a base type.
 * 
 * DTS files are a tree data structure.
 */
public abstract class TSObject {

	private TSObjectHeader header = null;
	private int byteLen;
	private int index = 0;
	private TSObject parent = null;
	private byte[] data;
	
	public TSObject() {
	}
	
	public TSObject(TSObjectHeader hdr) {
		this.header = hdr;
	}
		
	public TSObjectHeader getHeader() {
		return this.header;
	}

	public int getByteLen() {
		return byteLen;
	}

	public void setByteLen(int byteLen) {
		this.byteLen = byteLen;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public TSObject getParent() {
		return parent;
	}

	public void setParent(TSObject parent) {
		this.parent = parent;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	public int getDataIndex() {
		return this.index + 8;
	}

	public String metaInfoString(String chunkName) {
		StringBuilder str = new StringBuilder();
		
		str.append("{ \n\"class\" : \"").append(chunkName).append("\",\n");
		str.append("\"index\" : ").append(getIndex()).append(",\n");
//		str.append("\"hdr\" : ").append(Arrays.toString(getHeader().val())).append(",\n");
		str.append("\"len\" : ").append(String.valueOf(getByteLen())).append(",\n");
		
		return str.toString();
	}
	
	public abstract StringBuilder jsonString(StringBuilder str);
}
