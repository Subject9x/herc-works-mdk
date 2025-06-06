package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hercworks.core.data.file.dts.TSBasePart;
import org.hercworks.core.data.file.dts.TSGroup;
import org.hercworks.core.data.file.dts.TSObject;
import org.hercworks.core.data.file.dts.TSObjectHeader;
import org.hercworks.core.data.file.dts.TSPoly;
import org.hercworks.core.data.file.dts.TSShape;
import org.hercworks.core.data.file.dts.TSSurfaceEntry;
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
	
	private int indexTSGroup = 0;
	
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			//TODO - log null
			return null;
		}
		indexTSGroup = 0;
		setBytes(inputArray);
		
		DynamixThreeSpaceModel dts = new DynamixThreeSpaceModel();
		dts.setRawBytes(inputArray);
		dts.setExt(FileType.DTS);
		dts.setDir(FileType.DTS);
		
//		System.out.println("[\n");
		
		List<TSObject> meshes = new ArrayList<TSObject>();
		
		while(index < inputArray.length) {
			meshes.add(loadChunkByType(null));
		}

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
		
//		int markerIndex = index;
		
		byte[] marker = Bytes.from(indexSegment(4)).array();
		
//		if(parent == null) {
//			System.out.println("\n");
//			dbgSpace = "";
//		}
//		else {
//			dbgSpace = "";
//			TSObject p = parent;
//			do {
//				dbgSpace += treeSpace;
//				p = p.getParent();
//			}
//			while(p != null);
//		}
//		
//		System.out.println(dbgSpace + TSObjectHeader.findVal(marker) + " @ " + (markerIndex) + " | "
//									+ Bytes.from((markerIndex)).encodeHex() + " |"
//									+ " len " + Bytes.from(bytes, index, 4).byteOrder(ByteOrder.LITTLE_ENDIAN).toInt());
		
		
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
		
//		System.out.println("->Unknown chunk = " + Arrays.toString(marker));
		int len = indexIntLE();
//		System.out.println("--->len = " + len);
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
		link.setIdNumber(indexShortLE());
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
			link.setListIndex(indexTSGroup);
			indexTSGroup += 1;
		}
		link = (TSGroup) readTSBasePart(link, parent);
		
		short[] indexes = new short[indexShortLE()];
		Vec3Short[] points = new Vec3Short[indexShortLE()];
		int colorCount = indexShortLE();
		colorCount = colorCount / 4;
		TSObject[] items = new TSObject[indexShortLE()];
		
		
		for(int i=0; i < indexes.length; i++) {
			indexes[i] = indexShortLE();
		}
		link.setIndexes(indexes);
		
		for(int p=0; p < points.length; p++) {
			points[p] = new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE());
		}
		link.setPoints(points);
		

		TSSurfaceEntry[] surfaces = new TSSurfaceEntry[colorCount];
		for(int c=0; c < surfaces.length; c++) {
			
			TSSurfaceEntry sclr = new TSSurfaceEntry();
			sclr.setFrontColor(indexShortLE());
			sclr.setFrontFlag(indexShortLE());
			sclr.setFrontLineColor(indexShortLE());
			sclr.setFrontLineFlag(indexShortLE());
			sclr.setBackColor(indexShortLE());
			sclr.setBackColorFlag(indexShortLE());
			sclr.setBackLineColor(indexShortLE());
			sclr.setBackLineFlag(indexShortLE());
			
			surfaces[c] = sclr;
		}
		link.setSurfaces(surfaces);
		
		for(int s=0; s < items.length; s++) {
			items[s] = loadChunkByType(link);
		}
		link.setPolys(items);
		
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
		
//		System.out.println("	BSPPartNode @ " + node.getIndex() + " | " + Bytes.from(node.getIndex()).encodeHex() + " | len" + node.getByteLen());
		
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
		
//		int len = 0;
//		for(TSObject c : link.getParts()) {
//			len += c.getByteLen();
//		}
		
