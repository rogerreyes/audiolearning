package com.ud.audiolearning.api.domain;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection="Historial")
public class HistorialReproduccion implements Serializable{
	
	@Id
	private ObjectId id;
	private String usuario;
	@DBRef
	private List<Audio> audios;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public List<Audio> getAudios() {
		return audios;
	}
	public void setAudios(List<Audio> audios) {
		this.audios = audios;
	}
	
	

}
