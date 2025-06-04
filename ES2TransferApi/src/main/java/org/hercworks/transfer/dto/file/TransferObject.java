package org.hercworks.transfer.dto.file;

import org.hercworks.transfer.dto.file.shell.ArmHercDTO;
import org.hercworks.transfer.dto.file.shell.ArmWeapDTO;
import org.hercworks.transfer.dto.file.shell.CareerMissionsDTO;
import org.hercworks.transfer.dto.file.shell.HardpointOverlayDTO;
import org.hercworks.transfer.dto.file.shell.HercInfDTO;
import org.hercworks.transfer.dto.file.shell.InitHercDTO;
import org.hercworks.transfer.dto.file.shell.RepairHercDTO;
import org.hercworks.transfer.dto.file.shell.StartHercsDTO;
import org.hercworks.transfer.dto.file.shell.TrainingHercsDTO;
import org.hercworks.transfer.dto.file.shell.WeaponsDatDTO;
import org.hercworks.transfer.dto.file.sim.BeamDatDTO;
import org.hercworks.transfer.dto.file.sim.DebrisHercDTO;
import org.hercworks.transfer.dto.file.sim.FlightModelDTO;
import org.hercworks.transfer.dto.file.sim.GunLayoutDTO;
import org.hercworks.transfer.dto.file.sim.HercDmgDTO;
import org.hercworks.transfer.dto.file.sim.HercSimDatDTO;
import org.hercworks.transfer.dto.file.sim.MissileDatDTO;
import org.hercworks.transfer.dto.file.sim.PaperDollDTO;
import org.hercworks.transfer.dto.file.sim.ProjectileDataDTO;
import org.hercworks.transfer.dto.file.sim.WpnPDGDTO;
import org.hercworks.transfer.dto.file.sim.dts.DTSRootDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "classDef")
@JsonSubTypes({ 
  @Type(value = ArmHercDTO.class, name = "ArmHerc"), 
  @Type(value = ArmWeapDTO.class, name = "ArmWeap"),
  @Type(value = CareerMissionsDTO.class, name = "CareerMissions"),
  @Type(value = HardpointOverlayDTO.class, name = "HardpointOverlayConfig"),
  @Type(value = StartHercsDTO.class, name = "Hercs"),
  @Type(value = HercInfDTO.class, name = "HercInf"),
  @Type(value = InitHercDTO.class, name = "InitHerc"),
  @Type(value = RepairHercDTO.class, name = "RprHerc"),
  @Type(value = TrainingHercsDTO.class, name = "TrainingHercs"),
  @Type(value = WeaponsDatDTO.class, name = "WeaponsDat"),
  
  @Type(value = DebrisHercDTO.class, name = "DebrisHerc"),
  @Type(value = BeamDatDTO.class, name = "BeamData"),
  @Type(value = FlightModelDTO.class, name = "FlightModel"),
  @Type(value = GunLayoutDTO.class, name = "GunLayout"),
  @Type(value = HercDmgDTO.class, name = "HercSimDamage"),
  @Type(value = HercSimDatDTO.class, name = "HercSimDat"),
  @Type(value = MissileDatDTO.class, name = "MissileDatFile"),
  @Type(value = PaperDollDTO.class, name = "PaperDollGraphic"),
  @Type(value = ProjectileDataDTO.class, name = "ProjectileData"),
  @Type(value = WpnPDGDTO.class, name = "WeaponPaperDiagram"),
  
  @Type(value = DTSRootDTO.class, name = "DTSModel"),
})
public abstract class TransferObject {
	//meta class for generics.

	@JsonProperty(value = "fileName", index = 0)
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	//utilities for clean presentation
	public short floatStringToFixedShort(String strFloat) {
		float f = Float.valueOf(strFloat);
		f = f * 100f;
		return (short)f;
	}
	
	public String fixedShortToFloatString(short val) {
		float f = (float)val;
		
		return String.valueOf((f/100f));	
	}

}
