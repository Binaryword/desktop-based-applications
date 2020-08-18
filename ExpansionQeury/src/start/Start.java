package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application{

	public static void main(String[] args) {
	
//		ExpansionQeury eq = new ExpansionQeury();
//		eq.initSeedVariable("maize", "fertilizers", "irrigation", "soils");
//
//		Scanner input = new Scanner(System.in);
//		String query;
//
//		do {
//
//			System.out.println("Enter Qeury");
//			query = input.nextLine();
//			eq.setUserQuery(query.toLowerCase());
//			eq.processUserQuery();
//			System.out.println();
//
//		} while (query != "-z");
		
	//System.out.println(POS.NOUN);
		
		launch(args);
	
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	
		
		Parent root = FXMLLoader.load(getClass().getResource("/com/eq/graphic/Main.fxml")); 
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Expansion Query on FarmOntology");
		primaryStage.show();
		
	}// end o 	

}
