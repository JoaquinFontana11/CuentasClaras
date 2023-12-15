package CuentasClaras.CuentasClaras.Interfaces;


import org.springframework.data.repository.CrudRepository;

import CuentasClaras.CuentasClaras.Modelos.Payment;

public interface IPayment extends CrudRepository<Payment,Integer>{

}
