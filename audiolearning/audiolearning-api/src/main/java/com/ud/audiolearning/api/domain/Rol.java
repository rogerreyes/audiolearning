package com.ud.audiolearning.api.domain;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "Rol")
public class Rol implements Serializable{

	@Id
	private ObjectId id;
	private String nombre;
	private String descripcion;
	@DBRef
	private List<ALMenuItem> menuItems;
	private List<String> commonUrl;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ALMenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<ALMenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public List<String> getCommonUrl() {
		return commonUrl;
	}

	public void setCommonUrl(List<String> commonUrl) {
		this.commonUrl = commonUrl;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", menuItems=" + menuItems
				+ ", commonUrl=" + commonUrl + "]";
	}

	

	
}
