package mx.uam.tsis.modulus.negocio.dominio;


/**
 *  Clase de apoyo paa no enviar datos extra y/o sensibles
 * 
 * */
public class Mensaje {

	private boolean status;
	private String mensaje;
	private int valorMandar;
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getValorMandar() {
		return valorMandar;
	}
	public void setValorMandar(int valorMandar) {
		this.valorMandar = valorMandar;
	}
	
	public Mensaje(boolean status, String mensaje, int valorMandar) {
		super();
		this.status = status;
		this.mensaje = mensaje;
		this.valorMandar = valorMandar;
	}
	public Mensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Mensaje(boolean status) {
		this.status = status;
	}
	
	
}
