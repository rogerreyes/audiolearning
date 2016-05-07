package com.ud.audiolearning.administracion.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBRef;
import com.ud.audiolearning.api.anotaciones.AudioLDao;
import com.ud.audiolearning.api.dao.IDenunciasDao;
import com.ud.audiolearning.api.domain.Denuncia;
import com.ud.audiolearning.api.domain.ReporteDenuncia;


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
	
	
	
}
