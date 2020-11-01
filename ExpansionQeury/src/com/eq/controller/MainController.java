package com.eq.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.eq.model.ExpansionQeury;

import WordNet.WordRelationShip;
import WordNet.Wordnet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ontology.Ontology;

public class MainController implements Initializable{


    @FXML
    private AnchorPane anchor;

    @FXML
    private BorderPane border;

    @FXML
    private HBox textLayout;

    @FXML
    private TextField searchBox;
    
    @FXML
    private ImageView appIcon;
    
    // user initialization 
    public static ExpansionQeury eq  ; 
    public static String userQuery = null ; 
    

    @FXML
    void activate_search_panel(ActionEvent event) {

    // a condition need to be check if document is ready...
    
    if(searchBox.getText() != "" && searchBox.getText() != null) 
    {
    	String word = searchBox.getText().toString().toLowerCase() ;
    	userQuery = word ; 
    	// load Information panel GUi....
    	loadWindow(""); 
    	
    }// if 
    	
    
    }
	
	public void loadWindow(String title){
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/com/eq/graphic/Screen.fxml"));
			Stage stage = (Stage)anchor.getScene().getWindow() ; 
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle(title);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		appIcon.setImage(new Image(getClass().getResourceAsStream("/com/eq/resources/ont1.png")));
		
		searchBox.setFocusTraversable(false);
		new Thread(()->{

			eq = new ExpansionQeury();
			eq.initSeedVariable("maize", "fertilizers", "irrigation", "soils");
			//Ontology.printDocuments();
			//activateOntologyLearning();
			
		}).start();
		
	
		System.out.println("Controller called....");
	}
	
	public void activateOntologyLearning() {
		
		// GETTING WORDNET RESOURCE READY........
		Wordnet.getOntologyTerm(Ontology.getAllOntologyConcept());
		Wordnet.setOntologyDocument(Ontology.getDocuments());
		Wordnet.learnWordNetOntologyRelevance("tree");
		//List<WordRelationShip> wd = Wordnet.getFinalLearntWord() ;
		
		
		
	}

}
