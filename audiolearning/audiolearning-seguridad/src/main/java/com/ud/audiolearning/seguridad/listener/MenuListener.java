package com.ud.audiolearning.seguridad.listener;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.seguridad.ui.MenuApp;
import com.vaadin.ui.UI;

@UIComponent
public class MenuListener implements Serializable {

	private static final long serialVersionUID = 5004817563652571827L;
	@Autowired
	MenuApp menuApp;

	public void seleccionItem(String itemName) {
		String url = menuApp.getUrl(itemName);
		if (url != null) {
			UI.getCurrent().getNavigator().navigateTo(url);
		}
	}

}
