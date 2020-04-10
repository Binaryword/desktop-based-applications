package phonebook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import javafx.animation.*;

public class loginGraphic extends BorderPane{
	
	private double offsetX ; 
	Pane slidebox ;
	Path arrowHead ; 
	
	private double offsetY; 
	
	public loginGraphic(){
		
		this.setTop(menuBar()); 
		this.setCenter(mainContent()); 
		this.setStyle(" -fx-background-color: gray ; -fx-border-width:1px ; -fx-border-color:black");
		BorderPane.setMargin(mainContent(), new Insets(50)); 
	}
	
	
	public Pane menuBar(){
	
	Stop[] stop = new Stop[]{new Stop(0 , Color.web("#FF0000"))  , new Stop(1 , Color.web("#EE0000"))};
	LinearGradient lg = new LinearGradient(0 , 0 , 0 , 1 , true , CycleMethod.NO_CYCLE , stop);
		
	Circle closeButton = new Circle(10); 
	closeButton.setStroke(Color.GRAY);
	closeButton.setStrokeWidth(2);
	closeButton.setFill(lg); 
	closeButton.setOnMouseClicked(e->Platform.exit());
	
	Stop[] stop1 = new Stop[]{new Stop(0 , Color.web("#FFD700"))  , new Stop(1 , Color.web("#EEC900"))};
	LinearGradient lg1 = new LinearGradient(0 , 0 , 0 , 1 , true , CycleMethod.NO_CYCLE , stop1);
	
	Circle minimizeButton = new Circle(10); 
	minimizeButton.setStroke(Color.GRAY);
	minimizeButton.setStrokeWidth(2);
	minimizeButton.setFill(lg1); 
	minimizeButton.setOnMouseClicked(e->App.getStage().setIconified(true));

	Region region = new Region(); 
	
	HBox box = new HBox(10);
	box.setPadding(new Insets(10)); 
	box.getChildren().addAll(region , minimizeButton , closeButton ); 
	HBox.setHgrow(region, Priority.ALWAYS); 
	
	box.setStyle("-fx-background-color:linear-gradient(#C4C4C4,#9E9E9E)");
	
	return box ; 
		
	}
	
