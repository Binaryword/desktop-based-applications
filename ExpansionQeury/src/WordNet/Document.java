package WordNet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.POS;

public class Document {

	private List<String> ontTerm;
	private List<String> document ; 
	private List<String> matchList ; 
	private int senseIndex;
	private double relevanceValue ; 
	private IIndexWord w;
	private List<List<String>> allDoc = new ArrayList<>(); 

	public Document(List<String> doc ,  int index,  IIndexWord w) {
		
		document = new ArrayList<>();
		ontTerm = new LinkedList<>();
		this.document.addAll(doc) ; 
		this.senseIndex = index;
		this.w = w;
	}

	public void toLowerCase(Set<String> document2) {

		for (String doc : document2)
			doc.toLowerCase();
	}

	
	public List<String> getOntoTerm() {
		return ontTerm;
	}

	public void setOntologyterm(List<String> ontologyterm) {
		this.ontTerm = ontologyterm;
	}

	public List<String> getDocument() {
		return document;
	}

	public void setDocument(List<String> document) {
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
	
	public POS getPOS() {
		
		return w.getPOS() ;
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

		for (String doc : getDocument()) {
			builder.append("TOK => : " + doc);
			builder.append("\n");
		}

		return String.format("%s", builder.toString());
	}

	public void computeRelevanceToOntology(List<String> terms) {

		TFIDFCalculator cal  = null ; 
		matchList = new ArrayList<>();
		double totalRelevance = 0.0 ; 
		ontTerm.addAll(terms) ; 
		// for each senses in the word net
		//for(List<String> doct : getDocument()) {System.out.println("Ont Doc : " + doct); }

				for(String term : terms) 
				{
				
					cal = new TFIDFCalculator(); 
					//term = TextPreprocessing.lemmatize_word(term); 
					double value = cal.tfIdf(    getDocument()  , allDoc , term.toLowerCase());
					matchList.addAll(cal.getMatchTerms()); 
					//System.out.println("TF-IDF (ipsum) = of (" + term + ")  is "  + value); 
					totalRelevance = totalRelevance + value ;
				
				  
			} // inner loop..
		
				// after computing for a specific 
				setMatchList(matchList);
				setDocumentRelevanceValue(totalRelevance); 
				System.out.print(getDocument());
				System.out.println("||-->> Total relevance of sense  (" + getSenseIndex() + ")  =>>| " +  getDocumentRelevanceValue());
				//System.out.println(getDocument());

		
	}// end of method..

	public List<String> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<String> matchList) {
		System.out.println("Match : " + matchList);
		this.matchList = matchList;
	}

	public void setDocumentRelevanceValue(double totalRelevance) {

		relevanceValue = totalRelevance ; 
	}
	
	
	
	public double getDocumentRelevanceValue() {
		
		return ( relevanceValue )  ; 
	}

	public void setAllDocument(List<List<String>> documents) {
		
		allDoc.clear();
		allDoc.add(document); 
		
	}

	
	

}// end of class
