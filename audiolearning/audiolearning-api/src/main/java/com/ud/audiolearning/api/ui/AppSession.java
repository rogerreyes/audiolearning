package com.ud.audiolearning.api.ui;


import com.ud.audiolearning.api.domain.CriterioBusqueda;
import com.ud.audiolearning.api.domain.Usuario;
import com.vaadin.server.VaadinSession;

public class AppSession {


	private static final String USERNAME = "username";
	private static final String CRITERIO_BUSQUEDA = "busqueda";


	
	public static Usuario getUser() {
		return  (Usuario) VaadinSession.getCurrent().getAttribute(USERNAME);
	}
	
	public static void setUser(Object username) {
		VaadinSession.getCurrent().setAttribute(USERNAME, username);
	}
	
	
	public static  CriterioBusqueda getCriterioBusqueda(){
		return (CriterioBusqueda) VaadinSession.getCurrent().getAttribute(CRITERIO_BUSQUEDA);
		
		
	}
	
	public static void  setCriterioBusqueda(Object busqueda){
		VaadinSession.getCurrent().setAttribute(CRITERIO_BUSQUEDA, busqueda);
	}
	
}
