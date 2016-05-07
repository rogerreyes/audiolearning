package com.ud.audiolearning.api.domain;

import java.util.Date;

public class ReporteDenuncia {

	private Usuario usuario;
	private Date fechaDenuncia;
	private String detalle;
	private String motivo;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaDenuncia() {
		return fechaDenuncia;
	}
	public void setFechaDenuncia(Date fechaDenuncia) {
		this.fechaDenuncia = fechaDenuncia;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	@Override
	public String toString() {
		return "ReporteDenuncia [usuario=" + usuario + ", fechaDenuncia=" + fechaDenuncia + ", detalle=" + detalle
				+ ", motivo=" + motivo + "]";
	}

	
	
	
}
