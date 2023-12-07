package CuentasClaras.CuentasClaras.Modelos;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "expenses")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "amount")
	private double amount;

	@Column(name = "img")
	private String img;

	@OneToMany(mappedBy="expense",cascade = CascadeType.ALL)
	private List<Division> divisions;

	@Column(name = "isRecurrent")
	private boolean isRecurrent;

	@Column(name = "recurrency")
	private String recurrency;

	@Column(name = "cantRecurrency")
	private int cantRecurrency;

	@ManyToOne
	private Category category;
	
	//"group" o "user"
	@Column(name = "type")
	private String type;
	
	//Si el gasto lo realizo mas de una persona esto es mayor a 1, sino es 1;
	@OneToMany(mappedBy="expense")
	private List<MultipleUser> amountUsers;
	
	//Si es de tipo "group" se utiliza esta variable
	@ManyToOne
	private Group groupOwner;
	
	//Si es de tipo "user" se utiliza esta variable
	@ManyToOne
	private User userOwner;
	

	public Expense(double amount,String img,List<Division> div,boolean isRecurrent,String recurrency,int cantRecurrency,Category category,String type,List<MultipleUser> amountUsers,Group groupOwner) {
		super();
		this.amount = amount;
		this.img = img;
		this.divisions = div;
		this.isRecurrent = isRecurrent;
		this.recurrency = recurrency;
		this.cantRecurrency = cantRecurrency;
		this.category = category;
		this.type = type;
		this.amountUsers = amountUsers;
		this.groupOwner = groupOwner;
	}
	
	public Expense(double amount,String img,List<Division> div,boolean isRecurrent,String recurrency,int cantRecurrency,Category category,String type,User userOwner) {
		super();
		this.amount = amount;
		this.img = img;
		this.divisions = div;
		this.isRecurrent = isRecurrent;
		this.recurrency = recurrency;
		this.cantRecurrency = cantRecurrency;
		this.category = category;
		this.type = type;
		this.userOwner = userOwner;
	}

	public Expense() {
		super();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecurrency() {
		return recurrency;
	}

	public void setRecurrency(String recurrency) {
		this.recurrency = recurrency;
	}

	public int getCantRecurrency() {
		return cantRecurrency;
	}

	public void setCantRecurrency(int cantRecurrency) {
		this.cantRecurrency = cantRecurrency;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<MultipleUser> getAmountUsers() {
		return amountUsers;
	}

	public void setAmountUsers(List<MultipleUser> amountUsers) {
		this.amountUsers = amountUsers;
	}

	public Group getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(Group groupOwner) {
		this.groupOwner = groupOwner;
	}

	public User getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(User userOwner) {
		this.userOwner = userOwner;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<Division> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}

	public boolean isRecurrent() {
		return isRecurrent;
	}

	public void setRecurrent(boolean isRecurrent) {
		this.isRecurrent = isRecurrent;
	}

}
