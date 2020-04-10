package phonebook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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

public class menuGraphic extends BorderPane{
	

	protected Button contactButton ;
	protected Button createButton; 
	protected Button editButton; 
	protected Button addContactButton ; 
	protected Line buttonLine ;
	protected ContactTable table ; 
	protected BorderPane centerLayout ; 
	protected TextField searchField ; 
	
	//new contact field data
	public TextField txt_contactName ; 
	public TextField txt_phoneNumber ;
	public Label lbl_contactName ; 
	public Label lbl_phoneNumber ; 
	public Button btn_addContact ; 
	public Label lbl_errorMessage ;
	
	//update contact button 
	public Button btn_update ; 
	public Button btn_delete ; 
	

	
	public menuGraphic(ContactTable table ){
		this.table = table ; 
		this.setTop(menuBar()); 
		//this.setStyle("-fx-border-width:1px; -fx-border-color:black");
		this.setCenter(mainContent()); 
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
		box.getChildren().addAll(region , minimizeButton , closeButton); 
		HBox.setHgrow(region, Priority.ALWAYS); 
		
		box.setStyle("-fx-background-color:linear-gradient(#C4C4C4,#9E9E9E); -fx-border-width:1px ; -fx-border-color:gray");
		
		return box ; 
			
		}
	
	
        public Pane mainContent(){
		
		
		Pane mainPane = new Pane();  
		mainPane.setStyle("-fx-background-color: white ; -fx-border-width:1px ; -fx-border-color: black ;");
		
		Text title =  new Text("Phone-Book");
		title.setLayoutX(200);
		title.setLayoutY(40); 
		title.setFill(Color.WHITE); 
		title.setFont(Font.font("serif" , FontWeight.BOLD , FontPosture.ITALIC , 30)); 
		
		
		
		Path backArrow = new Path(new MoveTo(0 , 0) , new LineTo(20*.5 , 20*.5) ,  new LineTo(40*.5 , 0) , new MoveTo(20*.5 , 20*.5) ,
				new LineTo(20*.5 , -20*.5) ); 
		backArrow.setStroke(Color.WHITE);
		backArrow.setStrokeWidth(5); 
		backArrow.setStrokeLineCap(StrokeLineCap.ROUND); 
		backArrow.setRotate(90); 
		backArrow.setLayoutX(20); 
		backArrow.setLayoutY(30); 
		//setting back arrow to action ........switching back to login scene ..
		backArrow.setOnMouseClicked(e->{App.setLogInScene();});
		
		contactButton = new Button("Contact List"); 
		createButton = new Button("New Contact"); 
		editButton = new Button("Update Contact"); 
		contactButton.setId("navButton");
		createButton.setId("navButton");
		editButton.setId("navButton");
		
		//button lines 
		buttonLine =  new Line(0 , 0 , 100 , 0);
		buttonLine.setStroke(Color.web("#ffffff")); 
		buttonLine.setStrokeWidth(5); 
		buttonLine.setLayoutX(18); 
	//	buttonLine.setLayoutX(150); 
	//	buttonLine.setLayoutX(280); 
		buttonLine.setLayoutY(98); 

		HBox navBar = new HBox(10);
		navBar.getChildren().addAll(contactButton , createButton , editButton);
		navBar.setPadding(new Insets(5));
		navBar.setStyle("-fx-background-color:#104E8B ; -fx-border-width:1px ; -fx-border-color:#104E8B ;");
		navBar.setPrefSize(400, 100);
		navBar.setAlignment(Pos.BOTTOM_CENTER); 
		
		//button 
		addContactButton = new Button("Add"); 
		//addContactButton
		addContactButton.setStyle("-fx-background-color:green ;"); 
		
		centerLayout = new BorderPane();
		centerLayout.setTop(navBar);
		centerLayout.setCenter(setContactListLayout());
		
		//centerLayout.setBottom(addContactButton);
	//	centerLayout.setStyle("-fx-border-width:4px ; -fx-border-color:green ;") ; 
		

		
		mainPane.getChildren().addAll(centerLayout , buttonLine , title , backArrow); 
		return mainPane ; 
	}
        
    
     public Pane setContactListLayout(){
    	
    	searchField = new TextField();
    	searchField.setPromptText("Search Contact"); 
 		searchField.setPrefSize(200, 30);
 		searchField.setStyle("-fx-border-color: green ; -fx-border-width:2px ;"
 				+ " -fx-border-radius:30px ; -fx-background-radius:30px ; -fx-prompt-fill:lightgreen " );
 		searchField.setEffect(new DropShadow()); 
 		
 		String path  = "C:\\Users\\Mr Binary\\workspace\\phone book application\\src\\icon\\searchIcon.png" ;
 		ImageView searchIcon = importIcon(50 , 50 , path);
 		
 		Label searchLabel = new Label();
 		searchLabel.setGraphic(searchIcon); 
 		
    
 		HBox hbox = new HBox();
 		hbox.setAlignment(Pos.CENTER); 
 		hbox.getChildren().addAll(searchLabel , searchField);
 		hbox.setStyle("-fx-background-color:linear-gradient(#CCCCCC , #5E5E5E) ; -fx-border-color:black ; -fx-border-width:1px");
 		hbox.setPrefSize(USE_COMPUTED_SIZE, 55); 
 	
 		
    	 VBox vbox = new VBox(0); 
    	 vbox.getChildren().addAll(table , hbox);
    	 VBox.setVgrow(hbox, Priority.ALWAYS);
    	 vbox.setAlignment(Pos.CENTER); 
    	 return vbox ; 
     }
     
     
     //METHODS FOR IMPORTING ICONS.......
     public ImageView importIcon(double fitWidth , double fitHeight , String path){
    	 
    	 ImageView icon  = null ; 
  		Image image =  null ; 
  		try {
  		
  			image = new Image(new FileInputStream(path));
  			icon = new ImageView(image); 
  			icon.setFitHeight(fitWidth); 
  			icon.setFitWidth(fitHeight); 
  			icon.setPreserveRatio(true); 
  			
 		} catch (FileNotFoundException e) {
 			System.out.println(e.getMessage());
 		}
  		
  		return icon  ; 
     }
     
     
     
