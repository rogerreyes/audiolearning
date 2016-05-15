package com.ud.audiolearning.api.domain;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Denuncia")
public class Denuncia {

	 
	@Id
	private ObjectId id;
	private Audio audio;
	private String estado;
	private Usuario usuarioAtencion;
	private Date fechaApertura;
	private Date fechaCierre;
	private String detalleCierre;
	private String Resultado;
	private int numeroDenuncias;
	private List<ReporteDenuncia> reportesDenuncia;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Audio getAudio() {
		return audio;
	}
	public void setAudio(Audio audio) {
		this.audio = audio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Usuario getUsuarioAtencion() {
		return usuarioAtencion;
	}
	public void setUsuarioAtencion(Usuario usuarioAtencion) {
		this.usuarioAtencion = usuarioAtencion;
	}
	public Date getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getDetalleCierre() {
		return detalleCierre;
	}
	public void setDetalleCierre(String detalleCierre) {
		this.detalleCierre = detalleCierre;
	}
	public String getResultado() {
		return Resultado;
	}
	public void setResultado(String resultado) {
		Resultado = resultado;
	}
	public List<ReporteDenuncia> getReportesDenuncia() {
		return reportesDenuncia;
	}
	public void setReportesDenuncia(List<ReporteDenuncia> reportesDenuncia) {
		this.reportesDenuncia = reportesDenuncia;
	}
	public int getNumeroDenuncias() {
		return numeroDenuncias;
	}
	public void setNumeroDenuncias(int numeroDenuncias) {
		this.numeroDenuncias = numeroDenuncias;
	}
	@Override
	public String toString() {
		return "Denuncia [id=" + id + ", audio=" + audio + ", estado=" + estado + ", usuarioAtencion=" + usuarioAtencion
				+ ", fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre + ", detalleCierre="
				+ detalleCierre + ", Resultado=" + Resultado + ", numeroDenuncias=" + numeroDenuncias
				+ ", reportesDenuncia=" + reportesDenuncia + "]";
	}
	
	
	
	
	
	
}
