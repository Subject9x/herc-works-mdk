package org.hercworks.core.io.transform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hercworks.core.data.file.dbsim.FlightModel;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;

public class FlightModelTransformer extends ThreeSpaceByteTransformer{

	public FlightModelTransformer() {}
	
	
	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		
		if(inputArray == null) {
			return null;
		}
		
		setBytes(inputArray);
		
		FlightModel fm = new FlightModel();
		
		fm.setPitchRate(indexShortLE());
		fm.setRollRate(indexShortLE());
		fm.setRudderForce(indexShortLE());
		fm.setPitchForce(indexShortLE());
		fm.setRollForce(indexShortLE());
		fm.setThrustFactor(indexShortLE());
		
		skip(6);
		
		fm.setRollMax(indexShortLE());
		
		skip(2);
		
		fm.setUnk22_val16(indexShortLE());
		
		skip(2);
		
		fm.setUnk26_5or6(indexShortLE());
		
		skip(2);
		
		fm.setRollForce(indexShortLE());
		
		skip(2);
		
		fm.setAltitudeMax(indexIntLE());
		fm.setUnk38_val6000(indexIntLE());
		fm.setAirSpeedMax(indexIntLE());
		fm.setAirSpeedMin(indexIntLE());
		fm.setRollAccel(indexIntLE());
		
		
		return fm;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		
		if(source == null) {
			//TODO log null
			return null;
		}
		
		FlightModel fm = (FlightModel)source;
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		
		bytes.write(writeShortLE(fm.getPitchRate()));
		bytes.write(writeShortLE(fm.getRollRate()));
		bytes.write(writeShortLE(fm.getRudderForce()));
		bytes.write(writeShortLE(fm.getPitchForce()));
		bytes.write(writeShortLE(fm.getRollForce()));
		bytes.write(writeShortLE(fm.getThrustFactor()));
		
		bytes.write((byte)0x00);
		bytes.write((byte)0x00);
		bytes.write((byte)0x00);
		
		bytes.write(writeShortLE(fm.getRollMax()));
		
		bytes.write((byte)0x00);
		
		bytes.write(writeShortLE(fm.getUnk22_val16()));
		
		bytes.write((byte)0x00);
		
		bytes.write(writeShortLE(fm.getUnk26_5or6()));
		
		bytes.write((byte)0x00);
		
		bytes.write(writeShortLE(fm.getRollFriction()));
		
		bytes.write((byte)0x00);
		
		bytes.write(writeIntLE(fm.getAltitudeMax()));
		bytes.write(writeIntLE(fm.getUnk38_val6000()));
		bytes.write(writeIntLE(fm.getAirSpeedMax()));
		bytes.write(writeIntLE(fm.getAirSpeedMin()));
		bytes.write(writeIntLE(fm.getRollAccel()));
		
		return bytes.toByteArray();
	}

}
