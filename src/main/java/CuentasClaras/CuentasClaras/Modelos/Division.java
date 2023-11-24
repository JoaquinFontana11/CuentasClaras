package CuentasClaras.CuentasClaras.Modelos;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "divisions")
public class Division {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "amount")
	private double amount;
	
	@ManyToOne
	private User userOwner;
	
	 @ManyToOne
	 private Expense expense;

	public Division() {
		super();
	}

	public Division(int id, double amount,User user,Expense expense) {
		super();
		this.id = id;
		this.amount = amount;
		this.userOwner = user;
		this.expense = expense;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(User userOwner) {
		this.userOwner = userOwner;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	

}
