package mx.uam.tsis.modulus.negocio;

import java.util.Collection;
import java.util.List;

import mx.uam.tsis.modulus.datos.CancionRepository;
import mx.uam.tsis.modulus.negocio.dominio.Cancion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * En este servicio se lleva a cabo toda la logica de negocio
 * relacionada con los alumnos
 * 
 *
 */
@Service
public class CancionService {
	
	@Autowired
	private CancionRepository repository;
	
	
	/**
	 * Recupera las canciones con su nombre y artista
	 * 
	 * @param nombre
	 * @return Collection de Cancion o null
	 */
	public Collection<Cancion> getCancion(String nombre) {
		return repository.findByNombre(nombre);
	}
	
	
	/**
	 * Recupera las canciones con su nombre y artista
	 * 
	 * @return Collection de Cancion o null
	 */
	public List<Cancion> getCanciones() {
		return  repository.findAll();
	}
	
	
	/**
	 * Recupera la cancion con su nombre y artista
	 * 
	 * @param nombre, artista
	 * @return el Cancion o null
	 */
	public Cancion getCancionByName_Artist(String nombre, String artista) {
		return repository.findByNombreAndArtista(nombre, artista);
	}
	
	
	/**
	 * Agrega un cancion
	 * 
	 * @param cancion a agregar
	 * @return true si se agrego exitosamente, false sino
	 */
	public Boolean addCancion(Cancion cancion) {
		//NOTA: se puede cambiar para que regrese al alumno guardado 
		repository.save(cancion);
		return true; 
	}
	
	
}

