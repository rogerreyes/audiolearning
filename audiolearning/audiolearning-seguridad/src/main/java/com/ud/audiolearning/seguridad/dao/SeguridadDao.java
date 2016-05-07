package com.ud.audiolearning.seguridad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ud.audiolearning.api.anotaciones.AudioLDao;
import com.ud.audiolearning.api.domain.Usuario;


@AudioLDao
public class SeguridadDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public Usuario findUsuario(String nombre, String contraseña){
		return mongoTemplate.findOne(new Query(Criteria.where("nick").is(nombre).and("contraseña").is(contraseña)), Usuario.class);
	}

	
}
