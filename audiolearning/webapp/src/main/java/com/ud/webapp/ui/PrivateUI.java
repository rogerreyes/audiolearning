package com.ud.webapp.ui;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemu.VaadinIcons;

import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.domain.ALItem;
import com.ud.audiolearning.api.domain.ALMenuItem;
import com.ud.audiolearning.api.domain.CriterioBusqueda;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.ud.audiolearning.api.interfaces.PrivateUILayout;
import com.ud.audiolearning.api.service.ApiService;
import com.ud.audiolearning.api.ui.AppSession;
import com.ud.audiolearning.busquedas.ui.ViewBusquedaAvanzada;
import com.ud.audiolearning.seguridad.listener.MenuListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@UIComponent
public class PrivateUI extends VerticalLayout implements PrivateUILayout {

	private VerticalLayout areaContenido = new VerticalLayout();
	private Button b_busqueda;
	private Button b_avanzada;
	private TextField tf_busqueda;
	private MenuBar menuUsuario;
	private MenuBar menuAplicacion;

	@Autowired
	MenuListener menuListener;

	@Autowired
	ApiService apiService;

	private final Command menuCommand = new Command() {
		@Override
		public void menuSelected(final MenuItem selectedItem) {
			if (selectedItem.getText().equals("Salir")) {
				logout();
			}
			if (selectedItem.getText().equals("Perfil y Preferencias")) {
				UI.getCurrent().getNavigator().navigateTo("perfilUsuario");
			}
			if (selectedItem.getText().equals("Preferencias")) {
				UI.getCurrent().getNavigator().navigateTo("preferencias");
			}
		}
	};

	private final Command menuAppCommand = new Command() {
		@Override
		public void menuSelected(MenuItem selectedItem) {
			menuListener.seleccionItem(selectedItem.getText());
		}
	};

	public PrivateUI() {
		setSizeFull();

		HorizontalLayout cabecera = construirCabecera();
		cabecera.setId("cabecera");
		cabecera.setWidth("100%");
		cabecera.setMargin(new MarginInfo(false, true, false, false));
		Component menu = construirMenu();
		menu.setId("myMenuBar");
		Panel cuerpo = new Panel();
		cuerpo.setContent(construirCuerpo());
		cuerpo.setSizeFull();
		// cuerpo.addStyleName(ValoTheme.LAYOUT_CARD);
		cuerpo.addStyleName("panelCuerpo");
		addComponents(cabecera, menu, cuerpo);
		setExpandRatio(cuerpo, 1);

	}

	private Component construirCuerpo() {
		areaContenido.addStyleName("panelContenido");
		return areaContenido;
	}

	private HorizontalLayout construirCabecera() {
		tf_busqueda = new TextField();
		tf_busqueda.setWidth("500px");
		b_busqueda = new Button();
		b_busqueda.setIcon(FontAwesome.SEARCH);
		b_busqueda.setStyleName(ValoTheme.BUTTON_PRIMARY);
		b_busqueda.addClickListener(e -> buscar());

		b_avanzada = new Button();
		b_avanzada.setIcon(FontAwesome.GEARS);
		b_avanzada.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		b_avanzada.addClickListener(e -> avanzada());

		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
		Image logo = new Image(null, resource);

		GridLayout headerContent = new GridLayout();
		headerContent.setColumnExpandRatio(1, 1.0f);
		headerContent.setColumns(3);
		headerContent.setWidth("100%");

		headerContent.addComponent(logo);
		Component usuarioMenu = addUsuario("" + AppSession.getUser());

		HorizontalLayout busquedaLayout = new HorizontalLayout();
		busquedaLayout.addComponent(tf_busqueda);
		busquedaLayout.addComponent(b_busqueda);
		busquedaLayout.addComponent(b_avanzada);
		busquedaLayout.setSpacing(true);

		headerContent.addComponent(busquedaLayout);
		headerContent.addComponent(usuarioMenu);
		headerContent.setComponentAlignment(busquedaLayout, Alignment.MIDDLE_CENTER);
		headerContent.setComponentAlignment(usuarioMenu, Alignment.MIDDLE_RIGHT);
		headerContent.setMargin(new MarginInfo(false, true, false, false));
		return new HorizontalLayout(headerContent);
	}

	private Component addUsuario(String nombreUsuario) {
		menuUsuario = new MenuBar();
		MenuItem settingsItem = menuUsuario.addItem(nombreUsuario, null);
		settingsItem.setIcon(VaadinIcons.HANDS_UP);
		settingsItem.addItem("Perfil y Preferencias", menuCommand);
		settingsItem.addSeparator();
		settingsItem.addItem("Salir", FontAwesome.SIGN_OUT, menuCommand);
		menuUsuario.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		menuUsuario.addStyleName("menuUsuario");
		return menuUsuario;
	}

	private void avanzada() {

		Window w = new Window("Busqueda Avanzada");
		w.setContent(new ViewBusquedaAvanzada(apiService));
		w.setWidth("700px");
		w.setModal(true);
		w.center();
		UI.getCurrent().addWindow(w);

	}

	private void buscar() {

		String valorBusqueda = tf_busqueda.getValue().trim();
		if (!valorBusqueda.equals("")) {
			CriterioBusqueda criterioBusqueda = new CriterioBusqueda();
			criterioBusqueda.setTipo("B");
			criterioBusqueda.setQuery(valorBusqueda);
			AppSession.setCriterioBusqueda(criterioBusqueda);
			UI.getCurrent().getNavigator().navigateTo("Busqueda");
		} else {
			Notification.show("Busqueda!", "Debe ingresar un criterio de busqueda!", Type.WARNING_MESSAGE);
		}

	}

	private Component construirMenu() {

		menuAplicacion = new MenuBar();

		menuAplicacion.setWidth("100%");

		menuAplicacion.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

		return new VerticalLayout(menuAplicacion);

	}

	@Override
	public VerticalLayout getAreaContenido() {
		return areaContenido;
	}

	@Override
	public void setNombreUsuarioSession(String nombreUsuario) {
		menuUsuario.getItems().get(0).setText(nombreUsuario);

	}

	private void logout() {
		AudioLearnUI audioLearnUI = (AudioLearnUI) UI.getCurrent();
		audioLearnUI.showLoginUI();
	}

	@Override
	public void addMenuItem(Object menuItem) {
		ALMenuItem itemMenu = (ALMenuItem) menuItem;

		MenuItem MenuItem = menuAplicacion.addItem(itemMenu.getNombre(), VaadinIcons.MUSIC, null);
		if (itemMenu.isNavegable()) {
			MenuItem.setCommand(menuAppCommand);
		}

		for (ALItem item : itemMenu.getItems()) {
			MenuItem SubMenuItem = MenuItem.addItem(item.getNombre(), null);
			if (item.isNavegable()) {
				SubMenuItem.setCommand(menuAppCommand);
			}
		}

	}

}
