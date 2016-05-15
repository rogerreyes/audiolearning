package com.ud.audiolearning.api.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(language = "spanish", collection = "Audio")
public class Audio implements Serializable{

	
	@Id
	private ObjectId id;
	@TextIndexed(weight=2)
	private String titulo;
	private Date fechaCreacion;
	private String estado;
	@DBRef
	private Usuario usuario;
	@TextIndexed
	private String descripcion;
	private String genero;
	private String idioma;
	private String fileName;
	private String fileMimeType;
	@DBRef(lazy = true)
	private List<ListaDifusion> listasDifusion;
	@DBRef
	private List<Categoria> categorias;
	private List<Etiqueta> etiquetas;
	private String file;
	private Imagen imagen;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public List<ListaDifusion> getListasDifusion() {
		return listasDifusion;
	}

	public void setListasDifusion(List<ListaDifusion> listasDifusion) {
		this.listasDifusion = listasDifusion;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileMimeType() {
		return fileMimeType;
	}

	public void setFileMimeType(String fileMimeType) {
		this.fileMimeType = fileMimeType;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Audio [id=" + id + ", titulo=" + titulo + ", fechaCreacion=" + fechaCreacion + ", estado=" + estado
				+ ", usuario=" + usuario + ", descripcion=" + descripcion + ", genero=" + genero + ", idioma=" + idioma
				+ ", imagen=" + imagen + ", listasDifusion=" + listasDifusion + ", categorias=" + categorias
				+ ", etiquetas=" + etiquetas + "]";
	}

}
