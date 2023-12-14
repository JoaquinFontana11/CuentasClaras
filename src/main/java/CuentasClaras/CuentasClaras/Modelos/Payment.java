package CuentasClaras.CuentasClaras.Modelos;

import java.time.*;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name="payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name="amount")
	private double amount;
	
	@Column(name="accredited")
	private boolean accredited;
	
	@Column(name="date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "debtor")
	private User debtor;

	@ManyToOne
	private Expense expense;


	public void accredite() {
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isAccredited() {
		return accredited;
	}

	public void setAccredited(boolean accredited) {
		this.accredited = accredited;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Payment(User debtor, double amount, boolean accredited, LocalDate date) {
		super();
		this.amount = amount;
		this.accredited = accredited;
		this.date = date;
	}

	public Payment() {
		super();
	}

}