	public Pane mainContent(){
		
		
		Text text = new Text("Phone");
		Text text1 = new Text("Log");
		text1.setFont(Font.font("serif" , FontWeight.BOLD , FontPosture.ITALIC , 50)); 
		text.setFill(Color.GRAY); 
		text1.setFill(Color.RED);
		text.setFont(Font.font("serif" , FontWeight.SEMI_BOLD , FontPosture.ITALIC , 40));
		
		TextFlow flow =  new TextFlow(text , text1); 
		flow.setStyle("-fx-font-size:30pt"); 
		flow.setLayoutX(100); 
		flow.setLayoutY(50); 
		
	
		Pane mainPane = new Pane();  
		mainPane.setStyle("-fx-background-color: gray ;");
		
		Rectangle miniLayout = new Rectangle(300 , 500);
		miniLayout.setFill(Color.WHITE); 
		miniLayout.setStroke(Color.WHITE); 
		miniLayout.setLayoutX(50); 
		miniLayout.setLayoutY(25);
		
		mainPane.getChildren().addAll(miniLayout , flow , unlockPane() ); 
		return mainPane ; 
	}
	
	
	public Pane unlockPane(){
		
		Text slideText =  new Text("Slide To Unclock");
		slideText.setLayoutX(60);
		slideText.setLayoutY(45); 
		slideText.setFill(Color.BLUE); 
		slideText.setFont(Font.font("serif" , FontWeight.BOLD , FontPosture.ITALIC , 25)); 
		
		Path arrowHead = new Path(new MoveTo(0 , 0) , new LineTo(15 , 15) , new LineTo(0 , 30) , 
									new MoveTo(17 , 0) , new LineTo(32 , 15) , new LineTo(17 , 30)  , 
										new MoveTo(34 , 0) , new LineTo(49 , 15) , new LineTo(34 , 30)); 
		arrowHead.setStroke(Color.BLACK);
		arrowHead.setStrokeWidth(6); 
		arrowHead.setFill(Color.TRANSPARENT); 
		arrowHead.setStrokeLineCap(StrokeLineCap.ROUND); 
		arrowHead.setLayoutX(5);
		arrowHead.setLayoutY(20); 
		
		Path arrowHeadSpirit = new Path(new MoveTo(0 , 0) , new LineTo(15 , 15) , new LineTo(0 , 30) , 
				new MoveTo(17 , 0) , new LineTo(32 , 15) , new LineTo(17 , 30)  , 
					new MoveTo(34 , 0) , new LineTo(49 , 15) , new LineTo(34 , 30)); 
		arrowHeadSpirit.setStroke(Color.web("#E5E5E5"));
		arrowHeadSpirit.setStrokeWidth(6); 
		arrowHeadSpirit.setFill(Color.TRANSPARENT); 
		arrowHeadSpirit.setStrokeLineCap(StrokeLineCap.ROUND); 
		arrowHeadSpirit.setLayoutX(5);
		arrowHeadSpirit.setLayoutY(20); 
		
		Timeline timeline1 =  new Timeline(new KeyFrame(Duration.millis(1000) , 
				new KeyValue(arrowHeadSpirit.layoutXProperty() , 185 , Interpolator.EASE_BOTH)));
		timeline1.setCycleCount(Timeline.INDEFINITE);
		//timeline1.setAutoReverse(true); 
		timeline1.play();
		
		arrowHead.setOnMousePressed((MouseEvent e)->{
			offsetX = e.getScreenX() - arrowHead.getLayoutX() ;  
		});
		
		arrowHead.setOnMouseDragged((MouseEvent e)->{
			arrowHead.setLayoutX(e.getScreenX() - offsetX);
		//	System.out.println("X Position :" + arrowHead.getLayoutX());
			if(arrowHead.getLayoutX() <= 185){
				Timeline timeline =  new Timeline(new KeyFrame(Duration.millis(2000) , 
						new KeyValue(arrowHead.layoutXProperty() , 5 , Interpolator.EASE_BOTH)));
				timeline.play();
			
			}
			
			if(arrowHead.getLayoutX() >= 190){
				App.setMenuScene();
			}
		}); 
		
		arrowHead.setOnMouseReleased(e->{
			arrowHead.setLayoutX(5); 
		});
		
	
		FadeTransition ft = new FadeTransition();
		ft.setDuration(Duration.millis(3000)); 
		ft.setNode(slideText);
		ft.setCycleCount(Timeline.INDEFINITE); 
		ft.setFromValue(.8); 
		ft.setToValue(.2); 
		ft.play();
		
		FadeTransition ft1 = new FadeTransition();
		ft1.setDuration(Duration.millis(3000)); 
		ft1.setNode(arrowHead);
		ft1.setCycleCount(Timeline.INDEFINITE); 
		ft1.setFromValue(.2); 
		ft1.setToValue(.8); 
		ft1.play();
		
		
		ImageView icon = inportIcon("C:\\Users\\Mr Binary\\workspace\\phone book application\\src\\icon\\contactFolder.png" , 80 , 80);
		icon.setX(80); 
		icon.setY(-250);
		
		slidebox = new Pane(); 
		slidebox.getChildren().addAll(arrowHeadSpirit , arrowHead , slideText , icon);
		slidebox.setPadding(new Insets(5)); 
	//	slidebox.setStyle("-fx-border-widht:1px ; -fx-border-color: black " ); 
		slidebox.setLayoutY(450 ); 
		slidebox.setLayoutX(75); 
		slidebox.setEffect(null);
		
		
		
		
		
		return slidebox ; 
	}
	

	public ImageView inportIcon(String path , double width , double height){
		
		ImageView view  = null ; 
		Image image  = null ; 
		
		try {
			
			FileInputStream file = new FileInputStream(path);
			
			image = new Image(file);
		

			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		view = new ImageView(image);
		view.setFitWidth(width);
		view.setFitHeight(height); 
		
		return view ; 
	}
	
	
}
