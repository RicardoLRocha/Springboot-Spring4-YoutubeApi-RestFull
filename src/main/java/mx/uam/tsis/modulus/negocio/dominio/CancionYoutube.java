package mx.uam.tsis.modulus.negocio.dominio;

/**
 * Prueba
 * */
public class CancionYoutube {

	private String id_video;
	private String titulo;
	private String imagen;

	public CancionYoutube(String id_video, String titulo, String imagen) {
		this.id_video = id_video;
		this.titulo = titulo;
		this.imagen = imagen;
	}

	public String getId_video() {
		return id_video;
	}

	public void setId_video(String id_video) {
		this.id_video = id_video;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
