package models;

public class Participant {
	private int id;
	private String name;
    private Race race;
	
	/**
	 * Participant identifies by their id, two participants with the same id are considered the same.
	 * @param id , id number
	 */
	public Participant(int id) {
		this.id = id;
		name = "Not named";
	}
	
	/**
     * Gets the participant id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets participant name to parameter
	 * @param name , name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the participants name
	 * @return name
	 */
	public String getName() {
		return name;
	}

    public void setRace(Race race) {
        this.race = race;
    }

    public Race getRace() {
        return race;
    }

    /**
     * Generates the hashCode for this participant
     * @return hashCode
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

    /**
     * Compares to another object to see if they are equal
     * If it is a participant, compares id.
     * @param obj Object to compare with.
     * @return true if equal
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
        return id == other.id;
    }

    public String print(int printLimit) {
        return id+"; "+name+race.print(printLimit);
    }
}
