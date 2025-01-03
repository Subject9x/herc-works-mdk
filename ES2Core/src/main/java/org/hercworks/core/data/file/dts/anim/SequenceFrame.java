package org.hercworks.core.data.file.dts.anim;

public class SequenceFrame {

	private short tic = 0;
	private short numTransitions = 0;
	private short firstTransition = 0;
	
	public SequenceFrame() {}

	public short getTic() {
		return tic;
	}

	public short getNumTransitions() {
		return numTransitions;
	}

	public short getFirstTransition() {
		return firstTransition;
	}

	public void setTic(short tic) {
		this.tic = tic;
	}

	public void setNumTransitions(short numTransitions) {
		this.numTransitions = numTransitions;
	}

	public void setFirstTransition(short firstTransition) {
		this.firstTransition = firstTransition;
	}
	
}
