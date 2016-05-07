package com.ud.audiolearning.api.domain;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@SuppressWarnings("serial")
@Document(collection="Favoritos")
public class Favorito implements Serializable {
	
	
	@Id
	private ObjectId id;
	private String usuario;
	@DBRef
	private List<Audio> audios;
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
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Favorito [id=" + id + ", usuario=" + usuario + ", audios=" + audios + "]";
	}
	
	
	

}
