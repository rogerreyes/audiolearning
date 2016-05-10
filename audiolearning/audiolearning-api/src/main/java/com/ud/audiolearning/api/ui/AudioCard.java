package com.ud.audiolearning.api.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.bson.types.ObjectId;
import org.vaadin.teemu.VaadinIcons;

import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.CriterioBusqueda;
import com.ud.audiolearning.api.service.IBusquedasService;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class AudioCard extends Panel {

	private Button b_play;
	private Button b_descargar;
	private Button b_autor;
	private Button b_favoritos;
	private Label titulo;
	private HorizontalLayout hl_botones;
	private VerticalLayout content;
	private Embedded imagen;
	private VerticalLayout vl;

	public AudioCard(Audio audio, IBusquedasService busquedasService, boolean fix) {

		if(!fix){setWidth("100%");}else{setWidth("230px");}
			
		
		
		setHeight("300px");
		content = new VerticalLayout();
		content.setHeight("100%");

		if (audio != null) {

			this.vl = new VerticalLayout();

			vl.setHeight("100%");
			vl.setWidth("100%");
			this.hl_botones = new HorizontalLayout();
			this.titulo = new Label();
			this.imagen = new Embedded();
			this.b_play = new Button();
			this.b_descargar = new Button();
			this.b_favoritos = new Button();
			this.b_autor = new Button("Autor: " + audio.getUsuario().getNombres());

			String titulo = audio.getTitulo();
			if (titulo != null) {
				this.titulo.setValue(titulo.length() > 50 ? titulo.substring(0, 50) + "..." : titulo);
			}
			this.titulo.setStyleName("labelTituloCard");

			hl_botones.addComponents(b_play, b_descargar, b_favoritos);
			hl_botones.setSpacing(true);
			hl_botones.addStyleName("botonesMultimedia");

			style();

			b_play.addClickListener(e -> medotoPlay(audio.getId()));
			b_descargar.addClickListener(e -> medotoDescargar(audio.getFile(), busquedasService));
			b_autor.addClickListener(e -> metodoIrAutor(audio.getUsuario().getNombres(),busquedasService));
			b_favoritos.addClickListener(e -> metodoAgregarFavorito(audio, busquedasService));

			if (audio.getImagen().getBinaryData() != null) {
				this.imagen.setType(1);
				this.imagen.setSource(new StreamResource(new StreamSource() {
					@Override
					public InputStream getStream() {
						return new ByteArrayInputStream(audio.getImagen().getBinaryData().getData());
					}
				}, audio.getImagen().getNombre()));
			} else {

				this.imagen.setSource(new ThemeResource("img/component/embedded_icon.png"));
			}

			this.imagen.setHeight("180px");
			this.imagen.setWidth("100%");

			vl.addComponents(this.imagen, this.titulo, this.b_autor, hl_botones);
			vl.setExpandRatio(this.titulo, 1.0f);

			vl.addStyleName("descripcionAudioCard");

			content.addComponents(vl);
			vl.setComponentAlignment(this.b_autor, Alignment.MIDDLE_RIGHT);
			vl.setComponentAlignment(hl_botones, Alignment.BOTTOM_CENTER);
		} else {
			addStyleName(ValoTheme.PANEL_BORDERLESS);
		}

		setContent(content);
	}

	private void metodoAgregarFavorito(Audio audio, IBusquedasService busquedasService) {
		try {

			busquedasService.agregarFavorito(audio);
			Notification n = new Notification("Favoritos!",
					"Se ha añadio el audio" + audio.getTitulo() + " a tu lista de favoritos.", Type.HUMANIZED_MESSAGE);
			n.setDelayMsec(3600);
			n.show(UI.getCurrent().getPage());

		} catch (Exception e) {
			Notification.show("Error!", "No se pudo agregar el audio a Favoritos", Type.ERROR_MESSAGE);
		}
	}

	private void metodoIrAutor(String autor, IBusquedasService busquedasService) {
		CriterioBusqueda criterioBusqueda = new CriterioBusqueda();
		criterioBusqueda.setTipo("A");
		criterioBusqueda.setUsuario(autor);
		
		AppSession.setCriterioBusqueda(criterioBusqueda);
		UI.getCurrent().getNavigator().navigateTo("Busqueda");
		
	}

	private void medotoDescargar(String idFile, IBusquedasService busquedasService) {

		Window w = new Window("Descargar Audio");
		w.setWidth("-1px");
		w.setHeight("-1px");
		w.center();
		w.setModal(true);
		w.setContent(new windowDescargar(idFile, busquedasService));
		UI.getCurrent().addWindow(w);

	}

	private void style() {
		addStyleName("panelCard");
		b_play.setIcon(FontAwesome.PLAY);
		b_play.addStyleName("buttonPlay");
		b_descargar.setIcon(VaadinIcons.CLOUD_DOWNLOAD_O);
		b_autor.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		b_autor.addStyleName(ValoTheme.BUTTON_SMALL);
		b_favoritos.setIcon(VaadinIcons.HEART);

	}

	private void medotoPlay(ObjectId objectId) {
		getUI().getNavigator().navigateTo("reproductor/" + objectId);
	}

}
