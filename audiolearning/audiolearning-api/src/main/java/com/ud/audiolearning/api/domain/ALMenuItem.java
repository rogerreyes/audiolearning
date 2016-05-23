package com.ud.audiolearning.api.domain;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "MenuItem")
public class ALMenuItem implements Serializable {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ALMenuItem other = (ALMenuItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
}
