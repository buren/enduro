package models;

public class Participant {
	private int id;
	private String name;
	
	/**
	 * Participant identifies by their id, two participants with the same id are considered the same.
	 * @param id , id number
	 */

	public Participant(int id) {
		this.id = id;
		name = "Not named";
	}
	
	/**
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
	 * 
	 * @return name
	 */

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
