package org.hercworks.core.io.transform.dbsim;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dbsim.PaperDollGraphic;
import org.hercworks.core.data.file.dbsim.PaperDollGraphic.HardpointEntry;
import org.hercworks.core.data.file.dbsim.PaperDollGraphic.ViewEntry;
import org.hercworks.core.data.file.dbsim.PaperDollGraphic.ViewRegion;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class PaperDiagramGraphTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null || inputArray.length <= 0) {
			return null;
		}
		setBytes(inputArray);
		
		PaperDollGraphic pdg = new PaperDollGraphic();
		pdg.setRawBytes(inputArray);
		pdg.setExt(FileType.PDG);
		pdg.setDir(FileType.PDG);
		
		pdg.setTotalViews(indexIntLE());
		pdg.setEntries(new ViewEntry[pdg.getTotalViews()]);
		
		//Structure Damage view
		ViewEntry structure = pdg.newViewEntry();
		structure.setOrigin(new Point(indexIntLE(), indexIntLE()));
		structure.setSize(new Point(indexIntLE(), indexIntLE()));
		structure.setRegions(new ViewRegion[indexIntLE()]);
		for(int r = 0; r< structure.getRegions().length; r++) {
			ViewRegion region = pdg.newViewRegion();
			region.setIndex(indexIntLE());
			region.setTopLeft(new Point(indexIntLE(), indexIntLE()));
			region.setBottomRight(new Point(indexIntLE(), indexIntLE()));
			region.setUnk_val(indexIntLE());
			region.setSpacer(indexIntLE());
			structure.getRegions()[r] = region;
		}
		pdg.getEntries()[0] = structure;
		
		//Internal Damage view
		ViewEntry internals = pdg.newViewEntry();
		internals.setOrigin(new Point(indexIntLE(), indexIntLE()));
		internals.setSize(new Point(indexIntLE(), indexIntLE()));
		internals.setRegions(new ViewRegion[indexIntLE()]);
		for(int r = 0; r< internals.getRegions().length; r++) {
			ViewRegion region = pdg.newViewRegion();
			region.setIndex(indexIntLE());
			region.setTopLeft(new Point(indexIntLE(), indexIntLE()));
			region.setBottomRight(new Point(indexIntLE(), indexIntLE()));
			region.setUnk_val(indexIntLE());
			region.setSpacer(indexIntLE());
			internals.getRegions()[r] = region;
		}
		pdg.getEntries()[1] = internals;
		
		
		//HUD-F5 Target view
		ViewEntry hudTarget = pdg.newViewEntry();
		hudTarget.setOrigin(new Point(indexIntLE(), indexIntLE()));
		hudTarget.setSize(new Point(indexIntLE(), indexIntLE()));
		hudTarget.setRegions(new ViewRegion[indexIntLE()]);
		for(int r = 0; r< hudTarget.getRegions().length; r++) {
			ViewRegion region = pdg.newViewRegion();
			region.setIndex(indexIntLE());
			region.setTopLeft(new Point(indexIntLE(), indexIntLE()));
			region.setBottomRight(new Point(indexIntLE(), indexIntLE()));
			region.setUnk_val(indexIntLE());
			region.setSpacer(indexIntLE());
			hudTarget.getRegions()[r] = region;
		}
		pdg.getEntries()[2] = hudTarget;
		
		pdg.setHardpoints(new HardpointEntry[indexIntLE()]);
		for(int h = 0; h < pdg.getHardpoints().length; h++) {
			HardpointEntry hardpoint = pdg.newHardpointEntry();
			hardpoint.setOrigin(new Point(indexIntLE(), indexIntLE()));
			hardpoint.setUnk1(indexIntLE());
			hardpoint.setUnk2(indexIntLE());
			hardpoint.setSpacer(indexIntLE());
			pdg.getHardpoints()[h] = hardpoint;
		}
		
		return pdg;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		PaperDollGraphic pdg = (PaperDollGraphic)source;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write(writeIntLE(pdg.getTotalViews()));
		
		for(int v = 0; v < pdg.getEntries().length; v++) {
			ViewEntry view = pdg.getEntries()[v];
			
			out.write(writeIntLE(view.getOrigin().x));
			out.write(writeIntLE(view.getOrigin().y));
			out.write(writeIntLE(view.getSize().x));
			out.write(writeIntLE(view.getSize().y));
			out.write(writeIntLE(view.getRegions().length));
			
			for(int r = 0; r < view.getRegions().length; r++) {
				ViewRegion region = view.getRegions()[r];
				out.write(writeIntLE(region.getIndex()));
				out.write(writeIntLE(region.getTopLeft().x));
				out.write(writeIntLE(region.getTopLeft().y));
				out.write(writeIntLE(region.getBottomRight().x));
				out.write(writeIntLE(region.getBottomRight().y));
				out.write(writeIntLE(region.getUnk_val()));
				out.write(writeIntLE(region.getSpacer()));
			}
		}
		
		out.write(writeIntLE(pdg.getHardpoints().length));
		for(int h = 0; h < pdg.getHardpoints().length; h++) {
			HardpointEntry hpoint = pdg.getHardpoints()[h];
			out.write(writeIntLE(hpoint.getOrigin().x));
			out.write(writeIntLE(hpoint.getOrigin().y));
			out.write(writeIntLE(hpoint.getUnk1()));
			out.write(writeIntLE(hpoint.getUnk2()));
			out.write(writeIntLE(hpoint.getSpacer()));
		}
		
		return out.toByteArray();
	}

}
