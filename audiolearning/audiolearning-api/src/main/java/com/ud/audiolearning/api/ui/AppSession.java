package com.ud.audiolearning.api.ui;



import com.ud.audiolearning.api.domain.Usuario;
import com.vaadin.server.VaadinSession;

public class AppSession {


	private static final String USERNAME = "username";


	
	public static Usuario getUser() {
		return  (Usuario) VaadinSession.getCurrent().getAttribute(USERNAME);
	}
	
	public static void setUser(Object username) {
		VaadinSession.getCurrent().setAttribute(USERNAME, username);
	}
	
	
}
