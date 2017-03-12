package mx.uam.tsis.modulus.negocio.dominio;

import javax.persistence.*;

import java.util.Set;

@Entity
public class User {


	@Id
    @Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;

    @Column(name="email")
    private String email;
    
    @Column(name="pass")    
	private String pass;
	private String genero_pref;

	@OneToMany
    @JoinTable(name = "canciones_de_user", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "cancion_id", referencedColumnName = "cancion_id"))
	private Set<Cancion> canciones;


	public User(){
	}

	public User(String name){
		this.name = name;
	}


	public User(int id, String name, String email, String pass, String genero_pref, Set<Cancion> canciones) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.genero_pref = genero_pref;
		this.canciones = canciones;
	}

	public User(String name, String email, String pass, String genero_pref, Set<Cancion> canciones) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.genero_pref = genero_pref;
		this.canciones = canciones;
	}

	public User(String name, String email, String pass, String genero_pref) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.genero_pref = genero_pref;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getGenero_pref() {
		return genero_pref;
	}

	public void setGenero_pref(String genero_pref) {
		this.genero_pref = genero_pref;
	}

	/** Usamos aqui cascade para que guarde en la tabla intermedia, publisher al tener un libro asociado 
	 * 
	 * 
	
	/** Crear Tabla User y en base a esta, se llena la Tabla Cancion con el ID segun los que esten en User
	 * 
	 * 	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="usuario")
	 * */
    public Set<Cancion> getCanciones() {
		return canciones;
	}
    

	public void setCanciones(Set<Cancion> canciones) {
		this.canciones = canciones;
	}	


}
