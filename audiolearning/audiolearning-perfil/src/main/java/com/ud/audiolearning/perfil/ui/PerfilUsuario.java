package com.ud.audiolearning.perfil.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.domain.Categoria;
import com.ud.audiolearning.api.domain.Parametrico;
import com.ud.audiolearning.api.domain.Usuario;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.ud.audiolearning.api.service.ApiService;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.perfil.service.PerfilService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@UIComponent
@VaadinView("perfilUsuario")
public class PerfilUsuario extends CustomComponent implements View {
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button b_enviar;
	@AutoGenerated
	private HorizontalLayout hl_gridContent;
	@AutoGenerated
	private Label l_preferenciasUsuario;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private GridLayout gridLayout_1;
	@AutoGenerated
	private TextArea ta_descripcion;
	@AutoGenerated
	private Label l_descripcion;
	@AutoGenerated
	private TextField tf_twitter;
	@AutoGenerated
	private Label l_twitter;
	@AutoGenerated
	private TextField tf_facebook;
	@AutoGenerated
	private Label l_facebook;
	@AutoGenerated
	private TextField tf_blogWeb;
	@AutoGenerated
	private Label l_blog;
	@AutoGenerated
	private ComboBox cb_nacionalidad;
	@AutoGenerated
	private Label l_nacionalidad;
	@AutoGenerated
	private TextField tf_email;
	@AutoGenerated
	private Label l_email;
	@AutoGenerated
	private OptionGroup opg_sexo;
	@AutoGenerated
	private Label l_genero;
	@AutoGenerated
	private Label l_fechaRegistro;
	@AutoGenerated
	private Label l_Registro;
	@AutoGenerated
	private TextField tf_nick;
	@AutoGenerated
	private Label l_nick;
	@AutoGenerated
	private PasswordField pw_confirmarPass;
	@AutoGenerated
	private Label l_confirmarPass;
	@AutoGenerated
	private PasswordField pw_pass;
	@AutoGenerated
	private Label l_pass;
	@AutoGenerated
	private TextField tf_apellidos;
	@AutoGenerated
	private Label l_apellidos;
	@AutoGenerated
	private TextField tf_nombres;
	@AutoGenerated
	private Label l_nombre;
	@AutoGenerated
	private VerticalLayout vl_imagen;
	@AutoGenerated
	private Upload upload_1;
	@AutoGenerated
	private Embedded embedded_1;
	@AutoGenerated
	private HorizontalLayout hl_cabecera;
	@AutoGenerated
	private Label l_titulo;
	private Grid grid;

	@Autowired
	private ApiService apiService;

	@Autowired
	private PerfilService perfilService;

	private BeanItemContainer<Categoria> BICCategoria;

	private Usuario usuario;
	private FieldGroup binder;

	public PerfilUsuario() {
		
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

		iniciarCampos();
		
		this.usuario = perfilService.buscarUsuario();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		BeanItem<Usuario> BIUsuario = new BeanItem<Usuario>(this.usuario);
		binder= new FieldGroup();
		binder.setItemDataSource(BIUsuario);
		binder.bind(tf_nombres, "nombres");
		binder.bind(tf_apellidos, "apellidos");
		binder.bind(pw_pass, "contraseña");
		binder.bind(tf_nick, "nick");
		l_fechaRegistro.setValue(sdf.format(usuario.getFechaRegistro()));
		binder.bind(opg_sexo, "genero");
		binder.bind(cb_nacionalidad, "nacionalidad");
		binder.bind(tf_blogWeb, "blog");
		binder.bind(tf_facebook, "facebook");
		binder.bind(tf_twitter, "twitter");
		binder.bind(ta_descripcion, "descripcion");
		
		pw_confirmarPass.setValue(usuario.getContraseña());
		
		

		gridLayout_1.setColumnExpandRatio(1, 1.0f);

		upload_1.setImmediate(true);

		grid = new Grid();
		BICCategoria = new BeanItemContainer<>(Categoria.class);
		BICCategoria.addAll(apiService.findAllCategorias());

		grid.setContainerDataSource(BICCategoria);
		grid.setHeight("300px");
		grid.setWidth("90%");

		Grid.Column bornColumn = grid.getColumn("nombre");
		bornColumn.setHeaderCaption("CATEGORIAS");

		Grid.Column descripcionColumn = grid.getColumn("descripcion");
		descripcionColumn.setHeaderCaption("DESCRIPCION");

		grid.setColumns("nombre", "descripcion");
		descripcionColumn.setExpandRatio(1);

		grid.setSelectionMode(SelectionMode.MULTI);

		hl_gridContent.addComponent(grid);
		hl_gridContent.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
		
		if(usuario.getCategorias()!= null){
		usuario.getCategorias().forEach(categoria -> grid.select(categoria));
		}else{
			usuario.setCategorias(new ArrayList<Categoria>());
		}
		
		b_enviar.addClickListener(e-> actualizarUsuario());
		tf_nick.setEnabled(false);
	

	}

