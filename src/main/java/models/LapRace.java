package models;

import java.util.ArrayList;
import java.util.HashMap;

public class LapRace extends Race {
	private int lapCap;
	
	
	public LapRace(Object lapCap) {
		super(lapCap);
		laps = new ArrayList<>();
		laps.add(new Lap());
		this.lapCap = (Integer) lapCap;
	}
	
	/**
     * Returns the lap cap
     * @return the lap cap
     */
	public int getLapsCap() {
		return lapCap;
	}

	@Override
	public void setLapTime(Time lapTime) {
		setFinish(lapTime);
		if (size + 1 < lapCap) {
			Lap newLap = new Lap();
			newLap.setStart(lapTime);
			laps.add(newLap);
			size++;
		}
	}
	
	
		
}
