package models;

public class Participant {
	private int id;
	private String name;

	public Participant(int id) {
		this.id = id;
		name = "Not named";
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int HashCode() {
		return id;
	}

	public boolean equals(Participant p) {
		return p.getId() == id;
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
