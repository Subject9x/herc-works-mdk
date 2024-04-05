package org.hercworks.transfer.svc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hercworks.core.data.file.dat.shell.ArmHerc;
import org.hercworks.core.io.transform.shell.ArmHercTransformer;
import org.hercworks.transfer.dto.shell.ArmHercDTO;
import org.hercworks.transfer.svc.impl.ArmHercDTOServiceImpl;
import org.hercworks.voln.FileType;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UniversalJsonTest {

	@Test
	public void testJson() {
		
		try {
			Path resourceDirectory = Paths.get("src","test","resources");
			String absolutePath = resourceDirectory.toFile().getAbsolutePath();
			
			File dat = new File(absolutePath + "/ARM_APOC.DAT");
//			
			assertTrue(dat.exists());
			assertTrue(!dat.isDirectory());
//			
			FileInputStream fizz = new FileInputStream(dat);
			byte[] bytes = fizz.readAllBytes();
			fizz.close();
//			
			ArmHercTransformer armHercTransform = new ArmHercTransformer();
 			ArmHerc armHercDat = (ArmHerc) armHercTransform.bytesToObject(bytes);
//			
			assertNotNull(armHercDat);
//			
			armHercDat.setFileName("ARM_APOC");
			armHercDat.setExt(FileType.DAT);
			armHercDat.setGameDirPath("/GAM/");
//			
//			
			ArmHercDTOServiceImpl dtoSvc = new ArmHercDTOServiceImpl();
//			
			ArmHercDTO dto = dtoSvc.convertToDTO(armHercDat);
//			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			
			File export = new File(resourceDirectory+ "/ARM_APOC.json" );
			objectMapper.writeValue(export, dto);
			
			File exportBytes = new File(resourceDirectory+ "/ARM_APOC_2.DAT");
			
			ArmHercDTO readDTO = (ArmHercDTO)objectMapper.readValue(export, ArmHercDTO.class);
			
			armHercDat = dtoSvc.fromDTO(readDTO);
			
			FileOutputStream foss = new FileOutputStream(exportBytes);
			foss.write(armHercTransform.objectToBytes(armHercDat));
			foss.close();
			
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
