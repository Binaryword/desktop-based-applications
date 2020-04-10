package message;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;

public class MessageBox {

	private static  Scene scene ; 
	private static Stage stage ; 
	private static boolean option ; 
	private static double offsetX ; 
	private static double offsetY ; 
	static Label lbl_title ; 
	static TextField contactName ; 
	static TextField contactNumber ; 
	
	 
	public static boolean showAlertBox(Message message) {
		option = true ; 
		stage =  new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); 
		
		
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(3); 
		shadow.setOffsetY(3);
		
		
		Label lbl_message =  new Label(); 
		 lbl_title = new Label(); 
		Button btn_ok = new Button(); 
		Button btn_cancel = new Button(); 
		btn_ok.setEffect(shadow);
		btn_ok.setId("alertButton"); 
		btn_cancel.setEffect(shadow);
		
		btn_ok.setPrefSize(150, 30); 
		btn_cancel.setPrefSize(150, 30);
		
		
		
		lbl_message.setStyle("-fx-text-fill:black");
		lbl_message.setFont(new Font("serif" , 15));
		
		//setting the controls 
		lbl_message.setText(message.getMessage()); 
		lbl_title.setText(message.getTitle()); 
		lbl_title.setTextFill(Color.DARKBLUE);
		btn_ok.setText("Ok"); 
		btn_cancel.setText("Ignore");
		
		
		//
		btn_ok.setOnAction(e->{
			
			System.out.println("true");
			option = true  ; 
			stage.close(); 
		});
		
		
		
		
		HBox buttonBox = new HBox(20); 
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10));
		buttonBox.getChildren().addAll(btn_ok);
		
		VBox middleLayout = new VBox(5); 
		middleLayout.setAlignment(Pos.CENTER); 
		middleLayout.getChildren().addAll(lbl_message , buttonBox); 
		
		
		BorderPane mainLayout = new BorderPane(); 
		mainLayout.setEffect(new DropShadow()); 
		mainLayout.setCenter(middleLayout); 
		mainLayout.setTop(menuBar()); 
		mainLayout.setStyle("-fx-background-color:white ; -fx-border-color:black ; -fx-border-width:1px" );
		
		
		scene = new Scene(mainLayout , 300 , 170);
		stage.setScene(scene); 
		
		scene.setOnMousePressed(e->handleMousePressed(e)); 
		scene.setOnMouseDragged(e->handleMouseDragged(e));
		scene.getStylesheets().addAll("theme.css"); 
		stage.initStyle(StageStyle.UNDECORATED); 
		stage.showAndWait(); 
		
		
		return option ; 
		
		
	}
	
	
public static boolean showMessageBox(Message message) {
		
		option = false ; 
		stage =  new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); 
		
		
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(3); 
		shadow.setOffsetY(3);
		
		contactName  = new TextField(); 
		contactNumber = new TextField(); 
		
		Label lbl_message =  new Label(); 
		lbl_title = new Label(); 
		Button btn_ok = new Button(); 
		Button btn_cancel = new Button(); 
		
		btn_ok.setPrefSize(100, 30); 
		btn_cancel.setPrefSize(100, 30); 
		
		btn_ok.setId("alertButton"); 
		btn_cancel.setId("alertButton"); 
		
		btn_ok.setEffect(shadow);
		btn_cancel.setEffect(shadow);
		
		lbl_message.setStyle("-fx-text-fill:black");
		lbl_message.setFont(new Font("serif" , 18));
		
		//setting the controls 
		lbl_message.setText(message.getMessage()); 
		lbl_title.setText(message.getTitle()); 
		lbl_title.setTextFill(Color.DARKBLUE);
		btn_ok.setText("Yes"); 
		btn_cancel.setText("No");
		
		
		//
		btn_ok.setOnAction(e->{
			
			System.out.println("true");
			option = true  ; 
			stage.close(); 
		});
		
		btn_cancel.setOnAction(e->{
			
			System.out.println("false");
			option = false; 
			stage.close(); 
		});
		
		HBox buttonBox = new HBox(20); 
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10));
		buttonBox.getChildren().addAll(btn_ok , btn_cancel);
		
		VBox middleLayout = new VBox(5); 
		middleLayout.setAlignment(Pos.CENTER); 
		middleLayout.getChildren().addAll(lbl_message , buttonBox); 
		
		
		BorderPane mainLayout = new BorderPane(); 
		mainLayout.setCenter(middleLayout); 
		mainLayout.setTop(menuBar()); 
		mainLayout.setStyle("-fx-background-color:white ; -fx-border-color:black ; -fx-border-width:2px " );
		
		scene = new Scene(mainLayout , 300 , 170);
		stage.setScene(scene); 
		scene.setOnMousePressed(e->handleMousePressed(e)); 
		scene.setOnMouseDragged(e->handleMouseDragged(e));
		scene.getStylesheets().addAll("theme.css"); 
		stage.initStyle(StageStyle.UNDECORATED); 
		stage.showAndWait();
		
		
		return option ; 
		
		
	}
	
    public static HBox menuBar( ){
    	
    	Stop[] stop = new Stop[]{new Stop(0 , Color.web("#FF0000"))  , new Stop(1 , Color.web("#EE0000"))};
		LinearGradient lg = new LinearGradient(0 , 0 , 0 , 1 , true , CycleMethod.NO_CYCLE , stop);
			
		Circle closeButton = new Circle(10); 
		closeButton.setStroke(Color.GRAY);
		closeButton.setStrokeWidth(2);
		closeButton.setFill(lg); 
		closeButton.setOnMouseClicked(e->stage.close());
		
		Stop[] stop1 = new Stop[]{new Stop(0 , Color.web("#FFD700"))  , new Stop(1 , Color.web("#EEC900"))};
		LinearGradient lg1 = new LinearGradient(0 , 0 , 0 , 1 , true , CycleMethod.NO_CYCLE , stop1);
		
		Circle minimizeButton = new Circle(10); 
		minimizeButton.setStroke(Color.GRAY);
		minimizeButton.setStrokeWidth(2);
		minimizeButton.setFill(lg1); 
		minimizeButton.setOnMouseClicked(e->stage.close());


		Region region = new Region();
		lbl_title.setStyle("-fx-text-fill:#030303");
		
		HBox box = new HBox(10);
		box.setPadding(new Insets(10)); 
		box.getChildren().addAll(lbl_title , region , minimizeButton , closeButton); 
		HBox.setHgrow(region, Priority.ALWAYS); 
		
		box.setStyle("-fx-background-color:linear-gradient(#C4C4C4,#9E9E9E); -fx-border-width:1px ; -fx-border-color:gray");
		
		return box ; 
	 
	}
	
	public static void handleMousePressed(MouseEvent e){
	
	
		offsetX =  e.getScreenX() -  stage.getX() ; 
		offsetY =  e.getScreenY() - stage.getY() ; 
	}
	
	public static void handleMouseDragged(MouseEvent e){
	
		stage.setX(e.getScreenX() - offsetX); 
		stage.setY(e.getScreenY() - offsetY);
	}
	
	
	
	
	}
