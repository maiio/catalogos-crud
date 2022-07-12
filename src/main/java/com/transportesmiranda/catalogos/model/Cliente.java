package com.transportesmiranda.catalogos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase modelo para mapear la tabla de bd Clientes a  entidad.
 * @author mayolomirandamiranda
 *
 */
@Entity
@Table(name = "Clientes")
public class Cliente implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	//identificador de l catalogo.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCliente;
	
	// Nombre del cliente.
	@Column(name = "nombre")
	private String nombre;
	
	// fecha en que se realizo el registro del cliente.
	@Column(name = "fechaRegistro")
	private String fechaRegistro;
	
	//fecha en que se modifico el cliente.
	@Column(name = "fechaModificacion")
	private String fechaModificacion;
	
	/**
	 * 
	 * Constructor de clase Cliente por default.
	 */
	public Cliente()  {}

	/**
	 * 
	 * Constructor de clase Cliente.
	 * @param idCliente llave primaria para el catalogo de clientes
	 * @param nombre nombre del cliente 
	 * @param fechaRegistro fecha en que se registro el cliete
	 * @param fechaModificacion decha en que se modifico
	 */
	public Cliente(long idCliente, String nombre, String fechaRegistro, String fechaModificacion) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.fechaModificacion = fechaModificacion;
	}
	
	/**
	 * 
	 * Constructor de clase Cliente.
	 * @param idCliente llave primaria para el catalogo de clientes
	 * @param nombre nombre del cliente 
	 * @param fechaRegistro fecha en que se registro el cliete
	 */
	public Cliente(long idCliente, String nombre, String fechaRegistro) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", fechaRegistro=" + fechaRegistro
				+ ", fechaModificacion=" + fechaModificacion + "]";
	}
	


}
