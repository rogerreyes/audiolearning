package com.ud.audiolearning.busquedas.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemu.VaadinIcons;

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
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@UIComponent
@VaadinView("Busqueda")
public class ResultadosBusqueda extends CustomComponent implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button b_buscarMas;
	@AutoGenerated
	private VerticalLayout vl_contenedor;
	@AutoGenerated
	private HorizontalLayout hl_cabecera;
	@AutoGenerated
	private Label l_titulo;
	@Autowired
	BusquedasService busquedasService;
	

	private int numeroPagina = 0;
	private static int RESULTADOS_POR_PAGINA = 10;

	private CssLayout contenido;
	private CriterioBusqueda criterio;

	public ResultadosBusqueda() {

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

	private void init() {
		this.criterio = AppSession.getCriterioBusqueda();

		this.contenido = new CssLayout();
		vl_contenedor.addComponent(this.contenido);
		
		System.err.println("CRITERIO : " + criterio);
	
		switch (criterio.getTipo()) {
		case "B":
			busquedaBasica();
			b_buscarMas.addClickListener(e -> busquedaBasica());
			break;

		case "A":
			busquedaAvanzda();
			b_buscarMas.addClickListener(e -> busquedaAvanzda());
			break;
		default:
			
			break;
		}
	

	}

	private void busquedaAvanzda() {
		
		
		List<Audio> audios = busquedasService.busquedaAvanzada(this.criterio, numeroPagina, RESULTADOS_POR_PAGINA);

		
	
		
		
		audios.forEach(audio -> {
			this.contenido.addComponent(new AudioCard(audio, busquedasService, true));
		});
		
		if (audios.size() < RESULTADOS_POR_PAGINA) {
			b_buscarMas.setEnabled(false);
			return;
		} else {
			numeroPagina = numeroPagina+RESULTADOS_POR_PAGINA;
		}
	}

	private void busquedaBasica() {
		String textoBusqueda = criterio.getQuery().trim();
		if (!(textoBusqueda == "")) {
			List<Audio> audios = busquedasService.busquedaBasica(this.numeroPagina, RESULTADOS_POR_PAGINA,
					textoBusqueda);
			audios.forEach(audio -> {
				this.contenido.addComponent(new AudioCard(audio, busquedasService, true));
			});
			if (audios.size() < RESULTADOS_POR_PAGINA) {
				b_buscarMas.setEnabled(false);
				return;
			} else {
				numeroPagina++;
			}
		}
	}



	private void style() {
		mainLayout.setMargin(new MarginInfo(true, false, true, false));
		vl_contenedor.setMargin(new MarginInfo(true, false, true, false));
		b_buscarMas.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		b_buscarMas.setIcon(VaadinIcons.PLUS_CIRCLE_O);
		l_titulo.addStyleName(ValoTheme.LABEL_COLORED);
		l_titulo.addStyleName(ValoTheme.LABEL_H2);
		l_titulo.addStyleName(ValoTheme.LABEL_BOLD);
		this.contenido.addStyleName("contenidoCSSLayout");
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setStyleName("contenido");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");

		// hl_cabecera
		hl_cabecera = buildHl_cabecera();
		mainLayout.addComponent(hl_cabecera);

		// vl_contenedor
		vl_contenedor = new VerticalLayout();
		vl_contenedor.setImmediate(false);
		vl_contenedor.setWidth("100.0%");
		vl_contenedor.setHeight("-1px");
		vl_contenedor.setMargin(false);
		mainLayout.addComponent(vl_contenedor);

		// b_buscarMas
		b_buscarMas = new Button();
		b_buscarMas.setCaption("Ver mas Audios");
		b_buscarMas.setImmediate(true);
		b_buscarMas.setWidth("-1px");
		b_buscarMas.setHeight("-1px");
		mainLayout.addComponent(b_buscarMas);
		mainLayout.setComponentAlignment(b_buscarMas, new Alignment(48));

		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHl_cabecera() {
		// common part: create layout
		hl_cabecera = new HorizontalLayout();
		hl_cabecera.setStyleName("cabeceraContenido");
		hl_cabecera.setImmediate(false);
		hl_cabecera.setWidth("100.0%");
		hl_cabecera.setHeight("-1px");
		hl_cabecera.setMargin(false);

		// l_titulo
		l_titulo = new Label();
		l_titulo.setImmediate(false);
		l_titulo.setWidth("-1px");
		l_titulo.setHeight("-1px");
		l_titulo.setValue("Resultados de la Busqueda");
		hl_cabecera.addComponent(l_titulo);

		return hl_cabecera;
	}

}
