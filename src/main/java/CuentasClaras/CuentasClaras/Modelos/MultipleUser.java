package CuentasClaras.CuentasClaras.Modelos;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name="multiple_users")
public class MultipleUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="amount")
	private double amount;
	
	@OneToOne
	private User userOwner;
	
	@OneToOne
	@JoinColumn(name="expense")
	private Expense expense;
}
