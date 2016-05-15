package com.ud.audiolearning.busquedas.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;


import com.ud.audiolearning.api.anotaciones.AudioLService;

@AudioLService
public class FavoritosDao {

	@Autowired
	MongoTemplate mongoTemplate;

	
}