	private void actualizarUsuario() {
	
		try {
			validadFormulario();
			binder.commit();
			
			if(this.usuario.getCategorias()!= null)
				this.usuario.getCategorias().clear();
			grid.getSelectedRows().forEach(item ->{ this.usuario.getCategorias().add((Categoria) item);});
			
			perfilService.actualizarUsuario(this.usuario);
			
			Usuario usuarioSession = AppSession.getUser();
			
			
			usuarioSession.setNombres(this.usuario.getNombres());
			usuarioSession.setApellidos(this.usuario.getApellidos());
		
			usuarioSession.setCategorias(this.usuario.getCategorias());
			
			
			
			
			
			Notification n = new Notification("Correcto!",
					"Se han actualizado lo datos de perfil", Type.HUMANIZED_MESSAGE);
			n.setDelayMsec(3600);
			n.show(UI.getCurrent().getPage());
			
		} catch (CommitException e) {
			Notification.show("Aviso de validación!", "Por favor verifique los campos del formulario", Type.WARNING_MESSAGE);
		} catch (Error error) {
			Notification.show("Aviso de validación!", error.getMessage(), Type.WARNING_MESSAGE);
		} 
		
		
	}
	
	
	private void validadFormulario(){
		
		String requerido = "Campo Requerido";

		
		tf_nombres.setRequired(true);
		tf_nombres.setRequiredError(requerido);
		
		tf_apellidos.setRequired(true);
		tf_apellidos.setRequiredError(requerido);
		
		tf_nick.setRequired(true);
		tf_nick.setRequiredError(requerido);
		
		pw_pass.setRequired(true);
		pw_pass.setRequiredError(requerido);
		
		if(!pw_pass.getValue().equals(pw_confirmarPass.getValue())){
			
			pw_confirmarPass.setComponentError(new UserError("La contraseña no Coincide"));
			
			throw new Error("La contraseña no coincide");
			
		}
		
		
		
		cb_nacionalidad.setRequired(true);
		cb_nacionalidad.setRequiredError(requerido);
		
		
		opg_sexo.setComponentError(null);
		if(opg_sexo.getValue()==null){
			opg_sexo.setComponentError(new UserError(requerido));
			throw new Error("Debe seleccionar el genero");
		}

		
	}

