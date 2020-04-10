package com.binary.menubar;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Menubar {

	private Stage stage ;
	private boolean isClosedAdd ;
	private boolean isMinimizeAdd ;
	private String title ;
	private Text text ;
	private HBox box ;
	private double offsetX ; 
	private double offsetY ; 

	public Menubar(){


	}

	public Menubar(boolean addCloseButton , boolean addMinimizeButton){

		this.isClosedAdd = addCloseButton ;
		this.isMinimizeAdd = addMinimizeButton ;

	}


	public Pane setMenuBar(Stage stage){

		this.stage = stage ;
		stage.initStyle(StageStyle.UNDECORATED);
		//stage.getScene().setOnMousePressed(e->handleMousePressed(e));
		//stage.getScene().setOnMouseDragged(e->handleMouseDragged(e));
		
		text = new Text();
		text.setStyle("-fx-fill:white ");

		Stop[] stop = new Stop[]{new Stop(0 , Color.web("#FF0000"))  , new Stop(1 , Color.web("#EE0000"))};
		LinearGradient lg = new LinearGradient(0 , 0 , 0 , 1 , true , CycleMethod.NO_CYCLE , stop);

		Circle closeButton = new Circle(10);
		closeButton.setStroke(Color.BLACK);
		closeButton.setStrokeWidth(2);
		closeButton.setFill(lg);
		closeButton.setOnMouseClicked(e->Platform.exit());

		Stop[] stop1 = new Stop[]{new Stop(0 , Color.web("#FFD700"))  , new Stop(1 , Color.web("#EEC900"))};
		LinearGradient lg1 = new LinearGradient(0 , 0 , 0 , 1 , true , CycleMethod.NO_CYCLE , stop1);


		Circle minimizeButton = new Circle(10);
		minimizeButton.setStroke(Color.BLACK);
		minimizeButton.setStrokeWidth(2);
		minimizeButton.setFill(lg1);
		minimizeButton.setOnMouseClicked(e->stage.setIconified(true));


		Region region = new Region();

		box = new HBox(10);
		box.setPadding(new Insets(10));

		if(!(title == "" || title == null)){
			text.setText(title);
			box.getChildren().add(text);
		}


		box.getChildren().add(region);
		
		if(isMinimizeAdd())
			box.getChildren().add(minimizeButton);



		if(isClosedAdd())
			box.getChildren().add(closeButton);

		
		HBox.setHgrow(region, Priority.ALWAYS);
		box.setStyle("-fx-background-color:linear-gradient(#5E5E5E,#212121); -fx-border-width:1px ; -fx-border-color:gray");

		return box ;
	}


	

	public void handleMousePressed(MouseEvent event){
		
		offsetX = event.getScreenX() - stage.getX() ; 
		offsetY = event.getScreenY() - stage.getY(); 
	}
	
	public void handleMouseDragged(MouseEvent event){
		
		stage.setX(event.getScreenX() - offsetX); 
		stage.setY(event.getScreenY() - offsetY);
	}
	

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public boolean isClosedAdd() {
		return isClosedAdd;
	}

	public void setClosedAdd(boolean isClosedAdd) {
		this.isClosedAdd = isClosedAdd;
	}

	public boolean isMinimizeAdd() {
		return isMinimizeAdd;
	}

	public void setMinimizeAdd(boolean isMinimizeAdd) {
		this.isMinimizeAdd = isMinimizeAdd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public HBox getLayout(){

		return box ;
	}


}
