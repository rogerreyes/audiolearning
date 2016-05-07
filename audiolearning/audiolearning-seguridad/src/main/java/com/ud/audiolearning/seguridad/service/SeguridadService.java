package com.ud.audiolearning.seguridad.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.domain.Usuario;
import com.ud.audiolearning.seguridad.dao.SeguridadDao;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@AudioLService
public class SeguridadService implements Serializable {

	@Autowired
	SeguridadDao seguridadDao;

	private Set<String> views = new HashSet<String>();

	public Usuario BuscarUsuario(String Usuario, String Contraseña) {
		return seguridadDao.findUsuario(Usuario, Contraseña);
	}

	public boolean canAccess(String view) {
		boolean canAccess = views.contains(view);
		if (!canAccess) {
			UI.getCurrent().getNavigator().navigateTo("error");
		}
		return canAccess;
	}

	public Set<String> getViews() {
		return views;
	}

}
