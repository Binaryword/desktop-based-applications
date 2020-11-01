package WordNet;

public enum WordNetRelation {
	
	HOLONYMS("part_of" , "holonyms") , 
	MERONYMS("has" , "meronyms") , 
	HYPERNYMS("is_a" , "hypernyms") , 
	HYPONYMS("consist_of" , "hyponyms") ;  
	
	private String relation ;
	private String type; 
	
	WordNetRelation(String relation , String type) {
		this.relation = relation ; 
		this.type = type ; 
	}
	public String getRelation() {
		return relation;
	}
	public String getType() {
		return type;
	}


}
