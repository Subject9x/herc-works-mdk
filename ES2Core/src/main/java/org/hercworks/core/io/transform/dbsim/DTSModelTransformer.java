package org.hercworks.core.io.transform.dbsim;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hercworks.core.data.file.dts.TSBasePart;
import org.hercworks.core.data.file.dts.TSGroup;
import org.hercworks.core.data.file.dts.TSObject;
import org.hercworks.core.data.file.dts.TSObjectHeader;
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

	private String dbgSpace = "";
	private String treeSpace = "	";
	
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
		
		System.out.println("[\n");
		
		List<TSObject> meshes = new ArrayList<TSObject>();
		
		while(index < inputArray.length) {
			meshes.add(loadChunkByType(null));
		}
		System.out.println("{ \"meshes\" : [\n");
		for(int s=0; s < meshes.size(); s++) {
			System.out.println(meshes.iterator().next().toString());
			if(s < meshes.size() - 1) {
				System.out.println(",\n");
			}
		}
		System.out.println("]}");

		dts.setMeshes(meshes);
		return dts;
	}
	
	/**
	 * hacked together analogue for the ChunkTypes[] object in the ol' python script.
	 * DTS do seem to be nested objects and object lists, so a tree loading approach is
	 * necessary.
	 * @param marker
	 * @return
	 */
	private TSObject loadChunkByType(TSObject parent) {
		
		int markerIndex = index;
		
		byte[] marker = Bytes.from(indexSegment(4)).array();
		
		if(parent == null) {
			System.out.println("\n");
			dbgSpace = "";
		}
		else {
			dbgSpace = "";
			TSObject p = parent;
			do {
				dbgSpace += treeSpace;
				p = p.getParent();
			}
			while(p != null);
		}
		
		System.out.println(dbgSpace + TSObjectHeader.findVal(marker) + " @ " + (markerIndex) + " | "
									+ Bytes.from((markerIndex)).encodeHex() + " |"
									+ " len " + Bytes.from(bytes, index, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt());
		
		
		if(Arrays.equals(TSObjectHeader.TS_BASE_PART.val(), marker)) {
			return readTSBasePart(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_PART_LIST.val(), marker)) {
			return readTSPartList(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_SHAPE.val(), marker)) {
			return readTSShape(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.AN_SHAPE.val(), marker)) {
			return readANShape(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.BSP_PART.val(), marker)) {
			return readTSBSPPart(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_GROUP.val(), marker)) {
			return readTSGroup(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_POLY.val(), marker)) {
			return readTSPoly(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_SOLID_POLY.val(), marker)) {
			return readTSShadedPoly(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_SOLID_POLY.val(), marker)) {
			return readTSSolidPoly(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_TEXTURE4_POLY.val(), marker)) {
			return readTSTexture4Poly(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_SHADED_POLY.val(), marker)) {
			return readTSShadedPoly(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_GOURAUD_POLY.val(), marker)) {
			return readTSGouraudPoly(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_CELL_ANIM_PART.val(), marker)) {
			return readTSCellAnimPart(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.AN_ANIM_LIST.val(), marker)) {
			return readANAnimList(parent);
		}
		
		if(Arrays.equals(TSObjectHeader.AN_CYCLIC_SEQUENCE.val(), marker)) {
			return readANCyclicSequence(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.AN_SEQUENCE.val(), marker)) {
			return readANSequence(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_BITMAP_PART.val(), marker)) {
			return readTSBitmapPart(null, parent); 
		}
		
		if(Arrays.equals(TSObjectHeader.TS_DETAIL_PART.val(), marker)) {
			return readTSDetailPart(null, parent);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_BSP_GROUP.val(), marker)) {
			return readTSBSPGroup(null, parent);
		}
		
		System.out.println("->Unknown chunk = " + Arrays.toString(marker));
		int len = indexIntLE();
		System.out.println("--->len = " + len);
		skip(len);
		
		return null;
	}
	
	
	private TSBasePart readTSBasePart(TSBasePart link, TSObject parent) {
		
		if(link == null) {
			link = new TSBasePart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link.setTransform(indexShortLE());
		link.setIDNumber(indexShortLE());
		link.setRadius(indexShortLE());
		link.setCenter(new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE()));

		return link;
	}
	
	private TSBitmapPart readTSBitmapPart(TSBitmapPart link, TSObject parent) {
		
		if(link == null) {
			link = new TSBitmapPart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (TSBitmapPart) readTSBasePart(link, parent);
		
		link.setBmpTag(indexShortLE());
		link.setOfsX(indexByte());
		link.setOfsY(indexByte());
		
		return link;
	}
	
	private TSDetailPart readTSDetailPart(TSDetailPart link, TSObject parent) {
		
		if(link == null) {
			link = new TSDetailPart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}

		link = (TSDetailPart) readTSPartList(link, parent);

		//FIXME - so far these just seem to trail at the end for any remaining bytes of the TSObject's byte-len.
		//	pretty weird.
		int detailCount =  (link.getDataIndex() + link.getByteLen()) - index;
		short[] details = new short[detailCount / 2];
		for(int d=0; d < details.length; d++) {
			details[d] = indexShortLE();
		}

		link.setDetails(details);

		return link;
	}
	
	private TSPartList readTSPartList(TSPartList link, TSObject parent) {
		
		if(link == null) {
			link = new TSPartList();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (TSPartList) readTSBasePart(link, parent);
		
		TSObject[] parts = new TSObject[indexShortLE()];
		
		for(int i = 0; i < parts.length; i++) {
			TSObject part = loadChunkByType(link);
			if(part != null) {
				parts[i] = part;	
			}
		}
		link.setParts(parts);
		return link;
	}
	
	private TSCellAnimPart readTSCellAnimPart(TSCellAnimPart link, TSObject parent) {
		
		if(link == null) {
			link = new TSCellAnimPart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (TSCellAnimPart) readTSPartList(link, parent);
		
		link.setAnimSequence(indexShortLE());
		
		return link;
	}
	

	private TSGroup readTSGroup(TSGroup link, TSObject parent) {
		
		if(link == null) {
			link = new TSGroup();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (TSGroup) readTSBasePart(link, parent);
		
		short[] indexes = new short[indexShortLE()];
		Vec3Short[] points = new Vec3Short[indexShortLE()];
//		TSShapeColor[] colors = new TSShapeColor[indexShortLE()];
		int[] colors = new int[indexShortLE()];
		TSObject[] items = new TSObject[indexShortLE()];
		
		
		for(int i=0; i < indexes.length; i++) {
			indexes[i] = indexShortLE();
		}
		link.setIndexes(indexes);
		
		for(int p=0; p < points.length; p++) {
			points[p] = new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE());
		}
		link.setPoints(points);
		
		for(int c=0; c < colors.length; c++) {
//			colors[c] = new TSShapeColor(indexSegment(4));
			colors[c] = indexIntLE();
		}
		link.setColors(colors);
		
		for(int s=0; s < items.length; s++) {
			items[s] = loadChunkByType(link);
		}
		link.setItems(items);
		
		return link;
	}
	
	private TSBSPGroup readTSBSPGroup(TSBSPGroup link, TSObject parent) {

		if(link == null) {
			link = new TSBSPGroup();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
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
	
	private TSPoly readTSPoly(TSPoly link, TSObject parent) {

		if(link == null) {
			link = new TSPoly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		
		link.setNormal(indexShortLE());
		link.setCenter(indexShortLE());
		link.setVertexCount(indexShortLE());
		link.setVertexList(indexShortLE());

		
		return link;
	}
	
	private TSSolidPoly readTSSolidPoly(TSSolidPoly link, TSObject parent) {
		
		if(link == null) {
			link = new TSSolidPoly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		
		link = (TSSolidPoly) readTSPoly(link, parent);
		link.setColorIndexId(indexShortLE());
		
		return link;
	}
	
	private TSShadedPoly readTSShadedPoly(TSShadedPoly link, TSObject parent) {
		
		if(link == null) {
			link = new TSShadedPoly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (TSShadedPoly) readTSSolidPoly(link, parent);

		return link;
	}
	
	private TSTexture4Poly readTSTexture4Poly(TSTexture4Poly link, TSObject parent) {
		
		if(link == null) {
			link = new TSTexture4Poly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (TSTexture4Poly) readTSSolidPoly(link, parent);

		return link;
	}
	
	private TSGouraudPoly readTSGouraudPoly(TSGouraudPoly link, TSObject parent) {

		if(link == null) {
			link = new TSGouraudPoly();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
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
		node.setCoeff(indexIntLE());
		node.setFront(indexShortLE());
		node.setBack(indexShortLE());
		
		node.setByteLen(index - node.getIndex());
		node.setData(Bytes.from(bytes, node.getIndex(), node.getByteLen()).array());
		
		System.out.println("	BSPPartNode @ " + node.getIndex() + " | " + Bytes.from(node.getIndex()).encodeHex() + " | len" + node.getByteLen());
		
		return node;
	}
	
	private TSBSPPart readTSBSPPart(TSBSPPart link, TSObject parent) {
		
		if(link == null) {
			link = new TSBSPPart();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (TSBSPPart) readTSPartList(link, parent);
		
		TSBSPPartNode[] nodes = new TSBSPPartNode[indexShortLE()];
		for(int n=0; n < nodes.length; n++) {
			nodes[n] = readTSBSPPartNode();
		}
		link.setNodes(nodes);
		
		
		//AHA! I think this count is matched to # of BSPPartNodes in the list!
		short[] transforms = new short[nodes.length];
		for(int p=0; p < nodes.length; p++) {
			transforms[p] = indexShortLE();
		}
		link.setTransforms(transforms);
		
		return link;
	}
	
	private TSShape readTSShape(TSShape link, TSObject parent) {
		
		if(link == null) {
			link = new TSShape();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (TSShape) readTSPartList(link, parent);
		
		int len = 0;
		for(TSObject c : link.getParts()) {
			len += c.getByteLen();
		}
		
		System.out.println("inital byte len= " + link.getByteLen());
		System.out.println("branch byte len= " + len);
		System.out.println("remaining byte len=" + (link.getByteLen() - len));
		
		//66 remaining bytes in ACHILLES.DTS before adding the AnimationSequnce object, and maybe a counter for num of seq.
		// @ 32A0
		
		//14 remaining bytes in ROCKET_SPLIT_1 before the next TS SHAPE (in split 2.dts)
		// @ 982
		short transformTotal = indexShortLE();
		System.out.println("TSSHAPE - transfrm ? " + transformTotal + " @" + Bytes.from(index - 2).encodeHex());	
		
		short sequenceTotal = indexShortLE();
		System.out.println("TSSHAPE - seqeuence ? "+ sequenceTotal + " @" + Bytes.from(index - 2).encodeHex());
		
		short[] sequences = new short[sequenceTotal];
		System.out.println("		value = " + sequences.length);
		for(int s=0; s < sequences.length; s++) {
			sequences[s] = indexShortLE();
		}
		link.setSequenceList(sequences);
		
		short[] transforms = new short[transformTotal];		
		System.out.println("		value = " + transforms.length);
		for(int t=0; t < transforms.length; t++) {
			transforms[t] = indexShortLE();
		}
		link.setTransformList(transforms);
		
//		//XXX - OpenSiege source
//		short[] seq = new short[indexShortLE()];
//		for(int s=0; s < seq.length; s++) {
//			seq[s] = indexShortLE();
//		}
//		link.setSequenceList(seq);
//		
//		for(int t=0; t < transformList.length; t++) {
//			transformList[t] = indexShortLE();
//		}
//		link.setTransformList(transformList);
		
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
//		short[] transformList = new short[indexShortLE()];
//		short[] sequenceList = new short[indexShortLE()];
//
//		for(int s=0; s < sequenceList.length; s++ ) {
//			sequenceList[s] = indexShortLE();
//		}
//		
//		for(int t=0; t < transformList.length; t++ ) {
//			transformList[t] = indexShortLE();
//		}
//		
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
		frame.setFirstTransition(indexShortLE());
		frame.setNumTransitions(indexShortLE());
		
		frame.setByteLen(index - frame.getIndex());
		frame.setData(Bytes.from(bytes, frame.getIndex(), frame.getByteLen()).array());
		
		return frame;
	}
	
	private ANSequence readANSequence(ANSequence seq, TSObject parent) {
		
		if(seq == null) {
			seq = new ANSequence();
			seq.setByteLen(indexIntLE());
			seq.setIndex(index - 8);
			seq.setData(Bytes.from(bytes, seq.getIndex() + 8, seq.getByteLen()).array());
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
		
		int len = (partIds.length) * (frames.length);
		short[] transfrmIndex = new short[len];
		for(int t=0; t < transfrmIndex.length; t++) {
			transfrmIndex[t] = indexShortLE();
		}
		seq.setTransformIndices(transfrmIndex);
		
		return seq;
	}
	
	
	private ANCyclicSequence readANCyclicSequence(ANCyclicSequence link, TSObject parent) {
		
		if(link == null) {
			link = new ANCyclicSequence();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
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
	
	private ANAnimList readANAnimList(TSObject parent) {
		ANAnimList anim = new ANAnimList();
		
		anim.setByteLen(indexIntLE());
		anim.setIndex(index - 8);
		anim.setData(Bytes.from(bytes, anim.getIndex() + 8, anim.getByteLen()).array());
		anim.setParent(parent);
		
		TSObject[] sequences = new TSObject[indexShortLE()];
		for(int s=0; s < sequences.length; s++) {
			sequences[s] = loadChunkByType(anim);
		}
		anim.setSequences(sequences);
		
		ANAnimListTransition[] transitions = new ANAnimListTransition[indexShortLE()];
		for(int t=0; t < transitions.length; t++) {
			transitions[t] = readANAnimListTransition();
		}
		anim.setTransitions(transitions);
		
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
	
	private ANShape readANShape(ANShape link, TSObject parent) {
		
		if(link == null) {
			link = new ANShape();
			link.setByteLen(indexIntLE());
			link.setIndex(index - 8);
			link.setData(Bytes.from(bytes, link.getIndex() + 8, link.getByteLen()).array());
			link.setParent(parent);
		}
		link = (ANShape) readTSShape(link, parent);
		
		ANAnimList animationList = (ANAnimList)loadChunkByType(link);
		link.setAnimationList(animationList);
		
		return link;
	}
	
	
	
	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int calcChildBytesLen(TSObject tso, int len) {
		
		if(tso instanceof TSShape) {
			TSShape s = (TSShape)tso;
			
			for(TSObject c : s.getParts()) {
				len += calcChildBytesLen(c, len);
			}
			return len;
		}
		else {
			return tso.getByteLen() + len;
		}
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		b.append("index @ " + Bytes.from(index).encodeHex());
		
		return b.toString();
	}
}
