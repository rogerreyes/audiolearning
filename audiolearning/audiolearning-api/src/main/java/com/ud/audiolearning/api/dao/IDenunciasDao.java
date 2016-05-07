package com.ud.audiolearning.api.dao;

import org.bson.types.ObjectId;

import com.ud.audiolearning.api.domain.Denuncia;
import com.ud.audiolearning.api.domain.ReporteDenuncia;

public interface IDenunciasDao {

	
	public void insertarDenuncia(Denuncia denuncia);
	
	public Denuncia existeDenuncia(ObjectId idAudio);

	public void agregarReporteDenuncia(ObjectId idAudio, ReporteDenuncia reporteDenuncia);
}
