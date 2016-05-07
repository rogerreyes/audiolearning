package com.ud.audiolearning.mislistas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.domain.ListaDifusion;
import com.ud.audiolearning.mislistas.dao.MisListasDao;

@AudioLService
public class MisListasService {
	
	@Autowired
	MisListasDao misListasDao;
	
	public boolean guardarListaDifusion(ListaDifusion lista) {
		try {
			misListasDao.saveListaPublicacion(lista);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<ListaDifusion> findAllListasDifusion() {
		return misListasDao.BuscarListasPublicacion();
	}

	public void eliminarListaDifusion(ListaDifusion lista) {
		misListasDao.eliminarListaDifusion(lista);
	}

	public void editarListaDifusion(ListaDifusion lista) {
		misListasDao.editarListaDifusion(lista);
	}


}
