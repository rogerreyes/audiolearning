package com.ud.audiolearning.busquedas.ui;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.virkki.carousel.HorizontalCarousel;

import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.CriterioBusqueda;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.api.ui.AudioCard;
import com.ud.audiolearning.busquedas.service.BusquedasService;
import com.vaadin.annotations.AutoGenerated;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@UIComponent
@VaadinView("inicio")
public class Inicio extends CustomComponent implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private VerticalLayout vl_contenido;
	@Autowired
	BusquedasService busquedasService;
//	private BeanItemContainer<Audio> BICFavoritos;

	public Inicio() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
		AudioLearnUI audioLearning = (AudioLearnUI) UI.getCurrent();
		audioLearning.getPrivateUI().getAreaContenido().setHeight("-1px");
		buildMainLayout();
		setCompositionRoot(mainLayout);
		init();
		style();
	}

	public void init() {

		

		

		AppSession.getUser().getCategorias().forEach(categoria -> {

			HorizontalCarousel carousel = new HorizontalCarousel();
			carousel.setMouseDragEnabled(false);
			carousel.setMouseWheelEnabled(false);
			carousel.setWidth("100%");
			carousel.setHeight("300px");
			carousel.addStyleName("carousel");

			List<Audio> a = busquedasService.findAudiosByCategoria(categoria.getId());

			if (a.size() <= 5) {
				HorizontalLayout hl = new HorizontalLayout();
				hl.setSpacing(true);
				hl.setWidth("100%");
				hl.setMargin(new MarginInfo(false, true, false, true));
				a.forEach(audio -> {
					hl.addComponent(new AudioCard(audio, busquedasService, false));
				});
				int restantes = 5 - a.size();
				for (int i = 0; i < restantes; i++) {
					hl.addComponent(new AudioCard(null, busquedasService, false));
				}
				carousel.addComponent(hl);
			} else {
				HorizontalLayout hl = new HorizontalLayout();
				hl.setSpacing(true);
				hl.setWidth("100%");
				hl.setMargin(new MarginInfo(false, true, false, true));
				a.subList(0, 5).forEach(audio -> {
					hl.addComponent(new AudioCard(audio, busquedasService, false));
				});
				HorizontalLayout hl2 = new HorizontalLayout();
				hl2.setSpacing(true);
				hl2.setWidth("100%");
				hl2.setMargin(new MarginInfo(false, true, false, true));
				a.subList(5, a.size()).forEach(audio -> {
					hl2.addComponent(new AudioCard(audio, busquedasService, false));
				});
				carousel.addComponents(hl, hl2);

				int restantes = 10 - a.size();

				for (int i = 0; i < restantes; i++) {
					hl2.addComponent(new AudioCard(null, busquedasService, false));
				}

			}

			Label tituloCategoria = new Label(categoria.getNombre().toUpperCase());
			tituloCategoria.addStyleName("tituloCategoria");

			vl_contenido.addComponent(tituloCategoria);
			vl_contenido.addComponent(carousel);
			Button b_verMas = new Button("ver mas de "+categoria.getNombre());
			b_verMas.addStyleName("buttonVerMas");
			b_verMas.addClickListener(e-> buscarPorCategorias(categoria.getId()));
			b_verMas.setIcon(VaadinIcons.HEADPHONES);
			
			vl_contenido.addComponent(b_verMas);
			vl_contenido.setComponentAlignment(b_verMas, Alignment.BOTTOM_RIGHT);
			

		});

	}


	


	private void buscarPorCategorias(ObjectId objectId) {
		
		CriterioBusqueda criterioBusqueda = new CriterioBusqueda();
		criterioBusqueda.setTipo("A");
		List<ObjectId> categorias = new ArrayList<ObjectId>();
		categorias.add(objectId);
		criterioBusqueda.setCategorias(categorias);
		AppSession.setCriterioBusqueda(criterioBusqueda);
		
		UI.getCurrent().getNavigator().navigateTo("Busqueda");
		
	}

	

	
	private void style() {

	
		vl_contenido.setMargin(new MarginInfo(true, false, true, false));
		
		mainLayout.setMargin(new MarginInfo(true, false, true, false));
		
		
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// vl_contenido
		vl_contenido = new VerticalLayout();
		vl_contenido.setImmediate(false);
		vl_contenido.setWidth("100.0%");
		vl_contenido.setHeight("-1px");
		vl_contenido.setMargin(false);
		mainLayout.addComponent(vl_contenido);
		
		return mainLayout;
	}

}
