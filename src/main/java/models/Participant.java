package models;

public class Participant {
	private String id;
	private String name;

	public Participant(String id) {
		this.id = id;
		name = "Not named";
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int HashCode() {
		return Integer.parseInt(id);
	}

	public boolean equals(Participant p) {
		return p.getId().equals(id);
	}

}
