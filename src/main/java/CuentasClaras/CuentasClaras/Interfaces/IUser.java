package CuentasClaras.CuentasClaras.Interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import CuentasClaras.CuentasClaras.Modelos.User;

public interface IUser extends CrudRepository<User,Integer> {

	public Optional<?> findByuserName(String username);
	
	public Optional<?> findByemail(String email);

}
