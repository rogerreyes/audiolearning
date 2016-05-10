package com.ud.audiolearning.api.domain;


import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class CriterioBusqueda {

	private String tipo;
	private String query;
	private String usuario;
	private String titulo;
	private String etiqueta;
	private String idioma;
	private Date fechaInicial;
	private Date fechafinal;
	private List<ObjectId> categorias;
	private List<String> generos;

	
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechafinal() {
		return fechafinal;
	}

	public void setFechafinal(Date fechafinal) {
		this.fechafinal = fechafinal;
	}



	public List<ObjectId> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ObjectId> categorias) {
		this.categorias = categorias;
	}



	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	@Override
	public String toString() {
		return "CriterioBusqueda [tipo=" + tipo + ", query=" + query + ", usuario=" + usuario + ", titulo=" + titulo
				+ ", etiqueta=" + etiqueta + ", idioma=" + idioma + ", fechaInicial=" + fechaInicial + ", fechafinal="
				+ fechafinal + ", categorias=" + categorias + ", Generos=" + generos + "]";
	}

	
	
	
}