	private void style() {
		grid.addStyleName("myGrid");
		l_titulo.addStyleName(ValoTheme.LABEL_COLORED);
		l_titulo.addStyleName(ValoTheme.LABEL_H2);
		l_titulo.addStyleName(ValoTheme.LABEL_BOLD);
		b_enviar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		b_enviar.setIcon(FontAwesome.SAVE);
		l_preferenciasUsuario.addStyleName(ValoTheme.LABEL_COLORED);
		l_preferenciasUsuario.addStyleName(ValoTheme.LABEL_H2);
		opg_sexo.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
	}

	
	private void iniciarCampos(){
		
		BeanContainer<String, Parametrico> BIPaices = new BeanContainer<>(Parametrico.class);
		BIPaices.setBeanIdProperty("codigo");
		BIPaices.addAll(apiService.findAllPaises());
		cb_nacionalidad.setContainerDataSource(BIPaices);
		cb_nacionalidad.setItemCaptionPropertyId("nombre");
		cb_nacionalidad.setRequired(true);

		
		
		tf_nombres.setNullRepresentation("");
		tf_apellidos.setNullRepresentation("");
		tf_nick.setNullRepresentation("");
		cb_nacionalidad.setValue(null);
		

		
		opg_sexo.addItem("M");
		opg_sexo.setItemCaption("M", "Masculino");
		opg_sexo.addItem("F");
		opg_sexo.setItemCaption("F", "Femenino");
		
		
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
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// hl_cabecera
		hl_cabecera = buildHl_cabecera();
		mainLayout.addComponent(hl_cabecera);
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		mainLayout.setComponentAlignment(horizontalLayout_1, new Alignment(20));
		
		// l_preferenciasUsuario
		l_preferenciasUsuario = new Label();
		l_preferenciasUsuario.setStyleName("mih2");
		l_preferenciasUsuario.setImmediate(false);
		l_preferenciasUsuario.setWidth("100.0%");
		l_preferenciasUsuario.setHeight("-1px");
		l_preferenciasUsuario.setValue("Preferencias de Usuario");
		mainLayout.addComponent(l_preferenciasUsuario);
		
		// hl_gridContent
		hl_gridContent = new HorizontalLayout();
		hl_gridContent.setImmediate(false);
		hl_gridContent.setWidth("100.0%");
		hl_gridContent.setHeight("-1px");
		hl_gridContent.setMargin(false);
		mainLayout.addComponent(hl_gridContent);
		
		// b_enviar
		b_enviar = new Button();
		b_enviar.setCaption("Actualizar");
		b_enviar.setImmediate(true);
		b_enviar.setWidth("-1px");
		b_enviar.setHeight("-1px");
		mainLayout.addComponent(b_enviar);
		mainLayout.setComponentAlignment(b_enviar, new Alignment(48));
		
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
		l_titulo.setValue("Mi Perfil - Actualizacion de Datos");
		hl_cabecera.addComponent(l_titulo);
		
		return hl_cabecera;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		
		// vl_imagen
		vl_imagen = buildVl_imagen();
		horizontalLayout_1.addComponent(vl_imagen);
		
		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		horizontalLayout_1.addComponent(gridLayout_1);
		horizontalLayout_1.setExpandRatio(gridLayout_1, 1.0f);
		
		return horizontalLayout_1;
	}

