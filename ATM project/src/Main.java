
import java.util.Scanner;
import java.util.Random ; 
import javafx.application.Application; 
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application{
	
	Random rand = new Random(); 
	Stage window = new Stage() ; 
	Scene logScene , mainScene , accountTypeScene , receipScene , balanceScene , enterAmountScene; 
	AccountList acc = new AccountList(); 
	Account account ; 
	Transaction withdraw = new Withdraw(); 
	Transaction deposite = new Deposite();
	public static void main(String[] args){
		
		Application.launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage){
		window = primaryStage ; 
		
		
		
		
		
	
		
		
		window.setScene(loginScene());
		window.setResizable(false);
		window.setTitle("BANKING SYSTEM");
		window.show();
		
			
		
	}

			
	//=======================CREATE A  LOG IN LAYOUT====================
	public Scene loginScene(){
		 
			Label welcomeMessage = new Label();
			welcomeMessage.setText("Virtaul Banking System"); 
			welcomeMessage.setPadding(new Insets(10 , 10 , 10 , 50));
			welcomeMessage.setFont(Font.font("serif" ,FontWeight.EXTRA_BOLD , FontPosture.REGULAR, 50));
			welcomeMessage.setTextFill(Color.BLUE);
			
			Label enterAccount = new Label();
			enterAccount.setText("Please Enter Account Number");
			
			//Acount no ..... input data 
			
			TextField accountField = new TextField();
			accountField.setPromptText("XXXX");
			accountField.setPrefColumnCount(2);
			accountField.setMaxSize(400, 200);
			accountField.setAlignment(Pos.CENTER); 
			

			
			//pin text field 
			Label enterPin = new Label();
			enterPin.setText("Please Enter Pin");
			
			TextField pinField = new TextField();
			pinField.setPromptText("XXXX");
			pinField.setPrefColumnCount(2);
			pinField.setMaxSize(150, 200);
			pinField.setAlignment(Pos.CENTER); 
			
			
			VBox pinbox  = new VBox();
			pinbox.setSpacing(20);
			
			pinbox.setAlignment(Pos.TOP_CENTER);
			pinbox.setPadding(new Insets(80 , 10 , 10 , 10));
			pinbox.getChildren().addAll(enterAccount ,accountField ,  enterPin , pinField);
			
			
			//create the RIGTH BUTTON==================
			Button proceed = new Button("Proceed >");
			proceed.setMaxWidth(Double.MAX_VALUE);;
			Button cancel = new Button("Cancel >");
			cancel.setMaxWidth(Double.MAX_VALUE);
			cancel.setId("cancel-button");
			//create v box for button
			
			VBox bottonBox = new VBox();
			bottonBox.setSpacing(50);
		//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
			bottonBox.setAlignment(Pos.TOP_CENTER);
			bottonBox.setPrefWidth(200);
			bottonBox.setPadding(new Insets(130, 10 , 0 , 0));
			bottonBox.getChildren().addAll(proceed); 
			
			//==============================
			
			
			
			
			//Create a borderPane
			//=====================================
			BorderPane miniPane = new BorderPane();
			miniPane.setCenter(pinbox);
			miniPane.setTop(welcomeMessage);
			miniPane.setRight(bottonBox);
			//miniPane.setBottom(); 
			miniPane.setStyle("-fx-border-width:0px ; -fx-border-color:black");	
			
			
			//Action listener to the buttons ..................
			//===========================================================//
			proceed.setOnAction(e-> {
				
					//get account number for text box 
				int accNum = Integer.parseInt(accountField.getText());
				 acc.setAccountNumber(accNum);
				account = acc.getAccount();
				
				//get the pin from user 
				int pin =Integer.parseInt( pinField.getText() ) ; 
				acc.setPin(pin);
				
			if( (account != null) && (acc.validatePIn() == true) ){
				
					window.setScene(mainScene()); 
						
				
					}else{
						System.out.println("Error Account not found ");
					}
				
				});
			
			cancel.setOnAction(e->loginScene());
			//==========================================================//
			
			
			
			logScene = new Scene(miniPane , 800 , 500); 	
			logScene.getStylesheets().add("theme.css"); 
			return logScene ; 
	}	
	
	
	public Scene mainScene(){
		
		//=========================MAIN MENU LAYOUT
		 
		Label welcomeMessage1 = new Label();
		welcomeMessage1.setText("Select A Transaction"); 
		//welcomeMessage1.setPadding(new Insets(10 , 10 , 10 , 50));
		welcomeMessage1.setFont(Font.font("serif" ,FontWeight.EXTRA_BOLD , FontPosture.REGULAR, 30));
		welcomeMessage1.setTextFill(Color.BLUE);
		
		
		Label welcomeMessage2 = new Label();
		welcomeMessage2.setText("Press Cancel to terminate Transaction"); 
		//welcomeMessage2.setPadding(new Insets(10 , 10 , 10 , 50));
		welcomeMessage2.setFont(Font.font("serif" ,FontWeight.BOLD , FontPosture.REGULAR, 25));
		welcomeMessage2.setTextFill(Color.GREEN);
		
		VBox message = new VBox(2);
		message.setPadding(new Insets(10 , 10 , 10 , 20));
		message.getChildren().addAll(welcomeMessage1 , welcomeMessage2);
		
		
		//create the RIGTH BUTTON==================
		Button widthdraw = new Button("Withdraw >");
		widthdraw.setMaxWidth(Double.MAX_VALUE);;
		Button cancel = new Button("Cancel >");
		cancel.setMaxWidth(Double.MAX_VALUE);
		cancel.setId("cancel-button");
		Button enquiry = new Button("Enquiry >");
		enquiry.setMaxWidth(Double.MAX_VALUE);;
		
		//==========LEFT BUTTON ===============
		Button deposite = new Button("< Deposite");
		Button recharge = new Button("< Recharge");
		Button transfer = new Button("< Transfer");
		deposite.setMaxWidth(Double.MAX_VALUE);
		recharge.setMaxWidth(Double.MAX_VALUE);
		transfer.setMaxWidth(Double.MAX_VALUE);
		
		//create v box for the right button
		VBox rightbox = new VBox();
		rightbox.setSpacing(50);
	//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
		rightbox.setAlignment(Pos.CENTER);
		rightbox.setPrefWidth(200);
		rightbox.setPadding(new Insets(0, 10 , 0 , 0));
		rightbox.getChildren().addAll(widthdraw,enquiry , cancel); 
		
		//==============================
		
		//create v box for the left button
				VBox leftbox = new VBox();
				leftbox.setSpacing(50);
			//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
				leftbox.setAlignment(Pos.CENTER);
				leftbox.setPrefWidth(200);
				leftbox.setPadding(new Insets(0, 0 , 0 , 10));
				leftbox.getChildren().addAll(transfer,deposite , recharge); 
		
		
		
		//Create a borderPane
		//=====================================
		BorderPane miniPane = new BorderPane();
	//	miniPane.setCenter(pinbox);
		miniPane.setTop(message);
		miniPane.setRight(rightbox);
		miniPane.setLeft(leftbox);
		//miniPane.setBottom(); 
		miniPane.setStyle("-fx-border-width:0px ; -fx-border-color:black");	
		
		//Action listener to the buttons ..................
		//===========================================================//
		cancel.setOnAction(e->window.setScene(loginScene()));
		widthdraw.setOnAction(e-> window.setScene(accountTypeScene()));
		enquiry.setOnAction(e-> {
			window.setScene(showBalance()) ; 
			
		});
		
		
		//==========================================================//
		
		
		mainScene = new Scene(miniPane , 800 , 500); 	
		mainScene.getStylesheets().add("theme.css"); 
		return mainScene ; 
			
	}

	
    public Scene accountTypeScene(){
		
		
	//=========================MAIN MENU LAYOUT
	 
			Label welcomeMessage1 = new Label();
			welcomeMessage1.setText("Select The Enquiry Account"); 
			//welcomeMessage1.setPadding(new Insets(10 , 10 , 10 , 50));
			welcomeMessage1.setFont(Font.font("serif" ,FontWeight.EXTRA_BOLD , FontPosture.REGULAR, 30));
			welcomeMessage1.setTextFill(Color.BLUE);
			
			
			Label welcomeMessage2 = new Label();
			welcomeMessage2.setText("Press Cancel to terminate Transaction"); 
			//welcomeMessage2.setPadding(new Insets(10 , 10 , 10 , 50));
			welcomeMessage2.setFont(Font.font("serif" ,FontWeight.BOLD , FontPosture.REGULAR, 25));
			welcomeMessage2.setTextFill(Color.GREEN);
			
			VBox message = new VBox(2);
			message.setPadding(new Insets(10 , 10 , 10 , 20));
			message.getChildren().addAll(welcomeMessage1 , welcomeMessage2);
			
			
			//create the RIGTH BUTTON==================
			Button saving = new Button("Savings >");
			saving.setMaxWidth(Double.MAX_VALUE);;
			Button cancel = new Button("Cancel >");
			cancel.setMaxWidth(Double.MAX_VALUE);
			cancel.setId("cancel-button");
			Button current = new Button("Current >");
			current.setMaxWidth(Double.MAX_VALUE);;
			
			//==========LEFT BUTTON ===============
			Button credit = new Button("< Credit");
			credit.setMaxWidth(Double.MAX_VALUE);
			Button invalid = new Button("< invalid");
			invalid.setMaxWidth(Double.MAX_VALUE);

			
			//create v box for the right button
			VBox rightbox = new VBox();
			rightbox.setSpacing(50);
		//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
			rightbox.setAlignment(Pos.CENTER);
			rightbox.setPrefWidth(200);
			rightbox.setPadding(new Insets(0, 10 , 0 , 0));
			rightbox.getChildren().addAll(saving,current , cancel); 
			
			//==============================
			
			//create v box for the left button
					VBox leftbox = new VBox();
					leftbox.setSpacing(50);
				//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
					leftbox.setAlignment(Pos.CENTER);
					leftbox.setPrefWidth(200);
					leftbox.setPadding(new Insets(0, 0 , 0 , 10));
					leftbox.getChildren().addAll(credit , invalid); 
			
			
			
			//Create a borderPane
			//=====================================
			BorderPane miniPane = new BorderPane();
		//	miniPane.setCenter(pinbox);
			miniPane.setTop(message);
			miniPane.setRight(rightbox);
			miniPane.setLeft(leftbox);
			//miniPane.setBottom(); 
			miniPane.setStyle("-fx-border-width:0px ; -fx-border-color:black");	
			
			
			//Action listener to the buttons ..................
			//===========================================================//
			saving.setOnAction(e-> window.setScene(recieptScene()));
			cancel.setOnAction(e->cancelTransaction());
			//==========================================================//
			
			
			
			accountTypeScene = new Scene(miniPane , 800 , 500); 	
			accountTypeScene.getStylesheets().add("theme.css");
			
			return accountTypeScene ; 
				 
	}

    
    public Scene recieptScene(){
    	//=========================MAIN MENU LAYOUT
		 
    			Label welcomeMessage1 = new Label();
    			welcomeMessage1.setText("Do You Want A Reciept?"); 
    			//welcomeMessage1.setPadding(new Insets(10 , 10 , 10 , 50));
    			welcomeMessage1.setFont(Font.font("serif" ,FontWeight.EXTRA_BOLD , FontPosture.REGULAR, 30));
    			welcomeMessage1.setTextFill(Color.BLUE);
    			
    			
    			Label welcomeMessage2 = new Label();
    			welcomeMessage2.setText("Press Cancel to terminate Transaction"); 
    			//welcomeMessage2.setPadding(new Insets(10 , 10 , 10 , 50));
    			welcomeMessage2.setFont(Font.font("serif" ,FontWeight.BOLD , FontPosture.REGULAR, 25));
    			welcomeMessage2.setTextFill(Color.GREEN);
    			
    			VBox message = new VBox(2);
    			message.setPadding(new Insets(10 , 10 , 10 , 20));
    			message.getChildren().addAll(welcomeMessage1 , welcomeMessage2);
    			
    			
    			//create the RIGTH BUTTON==================
    			Button yes = new Button("Yes >");
    			yes.setMaxWidth(Double.MAX_VALUE);;
    			Button no = new Button("No >");
    			no.setMaxWidth(Double.MAX_VALUE);
    			no.setId("cancel-button");
    			
    			
    			//==========LEFT BUTTON ===============
    			Button cancel = new Button("Cancel >");
    			cancel.setMaxWidth(Double.MAX_VALUE);
    			cancel.setId("cancel-button");
    			Button invalid = new Button("< invalid");
    			invalid.setMaxWidth(Double.MAX_VALUE);

    			
    			//create v box for the right button
    			VBox rightbox = new VBox();
    			rightbox.setSpacing(50);
    		//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
    			rightbox.setAlignment(Pos.CENTER);
    			rightbox.setPrefWidth(200);
    			rightbox.setPadding(new Insets(0, 10 , 0 , 0));
    			rightbox.getChildren().addAll(yes , no); 
    			
    			//==============================
    			
    			//create v box for the left button
    					VBox leftbox = new VBox();
    					leftbox.setSpacing(50);
    				//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
    					leftbox.setAlignment(Pos.CENTER);
    					leftbox.setPrefWidth(200);
    					leftbox.setPadding(new Insets(0, 0 , 0 , 10));
    					leftbox.getChildren().addAll(cancel,invalid); 
    			
    			
    			
    			//Create a borderPane
    			//=====================================
    			BorderPane miniPane = new BorderPane();
    		//	miniPane.setCenter(pinbox);
    			miniPane.setTop(message);
    			miniPane.setRight(rightbox);
    			miniPane.setLeft(leftbox);
    			//miniPane.setBottom(); 
    			miniPane.setStyle("-fx-border-width:0px ; -fx-border-color:black");	
    			
    			//Action listener to the buttons ..................
    			//===========================================================//
    			yes.setOnAction(e-> window.setScene(showEnterAmount()));
    			cancel.setOnAction(e->cancelTransaction());
    			//==========================================================//
    			
    			
    			receipScene = new Scene(miniPane , 800 , 500); 	
    			receipScene.getStylesheets().add("theme.css"); 
    			return receipScene ; 
    }
    
    
    public Scene showBalance(){
    	Label message1 = new Label();
    	message1.setText("Available Balace"); 
    	message1.setFont(Font.font("serif" ,FontWeight.EXTRA_BOLD , FontPosture.REGULAR, 30));
    	message1.setTextFill(Color.RED);
		
		//Available balance text field 
		TextField accText = new TextField();
		accText.setText("NGN "+ account.getAccountBalance());
		accText.setPrefColumnCount(2);
		accText.setMaxSize(500, 200);
		accText.setAlignment(Pos.CENTER); 
		
		
    	Label message2 = new Label();
    	message2.setText("Ledger Balance"); 
    	message2.setFont(Font.font("serif" ,FontWeight.EXTRA_BOLD , FontPosture.REGULAR, 25));
    	message2.setTextFill(Color.BLUE);
    	
		//Available ledger balance text field 
		TextField accText2 = new TextField();
		accText2.setText("NGN "+ account.getAccountBalance());
		accText2.setPrefColumnCount(2);
		accText2.setMaxSize(300, 200);
		accText2.setAlignment(Pos.CENTER); 
		
		
		Label message3 = new Label();
    	message3.setText("Would you like to perform\n     another transaction?"); 
    	message3.setFont(Font.font("serif" ,FontWeight.EXTRA_BOLD , FontPosture.REGULAR, 25));
    	message3.setTextFill(Color.RED);
	
		Button yes = new Button("Yes >");
		yes.setMaxWidth(Double.MAX_VALUE);
		yes.setPrefWidth(200);
		Button no = new Button("< No");
		no.setMaxWidth(Double.MAX_VALUE);
		no.setPrefWidth(200);
		no.setId("cancel-button");
		
		//=========HBox for the botton and text 
		HBox buttonbox = new HBox(40);
		buttonbox.getChildren().addAll(no , message3 , yes);
		buttonbox.setAlignment(Pos.BASELINE_CENTER);
		buttonbox.setPadding(new Insets(30,10,10,10));
		
		//================== VBox to add all gadget 
		VBox vbox = new VBox(5); 
		vbox.getChildren().addAll(message1 , accText , message2 , accText2 , buttonbox);
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setAlignment(Pos.CENTER);
		
		//set yes and no button on action 
		
		yes.setOnAction(e-> {
			window.setScene(mainScene());
		});
		
		no.setOnAction(e-> window.setScene(loginScene()));
		
		
		
		
		balanceScene = new Scene(vbox , 800 , 500); 	
		balanceScene.getStylesheets().add("theme.css"); 
		return balanceScene ; 
    }
    
    
    public Scene showEnterAmount(){
    	
    	Label message = new Label();
		message.setText("Enter Amount"); 
		//welcomeMessage1.setPadding(new Insets(10 , 10 , 10 , 50));
		message.setFont(Font.font("serif" ,FontWeight.EXTRA_BOLD , FontPosture.REGULAR, 30));
		message.setTextFill(Color.BLUE);
		
		//Enter Amount text field 
				TextField amountText = new TextField();
				amountText.setPrefColumnCount(2);
				amountText.setMaxSize(500, 200);
				amountText.setAlignment(Pos.CENTER); 
	
		VBox vbox = new VBox(2);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(30 , 10 , 5 , 10));
		vbox.getChildren().addAll(message , amountText);
		
		
		//create the RIGTH BUTTON==================
		Button oneThousand = new Button("1,000 >");
		oneThousand.setOnAction(e->amountText.setText("1000"));
		oneThousand.setId("amount-button");
		oneThousand.setMaxWidth(Double.MAX_VALUE);;
		Button fiveThousand = new Button("5,000 >");
		fiveThousand.setOnAction(e->amountText.setText("5000"));
		fiveThousand.setMaxWidth(Double.MAX_VALUE);
		fiveThousand.setId("amount-button");
		
		Button tenThousand = new Button("10,000 >");
		tenThousand.setOnAction(e->amountText.setText("10000"));
		tenThousand.setMaxWidth(Double.MAX_VALUE);
		tenThousand.setId("amount-button");
		
		
		//=================CENTER BUTTON 
		Button cancel = new Button("Cancel >");
		//cancel.setMaxWidth(Double.MAX_VALUE);
		cancel.setPrefWidth(200);
		cancel.setId("cancel-button");
		
		Button proceed = new Button("Proceed ");
		proceed.setPrefWidth(200);
		
		VBox centerbutton = new VBox(30);
		centerbutton.setAlignment(Pos.CENTER);
		centerbutton.getChildren().addAll(proceed , cancel);
		
		
		//==========LEFT BUTTON ===============
		Button fiveHundred = new Button("< 500");
		fiveHundred.setOnAction(e->amountText.setText("500"));
		fiveHundred.setId("amount-button");
		Button fiftheenThousand = new Button("< 15,000");
		fiftheenThousand.setOnAction(e->amountText.setText("15000"));
		fiftheenThousand.setId("amount-button");
		Button twentyThousand = new Button("< 20,000");
		twentyThousand.setOnAction(e->amountText.setText("20000"));
		twentyThousand.setId("amount-button");
		fiveHundred.setMaxWidth(Double.MAX_VALUE);
		fiftheenThousand.setMaxWidth(Double.MAX_VALUE);
		twentyThousand.setMaxWidth(Double.MAX_VALUE);
		
		//create v box for the right button
		VBox rightbox = new VBox();
		rightbox.setSpacing(50);
	//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
		rightbox.setAlignment(Pos.CENTER);
		rightbox.setPrefWidth(200);
		rightbox.setPadding(new Insets(0, 10 , 0 , 0));
		rightbox.getChildren().addAll(oneThousand,fiveThousand,tenThousand); 
		
		//==============================
		
		//create v box for the left button
				VBox leftbox = new VBox();
				leftbox.setSpacing(50);
			//	bottonBox.setStyle("-fx-border-width:3px ; -fx-border-color:black");	
				leftbox.setAlignment(Pos.CENTER);
				leftbox.setPrefWidth(200);
				leftbox.setPadding(new Insets(0, 0 , 0 , 10));
				leftbox.getChildren().addAll(fiveHundred,fiftheenThousand , twentyThousand); 
		
		
		
		//Create a borderPane
		//=====================================
		BorderPane miniPane = new BorderPane();
		miniPane.setCenter(centerbutton);
		miniPane.setTop(vbox);
		miniPane.setRight(rightbox);
		miniPane.setLeft(leftbox);
		//miniPane.setBottom(); 
		miniPane.setStyle("-fx-border-width:0px ; -fx-border-color:black");	
		
		
		
		//Action listener to the buttons ..................
		//===========================================================//
		proceed.setOnAction(e->{
				window.setScene(showEnterAmount()); 
			int amount = Integer.parseInt(amountText.getText()); 
			
				
				
				if((amountText.getText() != null) && (amount < account.getAccountBalance())){
					
					
					System.out.println("Transaction in progress......");
					System.out.println("Enter amount to withdraw");
					withdraw.setAmount(Integer.parseInt(amountText.getText()));

					//set the balance from data base ; 
					System.out.println("your current account balance is === " + account.getAccountBalance());
					withdraw.setBalance(account.getAccountBalance());
					System.out.println(" ==" + withdraw.getBalance() + " - " + withdraw.getAmount());
					withdraw.activateTransaction();

					System.out.println("your new account balance is === " + withdraw.getNewBalance());
				
				}else 
					System.out.println("Invalid amount \nor insufficient funds"); 
						
			
			
			});
			
		cancel.setOnAction(e->cancelTransaction());
		//==========================================================//
		
		
		
		
		enterAmountScene = new Scene(miniPane , 800 , 500); 	
		enterAmountScene.getStylesheets().add("theme.css"); 
		return enterAmountScene ; 
			
    }
    
    
    public void cancelTransaction(){
    	
    	window.setScene(mainScene());
    }
    
   

}