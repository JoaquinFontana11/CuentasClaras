package CuentasClaras.CuentasClaras.Modelos;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "icon")
	private String icon;
	
	@Column(name = "isGroup")
	private boolean isGroup;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Category(String name, String icon) {
		super();
		this.name = name;
		this.icon = icon;
	}

	public Category() {
		super();
	}

}
