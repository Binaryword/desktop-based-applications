package WordNet;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.mit.jwi.item.IIndexWord;

public class Document {

	private Set<String> document;
	private List<List<String>> documents;
	private int senseIndex;
	private double relevanceValue ; 
	private IIndexWord w;

	public Document(Set<String> doc, int index, IIndexWord w) {
		
		documents = new ArrayList<>();
		document = new LinkedHashSet<>();
		this.document.addAll(doc);
		this.senseIndex = index;
		this.w = w;
	}

	public void toLowerCase(Set<String> document2) {

		for (String doc : document2)
			doc.toLowerCase();
	}

	public List<String> getDocument() {
		
		return new ArrayList<>(document);
	}

	public void setDocument(Set<String> document) {
		this.document = document;
	}

	public int getSenseIndex() {
		return senseIndex;
	}

	public void setSenseIndex(int senseIndex) {
		this.senseIndex = senseIndex;
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

		for (String doc : getDocument()) {
			builder.append("TOK => : " + doc);
			builder.append("\n");
		}

		return String.format("%s", builder.toString());
	}

	public void computeRelevanceToOntology(List<String> termList) {

		TFIDFCalculator cal = new TFIDFCalculator();
		double totalRelevance = 0.0 ; 
		// for each senses in the word net
		
			// for each term in the onto
			for (String term : termList) {

				 double value = cal.tfIdf(getDocument() , getAllDocument() , term.toLowerCase());
				 System.out.println("TF-IDF (ipsum) = of (" + term + ")  is "  + value); 
				 totalRelevance = totalRelevance + value ;
				  
			} // inner loop..
		
		setDocumentRelevanceValue(totalRelevance); 
		System.out.println("Total relevance of sense  (" + getSenseIndex() + ")  ===>>| " +  getDocumentRelevanceValue());

	}// end of method..

	public void setDocumentRelevanceValue(double totalRelevance) {

		relevanceValue = totalRelevance ; 
	}
	
	public double getDocumentRelevanceValue() {
		
		return ( relevanceValue ) * 100 ; 
	}

	public void setAllDocument(List<List<String>> documents2) {

		documents = documents2;
	}

	public List<List<String>> getAllDocument() {

		return documents;
	}

}// end of class
