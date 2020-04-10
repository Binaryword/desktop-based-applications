package phonebook;

import database.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{
	
	private static Scene loginScene ; 
	private static Scene menuScene ; 
	private static Stage window ; 
	private double offsetX ; 
	private double offsetY ; 
	
	

	public static void main(String[] args) {
		
		Application.launch(args);

	}
	
	public void start(Stage stage){
		
		window = stage ; 
		
		Database database = new Database(); 
		ContactTable contactTable = new ContactTable();
		loginGraphic login =  new loginGraphic(); 
		menuGraphic menu = new menuGraphic(contactTable); 
		
		//login
		loginScene = new Scene(login , 400 , 600);
	//	loginScene.setOnMousePressed(e->handleMousePressed(e));
	//	loginScene.setOnMouseDragged(e->handleMouseDragged(e));
		loginScene.getStylesheets().addAll("theme.css");
		
		//menu
		menuScene = new Scene(menu , 400 , 600);
		menuScene.setOnMousePressed(e->handleMousePressed(e));
		menuScene.setOnMouseDragged(e->handleMouseDragged(e));
		menuScene.getStylesheets().addAll("theme.css");
		
		window.setScene(loginScene); 
		window.initStyle(StageStyle.UNDECORATED); 
		window.show();
		
		
		//controllers 
		MenuController menuController = new MenuController(menu , contactTable , database); 
		
		
	}
	
	
	
	public void handleMousePressed(MouseEvent event){
		
		offsetX = event.getScreenX() - window.getX() ; 
		offsetY = event.getScreenY() - window.getY(); 
	}
	
	public void handleMouseDragged(MouseEvent event){
		
		window.setX(event.getScreenX() - offsetX); 
		window.setY(event.getScreenY() - offsetY);
	}
	
	

	public static void setLogInScene(){
		window.setScene(loginScene); 
	}
	
	public static void setMenuScene(){
		window.setScene(menuScene); 
	}


	public static Stage getStage(){
		return window ; 
	}

}
