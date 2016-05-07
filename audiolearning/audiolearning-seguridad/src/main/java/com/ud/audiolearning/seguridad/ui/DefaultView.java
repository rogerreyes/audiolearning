package com.ud.audiolearning.seguridad.ui;



import ru.xpoft.vaadin.VaadinView;
import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;


/**
 * 
 * @author alejandro
 *
 */
@UIComponent
@VaadinView("")
@SuppressWarnings("serial")
public class DefaultView extends CustomComponent implements View {
	

	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().getNavigator().navigateTo("inicio");
	}

}
