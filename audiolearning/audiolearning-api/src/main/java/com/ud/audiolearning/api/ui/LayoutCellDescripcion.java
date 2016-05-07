package com.ud.audiolearning.api.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class LayoutCellDescripcion extends VerticalLayout {

	private Label titulo;
	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");

	public LayoutCellDescripcion(String titulo, String descripcion, Date fechaCreacion, String estado) {
		this.titulo = new Label(titulo);
		this.titulo.addStyleName(ValoTheme.LABEL_COLORED);
		this.titulo.addStyleName(ValoTheme.LABEL_H3);
		this.titulo.addStyleName(ValoTheme.LABEL_BOLD);

		Label fechaCrea = new Label(dateFormat.format(fechaCreacion));

		Label est = new Label();
		if (estado.equals("A")) {
			est.setValue("► Activo");
			est.setStyleName("labelActivo");
		} else if (estado.equals("I")) {
			est.setValue("► Inactivo");
			est.setStyleName("labelInactivo");
		}

		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponents(est, fechaCrea);
		hl.addStyleName("publicacion");
		hl.setSpacing(true);

		addComponent(this.titulo);
		addComponent(hl);
		if (descripcion != null) {
			Label descrip = new Label(descripcion.length() > 250 ? descripcion.substring(0, 247) + "..." : descripcion);
			addComponent(descrip);
		}

		setWidth("100%");
	}

}
