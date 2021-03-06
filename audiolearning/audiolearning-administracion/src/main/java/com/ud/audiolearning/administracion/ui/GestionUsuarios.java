package com.ud.audiolearning.administracion.ui;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.ud.audiolearning.administracion.service.AdministracionService;
import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.domain.Usuario;
import com.ud.audiolearning.api.service.ApiService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@UIComponent
@VaadinView("Administracion/GestionUsuarios")
public class GestionUsuarios extends CustomComponent implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private Table table_usuarios;
	@AutoGenerated
	private HorizontalLayout hL_buttons;
	@AutoGenerated
	private Button bT_consultar;
	@AutoGenerated
	private GridLayout gL_opcionBuscar;
	@AutoGenerated
	private TextField tF_genero;
	@AutoGenerated
	private TextField tF_email;
	@AutoGenerated
	private TextField tF_nick;
	@AutoGenerated
	private TextField tF_nacionalidad;
	@AutoGenerated
	private TextField tF_apellidos;
	@AutoGenerated
	private TextField tF_nombres;
	@AutoGenerated
	private HorizontalLayout hl_cabecera;
	@AutoGenerated
	private Button bT_Crear;
	@AutoGenerated
	private Label l_titulo;
	@Autowired
	private AdministracionService administracionService;
	@Autowired
	ApiService apiService;
	//private Usuario usuario;
	private BeanItemContainer<Usuario> beanContainer;
	private HashMap<String, String> Parametros;
	private GestionUsuarios gestionUsuarios;

	public GestionUsuarios() {

	}

	public AdministracionService getAdministracionService() {
		return administracionService;
	}

	public void setAdministracionService(AdministracionService administracionService) {
		this.administracionService = administracionService;
	}

	public ApiService getApiService() {
		return apiService;
	}

	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		init();
		style();

		beanContainer.removeAllItems();
		beanContainer.addAll(administracionService.listadoUsuarios(null));
	}

	private void init() {
		
		gestionUsuarios= this;
		tF_apellidos.setImmediate(true);
		tF_email.setImmediate(true);
		tF_genero.setImmediate(true);
		tF_nick.setImmediate(true);
		tF_nacionalidad.setImmediate(true);
		tF_nombres.setImmediate(true);

		bT_consultar.setIcon(FontAwesome.SEARCH);
		bT_consultar.addClickListener(this::consultarUsuarios);
		bT_consultar.setDisableOnClick(true);
		bT_consultar.setImmediate(true);
		bT_Crear.setIcon(FontAwesome.USER);
		bT_Crear.addClickListener(this::crearUsuario);
		bT_Crear.setDisableOnClick(true);
		bT_Crear.setImmediate(true);

		beanContainer = new BeanItemContainer<Usuario>(Usuario.class);
		table_usuarios.setContainerDataSource(beanContainer);
		
		table_usuarios.addGeneratedColumn("Editar", this::editarUsuario);
		table_usuarios.setColumnWidth("Editar", 40);
		
		table_usuarios.addGeneratedColumn("Eliminar", this::eliminarUsuario);
		table_usuarios.setColumnWidth("Eliminar", 40);
		
		table_usuarios.setSelectable(true);
		table_usuarios.setVisibleColumns("Editar","Eliminar","nick", "nombres", "apellidos", "email", "nacionalidad", "genero");
		table_usuarios.setColumnHeaders("","","Usuario", "Nombres", "Apellidos", "Correo", "Nacionalidad", "Genero");
		table_usuarios.setImmediate(true);

	}

	public Object editarUsuario(Table source, Object itemId, Object columnId) {
		Button editarUsuario = new Button();
		editarUsuario.addClickListener(new ClickListener() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				Usuario editar = (Usuario)itemId;
				ViewCrearUsuario nuevoItem = new ViewCrearUsuario(gestionUsuarios , editar, gestionUsuarios::llenarTabla);
				Window crearUser = new Window("Modificar Usuario", nuevoItem);
				crearUser.setModal(true);
				crearUser.setWidth("95%");
				crearUser.setHeight("95%");
				UI.getCurrent().addWindow(crearUser);
				editarUsuario.setEnabled(true);
				
				
				
			}
		});
		editarUsuario.setDisableOnClick(true);
		editarUsuario.setDescription("Editar");
		editarUsuario.setIcon(FontAwesome.SEARCH_PLUS);
		editarUsuario.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		editarUsuario.setImmediate(true);
		
		return editarUsuario;
	}

	public Object eliminarUsuario(Table source, Object itemId, Object columnId) {
		Button eliminarUsuario = new Button();
		eliminarUsuario.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				Usuario eliminar = (Usuario)itemId;
				ConfirmDialog.show(UI.getCurrent(), "Eliminar Usuario:",
						"Esta seguro que desea eliminar el Usuario: " + eliminar.getNombres(), "Estoy Seguro",
						"No", new ConfirmDialog.Listener() {

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									gestionUsuarios.administracionService.eliminarUsuario(eliminar);
									gestionUsuarios.llenarTabla("");
								} else {
								}
							}
						});
				
				
				
			}
		});
		
		eliminarUsuario.setDescription("Eliminar");
		eliminarUsuario.setIcon(FontAwesome.BOMB);
		eliminarUsuario.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		eliminarUsuario.setImmediate(true);
		return eliminarUsuario;
	}

	private void style() {

		l_titulo.addStyleName(ValoTheme.LABEL_COLORED);
		l_titulo.addStyleName(ValoTheme.LABEL_H2);
		l_titulo.addStyleName(ValoTheme.LABEL_BOLD);
		table_usuarios.addStyleName("orangeHeader");
		bT_Crear.addStyleName(ValoTheme.BUTTON_PRIMARY);
		bT_consultar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
	}

	public void consultarUsuarios(Button.ClickEvent event) {
		Parametros = new HashMap<>();

		if (tF_nombres != null) {
			Parametros.put("nombres", tF_nombres.getValue());
		}

		if (tF_apellidos != null) {
			Parametros.put("apellidos", tF_apellidos.getValue());
		}

		if (tF_nacionalidad != null) {
			Parametros.put("nacionalidad", tF_nacionalidad.getValue());
		}

		if (tF_nick != null) {
			Parametros.put("nick", tF_nick.getValue());
		}

		if (tF_email != null) {
			Parametros.put("email", tF_email.getValue());
		}

		if (tF_genero != null) {
			Parametros.put("genero", tF_genero.getValue());
		}

		llenarTabla("");
		bT_consultar.setEnabled(true);
	}

	public void llenarTabla(String nada) {
		beanContainer.removeAllItems();
		beanContainer.addAll(administracionService.listadoUsuarios(Parametros));
	}

	public void crearUsuario(Button.ClickEvent event) {

		ViewCrearUsuario nuevoItem = new ViewCrearUsuario(this, null, this::llenarTabla);
		Window crearUser = new Window("Crear Usuario", nuevoItem);
		crearUser.setModal(true);
		crearUser.setWidth("95%");
		crearUser.setHeight("95%");
		UI.getCurrent().addWindow(crearUser);
		bT_Crear.setEnabled(true);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setStyleName("contenido");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// hl_cabecera
		hl_cabecera = buildHl_cabecera();
		mainLayout.addComponent(hl_cabecera);
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		mainLayout.addComponent(verticalLayout_2);
		
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
		l_titulo.setValue("Gestion de Usuarios");
		hl_cabecera.addComponent(l_titulo);
		
		// bT_Crear
		bT_Crear = new Button();
		bT_Crear.setCaption("Crear Usuario");
		bT_Crear.setImmediate(true);
		bT_Crear.setWidth("140px");
		bT_Crear.setHeight("-1px");
		hl_cabecera.addComponent(bT_Crear);
		hl_cabecera.setComponentAlignment(bT_Crear, new Alignment(6));
		
		return hl_cabecera;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("-1px");
		verticalLayout_2.setMargin(false);
		verticalLayout_2.setSpacing(true);
		
		// gL_opcionBuscar
		gL_opcionBuscar = buildGL_opcionBuscar();
		verticalLayout_2.addComponent(gL_opcionBuscar);
		
		// hL_buttons
		hL_buttons = buildHL_buttons();
		verticalLayout_2.addComponent(hL_buttons);
		verticalLayout_2.setComponentAlignment(hL_buttons, new Alignment(6));
		
		// table_usuarios
		table_usuarios = new Table();
		table_usuarios.setImmediate(false);
		table_usuarios.setWidth("100.0%");
		table_usuarios.setHeight("-1px");
		verticalLayout_2.addComponent(table_usuarios);
		
		return verticalLayout_2;
	}

	@AutoGenerated
	private GridLayout buildGL_opcionBuscar() {
		// common part: create layout
		gL_opcionBuscar = new GridLayout();
		gL_opcionBuscar.setImmediate(false);
		gL_opcionBuscar.setWidth("100.0%");
		gL_opcionBuscar.setHeight("-1px");
		gL_opcionBuscar.setMargin(false);
		gL_opcionBuscar.setSpacing(true);
		gL_opcionBuscar.setColumns(3);
		gL_opcionBuscar.setRows(3);
		
		// tF_nombres
		tF_nombres = new TextField();
		tF_nombres.setCaption("Nombres");
		tF_nombres.setImmediate(false);
		tF_nombres.setWidth("90.0%");
		tF_nombres.setHeight("-1px");
		gL_opcionBuscar.addComponent(tF_nombres, 0, 0);
		
		// tF_apellidos
		tF_apellidos = new TextField();
		tF_apellidos.setCaption("Apellidos");
		tF_apellidos.setImmediate(false);
		tF_apellidos.setWidth("90.0%");
		tF_apellidos.setHeight("-1px");
		gL_opcionBuscar.addComponent(tF_apellidos, 1, 0);
		
		// tF_nacionalidad
		tF_nacionalidad = new TextField();
		tF_nacionalidad.setCaption("Nacionalidad");
		tF_nacionalidad.setImmediate(false);
		tF_nacionalidad.setWidth("90.0%");
		tF_nacionalidad.setHeight("-1px");
		gL_opcionBuscar.addComponent(tF_nacionalidad, 2, 0);
		
		// tF_nick
		tF_nick = new TextField();
		tF_nick.setCaption("Usuario");
		tF_nick.setImmediate(false);
		tF_nick.setWidth("90.0%");
		tF_nick.setHeight("-1px");
		gL_opcionBuscar.addComponent(tF_nick, 0, 1);
		
		// tF_email
		tF_email = new TextField();
		tF_email.setCaption("Correo");
		tF_email.setImmediate(false);
		tF_email.setWidth("90.0%");
		tF_email.setHeight("-1px");
		gL_opcionBuscar.addComponent(tF_email, 1, 1);
		
		// tF_genero
		tF_genero = new TextField();
		tF_genero.setCaption("Genero");
		tF_genero.setImmediate(false);
		tF_genero.setWidth("90.0%");
		tF_genero.setHeight("-1px");
		gL_opcionBuscar.addComponent(tF_genero, 2, 1);
		
		return gL_opcionBuscar;
	}

	@AutoGenerated
	private HorizontalLayout buildHL_buttons() {
		// common part: create layout
		hL_buttons = new HorizontalLayout();
		hL_buttons.setImmediate(false);
		hL_buttons.setWidth("100.0%");
		hL_buttons.setHeight("-1px");
		hL_buttons.setMargin(false);
		hL_buttons.setSpacing(true);
		
		// bT_consultar
		bT_consultar = new Button();
		bT_consultar.setCaption("Consultar");
		bT_consultar.setImmediate(true);
		bT_consultar.setWidth("140px");
		bT_consultar.setHeight("-1px");
		hL_buttons.addComponent(bT_consultar);
		hL_buttons.setComponentAlignment(bT_consultar, new Alignment(48));
		
		return hL_buttons;
	}

}
