package CuentasClaras.CuentasClaras.Modelos;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "expenses")
public abstract class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "amount")
	private double amount;

	@Column(name = "img")
	private String img;

	@OneToOne
	private Division divisionType;

	@Column(name = "isRecurrent")
	private boolean isRecurrent;

	@Column(name = "recurrency")
	private String recurrency;

	@Column(name = "cantRecurrency")
	private int cantRecurrency;

	@OneToMany(mappedBy="expense",cascade = CascadeType.ALL)
	private List<Payment> payments;

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

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
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

	public Division getDivisionType() {
		return divisionType;
	}

	public void setDivisionType(Division divisionType) {
		this.divisionType = divisionType;
	}

	public boolean isRecurrent() {
		return isRecurrent;
	}

	public void setRecurrent(boolean isRecurrent) {
		this.isRecurrent = isRecurrent;
	}

	public Expense(double amount, String img, User owner,Division divisionType, boolean isRecurrent) {
		super();
		this.amount = amount;
		this.img = img;
		this.divisionType = divisionType;
		this.isRecurrent = isRecurrent;
	}

	public Expense() {
		super();
	}

}
