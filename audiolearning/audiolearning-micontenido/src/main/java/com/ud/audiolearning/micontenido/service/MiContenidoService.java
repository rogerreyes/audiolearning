package com.ud.audiolearning.micontenido.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBRef;
import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.micontenido.dao.AudioDao;
import com.ud.audiolearning.micontenido.dao.HistorialDao;


@AudioLService
public class MiContenidoService {

	@Autowired
	AudioDao audioDao;
	
	@Autowired
	HistorialDao historialDao;
	
	
	public List<Audio> consultarAudiosUsuario() {
		return audioDao.consultarAudiosUsuario();
	}
	
	public List<Audio> consultarFavoritos(String idUsuario){
		return audioDao.audiosFavoritos(idUsuario);
	}
	

	public void agregarFavorito(Audio audio){
		audioDao.agregarFavorito(AppSession.getUser().getId().toString(), audio);
	}
	
	

	public void removerFavorito(ObjectId objectId, Consumer<String> llenarFavoritos){
		audioDao.removerFavorito(AppSession.getUser().getId().toString(), objectId);
		llenarFavoritos.accept(null);
	}
	
	
	public List<Audio> consultarHistoricos(String idUsuario){
		return historialDao.consultarHistorico(idUsuario);
	}
	
	public void agregarHistorial(Audio audio){
		historialDao.agregarHistorico(AppSession.getUser().getId().toString(), audio);
	}
	
	

	public void removerHistorial(ObjectId objectId, Consumer<String> llenarHistoricos){
		historialDao.removeOneHistorico(AppSession.getUser().getId().toString(), objectId);
		llenarHistoricos.accept(null);
	}
	
	public void eliminarAudio(Audio audio){
		audioDao.eliminarAudio(audio);
	}
	
	
	public Audio audiosToEdit(String id){
		
		return audioDao.audiosToEdit(id);
	}
	
	public void actualizarAudio(Audio audio){
		
		ArrayList<DBRef> listasDifusion = new ArrayList<>();
		
		audio.getListasDifusion().forEach(item -> {listasDifusion.add(new DBRef("ListaDifusion", item.getId()));});
		
		
		ArrayList<DBRef> categorias = new ArrayList<>();
		
		audio.getCategorias().forEach(item -> {categorias.add(new DBRef("Categorias", item.getId()));});
		
		audioDao.actualizarAudio(audio, listasDifusion, categorias);
	}
}
