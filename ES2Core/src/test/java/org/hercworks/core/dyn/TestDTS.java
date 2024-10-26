package org.hercworks.core.dyn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.hercworks.core.io.transform.dbsim.DTSModelTransformer;
import org.junit.Test;

public class TestDTS {

	@Test
	public void testDTSModel() {
		
		try {
//			String targURL = "E:\\ES2_OS\\dev\\earthsiege2\\unpack\\simvol0\\dts\\ACHILLES.DTS";
		
			String targURL = "E:\\ES2_OS\\dev\\earthsiege2\\unpack\\simvol0\\dts\\TOMA_DEB.DTS";
			
			DTSModelTransformer transform = new DTSModelTransformer();
			
			FileInputStream fizz = new FileInputStream(new File(targURL));
			
			byte[] fileData = fizz.readAllBytes();
			fizz.close();
			transform.bytesToObject(fileData);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
