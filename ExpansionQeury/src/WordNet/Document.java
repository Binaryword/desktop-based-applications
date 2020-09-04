package WordNet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.mit.jwi.item.IIndexWord;

public class Document {

	private List<String> ontologyterm;
	private List<List<String>> document ; 
	private int senseIndex;
	private double relevanceValue ; 
	private IIndexWord w;

	public Document(List<String> term, List<List<String>> doc ,  int index,  IIndexWord w) {
		
		document = new ArrayList<>();
		ontologyterm = new LinkedList<>();
		this.document.addAll(doc) ; 
		this.ontologyterm.addAll(term);
		this.senseIndex = index;
		this.w = w;
	}

	public void toLowerCase(Set<String> document2) {

		for (String doc : document2)
			doc.toLowerCase();
	}

	
	public List<String> getWordnetterm() {
		return ontologyterm;
	}

	public void setOntologyterm(List<String> ontologyterm) {
		this.ontologyterm = ontologyterm;
	}

	public List<List<String>> getDocument() {
		return document;
	}

	public void setDocument(List<List<String>> document) {
		this.document = document;
	}

	public int getSenseIndex() {
		return senseIndex;
	}

	public void setSenseIndex(int senseIndex) {
		this.senseIndex = senseIndex;
	}

	public double getRelevanceValue() {
		return relevanceValue;
	}

	public void setRelevanceValue(double relevanceValue) {
		this.relevanceValue = relevanceValue;
	}

	public String getPartOfSpeech() {

		return w.getPOS().name();
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Sense : " + getSenseIndex());
		builder.append("\n");
		builder.append("POS TAG : " + getPartOfSpeech());
		builder.append("\n");
		builder.append("TOKENS : ");
		builder.append(" \n ");

		for (String doc : getWordnetterm()) {
			builder.append("TOK => : " + doc);
			builder.append("\n");
		}

		return String.format("%s", builder.toString());
	}

	public void computeRelevanceToOntology() {

		TFIDFCalculator cal = new TFIDFCalculator();
		double totalRelevance = 0.0 ; 
		// for each senses in the word net
		

				for(List<String> doc : getDocument()) 
				{
					// for each term in the onto
					for (String term : getWordnetterm()) {
					
					//term = TextPreprocessing.lemmatize_word(term); 
					double value = cal.tfIdf( doc , getDocument() , term.toLowerCase());
				//	System.out.println("TF-IDF (ipsum) = of (" + term + ")  is "  + value); 
					totalRelevance = totalRelevance + value ;
				
				}
				
				// after computing for a specific 
				setDocumentRelevanceValue(totalRelevance); 
				System.out.print(getDocument());
				System.out.println("||-->> Total relevance of sense  (" + getSenseIndex() + ")  =>>| " +  getDocumentRelevanceValue());
				 
				  
			} // inner loop..
		

		
	}// end of method..

	public void setDocumentRelevanceValue(double totalRelevance) {

		relevanceValue = totalRelevance ; 
	}
	
	public double getDocumentRelevanceValue() {
		
		return ( relevanceValue ) * 100 ; 
	}


	

}// end of class
