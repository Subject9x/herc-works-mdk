package org.hercworks.transfer.svc.impl;

import org.hercworks.core.data.file.dat.sim.HercInfoDat;
import org.hercworks.transfer.dto.file.TransferObject;
import org.hercworks.transfer.dto.file.sim.HercSimDatDTO;
import org.hercworks.transfer.svc.GeneralDTOService;
import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;

public class HercSimDataDTOServiceImpl implements GeneralDTOService {

	@Override
	public TransferObject convertToDTO(DataFile source) {
		
		HercInfoDat src = (HercInfoDat)source;
		
		HercSimDatDTO dto = new HercSimDatDTO();
		
		
		dto.setSpeedTurn(src.getSpeedTurn());
		dto.setSpeedReverse(src.getSpeedReverse());
		dto.setSpeedForward(src.getSpeedForward());
		dto.setUnk6_Val30(src.getUnk6_Val30());
		dto.setDecelTurning(src.getDecelTurning());
		
		dto.setCameraBoneId(src.getCameraBoneId());
		
		dto.setInputThrottleHercFlag(src.getInputThrottleHercFlag());
		
		dto.setUnk14_ValAnim1(src.getUnk14_ValAnim1());
		dto.setUnk16_ValAnim2(src.getUnk16_ValAnim2());
		dto.setUnk18_ValAnim3(src.getUnk18_ValAnim3());
		dto.setUnitOffsetYAdjust(src.getUnitOffsetYAdjust());
		dto.setUnk22_Val750Razor0(src.getUnk22_Val750Razor0());
		
		dto.setAiAimTargOffset(src.getAiAimTargOffset());
		
		dto.setInputTorsoRazrFlag(src.getInputTorsoRazrFlag());
		dto.setTorsoTwistSpeed(src.getTorsoTwistSpeed());
		
		dto.setUnk30_Val1000(src.getUnk30_Val1000());
		
		dto.setTorsoTwistDegreeMax(src.getTorsoTwistDegreeMax());
		dto.setInputFlagsTorso(src.getInputFlagsTorso());
		dto.setTorsoPitchMaxRate(src.getTorsoPitchMaxRate());
		dto.setTorsoPitchRate(src.getTorsoPitchRate());
		dto.setTorsoPitchMax(src.getTorsoPitchMax());
		dto.setTorsoPitchMin(src.getTorsoPitchMin());
		
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
		
		dto.setNameBytes(src.getNameBytes());
		
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
		
		dto.setUnk148_val(src.getUnk148_val());
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
		// TODO Auto-generated method stub
		return null;
	}

}
