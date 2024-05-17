package org.hercworks.core.io.transform.dbsim;

import org.hercworks.core.data.file.dat.sim.HercInfoDat;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;

import at.favre.lib.bytes.Bytes;

public class HercSimDataTransformer extends ThreeSpaceByteTransformer{

	public HercSimDataTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		index = 0;
		
		HercInfoDat data = new HercInfoDat();
		data.setRawBytes(inputArray);
		
		setBytes(inputArray);
		
		data.setSpeedTurn(indexShortLE());
		data.setSpeedReverse(indexShortLE());
		data.setSpeedForward(indexShortLE());
		data.setUnk6_Val30(indexShortLE());
		data.setDecelTurning(indexShortLE());

		data.setCameraBoneId(indexShortLE());
		
		data.setInputThrottleHercFlag(indexShortLE());
		
		data.setUnk14_ValAnim1(indexShortLE());
		data.setUnk16_ValAnim2(indexShortLE());
		data.setUnk18_ValAnim3(indexShortLE());
		data.setUnitOffsetYAdjust(indexShortLE());
		data.setUnk22_Val750Razor0(indexShortLE());
		
		data.setAiAimTargOffset(indexShortLE());
		
		data.setInputTorsoRazrFlag(indexShortLE());
		data.setTorsoTwistSpeed(indexShortLE());
		
		data.setUnk30_Val1000(indexShortLE());
		
		data.setTorsoTwistDegreeMax(indexShortLE());
		data.setInputFlagsTorso(indexShortLE());
		data.setTorsoPitchMaxRate(indexShortLE());
		data.setTorsoPitchRate(indexShortLE());
		data.setTorsoPitchMax(indexShortLE());
		data.setTorsoPitchMin(indexShortLE());
		
		data.setUnk44_MoveAnimRate(indexShortLE());
		
		for(int i=0; i < 20; i++) {
			data.getModelLoDBoneIds()[i] = indexByte();
		}
		
		data.setUnk66_Val1000(indexShortLE());
		
		data.setLegsCritFlags1(indexShortLE());
		data.setLegsCritFlags2(indexShortLE());
		data.setModelLegsTotal(indexShortLE());
		data.setModelFlagNoDebris(indexShortLE());
		
		data.setUnk76_Val(indexShortLE());
		
		data.setInputFlagFlyer(indexShortLE());
		
		data.setUnk80_ValHudId(indexShortLE());
		
		data.setUnk82_val(indexShortLE());
		data.setUnk84_val(indexShortLE());
		
		byte[] unitName = indexSegment(12);
		Bytes b = Bytes.from(unitName[0]);
		for(int i=1; i < 12; i++) {
			if(unitName[i] == 0x00) {
				break;
			}
			b = b.append(unitName[i]);
		}
		data.setNameBytes(new String(b.toCharArray()));
		
		data.setCameraYAxisAdj(indexShortLE());
		data.setCameraXAxisAdj(indexShortLE());
		
		skip(2); //blank bytes 0x102
		
		data.setCameraExtOrgOffset(indexShortLE());
		
		skip(2); //blank bytes 0x106
		
		data.setUnk108_camExtVal1(indexShortLE());
		data.setUnk110_camExtVal2(indexShortLE());
		
		data.setModelFlagsShadow1(indexShortLE());
		data.setModelFlagsShadow2(indexShortLE());
		
		data.setUnk116_val(indexShortLE());
		data.setUnk118_val(indexShortLE());
		data.setUnk120_val(indexShortLE());
		
		data.setUnk122_mdlFlagVal(indexShortLE());
		
		short[] seg500 = new short[12];
		for(int i=0; i<12; i++) {
			seg500[i] = indexShortLE();
		}
		data.setUnk124_all500(seg500);
		
		data.setModelSkinId(indexShortLE());
		
		data.setUnk148_val(indexShortLE());
		data.setUnk150_val(indexShortLE());
		data.setUnk152_val(indexShortLE());
		data.setUnk154_fixedVal(indexShortLE());
		data.setUnk156_400or800(indexShortLE());
		
		skip(11);
		
		data.setUnk170_val(indexShortLE());
		data.setUnk172_val(indexShortLE());
		data.setUnk174_250or275(indexShortLE());
		
		skip(13);
		
		data.setShieldMaxTotal(indexShortLE());
		data.setUnk192_val(indexShortLE());
		data.setPhysicsFrictionCoef(indexShortLE());
		data.setPhysicsFrctionAccel(indexShortLE());
		

		data.setDebrisFile(indexSegment(12));
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile dataObj)  throws ClassCastException{
		// TODO Auto-generated method stub
		return null;
	}

}
