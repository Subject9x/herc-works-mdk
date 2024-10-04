package org.hercworks.core.data.file.dts;

import java.util.Vector;

public class ANAnimList {

	private TSPartList[] sequences;
	private ANAnimListTransition[] transitions;
	private ANAnimListTransform[] transforms;
	private short[] defaultTransforms;
	private Vector<Short>[] relations;
	
	public ANAnimList() {}

	public TSPartList[] getSequences() {
		return sequences;
	}

	public ANAnimListTransition[] getTransitions() {
		return transitions;
	}

	public ANAnimListTransform[] getTransforms() {
		return transforms;
	}

	public short[] getDefaultTransforms() {
		return defaultTransforms;
	}

	public Vector<Short>[] getRelations() {
		return relations;
	}

	public void setSequences(TSPartList[] sequences) {
		this.sequences = sequences;
	}

	public void setTransitions(ANAnimListTransition[] transitions) {
		this.transitions = transitions;
	}

	public void setTransforms(ANAnimListTransform[] transforms) {
		this.transforms = transforms;
	}

	public void setDefaultTransforms(short[] defaultTransforms) {
		this.defaultTransforms = defaultTransforms;
	}

	public void setRelations(Vector<Short>[] relations) {
		this.relations = relations;
	}
}
