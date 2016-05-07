package com.ud.audiolearning.busquedas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.Favorito;

@AudioLService
public class FavoritosDao {

	@Autowired
	MongoTemplate mongoTemplate;

	
}
