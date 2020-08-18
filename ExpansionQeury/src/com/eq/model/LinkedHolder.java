package com.eq.model;

import java.util.List;
import java.util.Objects;

import WordNet.Wordnet;
import ontology.Ontology;
import ontology.Relation;

public class LinkedHolder {

	private Relevance relevance;
	private Relation relation;
	private String rele ; 
	private String rela ; 
	private String expansionData ; 
	
	
	public String getExpansionData() {
		return expansionData;
	}

	public void setExpansionData(String expansionData) {
		this.expansionData = expansionData;
	}


	public String getRele() {
		return rele;
	}

	public void setRele(String rele) {
		this.rele = rele;
	}

	public String getRela() {
		return rela;
	}

	public void setRela(String rela) {
		this.rela = rela;
	}

	public LinkedHolder(){
		
	}
	
	public LinkedHolder(Relation relation , Relevance relevance) {
		
		this.relation = relation ; 
		this.relevance = relevance; 
		 
	}

	public static double getMaxRelevance() {
		
		return Ontology.getMAX_RELEVANCE() ; 
	}
	
	public Relevance getRelevance() {
		return relevance;
	}

	public void setRelevance(Relevance relevance) {
		this.relevance = relevance;
	}

	public Relation getRelation() {
		return relation;
	}

	
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	
//	private  void get_expansion_meaning() {
//
//			System.out.println("PRINTING EXPANSION MEANING... FOR    ----- > " + relevance);
//			List<String> words = getRelevance().getDocument();
//			
//			
//			for (String word : words) {
//				
//				Wordnet.searchSemanticWordNet(word.trim().toLowerCase());
//
//			}// end of inner for loops 
//					
//	}// end of method.....
//
//	public String toString() {
//
//		return String.format("[%s %s]", getRelevance().toString(), getRelation().toString());
//	}

	@Override
	public int hashCode() {
		return Objects.hash(rela, rele);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedHolder other = (LinkedHolder) obj;
		return Objects.equals(rela, other.rela) && Objects.equals(rele, other.rele);
	}

}
