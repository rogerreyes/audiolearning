package com.ud.audiolearning.api.dao;


import org.bson.types.ObjectId;

import com.ud.audiolearning.api.domain.Audio;



public interface IHistoricoDao {


	void agregarHistorico(String idUsuario, Audio audio);

	void removeOneHistorico(String idUsuario, ObjectId objectId);

}
