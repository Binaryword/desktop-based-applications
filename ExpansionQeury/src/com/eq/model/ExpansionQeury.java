package com.eq.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import WordNet.Wordnet;
import ontology.Ontology;

public class ExpansionQeury {
	
	private List<String> seedVariableList ; 
	private String userQuery ;
	private int checkCount; 
	private List<Tagging> queryTags = new ArrayList<>(); 
	
	
	public ExpansionQeury(){
		
		this(new LinkedList<>());
	}
	
	public ExpansionQeury(List<String> seedVariableList){
		
		this.seedVariableList = seedVariableList ; 
		Wordnet.loadWordNet("C:\\Program Files (x86)\\WordNet\\2.1\\Dict");
		Ontology.readOntology(new File("maizeFarmingOntology_MODIFY3_VALIDATION.owl"));
		Ontology.initOntology();
		//Ontology.printActiveOntology();
		TextPreprocessing.initCorpus();
		
		new Thread(()->{
			
			Ontology.initDocument();
			Ontology.printDocuments();
			
		}).start();
		
		
	}
	
	public ExpansionQeury(List<String> seedVariableList, String userQuery){
		
		this.seedVariableList = seedVariableList ; 
		this.userQuery = userQuery ; 
	
	}

	public List<String> getSeedVariableList() {
		return seedVariableList;
	}

	public String getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}
	
	
	/*
	 * helper method to initialize and pre processed seed variable...
	 */
	public void initSeedVariable(String... seedVariable){
	
		
		System.out.println("Raw seed Variable " + seedVariable.toString());
		
		for(String sv : seedVariable) {
			
			seedVariableList.add(TextPreprocessing.lemmatize_word(sv)); 	
			
		}// end of for loops
		
		System.out.println("preprocessed seed variable " + seedVariableList.toString()) ; 
	}
	


	public void processUserQuery(){
		
		// token  user query 
		List<String> token = TextPreprocessing.tokenize_query(getUserQuery()); 
		
		// removing stop word from user query 
		List<String> cleanStopWord = TextPreprocessing.remove_stopword(TextPreprocessing.remove_punctuation(token));
		
		// initializing lemma list
		List<String> lemmaUserQueryList = new ArrayList<>(); 
		
		// stemming user query 
		for(String w : cleanStopWord) 
		{
			lemmaUserQueryList.add(TextPreprocessing.lemmatize_word(w)); 
			
		}// end of for loop
		
	
		// finding.. candidate term form onto file... 
		Set<String> fileterList = Ontology.getFilterCandidateTerm(lemmaUserQueryList);
		lemmaUserQueryList.clear();
		lemmaUserQueryList.addAll(fileterList); 
		
		// finding expansion to user query... 
		lemmaUserQueryList.addAll(findQueryExpansion(lemmaUserQueryList)) ; 
//		Set<String> expansion = findQueryExpansion(lemmaUserQueryList);
//		lemmaUserQueryList.clear();
//		lemmaUserQueryList.addAll(expansion); 
		
		System.out.println("Expanding Query ---> " + lemmaUserQueryList); 
		
		// computing semantic relevance to onto corpus .....
		Ontology.computeWordRelevance(lemmaUserQueryList);
		checkCount  = checkSVCount(lemmaUserQueryList); 
		
		
	}// end of method
	
	
	public int getSeedVariableCount(){
		
		return checkCount ; 
	}
	
	public  int checkSVCount(List<String> qeury){
	
		int counter = 0  ; 
		
		seedVariableList.addAll(findQueryExpansion(qeury)); 
		
		for(String sv : seedVariableList){	
			
			for(String uq : qeury){
				
				if(sv.equals(uq))
					counter++ ; 
				
			}// end of loop 
			
		}// end of for 
		
		System.out.println("SEED VARIBLE MATCH COUNTER ---------------------------->>>>>>>>>>>>> " + counter);
		
		return counter ;
	}
	
	
	
	
	
	/*
	 *  tagging to form expansion query .... thus getting synonyms related
	 *  to a query string.....
	 *  	this synonyms can also be search for in the onto equal class 
	 *  		however we can use word net to get syn set thus synonyms ..
	 *  
	 *   making use of this helper method to achieved the above
	 *   comment.......
	 */
	public Set<String> findQueryExpansion(List<String> words) {
		
		
		Set<String> expansion = new HashSet<>(); 
		queryTags.clear(); 
		 
		
		
		for(String word : words) {	
			
			List<String> syn = Wordnet.getSynonyms(word); 
			expansion.addAll(syn);
			queryTags.add(new Tagging(word , syn)); 
			
		}// end of for loop 
		
		
		return expansion ; 
		
	}//
	
	public List<Tagging> getQueryTags(){
		
		return queryTags ; 
	}

	public boolean isExpansionFormed() {

		Set<LinkedHolder> links = Ontology.getLinkeHolder() ; 
		
		if(!links.isEmpty()) 
		{
			return true ;
		
		}else {
			
			return false;
		}
		
	}


}
