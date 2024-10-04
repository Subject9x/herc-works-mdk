package org.hercworks.transfer.svc.impl.dbsim;

import java.util.LinkedHashMap;

import org.hercworks.core.data.file.dat.sim.HercSimDat;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.HercSimDatDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

import at.favre.lib.bytes.Bytes;

public class HercSimDataDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		HercSimDat src = (HercSimDat)source;
		
		HercSimDatDTO dto = new HercSimDatDTO();
		
		//Speed config block-------------------------------------------------------------------------------------
		dto.getSpeedValues().put("turn", String.valueOf(src.getSpeedTurn()));
		dto.getSpeedValues().put("reverse", String.valueOf(src.getSpeedReverse()));
		dto.getSpeedValues().put("forward", String.valueOf(src.getSpeedForward()));
		dto.getSpeedValues().put("accel_and_decel", String.valueOf(src.getSpeedAccelDecel()));
		dto.getSpeedValues().put("turning_decel", String.valueOf(src.getDecelTurning()));
		//-----------------------------------------------------------------------------------------------------
		
		dto.setCameraBoneId(src.getCameraBoneId());
		
		dto.setInputThrottleHercFlag(src.getInputThrottleHercFlag());
		
		dto.setUnk14_ValAnim1(src.getUnk14_ValAnim1());
		dto.setUnk16_ValAnim2(src.getUnk16_ValAnim2());
		dto.setUnk18_ValAnim3(src.getUnk18_ValAnim3());
		dto.setUnitOffsetYAdjust(src.getUnitOffsetYAdjust());
		dto.setUnk22_Val750Razor0(src.getUnk22_Val750Razor0());
		
		dto.setAiAimTargOffset(src.getAiAimTargOffset());
		
		//Torso config block---------------------------------------------------------------------------
		dto.getTorsoConfig().put("razor_override", String.valueOf(src.getInputTorsoRazrFlag()));
		
		LinkedHashMap<String, String> rotateSpeed = new LinkedHashMap<String, String>();
		rotateSpeed.put("rotate_speed", dto.fixedShortToFloatString(src.getTorsoTwistSpeed()));
		rotateSpeed.put("accel_rate",  dto.fixedShortToFloatString(src.getTorsoRotateAccel()));
		rotateSpeed.put("angle_max",  dto.fixedShortToFloatString(src.getTorsoTwistDegreeMax()));
		dto.getTorsoConfig().put("rotation", rotateSpeed);

		dto.getTorsoConfig().put("input_flags", String.valueOf(src.getInputFlagsTorso()));
		
		LinkedHashMap<String, String> torsoPitch = new LinkedHashMap<String, String>();
		torsoPitch.put("rate_max",  dto.fixedShortToFloatString(src.getTorsoPitchMaxRate()));
		torsoPitch.put("rate",  dto.fixedShortToFloatString(src.getTorsoPitchRate()));
		torsoPitch.put("angle_max",  dto.fixedShortToFloatString(src.getTorsoPitchMax()));
		torsoPitch.put("angle_min",  dto.fixedShortToFloatString(src.getTorsoPitchMin()));
		dto.getTorsoConfig().put("pitch", torsoPitch);
		//---------------------------------------------------------------------------------------------
		
		dto.setUnk44MoveAnimRate(src.getUnk44_MoveAnimRate());
		
		int[] boneId = new int[20];
		for(int i=0; i<20; i++) {
			boneId[i] = (int)src.getModelLoDBoneIds()[i]; 
		}
		dto.setModelLoDBoneIds(boneId);
		
		dto.setUnk66_Val1000(src.getUnk66_Val1000());
		
		dto.setLegsCritFlags1(src.getLegsCritFlags1());
		dto.setLegsCritFlags2(src.getLegsCritFlags2());
		dto.setModelLegsTotal(src.getModelLegsTotal());
		dto.setModelFlagNoDebris(src.getModelFlagNoDebris());
		
		dto.setUnk76_Val(src.getUnk76_Val());
		
		dto.setInputFlagFlyer(src.getInputFlagFlyer());
		
		dto.setUnk80_ValHudId(src.getUnk80_ValHudId());
		
		dto.setUnk82_val(src.getUnk82_val());
		dto.setUnk84_val(src.getUnk84_val());
		
		byte[] unitName = src.getNameBytes();
		Bytes b = Bytes.from(unitName[0]);
		for(int i=1; i < 12; i++) {
			if(unitName[i] == 0x00) {
				break;
			}
			b = b.append(unitName[i]);
		}
		
		dto.setNameStr(new String(Bytes.from(b).toCharArray()));
		
		dto.setCameraYAxisAdj(src.getCameraYAxisAdj());
		dto.setCameraXAxisAdj(src.getCameraXAxisAdj());
		dto.setCameraExtOrgOffset(src.getCameraExtOrgOffset());
		
		dto.setUnk108_camExtVal1(src.getUnk108_camExtVal1());
		dto.setUnk110_camExtVal2(src.getUnk110_camExtVal2());
		
		dto.setModelFlagsShadow1(src.getModelFlagsShadow1());
		dto.setModelFlagsShadow2(src.getModelFlagsShadow2());
		
		dto.setUnk116_val(src.getUnk116_val());
		dto.setUnk118_val(src.getUnk118_val());
		dto.setUnk120_val(src.getUnk120_val());
		dto.setUnk122_mdlFlagVal(src.getUnk122_mdlFlagVal());
		
		int[] seg500 = new int[12];
		for(int i=0; i<12; i++) {
			seg500[i] = (int)src.getUnk124_all500()[i]; 
		}
		dto.setUnk124_all500(seg500);
		
		dto.setModelSkinId(src.getModelSkinId());
		
		dto.setUnk150_val(src.getUnk150_val());
		dto.setUnk152_val(src.getUnk152_val());
		dto.setUnk154_fixedVal(src.getUnk154_fixedVal());
		dto.setUnk156_400or800(src.getUnk156_400or800());
		
		dto.setUnk170_val(src.getUnk170_val());
		dto.setUnk172_val(src.getUnk172_val());
		dto.setUnk174_250or275(src.getUnk174_250or275());
		
		dto.setShieldMaxTotal(src.getShieldMaxTotal());
		dto.setUnk192_val(src.getUnk192_val());
		dto.setPhysicsFrictionCoef(src.getPhysicsFrictionCoef());
		dto.setPhysicsFrctionAccel(src.getPhysicsFrctionAccel());
		
		int c = 0;
		for(int i=0; i < src.getDebrisFile().length; i++) {
			if(src.getDebrisFile()[i] == 0x00) {
				break;
			}
			c += 1;
		}
		String debrisFile = new String(Bytes.from(src.getDebrisFile(), 0, c).toCharArray());
		dto.setDebrisFile(debrisFile);
			
		return dto;
	}

	@Override
	public DataFile fromDTO(TransferObject source) {
		HercSimDatDTO dto = (HercSimDatDTO)source;
		HercSimDat dat = new HercSimDat();
		dat.setExt(FileType.DAT);
		dat.setDir(FileType.DAT);
		
		//Speed config block------------------------------------------------------------------------------------
		dat.setSpeedTurn(Short.valueOf(dto.getSpeedValues().get("turn")));
		dat.setSpeedReverse(Short.valueOf(dto.getSpeedValues().get("reverse")));
		dat.setSpeedForward(Short.valueOf(dto.getSpeedValues().get("forward")));
		dat.setSpeedAccelDecel(Short.valueOf(dto.getSpeedValues().get("accel_and_decel")));
		dat.setDecelTurning(Short.valueOf(dto.getSpeedValues().get("turning_decel")));
		//-----------------------------------------------------------------------------------------------------
		
		dat.setCameraBoneId((short)dto.getCameraBoneId());
		
		dat.setInputThrottleHercFlag((short)dto.getInputThrottleHercFlag());
		
		dat.setUnk14_ValAnim1((short)dto.getUnk14_ValAnim1());
		dat.setUnk16_ValAnim2((short)dto.getUnk16_ValAnim2());
		dat.setUnk18_ValAnim3((short)dto.getUnk18_ValAnim3());
		dat.setUnitOffsetYAdjust((short)dto.getUnitOffsetYAdjust());
		dat.setUnk22_Val750Razor0((short)dto.getUnk22_Val750Razor0());
		
		dat.setAiAimTargOffset((short)dto.getAiAimTargOffset());
		
		//Torso config block------------------------------------------------------------------------------------
		dat.setInputTorsoRazrFlag(Short.valueOf((String)dto.getTorsoConfig().get("razor_override")));
		
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, String> rotateSpeed = (LinkedHashMap<String, String>)dto.getTorsoConfig().get("rotation");	
		dat.setTorsoTwistSpeed(dto.floatStringToFixedShort(rotateSpeed.get("rotate_speed")));
		dat.setTorsoRotateAccel(dto.floatStringToFixedShort(rotateSpeed.get("accel_rate")));
		dat.setTorsoTwistDegreeMax(dto.floatStringToFixedShort(rotateSpeed.get("angle_max")));
		
		dat.setInputFlagsTorso(Short.valueOf((String)dto.getTorsoConfig().get("input_flags")));
		
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, String> torsoPitch = (LinkedHashMap<String, String>)dto.getTorsoConfig().get("pitch");	
		dat.setTorsoPitchMaxRate(dto.floatStringToFixedShort(torsoPitch.get("rate_max")));
		dat.setTorsoPitchRate(dto.floatStringToFixedShort(torsoPitch.get("rate")));
		dat.setTorsoPitchMax(dto.floatStringToFixedShort(torsoPitch.get("angle_max")));
		dat.setTorsoPitchMin(dto.floatStringToFixedShort(torsoPitch.get("angle_min")));
		//-------------------------------------------------------------------------------------------------------
		
		dat.setUnk44_MoveAnimRate((short)dto.getUnk44MoveAnimRate());
		
		byte[] boneId = new byte[20];
		for(int i=0; i<20; i++) {
			boneId[i] = (byte)dto.getModelLoDBoneIds()[i]; 
		}
		dat.setModelLoDBoneIds(boneId);
		
		dat.setUnk66_Val1000((short)dto.getUnk66_Val1000());
		
		dat.setLegsCritFlags1((short)dto.getLegsCritFlags1());
		dat.setLegsCritFlags2((short)dto.getLegsCritFlags2());
		dat.setModelLegsTotal((short)dto.getModelLegsTotal());
		dat.setModelFlagNoDebris((short)dto.getModelFlagNoDebris());
		
		dat.setUnk76_Val((short)dto.getUnk76_Val());
		
		dat.setInputFlagFlyer((short)dto.getInputFlagFlyer());
		
		dat.setUnk80_ValHudId((short)dto.getUnk80_ValHudId());
		
		dat.setUnk82_val((short)dto.getUnk82_val());
		dat.setUnk84_val((short)dto.getUnk84_val());
		
		byte[] chars = new byte[12];
		for(int i=0; i<12; i++) {
			if(i < dto.getNameStr().length()) {
				chars[i] = (byte)dto.getNameStr().toCharArray()[i];
			}
			else{
				chars[i] = (byte)0x00;
			}
		}
		dat.setNameBytes(chars);
		
		dat.setCameraYAxisAdj((short)dto.getCameraYAxisAdj());
		dat.setCameraXAxisAdj((short)dto.getCameraXAxisAdj());
		
		dat.setCameraExtOrgOffset((short)dto.getCameraExtOrgOffset());
		
		dat.setUnk108_camExtVal1((short)dto.getUnk108_camExtVal1());
		dat.setUnk110_camExtVal2((short)dto.getUnk110_camExtVal2());
		
		dat.setModelFlagsShadow1((short)dto.getModelFlagsShadow1());
		dat.setModelFlagsShadow2((short)dto.getModelFlagsShadow2());
		
		dat.setUnk116_val((short)dto.getUnk116_val());
		dat.setUnk118_val((short)dto.getUnk118_val());
		dat.setUnk120_val((short)dto.getUnk120_val());
		dat.setUnk122_mdlFlagVal((short)dto.getUnk122_mdlFlagVal());
		
		short[] seg500 = new short[12];
		for(int i=0; i<12; i++) {
			seg500[i] = (short)dto.getUnk124_all500()[i]; 
		}
		dat.setUnk124_all500(seg500);
		
		dat.setModelSkinId((short)dto.getModelSkinId());
		dat.setUnk150_val((short)dto.getUnk150_val());
		dat.setUnk152_val((short)dto.getUnk152_val());
		dat.setUnk154_fixedVal((short)dto.getUnk154_fixedVal());
		dat.setUnk156_400or800((short)dto.getUnk156_400or800());
		
		dat.setUnk170_val((short)dto.getUnk170_val());
		dat.setUnk172_val((short)dto.getUnk172_val());
		dat.setUnk174_250or275((short)dto.getUnk174_250or275());
		
		dat.setShieldMaxTotal((short)dto.getShieldMaxTotal());
		dat.setUnk192_val((short)dto.getUnk192_val());
		dat.setPhysicsFrictionCoef((short)dto.getPhysicsFrictionCoef());
		dat.setPhysicsFrctionAccel((short)dto.getPhysicsFrctionAccel());
		
		byte[] debrisChar = new byte[12];
		for(int i=0; i<12; i++) {
			if(i < dto.getDebrisFile().length()) {
				debrisChar[i] = (byte)dto.getDebrisFile().toCharArray()[i];
			}
			else{
				debrisChar[i] = (byte)0x00;
			}
		}
		dat.setDebrisFile(debrisChar);
		
		return dat;
	}

}
