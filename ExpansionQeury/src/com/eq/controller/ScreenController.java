package com.eq.controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.eq.model.LinkedHolder;
import com.jfoenix.controls.JFXListView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ontology.Ontology;

public class ScreenController implements Initializable {

	@FXML
	private TextField searchBox;
	
	@FXML
	private Text queryPrompt ; 

	@FXML
	private JFXListView<String> linkLayout;
	public static String userQuery;
	private boolean firster = false;

	@FXML
	void activate_searching(ActionEvent event) {

		if (searchBox.getText() != "" && searchBox.getText() != null) {
			String word = searchBox.getText().toString().toLowerCase();
			userQuery = word;
			// load Information panel GUi....
			MainController.eq.setUserQuery(word);
			MainController.eq.processUserQuery();
			linkLayout.getItems().clear();
			addLinkdeLayout();

		} // if
		else {
			
			System.out.println("Search field is empty..");
		}
	}

	public void addLinkdeLayout() {

		linkLayout.getItems().clear();
		Set<LinkedHolder> links = Ontology.getLinkeHolder();

		if (MainController.eq.getSeedVariableCount() >= 2) {

			/*
			 * if only more than two seed variable found
			 */

			boolean isFormed = MainController.eq.isExpansionFormed();

			if (isFormed) {
				
				queryPrompt.setText("Showing " + links.size() + " result for : " + searchBox.getText()) ; 
				
				for (LinkedHolder holder : links) {
					
					linkLayout.getItems().add(holder.getRelation().getReadableString());

				} // for loops ...

			} else {

				linkLayout.getItems().add("ACTIVATE ONTOLOGY LEARNING");
				
				/*
				 * 		implementing onto learning using word net...
				 * 
				 */

			}

		} else if (MainController.eq.getSeedVariableCount() == 1) {

			/*
			 * if only one seed variable found in user query
			 * 	
			 * 		the code can look into domain and range to 
			 * 			to suggest range if domain is matched or
			 * 				suggest domain if range is matched.....
			 * 
			 * 	<pre> process user query to get precise candidate term..
			 */
			queryPrompt.setText("Showing " + links.size() + " result for : " + searchBox.getText()) ;
			linkLayout.getItems().clear();
			linkLayout.getItems().add("SUGGESTING SEED VARIBLE.. ");

		} else if (links.isEmpty()) {

			/*
			 * when no one seed variable found in user query
			 */
			queryPrompt.setText("Showing " + links.size() + " result for : " + searchBox.getText()) ;
			linkLayout.getItems().clear();
			linkLayout.getItems().add("OUT OF SUBJECT GRANULARITY...");
		}

	}// end of method.......

	public void activate_link_clicked() {

		linkLayout.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		linkLayout.getSelectionModel().selectedItemProperty().addListener(ov -> {

			int index = linkLayout.getSelectionModel().getSelectedIndex();
			
			if ((index >= 0)) {
				
				List<LinkedHolder> link = new LinkedList<>(Ontology.getLinkeHolder());
				System.out.println(link.get(index).getRelevance().toString());
				loadInformationPane(link.get(index) , link.get(index).getRelation().getReadableString()); 
			}

		});
	}

	private void loadInformationPane(LinkedHolder link , String title) {
		
	try {
			
			FXMLLoader load = new FXMLLoader(getClass().getResource("/com/eq/graphic/InfoPanel.fxml"));
			Parent root = load.load() ;  
			InfoController controller =(InfoController)load.getController() ; 
			controller.setLink(link); 
			Scene scene = new Scene(root);
			Stage stage = new Stage();  
			stage.setScene(scene);
			stage.setTitle(title);
			//stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			stage.close();
			linkLayout.setFocusTraversable(false);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("Screen controller called....");

		if (!firster) {
			firster = true;

			userQuery = MainController.userQuery;
			searchBox.setText(userQuery);
			MainController.eq.setUserQuery(userQuery);
			MainController.eq.processUserQuery();
		}

		searchBox.setFocusTraversable(false);
		activate_link_clicked();
		addLinkdeLayout();

	}

}
