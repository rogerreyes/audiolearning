package com.ud.audiolearning.api.domain;

import java.util.Date;

public class Comentario {
	
	private Date fechaComentario;
	private String Autor;
	private String comentario;
	public Date getFechaComentario() {
		return fechaComentario;
	}
	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}
	public String getAutor() {
		return Autor;
	}
	public void setAutor(String autor) {
		Autor = autor;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	@Override
	public String toString() {
		return "Comentario [fechaComentario=" + fechaComentario + ", Autor=" + Autor + ", comentario=" + comentario
				+ "]";
	}
	
	
	
	
	
	

}
