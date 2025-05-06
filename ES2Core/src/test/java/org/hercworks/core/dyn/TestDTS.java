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
			String targURL = "E:\\ES2_OS\\dev\\earthsiege2\\unpack\\simvol0\\dts\\ROCKETS_SPLIT_1.DTS";
//			String targURL = "E:\\ES2_OS\\dev\\earthsiege2\\unpack\\simvol0\\dts\\BULLETS.DTS";
//			String targURL = "E:\\ES2_OS\\dev\\earthsiege2\\unpack\\simvol0\\dts\\ACHI_DEB.DTS";
//			String targURL = "E:\\ES2_OS\\dev\\earthsiege2\\unpack\\simvol0\\dts\\BASES_AN.DTS";
//			String targURL = "E:\\ES2_OS\\dev\\earthsiege2\\unpack\\simvol0\\dts\\ACHILLES.DTS";
			
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
