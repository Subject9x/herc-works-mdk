package org.hercworks.core.data.file.dts.anim;

import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSObject;
import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.struct.Vec2Short;

public class ANAnimList extends TSObject {
	
	private TSObject[] sequences;
	
	private ANAnimListTransition[] transitions;
	
	private ANAnimListTransform[] transforms;
	
	//FIXME - confirm datatype
	private short[] defaultTransforms;
	
	private Vec2Short[] relations;
	
	public ANAnimList() {
		super(TSObjectHeader.AN_ANIM_LIST);
	}
	
	public ANAnimList(TSObjectHeader hdr) {
		super(hdr);
	}

	public TSObject[] getSequences() {
		return sequences;
	}

	public void setSequences(TSObject[] sequences) {
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

		str = jsonString(str);

		str.append("\n");
		str.append("}\n");
		
		return str.toString();
	}
	
	@Override
	public StringBuilder jsonString(StringBuilder str) {
		
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
		for(int t=0; t < getTransitions().length; t++) {
			str.append(getTransitions()[t].toString());
			if(t < getTransitions().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"transforms\" : [\n");
		for(int trs=0; trs < getTransforms().length; trs++) {
			str.append(getTransforms()[trs].toString());
			if(trs < getTransforms().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("],\n");
		
		str.append("\"defTransforms\" : ").append(Arrays.toString(getDefaultTransforms())).append(",\n");
		
		str.append("\"relations\" : [\n");

		for(int r=0; r < getRelations().length; r++) {
			str.append(getRelations()[r].toString());
			if(r < getRelations().length - 1) {
				str.append(",");
			}
			str.append("\n");
		}
		str.append("]");
		
		return str;
	}
}
