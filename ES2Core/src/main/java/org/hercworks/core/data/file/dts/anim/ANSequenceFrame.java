package org.hercworks.core.data.file.dts.anim;

public class ANSequenceFrame {

	private int index;
	private byte[] data;
	
	//unsigned
	private short tick;
	
	//unsigned
	private short numTransitions;
	
	//unsigned
	private short firstTransition;
	
	public ANSequenceFrame() {}

	public short getTick() {
		return tick;
	}

	public void setTick(short tick) {
		this.tick = tick;
	}

	public short getNumTransitions() {
		return numTransitions;
	}

	public void setNumTransitions(short numTransitions) {
		this.numTransitions = numTransitions;
	}

	public short getFirstTransition() {
		return firstTransition;
	}

	public void setFirstTransition(short firstTransition) {
		this.firstTransition = firstTransition;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("{ \"class\" : \"").append(getClass().getSimpleName()).append("\",\n");
		str.append("\"index\" : ").append(getIndex()).append(",\n");
		str.append("\"tick\" :").append(getTick()).append(",\n");
		str.append("\"numTransitions\" :").append(getNumTransitions()).append(",\n");
		str.append("\"firstTransition\" :").append(getFirstTransition()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
