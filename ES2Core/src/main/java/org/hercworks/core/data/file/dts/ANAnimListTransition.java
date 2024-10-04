package org.hercworks.core.data.file.dts;

public class ANAnimListTransition {

	private short tic = 0;
	private short destSequence = 0;
	private short destFrame = 0;
	private short groundMovement = 0;
	
	public ANAnimListTransition() {}

	public short getTic() {
		return tic;
	}

	public short getDestSequence() {
		return destSequence;
	}

	public short getDestFrame() {
		return destFrame;
	}

	public short getGroundMovement() {
		return groundMovement;
	}

	public void setTic(short tic) {
		this.tic = tic;
	}

	public void setDestSequence(short destSequence) {
		this.destSequence = destSequence;
	}

	public void setDestFrame(short destFrame) {
		this.destFrame = destFrame;
	}

	public void setGroundMovement(short groundMovement) {
		this.groundMovement = groundMovement;
	}
}
