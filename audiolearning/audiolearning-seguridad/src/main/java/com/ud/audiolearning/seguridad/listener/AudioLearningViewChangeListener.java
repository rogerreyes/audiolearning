package com.ud.audiolearning.seguridad.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.seguridad.service.SeguridadService;
import com.vaadin.navigator.ViewChangeListener;


@SuppressWarnings("serial")
@UIComponent
public class AudioLearningViewChangeListener implements ViewChangeListener {

	@Autowired
	SeguridadService seguridadService;
	
	

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		return seguridadService.canAccess(event.getViewName());
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
