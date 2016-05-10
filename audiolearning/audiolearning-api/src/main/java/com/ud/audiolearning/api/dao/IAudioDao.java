package com.ud.audiolearning.api.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.gridfs.GridFSDBFile;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.CriterioBusqueda;

public interface IAudioDao {

	public Audio findOneAudio(String id);

	public void salvarAudio(Audio audio);

	public List<Audio> findAllAudios();

	public List<Audio> findAudiosByCategoria(ObjectId objectId);

	public List<Audio> audiosFavoritos(String idUsuario);

	public void agregarFavorito(String idUsuario, Audio audio);

	public void removerFavorito(String string, ObjectId objectId);

	public GridFSDBFile findAudioFile(String id);

	public List<Audio> searchAudio(int pagina, int tamañoPagina, String texto);

	public List<Audio> busquedaAvanzada(CriterioBusqueda criterioBusqueda, int skip, int limit);

}
