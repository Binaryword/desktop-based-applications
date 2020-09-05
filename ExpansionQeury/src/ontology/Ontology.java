package ontology;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;

import com.eq.model.LinkedHolder;
import com.eq.model.Relevance;
import com.eq.model.TFIDFCalculator;
import com.eq.model.TextPreprocessing;

import WordNet.Wordnet;

public class Ontology {

	private static ArrayList<String> objectPropertyList = new ArrayList<String>();
	private static ArrayList<String> subClassList = new ArrayList<String>();
	private static ArrayList<String> classList = new ArrayList<String>();
	private static OntModel ontModel = ModelFactory.createOntologyModel();
	private static List<Relation> relationList = new ArrayList<>();
	private static List<List<String>> documents = new ArrayList<>();
	private static String ontologyUrl;
	private static List<Relevance> relevanceList = new ArrayList<>();
	private static List<Relevance> pro_relevance = new ArrayList<>();
	private static Set<LinkedHolder> linkeList = new HashSet<>();
	private static String workspace1 = "https://staff.futminna.edu.ng/website_home.php?id2=82a324332344/maizeFarmingOntology_MODIFY3_VALIDATION.owl#";
	private static String workspace2 = "https://staff.futminna.edu.ng/website_home.php?id2=82a324332344/maizeFarmingOntology_MODIFY2_VALIDATION.owl#";
	private static String workspace3 = "http://www.semanticweb.org/hp/ontologies/2018/11/untitled-ontology-38#";

	// private static List<String> expansionMeaningList = new ArrayList<>();
	public static double queryRelevance = 0.00;

	public static ArrayList<String> getObjectPropertyList() {
		return objectPropertyList;
	}

	public static void setObjectPropertyList(ArrayList<String> objectPropertyList) {
		Ontology.objectPropertyList = objectPropertyList;
	}

	public static ArrayList<String> getClassList() {
		return classList;
	}

	public static void setClassList(ArrayList<String> classList) {
		Ontology.classList = classList;
	}

