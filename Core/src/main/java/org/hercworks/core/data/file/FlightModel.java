package org.hercworks.core.data.file;

import org.hercworks.voln.DataFile;


/**
 * 	FILE
 * 		/DBSIM/fm/RAZOR.DAT and DBSIM/fm/SKIMMER.DAT
 * 
 * 0- UINT16 - PITCH\RATE\MAX
 * 2- UINT16 - ROLL\RATE\MAX
 * 4- UINT16 - RUDDER\FORCE
 * 6- UINT16 - PITCH\FORCE
 * 8- UINT16 - ROLL\FORCE
 * 10- UINT16 - THRUST?
 * 12- UINT16 - ? blank
 * 14- UINT16 - ? blank
 * 16- UINT16 - ? blank
 * 18- UINT16 - ROLL\MAX?
 * 20- UINT16 - ? blank
 * 22- UINT16 - ? always 16 - kinda unknown  
 * 24- UINT16 - ? blank
 * 26- UINT16 - ? 5 for Razor, 6 for Skimmer
 * 28- UINT16 - ? blank
 * 30- UINT16 - ROLL\FRICTION
 * 32- UINT16 - ? blank
 * 34- UINT32 - ALTITUDE\MAX
 * 38- UINT32 - ? 6000
 * 42- UINT32 - AIRSPEED\MAX
 * 46- UINT32 - AIRSPEED\MIN
 * 50- UINT32 - ROLL\MAX
 */
public class FlightModel extends DataFile {

	
	private short pitchRate;
	private short rollRate;
	private short rudderForce;
	private short pitchForce;
	private short rollForce;
	private short thrustFactor;
	//blank bytes x2
	//blank bytes x2
	//blank bytes x2
	private short rollMax;
	//blank bytes x2
	private short unk22_val16 = 16;
	//blank bytes x2
	private short unk26_5or6;
	//blank bytes x2
	private short rollFriction;
	//blank bytes x2
	private int altitudeMax;
	private int unk38_val6000 = 6000;
	private int airSpeedMax;
	private int airSpeedMin;
	private int rollAccel;
	
	public FlightModel() {}

	public short getPitchRate() {
		return pitchRate;
	}

	public short getRollRate() {
		return rollRate;
	}

	public short getRudderForce() {
		return rudderForce;
	}

	public short getPitchForce() {
		return pitchForce;
	}

	public short getRollForce() {
		return rollForce;
	}

	public short getThrustFactor() {
		return thrustFactor;
	}

	public short getRollMax() {
		return rollMax;
	}

	public short getUnk22_val16() {
		return unk22_val16;
	}

	public short getUnk26_5or6() {
		return unk26_5or6;
	}

	public short getRollFriction() {
		return rollFriction;
	}

	public int getAltitudeMax() {
		return altitudeMax;
	}

	public int getUnk38_val6000() {
		return unk38_val6000;
	}

	public int getAirSpeedMax() {
		return airSpeedMax;
	}

	public int getAirSpeedMin() {
		return airSpeedMin;
	}

	public int getRollAccel() {
		return rollAccel;
	}

	public void setPitchRate(short pitchRate) {
		this.pitchRate = pitchRate;
	}

	public void setRollRate(short rollRate) {
		this.rollRate = rollRate;
	}

	public void setRudderForce(short rudderForce) {
		this.rudderForce = rudderForce;
	}

	public void setPitchForce(short pitchForce) {
		this.pitchForce = pitchForce;
	}

	public void setRollForce(short rollForce) {
		this.rollForce = rollForce;
	}

	public void setThrustFactor(short thrustFactor) {
		this.thrustFactor = thrustFactor;
	}

	public void setRollMax(short rollMax) {
		this.rollMax = rollMax;
	}

	public void setUnk22_val16(short unk22_val16) {
		this.unk22_val16 = unk22_val16;
	}

	public void setUnk26_5or6(short unk26_5or6) {
		this.unk26_5or6 = unk26_5or6;
	}

	public void setRollFriction(short rollFriction) {
		this.rollFriction = rollFriction;
	}

	public void setAltitudeMax(int altitudeMax) {
		this.altitudeMax = altitudeMax;
	}

	public void setUnk38_val6000(int unk38_val6000) {
		this.unk38_val6000 = unk38_val6000;
	}

	public void setAirSpeedMax(int airSpeedMax) {
		this.airSpeedMax = airSpeedMax;
	}

	public void setAirSpeedMin(int airSpeedMin) {
		this.airSpeedMin = airSpeedMin;
	}

	public void setRollAccel(int rollAccel) {
		this.rollAccel = rollAccel;
	}
	
}
