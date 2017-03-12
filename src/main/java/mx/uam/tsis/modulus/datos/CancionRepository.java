package mx.uam.tsis.modulus.datos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import mx.uam.tsis.modulus.negocio.dominio.Cancion;

public interface CancionRepository extends CrudRepository<Cancion, String>{

    
    /**
	 * Permite encontrar las canciones a partir de su nombre
	 * 
	 * @param name, el name de la cancion
	 * @return el Collection de canciones o null
	 */
	List<Cancion> findByNombre(String nombre); 

	
	
    Cancion findByNombreAndArtista(String nombre, String artista); 
    
    /**
	 * Regresa una coleccion que contiene a todos los alumnos
	 * 
	 */
    List<Cancion> findAll();

    
}
