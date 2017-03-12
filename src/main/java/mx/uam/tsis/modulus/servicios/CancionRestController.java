package mx.uam.tsis.modulus.servicios;

import java.util.Collection;
import java.util.List;

import mx.uam.tsis.modulus.negocio.CancionService;
import mx.uam.tsis.modulus.negocio.dominio.Cancion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * Controlador para el API rest
 * 
 */
//@CrossOrigin( methods = RequestMethod.GET, allowCredentials = "true")

@RestController
@CrossOrigin
public class CancionRestController {

	@Autowired
	private CancionService servicioCanciones;


	/**
	 *  Metodo para obtener todos las canciones 
	 *@return List<Cancion>
	 */
	@RequestMapping(value="/canciones", method=RequestMethod.GET)
	@ResponseBody
	@CrossOrigin( methods = RequestMethod.GET, allowCredentials = "true")
	public List<Cancion> dameCanciones() {
		return servicioCanciones.getCanciones();
	}


	/**
	 * Metodo para obtener canciones, mediante el (URL): /cancion/{name}
	 * 
	 * ejemplo: http://localhost:8080/cancion/Culero
	 * 
	 * @param name
	 * @return Collection<Cancion>
	 */
	@RequestMapping(value="/cancion/{name}", method=RequestMethod.GET)
	@ResponseBody
	//	@CrossOrigin( methods = RequestMethod.GET, allowCredentials = "true")
	public ResponseEntity<Collection<Cancion>> buscaCancion(@PathVariable String name) {
		Collection<Cancion> canciones = servicioCanciones.getCancion(name);

		if( canciones != null ){
			return new ResponseEntity<Collection<Cancion>>(canciones, HttpStatus.OK);

		}else{
			return new ResponseEntity<Collection<Cancion>>(canciones, HttpStatus.NOT_FOUND);			
		}	


	}


	/** buscar cancion con su nombre y artista correspondiente
	 * http://localhost:8081/cancion/culero/Nana%20pancha
	 * 
	 * @return Cancion
	 * */
	@RequestMapping(value="/cancion/{nombre}/{artista}", method=RequestMethod.GET)
	@ResponseBody
	public Cancion buscaCancionNombreAutor(@PathVariable String nombre, @PathVariable String artista) {
		Cancion canciones = servicioCanciones.getCancionByName_Artist(nombre, artista);
		return canciones;
	}


	/** Guarda registro de una cancion nueva
	 * http://localhost:8081/cancion/culero/Nana%20pancha
	 * 
	 * @return Cancion
	 * */
	@RequestMapping(value="/agregacancion", method = RequestMethod.POST)
	public ResponseEntity<Cancion> agregaAlumno(@RequestBody Cancion cancion) {

		//Invocar addAlumno en el servicio
		Boolean retorno = servicioCanciones.addCancion(cancion);

		if(retorno) {
			return new ResponseEntity<Cancion>(cancion, HttpStatus.OK);
		} else {
			return new ResponseEntity<Cancion>(cancion, HttpStatus.BAD_REQUEST);
		}
	}




}