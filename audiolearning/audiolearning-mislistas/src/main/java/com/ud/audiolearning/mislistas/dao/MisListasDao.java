package com.ud.audiolearning.mislistas.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ud.audiolearning.api.anotaciones.AudioLDao;
import com.ud.audiolearning.api.domain.ListaDifusion;
import com.ud.audiolearning.api.ui.AppSession;

@AudioLDao
public class MisListasDao {
	
	@Autowired
	MongoTemplate mongoTemplate;

	public void saveListaPublicacion(ListaDifusion lista) {
		mongoTemplate.save(lista);
	}

	
	public List<ListaDifusion> BuscarListasPublicacion() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "fechaCreacion"));
		query.addCriteria(Criteria.where("usuario.id").is(AppSession.getUser().getId()));
		List<ListaDifusion> lista = new ArrayList<>();
		lista.addAll(mongoTemplate.find(query, ListaDifusion.class));
		return lista;
		
	}
	
	public void eliminarListaDifusion(ListaDifusion lista){
		mongoTemplate.remove(lista);
	}
	
	public void editarListaDifusion(ListaDifusion lista){
		
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(lista.getId()));
		Update update =  new Update();
		update.set("titulo", lista.getTitulo());
		update.set("descripcion", lista.getDescripcion());
		update.set("estado", lista.getEstado());
		mongoTemplate.updateFirst(query, update , ListaDifusion.class);
		
	}
}
