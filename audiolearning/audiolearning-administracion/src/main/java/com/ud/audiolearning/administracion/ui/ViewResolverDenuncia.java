package com.ud.audiolearning.administracion.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.function.Consumer;

import org.bson.types.ObjectId;
import org.vaadin.teemu.VaadinIcons;

import com.ud.audiolearning.administracion.service.AdministracionService;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.Denuncia;
import com.ud.audiolearning.api.domain.ReporteDenuncia;
import com.ud.audiolearning.api.domain.Usuario;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.api.ui.LayoutCellDescripcion;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class ViewResolverDenuncia extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private Button b_resolver;
	@AutoGenerated
	private TextArea ta_observaciones;
	@AutoGenerated
	private Table table_reportesDenuncia;
	@AutoGenerated
	private Panel panel_2;
	@AutoGenerated
	private HorizontalLayout hl_detalleAudio;
	@AutoGenerated
	private Panel panel_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_4;
	@AutoGenerated
	private OptionGroup opg_acciones;
	@AutoGenerated
	private HorizontalLayout hl_descripcionAudio;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private Button b_play;
	@AutoGenerated
	private Embedded embedded_1;
	@AutoGenerated
	private Label l_titulo;
	private BeanItemContainer<ReporteDenuncia> BICReportesDenuncia;
	private Denuncia denunciaAtencion;
	private AdministracionService administracionService;
	private Consumer<String> consumerDenuncias;

	public ViewResolverDenuncia(ObjectId idDenuncia, ObjectId idAudio, AdministracionService administracionService, Consumer<String> consumerRegargarDenuncias) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.consumerDenuncias = consumerRegargarDenuncias;
		
		this.administracionService = administracionService;
		this.denunciaAtencion = this.administracionService.consultarDetalleDenuncia(idDenuncia);

		BICReportesDenuncia = new BeanItemContainer<ReporteDenuncia>(ReporteDenuncia.class);
		BICReportesDenuncia.addNestedContainerBean("usuario");
		table_reportesDenuncia.setContainerDataSource(BICReportesDenuncia);
		table_reportesDenuncia.setVisibleColumns("usuario.nick", "fechaDenuncia", "motivo", "detalle");
		table_reportesDenuncia.setColumnHeaders("USUARIO", "FECHA DENUNCIA", "TIPO", "DETALLE");

		Audio audio = administracionService.consultarDatosAudio(String.valueOf(idAudio));

		LayoutCellDescripcion descripcionAudio = new LayoutCellDescripcion(audio.getTitulo(), audio.getDescripcion(),
				audio.getFechaCreacion(), audio.getEstado());
		
		hl_descripcionAudio.addComponent(descripcionAudio);
		
		
		

		if (audio.getImagen().getBinaryData() != null) {
			embedded_1.setSource(new StreamResource(new StreamSource() {
				@Override
				public InputStream getStream() {
					return new ByteArrayInputStream(audio.getImagen().getBinaryData().getData());
				}
			}, audio.getImagen().getNombre()));
		}

		
		b_play.addClickListener(e-> medotoPlay(audio.getId()));
		b_play.setCaption("");
		
		
		b_resolver.addClickListener(e-> procesarDenuncia());
		llenar();
		llenarAcciones();
		style();

	}
	
	
	
	private void llenarAcciones(){
		
		opg_acciones.addItem("DELETE");
		opg_acciones.setItemCaption("DELETE", "Eliminar audio");
		
		opg_acciones.addItem("BLOCK");
		opg_acciones.setItemCaption("BLOCK", "Bloquear Contenido");
		
	}

	private void procesarDenuncia() {
		
		this.denunciaAtencion.setDetalleCierre(ta_observaciones.getValue());
		this.denunciaAtencion.setFechaCierre(new Date());
		this.denunciaAtencion.setEstado("C");
		Usuario usuarioAtencion = new Usuario();
		usuarioAtencion.setId(AppSession.getUser().getId());
		usuarioAtencion.setNombres(AppSession.getUser().getNombres());
		usuarioAtencion.setApellidos(AppSession.getUser().getApellidos());
		usuarioAtencion.setNick(AppSession.getUser().getNick());
		
		this.denunciaAtencion.setUsuarioAtencion(usuarioAtencion);
		
		
		try {
			administracionService.procesarDenuncia(this.denunciaAtencion);
			consumerDenuncias.accept(null);
			
			Notification n = new Notification("Correcto!", "Se ha procesado la denuncia",	Type.HUMANIZED_MESSAGE);
			n.setDelayMsec(3600);
			n.show(UI.getCurrent().getPage());
			
			UI.getCurrent().getWindows().forEach(window -> window.close());
			
		} catch (Exception e) {
			Notification.show("Error!", "Se produjo un error al intentar procesar la denuncia", Type.ERROR_MESSAGE);
		}
		
	}
	
	
	

	private void medotoPlay(ObjectId objectId) {
		getUI().getNavigator().navigateTo("reproductor/" + objectId);
		UI.getCurrent().getWindows().forEach(window -> window.close());
	}


	private void style() {

		table_reportesDenuncia.addStyleName("orangeHeader");
		b_resolver.addStyleName(ValoTheme.BUTTON_PRIMARY);
		l_titulo.addStyleName(ValoTheme.LABEL_COLORED);
		l_titulo.addStyleName(ValoTheme.LABEL_H2);
		b_play.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		b_play.setIcon(VaadinIcons.PLAY);
		hl_detalleAudio.setMargin(true);

	}

	private void llenar() {
		BICReportesDenuncia.removeAllItems();
		BICReportesDenuncia.addAll(this.denunciaAtencion.getReportesDenuncia());

	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// l_titulo
		l_titulo = new Label();
		l_titulo.setStyleName("mih2");
		l_titulo.setImmediate(false);
		l_titulo.setWidth("100.0%");
		l_titulo.setHeight("-1px");
		l_titulo.setValue("Detalles de la Denuncia");
		mainLayout.addComponent(l_titulo);
		
		// panel_2
		panel_2 = buildPanel_2();
		mainLayout.addComponent(panel_2);
		
		// table_reportesDenuncia
		table_reportesDenuncia = new Table();
		table_reportesDenuncia.setCaption("Reportes de Denuncia");
		table_reportesDenuncia.setImmediate(false);
		table_reportesDenuncia.setWidth("100.0%");
		table_reportesDenuncia.setHeight("180px");
		mainLayout.addComponent(table_reportesDenuncia);
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		return mainLayout;
	}



	@AutoGenerated
	private Panel buildPanel_2() {
		// common part: create layout
		panel_2 = new Panel();
		panel_2.setImmediate(false);
		panel_2.setWidth("100.0%");
		panel_2.setHeight("-1px");
		
		// hl_detalleAudio
		hl_detalleAudio = buildHl_detalleAudio();
		panel_2.setContent(hl_detalleAudio);
		
		return panel_2;
	}



	@AutoGenerated
	private HorizontalLayout buildHl_detalleAudio() {
		// common part: create layout
		hl_detalleAudio = new HorizontalLayout();
		hl_detalleAudio.setImmediate(false);
		hl_detalleAudio.setWidth("100.0%");
		hl_detalleAudio.setHeight("-1px");
		hl_detalleAudio.setMargin(false);
		hl_detalleAudio.setSpacing(true);
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		hl_detalleAudio.addComponent(verticalLayout_2);
		
		// hl_descripcionAudio
		hl_descripcionAudio = new HorizontalLayout();
		hl_descripcionAudio.setImmediate(false);
		hl_descripcionAudio.setWidth("100.0%");
		hl_descripcionAudio.setHeight("-1px");
		hl_descripcionAudio.setMargin(false);
		hl_detalleAudio.addComponent(hl_descripcionAudio);
		hl_detalleAudio.setExpandRatio(hl_descripcionAudio, 1.0f);
		
		// panel_1
		panel_1 = buildPanel_1();
		hl_detalleAudio.addComponent(panel_1);
		
		return hl_detalleAudio;
	}



	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("-1px");
		verticalLayout_2.setHeight("-1px");
		verticalLayout_2.setMargin(false);
		verticalLayout_2.setSpacing(true);
		
		// embedded_1
		embedded_1 = new Embedded();
		embedded_1.setImmediate(false);
		embedded_1.setWidth("72px");
		embedded_1.setHeight("72px");
		embedded_1.setSource(new ThemeResource("img/component/embedded_icon.png"));
		embedded_1.setType(1);
		embedded_1.setMimeType("image/png");
		verticalLayout_2.addComponent(embedded_1);
		
		// b_play
		b_play = new Button();
		b_play.setCaption("Button");
		b_play.setImmediate(true);
		b_play.setWidth("-1px");
		b_play.setHeight("-1px");
		verticalLayout_2.addComponent(b_play);
		verticalLayout_2.setComponentAlignment(b_play, new Alignment(48));
		
		return verticalLayout_2;
	}



	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setCaption("Acciones");
		panel_1.setImmediate(false);
		panel_1.setWidth("330px");
		panel_1.setHeight("100.0%");
		
		// verticalLayout_4
		verticalLayout_4 = buildVerticalLayout_4();
		panel_1.setContent(verticalLayout_4);
		
		return panel_1;
	}



	@AutoGenerated
	private VerticalLayout buildVerticalLayout_4() {
		// common part: create layout
		verticalLayout_4 = new VerticalLayout();
		verticalLayout_4.setImmediate(false);
		verticalLayout_4.setWidth("100.0%");
		verticalLayout_4.setHeight("100.0%");
		verticalLayout_4.setMargin(true);
		
		// opg_acciones
		opg_acciones = new OptionGroup();
		opg_acciones.setImmediate(false);
		opg_acciones.setWidth("100.0%");
		opg_acciones.setHeight("-1px");
		verticalLayout_4.addComponent(opg_acciones);
		
		return verticalLayout_4;
	}



	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("100.0%");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		horizontalLayout_1.setSpacing(true);
		
		// ta_observaciones
		ta_observaciones = new TextArea();
		ta_observaciones.setCaption("Observaciones");
		ta_observaciones.setImmediate(false);
		ta_observaciones.setWidth("100.0%");
		ta_observaciones.setHeight("60px");
		horizontalLayout_1.addComponent(ta_observaciones);
		horizontalLayout_1.setExpandRatio(ta_observaciones, 1.0f);
		
		// b_resolver
		b_resolver = new Button();
		b_resolver.setCaption("Resolver Denuncia");
		b_resolver.setImmediate(true);
		b_resolver.setWidth("-1px");
		b_resolver.setHeight("-1px");
		horizontalLayout_1.addComponent(b_resolver);
		horizontalLayout_1.setComponentAlignment(b_resolver, new Alignment(48));
		
		return horizontalLayout_1;
	}

}
