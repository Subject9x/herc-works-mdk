package org.hercworks.core.api;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.hercworks.core.data.file.dyn.DynamixThreeSpaceModel;
import org.hercworks.core.io.transform.dbsim.DTSModelTransformer;
import org.junit.Test;

public class TestDTSLoader {

	
	@Test
	public void testDTSPull() {
		
		DTSModelTransformer transformDTS = new DTSModelTransformer();
		
		
//		File dtsFile = new File("e:/es2_os/dev/earthsiege2/unpack/simvol0/dts/TOMA_DEB.dts");
		File dtsFile = new File("e:/es2_os/dev/earthsiege2/unpack/simvol0/dts/COLOSSUS.dts");
		
		DynamixThreeSpaceModel dts = null;
		try {
			FileInputStream fizz = new FileInputStream(dtsFile);
			
			byte[] b = fizz.readAllBytes();
			fizz.close();
			
			dts = (DynamixThreeSpaceModel)transformDTS.bytesToObject(b);
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		} 
		catch (IOException e) {
			e.printStackTrace(System.out);
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		assertNotNull(dts);
	}
}
