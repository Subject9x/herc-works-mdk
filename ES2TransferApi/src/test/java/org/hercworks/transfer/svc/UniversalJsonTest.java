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
import org.hercworks.core.data.file.dat.shell.Hercs;
import org.hercworks.core.data.file.dat.shell.WeaponsDat;
import org.hercworks.core.data.file.dyn.DynamixThreeSpaceModel;
import org.hercworks.core.io.transform.dbsim.DTSModelTransformer;
import org.hercworks.core.io.transform.shell.ArmHercTransformer;
import org.hercworks.core.io.transform.shell.HercsStartTransformer;
import org.hercworks.core.io.transform.shell.WeaponsDatTransformer;
import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.dto.file.sim.dts.DTSRootDTO;
import org.hercworks.transfer.svc.impl.dbsim.DTSServiceImpl;
import org.hercworks.transfer.svc.impl.shell.ArmHercDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.StartingHercsDTOServiceImpl;
import org.hercworks.transfer.svc.impl.shell.WeaponsDatShellDTOServiceImpl;
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
			GeneralDTOService dtoSvc = new StartingHercsDTOServiceImpl();
			StartHercsDTO dto = (StartHercsDTO)dtoSvc.convertToDTO(armHercDat);
//			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			
			File export = new File(resourceDirectory+ "/HERCS.json" );
			objectMapper.writeValue(export, dto);
			
			
			StartHercsDTO readDTO = (StartHercsDTO)objectMapper.readValue(export, StartHercsDTO.class);
			
			armHercDat = (Hercs)dtoSvc.fromDTO(readDTO);

			File exportBytes = new File(resourceDirectory+ "/HERCS.DAT");
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
			
			File dat = new File(absolutePath + "/ARM_MAVR.DAT");
//			
			assertTrue(dat.exists());
			assertTrue(!dat.isDirectory());
//			
			FileInputStream fizz = new FileInputStream(dat);
			byte[] bytes = fizz.readAllBytes();
			fizz.close();
//			
			ArmHercTransformer transform = new ArmHercTransformer();
 			ArmHerc data = (ArmHerc) transform.bytesToObject(bytes);
//			
			assertNotNull(data);
//			
			data.setFileName("ARM_MAVR");
			data.setExt(FileType.DAT);
			data.setGameDirPath("/GAM/");
//			
//			
			GeneralDTOService dtoSvc = new ArmHercDTOServiceImpl();
//			
			ArmHercDTO dto = (ArmHercDTO)dtoSvc.convertToDTO(data);
//			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			
			File export = new File(resourceDirectory+ "/ARM_MAVR.json" );
			objectMapper.writeValue(export, dto);
			
			File exportBytes = new File(resourceDirectory+ "/ARM_MAVR_2.DAT");
			
			ArmHercDTO readDTO = (ArmHercDTO)objectMapper.readValue(export, ArmHercDTO.class);
			
			data = (ArmHerc)dtoSvc.fromDTO(readDTO);
			
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
			
			GeneralDTOService dtoSvc = new WeaponsDatShellDTOServiceImpl();
			WeaponsDatDTO dto = (WeaponsDatDTO)dtoSvc.convertToDTO(data);
//			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			
			File export = new File(resourceDirectory+ "/WEAPONS.json" );
			objectMapper.writeValue(export, dto);
			
			File exportBytes = new File(resourceDirectory+ "/WEAPONS2.DAT");
			
			WeaponsDatDTO readDTO = (WeaponsDatDTO)objectMapper.readValue(export, WeaponsDatDTO.class);
			
			data = (WeaponsDat)dtoSvc.fromDTO(readDTO);
			
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
	public void testDTStoDTO() {
		
		try(FileInputStream fizz = new FileInputStream(new File("e://es2_os//dev//earthsiege2//unpack//simvol0//dts//samson.dts"))){
			
			byte[] data = fizz.readAllBytes();
			fizz.close();
			DTSModelTransformer convert = new DTSModelTransformer();
			DynamixThreeSpaceModel dts = (DynamixThreeSpaceModel)convert.bytesToObject(data);
			dts.setFileName("SAMSON.DTS");
//
			DTSServiceImpl dtoSvc = new DTSServiceImpl();
			DTSRootDTO dto = (DTSRootDTO) dtoSvc.convertToDTO(dts);
//			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
//			
			File export = new File("e://es2_os/dev/earthsiege2/unpack/dts/smugson.json");
			objectMapper.writeValue(export, dto);
			
			DTSRootDTO readDTO = (DTSRootDTO)objectMapper.readValue(export, DTSRootDTO.class);
			
			dts = (DynamixThreeSpaceModel)dtoSvc.fromDTO(readDTO);
			
			FileOutputStream foss = new FileOutputStream(new File("e://es2_os//dev//earthsiege2//dts//samson.dts"));
			foss.write(convert.objectToBytes(dts));
			foss.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
