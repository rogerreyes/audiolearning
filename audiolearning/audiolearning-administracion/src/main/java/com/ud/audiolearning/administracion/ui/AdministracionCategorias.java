package com.ud.audiolearning.administracion.ui;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.teemu.VaadinIcons;

import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.domain.Categoria;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.ud.audiolearning.api.service.ApiService;
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
@VaadinView("Administracion/Categorias")
public class AdministracionCategorias extends CustomComponent implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Table table_categorias;
	@AutoGenerated
	private HorizontalLayout hl_cabecera;
	@AutoGenerated
	private Button b_addCategoria;
	@AutoGenerated
	private Label l_titulo;
	@Autowired
	private ApiService apiService;

	private BeanItemContainer<Categoria> BICCategorias;

	public AdministracionCategorias() {

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

		BICCategorias = new BeanItemContainer<>(Categoria.class);
		table_categorias.setContainerDataSource(BICCategorias);
		table_categorias.addGeneratedColumn("Imagen", this::construirImagen);
		table_categorias.addGeneratedColumn("Admin", this::construirAdmin);
		table_categorias.setVisibleColumns("Imagen", "codigo", "nombre", "descripcion", "Admin");
		table_categorias.setColumnWidth("Imagen", 90);
		table_categorias.setColumnAlignment("Admin", Align.CENTER);
		table_categorias.setColumnAlignment("Imagen", Align.CENTER);
		b_addCategoria.addClickListener(e-> viewAdminCategoria(null));

		llenarCategorias();

	}

	private void llenarCategorias() {

		BICCategorias.removeAllItems();
		BICCategorias.addAll(apiService.findAllCategorias());
	}

	private Object construirImagen(Table source, Object itemId, Object columnId) {
		Image imagen = new Image();
		imagen.setSource(new ThemeResource("img/categoria-icon.png"));
		return imagen;
	}

	private Object construirAdmin(Table source, Object itemId, Object columnId) {

		Categoria categoria = (Categoria) itemId;
		HorizontalLayout hl = new HorizontalLayout();
		hl.addStyleName("botonesMultimedia");
		hl.setSpacing(true);
		hl.setWidth("-1px");

		Button removeCategoria = new Button();
		removeCategoria.addClickListener(e -> removeCategoria(categoria));
		removeCategoria.setIcon(VaadinIcons.TRASH);

		Button editCategoria = new Button();
		editCategoria.setIcon(VaadinIcons.EDIT);
		editCategoria.addClickListener(e -> viewAdminCategoria(categoria));

		hl.addComponents(editCategoria, removeCategoria);

		return hl;
	}

	private void viewAdminCategoria(Categoria categoria) {

		Window w = new Window();
		w.setContent(new ViewCategoria(categoria, apiService, consumerCategoria));
		w.setWidth("-1px");
		w.setHeight("-1px");
		w.center();
		w.setModal(true);
		UI.getCurrent().addWindow(w);
	}

	public Consumer<String> consumerCategoria = (valor) -> llenarCategorias();
	
	private void removeCategoria(Categoria categoria) {
		
		
		ConfirmDialog.show(UI.getCurrent(), "Remover Categoria ", "Esta seguro que desea remover la Categoria seleccionada ", "Remover",
				"No Remover", new ConfirmDialog.Listener() {

					public void onClose(ConfirmDialog dialog) {
						
						
						if (dialog.isConfirmed()) {

							Notification n = new Notification("Correcto!", "Se ha eliminado con exito el registro",
									Type.HUMANIZED_MESSAGE);
							n.setDelayMsec(3600);
							n.show(UI.getCurrent().getPage());
						} else {
						}
						
					}});
		
		
	}

	private void style() {

		l_titulo.addStyleName(ValoTheme.LABEL_COLORED);
		l_titulo.addStyleName(ValoTheme.LABEL_H2);
		l_titulo.addStyleName(ValoTheme.LABEL_BOLD);
		table_categorias.addStyleName("orangeHeader");
		table_categorias.addStyleName(ValoTheme.TABLE_NO_STRIPES);
		table_categorias.addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
		b_addCategoria.addStyleName(ValoTheme.BUTTON_PRIMARY);
		b_addCategoria.setIcon(VaadinIcons.PLUS);
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
		
		// table_categorias
		table_categorias = new Table();
		table_categorias.setImmediate(false);
		table_categorias.setWidth("100.0%");
		table_categorias.setHeight("100.0%");
		mainLayout.addComponent(table_categorias);
		mainLayout.setExpandRatio(table_categorias, 1.0f);
		
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
		l_titulo.setValue("Administración de Categorias");
		hl_cabecera.addComponent(l_titulo);
		
		// b_addCategoria
		b_addCategoria = new Button();
		b_addCategoria.setCaption("Agregar Nueva Categoria");
		b_addCategoria.setImmediate(true);
		b_addCategoria.setWidth("-1px");
		b_addCategoria.setHeight("-1px");
		hl_cabecera.addComponent(b_addCategoria);
		hl_cabecera.setComponentAlignment(b_addCategoria, new Alignment(6));
		
		return hl_cabecera;
	}

}