package mx.uam.tsis.modulus.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.uam.tsis.modulus.negocio.YoutubeService;
import mx.uam.tsis.modulus.negocio.dominio.CancionYoutube;

@RestController
@CrossOrigin
public class CancionYoutubeRestController {
	
	@Autowired
	private YoutubeService servicioCanciones;
	
	/************
	 *Metodo Canción Youtube 
	 *********/
	@RequestMapping(value="/cancionyoutube/{name}", method=RequestMethod.GET)
	public ArrayList<CancionYoutube> buscaAlumno(@PathVariable String name) {
		ArrayList<CancionYoutube> canciones = servicioCanciones.busca(name);
		return canciones;
	}

}
