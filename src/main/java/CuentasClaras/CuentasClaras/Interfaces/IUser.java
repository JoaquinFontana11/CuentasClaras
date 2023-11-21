package CuentasClaras.CuentasClaras.Interfaces;

import org.springframework.data.repository.CrudRepository;
import CuentasClaras.CuentasClaras.Modelos.User;

public interface IUser extends CrudRepository<User,Integer> {

}
