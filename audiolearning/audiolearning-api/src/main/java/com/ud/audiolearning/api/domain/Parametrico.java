package com.ud.audiolearning.api.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection="Parametrico")
public class Parametrico implements Serializable{

	@Id
	private ObjectId id;
	private String codigo;
	private String nombre;
	private String tipo;
	
	
	

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Parametrico [codigo=" + codigo + ", nombre=" + nombre + ", tipo=" + tipo + "]";
	}

	
	
	
}
