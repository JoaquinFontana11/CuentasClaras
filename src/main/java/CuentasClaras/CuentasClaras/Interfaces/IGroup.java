package CuentasClaras.CuentasClaras.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import CuentasClaras.CuentasClaras.Modelos.Category;
import CuentasClaras.CuentasClaras.Modelos.Expense;
import CuentasClaras.CuentasClaras.Modelos.Group;


public interface IGroup extends CrudRepository<Group,Integer>{
	
	public Optional<Group> findByname(String name);

}
