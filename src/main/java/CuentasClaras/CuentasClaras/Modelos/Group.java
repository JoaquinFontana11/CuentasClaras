package CuentasClaras.CuentasClaras.Modelos;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "party")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "groups")
	private List<User> members;

	@ManyToOne
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Group(String name, Category category) {
		super();
		this.name = name;
		this.category = category;
		this.members = new ArrayList<User>();
	}

	public Group() {
		super();
	}

	public void addMember(User u) {
		this.members.add(u);
	}

	public void addExpence() {
	}

}
