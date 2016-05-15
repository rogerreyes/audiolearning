package com.ud.audiolearning.micontenido.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.DBRef;
import com.mongodb.gridfs.GridFSDBFile;
import com.ud.audiolearning.api.anotaciones.AudioLDao;
import com.ud.audiolearning.api.dao.IAudioDao;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.CriterioBusqueda;
import com.ud.audiolearning.api.domain.Favorito;
import com.ud.audiolearning.api.domain.ListaDifusion;
import com.ud.audiolearning.api.domain.Usuario;
import com.ud.audiolearning.api.ui.AppSession;

@AudioLDao
public class AudioDao implements IAudioDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	@Qualifier("audioGFS")
	GridFsTemplate gt;

	@Override
	public void salvarAudio(Audio audio) {
		mongoTemplate.save(audio);
		for (ListaDifusion ld : audio.getListasDifusion()) {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(ld.getId()));
			Update update = new Update();
			update.addToSet("audios").value(audio);
			mongoTemplate.updateFirst(query, update, ListaDifusion.class);
		}
	}

	public List<Audio> consultarAudiosUsuario() {
		Query query = new Query();
		query.fields().include("fechaCreacion");
		query.fields().include("titulo");
		query.fields().include("estado");
		query.fields().include("descripcion");
		query.fields().include("file");
		query.fields().include("imagen");
		query.with(new Sort(Sort.Direction.DESC, "fechaCreacion"));
		query.addCriteria(Criteria.where("usuario.id").is(AppSession.getUser().getId()));
		return mongoTemplate.find(query, Audio.class);
	}

	@Override
	public Audio findOneAudio(String id) {
		return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(id)), Audio.class);
	}

	@Override
	public List<Audio> findAllAudios() {
		Query query = new Query();
		query.fields().include("titulo");
		query.fields().include("imagen");
		query.fields().include("usuario");
		query.addCriteria(Criteria.where("estado").is("A"));
		return mongoTemplate.find(query, Audio.class);
	}

	@Override
	public List<Audio> findAudiosByCategoria(ObjectId objectId) {

		Query query = new Query();
		query.fields().include("titulo");
		query.fields().include("imagen");
		query.fields().include("usuario");
		query.fields().include("file");
	
		query.addCriteria(Criteria.where("categorias.id").all(objectId));
		query.with(new Sort(Sort.Direction.DESC, "fechaCreacion"));
		query.limit(10);
		return mongoTemplate.find(query, Audio.class);
	}

	@Override
	public List<Audio> audiosFavoritos(String idUsuario) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usuario").is(idUsuario));
		query.fields().include("audios");
		Favorito favoritos = mongoTemplate.findOne(query, Favorito.class);
		if(favoritos==null){
			return new ArrayList<>();
		}
		
		
		return favoritos.getAudios();
	}

	@Override
	public void agregarFavorito(String idUsuario, Audio audio) {

		Query query = new Query();
		query.addCriteria(Criteria.where("usuario").is(idUsuario));
		Update update = new Update();
		update.addToSet("audios").value(audio);
		mongoTemplate.upsert(query, update, Favorito.class);
		

	}

	@Override
	public void removerFavorito(String idUsuario, ObjectId objectId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("usuario").is(idUsuario));
		Update update = new Update();
		update.pull("audios", objectId);
		mongoTemplate.updateFirst(query, update, Favorito.class);
	}

	public void eliminarAudio(Audio audio) {
		gt.delete(new Query().addCriteria(GridFsCriteria.where("_id").is(new ObjectId(audio.getFile()))));
		Update updateListas = new Update();
		updateListas.pull("audios", audio);
		mongoTemplate.updateMulti((new Query(Criteria.where("audios").in(audio))), updateListas, ListaDifusion.class);
		mongoTemplate.remove(audio);
	}

	public Audio audiosToEdit(String id) {
		Query query = new Query();
		query.fields().exclude("usuario");
		query.fields().exclude("file");
		query.fields().exclude("fileMimeType");
		query.addCriteria(Criteria.where("id").is(id).and("usuario.id").is(AppSession.getUser().getId()));
		return mongoTemplate.findOne(query, Audio.class);
	}

	public void actualizarAudio(Audio audio, ArrayList<DBRef> listasDifusion, ArrayList<DBRef> categorias) {
		Update update = new Update();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(audio.getId()));
		update.set("titulo", audio.getTitulo());
		update.set("descripcion", audio.getDescripcion());
		update.set("estado", audio.getEstado());
		update.set("genero", audio.getGenero());
		update.set("idioma", audio.getIdioma());
		update.set("listasDifusion", listasDifusion);
		update.set("categorias", categorias);
		update.set("imagen", audio.getImagen());

		mongoTemplate.updateFirst(query, update, Audio.class);

	}

	@Override
	public GridFSDBFile findAudioFile(String id) {
		return gt.findOne(new Query().addCriteria(Criteria.where("_id").is(id)));
	}

	@Override
	public List<Audio> searchAudio(int pagina, int tamañoPagina, String texto) {
		TextCriteria criteria = TextCriteria.forLanguage("spanish").matchingAny(texto);
		Query query = TextQuery.queryText(criteria).sortByScore().with(new PageRequest(pagina, tamañoPagina));
		return mongoTemplate.find(query, Audio.class);

	}

	@Override
	public List<Audio> busquedaAvanzada(CriterioBusqueda criterioBusqueda, int skip, int limit) {

		Criteria CRITERIA = new Criteria();
		List<Criteria> docCriterias = new ArrayList<Criteria>();
		if (criterioBusqueda.getIdioma() != null) {
			docCriterias.add(Criteria.where("idioma").is(criterioBusqueda.getIdioma()));
		}
		if (criterioBusqueda.getUsuario() != null) {
			Query queryUsuarios = new Query()
					.addCriteria(Criteria.where("nombres").regex(criterioBusqueda.getUsuario()));
			queryUsuarios.fields().include("id");
			List<Usuario> usuarios = mongoTemplate.find(queryUsuarios, Usuario.class);
			docCriterias.add(Criteria.where("usuario.id").in(usuarios));
		}
		
		if(criterioBusqueda.getCategorias()!=null && criterioBusqueda.getCategorias().size()>0){
			docCriterias.add(Criteria.where("categorias.id").in(criterioBusqueda.getCategorias()));
		}
		
		if(criterioBusqueda.getGeneros()!=null && criterioBusqueda.getGeneros().size()>0){
			docCriterias.add(Criteria.where("genero").in(criterioBusqueda.getGeneros()));
		}

		if(criterioBusqueda.getFechaInicial()!=null && criterioBusqueda.getFechaInicial()!=null){
			docCriterias.add(Criteria.where("fechaCreacion").gt(criterioBusqueda.getFechaInicial()).lt(criterioBusqueda.getFechafinal()));
		}
		

		if (docCriterias.size() > 0) {
			CRITERIA.andOperator(docCriterias.toArray(new Criteria[docCriterias.size()]));
		}
		
		

		Query query = new Query();
		query.addCriteria(CRITERIA);
		query.limit(limit);
		query.skip(skip);

		return mongoTemplate.find(query, Audio.class);

	}

}
