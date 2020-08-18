package com.eq.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.eq.model.LinkedHolder;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import ontology.Relation;

public class InfoController implements Initializable {

	@FXML
	private JFXTextArea infoPanel;

	@FXML
	private Text headerText;

	private LinkedHolder links = new LinkedHolder(); 

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setLink(LinkedHolder link) {
		links = link;
		headerText.setText(ScreenController.userQuery);
		
		// setting the information display at load up...
		setInfoPanel(); 
	}// end of method
	
	
	public void setInfoPanel() {
		
		Relation relation = links.getRelation() ;
		//Relevance relevance =  links.getRelevance(); 
		
		StringBuilder wordBuilder = new StringBuilder();

		
		wordBuilder.append("ONTOLOGY INFORMATION ON : ( " + relation.getReadableString() + " )") ; 
		wordBuilder.append("\n"); 
		
		
		
		
		
		/*
		 *    PRINTING OUT EQUAL INFORMATION RELATION TO DOMAIN
		 *    AND RANGE
		 */
		
		// *************************************************************************************
		
		/*
		 * if we have equal domain data in the onto file .... 
		
		/*
		 * if we have equal domain data in the onto file .... 
		 */
		if(!relation.getDomainEqual().isEmpty()) 
		{
			wordBuilder.append("\n");
			wordBuilder.append("\n");
			wordBuilder.append("OTHER SENSES (THUS SYNONYMS) OF : ( " + relation.getDomain() + " )  " ); 
			wordBuilder.append("\n");
			for(String syn : relation.getDomainEqual()) 	
			{
				if(syn == null) 
					continue ; 
				
				wordBuilder.append(syn + " " + relation.getObject() +  " " + relation.getRange()); 
				wordBuilder.append("\n");
				wordBuilder.append("\n");
				
			}// end of for
					
		}// end of if 
		
		
		
		/*
		 * if we have equal range data found in the onto file .... 
		 */
		if(!relation.getDomainEqual().isEmpty()) 
		{
			wordBuilder.append("\n");
			wordBuilder.append("\n");
			wordBuilder.append("OTHER SENSES (THUS SYNONYMS) OF : ( " + relation.getRange() + " ) " );
			wordBuilder.append("\n");
			for(String syn : relation.getRangeEqual()) 	
			{
				if(syn == null) 
					continue ; 
				
				wordBuilder.append(relation.getDomain() + " " + relation.getObject() +  " " + syn ); 
				wordBuilder.append("\n");
				wordBuilder.append("\n");
				
			}// end of for
					
		}// end of if 
		
		// *************************************************************************************
		
		
		/*
		 *    PRINTING OUT SUBCLASS INFORMATION RELATION TO DOMAIN
		 *    AND RANGE
		 */
		
		// *************************************************************************************
		
		/*
		 * if we have equal domain data in the onto file .... typical 
		 */
		if(!relation.getDomainIntances().isEmpty()) 
		{
			wordBuilder.append("\n");
			wordBuilder.append("\n");
			wordBuilder.append("SENSES FROM TYPICAL EXAMPLE OF : ( " + relation.getDomain() + " ) " ); 
			wordBuilder.append("\n");
			wordBuilder.append("\n");
			for(String sub : relation.getDomainIntances()) 	
			{
				
				if(sub == null) 
					continue ; 
				
				wordBuilder.append(sub + " " + relation.getObject() +  " " + relation.getRange()); 
				wordBuilder.append("\n");
				wordBuilder.append("\n");
				
			}// end of for
					
		}// end of if 
		
		wordBuilder.append("\n");
		wordBuilder.append("\n");
		
		/*
		 * if we have equal range data found in the onto file .... 
		 */
		if(!relation.getRangeIntances().isEmpty()) 
		{
			wordBuilder.append("\n");
			wordBuilder.append("\n");
			wordBuilder.append("SENSES FROM TYPICAL EXAMPLE OF :( " + relation.getRange() + " ) " );
			wordBuilder.append("\n");
			wordBuilder.append("\n");
			for(String sub : relation.getRangeIntances()) 	
			{
				if(sub == null) 
					continue ; 
				
				wordBuilder.append(relation.getDomain() + " " + relation.getObject() +  " " + sub ); 
				wordBuilder.append("\n");
				wordBuilder.append("\n");
				
			}// end of for
					
		}// end of if 
		
		
		
		
		//**************************************************************************************
		
		
		
		/*
		 *  WordNet definition and semantic info pertaining to 
		 *  the onto relation..... 
		 */
		
		wordBuilder.append("\n");
		wordBuilder.append("\n");
		wordBuilder.append("\n");
		wordBuilder.append("ENHANCE WORDNET SEMANTIC MEANING ON : ( " + relation.getReadableString() + " )") ; 
		wordBuilder.append("\n");
		wordBuilder.append(links.getExpansionData());
		infoPanel.setText(wordBuilder.toString());
		
	}// end of method... 
	
	

}































