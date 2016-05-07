package com.ud.webapp.config;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class AudioErrorHandler implements ErrorHandler {

	@Override
	public void error(ErrorEvent event) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		Notification notification = new Notification("Error!","Fecha: "+ currentDate + "<br> Se ha producido un error, consulte al administrador", Type.ERROR_MESSAGE);
		notification.setHtmlContentAllowed(true);
		notification.show(Page.getCurrent());
	}
}
