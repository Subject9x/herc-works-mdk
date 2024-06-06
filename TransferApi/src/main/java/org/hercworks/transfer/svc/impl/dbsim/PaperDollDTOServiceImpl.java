package org.hercworks.transfer.svc.impl.dbsim;

import java.awt.Point;

import org.hercworks.core.data.file.dbsim.PaperDollGraphic;
import org.hercworks.core.data.file.dbsim.PaperDollGraphic.HardpointEntry;
import org.hercworks.core.data.file.dbsim.PaperDollGraphic.ViewEntry;
import org.hercworks.core.data.file.dbsim.PaperDollGraphic.ViewRegion;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.PaperDollDTO;
import org.hercworks.transfer.dto.struct.dbsim.PaperDollHardpointDTO;
import org.hercworks.transfer.dto.struct.dbsim.PaperDollRegionDTO;
import org.hercworks.transfer.dto.struct.dbsim.PaperDollViewDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class PaperDollDTOServiceImpl implements GeneralDTOService{

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		PaperDollGraphic pdg = (PaperDollGraphic)source;
		PaperDollDTO dto = new PaperDollDTO();
		
		dto.setTotalViews(pdg.getTotalViews());

		if(pdg.getEntries() != null && pdg.getEntries().length > 0) {
			ViewEntry structView = pdg.getEntries()[0];
			PaperDollViewDTO struct = new PaperDollViewDTO();
			struct.setOrigin(new int[] {structView.getOrigin().x, structView.getOrigin().y});
			struct.setSize(new int[] {structView.getSize().x, structView.getSize().y});
			struct.setRegions(new PaperDollRegionDTO[structView.getRegions().length]);
			for(int r = 0; r < struct.getRegions().length; r++) {
				ViewRegion viewRegion = structView.getRegions()[r];
				PaperDollRegionDTO regionDTO = new PaperDollRegionDTO();
				regionDTO.setIndex(viewRegion.getIndex());
				regionDTO.setTopLeft(new int[] {viewRegion.getTopLeft().x, viewRegion.getTopLeft().y});
				regionDTO.setBotRight(new int[] {viewRegion.getBottomRight().x, viewRegion.getBottomRight().y});
				regionDTO.setUnk_val(viewRegion.getUnk_val());
				regionDTO.setSpacer(viewRegion.getSpacer());
				struct.getRegions()[r] = regionDTO;
			}
			dto.setStructure(struct);
		}

		if(pdg.getEntries() != null && pdg.getEntries().length > 1) {
			ViewEntry inrtnlView = pdg.getEntries()[1];
			PaperDollViewDTO internal = new PaperDollViewDTO();
			internal.setOrigin(new int[] {inrtnlView.getOrigin().x, inrtnlView.getOrigin().y});
			internal.setSize(new int[] {inrtnlView.getSize().x, inrtnlView.getSize().y});
			internal.setRegions(new PaperDollRegionDTO[inrtnlView.getRegions().length]);
			for(int r = 0; r < inrtnlView.getRegions().length; r++) {
				ViewRegion viewRegion = inrtnlView.getRegions()[r];
				PaperDollRegionDTO regionDTO = new PaperDollRegionDTO();
				regionDTO.setIndex(viewRegion.getIndex());
				regionDTO.setTopLeft(new int[] {viewRegion.getTopLeft().x, viewRegion.getTopLeft().y});
				regionDTO.setBotRight(new int[] {viewRegion.getBottomRight().x, viewRegion.getBottomRight().y});
				regionDTO.setUnk_val(viewRegion.getUnk_val());
				regionDTO.setSpacer(viewRegion.getSpacer());
				internal.getRegions()[r] = regionDTO;
			}
			dto.setInternals(internal);
		}

		if(pdg.getEntries() != null && pdg.getEntries().length > 2) {
			ViewEntry unkView = pdg.getEntries()[2];
			
			PaperDollViewDTO unk = new PaperDollViewDTO();
			unk.setOrigin(new int[] {unkView.getOrigin().x, unkView.getOrigin().y});
			unk.setSize(new int[] {unkView.getSize().x, unkView.getSize().y});
			unk.setRegions(new PaperDollRegionDTO[unkView.getRegions().length]);
			for(int r = 0; r < unkView.getRegions().length; r++) {
				ViewRegion viewRegion = unkView.getRegions()[r];
				PaperDollRegionDTO regionDTO = new PaperDollRegionDTO();
				regionDTO.setIndex(viewRegion.getIndex());
				regionDTO.setTopLeft(new int[] {viewRegion.getTopLeft().x, viewRegion.getTopLeft().y});
				regionDTO.setBotRight(new int[] {viewRegion.getBottomRight().x, viewRegion.getBottomRight().y});
				regionDTO.setUnk_val(viewRegion.getUnk_val());
				regionDTO.setSpacer(viewRegion.getSpacer());
				unk.getRegions()[r] = regionDTO;
			}
			dto.setHudTarget(unk);
		}
		
		if(pdg.getHardpoints() != null && pdg.getHardpoints().length != 0) {
			for(int h = 0; h < pdg.getHardpoints().length; h++) {
				PaperDollHardpointDTO hpointDTO = new PaperDollHardpointDTO();
				HardpointEntry hardpoint = pdg.getHardpoints()[h];
				
				hpointDTO.setOrigin(new int[] {hardpoint.getOrigin().x, hardpoint.getOrigin().y});
				hpointDTO.setUnk1(hardpoint.getUnk1());
				hpointDTO.setUnk2(hardpoint.getUnk2());
				hpointDTO.setSpacer(hardpoint.getSpacer());
				dto.getHardpoints().put(h, hpointDTO);
			}
		}
		
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
	
		PaperDollDTO dto = (PaperDollDTO)source;
		PaperDollGraphic pdg = new PaperDollGraphic();
		pdg.setExt(FileType.PDG);
		pdg.setDir(FileType.PDG);
		
		int total = 0;
		if(dto.getStructure() != null) {
			total += 1;
		}
		if(dto.getInternals() != null) {
			total += 1;
		}
		if(dto.getHudTarget() != null) {
			total += 1;
		}
		
		pdg.setTotalViews(total);
		pdg.setEntries(new ViewEntry[total]);
		if(pdg.getTotalViews() > 0) {
			ViewEntry struct = pdg.newViewEntry();
			PaperDollViewDTO structDTO = dto.getStructure();
			
			struct.setOrigin(new Point(structDTO.getOrigin()[0], structDTO.getOrigin()[1]));
			struct.setSize(new Point(structDTO.getSize()[0], structDTO.getSize()[1]));
			struct.setRegions(new ViewRegion[structDTO.getRegions().length]);
			for(int r=0; r < structDTO.getRegions().length; r++) {
				PaperDollRegionDTO regionDTO = structDTO.getRegions()[r];
				ViewRegion region = pdg.newViewRegion();
				
				region.setIndex(regionDTO.getIndex());
				region.setTopLeft(new Point(regionDTO.getTopLeft()[0], regionDTO.getTopLeft()[1]));
				region.setBottomRight(new Point(regionDTO.getBotRight()[0], regionDTO.getBotRight()[1]));
				region.setUnk_val(regionDTO.getUnk_val());
				region.setSpacer(regionDTO.getSpacer());
				
				struct.getRegions()[r] = region;
			}
			pdg.getEntries()[0] = struct;
		}
		
		if(pdg.getTotalViews() > 1) {
			ViewEntry internals = pdg.newViewEntry();
			PaperDollViewDTO internDTO = dto.getInternals();
			
			internals.setOrigin(new Point(internDTO.getOrigin()[0], internDTO.getOrigin()[1]));
			internals.setSize(new Point(internDTO.getSize()[0], internDTO.getSize()[1]));
			internals.setRegions(new ViewRegion[internDTO.getRegions().length]);
			for(int r=0; r < internDTO.getRegions().length; r++) {
				PaperDollRegionDTO regionDTO = internDTO.getRegions()[r];
				ViewRegion region = pdg.newViewRegion();
				
				region.setIndex(regionDTO.getIndex());
				region.setTopLeft(new Point(regionDTO.getTopLeft()[0], regionDTO.getTopLeft()[1]));
				region.setBottomRight (new Point(regionDTO.getBotRight()[0], regionDTO.getBotRight()[1]));
				region.setUnk_val(regionDTO.getUnk_val());
				region.setSpacer(regionDTO.getSpacer());
				
				internals.getRegions()[r] = region;
			}
			pdg.getEntries()[1] = internals;
		}
		
		if(pdg.getTotalViews() > 2) {
			ViewEntry targetHud = pdg.newViewEntry();
			PaperDollViewDTO targetHudDTO = dto.getHudTarget();
			
			targetHud.setOrigin(new Point(targetHudDTO.getOrigin()[0], targetHudDTO.getOrigin()[1]));
			targetHud.setSize(new Point(targetHudDTO.getSize()[0], targetHudDTO.getSize()[1]));
			targetHud.setRegions(new ViewRegion[targetHudDTO.getRegions().length]);
			for(int r=0; r < targetHudDTO.getRegions().length; r++) {
				PaperDollRegionDTO regionDTO = targetHudDTO.getRegions()[r];
				ViewRegion region = pdg.newViewRegion();
				
				region.setIndex(regionDTO.getIndex());
				region.setTopLeft(new Point(regionDTO.getTopLeft()[0], regionDTO.getTopLeft()[1]));
				region.setBottomRight(new Point(regionDTO.getBotRight()[0], regionDTO.getBotRight()[1]));
				region.setUnk_val(regionDTO.getUnk_val());
				region.setSpacer(regionDTO.getSpacer());
				
				targetHud.getRegions()[r] = region;
			}
			pdg.getEntries()[2] = targetHud;
		}
		
		pdg.setHardpoints(new HardpointEntry[dto.getHardpoints().size()]);
		for(Integer i : dto.getHardpoints().keySet()) {
			PaperDollHardpointDTO hardpointDTO = dto.getHardpoints().get(i);
			HardpointEntry entry = pdg.newHardpointEntry();
			
			entry.setOrigin(new Point(hardpointDTO.getOrigin()[0], hardpointDTO.getOrigin()[1]));
			entry.setUnk1(hardpointDTO.getUnk1());
			entry.setUnk2(hardpointDTO.getUnk2());
			entry.setSpacer(hardpointDTO.getSpacer());
			
			pdg.getHardpoints()[i] = entry;
		}
		
		return pdg;
	}

}
