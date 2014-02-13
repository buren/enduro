package models;

public class Time {

	private int seconds;
	private boolean empty;

	public Time() {
		empty = true;
	}

	public Time(String time) {
		seconds = Integer.parseInt(time.substring(0, 2)) * 3600;
		seconds += Integer.parseInt(time.substring(3, 5)) * 60;
		seconds += Integer.parseInt(time.substring(6));
	}

	public Time(int seconds) {
		this.seconds = seconds;
	}

	@Override
	public String toString() {
		if (empty)
			return "--.--.--";
		String hours, minutes, seconds;
		hours = String.format("%02d", this.seconds / 3600);
		minutes = String.format("%02d", (this.seconds % 3600) / 60);
		seconds = String.format("%02d", ((this.seconds % 3600) % 60));
		return hours + "." + minutes + "." + seconds;
	}

	/**
	 * Returns the absolute difference in time If one of the times is empty,
	 * returns a new empty time.
	 * 
	 * @param compare
	 * @return time difference
	 */
	public Time compareTo(Time compare) {
		if (this.empty || compare.empty)
			return new Time();
		int difference = Math.abs(seconds - compare.seconds);
		return new Time(difference);
	}

	/**
	 * Compares two times. Returns false if either of the times are empty.
	 * 
	 * @param equals
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		if (seconds != other.seconds)
			return false;
		return true;
	}

	public boolean isEmpty() {
		return empty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + seconds;
		return result;
	}

}
