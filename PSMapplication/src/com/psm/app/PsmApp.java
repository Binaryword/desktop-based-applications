 package com.psm.app;

import com.psm.model.Preferences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PsmApp extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);


	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		Parent root =  FXMLLoader.load(getClass().getResource("/com/psm/front_design/login_design.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Praise Fountain Login");
		primaryStage.setScene(scene);
		primaryStage.show();
		Preferences.getConfig(); 

	}




}
