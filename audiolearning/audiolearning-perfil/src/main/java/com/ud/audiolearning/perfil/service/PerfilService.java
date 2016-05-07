package com.ud.audiolearning.perfil.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DBRef;
import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.domain.Usuario;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.perfil.dao.PerfilDao;

@AudioLService
public class PerfilService {

	@Autowired
	PerfilDao perfilDao;

	public Usuario buscarUsuario() {

		return perfilDao.buscarDatosUsuario(AppSession.getUser().getId());
	}

	public void actualizarUsuario(Usuario usuario) {

		ArrayList<DBRef> categorias = new ArrayList<>();

		usuario.getCategorias().forEach(item -> {
			categorias.add(new DBRef("Categorias", item.getId()));
		});

		perfilDao.actualizarUsuario(usuario, categorias);
	}

}
