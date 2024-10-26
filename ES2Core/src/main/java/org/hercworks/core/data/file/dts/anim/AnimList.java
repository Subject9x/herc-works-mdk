package org.hercworks.core.data.file.dts.anim;

import java.util.LinkedList;

import org.hercworks.core.data.file.dts.DTSChunkTypes;
import org.hercworks.core.data.file.dts.DTSObject;
import org.hercworks.core.data.file.dts.DTSSegment;

public class AnimList extends DTSObject implements DTSSegment{

	private LinkedList<Sequence> sequences = new LinkedList<Sequence>();
	private LinkedList<Transition> transitions = new LinkedList<Transition>();
	private LinkedList<Transform> transforms = new LinkedList<Transform>();
	private LinkedList<Short> defaultTransforms = new LinkedList<Short>();
	private LinkedList<Relation> relations = new LinkedList<Relation>();
	
	public AnimList() {}

	public LinkedList<Sequence> getSequences() {
		return sequences;
	}

	public LinkedList<Transition> getTransitions() {
		return transitions;
	}

	public LinkedList<Transform> getTransforms() {
		return transforms;
	}

	public LinkedList<Short> getDefaultTransforms() {
		return defaultTransforms;
	}

	public LinkedList<Relation> getRelations() {
		return relations;
	}

	public void setSequences(LinkedList<Sequence> sequences) {
		this.sequences = sequences;
	}

	public void setTransitions(LinkedList<Transition> transitions) {
		this.transitions = transitions;
	}

	public void setTransforms(LinkedList<Transform> transforms) {
		this.transforms = transforms;
	}

	public void setDefaultTransforms(LinkedList<Short> defaultTransforms) {
		this.defaultTransforms = defaultTransforms;
	}

	public void setRelations(LinkedList<Relation> relations) {
		this.relations = relations;
	}

	@Override
	public DTSChunkTypes getSegType() {
		return DTSChunkTypes.ANIM_LIST;
	}
}
