package com.mech.works.io.transform.dbsim;

import com.mech.works.data.file.dat.sim.HercInfoDat;
import com.mech.works.data.ref.files.DataFile;
import com.mech.works.io.transform.ThreeSpaceByteTransformer;

import at.favre.lib.bytes.Bytes;

public class TransformHercInfoDat extends ThreeSpaceByteTransformer{

	public TransformHercInfoDat() {}
	
	@Override
	public <T extends DataFile> T bytesToObject(byte[] inputArray, T file) throws ClassCastException {
		
		if(!(file instanceof HercInfoDat)) {
			throw new ClassCastException(file.getClass().toString() + "not instance of " + HercInfoDat.class.toString());
		}
		HercInfoDat data = (HercInfoDat)file;
		
		index = 0;
		bytes = Bytes.from(inputArray);
		
		
		data.setSpeedTurn(indexShortLE());
		data.setSpeedReverse(indexShortLE());
		data.setSpeedForward(indexShortLE());
		data.setDecelTurning(indexShortLE());

		data.setCameraBoneId(indexShortLE());
		data.setUnk12_ValFlag05(indexShortLE());
		
		data.setUnk14_ValAnim1(indexShortLE());
		data.setUnk16_ValAnim2(indexShortLE());
		data.setUnk18_ValAnim3(indexShortLE());
		data.setUnk20_Val400(indexShortLE());
		data.setUnk22_Val750Razor0(indexShortLE());
		
		data.setAiAimOffset(indexShortLE());
		
		index += 2;	//blank bytes
		
		data.setTorsoTwistSpeed(indexShortLE());
		
		data.setUnk30_Val1000(indexShortLE());
		
		data.setTorsoTwistDegreeMax(indexShortLE());
		data.setTorsoFlagsInput(indexShortLE());
		data.setTorsoPitchMaxRate(indexShortLE());
		data.setTorsoPitchRate(indexShortLE());
		data.setTorsoPitchMax(indexShortLE());
		data.setTorsoPitchMin(indexShortLE());
		
		data.setUnk44_MoveAnim(indexShortLE());
		
		for(int i=0; i < 20; i++) {
			data.getModelLODId()[i] = indexByte();
		}
		
		
		
		
		return null;
		
	}

	@Override
	public byte[] objectToBytes(DataFile dataObj)  throws ClassCastException{
		// TODO Auto-generated method stub
		return null;
	}

}
