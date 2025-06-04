package org.hercworks.transfer.svc.impl.dbsim;

import java.util.ArrayList;
import java.util.List;

import org.hercworks.core.data.file.dts.TSBasePart;
import org.hercworks.core.data.file.dts.TSGroup;
import org.hercworks.core.data.file.dts.TSObject;
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
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.dts.DTSRootDTO;
import org.hercworks.transfer.dto.file.sim.dts.TSGroupDTO;
import org.hercworks.transfer.dto.file.sim.dts.TSObjectDTO;
import org.hercworks.transfer.dto.file.sim.dts.TSPartListDTO;
import org.hercworks.transfer.dto.file.sim.dts.TSShapeDTO;
import org.hercworks.transfer.dto.file.sim.dts.TSSurfaceEntryDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.ANAnimListDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.ANCyclicSequenceDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.ANSequenceDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.ANShapeDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.AnimTransformDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.AnimTransitionDTO;
import org.hercworks.transfer.dto.file.sim.dts.anim.SequenceFrameDTO;
import org.hercworks.transfer.dto.file.sim.dts.bsp.TSBSPGroupDTO;
import org.hercworks.transfer.dto.file.sim.dts.bsp.TSBSPGroupNodeDTO;
import org.hercworks.transfer.dto.file.sim.dts.bsp.TSBSPPartDTO;
import org.hercworks.transfer.dto.file.sim.dts.bsp.TSBSPPartNodeDTO;
import org.hercworks.transfer.dto.file.sim.dts.bsp.TSGouraudPolyDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSBasePartDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSBitmapPartDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSCellAnimPartDTO;
import org.hercworks.transfer.dto.file.sim.dts.part.TSDetailPartDTO;
import org.hercworks.transfer.dto.file.sim.dts.poly.TSPolyDTO;
import org.hercworks.transfer.dto.file.sim.dts.poly.TSShadedPolyDTO;
import org.hercworks.transfer.dto.file.sim.dts.poly.TSSolidPolyDTO;
import org.hercworks.transfer.dto.file.sim.dts.poly.TSTexture4PolyDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class DTSServiceImpl implements GeneralDTOService {

	
	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		DynamixThreeSpaceModel dts = (DynamixThreeSpaceModel)source;
		
		DTSRootDTO exportDTO = new DTSRootDTO();
		
		exportDTO.setFileName(source.originNameNoExt());
		
		TSObjectDTO[] shapes = new TSObjectDTO[dts.getMeshes().size()];
		
		for(int s=0; s < shapes.length; s++) {
			shapes[s] = buildTSObject(dts.getMeshes().get(s));
		}
		exportDTO.setShapes(shapes);
		
		return exportDTO;
	}

	
	
	private TSObjectDTO buildTSObject(TSObject o) {
		
		System.out.println(o.getClass().getSimpleName());
		if(o.getClass().getSimpleName().equals("TSBasePart")) {
			return expTSBasePart((TSBasePart)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSPartList")) {
			return expTSPartList((TSPartList)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSShape")) {
			return expTSShape((TSShape)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("ANShape")) {
			return expANShape((ANShape)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSBSPPart")) {
			return expTSBSPPart((TSBSPPart)o);
		}
		
		if(o.getClass().getSimpleName().equals("TSGroup")) {
			return expTSGroup((TSGroup)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSPoly")) {
			return expTSPoly((TSPoly)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSSolidPoly")) {
			return expTSSolidPoly((TSSolidPoly)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSTexture4Poly")) {
			return expTSTexture4Poly((TSTexture4Poly)o);
		}
		
		if(o.getClass().getSimpleName().equals("TSShadedPoly")) {
			return expTSShadedPoly((TSShadedPoly)o);
		}
		
		if(o.getClass().getSimpleName().equals("TSGouraudPoly")) {
			return expTSGouradPoly((TSGouraudPoly)o);
		}
		
		if(o.getClass().getSimpleName().equals("TSCellAnimPart")) {
			return expTSCellAnimPart((TSCellAnimPart)o);
		}
		
		if(o.getClass().getSimpleName().equals("ANAnimList")) {
			return expANAnimList((ANAnimList)o);
		}
		
		if(o.getClass().getSimpleName().equals("ANCyclicSequence")) {
			return expANCylicSequence((ANCyclicSequence)o);
		}
		
		if(o.getClass().getSimpleName().equals("ANSequence")) {
			return expANSequence((ANSequence)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSBitmapPart")) {
			return expTSBitmapPart((TSBitmapPart)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSDetailPart")) {
			return expTSDetailPart((TSDetailPart)o, null);
		}
		
		if(o.getClass().getSimpleName().equals("TSBSPGroup")) {
			return expTSBSPGroup((TSBSPGroup)o, null);
		}
		
		return null;
	}
	
	private TSBasePartDTO expTSBasePart(TSBasePart o, TSBasePartDTO link) {
		
		if(link == null) {
			link = new TSBasePartDTO();
		}
		
		link.setTransform(o.getTransform());
		
		link.setTransformId(o.getUID());
		
		link.setRadius((float)o.getRadius());
		
		link.setCenter(vec3ToFloat(o.getCenter()));
		
		return link;
	}
	
	private TSPartListDTO expTSPartList(TSPartList o, TSPartListDTO link) {
		
		if(link == null) {
			link = new TSPartListDTO();
		}
		link = (TSPartListDTO) expTSBasePart((TSBasePart)o, (TSBasePartDTO) link);
		
		TSObjectDTO[] parts = new TSObjectDTO[o.getParts().length];
		
		for(int p=0; p < o.getParts().length; p++) {
			parts[p] = buildTSObject(o.getParts()[p]);
		}
		
		link.setParts(parts);
		
		return link;
	}
	
	private TSShapeDTO expTSShape(TSShape s, TSShapeDTO link) {
		
		if(link == null) {
			link = new TSShapeDTO();
		}
		
		link = (TSShapeDTO) expTSPartList((TSPartList)s, (TSPartListDTO)link);
		
		int[] seqs = new int[s.getSequenceList().length];
		
		for(int q=0; q < seqs.length; q++) {
			seqs[q] = s.getSequenceList()[q];
		}
		link.setSequences(seqs);
		
		int[] trs = new int[s.getTransformList().length];
		for(int r=0; r < trs.length; r++) {
			trs[r] = s.getTransformList()[r];
		}
		link.setTransforms(trs);
		
		return link;
	}
	
	private ANShapeDTO expANShape(ANShape s, ANShapeDTO link) {
		
		if(link == null) {
			link = new ANShapeDTO();
		}
		
		link = (ANShapeDTO) expTSShape((TSShape)s, (TSShapeDTO)link);
		
		ANAnimListDTO animDTO = (ANAnimListDTO)buildTSObject(s.getAnimationList());
		
		link.setAnimationList(animDTO);
		
		return link;
	}
	
	private TSBSPPartNodeDTO expTSBSPPartNode(TSBSPPartNode node, int index) {
		TSBSPPartNodeDTO dto = new TSBSPPartNodeDTO();
		
		dto.setIndex(index);
		
		dto.setNormal(vec3ToFloat(node.getNormal()));
		
		dto.setCoeff(node.getCoeff());
		
		dto.setFront(node.getFront());
		
		dto.setBack(node.getBack());
		
		return dto;
	}
	
	private TSBSPPartDTO expTSBSPPart(TSBSPPart p) {
		
		TSBSPPartDTO part = new TSBSPPartDTO();
		
		part = (TSBSPPartDTO) expTSPartList((TSPartList)p, (TSPartListDTO)part);
		
		TSBSPPartNodeDTO[] nodes = new TSBSPPartNodeDTO[p.getNodes().length];
		
		for(int n=0; n < p.getNodes().length; n++) {
			nodes[n] = expTSBSPPartNode(p.getNodes()[n], n);
		}
		part.setNodes(nodes);
		
		int[] transfrms = new int[p.getTransforms().length];
		for(int t=0; t < p.getTransforms().length; t++) {
			transfrms[t] = p.getTransforms()[t]; 
		}
		part.setTransformIds(transfrms);
		
		return part;
	}
	
	private TSGroupDTO expTSGroup(TSGroup g, TSGroupDTO link) {
		
		if(link == null) {
			link = new TSGroupDTO();	
		}
		
		link = (TSGroupDTO)expTSBasePart(g, (TSBasePartDTO)link);
		
		int[] indexes = new int[g.getIndexes().length];
		for(int i=0; i < g.getIndexes().length; i++) {
			indexes[i] = g.getIndexes()[i]; 
		}
		link.setIndexes(indexes);
		
		float[][] verts = new float[g.getPoints().length][3];
		for(int p=0; p < g.getPoints().length; p++) {
			float[] v =vec3ToFloat(g.getPoints()[p]); 
			verts[p] = v;
		}
		link.setVertices(verts);
		
		TSSurfaceEntryDTO[] surfaces = new TSSurfaceEntryDTO[g.getSurfaces().length];
		for(int s=0; s < g.getSurfaces().length; s++) {
			TSSurfaceEntry e = g.getSurfaces()[s];
			TSSurfaceEntryDTO srf = new TSSurfaceEntryDTO();
			
			srf.setIndex(s);
			
			srf.setFront(srf.newSurfaceColor(e.getFrontColor(), e.getFrontFlag(), e.getFrontLineColor(), e.getFrontLineFlag()));
			srf.setBack(srf.newSurfaceColor(e.getBackColor(), e.getBackColorFlag(), e.getBackLineColor(), e.getBackLineFlag()));
			
			surfaces[s] = srf;
		}
		link.setSurfaces(surfaces);
		
		TSObjectDTO[] parts = new TSObjectDTO[g.getPolys().length];
		for(int l=0; l < g.getPolys().length; l++) {
			TSObjectDTO part = buildTSObject(g.getPolys()[l]);
			
			part.setIndex(l);
			parts[l] = part;
		}
		link.setPolys(parts);
		
		return link;		
	}
	
	private TSPolyDTO expTSPoly(TSPoly p, TSPolyDTO link) {
		
		if(link == null) {
			link = new TSPolyDTO();
		} 
		
		link.setNormal(p.getNormal());
		link.setCenter(p.getCenter());
		link.setVertexTotal(p.getVertexCount());
		link.setVertixListStartNum(p.getVertexList());
		
		return link;
	}
	
	private TSSolidPolyDTO expTSSolidPoly(TSSolidPoly p, TSSolidPolyDTO link) {
		
		if(link == null) {
			link = new TSSolidPolyDTO();
		}
		
		link = (TSSolidPolyDTO) expTSPoly(p, link);
		
		link.setSurfaceNum(p.getColorIndexId() / 4);
		
		return link;
	}
	
	private TSShadedPolyDTO expTSShadedPoly(TSShadedPoly p) {
		
		TSShadedPolyDTO t = new TSShadedPolyDTO();
		
		t = (TSShadedPolyDTO) expTSSolidPoly(p, t);
		
		return t;
	}
	
	private TSTexture4PolyDTO expTSTexture4Poly(TSTexture4Poly p) {
		
		TSTexture4PolyDTO t = new TSTexture4PolyDTO();
		
		t = (TSTexture4PolyDTO) expTSSolidPoly(p, (TSSolidPolyDTO)t);
		
		return t;
	}
	
	private TSGouraudPolyDTO expTSGouradPoly(TSGouraudPoly p) {
		
		TSGouraudPolyDTO t = new TSGouraudPolyDTO();
		
		t = (TSGouraudPolyDTO) expTSSolidPoly(p, t);
		
		return t;
	}
	
	private TSCellAnimPartDTO expTSCellAnimPart(TSCellAnimPart cell) {
		
		TSCellAnimPartDTO t = new TSCellAnimPartDTO();
		
		t = (TSCellAnimPartDTO) expTSPartList(cell, (TSPartListDTO)t);
		
		t.setAnimSequenceNum(cell.getAnimSequence());
		
		return t;
	}
	
	private ANAnimListDTO expANAnimList(ANAnimList anim) {
		
		ANAnimListDTO t = new ANAnimListDTO();
		
		TSObjectDTO[] seqs = new TSObjectDTO[anim.getSequences().length];
		for(int s=0; s < anim.getSequences().length; s++) {
			seqs[s] = buildTSObject(anim.getSequences()[s]);
		}
		t.setSequences(seqs);
		
		AnimTransitionDTO[] transitions = new AnimTransitionDTO[anim.getTransitions().length];
		for(int a=0; a < anim.getTransitions().length; a++) {
			ANAnimListTransition antr = anim.getTransitions()[a];
			AnimTransitionDTO trs = new AnimTransitionDTO();
			
			trs.setIndex(a);
			trs.setTick(antr.getTick());
			trs.setDestination_seq(antr.getDestSequence());
			trs.setDestination_frame(antr.getDestFrame());
			trs.setGroundMovement(antr.getGroundMovement());
			
			transitions[a] = trs;
		}
		t.setTransitions(transitions);
		
		AnimTransformDTO[] transforms = new AnimTransformDTO[anim.getTransforms().length];
		for(int b=0; b < anim.getTransforms().length; b++) {
			ANAnimListTransform atr = anim.getTransforms()[b];
			AnimTransformDTO trf = new AnimTransformDTO();
			
			trf.setIndex(b);
			trf.setRotation(vec3ToFloat(atr.getRotation()));
			trf.setTranslation(vec3ToFloat(atr.getTranslation()));
			
			transforms[b] = trf;
		}
		t.setTransforms(transforms);
		
		int[] defTransform = new int[anim.getDefaultTransforms().length];
		for(int d=0; d < anim.getDefaultTransforms().length; d++) {
			defTransform[d] = anim.getDefaultTransforms()[d];
		}
		t.setDefaultTransforms(defTransform);
		
		int[][] relations = new int[anim.getRelations().length][2];
		for(int r=0; r < anim.getRelations().length; r++) {
			int[] rel = new int[2];
			rel[0] = anim.getRelations()[r].getX();
			rel[1] = anim.getRelations()[r].getY();
			relations[r] = rel;
		}
		t.setTransformIdLinks(relations);
		
		return t;
	}
	
	private ANSequenceDTO expANSequence(ANSequence seq, ANSequenceDTO link) {
		
		if(link == null) {
			link = new ANSequenceDTO();
		}
		
		link.setTick(seq.getTick());
		link.setPriority(seq.getPriority());
		link.setGroundMoveFlag(seq.getGroundMovement());
		
		SequenceFrameDTO[] frames = new SequenceFrameDTO[seq.getFrames().length];
		for(int f=0; f < seq.getFrames().length; f++) {
			SequenceFrameDTO frame = new SequenceFrameDTO();
			ANSequenceFrame anFrame = seq.getFrames()[f];
			
			frame.setId(f);
			frame.setTick(anFrame.getTick());
			frame.setNumberTransitions(anFrame.getNumTransitions());
			frame.setFirstTransition(anFrame.getFirstTransition());
			
			frames[f] = frame;
		}
		link.setFrames(frames);
		
		
		int[] partIds = new int[seq.getPartIds().length];
		for(int p=0; p < seq.getPartIds().length; p++) {
			partIds[p] = seq.getPartIds()[p];
		}
		link.setPartIds(partIds);
		
		int[] transformIndices = new int[seq.getTransformIndices().length];
		for(int t=0; t < seq.getTransformIndices().length; t++) {
			transformIndices[t] = seq.getTransformIndices()[t];
		}
		link.setTransformIndexes(transformIndices);
		
		return link;
	}
	
	private ANCyclicSequenceDTO expANCylicSequence(ANCyclicSequence seq) {
		
		ANCyclicSequenceDTO t = new ANCyclicSequenceDTO();
		
		t = (ANCyclicSequenceDTO) expANSequence(seq, t);
		
		return t;
	}
	
	private TSBitmapPartDTO expTSBitmapPart(TSBitmapPart bmp, TSBitmapPartDTO link) {
		
		if(link == null) {
			link = new TSBitmapPartDTO();
		}
		
		link = (TSBitmapPartDTO) expTSBasePart(bmp, (TSBasePartDTO)link);
		
		link.setBmpFrameNum(bmp.getBmpTag());
		link.setOffsetX(bmp.getOfsX());
		link.setOffsetY(bmp.getOfsY());
		
		return link;
	}
	
	private TSDetailPartDTO expTSDetailPart(TSDetailPart det, TSDetailPartDTO link) {
		
		if(link == null) {
			link = new TSDetailPartDTO();
		}
		
		link = (TSDetailPartDTO) expTSPartList(det, (TSPartListDTO)link);
		
		int[] details = new int[det.getDetails().length];
		for(int d=0; d < det.getDetails().length; d++) {
			details[d] = det.getDetails()[d];
		}
		link.setDetails(details);
		
		return link;
	}
	
	private TSBSPGroupDTO expTSBSPGroup(TSBSPGroup g, TSBSPGroupDTO link) {
		
		if(link == null) {
			link = new TSBSPGroupDTO();
		}
		
		link = (TSBSPGroupDTO) expTSGroup(g, (TSGroupDTO)link);
		
		TSBSPGroupNodeDTO[] nodes = new TSBSPGroupNodeDTO[g.getGroupNodes().length];
		for(int n=0; n < g.getGroupNodes().length; n++) {
			TSBSPGroupNodeDTO dto = new TSBSPGroupNodeDTO();
			TSBSPGroupNode node = g.getGroupNodes()[n];
			
			dto.setIndex(n);
			dto.setCoeff(node.getCoeff());
			dto.setPolyNum(node.getPoly());
			dto.setFront(node.getFront());
			dto.setBack(node.getBack());
			
			nodes[n] = dto;
		}
		link.setNodes(nodes);
		
		return link;
	}

	
	//---------------------------------------------------------------------------------
	@Override
	public DataFile fromDTO(TransferObject source) {
		
		DTSRootDTO dto = (DTSRootDTO)source;
		
		DynamixThreeSpaceModel dts = new DynamixThreeSpaceModel();
		dts.setDir(FileType.DTS);
		dts.setExt(FileType.DTS);
		dts.setFileName(dto.getFileName() + ".DTS");
		
		
		List<TSObject> meshes = new ArrayList<TSObject>();
		for(int i=0; i < dto.getShapes().length; i++) {
			meshes.add(readTSObjectDTO(dto.getShapes()[i]));
		}
		dts.setMeshes(meshes);
		
		return dts;
	}

	
	private TSObject readTSObjectDTO(TSObjectDTO dto) {
		
		System.out.println(dto.getClass().getSimpleName());
		
		if(dto.getClass().getSimpleName().equals("TSBasePartDTO")) {
			return parseTSBasePartDTO((TSBasePartDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSPartListDTO")) {
			return parseTSPartListDTO((TSPartListDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSShapeDTO")) {
			return parseTSShapeDTOP((TSShapeDTO) dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("ANShapeDTO")) {
			return parseANShapeDTO((ANShapeDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSBSPPartDTO")) {
			return parseTSBSPPartDTO((TSBSPPartDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSGroupDTO")) {
			return parseTSGroupDTO((TSGroupDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSPolyDTO")) {
			return parseTSPolyDTO((TSPolyDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSSolidPolyDTO")) {
			return parseTSSolidPolyDTO((TSSolidPolyDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSTexture4PolyDTO")) {
			return parseTSTexture4PolyDTO((TSTexture4PolyDTO)dto);
		}
		
		if(dto.getClass().getSimpleName().equals("TSShadedPolyDTO")) {
			return parseTSShadedPolyDTO((TSShadedPolyDTO)dto);
		}
		
		if(dto.getClass().getSimpleName().equals("TSGouraudPolyDTO")) {
			return parseTSGouradPolyDTO((TSGouraudPolyDTO)dto);
		}
		
		if(dto.getClass().getSimpleName().equals("TSCellAnimPartDTO")) {
			return parseTSCellAnimPartDTO((TSCellAnimPartDTO) dto);
		}
		
		if(dto.getClass().getSimpleName().equals("ANAnimListDTO")) {
			return parseANAnimListDTO((ANAnimListDTO) dto);
		}
		
		if(dto.getClass().getSimpleName().equals("ANCyclicSequenceDTO")) {
			return parseANCylicSequenceDTO((ANCyclicSequenceDTO)dto);
		}
		
		if(dto.getClass().getSimpleName().equals("ANSequenceDTO")) {
			return parseANSequenceDTO((ANSequenceDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSBitmapPartDTO")) {
			return parseTSBitmapPartDTO((TSBitmapPartDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSDetailPartDTO")) {
			return partTSDetailPartDTO((TSDetailPartDTO)dto, null);
		}
		
		if(dto.getClass().getSimpleName().equals("TSBSPGroupDTO")) {
			return parseTSBSPGroupDTO((TSBSPGroupDTO)dto, null);
		}
		return null;
	}
	
	private TSBasePart parseTSBasePartDTO(TSBasePartDTO dto, TSBasePart link) {
		
		if(link == null) {
			link = new TSBasePart();
		}
		
		link.setTransform((short)dto.getTransform());
		
		link.setUID((short)dto.getTransformId());
		
		link.setRadius((short)Math.round(dto.getRadius()));
		
		link.setCenter(vec3FloatToShort(dto.getCenter()));
		
		return link;
	}
	
	private TSPartList parseTSPartListDTO(TSPartListDTO o, TSPartList link) {
		
		if(link == null) {
			link = new TSPartList();
		}
		link = (TSPartList) parseTSBasePartDTO((TSBasePartDTO)o, (TSBasePart) link);
		
		TSObject[] parts = new TSObject[o.getParts().length];
		for(int p=0; p < o.getParts().length; p++) {
			parts[p] = readTSObjectDTO(o.getParts()[p]);
		}
		link.setParts(parts);
		
		return link;
	}
	
	private TSShape parseTSShapeDTOP(TSShapeDTO s, TSShape link) {
		
		if(link == null) {
			link = new TSShape();
		}
		
		link = (TSShape) parseTSPartListDTO((TSPartListDTO)s, (TSPartList)link);
		
		short[] seqs = new short[s.getSequences().length];
		for(int q=0; q < s.getSequences().length; q++) {
			seqs[q] = (short)s.getSequences()[q];
		}
		link.setSequenceList(seqs);
		
		short[] trs = new short[s.getTransforms().length];
		for(int r=0; r < s.getTransforms().length; r++) {
			trs[r] = (short)s.getTransforms()[r];
		}
		link.setTransformList(trs);
		
		return link;
	}
	
	private ANShape parseANShapeDTO(ANShapeDTO s, ANShape link) {
		
		if(link == null) {
			link = new ANShape();
		}
		
		link = (ANShape) parseTSShapeDTOP((TSShapeDTO)s, (TSShape)link);
		
		ANAnimList animList = (ANAnimList)readTSObjectDTO(s.getAnimationList());
		
		link.setAnimationList(animList);
		
		return link;
	}
	
	private TSBSPPartNode parseTSBSPPartNodeDTO(TSBSPPartNodeDTO dto) {
		TSBSPPartNode node = new TSBSPPartNode();
		
		node.setNormal(vec3FloatToShort(dto.getNormal()));
		
		node.setCoeff(dto.getCoeff());
		
		node.setFront((short)dto.getFront());
		
		node.setBack((short)dto.getBack());
		
		return node;
	}
	
	private TSBSPPart parseTSBSPPartDTO(TSBSPPartDTO p, TSBSPPart link) {
		
		if(link == null) {
			link = new TSBSPPart();	
		}
		
		link = (TSBSPPart) parseTSPartListDTO((TSPartListDTO)p, (TSBSPPart)link);
		
		TSBSPPartNode[] nodes = new TSBSPPartNode[p.getNodes().length];
		
		for(int n=0; n < p.getNodes().length; n++) {
			nodes[n] = parseTSBSPPartNodeDTO(p.getNodes()[n]);
		}
		link.setNodes(nodes);
		
		short[] transfrms = new short[p.getTransformIds().length];
		for(int t=0; t < p.getTransformIds().length; t++) {
			transfrms[t] = (short)p.getTransformIds()[t]; 
		}
		link.setTransforms(transfrms);
		
		return link;
	}
	
	private TSGroup parseTSGroupDTO(TSGroupDTO g, TSGroup link) {
		
		if(link == null) {
			link = new TSGroup();	
		}
		
		link = (TSGroup)parseTSBasePartDTO((TSBasePartDTO)g, (TSBasePart)link);
		
		short[] indexes = new short[g.getIndexes().length];
		for(int i=0; i < g.getIndexes().length; i++) {
			indexes[i] = (short)g.getIndexes()[i]; 
		}
		link.setIndexes(indexes);
		
		Vec3Short[] verts = new Vec3Short[g.getVertices().length];
		for(int p=0; p < g.getVertices().length; p++) {
			verts[p] =  vec3FloatToShort(g.getVertices()[p]);
		}
		link.setPoints(verts);
		
		TSSurfaceEntry[] surfaces = new TSSurfaceEntry[g.getSurfaces().length];
		for(int s=0; s < g.getSurfaces().length; s++) {
			TSSurfaceEntryDTO e = g.getSurfaces()[s];
			TSSurfaceEntry srf = new TSSurfaceEntry();

			srf.setFrontColor((short)e.getFront().getColor());
			srf.setFrontFlag((short)e.getFront().getFlag());
			srf.setFrontLineColor((short)e.getFront().getEdgeColor());
			srf.setFrontLineFlag((short)e.getFront().getEdgeFlag());
			srf.setBackColor((short)e.getBack().getColor());
			srf.setBackColorFlag((short)e.getBack().getFlag());
			srf.setBackLineColor((short)e.getBack().getEdgeColor());
			srf.setBackLineFlag((short)e.getBack().getEdgeFlag());
			
			surfaces[s] = srf;			
		}
		link.setSurfaces(surfaces);
		
		TSObject[] parts = new TSObject[g.getPolys().length];
		for(int l=0; l < g.getPolys().length; l++) {
			parts[l] = readTSObjectDTO(g.getPolys()[l]);
		}
		link.setPolys(parts);
		
		return link;		
	}
	
	private TSPoly parseTSPolyDTO(TSPolyDTO p, TSPoly link) {
		
		if(link == null) {
			link = new TSPoly();
		} 
		
		link.setNormal((short)p.getNormal());
		link.setCenter((short)p.getCenter());
		link.setVertexCount((short)p.getVertexTotal());
		link.setVertexList((short)p.getVertixListStartNum());
		
		return link;
	}
	
	private TSSolidPoly parseTSSolidPolyDTO(TSSolidPolyDTO p, TSSolidPoly link) {
		
		if(link == null) {
			link = new TSSolidPoly();
		}
		
		link = (TSSolidPoly) parseTSPolyDTO((TSPolyDTO)p, (TSPoly)link);
		
		link.setColorIndexId((short)(p.getSurfaceNum() * 4));
		
		return link;
	}
	
	private TSShadedPoly parseTSShadedPolyDTO(TSShadedPolyDTO p) {
		
		TSShadedPoly t = new TSShadedPoly();
		
		t = (TSShadedPoly) parseTSSolidPolyDTO((TSSolidPolyDTO)p, t);
		
		return t;
	}
	
	private TSTexture4Poly parseTSTexture4PolyDTO(TSTexture4PolyDTO p) {
		
		TSTexture4Poly t = new TSTexture4Poly();
		
		t = (TSTexture4Poly) parseTSSolidPolyDTO((TSSolidPolyDTO)p, (TSSolidPoly)t);
		
		return t;
	}
	
	private TSGouraudPoly parseTSGouradPolyDTO(TSGouraudPolyDTO p) {
		
		TSGouraudPoly t = new TSGouraudPoly();
		
		t = (TSGouraudPoly) parseTSSolidPolyDTO((TSSolidPolyDTO)p, (TSSolidPoly)t);
		
		t.setNormalList((short)p.getNormalList());
		
		return t;
	}
	
	private TSCellAnimPart parseTSCellAnimPartDTO(TSCellAnimPartDTO cell) {
		
		TSCellAnimPart t = new TSCellAnimPart();
		
		t = (TSCellAnimPart) parseTSPartListDTO((TSPartListDTO)cell, (TSPartList)t);
		
		t.setAnimSequence((short)cell.getAnimSequenceNum());
		
		return t;
	}

	
	private ANAnimList parseANAnimListDTO(ANAnimListDTO anim) {
		
		ANAnimList t = new ANAnimList();
		
		TSObject[] seqs = new TSObject[anim.getSequences().length];
		for(int s=0; s < anim.getSequences().length; s++) {
			seqs[s] = readTSObjectDTO(anim.getSequences()[s]);
		}
		t.setSequences(seqs);
		
		ANAnimListTransition[] transitions = new ANAnimListTransition[anim.getTransitions().length];
		for(int a=0; a < anim.getTransitions().length; a++) {
			ANAnimListTransition antr = new ANAnimListTransition();
			AnimTransitionDTO trs = anim.getTransitions()[a];
			
			antr.setTick((short)trs.getTick());
			antr.setDestSequence((short)trs.getDestination_seq());
			antr.setDestFrame((short)trs.getDestination_frame());
			antr.setGroundMovement((short)trs.getGroundMovement());
			
			transitions[a] = antr;
		}
		t.setTransitions(transitions);
		
		ANAnimListTransform[] transforms = new ANAnimListTransform[anim.getTransforms().length];
		for(int b=0; b < anim.getTransforms().length; b++) {
			ANAnimListTransform atr = new ANAnimListTransform();
			AnimTransformDTO trf = anim.getTransforms()[b];
			
			atr.setRotation(vec3FloatToShort(trf.getRotation()));
			atr.setTranslation(vec3FloatToShort(trf.getTranslation()));
			
			transforms[b] = atr;
		}
		t.setTransforms(transforms);
		
		short[] defTransform = new short[anim.getDefaultTransforms().length];
		for(int d=0; d < anim.getDefaultTransforms().length; d++) {
			defTransform[d] = (short)anim.getDefaultTransforms()[d];
		}
		t.setDefaultTransforms(defTransform);
		
		Vec2Short[] relations = new Vec2Short[anim.getTransformIdLinks().length];
		for(int r=0; r < anim.getTransformIdLinks().length; r++) {
			int[] rel = anim.getTransformIdLinks()[r];
			relations[r] = new Vec2Short((short)rel[0], (short)rel[1]);
		}
		t.setRelations(relations);
		
		return t;
	}
	
	private ANSequence parseANSequenceDTO(ANSequenceDTO seq, ANSequence link) {
		
		if(link == null) {
			link = new ANSequence();
		}
		
		link.setTick((short)seq.getTick());
		link.setPriority((short)seq.getPriority());
		link.setGroundMovement((short)seq.getGroundMoveFlag());
		
		ANSequenceFrame[] frames = new ANSequenceFrame[seq.getFrames().length];
		for(int f=0; f < seq.getFrames().length; f++) {
			SequenceFrameDTO frame = seq.getFrames()[f];
			ANSequenceFrame anFrame = new ANSequenceFrame();
			
			anFrame.setTick((short)frame.getTick());
			anFrame.setNumTransitions((short)frame.getNumberTransitions());
			anFrame.setFirstTransition((short)frame.getFirstTransition());
			
			frames[f] = anFrame;
		}
		link.setFrames(frames);
		
		
		short[] partIds = new short[seq.getPartIds().length];
		for(int p=0; p < seq.getPartIds().length; p++) {
			partIds[p] = (short)seq.getPartIds()[p];
		}
		link.setPartIds(partIds);
		
		short[] transformIndices = new short[seq.getTransformIndexes().length];
		for(int t=0; t < seq.getTransformIndexes().length; t++) {
			transformIndices[t] = (short)seq.getTransformIndexes()[t];
		}
		link.setTransformIndices(transformIndices);
		
		return link;
	}
	
	private ANCyclicSequence parseANCylicSequenceDTO(ANCyclicSequenceDTO seq) {
		
		ANCyclicSequence t = new ANCyclicSequence();
		
		t = (ANCyclicSequence) parseANSequenceDTO((ANSequenceDTO)seq, (ANSequence)t);
		
		return t;
	}
	
	private TSBitmapPart parseTSBitmapPartDTO(TSBitmapPartDTO bmp, TSBitmapPart link) {
		
		if(link == null) {
			link = new TSBitmapPart();
		}
		
		link = (TSBitmapPart) parseTSBasePartDTO((TSBasePartDTO)bmp, (TSBasePart)link);
		
		link.setBmpTag((short)bmp.getBmpFrameNum());
		link.setOfsX((byte)bmp.getOffsetX());
		link.setOfsY((byte)bmp.getOffsetY());
		
		return link;
	}
	
	private TSDetailPart partTSDetailPartDTO(TSDetailPartDTO det, TSDetailPart link) {
		
		if(link == null) {
			link = new TSDetailPart();
		}
		
		link = (TSDetailPart) parseTSPartListDTO(det, (TSPartList)link);
		
		short[] details = new short[det.getDetails().length];
		for(int d=0; d < det.getDetails().length; d++) {
			details[d] = (short)det.getDetails()[d];
		}
		link.setDetails(details);
		
		return link;
	}
	
	private TSBSPGroup parseTSBSPGroupDTO(TSBSPGroupDTO g, TSBSPGroup link) {
		
		if(link == null) {
			link = new TSBSPGroup();
		}
		
		link = (TSBSPGroup) parseTSGroupDTO((TSGroupDTO)g, (TSGroup)link);
		
		TSBSPGroupNode[] nodes = new TSBSPGroupNode[g.getNodes().length];
		for(int n=0; n < g.getNodes().length; n++) {
			TSBSPGroupNodeDTO dto = g.getNodes()[n]; 
			TSBSPGroupNode node = new TSBSPGroupNode(
						(short)dto.getCoeff(),
						(short)dto.getPolyNum(),
						(short)dto.getFront(),
						(short)dto.getBack()
					);
			
			nodes[n] = node;
		}
		link.setGroupNodes(nodes);
		
		return link;
	}
	
	
	
	//---------------------------------------------------------------------------------
	
	private float[] vec3ToFloat(Vec3Short v) {
		
		float[] f = new float[3];
		f[0] = (float)v.getX();
		f[1] = (float)v.getY();
		f[2] = (float)v.getZ();
		
		return f;
	}
	
	private Vec3Short vec3FloatToShort(float[] vec) {
		
		Vec3Short vec3 = new Vec3Short();
		
		vec3.setX((short)Math.round(vec[0]));
		vec3.setY((short)Math.round(vec[1]));
		vec3.setZ((short)Math.round(vec[2]));
		
		return vec3;
	}
}
