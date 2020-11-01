package com.eq.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class DebugController implements Initializable{

	 @FXML
	    private JFXTextArea infoPanel;

	    @FXML
	    private Text headerText;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


	public void setContent(String string) {
		// TODO Auto-generated method stub
		infoPanel.setText(infoPanel.getText()  + " " + string);
	}

}
