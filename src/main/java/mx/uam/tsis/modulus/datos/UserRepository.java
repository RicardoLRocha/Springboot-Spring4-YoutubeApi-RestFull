package mx.uam.tsis.modulus.datos;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import mx.uam.tsis.modulus.negocio.dominio.User;

public interface UserRepository extends CrudRepository<User, String>{
	
    
    /**
	 * Permite verificar usuario sino existe
	 * 
	 * @param email, la email de posible user
	 * @return null sino existe 
	 */    
    User findOneByEmail(String email); 
    
    /**
	 * Permite verificar usuario sino existe
	 * 
	 * @param id, el id de posible user
	 * @return null sino existe 
	 */    
    User findOneById(int id); 
    
    
    /** busqueda de usuaruo por nombre y password
	 * 
	 * @param nombre, pass
	 * @return User
	 * */
    User findOneByEmailAndPass(String nombre, String pass); 
    
    /** regresa todos los usuario
	 * 
	 * @param 
	 * @return List<User> 
	 * */
    List<User> findAll();


}
