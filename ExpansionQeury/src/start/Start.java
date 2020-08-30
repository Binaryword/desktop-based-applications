package start;

import java.util.Scanner;

import com.eq.model.ExpansionQeury;

import WordNet.Wordnet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ontology.Ontology;

public class Start extends Application{

	public static void main(String[] args) {
	
	
	//System.out.println(POS.NOUN);
	//startConsole();
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
	
	public static void startConsole() {
		
		ExpansionQeury eq = new ExpansionQeury();
		eq.initSeedVariable("maize", "fertilizers", "irrigation", "soils");

		Scanner input = new Scanner(System.in);
		String query;

		do {

			System.out.println("Enter Qeury");
			query = input.nextLine();
			eq.setUserQuery(query.toLowerCase());
			Wordnet.getOntologyTerm(Ontology.getAllOntologyConcept());
			//eq.processUserQuery();
			
			
			System.out.println();

		} while (query != "-z");
		
		   
	}

}
