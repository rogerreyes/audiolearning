package com.ud.audiolearning.mislistas.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ViewAddAudio extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Table table_1;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ViewAddAudio() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// table_1
		table_1 = new Table();
		table_1.setImmediate(false);
		table_1.setWidth("100.0%");
		table_1.setHeight("-1px");
		mainLayout.addComponent(table_1);
		
		return mainLayout;
	}

}
