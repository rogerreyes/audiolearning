package com.ud.audiolearning.seguridad.listener;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.ud.audiolearning.api.interfaces.LoginUILayout;
import com.ud.audiolearning.api.domain.Usuario;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.seguridad.service.SeguridadService;
import com.ud.audiolearning.seguridad.ui.MenuApp;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@UIComponent
public class LoginListener implements LoginUILayout.AudioLearningLoginListener, Serializable {

	@Autowired
	SeguridadService seguridadService;
	
	@Autowired
	MenuApp menuApp;

	public boolean login(String Usuario, String Contraseña) {
		
		Usuario u =seguridadService.BuscarUsuario(Usuario, Contraseña); 
		if (!(u== null)) {
			AppSession.setUser(u);
			actualizarNombreMenuUsuario(u.getNombres());
			menuApp.construirMenu(u.getRol());
			return true;
		}
		return false;
	}

	private void actualizarNombreMenuUsuario(String usuarioSession) {
		AudioLearnUI audioLearnUI = (AudioLearnUI) UI.getCurrent();
		audioLearnUI.getPrivateUI().setNombreUsuarioSession(usuarioSession);
	}

}
