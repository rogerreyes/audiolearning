package com.ud.audiolearning.perfil.dao;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBRef;
import com.ud.audiolearning.api.anotaciones.AudioLDao;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.Usuario;

@AudioLDao
public class PerfilDao {

	
	@Autowired
	MongoTemplate mongoTemplate;

	public Usuario buscarDatosUsuario(ObjectId idUsuario) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(idUsuario));
		query.fields().exclude("rol");
		return mongoTemplate.findOne(query, Usuario.class);
	}
	
	
	public void actualizarUsuario(Usuario usuario, ArrayList<DBRef> categorias){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(usuario.getId()));
		
		Update update = new Update();
		update.set("nombres", usuario.getNombres());
		update.set("apellidos", usuario.getApellidos());
		update.set("contraseña", usuario.getContraseña());
		update.set("genero", usuario.getGenero());
		update.set("email", usuario.getEmail());
		update.set("nacionalidad", usuario.getNacionalidad());
		update.set("blog", usuario.getBlog());
		update.set("facebook", usuario.getFacebook());
		update.set("twitter", usuario.getTwitter());
		update.set("descripcion", usuario.getDescripcion());
		update.set("categorias", categorias);
		
		mongoTemplate.updateFirst(query, update, Usuario.class);
		
		
		
	}
	
}
