package com.ud.webapp.config;


import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@UIComponent
public class LayoutBase extends VerticalLayout {

	private CssLayout areaContenido = new CssLayout();
	private CssLayout areaMenu = new CssLayout();

	public LayoutBase() {
		setSizeFull();
		VerticalLayout barraBusqueda = new VerticalLayout();
		barraBusqueda.addComponent(new TextField());
		areaMenu.addComponent(new Label("Menu"));
		areaContenido.addComponent(new Label("Contenido"));
		
		addComponents(barraBusqueda, areaMenu, areaContenido);
	}

	public CssLayout getAreaContenido() {
		return areaContenido;
	}

	
	public void addMenu(HorizontalLayout c){
		
		areaMenu.addComponent(c);
		
	}
}
