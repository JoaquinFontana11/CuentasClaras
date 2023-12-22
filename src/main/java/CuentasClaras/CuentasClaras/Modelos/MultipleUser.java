package CuentasClaras.CuentasClaras.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name="multiple_users")
public class MultipleUser{
	
	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="amount")
	private double amount;
	
	@ManyToOne
	private User userOwner;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="expense")
	private Expense expense;
	

	public MultipleUser() {
		super();
	}
	
	

	public MultipleUser(double amount, User userOwner, Expense expense) {
		super();
		this.amount = amount;
		this.userOwner = userOwner;
		this.expense = expense;
	}



	public int getId() {
		return id;
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
