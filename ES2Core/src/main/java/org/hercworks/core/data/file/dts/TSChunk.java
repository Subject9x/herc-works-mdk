package org.hercworks.core.data.file.dts;

/**
 * 
 */
public abstract class TSChunk {

	private TSChunkHeader header = null;
	private int byteLen;
	private int index = 0;
	private TSChunk parent = null;
	private byte[] data;
	
	public TSChunk() {
	}
	
	public TSChunk(TSChunkHeader hdr) {
		this.header = hdr;
	}
		
	public TSChunkHeader getHeader() {
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

	public TSChunk getParent() {
		return parent;
	}

	public void setParent(TSChunk parent) {
		this.parent = parent;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String metaInfoString(String chunkName) {
		StringBuilder str = new StringBuilder();
		
		str.append("{ \n\"class\" : \"").append(chunkName).append("\",\n");
		str.append("\"index\" : ").append(getIndex()).append(",\n");
//		str.append("\"hdr\" : ").append(Arrays.toString(getHeader().val())).append(",\n");
		str.append("\"len\" : ").append(String.valueOf(getByteLen())).append(",\n");
		
		return str.toString();
	}
}
