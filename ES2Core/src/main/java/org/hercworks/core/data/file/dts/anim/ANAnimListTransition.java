package org.hercworks.core.data.file.dts.anim;

public class ANAnimListTransition {

	//unsigned
	private short tick;
	
	//unsigned
	private short destSequence;
	
	//unsigned
	private short destFrame;
	
	//unsigned
	private short groundMovement;
	
	private int index;
	
	public ANAnimListTransition() {}

	public short getTick() {
		return tick;
	}

	public void setTick(short tick) {
		this.tick = tick;
	}

	public short getDestSequence() {
		return destSequence;
	}

	public void setDestSequence(short destSequence) {
		this.destSequence = destSequence;
	}

	public short getDestFrame() {
		return destFrame;
	}

	public void setDestFrame(short destFrame) {
		this.destFrame = destFrame;
	}

	public short getGroundMovement() {
		return groundMovement;
	}

	public void setGroundMovement(short groundMovement) {
		this.groundMovement = groundMovement;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("{ \"class\" : \"").append(getClass().getSimpleName()).append("\",\n");
		str.append("\"index\" : ").append(getIndex()).append(",\n");
		str.append("\"tick\" :").append(getTick()).append(",\n");
		str.append("\"destSequence\" :").append(getDestSequence()).append(",\n");
		str.append("\"destFrame\" :").append(getDestFrame()).append(",\n");
		str.append("\"groundMovement\" :").append(getGroundMovement()).append("\n");
		str.append("}\n");
		
		return str.toString();
	}
}
