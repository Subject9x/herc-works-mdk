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

import org.hercworks.core.data.file.dat.shell.ArmWeap;
import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.io.transform.shell.ArmWeapTransformer;
import org.hercworks.core.io.transform.shell.HercsStartTransformer;
import org.hercworks.core.io.transform.shell.WeaponsDatTransformer;
import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.svc.impl.ArmWeapDTOServiceImpl;
import org.hercworks.transfer.svc.impl.StartingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.WeaponsDatShellDTOServiceImpl;
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
			
			File dat = new File(absolutePath + "/HERCS.DAT");
//			
			assertTrue(dat.exists());
			assertTrue(!dat.isDirectory());
//			
			FileInputStream fizz = new FileInputStream(dat);
			byte[] bytes = fizz.readAllBytes();
			fizz.close();
//			
			HercsStartTransformer hercsConvert = new HercsStartTransformer();
			Hercs armHercDat = (Hercs) hercsConvert.bytesToObject(bytes);
//			
			assertNotNull(armHercDat);
//			
			armHercDat.setFileName("HERCS");
			armHercDat.setExt(FileType.DAT);
			armHercDat.setGameDirPath("/GAM/");
//			
//			
			StartingHercsDTOService dtoSvc = new StartingHercsDTOServiceImpl();
			StartHercsDTO dto = dtoSvc.convertToDTO(armHercDat);
//			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			
			File export = new File(resourceDirectory+ "/HERCS.json" );
			objectMapper.writeValue(export, dto);
			
			File exportBytes = new File(resourceDirectory+ "/HERCS.DAT");
			
			StartHercsDTO readDTO = (StartHercsDTO)objectMapper.readValue(export, StartHercsDTO.class);
			
			armHercDat = dtoSvc.fromDTO(readDTO);
			
			FileOutputStream foss = new FileOutputStream(exportBytes);
			foss.write(hercsConvert.objectToBytes(armHercDat));
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
	
	@Test
	public void testArmWeap() {
		
		try {
			Path resourceDirectory = Paths.get("src","test","resources");
			String absolutePath = resourceDirectory.toFile().getAbsolutePath();
			
			File dat = new File(absolutePath + "/ARM_WEAP.DAT");
//			
			assertTrue(dat.exists());
			assertTrue(!dat.isDirectory());
//			
			FileInputStream fizz = new FileInputStream(dat);
			byte[] bytes = fizz.readAllBytes();
			fizz.close();
//			
			ArmWeapTransformer transform = new ArmWeapTransformer();
 			ArmWeap data = (ArmWeap) transform.bytesToObject(bytes);
//			
			assertNotNull(data);
//			
			data.setFileName("HERC_INF");
			data.setExt(FileType.DAT);
			data.setGameDirPath("/GAM/");
//			
//			
			ArmWeapDTOService dtoSvc = new ArmWeapDTOServiceImpl();
//			
			ArmWeapDTO dto = dtoSvc.convertToDTO(data);
//			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			
			File export = new File(resourceDirectory+ "/ARM_WEAP.json" );
			objectMapper.writeValue(export, dto);
			
			File exportBytes = new File(resourceDirectory+ "/ARM_WEAP_2.DAT");
			
			ArmWeapDTO readDTO = (ArmWeapDTO)objectMapper.readValue(export, ArmWeapDTO.class);
			
			data = dtoSvc.fromDTO(readDTO);
			
			FileOutputStream foss = new FileOutputStream(exportBytes);
			foss.write(transform.objectToBytes(data));
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
	
	@Test
	public void testWeaponsDat() {
		
		try {
			Path resourceDirectory = Paths.get("src","test","resources");
			String absolutePath = resourceDirectory.toFile().getAbsolutePath();
			
			File dat = new File(absolutePath + "/WEAPONS.DAT");
//			
			assertTrue(dat.exists());
			assertTrue(!dat.isDirectory());
//			
			FileInputStream fizz = new FileInputStream(dat);
			byte[] bytes = fizz.readAllBytes();
			fizz.close();
//			
			WeaponsDatTransformer transform = new WeaponsDatTransformer();
 			WeaponsDat data = (WeaponsDat)transform.bytesToObject(bytes);
//			
			assertNotNull(data);
			data.setGameDirPath("/GAM/");
			
			WeaponsDatShellDTOService dtoSvc = new WeaponsDatShellDTOServiceImpl();
			WeaponsDatDTO dto = dtoSvc.convertToDTO(data);
//			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			
			File export = new File(resourceDirectory+ "/WEAPONS.json" );
			objectMapper.writeValue(export, dto);
			
			File exportBytes = new File(resourceDirectory+ "/WEAPONS2.DAT");
			
			WeaponsDatDTO readDTO = (WeaponsDatDTO)objectMapper.readValue(export, WeaponsDatDTO.class);
			
			data = dtoSvc.fromDTO(readDTO);
			
			FileOutputStream foss = new FileOutputStream(exportBytes);
			foss.write(transform.objectToBytes(data));
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
