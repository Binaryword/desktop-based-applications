package windowbar;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class WindowBar extends HBox {

	private StringProperty title;
	private Text text_title ; 
	private Path closeButton;
	private Path minimizeButton;
	private Stage window ; 
	private Region region = new Region(); 

	public WindowBar(Stage stage) {
		this("Title" , stage);
	}

	public WindowBar(String title , Stage window) {
		
		this.window =  window ; 
		this.title = new SimpleStringProperty(this, "title", title);
		this.text_title = new Text(title);
		this.text_title.setFont(Font.font("arial" , 18)); 
	
		createMinimizeButton(); 
		createCloseButton(); 
		handleCloseRequest(); 
		handleMinimizedRequest(); 
		
		//setting layout property 
		this.setSpacing(10); 
		HBox.setHgrow(region, Priority.ALWAYS); 
		this.setPadding(new Insets(5));
		this.setAlignment(Pos.CENTER); 
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
		this.text_title.setText(title); 
	}

	public StringProperty titleProperty() {
		return this.title;
	}
	
	public Text getTextTitle(){
		return text_title ; 
	}

	protected void createCloseButton() {

		closeButton = new Path(new MoveTo(0, 0), new LineTo(16,16),
				new ClosePath(), new MoveTo(16, 0), new LineTo(0, 16));

		closeButton.setStrokeWidth(4);
		closeButton.setStrokeLineCap(StrokeLineCap.ROUND);
		closeButton.setStroke(Color.BLACK);

	}
	
	public Path getCloseButton(){
		return closeButton ; 
	}
	
	protected void createMinimizeButton() {

		minimizeButton = new Path(new MoveTo(0, 0), new LineTo(20, 0));

		minimizeButton.setStrokeWidth(4);
		minimizeButton.setStrokeLineCap(StrokeLineCap.ROUND);
		minimizeButton.setStroke(Color.BLACK);

	}
	
	public Path getMinimizeButton(){
		return minimizeButton; 
	}
	
	
	
	public void setBackgroundColor(String style){
		
		this.setStyle(style); 
	}
	
	public void handleCloseRequest(){
		
		closeButton.setOnMouseClicked(e->Platform.exit()); 
	}
	
	public void handleMinimizedRequest(){
		
		minimizeButton.setOnMouseClicked(e->window.setIconified(true));
	}
	
	public void setWindowBar(Window w){
		
		Window window = w ; 
		
		switch(window){
		
		case CLOSE:
					this.getChildren().addAll(text_title , region , getCloseButton()); 
			break;
			
		case MINIMIZE:
					this.getChildren().addAll(text_title , region , getMinimizeButton()); 
			break;
			
		case MINIMIZE_CLOSE :
					this.getChildren().addAll(text_title , region , getMinimizeButton() , getCloseButton());
			break ; 
			
		default:
			
			this.getChildren().addAll(text_title , region , getMinimizeButton() , getCloseButton());
			
			break;
		
		}//end of switch.....
	}
	
	
	/**
	 * package windowbar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Test extends Application{

	public static void main(String[] args) {
		
			launch();
	}
	
	
	public void start(Stage stage){
		
		BorderPane pane = new BorderPane(); 
		
		WindowBar bar = new WindowBar("title" , stage);
		bar.setWindowBar(Window.MINIMIZE_CLOSE); 
		bar.setBackgroundColor("-fx-background-color:linear-gradient(gray , lightgray)"); 
		bar.getCloseButton().setStroke(Color.YELLOW); 
		
		pane.setTop(bar); 
		
		pane.setStyle("-fx-border-color:black; -fx-border-width:2px");
		
		Scene scene = new Scene(pane , 500 , 400);
		stage.setScene(scene); 
		stage.initStyle(StageStyle.UNDECORATED); 
		stage.show();
		
		
	}

}

	 * 
	 */
	
}
