package com.ud.audiolearning.busquedas.service;





import java.util.List;
import java.util.function.Consumer;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.mongodb.gridfs.GridFSDBFile;
import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.dao.IAudioDao;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.CriterioBusqueda;
import com.ud.audiolearning.api.ui.AppSession;

@AudioLService
public class BusquedasService implements com.ud.audiolearning.api.service.IBusquedasService {

	
	@Autowired
	IAudioDao audioDao;
	
	
	


	public List<Audio>BuscarAudios (){
		return audioDao.findAllAudios();
	}
	
	
	public List<Audio> findAudiosByCategoria(ObjectId objectId){
		
		return audioDao.findAudiosByCategoria(objectId);
	}
	
	
	public List<Audio> consultarFavoritos(String idUsuario){
		return audioDao.audiosFavoritos(idUsuario);
	}
	
	@Override
	public void agregarFavorito(Audio audio){
		audioDao.agregarFavorito(AppSession.getUser().getId().toString(), audio);
	}
	
	
	@Override
	public void removerFavorito(ObjectId objectId, Consumer<String> llenarFavoritos){
		audioDao.removerFavorito(AppSession.getUser().getId().toString(), objectId);
		llenarFavoritos.accept(null);
	}

	@Override
	public GridFSDBFile buscarAudioFile(String idAudio) {
		return  audioDao.findAudioFile(idAudio);
		
	}
	

	@Override
	public List<Audio> busquedaBasica(int pagina, int tamañoPagina, String texto) {
		return audioDao.searchAudio(pagina, tamañoPagina, texto);
	}
	
	
	@Override
	public List<Audio> busquedaAvanzada(CriterioBusqueda criterioBusqueda, int skip, int limit){

		return audioDao.busquedaAvanzada(criterioBusqueda, skip, limit);
				
	}
	
	
}
