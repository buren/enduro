package models;

public class Time {
    private int seconds;
    private boolean empty;

    /**
     * Creates a new empty time
     */
    public Time() {
        empty = true;
    }

    /**
     * Creates a new time
     *
     * @param time the time, as a string formatted "HH.mm.ss".
     */
    public Time(String time) {
        time = time.trim();
        seconds = Integer.parseInt(time.substring(0, 2)) * 3600;
        seconds += Integer.parseInt(time.substring(3, 5)) * 60;
        seconds += Integer.parseInt(time.substring(6));
    }

    /**
     * Creates a new time, based of seconds
     *
     * @param seconds Number of seconds since midnight.
     */
    public Time(int seconds) {
        if (seconds == 0) {
            empty = true;
        }
        this.seconds = seconds;
    }

    /**
     * Returns a string representing the time
     *
     * @return a string
     */
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
     * @param compare Time to compare to.
     * @return time difference
     */
    public Time getDifference(Time compare) {
        if (this.empty || compare.empty)
            return new Time();
        int difference = Math.abs(seconds - compare.seconds);
        return new Time(difference);
    }

    /**
     * Compares the amount of seconds passed since 00.00.00
     *
     * @param time
     * @return Positive if argument seconds are more, negative if less, 0 if equal.
     */
    public int compareTo(Time time) {
        return time.seconds - seconds;
    }

    /**
     * Returns a new time combining the two times.
     *
     * @param other time to add with.
     * @return the sum of the times.
     */
    public Time addTo(Time other) {
        return new Time(this.seconds += other.seconds);
    }

    /**
     * Compares two times to see which is earlier.
     *
     * @param time Time to compare to.
     * @return true if this is before other
     */
    public boolean isBefore(Time time) {
        return seconds < time.seconds;
    }

    /**
     * Compares two times. Returns false if either of the times are empty.
     *
     * @param obj Object to compare to.
     * @return true if the times are the same.
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
        return seconds == other.seconds;
    }

    /**
     * Checks if there is a time
     *
     * @return true if it is empty
     */
    public boolean isEmpty() {
        return empty;
    }
}
