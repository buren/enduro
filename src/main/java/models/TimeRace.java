package models;

import java.util.ArrayList;

public class TimeRace extends Race {
	private Time timeCap;
	
	public TimeRace(Object o) {
		super(o);
		this.timeCap = new Time((String)o);
		laps = new ArrayList<>();
		laps.add(new Lap());
	}

	@Override
	public void setLapTime(Time lapTime) {
		setFinish(lapTime);
		if (timeCap.addTimes(getStart()).compareValue(lapTime) > 0) {
			Lap newLap = new Lap();
			newLap.setStart(lapTime);
			laps.add(newLap);
			size++;
		}
	}

	
	
		
}
