package com.ud.audiolearning.api.domain;

import java.io.Serializable;

import org.bson.types.Binary;

@SuppressWarnings("serial")
public class Imagen implements Serializable{

	
	private String nombre;
	private String mimeType;
	private Binary binaryData;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Binary getBinaryData() {
		return binaryData;
	}
	public void setBinaryData(Binary binaryData) {
		this.binaryData = binaryData;
	}
	
	
}
