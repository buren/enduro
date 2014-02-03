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
		return p.getId()==id;
	}

}
