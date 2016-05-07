package com.ud.webapp.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.ud.audiolearning.api.anotaciones.UIComponent;
import com.ud.audiolearning.api.interfaces.AudioLearnUI;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;


@UIComponent
@SuppressWarnings("serial")
@PreserveOnRefresh
@Theme("AudioLearningTheme")
@Widgetset("com.ud.webapp.ui.MyAppWidgetset")
public class AudioLearningUI extends UI implements AudioLearnUI{
	
	
	
	@Autowired
    private LoginUI loginUI;
	@Autowired
    private PrivateUI privateUI;
	
	@Autowired
	UIChangeListener UIChangeListener;


	@Override
    protected void init(VaadinRequest vaadinRequest) {
		//VaadinSession.getCurrent().setErrorHandler(new AudioErrorHandler());
		showLoginUI();
		getPage().setTitle("AudioLearning");

    }
    
    @Override
    public void showLoginUI() {
		setContent(loginUI);
		UIChangeListener.afterSwitchToLoginUI();
	}

    @Override
	public void showPrivateUI() {
		setContent(privateUI);
		UIChangeListener.afterSwitchToPrivateUI();
	}
    

	public PrivateUI getPrivateUI() {
		return privateUI;
	}


}
