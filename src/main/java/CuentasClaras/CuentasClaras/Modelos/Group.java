package CuentasClaras.CuentasClaras.Modelos;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name="party")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(mappedBy = "groups")
	private List<User> memebers;
	
	@ManyToOne
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}

	public Group(String name, Category category) {
		super();
		this.name = name;
		//this.category = category;
	}

	public Group() {
		super();
	}

	public void addMember() {
	}

	public void addExpence() {
	}

}
