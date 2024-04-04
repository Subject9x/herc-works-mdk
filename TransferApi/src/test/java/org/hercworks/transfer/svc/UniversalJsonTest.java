package org.hercworks.transfer.svc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.io.transform.shell.ArmHercTransformer;
import org.hercworks.voln.FileType;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UniversalJsonTest {

	@Test
	public void testJson() {
		
		try {
			Path resourceDirectory = Paths.get("src","test","resources");
			String absolutePath = resourceDirectory.toFile().getAbsolutePath();
			
			File dat = new File(absolutePath + "/ARM_RAPT.DAT");
			
			assertTrue(dat.exists());
			assertTrue(!dat.isDirectory());
			
			FileInputStream fizz = new FileInputStream(dat);
			byte[] bytes = fizz.readAllBytes();
			fizz.close();
			
			ArmHercTransformer armHercTransform = new ArmHercTransformer();
 			ArmHerc armHercDat = (ArmHerc) armHercTransform.bytesToObject(bytes);
			
			assertNotNull(armHercDat);
			
			armHercDat.setFileName("ARM_RAPT");
			armHercDat.setExt(FileType.DAT);
			armHercDat.setGameDirPath("/GAM/");
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(new File(resourceDirectory+ "/ARM_RAPT.json" ), armHercDat);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
