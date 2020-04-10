package com.binary.menubar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Test extends Application{


	public static void main(String arg[]){
		
		launch();
	}
	
	public void start(Stage stage){
		
		Menubar menubar  = new Menubar(true , true);
		menubar.setTitle("Menu");
	
		
		BorderPane b = new BorderPane();
		b.setTop(menubar.setMenuBar(stage));
		
		Scene scene = new Scene(b, 500 , 500);
		scene.setOnMouseDragged(e->menubar.handleMouseDragged(e));
		scene.setOnMousePressed(e->menubar.handleMousePressed(e));
		stage.setScene(scene);
		
		stage.show();
		
	}
	
	
	
}
