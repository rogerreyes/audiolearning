package com.ud.audiolearning.micontenido.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ud.audiolearning.api.anotaciones.AudioLDao;
import com.ud.audiolearning.api.dao.IHistoricoDao;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.HistorialReproduccion;

@AudioLDao
public class HistorialDao implements IHistoricoDao{

	@Autowired
	MongoTemplate mongoTemplate;

	
	public List<Audio> consultarHistorico(String idUsuario) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usuario").is(idUsuario));
		query.fields().include("audios");
		HistorialReproduccion historicos = mongoTemplate.findOne(query, HistorialReproduccion.class);
		if(historicos == null){
			return new ArrayList<>();
		}
		return historicos.getAudios();
	}

	@Override
	public void agregarHistorico(String idUsuario, Audio audio) {

		Query query = new Query();
		query.addCriteria(Criteria.where("usuario").is(idUsuario));
		Update update = new Update();
		update.addToSet("audios").value(audio);
		mongoTemplate.updateFirst(query, update, HistorialReproduccion.class);

	}

	@Override
	public void removeOneHistorico(String idUsuario, ObjectId objectId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usuario").is(idUsuario));
		Update update = new Update();
		update.pull("audios", objectId);
		mongoTemplate.updateFirst(query, update, HistorialReproduccion.class);
	}

	public void removeAllHistorico(String idUsuario) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usuario").is(idUsuario));
		Update update = new Update();
		update.set("audios", null);
		mongoTemplate.updateFirst(query, update, HistorialReproduccion.class);
	}

}
