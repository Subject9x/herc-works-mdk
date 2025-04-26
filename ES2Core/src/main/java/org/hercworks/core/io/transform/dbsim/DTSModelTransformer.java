package org.hercworks.core.io.transform.dbsim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.hercworks.core.data.file.dts.TSBasePart;
import org.hercworks.core.data.file.dts.TSChunk;
import org.hercworks.core.data.file.dts.TSChunkHeader;
import org.hercworks.core.data.file.dts.TSGroup;
import org.hercworks.core.data.file.dts.TSPoly;
import org.hercworks.core.data.file.dts.TSShape;
import org.hercworks.core.data.file.dts.TSShapeColor;
import org.hercworks.core.data.file.dts.anim.ANAnimList;
import org.hercworks.core.data.file.dts.anim.ANAnimListTransform;
import org.hercworks.core.data.file.dts.anim.ANAnimListTransition;
import org.hercworks.core.data.file.dts.anim.ANCyclicSequence;
import org.hercworks.core.data.file.dts.anim.ANSequence;
import org.hercworks.core.data.file.dts.anim.ANSequenceFrame;
import org.hercworks.core.data.file.dts.anim.ANShape;
import org.hercworks.core.data.file.dts.bsp.TSBSPGroup;
import org.hercworks.core.data.file.dts.bsp.TSBSPGroupNode;
import org.hercworks.core.data.file.dts.bsp.TSBSPPart;
import org.hercworks.core.data.file.dts.bsp.TSBSPPartNode;
import org.hercworks.core.data.file.dts.part.TSBitmapPart;
import org.hercworks.core.data.file.dts.part.TSCellAnimPart;
import org.hercworks.core.data.file.dts.part.TSDetailPart;
import org.hercworks.core.data.file.dts.part.TSPartList;
import org.hercworks.core.data.file.dts.poly.TSGouraudPoly;
import org.hercworks.core.data.file.dts.poly.TSShadedPoly;
import org.hercworks.core.data.file.dts.poly.TSSolidPoly;
import org.hercworks.core.data.file.dts.poly.TSTexture4Poly;
import org.hercworks.core.data.file.dyn.DynamixThreeSpaceModel;
import org.hercworks.core.data.struct.Vec2Short;
import org.hercworks.core.data.struct.Vec3Short;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

import at.favre.lib.bytes.Bytes;