     public Pane setAddNewContactLayout(){
    	
    	 DropShadow shadow = new DropShadow(); 
    	 shadow.setColor(Color.BLACK); 
    	 shadow.setRadius(6);
    	 shadow.setSpread(.50);
    	 shadow.setOffsetX(2); 
    	 shadow.setOffsetY(1);
    	 
    	Text layoutName = new Text("Register New Contact"); 
    	layoutName.setFont(Font.font("arial" , FontWeight.BOLD , FontPosture.REGULAR , 20)); 
    	layoutName.setFill(Color.WHITE); 
    	layoutName.setEffect(shadow); 
    	
    	
    	String path  = "C:\\Users\\Mr Binary\\workspace\\phone book application\\src\\icon\\contactIcon.png" ;
   		Image contactImage =  null ; 
   		
   		try {
   		
   			contactImage = new Image(new FileInputStream(path));
  		} catch (FileNotFoundException e) {
  			System.out.println(e.getMessage());
  		}
  		 
     	
  		 Circle imageHolder = new Circle(60);
  		 imageHolder.setStroke(Color.GREEN); 
  		 imageHolder.setStrokeWidth(3);
  		 imageHolder.setFill(new ImagePattern(contactImage));
  		 imageHolder.setEffect(new Reflection()); 
  		 
  		 
  		 //collecting user name and phone number......
  		 lbl_contactName = new Label("Name       :");
  		 lbl_phoneNumber = new Label("Phone No :"); 
  		 txt_contactName = new TextField();
  		 txt_phoneNumber = new TextField(); 
  		 txt_contactName.setId("inputText"); 
  		 txt_phoneNumber.setId("inputText"); 
  		 
  		 //line for textField 
  		 Line nameLine = new Line(0 , 0 , 150 , 0);
  		 Line numberLine = new Line(0 , 0 , 150 , 0);
  		 
  		 //setting property
  		 nameLine.setStroke(Color.LIGHTGRAY); 
  		 nameLine.setStrokeWidth(3);
  		 nameLine.setStrokeLineCap(StrokeLineCap.ROUND); 
  		 numberLine.setStroke(Color.LIGHTGRAY); 
  		 numberLine.setStrokeWidth(3);
  		 numberLine.setStrokeLineCap(StrokeLineCap.ROUND); 
  		 
  		 //setting property
  		 txt_contactName.setPromptText("Enter Name ");
  		 txt_phoneNumber.setPromptText("+234********");
  		// txt_contactName.setStyle("-fx-border-color:null ; -fx-background-color:null"); 
  		 
  		//placing in a grid pane 
  		 GridPane gridPane = new GridPane();
  		 gridPane.addRow(0, lbl_contactName , txt_contactName);
  		 gridPane.add(nameLine , 1 , 1 ); 
  		 gridPane.addRow(2, lbl_phoneNumber , txt_phoneNumber);
  		 gridPane.add(numberLine , 1 , 3);
  		 gridPane.setAlignment(Pos.CENTER); 
  		 gridPane.setVgap(0); 
  		 gridPane.setHgap(20); 
  		 GridPane.setMargin(nameLine , new Insets(0));
  		 
  		 
  		 //button to add information to contact 
  		 btn_addContact = new Button("Save Contact"); 
  		 btn_addContact.setPrefSize(200, 30); 
  		 btn_addContact.setEffect(shadow); 

  		 lbl_errorMessage =  new Label("Error Message");
  		 lbl_errorMessage.setStyle("-fx-text-fill:red");
  		 
    	 VBox mainLayout = new VBox(30);
    	 mainLayout.getChildren().addAll( layoutName , imageHolder , gridPane , btn_addContact , lbl_errorMessage); 
    	 mainLayout.setPadding(new Insets(20));
    	 mainLayout.setAlignment(Pos.CENTER);
    	 VBox.setMargin(btn_addContact , new Insets(30 , 0 , 0 , 0)); 
    	 
    	 return mainLayout; 
     }
     
