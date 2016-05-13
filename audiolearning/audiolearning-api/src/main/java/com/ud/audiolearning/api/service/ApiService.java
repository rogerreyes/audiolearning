package com.ud.audiolearning.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.dao.ApiDao;
import com.ud.audiolearning.api.domain.Categoria;
import com.ud.audiolearning.api.domain.ListaDifusion;
import com.ud.audiolearning.api.domain.Parametrico;

@AudioLService
public class ApiService {

	@Autowired
	ApiDao apiDao;

	public List<Parametrico> selectItemsIdiomas() {
		return apiDao.findAllIdioma();
	}

	public List<Parametrico> selectItemsGeneros() {
		return apiDao.findAllGenero();
	}

	public List<Categoria> findAllCategorias() {
		return apiDao.findAllCategorias();
	}

	public List<ListaDifusion> findAllListasDifusion() {
		return apiDao.findAllListasDifusion();
	}

	public List<Parametrico> findAllTiposDenuncia() {

		return apiDao.findAllTiposDenuncia();
	}

	public List<Parametrico> findAllPaises() {
		return apiDao.findAllPais();
	}

	public void editarParametrico(Parametrico parametrico) {
		apiDao.editarParametrico(parametrico);
	}

	public void crearParametrico(Parametrico parametrico) {
		apiDao.crearParametrico(parametrico);
	}

	public void removerParametrico(Parametrico parametrico) {
		apiDao.removerParametrico(parametrico);
	}

}
