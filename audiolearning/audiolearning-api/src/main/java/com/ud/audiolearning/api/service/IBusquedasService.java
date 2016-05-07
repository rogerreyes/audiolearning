package com.ud.audiolearning.api.service;



import java.util.List;
import java.util.function.Consumer;

import org.bson.types.ObjectId;

import com.mongodb.gridfs.GridFSDBFile;
import com.ud.audiolearning.api.domain.Audio;


public interface IBusquedasService {

	void agregarFavorito(Audio audio);

	void removerFavorito(ObjectId objectId, Consumer<String> llenarFavoritos);
	
	public GridFSDBFile buscarAudioFile(String idAudio);
	
	public 	List<Audio> busquedaGeneralAudio(int pageInicio, int pageFin, String texto);

}
