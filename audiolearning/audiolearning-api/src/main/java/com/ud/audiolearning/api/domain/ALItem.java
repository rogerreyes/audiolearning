package com.ud.audiolearning.api.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection="Item")
public class ALItem implements Serializable{

	private String nombre;
	private String url;
	private String estado;
	private boolean navegable;
	
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isNavegable() {
		return navegable;
	}
	public void setNavegable(boolean navegable) {
		this.navegable = navegable;
	}
	@Override
	public String toString() {
		return "Item [nombre=" + nombre + ", url=" + url + ", estado=" + estado + ", navegable=" + navegable + "]";
	}
	
	
	
	
}
