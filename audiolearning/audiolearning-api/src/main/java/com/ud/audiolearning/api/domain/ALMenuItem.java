package com.ud.audiolearning.api.domain;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "MenuItem")
public class ALMenuItem implements Serializable{

	@Id
	private ObjectId id;
	private String nombre;
	private String url;
	private String estado;
	private boolean navegable;
	private List<ALItem> items;

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

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public boolean isNavegable() {
		return navegable;
	}

	public void setNavegable(boolean navegable) {
		this.navegable = navegable;
	}

	public List<ALItem> getItems() {
		return items;
	}

	public void setItems(List<ALItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", nombre=" + nombre + ", url=" + url + ", estado=" + estado + ", navegable="
				+ navegable + ", items=" + items + "]";
	}
	
	

}
