package com.ud.audiolearning.api.service;



import java.util.List;
import java.util.function.Consumer;

import org.bson.types.ObjectId;

import com.mongodb.gridfs.GridFSDBFile;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.CriterioBusqueda;


public interface IBusquedasService {

	void agregarFavorito(Audio audio);

	void removerFavorito(ObjectId objectId, Consumer<String> llenarFavoritos);
	
	public GridFSDBFile buscarAudioFile(String idAudio);
	

	public List<Audio> busquedaBasica(int pageInicio, int pageFin, String texto);

	public List<Audio> busquedaAvanzada(CriterioBusqueda criterioBusqueda, int skip, int limit);

}
