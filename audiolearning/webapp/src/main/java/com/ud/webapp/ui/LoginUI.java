package com.ud.webapp.ui;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.interfaces.LoginUILayout;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@UIComponent
public class LoginUI extends VerticalLayout implements LoginUILayout {

	private TextField tf_usuario;
	private PasswordField pf_password;
	private Button b_login;

	@Autowired
	AudioLearningLoginListener loginListener;

	public LoginUI() {

		setSizeFull();
		Component loginForm = buildLoginForm();
		loginForm.addStyleName("login");
		loginForm.addStyleName(ValoTheme.LAYOUT_CARD);
		VerticalLayout vltop = new VerticalLayout();
		vltop.addStyleName("loginTop");
		vltop.setHeight("100%");
		VerticalLayout vlbottom = new VerticalLayout();
		vlbottom.setHeight("100%");
		vlbottom.addStyleName("loginBottom");
		vlbottom.addComponent(loginForm);
		addComponents(vltop, vlbottom);
		vlbottom.setComponentAlignment(loginForm, Alignment.TOP_CENTER);
	}

	private Component buildLoginForm() {
		final VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.setSpacing(true);
		loginPanel.addComponent(buildFields());
		loginPanel.setMargin(true);
		return loginPanel;
	}

	private Component buildFields() {
		VerticalLayout fields = new VerticalLayout();
		VerticalLayout vlImagen = new VerticalLayout();
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo3.png"));

		Label l_titulo = new Label("AUDIOLEARNING");
		l_titulo.addStyleName(ValoTheme.LABEL_H3);
		l_titulo.addStyleName(ValoTheme.LABEL_COLORED);
		l_titulo.addStyleName(ValoTheme.LABEL_BOLD);
		
		Image logo = new Image(null, resource);
		vlImagen.addComponent(logo);
		vlImagen.setComponentAlignment(logo, Alignment.TOP_CENTER);
		vlImagen.addStyleName("loginImagen");
		
		tf_usuario = new TextField("Usuario");
		tf_usuario.setIcon(FontAwesome.USER);
		tf_usuario.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		tf_usuario.setWidth("100%");

		pf_password = new PasswordField("Contraseña");
		pf_password.setIcon(FontAwesome.LOCK);
		pf_password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		pf_password.setWidth("100%");

		b_login = new Button("Entrar");
		b_login.setWidth("100%");
		b_login.addStyleName(ValoTheme.BUTTON_PRIMARY);
		b_login.setClickShortcut(KeyCode.ENTER);
		b_login.focus();
		b_login.addClickListener(e -> login(e));
		
		fields.setWidth("230px");
		fields.setSpacing(true);
		fields.addComponent(vlImagen);
		fields.addComponent(l_titulo);
		fields.setComponentAlignment(l_titulo, Alignment.MIDDLE_CENTER);
		fields.addComponents(tf_usuario, pf_password, b_login);
		fields.setComponentAlignment(b_login, Alignment.BOTTOM_LEFT);

		return fields;
	}

	private void login(ClickEvent e) {

		if (tf_usuario.getValue() != null && !tf_usuario.getValue().isEmpty() && pf_password.getValue() != null
				&& !pf_password.getValue().isEmpty()
				&& loginListener.login(tf_usuario.getValue(), pf_password.getValue())) {
			AudioLearningUI audioLearningUI = (AudioLearningUI) UI.getCurrent();
			audioLearningUI.showPrivateUI();
		} else {
			Notification.show("Error!", "Credenciales Incorrectas", Type.ERROR_MESSAGE);
		}
	}



}
