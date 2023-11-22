package CuentasClaras.CuentasClaras.Interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import CuentasClaras.CuentasClaras.Modelos.Category;

public interface ICategory extends CrudRepository<Category,Integer>{

	public Optional<Category> findByname(String name);
}
