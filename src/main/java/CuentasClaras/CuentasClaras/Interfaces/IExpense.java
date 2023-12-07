package CuentasClaras.CuentasClaras.Interfaces;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;

import CuentasClaras.CuentasClaras.Modelos.Expense;

public interface IExpense extends CrudRepository<Expense,Integer>{

	
	@Query(value = "SELECT * FROM expenses WHERE user_owner_id = ?1", nativeQuery = true)
	public List<Expense> findByUserOwner(int id);
	
	@Query(value = "SELECT * FROM expenses WHERE group_owner_id = ?1", nativeQuery = true)
	public List<Expense> findByGroupOwner(int id);
}
