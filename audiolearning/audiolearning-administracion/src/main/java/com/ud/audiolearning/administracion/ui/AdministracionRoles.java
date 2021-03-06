package com.ud.audiolearning.administracion.ui;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.teemu.VaadinIcons;

import com.ud.audiolearning.administracion.service.AdministracionService;
import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.domain.Rol;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@UIComponent
@VaadinView("Administracion/Roles")
public class AdministracionRoles extends CustomComponent implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Table table_1;
	@AutoGenerated
	private HorizontalLayout hl_cabecera;
	@AutoGenerated
	private Button b_addRol;
	@AutoGenerated
	private Label l_titulo;
	private BeanItemContainer<Rol> BICRoles;

	@Autowired
	AdministracionService administracionService;

	public AdministracionRoles() {

	}

	@Override
	public void enter(ViewChangeEvent event) {

		AudioLearnUI audioLearning = (AudioLearnUI) UI.getCurrent();
		audioLearning.getPrivateUI().getAreaContenido().setHeight("100%");
		buildMainLayout();
		setCompositionRoot(mainLayout);
		init();
		style();
	}

	private void init() {

		BICRoles = new BeanItemContainer<>(Rol.class);
		table_1.setContainerDataSource(BICRoles);
		table_1.addGeneratedColumn("Imagen", this::construirImagen);
		table_1.addGeneratedColumn("Admin", this::construirAdmin);
		table_1.setVisibleColumns("Imagen", "nombre", "descripcion", "Admin");
		table_1.setColumnWidth("Imagen", 90);
		table_1.setColumnAlignment("Imagen", Align.CENTER);
		table_1.setColumnAlignment("Admin", Align.CENTER);

		b_addRol.addClickListener(e-> gestionRol(null));
		llenarRoles();
	}

	private void llenarRoles() {

		BICRoles.removeAllItems();
		BICRoles.addAll(administracionService.listadoRoles());
	}

	private Object construirImagen(Table source, Object itemId, Object columnId) {
		Image imagen = new Image();
		imagen.setSource(new ThemeResource("img/rol-icon.png"));

		return imagen;

	}

	private Object construirAdmin(Table source, Object itemId, Object columnId) {
		Rol rol = (Rol) itemId;
		HorizontalLayout hl = new HorizontalLayout();
		hl.addStyleName("botonesMultimedia");
		hl.setSpacing(true);
		hl.setWidth("-1px");

		Button removeRol = new Button();
		removeRol.addClickListener(e -> removeRol(rol));
		removeRol.setIcon(VaadinIcons.TRASH);

		Button editRol = new Button();
		editRol.setIcon(VaadinIcons.EDIT);
		editRol.addClickListener(e -> gestionRol(rol));

		hl.addComponents(editRol, removeRol);

		return hl;

	}

	private void gestionRol(Rol rol) {
		Window w = new Window();
		w.setContent(new ViewRol(rol, administracionService));
		w.setWidth("-1px");
		w.setHeight("-1px");
		w.center();
		w.setModal(true);
		UI.getCurrent().addWindow(w);
		
	}

	private void removeRol(Rol rol) {
		ConfirmDialog.show(UI.getCurrent(), "Remover Rol ",
				"Esta seguro que desea remover el rol seleccionado ", "Remover", "No Remover",
				new ConfirmDialog.Listener() {

					public void onClose(ConfirmDialog dialog) {

						if (dialog.isConfirmed()) {

							Notification n = new Notification("Correcto!", "Se ha eliminado con exito el registro",
									Type.HUMANIZED_MESSAGE);
							n.setDelayMsec(3600);
							n.show(UI.getCurrent().getPage());
						} else {
						}

					}
				});

	}

	

	private void style() {

		l_titulo.addStyleName(ValoTheme.LABEL_COLORED);
		l_titulo.addStyleName(ValoTheme.LABEL_H2);
		l_titulo.addStyleName(ValoTheme.LABEL_BOLD);
		table_1.addStyleName("orangeHeader");
		table_1.addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
		b_addRol.addStyleName(ValoTheme.BUTTON_PRIMARY);
		b_addRol.setIcon(VaadinIcons.PLUS);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setStyleName("contenido");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// hl_cabecera
		hl_cabecera = buildHl_cabecera();
		mainLayout.addComponent(hl_cabecera);
		
		// table_1
		table_1 = new Table();
		table_1.setImmediate(false);
		table_1.setWidth("100.0%");
		table_1.setHeight("100.0%");
		mainLayout.addComponent(table_1);
		mainLayout.setExpandRatio(table_1, 1.0f);
		
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
		l_titulo.setStyleName("mih2");
		l_titulo.setImmediate(false);
		l_titulo.setWidth("-1px");
		l_titulo.setHeight("-1px");
		l_titulo.setValue("Administracion de Roles");
		hl_cabecera.addComponent(l_titulo);
		
		// b_addRol
		b_addRol = new Button();
		b_addRol.setCaption("Crear Nuevo Rol");
		b_addRol.setImmediate(true);
		b_addRol.setWidth("-1px");
		b_addRol.setHeight("-1px");
		hl_cabecera.addComponent(b_addRol);
		hl_cabecera.setComponentAlignment(b_addRol, new Alignment(6));
		
		return hl_cabecera;
	}

}
