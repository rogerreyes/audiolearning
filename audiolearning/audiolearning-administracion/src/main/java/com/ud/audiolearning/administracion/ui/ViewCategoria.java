package com.ud.audiolearning.administracion.ui;

import java.util.function.Consumer;

import com.ud.audiolearning.api.domain.Categoria;
import com.ud.audiolearning.api.service.ApiService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class ViewCategoria extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button b_editar;
	@AutoGenerated
	private Button b_crear;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private GridLayout gridLayout_1;
	@AutoGenerated
	private TextArea ta_descripcion;
	@AutoGenerated
	private Label label_3;
	@AutoGenerated
	private TextField tf_nombre;
	@AutoGenerated
	private Label label_1;
	@AutoGenerated
	private TextField tf_codigo;
	@AutoGenerated
	private Label label_2;
	@AutoGenerated
	private Label l_titulo;
	private ApiService apiService;
	private Consumer<String> consumerCategoria;
	private Categoria categoria;
	private FieldGroup binder;
	private BeanItem<Categoria> beanCategoria;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 * 
	 * @param categoria
	 * @param apiService
	 * @param consumerCategoria
	 */
	public ViewCategoria(Categoria categoria, ApiService apiService, Consumer<String> consumerCategoria) {
		this.apiService = apiService;
		this.consumerCategoria = consumerCategoria;
		this.categoria = categoria;

		buildMainLayout();
		setCompositionRoot(mainLayout);

		if (this.categoria == null) {
			b_editar.setVisible(false);
			b_crear.setVisible(true);

		} else {
			b_editar.setVisible(true);
			b_crear.setVisible(false);
		}

		init();
		style();
		b_crear.addClickListener(e -> crear(consumerCategoria));
		b_editar.addClickListener(e -> edita(consumerCategoria));
	}

	private void edita(Consumer<String> consumer) {
		try {
			validarFormulario();
			binder.commit();
			// apiService.editarParametrico(this.parametrico);
			consumer.accept(null);
			System.err.println(this.categoria);
			Notification n = new Notification("Correcto!", "Se ha editado con exito el registro",
					Type.HUMANIZED_MESSAGE);
			n.setDelayMsec(3600);
			n.show(UI.getCurrent().getPage());
			UI.getCurrent().getWindows().forEach(window -> window.close());

		} catch (CommitException e) {
			Notification.show("Error de Validacion", "verifique los campos del formulario", Type.WARNING_MESSAGE);

		}
	}

	private void crear(Consumer<String> consumer) {
		try {
			validarFormulario();
			binder.commit();
			// apiService.crearParametrico(this.parametrico);

			System.err.println(this.categoria);
			consumer.accept(null);

			Notification n = new Notification("Correcto!", "Se ha creado con exito el registro",
					Type.HUMANIZED_MESSAGE);
			n.setDelayMsec(3600);
			n.show(UI.getCurrent().getPage());
			UI.getCurrent().getWindows().forEach(window -> window.close());

		} catch (CommitException e) {
			Notification.show("Error de Validacion", "verifique los campos del formulario", Type.WARNING_MESSAGE);

		}
	}

	private void validarFormulario() {

	}

	public void init() {

		binder = new FieldGroup();
		String accion = this.categoria == null ? "Agregar" : "Editar";
		if (this.categoria == null) {
			categoria = new Categoria();
		}

		beanCategoria = new BeanItem<Categoria>(this.categoria);
		binder.setItemDataSource(beanCategoria);

		iniciarImputs();

		binder.bind(tf_codigo, "codigo");
		binder.bind(tf_nombre, "nombre");
		binder.bind(ta_descripcion, "descripcion");

		l_titulo.setValue(accion + " Categoria");

	}

	public void style() {

		b_crear.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		b_crear.setIcon(FontAwesome.SAVE);
		b_editar.addStyleName(ValoTheme.BUTTON_PRIMARY);
		b_editar.setIcon(FontAwesome.EDIT);

		l_titulo.addStyleName(ValoTheme.LABEL_H2);
		l_titulo.addStyleName(ValoTheme.LABEL_COLORED);
		l_titulo.addStyleName(ValoTheme.LABEL_BOLD);
		l_titulo.addStyleName("mih2");
	}

	private void iniciarImputs() {

		tf_codigo.setNullRepresentation("");
		tf_nombre.setNullRepresentation("");
		ta_descripcion.setNullRepresentation("");
		Embedded imagen = new Embedded();
		imagen.setHeight("128px");
		imagen.setWidth("128px");
		imagen.setSource(new ThemeResource("img/categoria-icon.png"));
		horizontalLayout_1.addComponent(imagen, 0);
		horizontalLayout_1.setComponentAlignment(imagen, Alignment.MIDDLE_CENTER);

	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");

		// l_titulo
		l_titulo = new Label();
		l_titulo.setImmediate(false);
		l_titulo.setWidth("-1px");
		l_titulo.setHeight("-1px");
		l_titulo.setValue("Titulo");
		mainLayout.addComponent(l_titulo);

		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);

		// b_crear
		b_crear = new Button();
		b_crear.setCaption("Crear");
		b_crear.setImmediate(true);
		b_crear.setWidth("-1px");
		b_crear.setHeight("-1px");
		mainLayout.addComponent(b_crear);
		mainLayout.setComponentAlignment(b_crear, new Alignment(48));

		// b_editar
		b_editar = new Button();
		b_editar.setCaption("Editar");
		b_editar.setImmediate(true);
		b_editar.setWidth("-1px");
		b_editar.setHeight("-1px");
		mainLayout.addComponent(b_editar);
		mainLayout.setComponentAlignment(b_editar, new Alignment(48));

		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		horizontalLayout_1.addComponent(gridLayout_1);

		return horizontalLayout_1;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("-1px");
		gridLayout_1.setHeight("-1px");
		gridLayout_1.setMargin(false);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setColumns(2);
		gridLayout_1.setRows(3);

		// label_2
		label_2 = new Label();
		label_2.setImmediate(false);
		label_2.setWidth("-1px");
		label_2.setHeight("-1px");
		label_2.setValue("Codigo");
		gridLayout_1.addComponent(label_2, 0, 0);

		// tf_codigo
		tf_codigo = new TextField();
		tf_codigo.setImmediate(false);
		tf_codigo.setWidth("250px");
		tf_codigo.setHeight("-1px");
		gridLayout_1.addComponent(tf_codigo, 1, 0);

		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Nombre Categoria");
		gridLayout_1.addComponent(label_1, 0, 1);

		// tf_nombre
		tf_nombre = new TextField();
		tf_nombre.setImmediate(false);
		tf_nombre.setWidth("250px");
		tf_nombre.setHeight("-1px");
		gridLayout_1.addComponent(tf_nombre, 1, 1);

		// label_3
		label_3 = new Label();
		label_3.setImmediate(false);
		label_3.setWidth("-1px");
		label_3.setHeight("-1px");
		label_3.setValue("Descripcion");
		gridLayout_1.addComponent(label_3, 0, 2);

		// ta_descripcion
		ta_descripcion = new TextArea();
		ta_descripcion.setImmediate(false);
		ta_descripcion.setWidth("250px");
		ta_descripcion.setHeight("-1px");
		gridLayout_1.addComponent(ta_descripcion, 1, 2);

		return gridLayout_1;
	}

}
