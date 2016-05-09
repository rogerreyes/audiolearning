package com.ud.audiolearning.administracion.dao;

import java.util.HashMap;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.ud.audiolearning.api.anotaciones.AudioLDao;
import com.ud.audiolearning.api.dao.IDenunciasDao;
import com.ud.audiolearning.api.domain.Denuncia;
import com.ud.audiolearning.api.domain.ReporteDenuncia;
import com.ud.audiolearning.api.domain.Rol;
import com.ud.audiolearning.api.domain.Usuario;


@AudioLDao
public class AdministracionDao implements IDenunciasDao {

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Denuncia> denunciasSinAtender() {

		Query query = new Query();
		query.addCriteria(Criteria.where("estado").is("A"));
		query.fields().include("fechaApertura");
		query.fields().include("audio.titulo");
		query.fields().include("audio.id");
		query.fields().include("numeroDenuncias");
		query.fields().include("estado");
		return mongoTemplate.find(query, Denuncia.class);
	}

	public List<Denuncia> denunciasProcesadas() {

		Query query = new Query();
		query.addCriteria(Criteria.where("estado").is("C"));
		return mongoTemplate.find(query, Denuncia.class);
	}
	
	@Override
	public void insertarDenuncia(Denuncia denuncia) {
		mongoTemplate.save(denuncia);
	}

	@Override
	public Denuncia existeDenuncia(ObjectId idAudio) {

		Query query = new Query();
		query.addCriteria(Criteria.where("audio.id").is(idAudio).and("estado").is("A"));

		return mongoTemplate.findOne(query, Denuncia.class);
	}

	@Override
	public void agregarReporteDenuncia(ObjectId idAudio, ReporteDenuncia reporteDenuncia) {
		Query query = new Query();
		query.addCriteria(Criteria.where("audio.id").is(idAudio).and("estado").is("A"));
		Update update = new Update();
		update.addToSet("reportesDenuncia").value(reporteDenuncia);
		update.inc("numeroDenuncias", 1);
		mongoTemplate.updateFirst(query, update, Denuncia.class);
	}

	public Denuncia buscarDenuncia(ObjectId idDenuncia) {

		return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(idDenuncia).and("estado").is("A")),
				Denuncia.class);
	}
	
	public void actualizarDenunciaCierre (Denuncia denuncia){
		Update update = new Update();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(denuncia.getId()));
		update.set("estado", denuncia.getEstado());
		update.set("detalleCierre", denuncia.getDetalleCierre());
		update.set("fechaCierre", denuncia.getFechaCierre());
		update.set("usuarioAtencion", denuncia.getUsuarioAtencion());
		
		mongoTemplate.updateFirst(query, update, Denuncia.class);
	
	}
	
	public void insertarUsuario(Usuario usuario){
		mongoTemplate.save(usuario);
	}
	
	public void modificarUsuario(Usuario usuario){
		Update update = new Update();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(usuario.getId()));
		update.set("nombres", usuario.getNombres());
		update.set("apellidos", usuario.getApellidos());
		update.set("nick", usuario.getNick());
		update.set("email", usuario.getEmail());
		update.set("genero", usuario.getGenero());
		update.set("nacionalidad", usuario.getNacionalidad());
		update.set("descripcion", usuario.getDescripcion());
		update.set("facebook", usuario.getFacebook());
		update.set("twitter", usuario.getTwitter());
		update.set("fechaRegistro", usuario.getFechaRegistro());
		update.set("contraseña", usuario.getContraseña());
		update.set("blog", usuario.getBlog());
		update.set("categorias", usuario.getCategorias());
		update.set("rol", usuario.getRol());		
		mongoTemplate.updateFirst(query, update, Usuario.class);
	}
	
	public void eliminarUsuario(Usuario usuario){
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(usuario.getId()));		
		mongoTemplate.findAndRemove(query, Usuario.class);
	}
	
	public List<Usuario> listadoUsuarios(HashMap<String,String> Parametros){
		if(Parametros != null){			
			Criteria criteriaV1 = Criteria.where("nombres").regex(Parametros.get("nombres"));
			Criteria criteriaV2 = Criteria.where("apellidos").regex(Parametros.get("apellidos"));
			Criteria criteriaV3 = Criteria.where("nacionalidad").regex(Parametros.get("nacionalidad"));
			Criteria criteriaV4 = Criteria.where("nick").regex(Parametros.get("nick"));
			Criteria criteriaV5 = Criteria.where("email").regex(Parametros.get("email"));
			Criteria criteriaV6 = Criteria.where("genero").regex(Parametros.get("genero"));
			Query query = new Query(new Criteria().andOperator(criteriaV1,criteriaV2,criteriaV3,criteriaV4,criteriaV5,criteriaV6));	 
			return mongoTemplate.find(query,Usuario.class);
		}else{
			return mongoTemplate.findAll(Usuario.class);
		}
				
	}
	
	public Rol consultaROL(String id){
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));		
		return  mongoTemplate.findOne(query, Rol.class);
	}
	
	public List<Rol> listadoRol(){
		return mongoTemplate.findAll(Rol.class);
	}
	
}
