package com.ud.audiolearning.reproductor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.gridfs.GridFSDBFile;
import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.dao.IAudioDao;
import com.ud.audiolearning.api.dao.IDenunciasDao;
import com.ud.audiolearning.api.dao.IHistoricoDao;
import com.ud.audiolearning.api.dao.ApiDao;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.Denuncia;
import com.ud.audiolearning.api.domain.Parametrico;
import com.ud.audiolearning.api.domain.ReporteDenuncia;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.reproductor.dao.ReproductorDao;

@AudioLService
public class ReproductorService  {

	@Autowired
	ReproductorDao reproductorDao;
	
	@Autowired
	ApiDao apiDao;
	
	@Autowired
	IAudioDao audioDao;

	@Autowired
	IHistoricoDao historicoDao;
	
	@Autowired
	IDenunciasDao denunciasDao;
	
	public Audio buscarAudio (String id){
		return audioDao.findOneAudio(id);
	}

	public GridFSDBFile buscarAudioFile(Audio audio) {
		return reproductorDao.findAudioFile(String.valueOf(audio.getFile()));
	}

	public void agregarHistorico(Audio audio) {
		historicoDao.agregarHistorico(AppSession.getUser().getId().toString(), audio);
	}
	
	
	public List<Parametrico> buscarTiposDenincia() {
		return apiDao.findAllTiposDenuncia();
	}
	
	public void enviarDenuncia(Audio audio, ReporteDenuncia reporteDenuncia){

		if(denunciasDao.existeDenuncia(audio.getId())==null){
			Denuncia d = new Denuncia();
			d.setAudio(audio);
			d.setEstado("A");
			d.setFechaApertura(new Date());
			d.setNumeroDenuncias(1);
			List<ReporteDenuncia> listaReportes= new ArrayList<ReporteDenuncia>();
			listaReportes.add(reporteDenuncia);
			d.setReportesDenuncia(listaReportes);
			denunciasDao.insertarDenuncia(d);
		}else{
			denunciasDao.agregarReporteDenuncia(audio.getId(),reporteDenuncia);
		}

	}
	
	
}
