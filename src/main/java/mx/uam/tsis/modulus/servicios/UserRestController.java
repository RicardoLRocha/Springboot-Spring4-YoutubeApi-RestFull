package mx.uam.tsis.modulus.servicios;

import java.util.Collection;
import java.util.List;

import mx.uam.tsis.modulus.negocio.CancionService;
import mx.uam.tsis.modulus.negocio.UserService;
import mx.uam.tsis.modulus.negocio.dominio.Cancion;
import mx.uam.tsis.modulus.negocio.dominio.Mensaje;
import mx.uam.tsis.modulus.negocio.dominio.User;

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
 * 
 * STATUS httpStatus
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
 * 
 * 
 * 
 *@autor Ricardo
 */
@RestController
@CrossOrigin
public class UserRestController {

	@Autowired
	private CancionService servicioCanciones;
	

	@Autowired
	private UserService servicioUsuarios;

	
	/** Retrieve
	 *  Metodo para obtener todos los usuarios 
	 */    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = servicioUsuarios.getUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    /** Busqueda del usuario via correo y pass
	 * viable para acceder por login
	 * http://localhost:8080/user/luis1/login/luis@gmail.com
	 * 
	 * @param pass, email de user
	 * @return mensaje
	 */
	@RequestMapping(value="/user/{pass}/login/{email:.+}", method = RequestMethod.GET )
	@ResponseBody
	public Mensaje getExisteUser(@PathVariable String pass, @PathVariable String email) {

		Boolean existeUserWithEmail = servicioUsuarios.getExisteUser( email );
		User usuario = null;
		
		Mensaje mensaje = new Mensaje(existeUserWithEmail);
		if( existeUserWithEmail ){
			mensaje.setMensaje("permitido");
			mensaje.setValorMandar( servicioUsuarios.getUser(email).getId() );
			
			return mensaje;
		}else{
			mensaje.setStatus(false);
			mensaje.setMensaje("denegado");
			mensaje.setValorMandar( -1 );
			
			/** problemas probando en localhost mediante servidro externo para aplicacion movil */
			
			//return new ResponseEntity<Mensaje>(mensaje, HttpStatus.NOT_FOUND);			
			return mensaje;
		}	
	}

    /** Nota para desarrolladores
     * 
     * GET con email
     * Spring trunca todo lo que viene después de DOT (.) Suponiendo que sea una extensión.
     * 
     * 
     * */
    
	/** Ayudara a ver si existe y ademas a buscar un usuario en espesifico
	 * http://localhost:8080/getuser/richi@gmail.com
	 * 
	 * @RequestMapping( value="/getBase64/<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>", method=RequestMethod.GET )
	 * 
	 * @param email
	 * @return User
	 * 	 */
	@RequestMapping(value="/getuser/{email:.+}", method=RequestMethod.GET)
	@ResponseBody
	public User dameUsuario(@PathVariable String email) {
		return servicioUsuarios.getUser(email);
	}
	
	/** Ayudara a ver si existe el correo al momento de registrarse
	 * por cuestiones de desarrollo local, no usamos "ResponseEntity" por problemas al exponer los servicios
	 * 	 * 
	 * 
	 * @param email
	 * @return Mensaje
	 * 	 */
	@RequestMapping(value="/verificacorreo/{email:.+}", method=RequestMethod.GET)
	@ResponseBody
	public Mensaje existeUsuario(@PathVariable String email) {
		
		String existeUserWithEmail = servicioUsuarios.getUser(email).getEmail();
		
		Mensaje mensaje = new Mensaje(existeUserWithEmail);
		
		if( existeUserWithEmail != null ){
			mensaje.setStatus(true);
			
			return mensaje;
		}else{
			mensaje.setStatus(false);
			mensaje.setMensaje("disponible");
			
			return mensaje;		
		}	
		
	}

	
	/**
	 * Metodo para obtener canciones de un usuario por su email, mediante el (URL): /userCanciones/{email}
	 * http://localhost:8081/user/canciones/email/richi@gmail.com
	 * 
	 * 
	 * @param email
	 * @return Collection<Cancion>
	 */
	@RequestMapping(value="/user/canciones/email/{email:.+}", method=RequestMethod.GET)
	@ResponseBody
	public Collection<Cancion> buscaCancionesUserByEmail(@PathVariable String email) {

		Collection<Cancion> canciones = servicioUsuarios.getCancionesByUser( email );
		return canciones;
	}
	 

	
	
	/**
	 * Metodo para obtener canciones de un usuario por su email, mediante el (URL): /userCanciones/{email}
	 * http://localhost:8081/user/canciones/id/1
	 * 
	 * @param id
	 * @return Collection<Cancion>
	 */
	@RequestMapping(value="/user/canciones/id/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Collection<Cancion> buscancionesUserId(@PathVariable int id) {

		return servicioUsuarios.getCancionesUserById( id );
	}

	
	/**
	 * Metodo para agregar usuario
	 * Viable para agregar un usuario con una Vista de formulario
	 * 
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value="/adduser", method = RequestMethod.POST)
	public ResponseEntity<User> agregaUsuario(@RequestBody User usuario) {

		Boolean existeUserWithEmail = servicioUsuarios.getExisteUser( usuario.getEmail() );

		if( !existeUserWithEmail ){
			//Invocar addAlumno en el servicio
			Boolean retorno = servicioUsuarios.addUsuario(usuario);

			if(retorno) {
				return new ResponseEntity<User>(usuario, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(usuario, HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<User>(usuario, HttpStatus.CONFLICT);			
		}	
	}

	/** metodo para actualizar lista de reproduccion de un usuario en base a su ID
	 * 
	 * @param id, cancion
	 * @return User
	 */
	@RequestMapping(value = "/updateuser/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public @ResponseBody User populateActivePSwapBasketPUT(@PathVariable int id, @RequestBody Cancion cancion) {		
		
		// primero realizo persistencia de la cancion misma
		servicioCanciones.addCancion(cancion);

		User usuarioActualizado = servicioUsuarios.actualizaCancionesDeUser(id, cancion);

		return usuarioActualizado;
	}
	

}