package com.ud.audiolearning.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.ud.audiolearning.api.anotaciones.AudioLDao;
import com.ud.audiolearning.api.domain.Categoria;
import com.ud.audiolearning.api.domain.ListaDifusion;
import com.ud.audiolearning.api.domain.Parametrico;
import com.ud.audiolearning.api.ui.AppSession;



@AudioLDao
public class ApiDao {
	
	
	@Autowired 
	MongoTemplate mongoTemplate;
	
	
	public List<Parametrico> findAllIdioma(){
		return mongoTemplate.find(new Query().addCriteria(Criteria.where("tipo").is("IDIOMA")), Parametrico.class);
	}
	
	
	public List<Parametrico> findAllGenero(){
		return mongoTemplate.find(new Query().addCriteria(Criteria.where("tipo").is("GENERO")), Parametrico.class);
	}
	
	
	public List<Categoria> findAllCategorias(){
		return mongoTemplate.findAll(Categoria.class);
	}

	public List<ListaDifusion> findAllListasDifusion(){
		return mongoTemplate.find(new Query().addCriteria(Criteria.where("usuario.id").is(AppSession.getUser().getId()).andOperator(Criteria.where("estado").is("A"))), ListaDifusion.class);
	}
	
	public List<Parametrico> findAllTiposDenuncia(){
		return mongoTemplate.find(new Query().addCriteria(Criteria.where("tipo").is("TIPODENUNCIA")), Parametrico.class);
	}

}