     public Pane setUpdateContactLayout(){
    	 
    	 btn_update = new Button("Update Contact");
    	 btn_update.setStyle("-fx-border-color: blue ; -fx-border-width:0px ;"
  				+ " -fx-border-radius:0px ; -fx-background-radius:0px ;" );
    	 btn_update.setPrefSize(180, 30);
    	 
    	 btn_delete = new Button("Delete Contact");    
    	 btn_delete.setId("delete-button");
     	 btn_delete.setPrefSize(180, 30);
    	 
    	 DropShadow shadow = new DropShadow();
    	 shadow.setOffsetX(5); 
    	 shadow.setOffsetY(5); 
    	 btn_update.setEffect(shadow); 
    	 btn_delete.setEffect(shadow); 
  		
    	
    	 HBox pane = new HBox(10);
    	 pane.getChildren().addAll(btn_update , btn_delete);
    	 pane.setStyle("-fx-border-width:1px ; -fx-border-color:black ; -fx-background-color:linear-gradient(#CCCCCC , #5E5E5E)");
    	 pane.setPrefSize(USE_COMPUTED_SIZE, 55); 
    	 pane.setAlignment(Pos.CENTER); 
    	 
  		 table.setEffect(new Reflection()); 
    
     	 VBox vbox = new VBox(); 
     	 vbox.getChildren().addAll( table , pane  );
     	 vbox.setAlignment(Pos.CENTER); 
     	 vbox.setStyle("-fx-background-color:linear-gradient(#CCCCCC , #5E5E5E)");
     	 VBox.setVgrow(pane, Priority.ALWAYS); 
     	
     	 
     	 return vbox ; 
    
     }
     
     
     
     
     
     //method for setting center layout 
     public void setCenterLayout(Pane pane){
    	 
    	 centerLayout.setCenter(pane); 
     }
     
     
     
     
     

}
