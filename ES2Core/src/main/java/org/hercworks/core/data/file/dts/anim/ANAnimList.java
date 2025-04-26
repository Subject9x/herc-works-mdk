package org.hercworks.core.data.file.dts.anim;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSChunk;
import org.hercworks.core.data.file.dts.TSChunkHeader;
import org.hercworks.core.data.file.dts.part.TSPartList;
import org.hercworks.core.data.struct.Vec2Short;

public class ANAnimList extends TSChunk {
	
	private TSPartList[] sequences;
	
	private ANAnimListTransition[] transitions;
	
	private ANAnimListTransform[] transforms;
	
	//FIXME - confirm datatype
	private short[] defaultTransforms;
	
	private Vec2Short[] relations;
	
	public ANAnimList() {
		super(TSChunkHeader.AN_ANIM_LIST);
	}
	
	public ANAnimList(TSChunkHeader hdr) {
		super(hdr);
	}

	public TSPartList[] getSequences() {
		return sequences;
	}

	public void setSequences(TSPartList[] sequences) {
		this.sequences = sequences;
	}

	public ANAnimListTransition[] getTransitions() {
		return transitions;
	}

	public void setTransitions(ANAnimListTransition[] transitions) {
		this.transitions = transitions;
	}

	public ANAnimListTransform[] getTransforms() {
		return transforms;
	}

	public void setTransforms(ANAnimListTransform[] transforms) {
		this.transforms = transforms;
	}

	public short[] getDefaultTransforms() {
		return defaultTransforms;
	}

	public void setDefaultTransforms(short[] defaultTransforms) {
		this.defaultTransforms = defaultTransforms;
	}

	public Vec2Short[] getRelations() {
		return relations;
	}

	public void setRelations(Vec2Short[] relations) {
		this.relations = relations;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(metaInfoString(getClass().getSimpleName()));

		str.append("\"sequences\" : [\n");
		for(int s=0; s < getSequences().length; s++) {
			str.append(getSequences()[s].toString());
			if(s < getSequences().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"transitions\" : [\n");
		for(int s=0; s < getTransitions().length; s++) {
			str.append(getTransitions()[s].toString());
			if(s < getSequences().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"transforms\" : [\n");
		for(int s=0; s < getTransforms().length; s++) {
			str.append(getTransforms()[s].toString());
			if(s < getSequences().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"defTransforms\" : ").append(Arrays.toString(getDefaultTransforms())).append(",\n");
		
		str.append("\"relations\" : [\n");

		for(int s=0; s < getRelations().length; s++) {
			str.append(getRelations()[s].toString());
			if(s < getRelations().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]\n");
		
		str.append("}\n");
		
		return str.toString();
	}
}
