package com.ud.audiolearning.api.interfaces;


import com.vaadin.ui.ComponentContainer;

public interface PrivateUILayout {

	
	ComponentContainer getAreaContenido();


	void setNombreUsuarioSession(String username);


	void addMenuItem(Object menuItem);
}
