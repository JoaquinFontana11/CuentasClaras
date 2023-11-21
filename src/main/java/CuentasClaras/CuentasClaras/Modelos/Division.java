package CuentasClaras.CuentasClaras.Modelos;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "divisions")
public abstract class Division {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "amount")
	private double amount;

	public abstract double calc(double cant);
}
