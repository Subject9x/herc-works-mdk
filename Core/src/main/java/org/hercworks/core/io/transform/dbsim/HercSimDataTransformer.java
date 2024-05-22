package org.hercworks.core.io.transform.dbsim;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dat.sim.HercSimDat;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.hercworks.voln.FileType;

public class HercSimDataTransformer extends ThreeSpaceByteTransformer{

	public HercSimDataTransformer() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		index = 0;
		
		HercSimDat data = new HercSimDat();
		data.setExt(FileType.DAT);
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
		
		data.setNameBytes(indexSegment(12));
		
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
		
		data.setUnk150_val(indexShortLE());
		data.setUnk152_val(indexShortLE());
		data.setUnk154_fixedVal(indexShortLE());
		data.setUnk156_400or800(indexShortLE());
		
		skip(12);
		
		data.setUnk170_val(indexShortLE());
		data.setUnk172_val(indexShortLE());
		data.setUnk174_250or275(indexShortLE());
		
		skip(14);
		
		data.setShieldMaxTotal(indexShortLE());
		data.setUnk192_val(indexShortLE());
		data.setPhysicsFrictionCoef(indexShortLE());
		data.setPhysicsFrctionAccel(indexShortLE());
		
		skip(6);
				
		data.setDebrisFile(indexSegment(12));
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile dataObj)  throws ClassCastException, IOException{
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		HercSimDat data = (HercSimDat)dataObj;
		
		out.write(writeShortLE(data.getSpeedTurn()));
		out.write(writeShortLE(data.getSpeedReverse()));
		out.write(writeShortLE(data.getSpeedForward()));
		out.write(writeShortLE(data.getUnk6_Val30()));
		out.write(writeShortLE(data.getDecelTurning()));
		
		out.write(writeShortLE(data.getCameraBoneId()));
		
		out.write(writeShortLE(data.getInputThrottleHercFlag()));
		
		out.write(writeShortLE(data.getUnk14_ValAnim1()));
		out.write(writeShortLE(data.getUnk16_ValAnim2()));
		out.write(writeShortLE(data.getUnk18_ValAnim3()));
		out.write(writeShortLE(data.getUnitOffsetYAdjust()));
		
		out.write(writeShortLE(data.getUnk22_Val750Razor0()));
		
		out.write(writeShortLE(data.getAiAimTargOffset()));
		
		out.write(writeShortLE(data.getInputTorsoRazrFlag()));
		out.write(writeShortLE(data.getTorsoTwistSpeed()));
		out.write(writeShortLE(data.getUnk30_Val1000()));
		out.write(writeShortLE(data.getTorsoTwistDegreeMax()));
		out.write(writeShortLE(data.getInputFlagsTorso()));
		out.write(writeShortLE(data.getTorsoPitchMaxRate()));
		out.write(writeShortLE(data.getTorsoPitchRate()));
		out.write(writeShortLE(data.getTorsoPitchMax()));
		out.write(writeShortLE(data.getTorsoPitchMin()));
		
		out.write(writeShortLE(data.getUnk44_MoveAnimRate()));
		
		for(int i=0; i < 20; i++) {
			out.write(data.getModelLoDBoneIds()[i]);
		}
		
		out.write(writeShortLE(data.getUnk66_Val1000()));
		
		out.write(writeShortLE(data.getLegsCritFlags1()));
		out.write(writeShortLE(data.getLegsCritFlags2()));
		out.write(writeShortLE(data.getModelLegsTotal()));
		out.write(writeShortLE(data.getModelFlagNoDebris()));
		
		out.write(writeShortLE(data.getUnk76_Val()));
		
		out.write(writeShortLE(data.getInputFlagFlyer()));
		
		out.write(writeShortLE(data.getUnk80_ValHudId()));
		
		out.write(writeShortLE(data.getUnk82_val()));
		
		out.write(writeShortLE(data.getUnk84_val()));
		
		//write name
		out.write(data.getNameBytes());
		
		out.write(writeShortLE(data.getCameraYAxisAdj()));
		out.write(writeShortLE(data.getCameraXAxisAdj()));
		
		//blank bytes 0x102
		out.write((byte)0x00);
		out.write((byte)0x00);
		
		out.write(writeShortLE(data.getCameraExtOrgOffset()));
		
		//blank bytes 0x106
		out.write((byte)0x00);
		out.write((byte)0x00);
		
		out.write(writeShortLE(data.getUnk108_camExtVal1()));
		out.write(writeShortLE(data.getUnk110_camExtVal2()));
		
		out.write(writeShortLE(data.getModelFlagsShadow1()));
		out.write(writeShortLE(data.getModelFlagsShadow2()));
		
		out.write(writeShortLE(data.getUnk116_val()));
		out.write(writeShortLE(data.getUnk118_val()));
		out.write(writeShortLE(data.getUnk120_val()));
		out.write(writeShortLE(data.getUnk122_mdlFlagVal()));
		
		//range
		for(int i=0; i < 12; i++){
			out.write(writeShortLE(data.getUnk124_all500()[i]));
		}
		
		out.write(writeShortLE(data.getModelSkinId()));
		
		out.write(writeShortLE(data.getUnk150_val()));
		out.write(writeShortLE(data.getUnk152_val()));
		out.write(writeShortLE(data.getUnk154_fixedVal()));
		out.write(writeShortLE(data.getUnk156_400or800()));
		
		//158 - 169 - BLANK BYTES
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		out.write((byte)0x0);
		
		
		out.write(writeShortLE(data.getUnk170_val()));
		out.write(writeShortLE(data.getUnk172_val()));
		out.write(writeShortLE(data.getUnk174_250or275()));
		
		// 176 - 189 - BLANK BYTES
		for(int i=0; i<14; i++) {
			out.write((byte)0x00);
		}
		
		out.write(writeShortLE(data.getShieldMaxTotal()));
		out.write(writeShortLE(data.getUnk192_val()));
		out.write(writeShortLE(data.getPhysicsFrictionCoef()));
		out.write(writeShortLE(data.getPhysicsFrctionAccel()));
		
		//198 - 203 - BLANK BYTES
		for(int i=0; i<6; i++) {
			out.write((byte)0x00);
		}
		
	    out.write(data.getDebrisFile());

		
		return out.toByteArray();
	}

}
