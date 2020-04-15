package com.psm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.binary.alert.Alert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.psm.model.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

		@FXML
		private AnchorPane root ;

	 	@FXML
	    private JFXTextField txt_username;

	    @FXML
	    private JFXPasswordField txt_password;

	    @FXML
	    private JFXButton btn_login;

	    @FXML
	    private JFXCheckBox check_rember_mi;
	    
	    private Preferences preferences  ; 

	    private String USER_NAME ;
	    private String PASSWORD ;
	  


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		 preferences = Preferences.getConfig() ; 
		 txt_username.setText(preferences.getAdmin_username().toLowerCase());
		 txt_username.setFocusTraversable(false);
		 
    	 USER_NAME = preferences.getAdmin_username().trim().toLowerCase() ; 
    	 PASSWORD = preferences.getAdmin_password().trim().toLowerCase() ; 

	}

     @FXML void activateLoginUser(ActionEvent event) {

    	 System.out.println("login user");
    	 
    	 USER_NAME = txt_username.getText().toLowerCase() ;
    	 PASSWORD = txt_password.getText().toLowerCase() ;
    
    	 
    	 if(USER_NAME.equals(txt_username.getText().toLowerCase()) && PASSWORD.equals(txt_password.getText().toLowerCase())){

    		 // close login
    		 Stage window = (Stage)root.getScene().getWindow() ;
    		 window.close();

    		    Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("/com/psm/front_design/menu_design.fxml"));
					Scene scene = new Scene(root , 1000 , 650);
	    			Stage stage  = new Stage();
	    			stage.setTitle("School Management System");
	    			stage.setScene(scene);
	    			stage.show();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    	 }else{

    		
    		 txt_username.getStyleClass().add("wrong-credentials");
    		 txt_password.getStyleClass().add("wrong-credentials");

    	 }// wrong password

     }

 


}
