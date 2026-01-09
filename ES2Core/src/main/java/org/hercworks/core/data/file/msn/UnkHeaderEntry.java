package org.hercworks.core.data.file.msn;

/**
 * 14 bytes per entry
 * observed at the top of the MSN file,
 * 	there's a counter for how many,
 * and each has an iterator id.
 * 
 */
public class UnkHeaderEntry {

	private short indexId;
	
	private short startFrameIndexId;
	
	private short unkValue1;
	
	private short frameStartTime;
	
	private short frameEndTime;
	
	private short totalTime;
	
	private short unkValue2;
	
	
	public UnkHeaderEntry() {}
	
	public UnkHeaderEntry(short indexId, short startFrameIndexId, short v1, short frameStartTime, short frameEndTime, short totalTime, short unkValue2) {
		this.indexId = indexId;
		this.startFrameIndexId = startFrameIndexId;
		this.unkValue1 = v1;
		this.frameStartTime = frameStartTime;
		this.frameEndTime = frameEndTime;
		this.totalTime = totalTime;
		this.unkValue2 = unkValue2;
	}


	
	
	public short getIndexId() {
		return indexId;
	}

	public void setIndexId(short indexId) {
		this.indexId = indexId;
	}

	public short getStartFrameIndexId() {
		return startFrameIndexId;
	}

	public void setStartFrameIndexId(short startFrameIndexId) {
		this.startFrameIndexId = startFrameIndexId;
	}

	public short getUnkValue1() {
		return unkValue1;
	}

	public void setUnkValue1(short unkValue1) {
		this.unkValue1 = unkValue1;
	}

	public short getFrameStartTime() {
		return frameStartTime;
	}

	public void setFrameStartTime(short frameStartTime) {
		this.frameStartTime = frameStartTime;
	}

	public short getFrameEndTime() {
		return frameEndTime;
	}

	public void setFrameEndTime(short frameEndTime) {
		this.frameEndTime = frameEndTime;
	}

	public short getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(short totalTime) {
		this.totalTime = totalTime;
	}

	public short getUnkValue2() {
		return unkValue2;
	}

	public void setUnkValue2(short unkValue2) {
		this.unkValue2 = unkValue2;
	}

	@Override
	public String toString() {
		StringBuilder b =new StringBuilder();
		b.append("[")
			.append(getIndexId()).append(", ")
			.append(getStartFrameIndexId()).append(", ")
			.append(getUnkValue1()).append(", ")
			.append(getFrameStartTime()).append(", ")
			.append(getFrameEndTime()).append(", ")
			.append(getTotalTime()).append(", ")
			.append(getUnkValue2())
			.append("]");
		
		return b.toString();
	}
	
}
