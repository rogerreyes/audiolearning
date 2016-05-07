package com.ud.audiolearning.api.interfaces;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;

public interface AudioLearnUI {

	static interface UIChangeListener {

		void afterSwitchToPrivateUI();

		void afterSwitchToLoginUI();

	}

	PrivateUILayout getPrivateUI();

	Page getPage();

	Navigator getNavigator();

	void setNavigator(Navigator navigator);

	void showLoginUI();

	void showPrivateUI();

}
