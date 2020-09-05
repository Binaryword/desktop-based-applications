package WordNet;

import java.util.ArrayList;
import java.util.List;

public class TFIDFCalculator {
    /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
	
	private List<String> foundList = new ArrayList<>(); 
	
    public double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
        	if(term.isEmpty() || term =="")
    			continue ;
            if (word.toLowerCase().contains(term)) {
            	foundList.add(term); 
            	result++; 
            }
        }
        
        //System.out.print("TF=" + result/doc.size() + " |" );
        return result / doc.size();
    }

    /**
     * @param docs list of list of strings represents the data set
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
        		if( term.isEmpty() || term == "" )
        			continue ; 
            for (String word : doc) {
                if (word.toLowerCase().contains(term) ) {
                	foundList.add(term); 
                    n++;
                    break;
                }
            }
        }
        
        //System.out.print("IDF=" + Math.log(docs.size()+1) /n + ""  + "  ||  " );
        return Math.log(docs.size()+1 / n);
    }
    
    
    public List<String> getMatchTerms() {
    	return foundList ;  	
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
    	
    	double result = tf(doc, term) * idf(docs, term) ;
    	
    	if( String.valueOf(result) == "NaN" )
    		return 0.0 ; 
    	else 
    		return result ; 

    }

//    public static void main(String[] args) {
//
//        List<String> doc1 = Arrays.asList("Lorem", "ipsums", "dolor", "ipsums", "sit", "ipsums");
//        List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
//        List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
//        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);
//
//        TFIDFCalculator calculator = new TFIDFCalculator();
//        double tfidf1 = calculator.tfIdf(doc1, documents, "ipsum");
//        double tfidf2 = calculator.tfIdf(doc2, documents, "ipsum");
//        double tfidf3 = calculator.tfIdf(doc3, documents, "ipsum");
//        System.out.println("TF-IDF (ipsum) = " + tfidf1*100);
//        System.out.println("TF-IDF (ipsum) = " + tfidf2*100);
//        System.out.println("TF-IDF (ipsum) = " + tfidf3*100);
//
//
//    }


}