	@AutoGenerated
	private VerticalLayout buildVl_imagen() {
		// common part: create layout
		vl_imagen = new VerticalLayout();
		vl_imagen.setStyleName("uploadImagen");
		vl_imagen.setImmediate(false);
		vl_imagen.setWidth("-1px");
		vl_imagen.setHeight("-1px");
		vl_imagen.setMargin(true);
		vl_imagen.setSpacing(true);
		
		// embedded_1
		embedded_1 = new Embedded();
		embedded_1.setImmediate(false);
		embedded_1.setWidth("300px");
		embedded_1.setHeight("300px");
		embedded_1.setSource(new ThemeResource("img/component/embedded_icon.png"));
		embedded_1.setType(1);
		embedded_1.setMimeType("image/png");
		vl_imagen.addComponent(embedded_1);
		
		// upload_1
		upload_1 = new Upload();
		upload_1.setImmediate(false);
		upload_1.setWidth("-1px");
		upload_1.setHeight("-1px");
		vl_imagen.addComponent(upload_1);
		vl_imagen.setComponentAlignment(upload_1, new Alignment(48));
		
		return vl_imagen;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("-1px");
		gridLayout_1.setHeight("-1px");
		gridLayout_1.setMargin(true);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setColumns(2);
		gridLayout_1.setRows(13);
		
		// l_nombre
		l_nombre = new Label();
		l_nombre.setImmediate(false);
		l_nombre.setWidth("200px");
		l_nombre.setHeight("18px");
		l_nombre.setValue("Nombres:");
		gridLayout_1.addComponent(l_nombre, 0, 0);
		
		// tf_nombres
		tf_nombres = new TextField();
		tf_nombres.setImmediate(false);
		tf_nombres.setWidth("300px");
		tf_nombres.setHeight("-1px");
		gridLayout_1.addComponent(tf_nombres, 1, 0);
		
		// l_apellidos
		l_apellidos = new Label();
		l_apellidos.setImmediate(false);
		l_apellidos.setWidth("-1px");
		l_apellidos.setHeight("-1px");
		l_apellidos.setValue("Apellidos:");
		gridLayout_1.addComponent(l_apellidos, 0, 1);
		
		// tf_apellidos
		tf_apellidos = new TextField();
		tf_apellidos.setImmediate(false);
		tf_apellidos.setWidth("100.0%");
		tf_apellidos.setHeight("-1px");
		gridLayout_1.addComponent(tf_apellidos, 1, 1);
		
		// l_pass
		l_pass = new Label();
		l_pass.setImmediate(false);
		l_pass.setWidth("-1px");
		l_pass.setHeight("-1px");
		l_pass.setValue("Contraseña:");
		gridLayout_1.addComponent(l_pass, 0, 2);
		
		// pw_pass
		pw_pass = new PasswordField();
		pw_pass.setImmediate(false);
		pw_pass.setWidth("100.0%");
		pw_pass.setHeight("-1px");
		gridLayout_1.addComponent(pw_pass, 1, 2);
		
		// l_confirmarPass
		l_confirmarPass = new Label();
		l_confirmarPass.setImmediate(false);
		l_confirmarPass.setWidth("-1px");
		l_confirmarPass.setHeight("-1px");
		l_confirmarPass.setValue("Confirmar Contraseña:");
		gridLayout_1.addComponent(l_confirmarPass, 0, 3);
		
		// pw_confirmarPass
		pw_confirmarPass = new PasswordField();
		pw_confirmarPass.setImmediate(false);
		pw_confirmarPass.setWidth("100.0%");
		pw_confirmarPass.setHeight("-1px");
		gridLayout_1.addComponent(pw_confirmarPass, 1, 3);
		
		// l_nick
		l_nick = new Label();
		l_nick.setImmediate(false);
		l_nick.setWidth("-1px");
		l_nick.setHeight("-1px");
		l_nick.setValue("Nombre Usuario/Nick:");
		gridLayout_1.addComponent(l_nick, 0, 4);
		
		// tf_nick
		tf_nick = new TextField();
		tf_nick.setImmediate(false);
		tf_nick.setWidth("100.0%");
		tf_nick.setHeight("-1px");
		gridLayout_1.addComponent(tf_nick, 1, 4);
		
		// l_Registro
		l_Registro = new Label();
		l_Registro.setImmediate(false);
		l_Registro.setWidth("-1px");
		l_Registro.setHeight("-1px");
		l_Registro.setValue("Fecha de Registro:");
		gridLayout_1.addComponent(l_Registro, 0, 5);
		
		// l_fechaRegistro
		l_fechaRegistro = new Label();
		l_fechaRegistro.setImmediate(false);
		l_fechaRegistro.setWidth("100.0%");
		l_fechaRegistro.setHeight("-1px");
		l_fechaRegistro.setValue("---------");
		gridLayout_1.addComponent(l_fechaRegistro, 1, 5);
		
		// l_genero
		l_genero = new Label();
		l_genero.setImmediate(false);
		l_genero.setWidth("-1px");
		l_genero.setHeight("-1px");
		l_genero.setValue("Genero:");
		gridLayout_1.addComponent(l_genero, 0, 6);
		
		// opg_sexo
		opg_sexo = new OptionGroup();
		opg_sexo.setImmediate(false);
		opg_sexo.setWidth("100.0%");
		opg_sexo.setHeight("30px");
		gridLayout_1.addComponent(opg_sexo, 1, 6);
		
		// l_email
		l_email = new Label();
		l_email.setImmediate(false);
		l_email.setWidth("-1px");
		l_email.setHeight("-1px");
		l_email.setValue("Email:");
		gridLayout_1.addComponent(l_email, 0, 7);
		
		// tf_email
		tf_email = new TextField();
		tf_email.setImmediate(false);
		tf_email.setWidth("100.0%");
		tf_email.setHeight("-1px");
		gridLayout_1.addComponent(tf_email, 1, 7);
		
		// l_nacionalidad
		l_nacionalidad = new Label();
		l_nacionalidad.setImmediate(false);
		l_nacionalidad.setWidth("-1px");
		l_nacionalidad.setHeight("-1px");
		l_nacionalidad.setValue("Nacionalidad:");
		gridLayout_1.addComponent(l_nacionalidad, 0, 8);
		
		// cb_nacionalidad
		cb_nacionalidad = new ComboBox();
		cb_nacionalidad.setImmediate(false);
		cb_nacionalidad.setWidth("100%");
		cb_nacionalidad.setHeight("-1px");
		gridLayout_1.addComponent(cb_nacionalidad, 1, 8);
		
		// l_blog
		l_blog = new Label();
		l_blog.setImmediate(false);
		l_blog.setWidth("-1px");
		l_blog.setHeight("-1px");
		l_blog.setValue("Tu Blog/Web:");
		gridLayout_1.addComponent(l_blog, 0, 9);
		
		// tf_blogWeb
		tf_blogWeb = new TextField();
		tf_blogWeb.setImmediate(false);
		tf_blogWeb.setWidth("100.0%");
		tf_blogWeb.setHeight("-1px");
		gridLayout_1.addComponent(tf_blogWeb, 1, 9);
		
		// l_facebook
		l_facebook = new Label();
		l_facebook.setImmediate(false);
		l_facebook.setWidth("-1px");
		l_facebook.setHeight("-1px");
		l_facebook.setValue("Facebook:");
		gridLayout_1.addComponent(l_facebook, 0, 10);
		
		// tf_facebook
		tf_facebook = new TextField();
		tf_facebook.setImmediate(false);
		tf_facebook.setWidth("100.0%");
		tf_facebook.setHeight("-1px");
		gridLayout_1.addComponent(tf_facebook, 1, 10);
		
		// l_twitter
		l_twitter = new Label();
		l_twitter.setImmediate(false);
		l_twitter.setWidth("-1px");
		l_twitter.setHeight("-1px");
		l_twitter.setValue("Twitter:");
		gridLayout_1.addComponent(l_twitter, 0, 11);
		
		// tf_twitter
		tf_twitter = new TextField();
		tf_twitter.setImmediate(false);
		tf_twitter.setWidth("100.0%");
		tf_twitter.setHeight("-1px");
		gridLayout_1.addComponent(tf_twitter, 1, 11);
		
		// l_descripcion
		l_descripcion = new Label();
		l_descripcion.setImmediate(false);
		l_descripcion.setWidth("-1px");
		l_descripcion.setHeight("-1px");
		l_descripcion.setValue("Descripción:");
		gridLayout_1.addComponent(l_descripcion, 0, 12);
		
		// ta_descripcion
		ta_descripcion = new TextArea();
		ta_descripcion.setImmediate(false);
		ta_descripcion.setWidth("100.0%");
		ta_descripcion.setHeight("60px");
		gridLayout_1.addComponent(ta_descripcion, 1, 12);
		
		return gridLayout_1;
	}

}
