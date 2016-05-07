package com.ud.audiolearning.api.interfaces;

import com.vaadin.ui.Component;

public interface LoginUILayout extends Component{
	
	static interface AudioLearningLoginListener{
		
		
		boolean login(String username, String password);
		
	}



}
