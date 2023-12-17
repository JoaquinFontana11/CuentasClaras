package CuentasClaras.CuentasClaras.Interfaces;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import CuentasClaras.CuentasClaras.Modelos.Payment;

public interface IPayment extends CrudRepository<Payment,Integer>{
	
	@Query(value = "SELECT * FROM payments WHERE debtor = ?1 AND expense_id = ?2", nativeQuery = true)
	public Optional<Payment> findByDebtorANDExpense(int debtor_id,int expense_id);

}
