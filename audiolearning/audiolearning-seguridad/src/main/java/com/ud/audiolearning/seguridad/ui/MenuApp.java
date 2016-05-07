package com.ud.audiolearning.seguridad.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.ud.audiolearning.api.interfaces.PrivateUILayout;
import com.ud.audiolearning.api.domain.ALItem;
import com.ud.audiolearning.api.domain.ALMenuItem;
import com.ud.audiolearning.api.domain.Rol;
import com.ud.audiolearning.seguridad.service.SeguridadService;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@UIComponent
@Scope("session")
public class MenuApp implements Serializable {

	@Autowired
	SeguridadService seguridadService;
	private Map<String, String> urls = new HashMap<>();
	private Set<String> views;

	public void construirMenu(Rol rol) {
		views = seguridadService.getViews();
		views.clear();
		views.add("");
		views.add("error");
		
		if (rol.getCommonUrl()!=null) {
			views.addAll(rol.getCommonUrl());
		}
		urls.clear();
		for (ALMenuItem menuItem : rol.getMenuItems()) {
			addOpcion(menuItem);
		}
	}

	private void addOpcion(ALMenuItem menuItem) {
		AudioLearnUI AudioLearningUI = (AudioLearnUI) UI.getCurrent();
		PrivateUILayout privateUI = AudioLearningUI.getPrivateUI();

		privateUI.addMenuItem(menuItem);

		if (menuItem.isNavegable()) {
			urls.put(menuItem.getNombre(), menuItem.getUrl());
			views.add(menuItem.getUrl());
		}

		for (ALItem item : menuItem.getItems()) {
			if (item.isNavegable()) {
				urls.put(item.getNombre(), item.getUrl());
				views.add(item.getUrl());
			}
		}

	}

	public String getUrl(String optionID) {
		return urls.get(optionID);
	}

}
