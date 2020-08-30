package WordNet;

import java.util.ArrayList;
import java.util.List;

public class DocumentList {
	
	List<List<Document>> document = new ArrayList<>() ; 
	
	
	public DocumentList() {}

	
	public void addDocument(List<Document> document) 
	{
		this.document.add(document); 
	}
	
	public int getDocumentSize(){
		
		return this.document.size() ; 
	}
	
	
}
