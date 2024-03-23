package org.hercworks.core.io.transform.dbsim;

import org.hercworks.core.data.file.dat.sim.HercInfoDat;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;

public class TransformHercInfoDat extends ThreeSpaceByteTransformer{

	public TransformHercInfoDat() {}
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		index = 0;
		
		HercInfoDat data = new HercInfoDat();
		data.setRawBytes(inputArray);
		
		setBytes(inputArray);
		
		
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
		
		
		
		
		return data;
	}

	@Override
	public byte[] objectToBytes(DataFile dataObj)  throws ClassCastException{
		// TODO Auto-generated method stub
		return null;
	}

}
