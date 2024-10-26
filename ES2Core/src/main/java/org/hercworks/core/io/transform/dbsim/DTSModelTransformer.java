package org.hercworks.core.io.transform.dbsim;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.hercworks.core.data.file.dts.BMPPart;
import org.hercworks.core.data.file.dts.BSPGroup;
import org.hercworks.core.data.file.dts.BSPGroupNode;
import org.hercworks.core.data.file.dts.BSPPart;
import org.hercworks.core.data.file.dts.BSPPartNode;
import org.hercworks.core.data.file.dts.BasePart;
import org.hercworks.core.data.file.dts.CellAnimPart;
import org.hercworks.core.data.file.dts.DTSChunkTypes;
import org.hercworks.core.data.file.dts.DTSObject;
import org.hercworks.core.data.file.dts.DetailPart;
import org.hercworks.core.data.file.dts.GouradPoly;
import org.hercworks.core.data.file.dts.Group;
import org.hercworks.core.data.file.dts.PartList;
import org.hercworks.core.data.file.dts.Poly;
import org.hercworks.core.data.file.dts.ShadedPoly;
import org.hercworks.core.data.file.dts.Shape;
import org.hercworks.core.data.file.dts.SolidPoly;
import org.hercworks.core.data.file.dts.Texture4Poly;
import org.hercworks.core.data.file.dts.anim.AnimList;
import org.hercworks.core.data.file.dts.anim.AnimShape;
import org.hercworks.core.data.file.dts.anim.CyclicSequence;
import org.hercworks.core.data.file.dts.anim.Relation;
import org.hercworks.core.data.file.dts.anim.Sequence;
import org.hercworks.core.data.file.dts.anim.SequenceFrame;
import org.hercworks.core.data.file.dts.anim.Transform;
import org.hercworks.core.data.file.dts.anim.Transition;
import org.hercworks.core.data.file.dyn.DynamixThreeSpaceModel;
import org.hercworks.core.data.struct.ColorBytes;
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

		LinkedList<DTSObject> entries = new LinkedList<DTSObject>();
		
		while(index < inputArray.length) {
			entries.add(segmentLoader(indexSegment(4)));
		}
		
		dts.setDTSParts(entries);
		
		return dts;
	}

	//Just a note here, I didn't want to mix data-models with loader-logic,
	//ie, yes a generic _load callback per-DTS object would be more slick...
	//put that would pollute the data-model package with implied assumptions
	//around how consumers would want to unpack the data, if at-all. - Subject9x
	private DTSObject segmentLoader(byte[] typMarker) {
		boolean dbgTrig = false;
		
		System.out.println("@index =" + (index - 4) +", typeMarker=" + Bytes.from(typMarker).encodeHex());
		DTSObject dtsObj = null;
		int len = indexIntLE();
		System.out.println("@index =" + (index - 4) +", len=" + len);
		for(DTSChunkTypes type : DTSChunkTypes.values()) {	
			if(Bytes.from(type.marker()).encodeHex().equals(Bytes.from(typMarker).encodeHex())) {
				dbgTrig = true;
				switch(type) {
				case POLY: dtsObj = readPoly(null); break;
				
				case SOLID_POLY: dtsObj = readSolidPoly(null); break;
				
				case TEXTURE4_POLY: dtsObj = readTexture4Poly(null); break;
				
				case SHADED_POLY: dtsObj = readShadePoly(null); break;
				
				case GOURAD_POLY: dtsObj = readGouradPoly(null); break;
				
				case ALIAS_SOLID_POLY: dtsObj = readSolidPoly(null); break;
				
				case ALIAS_SHADE_POLY: dtsObj = readShadePoly(null); break;
				
				case ALIAS_GOURAD_POLY: dtsObj = readGouradPoly(null); break;
				
				case GROUP: dtsObj = readGroup(null); break;
				
				case BSP_GROUP: dtsObj = readBSPGroup(null); break;
				
				case BASE_PART: dtsObj = readBasePart(null); break;
				
				case BMP_PART: dtsObj = readBMPPart(null); break;
					
				case PART_LIST: dtsObj = readPartList(null); break;
				
				case CELL_ANIM_PART: dtsObj = readCellAnimPart(null); break;
				
				case DETAIL_PART: dtsObj = readDetailPart(null); break;
				
				case BSP_PART: dtsObj = readBSPPart(null); break;
				
				case TS_SHAPE: dtsObj = readShape(null); break;
				
				case SEQ: dtsObj = readSequence(null); break;
				
				case CYCLIC_SEQ: dtsObj = readCyclicSequence(null); break;
					
				case ANIM_SHAPE: dtsObj = readAnimShape(null); break;
					
				case ANIM_LIST: dtsObj = readAnimList(); break;
				
				case NULL_OBJ: break;
				}
			}
		}
		if(dbgTrig == false) {
			System.out.println("    @ index=" + index + ", ALERT: object not found");
			skip(len);
		}
		
		return dtsObj;
	}
	
	private BasePart readBasePart(BasePart part) {
		if(part == null) {
			System.out.println("--------------readTSBasePart()------------");
			part = new BasePart();
		}
		
		part.setTransform(indexShortLE());
		part.setIDNumber(indexShortLE());
		part.setRadius(indexShortLE());
		part.setOrigin(new Vector3D(indexShortLE(), indexShortLE(), indexShortLE()));
		
		return part;
	}
	
	private PartList readPartList(PartList partList) {
		
		if(partList == null) {
			System.out.println("--------------readTSPartList()------------");
			partList = new PartList();
		}

		partList = (PartList)readBasePart(partList);
		
		//READ Part Objects
		int objCount = indexShortLE();
		System.out.print("	index @" + (index-2) + ", objCount="+ objCount + "\n");
		
		for(int o=0; o < objCount; o++) {
			partList.getParts().add(segmentLoader(indexSegment(4)));
		}
		
		return partList;
	}
	
	private BSPPart readBSPPart(BSPPart bspPart) {
		
		if(bspPart == null) {
			System.out.println("--------------readBSPPart()------------");
			bspPart = new BSPPart();
		}
		
		bspPart = (BSPPart)readPartList(bspPart);
		
		//READ BSP Part Nodes
		int nodeCount = indexShortLE();
		System.out.print("	index @" + (index-4) + ", nodeCount="+ nodeCount + "\n");
		
		for(int n=0; n < nodeCount; n++) {
			System.out.println("	index @" + index + " add TSBSPPart_node");
			bspPart.getNodes().add(readTSBSPPart_Node());
		}
		
		//READ Transforms
		int transformCount = indexShortLE();
		System.out.println("	index @" + (index-2) + ", begin transform read");
		
		for(int t=0; t < transformCount; t++) {
			short s = indexShortLE();
			System.out.println("	index @" + (index-2)  + ", transform="+s);
			bspPart.getTransforms().add(s);
		}
		System.out.println("	index @" + index + ", end transform read");
		
		return bspPart;
	}
	
	private Group readGroup(Group group) {
		
		if(group == null) {
			System.out.println("--------------readTSGroup()------------");
			group = new Group();
		}
		
		group = (Group)readBasePart(group);

		int indexCount = indexShortLE();
		System.out.print("	index @" + (index-2) + ", indexCount="+ indexCount + "\n");
		
		int pointCount = indexShortLE();
		System.out.print("	index @" + (index-2) + ", pointCount="+ pointCount + "\n");
		
		int colorCount = indexShortLE();
		System.out.print("	index @" + (index-2) + ", colorCount="+ colorCount + "\n");
		
		int itemCount = indexShortLE();
		System.out.print("	index @" + (index-2) + ", itemCount="+ itemCount + "\n");
		
		for(int idx=0; idx < indexCount; idx++) {
			group.getIndexes().add(indexShortLE());
		}
		
		for(int p=0; p < pointCount; p++) {
			group.getPoints().add(new Vector3D(indexShortLE(), indexShortLE(), indexShortLE()));
		}
		
		for(int c=0; c < colorCount; c++) {
			group.getColors().add(new ColorBytes(indexSegment(4)));
		}
		
		for(int itm=0; itm < itemCount; itm++) {
			group.getItems().add(segmentLoader(indexSegment(4)));
		}
		
		return group;
	}
	
	private BSPGroup readBSPGroup(BSPGroup bspGroup) {
		
		if(bspGroup == null) {
			bspGroup = new BSPGroup();
		}
		
		bspGroup = (BSPGroup)readGroup(bspGroup);
		
		int nodeCount = indexShortLE();
		System.out.println("	index @" + (index-2) + ", nodeCount="+ nodeCount);
		for(int n=0; n < nodeCount; n++) {
			bspGroup.getNodes().add(readBSPGroupNode());
		}
		
		return bspGroup;
	}
	
	private BSPGroupNode readBSPGroupNode() {
		
		BSPGroupNode bspNode = new BSPGroupNode();
		
		bspNode.setCoeff(indexIntLE());
		bspNode.setPoly(indexShortLE());
		bspNode.setFront(indexShortLE());
		bspNode.setBack(indexShortLE());
		
		return bspNode;
	}
	
	private Poly readPoly(Poly poly) {
		
		if(poly == null) {
			System.out.println("--------------readTSPoly()------------");
			poly = new Poly();	
		}
		
		poly.setNormal(indexShortLE());
		poly.setCenter(indexShortLE());
		poly.setVertexCount(indexShortLE());
		poly.setVertexList(indexShortLE());
		
		return poly;
	}
	
	private SolidPoly readSolidPoly(SolidPoly solidPoly) {
		
		if(solidPoly == null) {
			System.out.println("--------------readTSSolidPoly()------------");
			solidPoly = new SolidPoly();
		}
		
		solidPoly = (SolidPoly)readPoly(solidPoly);
		
		solidPoly.setColor(indexShortLE());
		
		return solidPoly;
	}
	
	private GouradPoly readGouradPoly(GouradPoly gouradPoly) {
		
		if(gouradPoly == null) {
			System.out.println("--------------readTSGouradPoly()------------");
			gouradPoly = new GouradPoly();
		}
		
		gouradPoly = (GouradPoly)readSolidPoly(gouradPoly);
		
		gouradPoly.setNormalList(indexShortLE());
		
		
		return gouradPoly;
	}
	
	private Texture4Poly readTexture4Poly(Texture4Poly texturePoly) {
		
		if(texturePoly == null) {
			System.out.println("--------------readTSTexture4Poly()------------");
			texturePoly = new Texture4Poly();
		}
		
		texturePoly = (Texture4Poly)readSolidPoly(texturePoly);
		
		return texturePoly;
	}
	
	private ShadedPoly readShadePoly(ShadedPoly shadedPoly) {
		
		if(shadedPoly == null) {
			System.out.println("--------------readTSShadePoly()------------");
			shadedPoly = new ShadedPoly();
		}
		
		shadedPoly = (ShadedPoly)readSolidPoly(shadedPoly);
		
		return shadedPoly;
	}
	
	private Shape readShape(Shape shape) {
		
		if(shape == null) {
			System.out.println("--------------readTSShape()------------");
			shape = new Shape();
		}
		
		shape = (Shape)readPartList((PartList)shape);
		
		short transfrmListCount = indexShortLE();
		System.out.print("	index @" + (index-2) + ", transfrmListCount="+ transfrmListCount + "\n");
		
		short seqListCount = indexShortLE();
		System.out.print("	index @" + (index-2) + ", seqListCount="+ seqListCount + "\n");
		
		for(int sq=0; sq < seqListCount; sq++) {
			shape.getSequenceList().add(indexShortLE());
		}
		
		for(int i=0; i < transfrmListCount; i++) {
			shape.getTransformList().add(indexShortLE());
		}
		
		return shape;
	}
	
	private CellAnimPart readCellAnimPart(CellAnimPart cellAnimPart) {
		
		if(cellAnimPart == null) {
			System.out.println("--------------readTSCellAnimPart()------------");
			cellAnimPart = new CellAnimPart();
		}
		
		cellAnimPart = (CellAnimPart)readPartList(cellAnimPart);
		
		cellAnimPart.setAnimSequence(indexShortLE());
		
		return cellAnimPart;
	}
	
	private DetailPart readDetailPart(DetailPart detailPart) {
		
		if(detailPart == null) {
			System.out.println("--------------readTSDetailPart()------------");
			detailPart = new DetailPart();
		}
		
		detailPart = (DetailPart)readPartList(detailPart);
		
		int detailCount = indexShortLE();
		
		for(int d=0; d < detailCount; d++) {
			detailPart.getDetails().add(indexShortLE());
		}
		
		return detailPart;
	}
	
	private BMPPart readBMPPart(BMPPart bmpPart) {
		
		if(bmpPart == null) {
			System.out.println("--------------readTSBMPPart()------------");
			bmpPart = new BMPPart();
		}
		
		bmpPart = (BMPPart)readBasePart(bmpPart);
		
		bmpPart.setBmpTag(indexShortLE());
		bmpPart.setOfs_x(indexByte());
		bmpPart.setOfs_y(indexByte());
		
		return bmpPart;
	}
	
	private Sequence readSequence(Sequence sequence) {
		
		if(sequence == null) {
			System.out.println("--------------readSequence()------------");
			sequence =  new Sequence();
		}
		
		sequence.setTic(indexShortLE());
		sequence.setPriority(indexShortLE());
		sequence.setGm(indexShortLE());
		
		//READ frameCount;
		int frameCount = indexShortLE();
		System.out.println("	index @" + (index-2) + ", frameCount="+ frameCount);;
		
		for(int f=0; f < frameCount; f++) {
			sequence.getFrames().add(readANSequenceFrame());
		}
		
		//READ part Id's.
		int partCount = indexShortLE();
		System.out.println("	index @" + (index-2) + ", partCount="+ partCount);
		
		for(int p=0; p < partCount; p++) {
			sequence.getParts().add(indexShortLE());
		}
		
		//READ transformIndices
		int transformCount = frameCount * partCount;
		System.out.println("	index @" + (index-2) + ", start transformCount["+transformCount+"]");
		
		for(int t=0; t < transformCount; t++) {
			short trn = indexShortLE();
			System.out.println("	index @" + (index-2) + ", transformIndex="+ trn);
			sequence.getTransformIndexList().add(trn);
		}
		System.out.println("    index @" + index + ", end transform index read");
		
		return sequence;
	}
	
	private CyclicSequence readCyclicSequence(CyclicSequence sequence) {
		
		if(sequence == null) {
			System.out.println("--------------readCyclicSequence()------------");
			sequence = new CyclicSequence();
		}
		
		sequence = (CyclicSequence)readSequence(sequence);
		
		return sequence;
	}

	private AnimList readAnimList() {
		System.out.println("--------------readAnimList()------------");
		AnimList anims = new AnimList();
		
		//READ TSPartLists
		int seqCount = indexShortLE();
		System.out.println("	index @" + (index-2) + ", seqCount="+ seqCount);
		for(int seq=0; seq < seqCount; seq++) {
			System.out.println("	index @" + index + " add TSPartList");
			System.out.println("	index @" + (index-4) + " "+ Bytes.from(indexSegment(4)).encodeHex());
			anims.getSequences().add(readSequence(null));
		}
		
		//READ Transitions
		int transitCount = indexShortLE();
		System.out.println("	index @" + (index-2) + ", transitCount="+ transitCount);
		for(int t=0; t < transitCount; t++) {
			System.out.println("	index @" + index + " add AnimList_Transition");
			anims.getTransitions().add(readAnimListTransition());
		}
		
		//READ Transforms
		int trnsfrmCount = indexShortLE();
		System.out.println("	index @" + (index-2) + ", trnsfrmCount="+ trnsfrmCount);
		for(int t=0; t < trnsfrmCount; t++) {
			System.out.println("	index @" + index + " add AnimList_Transform");
			anims.getTransforms().add(readAnimListTransform());
		}

		//READ defaultTransform
		int defTransform = indexShortLE();
		System.out.println("	index @" + (index-2) + ", defTransform="+ defTransform);
		for(int t=0; t < trnsfrmCount; t++) {
			anims.getDefaultTransforms().add(indexShortLE());
		}
		
		//READ relationsCount;
		int relationCount = indexShortLE();
		System.out.println("	index @" + (index-2) + ", relationCount="+ relationCount);
		for(int t=0; t < trnsfrmCount; t++) {
			Relation relation = new Relation();
			relation.setParent(indexShortLE());
			relation.setDestination(indexShortLE());
			anims.getRelations().add(relation);
		}
		
		return anims;
	}
	
	private AnimShape readAnimShape(AnimShape shape) {
		
		if(shape == null) {
			System.out.println("--------------readANShape()------------");
			shape = new AnimShape();
		}
		shape = (AnimShape)readShape(shape);
		
		shape.setPart(segmentLoader(indexSegment(4)));
		
		return shape;
	}
	
	//-------------------------------------------------------------
	//sub objects, these don't have chunk markers.
	private Transition readAnimListTransition() {
		System.out.println("@index="+index+", readAnimListTransition()------------");
		Transition transition = new Transition();
		
		transition.setTic(indexShortLE());
		transition.setDestSequence(indexShortLE());
		transition.setDestFrame(indexShortLE());
		transition.setGroundMovement(indexShortLE());
		
		return transition;
	}
	
	private Transform readAnimListTransform() {
		System.out.println("@index="+index+", readAnimListTransform()------------");
		Transform transform = new Transform();
		
		transform.setR(new Vector3D(indexShortLE(), indexShortLE(), indexShortLE()));
		transform.setT(new Vector3D(indexShortLE(), indexShortLE(), indexShortLE()));
		
		return transform;
	}
	
	private SequenceFrame readANSequenceFrame() {
		System.out.println("@index="+index+", readANSequenceFrame()------------");
		SequenceFrame frame = new SequenceFrame();
		
		frame.setTic(indexShortLE());
		frame.setNumTransitions(indexShortLE());
		frame.setFirstTransition(indexShortLE());
		
		return frame;
	}
	
	private BSPPartNode readTSBSPPart_Node() {
		System.out.println("@index="+index+", readANSequenceFrame()------------");
		BSPPartNode node = new BSPPartNode();
		
		node.setNormal(new Vector3D(indexShortLE(), indexShortLE(), indexShortLE()));
		node.setCoeff(indexIntLE());
		node.setFront(indexShortLE());
		node.setBack(indexShortLE());
		
		return node;
	}
	
	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
