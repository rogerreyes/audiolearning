package com.ud.audiolearning.seguridad.listener;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.seguridad.ui.PaginaError;
import com.vaadin.server.VaadinService;

import com.vaadin.ui.UI;

import ru.xpoft.vaadin.DiscoveryNavigator;

@SuppressWarnings("serial")
@UIComponent
public class UIChangeListener implements AudioLearnUI.UIChangeListener, Serializable{

	
	@Autowired
	private AudioLearningViewChangeListener audioLearningViewChangeListener;
	
	@Autowired
	private PaginaError paginaError;
	
	@Override
	public void afterSwitchToLoginUI() {
		if(AppSession.getUser() != null) {
			AudioLearnUI audiolearnUI = (AudioLearnUI) UI.getCurrent();
			audiolearnUI.getPage().setLocation("");
			VaadinService.getCurrentRequest().getWrappedSession().invalidate();
		}
		
	}

	

	@Override
	public void afterSwitchToPrivateUI() {
		
		AudioLearnUI audioLearning = (AudioLearnUI) UI.getCurrent();
		DiscoveryNavigator navigator = new DiscoveryNavigator((UI)audioLearning, audioLearning.getPrivateUI().getAreaContenido());
		navigator.addViewChangeListener(audioLearningViewChangeListener );
		navigator.setErrorView(paginaError);
		audioLearning.setNavigator(navigator);
		audioLearning.getPage().setUriFragment("inicio");
	}
	

	
	
}