public class DTSModelTransformer extends ThreeSpaceByteTransformer {

	
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - log null
			return null;
		}
		setBytes(inputArray);
		
		DynamixThreeSpaceModel dts = new DynamixThreeSpaceModel();
		dts.setRawBytes(inputArray);
		dts.setExt(FileType.DTS);
		dts.setDir(FileType.DTS);
		
		ArrayList<TSChunk> chunks = new ArrayList<TSChunk>();
		
		System.out.println("[\n");
		
		while(index < inputArray.length) {
			chunks.add(loadChunkByType(null));
		}
		for(int s=0; s < chunks.size(); s++) {
			System.out.println(chunks.get(s).toString());
			if(s < chunks.size()-1) {
				System.out.println(",\n");
			}
		}
		
		
		System.out.println("]");
		
		return null;
	}
	
	private TSBasePart readTSBasePart(TSBasePart link, TSChunk parent) {
		
		if(link == null) {
			link = new TSBasePart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link.setTransform(indexShortLE());
		link.setIDNumber(indexShortLE());
		link.setRadius(indexShortLE());
		link.setCenter(new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE()));

		return link;
	}
	
	private TSBitmapPart readTSBitmapPart(TSBitmapPart link, TSChunk parent) {
		
		if(link == null) {
			link = new TSBitmapPart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSBitmapPart) readTSBasePart(link, parent);
		
		link.setBmpTag(indexShortLE());
		link.setOfsX(indexByte());
		link.setOfsY(indexByte());
		
		return link;
	}
	
	private TSDetailPart readTSDetailPart(TSDetailPart link, TSChunk parent) {
		
		if(link == null) {
			link = new TSDetailPart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}

		link = (TSDetailPart) readTSPartList(link, parent);
		
		short[] details = new short[indexShortLE()];
		
		for(int d=0; d < details.length; d++) {
			details[d] = indexShortLE();
		}

		link.setDetails(details);

		return link;
	}
	
	private TSPartList readTSPartList(TSPartList link, TSChunk parent) {
		
		if(link == null) {
			link = new TSPartList();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSPartList) readTSBasePart(link, parent);
		
		short totalParts = indexShortLE();
		TSChunk[] parts = new TSChunk[totalParts];
		
		for(int i = 0; i < parts.length; i++) {
			TSChunk part = loadChunkByType(link);
			if(part != null) {
				parts[i] = part;	
			}
		}
		link.setParts(parts);
		return link;
	}
	
	private TSCellAnimPart readTSCellAnimPart(TSCellAnimPart link, TSChunk parent) {
		
		if(link == null) {
			link = new TSCellAnimPart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSCellAnimPart) readTSPartList(link, parent);
		
		link.setAnimSequence(indexShortLE());

		
		return link;
	}
	

	private TSGroup readTSGroup(TSGroup link, TSChunk parent) {
		
		if(link == null) {
			link = new TSGroup();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSGroup) readTSBasePart(link, parent);
		
		short[] indexes = new short[indexShortLE()];
		Vec3Short[] points = new Vec3Short[indexShortLE()];
		TSShapeColor[] colors = new TSShapeColor[indexShortLE()];
		TSChunk[] items = new TSChunk[indexShortLE()];
		
		
		for(int i=0; i < indexes.length; i++) {
			indexes[i] = indexShortLE();
		}
		link.setIndexes(indexes);
		
		for(int p=0; p < points.length; p++) {
			points[p] = new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE());
		}
		link.setPoints(points);
		
		for(int c=0; c < colors.length; c++) {
			colors[c] = new TSShapeColor(indexSegment(4));
		}
		link.setColors(colors);
		
		for(int s=0; s < items.length; s++) {
			items[s] = loadChunkByType(link);
		}
		link.setItems(items);
		
		return link;
	}
	
	private TSBSPGroup readTSBSPGroup(TSBSPGroup link, TSChunk parent) {

		if(link == null) {
			link = new TSBSPGroup();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSBSPGroup) readTSGroup(link, parent);

		TSBSPGroupNode[] nodes = new TSBSPGroupNode[indexShortLE()];
		
		for(int n=0; n < nodes.length; n++) {
			nodes[n] = new TSBSPGroupNode(indexShortLE(), 
					indexShortLE(), 
					indexShortLE(), 
					indexShortLE());
		}
		link.setGroupNodes(nodes);
		
		return link;
	}
	
	private TSPoly readTSPoly(TSPoly link, TSChunk parent) {

		
		if(link == null) {
			link = new TSPoly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		
		link.setNormal(indexShortLE());
		link.setCenter(indexShortLE());
		link.setVertexCount(indexShortLE());
		link.setVertexList(indexShortLE());

		
		return link;
	}
	
	private TSSolidPoly readTSSolidPoly(TSSolidPoly link, TSChunk parent) {
		
		if(link == null) {
			link = new TSSolidPoly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSSolidPoly) readTSPoly(link, parent);
		link.setColor(indexShortLE());

		
		return link;
	}
	
	private TSShadedPoly readTSShadedPoly(TSShadedPoly link, TSChunk parent) {
		
		if(link == null) {
			link = new TSShadedPoly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSShadedPoly) readTSSolidPoly(link, parent);

		return link;
	}
	
	private TSTexture4Poly readTSTexture4Poly(TSTexture4Poly link, TSChunk parent) {
		
		if(link == null) {
			link = new TSTexture4Poly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSTexture4Poly) readTSSolidPoly(link, parent);

		return link;
	}
	
	private TSGouraudPoly readTSGouraudPoly(TSGouraudPoly link, TSChunk parent) {

		if(link == null) {
			link = new TSGouraudPoly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSGouraudPoly) readTSSolidPoly(link, parent);
		link.setNormalList(indexShortLE());

		return link;
	}
	
	private TSBSPPartNode readTSBSPPartNode() {
		
		TSBSPPartNode node = new TSBSPPartNode();
		node.setIndex(index);
		
		node.setNormal(new Vec3Short(indexShortLE(),indexShortLE(),indexShortLE()));
		node.setCoeff(indexByte());
		node.setFront(indexByte());
		node.setBack(indexByte());
		
		return node;
	}
	
	private TSBSPPart readTSBSPPart(TSBSPPart link, TSChunk parent) {
		
		if(link == null) {
			link = new TSBSPPart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSBSPPart) readTSPartList(link, parent);
		
		TSBSPPartNode[] nodes = new TSBSPPartNode[indexShortLE()];
		for(int n=0; n < nodes.length; n++) {
			nodes[n] = readTSBSPPartNode();
		}
		link.setNodes(nodes);
		
		short[] transforms = new short[nodes.length];
		for(int t=0; t < transforms.length; t++) {
			transforms[t] = indexShortLE();
		}
		link.setTransforms(transforms);

		
		return link;
	}
	
	private TSShape readTSShape(TSShape link, TSChunk parent) {
		
		if(link == null) {
			link = new TSShape();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (TSShape) readTSPartList(link, parent);

		short[] transformList = new short[indexShortLE()];
		
		//XXX - OpenSiege source
		short[] seq = new short[indexShortLE()];
		for(int s=0; s < seq.length; s++) {
			seq[s] = indexShortLE();
		}
		link.setSequenceList(seq);
		
		for(int t=0; t < transformList.length; t++) {
			transformList[t] = indexShortLE();
		}
		link.setTransformList(transformList);
		
//		int remainSize = index - ((link.getIndex() + 8) + link.getByteLen());
//		int limit = index + remainSize;
//		
//		ArrayList<TSChunk> extraParts = new ArrayList<TSChunk>();
//		do {
//			extraParts.add(loadChunkByType(link));
//		}
//		while(index < limit);
//		
//		link.setExtraParts((TSChunk[])extraParts.toArray());
//		
		
		//XXX - based on older python code
//		short[] transformList = new short[indexByte()];
//		short[] sequenceList = new short[indexByte()];
//
//		for(int s=0; s < sequenceList.length; s++ ) {
//			sequenceList[s] = indexShortLE();
//		}
//		
//		for(int t=0; t < transformList.length; t++ ) {
//			transformList[t] = indexShortLE();
//		}
		
//		int total = s + (t*16);
//		short[] sequence1 = new short[total];
//		for(int seq=0; seq < total; seq++) {
//			sequence1[seq] = indexShortLE();
//		}
//		link.setSeqList1(sequence1);
//		
//		short[] sequence2 = new short[s];
//		Arrays.fill(sequence2, (short)0);
//		
//		link.setSeqList2(sequence2);
		
		//FIXME - transformList seems unused!?

		return link;
	}
	
	private ANSequenceFrame readANSequenceFrame() {
		
		ANSequenceFrame frame = new ANSequenceFrame();
		frame.setIndex(index);
		frame.setTick(indexShortLE());
		frame.setNumTransitions(indexShortLE());
		frame.setFirstTransition(indexShortLE());
		
		return frame;
	}
	
	private ANSequence readANSequence(ANSequence seq, TSChunk parent) {
		
		if(seq == null) {
			seq = new ANSequence();
			seq.setByteLen(indexIntLE());
			seq.setIndex(index - 8);
			seq.setData(Bytes.from(bytes, seq.getIndex(), seq.getByteLen()+8).array());
			seq.setParent(parent);
		}
		
		seq.setTick(indexShortLE());
		seq.setPriority(indexShortLE());
		seq.setGroundMovement(indexShortLE());
		
		ANSequenceFrame[] frames = new ANSequenceFrame[indexShortLE()];
		for(int f=0; f < frames.length; f++) {
			frames[f] = readANSequenceFrame();
		}
		seq.setFrames(frames);
		
		short[] partIds = new short[indexShortLE()];
		for(int p=0; p < partIds.length; p++) {
			partIds[p] = indexShortLE();
		}
		seq.setPartIds(partIds);
		
		int len = (partIds.length - 1) * (frames.length - 1);
		short[] transfrmIndex = new short[len];
		for(int t=0; t < transfrmIndex.length; t++) {
			transfrmIndex[t] = indexShortLE();
		}
		seq.setTransformIndices(transfrmIndex);
		
		return seq;
	}
	
	
	private ANCyclicSequence readANCyclicSequence(ANCyclicSequence link, TSChunk parent) {
		
		if(link == null) {
			link = new ANCyclicSequence();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (ANCyclicSequence) readANSequence(link, parent);

		return link;
	}
	
	private ANAnimListTransition readANAnimListTransition() {
		
		ANAnimListTransition transition = new ANAnimListTransition();

		transition.setIndex(index);
		transition.setTick(indexShortLE());
		transition.setDestSequence(indexShortLE());
		transition.setDestFrame(indexShortLE());
		transition.setGroundMovement(indexShortLE());
		
		return transition;
	}
	
	private ANAnimListTransform readANAnimListTransform() {
		ANAnimListTransform transform = new ANAnimListTransform();
		
		transform.setIndex(index);
		transform.setR(new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE()));
		transform.setT(new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE()));
		
		return transform;
	}
	
	private ANAnimList readANAnimList(TSChunk parent) {
		ANAnimList anim = new ANAnimList();
		
		anim.setByteLen(indexIntLE());
		anim.setIndex(index - 8);
		anim.setParent(parent);
		
		TSPartList[] sequences = new TSPartList[indexShortLE()];
		for(int s=0; s < sequences.length; s++) {
			sequences[s] = readTSPartList(null, anim);
		}
		anim.setSequences(sequences);
		
		ANAnimListTransition[] transitions = new ANAnimListTransition[indexShortLE()];
		for(int t=0; t < transitions.length; t++) {
			transitions[t] = readANAnimListTransition();
		}
		
		ANAnimListTransform[] transforms = new ANAnimListTransform[indexShortLE()];
		for(int v=0; v < transforms.length; v++) {
			transforms[v] = readANAnimListTransform();
		}
		anim.setTransforms(transforms);
		
		short[] defTransforms = new short[indexShortLE()];
		for(int d=0; d < defTransforms.length; d++) {
			defTransforms[d] = indexShortLE();
		}
		anim.setDefaultTransforms(defTransforms);
		
		Vec2Short[] relations = new Vec2Short[indexShortLE()];
		for(int r=0; r < relations.length; r++) {
			relations[r] = new Vec2Short(indexShortLE(), indexShortLE());
		}
		anim.setRelations(relations);
		
		return anim;
	}
	
	private ANShape readANShape(ANShape link, TSChunk parent) {
		
		if(link == null) {
			link = new ANShape();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex(), link.getByteLen()+8).array());
			link.setParent(parent);
		}
		link = (ANShape) readTSShape(link, parent);
		
		link.setPart(readTSPartList(null,link));
		
		return link;
	}
	
	/**
	 * hacked together analogue for the ChunkTypes[] object in the ol' python script.
	 * DTS do seem to be nested objects and object lists, so a tree loading approach is
	 * necessary.
	 * @param marker
	 * @return
	 */
	private TSChunk loadChunkByType(TSChunk parent) {
		
		byte[] marker = Bytes.from(indexSegment(4)).array();
		
		if(Arrays.equals(TSChunkHeader.TS_BASE_PART.val(), marker)) {
			return readTSBasePart(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_PART_LIST.val(), marker)) {
			return readTSPartList(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_SHAPE.val(), marker)) {
			return readTSShape(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.AN_SHAPE.val(), marker)) {
			return readANShape(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.BSP_PART.val(), marker)) {
			return readTSBSPPart(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_GROUP.val(), marker)) {
			return readTSGroup(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_POLY.val(), marker)) {
			return readTSPoly(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_SOLID_POLY.val(), marker)) {
			return readTSShadedPoly(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_SOLID_POLY.val(), marker)) {
			return readTSSolidPoly(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_TEXTURE4_POLY.val(), marker)) {
			return readTSTexture4Poly(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_SHADED_POLY.val(), marker)) {
			return readTSShadedPoly(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_GOURAUD_POLY.val(), marker)) {
			return readTSGouraudPoly(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_CELL_ANIM_PART.val(), marker)) {
			return readTSCellAnimPart(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.AN_ANIM_LIST.val(), marker)) {
			return readANAnimList(parent);
		}
		
		if(Arrays.equals(TSChunkHeader.AN_CYCLIC_SEQUENCE.val(), marker)) {
			return readANCyclicSequence(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.AN_SEQUENCE.val(), marker)) {
			return readANSequence(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_BITMAP_PART.val(), marker)) {
			return readTSBitmapPart(null, parent); 
		}
		
		if(Arrays.equals(TSChunkHeader.TS_DETAIL_PART.val(), marker)) {
			return readTSDetailPart(null, parent);
		}
		
		if(Arrays.equals(TSChunkHeader.TS_BSP_GROUP.val(), marker)) {
			return readTSBSPGroup(null, parent);
		}
		
		System.out.println("->Unknown chunk = " + Arrays.toString(marker));
		int len = indexIntLE();
		System.out.println("--->len = " + len);
		skip(len);
		
		return null;
	}
	
	
	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
