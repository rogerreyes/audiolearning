package com.ud.audiolearning.mislistas.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;
import org.vaadin.teemu.VaadinIcons;

import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.ListaDifusion;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.mislistas.service.MisListasService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")

public class ViewInfoLista extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private Button b_editar;
	@AutoGenerated
	private Button b_guardar;
	@PropertyId("descripcion")
	@AutoGenerated
	private TextArea ta_descripcion;
	@AutoGenerated
	private GridLayout gridLayout_2;
	@PropertyId("fechaCreacion")
	@AutoGenerated
	private PopupDateField df_fechaCreacion;
	@PropertyId("estado")
	@AutoGenerated
	private ComboBox cb_estado;
	@PropertyId("titulo")
	@AutoGenerated
	private TextField tf_titulo;
	private ListaDifusion listaDifusion;

	
	
	MisListasService misListasService;
	private Consumer<String> ConsumerLista;

	public ViewInfoLista(ListaDifusion lista, String operacion, MisListasService misListasService, Consumer<String> consumer) {
		this.listaDifusion = lista;
		this.misListasService = misListasService;
		this.ConsumerLista = consumer;

		buildMainLayout();
		setCompositionRoot(mainLayout);
		init(operacion);
		style();
	}

	public void init(String operacion) {

		BeanItem<ListaDifusion> BeanItem = new BeanItem<ListaDifusion>(listaDifusion);
		FieldGroup binder = new FieldGroup(BeanItem);
		binder.bindMemberFields(this);

		
		validaciones();
		
		cb_estado.addItem("A");
		cb_estado.addItem("I");
		cb_estado.setItemCaption("A", "Activo");
		cb_estado.setItemCaption("I", "Inactivo");
		deshabilitarCampos();

		switch (operacion) {
		case "C":
			df_fechaCreacion.setValue(new Date());
			b_guardar.setVisible(true);
			listaDifusion.setUsuario(AppSession.getUser());
			listaDifusion.setAudios(new ArrayList<Audio>());
			break;
		case "U":
			b_editar.setVisible(true);
			break;
		default:
			break;
		}

		b_guardar.addClickListener(e -> guardarLista(binder));

		b_editar.addClickListener(e -> editarLista(binder));
	}
	
	private void validaciones(){
		cb_estado.addValidator(new NullValidator("Requerido", false));	
		cb_estado.setValidationVisible(false);
		tf_titulo.addValidator(new NullValidator("Requerido", false));
		tf_titulo.setValidationVisible(false);
		tf_titulo.setNullRepresentation("");
		ta_descripcion.setNullRepresentation("");
	}
	
	private void mostrarValidaciones(){
		cb_estado.setValidationVisible(true);
		tf_titulo.setValidationVisible(true);
	}
	
	

	private void guardarLista(FieldGroup binder) {
		mostrarValidaciones();
		try {
			
			cb_estado.isValid();
			binder.commit();
			if(!misListasService.guardarListaDifusion(listaDifusion)){
				Notification.show("Error!", "Ocurrio un problema al intentar insertar la Lista de Difusion!", Type.ERROR_MESSAGE);
			}
			ConsumerLista.accept(null);
			UI.getCurrent().removeWindow((Window)this.getParent());
			Notification n = new Notification("Correcto!", "Se ha insertado correctamente la Lista", Type.HUMANIZED_MESSAGE);
			n.setDelayMsec(3600);
			n.show(UI.getCurrent().getPage());
		} catch (CommitException e) {
			Notification.show("Aviso!", "Verifique los campos del formulario", Type.WARNING_MESSAGE);
		}
	}

	private void editarLista(FieldGroup binder) {
		mostrarValidaciones();
		try {
			binder.commit();
			
			misListasService.editarListaDifusion(listaDifusion);
			ConsumerLista.accept(null);
			UI.getCurrent().removeWindow((Window)this.getParent());
			Notification n = new Notification("Correcto!", "Se ha modificado correctamente la Lista", Type.HUMANIZED_MESSAGE);
			n.setDelayMsec(3600);
			n.show(UI.getCurrent().getPage());
		} catch (Exception e2) {
			Notification.show("Aviso!", "Verifique los campos del formulario", Type.WARNING_MESSAGE);
		}
	}

	public void style() {

		b_editar.setIcon(VaadinIcons.EDIT);
		b_guardar.setIcon(FontAwesome.SAVE);
		b_guardar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		b_editar.addStyleName(ValoTheme.BUTTON_PRIMARY);
	}

	private void deshabilitarCampos() {
		df_fechaCreacion.setEnabled(false);
		b_guardar.setVisible(false);
		b_editar.setVisible(false);
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
		
		// tf_titulo
		tf_titulo = new TextField();
		tf_titulo.setCaption("Titulo");
		tf_titulo.setImmediate(true);
		tf_titulo.setWidth("100.0%");
		tf_titulo.setHeight("-1px");
		mainLayout.addComponent(tf_titulo);
		
		// gridLayout_2
		gridLayout_2 = buildGridLayout_2();
		mainLayout.addComponent(gridLayout_2);
		
		// ta_descripcion
		ta_descripcion = new TextArea();
		ta_descripcion.setCaption("Descripcion");
		ta_descripcion.setImmediate(false);
		ta_descripcion.setWidth("100.0%");
		ta_descripcion.setHeight("-1px");
		mainLayout.addComponent(ta_descripcion);
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		mainLayout.setComponentAlignment(horizontalLayout_1, new Alignment(48));
		
		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_2() {
		// common part: create layout
		gridLayout_2 = new GridLayout();
		gridLayout_2.setImmediate(false);
		gridLayout_2.setWidth("100.0%");
		gridLayout_2.setHeight("-1px");
		gridLayout_2.setMargin(false);
		gridLayout_2.setColumns(2);
		
		// cb_estado
		cb_estado = new ComboBox();
		cb_estado.setCaption("Estado");
		cb_estado.setImmediate(true);
		cb_estado.setWidth("90.0%");
		cb_estado.setHeight("-1px");
		gridLayout_2.addComponent(cb_estado, 0, 0);
		
		// df_fechaCreacion
		df_fechaCreacion = new PopupDateField();
		df_fechaCreacion.setCaption("Fecha de Creación");
		df_fechaCreacion.setImmediate(true);
		df_fechaCreacion.setWidth("90.0%");
		df_fechaCreacion.setHeight("-1px");
		gridLayout_2.addComponent(df_fechaCreacion, 1, 0);
		
		return gridLayout_2;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		horizontalLayout_1.setSpacing(true);
		
		// b_guardar
		b_guardar = new Button();
		b_guardar.setCaption("Guardar");
		b_guardar.setImmediate(true);
		b_guardar.setWidth("-1px");
		b_guardar.setHeight("-1px");
		horizontalLayout_1.addComponent(b_guardar);
		
		// b_editar
		b_editar = new Button();
		b_editar.setCaption("Editar");
		b_editar.setImmediate(true);
		b_editar.setWidth("-1px");
		b_editar.setHeight("-1px");
		horizontalLayout_1.addComponent(b_editar);
		
		return horizontalLayout_1;
	}

}
