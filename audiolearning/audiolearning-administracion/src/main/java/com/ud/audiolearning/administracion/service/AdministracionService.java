package com.ud.audiolearning.administracion.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.ud.audiolearning.administracion.dao.AdministracionDao;
import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.dao.IAudioDao;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.Denuncia;


@AudioLService
public class AdministracionService {

	@Autowired
	AdministracionDao administracionDao;
	
	@Autowired
	IAudioDao audioDao;

	public List<Denuncia> denunciasPorAtender() {
		return administracionDao.denunciasSinAtender();
	}
	
	public List<Denuncia> denunciasProcesadas() {
		return administracionDao.denunciasProcesadas();
	}

	public void guardarDenuncia(Denuncia denuncia) {
		administracionDao.insertarDenuncia(denuncia);
	}

	
	public Denuncia consultarDetalleDenuncia(ObjectId idDenuncia){
		return administracionDao.buscarDenuncia(idDenuncia);
	} 
	
	public Audio consultarDatosAudio(String idAudio){
		return audioDao.findOneAudio(idAudio);
	}
	
	public void procesarDenuncia(Denuncia denuncia){
		administracionDao.actualizarDenunciaCierre(denuncia);
	}
	
	
}
