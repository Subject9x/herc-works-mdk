package org.hercworks.core.io.transform.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.shell.HardpointOverlayConfig;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class HardpointOverlayTransformer extends ThreeSpaceByteTransformer {

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		if(inputArray == null || inputArray.length <= 0) {
			//TODO  - error for empty byte array
			return null;
		}
		setBytes(inputArray);
		
		HardpointOverlayConfig rprHercOverlay = new HardpointOverlayConfig();
		rprHercOverlay.setFileName("RPR_HOTS");
		rprHercOverlay.setExt(FileType.DAT);
		rprHercOverlay.setDir(FileType.GAM);
		rprHercOverlay.setRawBytes(inputArray);
		
		HardpointOverlayConfig.Herc[] entries = new HardpointOverlayConfig.Herc[indexShortLE()];
		
		for(int i=0; i < entries.length; i++) {
			HardpointOverlayConfig.Herc entry = rprHercOverlay.newEntry();
			
			entry.setHercId(indexShortLE());
			HardpointOverlayConfig.Herc.OverlayArea[] coords = new HardpointOverlayConfig.Herc.OverlayArea[indexShortLE()];
			
			for(int c=0; c < coords.length; c++) {
				HardpointOverlayConfig.Herc.OverlayArea seg = entry.newSegment();
				seg.setId(c);
				seg.setX(indexIntLE());
				seg.setY(indexIntLE());
				seg.setWidth(indexIntLE());
				seg.setHeight(indexIntLE());
				coords[c] = seg;
			}
			entry.setAreas(coords);
			entries[i] = entry;
		}
		rprHercOverlay.setEntries(entries);
		
		return rprHercOverlay;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		HardpointOverlayConfig data = (HardpointOverlayConfig)source;
		
		out.write(writeShortLE((short)data.getEntries().length));
		
		for(int i=0; i < data.getEntries().length; i++) {
			HardpointOverlayConfig.Herc entry = data.getEntries()[i];
			
			out.write(writeShortLE(entry.getHercId()));
			out.write(writeShortLE((short)(entry.getAreas().length)));
			for(int c=0; c < entry.getAreas().length; c++) {
				HardpointOverlayConfig.Herc.OverlayArea seg = entry.getAreas()[c];
				
				out.write(writeIntLE(seg.getX()));
				out.write(writeIntLE(seg.getY()));
				out.write(writeIntLE(seg.getWidth()));
				out.write(writeIntLE(seg.getHeight()));
				
			}
			out.flush();
		}
		
		return out.toByteArray();
	}

}