//		System.out.println("inital byte len= " + link.getByteLen());
//		System.out.println("branch byte len= " + len);
//		System.out.println("remaining byte len=" + (link.getByteLen() - len));
		
		//66 remaining bytes in ACHILLES.DTS before adding the AnimationSequnce object, and maybe a counter for num of seq.
		// @ 32A0
		
		//14 remaining bytes in ROCKET_SPLIT_1 before the next TS SHAPE (in split 2.dts)
		// @ 982
		short transformTotal = indexShortLE();
//		System.out.println("TSSHAPE - transfrm ? " + transformTotal + " @" + Bytes.from(index - 2).encodeHex());	
		
		short sequenceTotal = indexShortLE();
//		System.out.println("TSSHAPE - seqeuence ? "+ sequenceTotal + " @" + Bytes.from(index - 2).encodeHex());
		
		short[] sequences = new short[sequenceTotal];
//		System.out.println("		value = " + sequences.length);
		for(int s=0; s < sequences.length; s++) {
			sequences[s] = indexShortLE();
		}
		link.setSequenceList(sequences);
		
		short[] transforms = new short[transformTotal];		
//		System.out.println("		value = " + transforms.length);
		for(int t=0; t < transforms.length; t++) {
			transforms[t] = indexShortLE();
		}
		link.setTransformList(transforms);
		
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
		transform.setRotation(new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE()));
		transform.setTranslation(new Vec3Short(indexShortLE(), indexShortLE(), indexShortLE()));
		
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

		if(source == null) {
			return null;
		}
		
		DynamixThreeSpaceModel mdl = (DynamixThreeSpaceModel)source;
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		
		for(TSObject o : mdl.getMeshes()) {
			byte[]  data = writeTSObject(null, o);
			bytes.write(data);
		}
		
		
		return bytes.toByteArray();
	}
	
	private byte[] writeTSObject(TSObject parent, TSObject object) throws IOException {

		
		ByteArrayOutputStream objectData = new ByteArrayOutputStream();
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		
		
		if(Arrays.equals(TSObjectHeader.TS_BASE_PART.val(), object.getHeader().val())) {
			objectBytes = writeTSBasePart((TSBasePart)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_PART_LIST.val(), object.getHeader().val())) {
			objectBytes = writeTSPartList((TSPartList)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_SHAPE.val(), object.getHeader().val())) {
			objectBytes = writeTSShape((TSShape)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.AN_SHAPE.val(), object.getHeader().val())) {
			objectBytes = writeANShape((ANShape)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.BSP_PART.val(), object.getHeader().val())) {
			objectBytes = writeTSBSPPart((TSBSPPart)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_GROUP.val(), object.getHeader().val())) {
			objectBytes = writeTSGroup((TSGroup)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_POLY.val(), object.getHeader().val())) {
			objectBytes = writeTSPoly((TSPoly)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_SOLID_POLY.val(), object.getHeader().val())) {
			objectBytes = writeTSSolidPoly((TSSolidPoly)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_TEXTURE4_POLY.val(), object.getHeader().val())) {
			objectBytes = writeTSTexture4Poly((TSTexture4Poly)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_SHADED_POLY.val(), object.getHeader().val())) {
			objectBytes = writeTSShadedPoly((TSShadedPoly)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_GOURAUD_POLY.val(), object.getHeader().val())) {
			objectBytes = writeTSGouradPoly((TSGouraudPoly)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_CELL_ANIM_PART.val(), object.getHeader().val())) {
			objectBytes = writeTSCellAnimPart((TSCellAnimPart)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.AN_ANIM_LIST.val(), object.getHeader().val())) {
			objectBytes = writeANAnimList((ANAnimList)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.AN_CYCLIC_SEQUENCE.val(), object.getHeader().val())) {
			objectBytes = writeANCyclicSequence((ANCyclicSequence)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.AN_SEQUENCE.val(), object.getHeader().val())) {
			objectBytes = writeANSequence((ANSequence)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_BITMAP_PART.val(), object.getHeader().val())) {
			objectBytes = writeTSBitmapPart((TSBitmapPart)object, objectBytes); 
		}
		
		if(Arrays.equals(TSObjectHeader.TS_DETAIL_PART.val(), object.getHeader().val())) {
			objectBytes = writeTSDetailPart((TSDetailPart)object, objectBytes);
		}
		
		if(Arrays.equals(TSObjectHeader.TS_BSP_GROUP.val(), object.getHeader().val())) {
			objectBytes = writeTSBSPGroup((TSBSPGroup)object, objectBytes);
		}

		System.out.println("Write " + object.getHeader());
		objectData.write(object.getHeader().val());
		byte[] data = objectBytes.toByteArray();;
		objectData.write(writeIntLE(data.length));
		objectData.write(data);
		
		byte[] arr = objectData.toByteArray(); 
		return arr;
	}
	
	private ByteArrayOutputStream writeTSBasePart(TSBasePart basePart, ByteArrayOutputStream bos) throws IOException  {

		bos.write(writeShortLE(basePart.getTransform()));
		bos.write(writeShortLE(basePart.getIdNumber()));
		bos.write(writeShortLE(basePart.getRadius()));
		bos.write(writeShortLE(basePart.getCenter().getX()));
		bos.write(writeShortLE(basePart.getCenter().getY()));
		bos.write(writeShortLE(basePart.getCenter().getZ()));
		
		return bos;	
	}
	
	private ByteArrayOutputStream writeTSBitmapPart(TSBitmapPart bitmapPart, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSBasePart((TSBasePart)bitmapPart, bos);
		
		bos.write(writeShortLE(bitmapPart.getBmpTag()));
		bos.write(bitmapPart.getOfsX());
		bos.write(bitmapPart.getOfsY());
		
		return bos;
	}

	private ByteArrayOutputStream writeTSDetailPart(TSDetailPart detailPart, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSPartList(detailPart, bos);
		
		for(int d=0; d < detailPart.getDetails().length; d++) {
			bos.write(writeShortLE(detailPart.getDetails()[d]));
		}
		
		return bos;
	}
	
	private ByteArrayOutputStream writeTSPartList(TSPartList partList, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSBasePart((TSBasePart)partList, bos);
		
		bos.write(writeShortLE((short)partList.getParts().length));
		for(int i=0; i < partList.getParts().length; i++) {
			byte[] data = writeTSObject(partList, partList.getParts()[i]);
			
			bos.write(data);
			bos.flush();
		}
		
		return bos;
	}
	
	private ByteArrayOutputStream writeTSCellAnimPart(TSCellAnimPart animPart, ByteArrayOutputStream bos) throws IOException  {

		bos = writeTSPartList(animPart, bos);
		
		bos.write(writeShortLE(animPart.getAnimSequence()));
		
		return bos;		
	}
	
	private ByteArrayOutputStream writeTSGroup(TSGroup group, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSBasePart(group, bos);
		
		bos.write(writeShortLE((short)group.getIndexes().length));
		bos.write(writeShortLE((short)group.getPoints().length));
		bos.write(writeShortLE((short)(group.getSurfaces().length * 4)));
		bos.write(writeShortLE((short)group.getPolys().length));
		
		for(int i=0; i < group.getIndexes().length; i++) {
			bos.write(writeShortLE(group.getIndexes()[i]));
		}
		
		for(int p=0; p < group.getPoints().length; p++) {
			bos.write(writeShortLE(group.getPoints()[p].getX()));
			bos.write(writeShortLE(group.getPoints()[p].getY()));
			bos.write(writeShortLE(group.getPoints()[p].getZ()));
		}

		for(int srf=0; srf < group.getSurfaces().length; srf++) {
			TSSurfaceEntry surface = group.getSurfaces()[srf];

			bos.write(writeShortLE(surface.getFrontColor()));
			bos.write(writeShortLE(surface.getFrontFlag()));
			bos.write(writeShortLE(surface.getFrontLineColor()));
			bos.write(writeShortLE(surface.getFrontLineFlag()));
			bos.write(writeShortLE(surface.getBackColor()));
			bos.write(writeShortLE(surface.getBackColorFlag()));
			bos.write(writeShortLE(surface.getBackLineColor()));
			bos.write(writeShortLE(surface.getBackLineFlag()));
		}

		for(int ply=0; ply < group.getPolys().length; ply++) {
			byte[] data = writeTSObject(group, group.getPolys()[ply]);
			bos.write(data);
		}
		
		return bos;
	}
	
	private ByteArrayOutputStream writeTSBSPGroup(TSBSPGroup bspGroup, ByteArrayOutputStream bos) throws IOException  {
	
		bos = writeTSGroup(bspGroup, bos);
		
		bos.write(writeShortLE((short)bspGroup.getGroupNodes().length));
		
		for(int n=0; n < bspGroup.getGroupNodes().length; n++) {
			
			TSBSPGroupNode node = bspGroup.getGroupNodes()[n];
			
			bos.write(writeShortLE(node.getCoeff()));
			bos.write(writeShortLE(node.getPoly()));
			bos.write(writeShortLE(node.getFront()));
			bos.write(writeShortLE(node.getBack()));
		}
		
		return bos;
	}
	
	private ByteArrayOutputStream writeTSPoly(TSPoly poly, ByteArrayOutputStream bos) throws IOException {
		
		bos.write(writeShortLE(poly.getNormal()));
		bos.write(writeShortLE(poly.getCenter()));
		bos.write(writeShortLE(poly.getVertexCount()));
		bos.write(writeShortLE(poly.getVertexList()));
		
		return bos;
	}
	
	private ByteArrayOutputStream writeTSSolidPoly(TSSolidPoly solidPoly, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSPoly(solidPoly, bos);
		bos.write(writeShortLE(solidPoly.getColorIndexId()));
		
		return bos;
	}
	
	private ByteArrayOutputStream writeTSShadedPoly(TSShadedPoly shadedPoly, ByteArrayOutputStream bos) throws IOException  {
		
		return writeTSSolidPoly(shadedPoly, bos);
	}
	
	private ByteArrayOutputStream writeTSTexture4Poly(TSTexture4Poly texture4Poly, ByteArrayOutputStream bos) throws IOException  {
		
		return writeTSSolidPoly(texture4Poly, bos);
	}
	
	private ByteArrayOutputStream writeTSGouradPoly(TSGouraudPoly gouradPoly, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSSolidPoly(gouradPoly, bos);
		bos.write(writeShortLE(gouradPoly.getNormalList()));
		
		return bos;
	}
	
	private ByteArrayOutputStream writeTSShape(TSShape shape, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSPartList(shape, bos);
		
		bos.write(writeShortLE((short)shape.getTransformList().length));
		
		bos.write(writeShortLE((short)shape.getSequenceList().length));

		for(int s=0; s < shape.getSequenceList().length; s++) {
			bos.write(writeShortLE(shape.getSequenceList()[s]));
		}
		
		for(int t=0; t < shape.getTransformList().length; t++) {
			bos.write(writeShortLE(shape.getTransformList()[t]));
		}
		
		return bos;
	}

	private ByteArrayOutputStream writeTSBSPPartNode(TSBSPPartNode node, ByteArrayOutputStream bos) throws IOException{
		
		bos.write(writeShortLE(node.getNormal().getX()));
		bos.write(writeShortLE(node.getNormal().getY()));
		bos.write(writeShortLE(node.getNormal().getZ()));
		
		bos.write(writeIntLE(node.getCoeff()));
		bos.write(writeShortLE(node.getFront()));
		bos.write(writeShortLE(node.getBack()));
		
		return bos;
	}
	
	private ByteArrayOutputStream writeTSBSPPart(TSBSPPart part, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSPartList(part, bos);
		
		bos.write(writeShortLE((short)part.getNodes().length));
		for(int n=0; n < part.getNodes().length; n++) {
			bos = writeTSBSPPartNode(part.getNodes()[n], bos);
		}
		
		for(int p=0; p < part.getNodes().length; p++) {
			bos.write(writeShortLE(part.getTransforms()[p]));
		}
		
		return bos;
	}
	
	
	private ByteArrayOutputStream writeANAnimListTransition(ANAnimListTransition transition, ByteArrayOutputStream bos) throws IOException{
		
		bos.write(writeShortLE(transition.getTick()));
		bos.write(writeShortLE(transition.getDestSequence()));
		bos.write(writeShortLE(transition.getDestFrame()));
		bos.write(writeShortLE(transition.getGroundMovement()));
		
		return bos;
	}
	
	private ByteArrayOutputStream writeANAnimListTransform(ANAnimListTransform transform, ByteArrayOutputStream bos) throws IOException{
		
		bos.write(writeShortLE(transform.getRotation().getX()));
		bos.write(writeShortLE(transform.getRotation().getY()));
		bos.write(writeShortLE(transform.getRotation().getZ()));
		
		bos.write(writeShortLE(transform.getTranslation().getX()));
		bos.write(writeShortLE(transform.getTranslation().getY()));
		bos.write(writeShortLE(transform.getTranslation().getZ()));
		
		return bos;
	}
	
	private ByteArrayOutputStream writeANAnimList(ANAnimList animList, ByteArrayOutputStream bos) throws IOException  {
		
		bos.write(writeShortLE((short)animList.getSequences().length));
		for(int s=0; s < animList.getSequences().length; s++) {
			byte[] data = writeTSObject(animList, animList.getSequences()[s]);
			bos.write(data);
		}
		
		bos.write(writeShortLE((short)animList.getTransitions().length));
		for(int t=0; t < animList.getTransitions().length; t++) {
			bos = writeANAnimListTransition(animList.getTransitions()[t], bos);
		}
		
		bos.write(writeShortLE((short)animList.getTransforms().length));
		for(int t=0; t < animList.getTransforms().length; t++) {
			bos = writeANAnimListTransform(animList.getTransforms()[t], bos);
		}

		bos.write(writeShortLE((short)animList.getDefaultTransforms().length));
		for(int d=0; d < animList.getDefaultTransforms().length; d++) {
			bos.write(writeShortLE(animList.getDefaultTransforms()[d]));
		}
		
		bos.write(writeShortLE((short)animList.getRelations().length));
		for(int r=0; r < animList.getRelations().length; r++) {
			bos.write(writeShortLE(animList.getRelations()[r].getX()));
			bos.write(writeShortLE(animList.getRelations()[r].getY()));
		}
		
		return bos;
		
	}
	
	private ByteArrayOutputStream writeANSequenceFrame(ANSequenceFrame frame, ByteArrayOutputStream bos) throws IOException{
		
		bos.write(writeShortLE(frame.getTick()));
		bos.write(writeShortLE(frame.getFirstTransition()));
		bos.write(writeShortLE(frame.getNumTransitions()));
		
		return bos;
	}
	
	private ByteArrayOutputStream writeANSequence(ANSequence seq, ByteArrayOutputStream bos) throws IOException {
		
		bos.write(writeShortLE(seq.getTick()));
		bos.write(writeShortLE(seq.getPriority()));
		bos.write(writeShortLE(seq.getGroundMovement()));
		
		bos.write(writeShortLE((short)seq.getFrames().length));
		for(int f=0; f < seq.getFrames().length; f++) {
			bos = writeANSequenceFrame(seq.getFrames()[f], bos);
		}
		
		bos.write(writeShortLE((short)seq.getPartIds().length));
		for(int p=0; p < seq.getPartIds().length; p++) {
			bos.write(writeShortLE(seq.getPartIds()[p]));
		}
		
		for(int t=0; t < seq.getTransformIndices().length; t++) {
			bos.write(writeShortLE(seq.getTransformIndices()[t]));
		}
		
		return bos;
	}
	
	private ByteArrayOutputStream writeANCyclicSequence(ANCyclicSequence seq, ByteArrayOutputStream bos) throws IOException {
		
		bos = writeANSequence(seq, bos);
		
		return bos;
	}
		
	private ByteArrayOutputStream writeANShape(ANShape shape, ByteArrayOutputStream bos) throws IOException  {
		
		bos = writeTSShape(shape, bos);
		
		byte[] data = writeTSObject(shape, shape.getAnimationList());
		bos.write(data);
		
		return bos;
	}
	
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		b.append("index @ " + Bytes.from(index).encodeHex());
		
		return b.toString();
	}
}
