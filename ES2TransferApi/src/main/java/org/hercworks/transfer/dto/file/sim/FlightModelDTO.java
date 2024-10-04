package org.hercworks.transfer.dto.file.sim;

import org.hercworks.transfer.dto.file.TransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("flightModel")
public class FlightModelDTO extends TransferObject  {

	@JsonProperty(value = "", index = 1)
	private short pitchRate;
	
	@JsonProperty(value = "", index = 2)
	private short rollRate;
	
	@JsonProperty(value = "", index = 3)
	private short rudderForce;
	
	@JsonProperty(value = "", index = 4)
	private short pitchForce;
	
	@JsonProperty(value = "", index = 5)
	private short rollForce;
	
	@JsonProperty(value = "", index = 6)
	private short thrustFactor;
	
	@JsonProperty(value = "", index = 7)
	private short rollMax;
	
	@JsonProperty(value = "", index = 8)
	private short unk22_val16 = 16;
	
	@JsonProperty(value = "", index = 9)
	private short unk26_5or6;
	
	@JsonProperty(value = "", index = 10)
	private short rollFriction;
	
	@JsonProperty(value = "", index = 11)
	private int altitudeMax;
	
	@JsonProperty(value = "", index = 12)
	private int unk38_val6000 = 6000;
	
	@JsonProperty(value = "", index = 13)
	private int airSpeedMax;
	
	@JsonProperty(value = "", index = 14)
	private int airSpeedMin;
	
	@JsonProperty(value = "", index = 15)
	private int rollAccel;
	
	public FlightModelDTO() {}

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
