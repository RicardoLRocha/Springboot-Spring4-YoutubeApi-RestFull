package mx.uam.tsis.modulus.negocio.dominio;

import javax.persistence.*;


@Entity
public class Cancion{

	@Id
    @Column(name="cancion_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nombre;
	private String genero;
	private String url;
	private String artista;
	private String autor;
	private String album;

	public Cancion() {
	}

	public Cancion(String nombre) {
		this.nombre = nombre;
	}
	
	public Cancion(String nombre, String genero, String url, String artista, String autor, String album ) {
		this.nombre = nombre;
		this.genero = genero;
		this.url = url;
		this.artista = artista;
		this.autor = autor;
		this.album = album;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	/*
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_publisher", 
		joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "id"))
	 */
	/*
	@ManyToOne(fetch=FetchType.LAZY)	
		@JoinColumn(name="user_id")
	public User getUsuario() {
		return usuario;
	}
	

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	*/

}
