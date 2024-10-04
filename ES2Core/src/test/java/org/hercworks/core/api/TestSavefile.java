package org.hercworks.core.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.hercworks.core.data.file.sav.PlayerSave;
import org.hercworks.core.io.transform.common.PlayerSaveTransform;
import org.junit.Test;

public class TestSavefile {

	
	@Test
	public void testSavefile() {
			
		File file = new File("e://es2_os/dev/earthsiege2/sav/GAME_5.sav");
		
		assertTrue(file.isFile());
		
		FileInputStream fizz;
		try {
			fizz = new FileInputStream(file);
			byte[] inputArr = fizz.readAllBytes();
			
			fizz.close();
			
			PlayerSaveTransform playerSaveTransformer = new PlayerSaveTransform();
			
			PlayerSave save = (PlayerSave)playerSaveTransformer.bytesToObject(inputArr);
			
			assertNotNull(save);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}


