package mx.uam.tsis.modulus.negocio;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.uam.tsis.modulus.datos.UserRepository;
import mx.uam.tsis.modulus.negocio.dominio.Cancion;
import mx.uam.tsis.modulus.negocio.dominio.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * En este servicio se lleva a cabo toda la logica de negocio
 * relacionada con los alumnos
 * 
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	
	/**
	 * Recupera un usuario a partir de su email
	 * 
	 * @param email
	 * @return el user o null
	 */
	public List<User> getUsers() {
		return repository.findAll();
	}

	/**
	 * Recupera un usuario a partir de su email
	 * 
	 * @param email
	 * @return el user o null
	 */
	public User getUser(String email) {
		return repository.findOneByEmail(email);
	}

	
	public Boolean getExisteUser(String email) {
		if (  repository.findOneByEmail(email) == null )
			return false;
		else
			return true;
	}
	
	
	/**
	 * Elimina un alumno a partir de su email
	 * 
	 * @param email
	 * @return el user o null
	 */
	public Boolean deleteUser(String email) {
		/*MEtodo .delete no regresa nada por ende solo madamos true a menos que genere alguna exception*/
		try{
			repository.delete( repository.findOneByEmail(email) );
			return true;
		}catch (Exception e){
			return false;
		}
	}


	/**
	 * Agrega un usuario nuevo
	 * 
	 * @param alumno el alumno a agregar
	 * @return true si se agrego exitosamente, false sino
	 */
	public Boolean addUsuario(User usuario) {
		//NOTA: se puede cambiar para que regrese al alumno guardado 
		repository.save(usuario);		
		return true; 
	}

	
	/**
	 * Recupera todas las canciones asociadas a un usuario
	 * 
	 * @param email
	 * @return Set de canciones
	 */
	public Collection<Cancion> getCancionesByUser( String email ) {
		return  repository.findOneByEmail(email).getCanciones();
	}
	
	/**
	 * Recupera todas las canciones asociadas a un usuario
	 * 
	 * @return Set de canciones
	 */
	public Collection<Cancion> getCancionesUserById( int id ) {
		return  repository.findOneById(id).getCanciones();
	}
	
	/**
	 * Recupera todas las canciones asociadas a un usuario
	 * 
	 * @return Set de canciones
	 */
	public User actualizaCancionesDeUser( int id, Cancion cancion ) {
		
		User userUpdate = repository.findOneById(id);
		
		Set<Cancion> cancionesUpdate = new HashSet<Cancion>();
		cancionesUpdate =  userUpdate.getCanciones() ;
		cancionesUpdate.add(cancion);
		
		userUpdate.setCanciones(cancionesUpdate);
		
		return  repository.save(userUpdate);
	}
	
	
	/**
	 * Recupera todas las canciones asociadas a un usuario
	 * 
	 * @return Set de canciones
	 */
	public User actualizaCancionesDeUserEmail( String email, Cancion cancion ) {
		
		User userUpdate = repository.findOneByEmail(email);
		
		Set<Cancion> cancionesUpdate = new HashSet<Cancion>();
		cancionesUpdate =  userUpdate.getCanciones() ;
		cancionesUpdate.add(cancion);
		
		userUpdate.setCanciones(cancionesUpdate);
		
		return  repository.save(userUpdate);
	}
	
	/**
	 * Recupera la cancion con su nombre y artista
	 * 
	 * @param nombre, artista
	 * @return el Cancion o null
	 */
	public User getUserLogin(String email, String pass) {
		return repository.findOneByEmailAndPass(email, pass);
	}
	
}