	public static void readOntology(File file) {

		System.out.println("file name is " + file.getName());

		try {

			InputStream inputStream = new FileInputStream(file.getAbsolutePath());
			if (inputStream != null)
				ontModel.read(inputStream, null);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}//

	public static void printOntology() {
		ontModel.write(System.out);

	}

	public static void initOntology() {

		setOntologyUrl();
		initializeClass();
		initializeObjectProperty();
		composeRelation(getObjectPropertyList());

	}

	public static void printActiveOntology() {

		System.out.println("\t\tPRINT ACTIVE ONTOLOGY");
		System.out.println("=====> /n SIZE OF ONTOOGY CLASS  : " + classList.size());
		System.out.println("=====> /n SIZE OF ONTOOGY SUB CLASS  : " + subClassList.size());
		System.out.println("=====> /n SIZE OF ONTOOGY OBJECT PROPERTY  : " + objectPropertyList.size());
	}

	public void setOntologyUrl(String url) {

	}

	private static void setOntologyUrl() {

		ExtendedIterator<OntClass> ontologyClasses = ontModel.listClasses();

		if (ontologyClasses != null) {

			if (ontologyClasses.hasNext()) {

				OntClass cl = ontologyClasses.next();
				String[] array = cl.toString().split("#");
				ontologyUrl = array[0];

			} // end of inner if

		} // end of outer if statement ...

	}// end of method

	public static String getOntologyUrl() {

		// check if the ONTOLOGY as been initialized
		if (ontModel != null) {

			setOntologyUrl();
			return ontologyUrl.concat("#");
		}

		return null;
	}

	// ========================================= initializing the list
	// ==============================//

	public static void initializeClass() {

		ExtendedIterator<OntClass> ontologyClasses = ontModel.listClasses();

		if (ontologyClasses != null) {

			while (ontologyClasses.hasNext()) {

				OntClass ontologyClass = ontologyClasses.next();
				initializeSubClasses(ontologyClass);

				if (ontologyClass.getLocalName() == null)
					continue;

				classList.add(ontologyClass.toString());
			}

		}

	}

	public static void initializeObjectProperty() {

		ExtendedIterator<ObjectProperty> ontologyProperties = ontModel.listObjectProperties();

		if (ontologyProperties != null) {

			while (ontologyProperties.hasNext()) {

				ObjectProperty ontologyProperty = ontologyProperties.next();

				if (ontologyProperty.getLocalName() == null)
					continue;

				objectPropertyList.add(ontologyProperty.getLocalName());
			}

		}

	}

	public static void initializeSubClasses(OntClass ontClass) {

		if (ontClass.hasSubClass()) {

			ExtendedIterator<OntClass> subclasses = ontClass.listSubClasses();

			while (subclasses.hasNext()) {

				OntClass subclass = subclasses.next();

				if (subclass.getLocalName() == null)
					continue;

				subClassList.add(subclass.getLocalName());
			}

		}

	}//

	public static void printAllClasses() {

		System.out.println("list of All classes");

		for (String li : classList) {

			System.out.println(li);
		}

		System.out.println("=====> /n SIZE OF ONTOOGY CLASS  : " + classList.size());
	}// end of method

	public static void printAllSubClasses() {

		System.out.println("list of All subclasses");

		for (String li : subClassList) {

			System.out.println(li);
		}

		System.out.println("=====> /n SIZE OF ONTOOGY SUB CLASS  : " + subClassList.size());
	}// end of method

	public static void printAllObjectProperty() {

		System.out.println("list of All classes");

		for (String li : objectPropertyList) {

			System.out.println(li);

		}

		System.out.println("=====> /n SIZE OF ONTOOGY OBJECT PROPERTY  : " + objectPropertyList.size());

	}// end of method

	/*
	 * PREPROCESSED.... ACTIVE ONTOLOGY .....
	 */

	public static void composeRelation(List<String> obj) {

		for (String b : obj) {

			ObjectProperty object = ontModel.getObjectProperty(getOntologyUrl() + b);
			int idCounder = 0;
			if (object == null)
				continue;

			ExtendedIterator<? extends OntResource> range = object.listRange();
			ExtendedIterator<? extends OntResource> domain = object.listDomain();

//			System.out.println(object.getDomain().getLocalName() + "     " + object.getLocalName() + "     " + " "
//					+ object.getRange().getLocalName());

			List<String> dom = new ArrayList<>();
			List<String> rag = new ArrayList<>();

			while (domain.hasNext()) {

				OntClass domainClass = (OntClass) domain.next();
				if (domainClass == null)
					continue;

				dom.add(domainClass.getLocalName());
				// domSub.addAll(getOntologyUtilList(domainClass.listSubClasses().toList()));
				// domEqual.addAll(getOntologyUtilList(domainClass.listEquivalentClasses().toList()));

			} // end of while loop ...

			while (range.hasNext()) {

				OntClass rangeClass = (OntClass) range.next();
				if (rangeClass == null)
					continue;
				rag.add(rangeClass.getLocalName());
				// ragSub.addAll(getOntologyUtilList(rangeClass.listSubClasses().toList()));
				// ragEqual.addAll(getOntologyUtilList(rangeClass.listEquivalentClasses().toList()));
			}

			// System.out.println("Domain " + dom);
			// System.out.println("Range " + rag);

			if (!(dom.isEmpty() && rag.isEmpty())) {

				for (int d = 0; d < dom.size(); d++) {

					for (int r = 0; r < rag.size(); r++) {

						if (dom.get(d) != null && rag.get(r) != null) {

							Relation relation = new Relation();
							relation.setDomain(dom.get(d));
							relation.setObject(object.getLocalName().toString());
							relation.setRange(rag.get(r));
							relation.setRelationId(idCounder);
							idCounder++;
							relationList.add(relation);
							//System.out.println(relation.toString());

						} // if
					} // inner for

				} // outer for

			} // end of outer if statement

		} // end of for loop one

		System.out.println("Execution completed");
		System.out.println("\n Relation Size : " + relationList.size());

	}// end of method

	public static List<String> getSubClass(String concept) {

		OntClass classes = getWorkSpace(concept);

		if (classes == null)
			return new ArrayList<String>();

		//System.out.println(classes.getLocalName());

		return getOntologyUtilList(classes.listSubClasses().toList());
	}

	public static OntClass getWorkSpace(String concept) {

		List<OntClass> workspaces = new ArrayList<>();
		OntClass ontClass = null;
		
		workspaces.add(ontModel.getOntClass(workspace1 + concept));
		workspaces.add(ontModel.getOntClass(workspace2 + concept));
		workspaces.add(ontModel.getOntClass(workspace3 + concept));
		
		for (OntClass clas : workspaces) {

			if (clas != null) {

				ontClass = clas;

			} // end of if

		} // end of for loop

		return ontClass;
		
	}// end of method..

	public static List<String> get_sub_class(String word) {

		subClassList.clear();
		OntClass ontClass = ontModel.getOntClass(getOntologyUrl() + word);
		if (ontClass.hasSubClass()) {

			ExtendedIterator<OntClass> subclasses = ontClass.listSubClasses();

			while (subclasses.hasNext()) {

				OntClass subclass = subclasses.next();

				if (subclass.getLocalName() == null)
					continue;

				subClassList.add(subclass.getLocalName());
			}

		}

		return subClassList;

	}//

	public static List<String> getEqualClass(String concept) {

		OntClass classes = getWorkSpace(concept);

		//System.out.println("OntClass----> " + classes);

		if (classes == null)
			return new ArrayList<String>();

		//System.out.println("OntClass localName ---> " + classes.getLocalName());

		return getOntologyUtilList(classes.listEquivalentClasses().toList());
	}
	
	public static List<String> getClassIndividuals(String clas) {

		OntClass classes = getWorkSpace(clas);

		if(classes == null)
			return new ArrayList<String>();
			
		Iterator<? extends OntResource> iterator = classes.listInstances() ; 
		List<String> individualList = new LinkedList<>(); 
		
		while(iterator.hasNext()) 
		{
			Individual individual = (Individual) iterator.next() ; 
			individualList.add(individual.getLocalName()); 
			//System.out.println("Individual localName ---> " + individual.getLocalName());
		}



		return individualList ; 
	}

	
	

	public static List<String> getOntologyUtilList(List<OntClass> concept) {

		List<String> con = new ArrayList<>();

		if (concept.isEmpty())
			return con;

		for (OntClass cl : concept) {

			con.add(cl.getLocalName());

		} // end of for loop ....

		return con;
	}
	
	

	public static void initDocument() {

		for (Relation relation : relationList) {

			relation.setDomain(relation.getDomain().replace("PH", " ph "));
			relation.setRange(relation.getRange().replace("PH", " ph "));
			relation.setObject(relation.getObject().replace("PH", " ph "));
			documents.add(relation.getDocument());
		}

	}// end of method...

	public static List<Relation> getRelation() {

		return relationList;
	}

	// document helper method

	public static List<List<String>> getDocuments() {

		return documents;
	}

	public static void printDocuments() {

		for (List<String> doc : documents) {

			//System.out.println("DOCUMENT ===============================  >  : " + doc);

		} // outer for loop

//		System.out.println("DOCUMENT SIZE =============================   > " + documents.size());
	}

	public static int getDocumentSize() {

		return documents.size();
	}

	public static void computeWordRelevance(List<String> queryList) {

		TFIDFCalculator calculator = new TFIDFCalculator();
		relevanceList.clear();

		for (List<String> doc : documents) {

			queryRelevance = 0.0;

			for (String candidateTerm : queryList) {

				double tfidf = calculator.tfIdf(doc, documents, candidateTerm);
				queryRelevance = queryRelevance + tfidf;
//				System.out.println("TF-IDF OF (" + candidateTerm + ")  = " + tfidf);

			}

//			System.out.println("Qury Relevance " + queryRelevance);
			Relevance relevance = new Relevance(queryRelevance, doc);
			relevanceList.add(relevance);
//			System.out.println(relevance);

		} // outer for loop

		processValideRelevance();

	}// end of method.....

	public static void processValideRelevance() {

		// List<Relevance> pro_relevance = new ArrayList<>();
		pro_relevance.clear();
		
		double maxdigit = getRelevanceDigitCondition(relevanceList);

		if (maxdigit > 10.0) 
		{
			
			for (Relevance relevance : relevanceList) {
				// checking if user query is 50 percent relevance to document...
				if (relevance.getDigitPercent() >= maxdigit) {
					
					pro_relevance.add(relevance);
				}
				
			} // end of for loop ....
			
			printRelevantDoc();
			
		}
		

	}// end of method
	
	public static double getMAX_RELEVANCE() {
		
		return getRelevanceDigitCondition(relevanceList);
		
	}
	
	public static double getRelevanceDigitCondition(List<Relevance> rel) {
		
		double maxdigit = 0.0 ; 
		
		for(Relevance r : rel) {
			
			if(r.getDigitPercent() > maxdigit) 
			{
				maxdigit = r.getDigitPercent() ; 
				
			}//end of if 
		}
		
		System.out.println("Maximum relvance1 .. " + maxdigit ); 
		
		double digit = (maxdigit / 4) ; 
		maxdigit = (maxdigit - digit) ; 
		
		System.out.println("Maximum relvance2 .. " + maxdigit ); 
		return  maxdigit ; 
	}

	public static void printRelevantDoc() {

		System.out.println(".........................   USER QUERY DOCUMENT PULL  ....................... "
				+ pro_relevance.size());

		for (Relevance relevance : pro_relevance) {

			//System.out.println(relevance);
		}

		documentsMatcher();

	}// end of method... 

	public static List<Relevance> getRelevance() {

		return pro_relevance;
	}

	public static String activate_expansion_meaning(List<String> documents) {

		// expansionMeaningList.clear();
		StringBuilder builder = new StringBuilder();
		for (String word : documents) {

			Wordnet.searchSemanticWordNet(word.trim().toLowerCase());
			String meaningBuilder = Wordnet.getWordMeaningBuilder();
//						System.out.println("from expansion : \n"  + meaningBuilder);
			builder.append(meaningBuilder);
			// expansionMeaningList.add(meaningBuilder);
		} // end of inner for loops

		return builder.toString();
	}// end of method....

	public static void documentsMatcher() {

		linkeList.clear();

		for (Relevance reli : pro_relevance) {

			for (Relation rel : relationList) {
				String relation = rel.getCombineString().replaceAll("_", "").trim().toLowerCase();
				String relevance = reli.combineDocument();
				// System.out.println("relation : " + relation +" relevant : " + relevance );

				if (relation.matches(relevance)) {

					LinkedHolder older = new LinkedHolder(rel, reli);
					older.setRela(relation);
					older.setRele(relevance);
					older.setRelation(rel);
					older.setRelevance(reli);
					List<String> doc = rel.getDocument();
					List<String> clean_doc = TextPreprocessing.remove_stopword(doc);
					List<String> lemmaDocumentList = new ArrayList<>();

					for (String w : clean_doc) {
						lemmaDocumentList.add(TextPreprocessing.lemmatize_word(w));
					}

					String conceptMeaning = activate_expansion_meaning(lemmaDocumentList);
					older.setExpansionData(conceptMeaning);
					linkeList.add(older);

				} // if
			}
		}

		System.out.println("SIZE OF LINKED ::::: " + linkeList.size());
	}

	public static Set<LinkedHolder> getLinkeHolder() {

		return linkeList;
	}

	public static int getLinkedSize() {

		return linkeList.size();
	}

	public static Set<String> getFilterCandidateTerm(List<String> lemmaUserQueryList) {
		
		List<String> domain = new LinkedList<>();
		List<String> range = new LinkedList<>();
		List<String> obj = new LinkedList<>();
		Set<String> filter = new HashSet<>(); 
		
		for(Relation rel : relationList) 
		{
			
			domain.add(rel.getDomain().toLowerCase()); 
			range.add(rel.getRange().toLowerCase());
			obj.add(rel.getObject().toLowerCase()); 
			
		}
		
		for(String query : lemmaUserQueryList) 
		{
			// check for domain match
			for(String d : domain) 
			{
				if(d.contains(query)) {
					filter.add(query); 
				} 
				
			}
			
			/// check for range match
			for(String r : range) 
			{
				if(r.contains(query)) {
					filter.add(query); 
				} 
				
			}
			
			// check for object match
			for(String o : obj) 
			{
				if(o.contains(query)) {
					filter.add(query); 
				} 
				
			}
			
		}
		
		
		
		
	System.out.println("Filter size " + filter.size()); 
		//System.out.println(filter); 
		return filter;
	}
	
	public static Set<String> getAllOntologyConcept() {
		
		List<String> domain = new LinkedList<>();
		List<String> range = new LinkedList<>();
		//List<String> object = new LinkedList<>();
		Set<String> filterList = new HashSet<>(); 
		
		
		for(Relation rel : relationList) 
		{
			
			domain.add(rel.getDomain()); 
			domain.addAll(rel.getDomainEqual());
			domain.addAll(rel.getDomainSub()); 
			//domain.addAll(rel.getDomainIntances()); 
			range.add(rel.getRange());
			range.addAll(rel.getRangeEqual()); 
			//range.addAll(rel.getRangeIntances()); 
			range.addAll(rel.getRangeSub());
			
		}
		
		
		filterList.addAll(domain); 
		filterList.addAll(range); 	
	
		return filterList ; 
	}

	/*
	 * sort document based on percentage relevance..
	 */
//	public static void sortDocument() {
//
//		System.out.println("PRINTING THE SORTED RELEVANCE OF DOCUMENT......"); 
//		double max = 0.0 ; 
//		List<Relevance> tempList = new ArrayList<>(); 
//	
//		
////		tempList.add(listSorter(pro_relevance)); 
////
////		for(Relevance relevance : tempList)
////				System.out.println(relevance);
////	
//		
//	}//
//	

//	public static Relevance listSorter(List<Relevance> list) {
//		
//		double max = 0 ; 
//		
//		Iterator<Relevance> it = list.iterator() ; 
//		Relevance maxRel = null ; 
//		
//		if(list.size() == 1 )
//			return list.get(0); 
//		
//		if(!list.isEmpty()) {
//			max = list.get(0).getDigitPercent(); 
//			
//			while(it.hasNext()) 
//			{
//				Relevance rel = it.next() ; 
//				
//					if(rel.getDigitPercent() > max) {
//						
//						max = rel.getDigitPercent();
//					    maxRel = rel ; 
//					    it.remove();
//					}
//					
//					
//				
//			}// for loop ........ end of methods.......		
//
//			listSorter(list); 
//			
//		}// if statement... 
//		else {
//			
//			//return null; 
//			System.out.println("this cannot occur...");
//		}
//	
//		return  maxRel; 
//	}// end  of method...

}
