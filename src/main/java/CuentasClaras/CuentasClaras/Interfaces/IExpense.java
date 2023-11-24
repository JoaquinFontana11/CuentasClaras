package CuentasClaras.CuentasClaras.Interfaces;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;

import CuentasClaras.CuentasClaras.Modelos.Expense;

public interface IExpense extends CrudRepository<Expense,Integer>{

	
	@Query("SELECT e FROM expenses e WHERE e.user_owner_id = ?1")
	public List<Expense> findByUserOwner(int id);
	
	@Query("SELECT e FROM expenses e WHERE e.group_owner_id = ?1")
	public List<Expense> findByGroupOwner(int id);
}
