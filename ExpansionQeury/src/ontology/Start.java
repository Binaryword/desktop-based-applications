package ontology;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Start {

	public static void main(String[] args) {
		
		//Ontology.readOntology(new File("maizeFarmingOntology_MODIFY3_VALIDATION.owl"));
		Ontology.readOntology(new File("newOntFile.owl"));
		Ontology.initOntology();
	 Ontology.printAllObjectProperty();
	//   Ontology.printAllSubClasses();
			//Ontology.printRelation();
		//Ontology.encodeOntology("is_a" , "Tree" , "Plant" ,   new ArrayList<>(Arrays.asList("tree1" , "Tree2"))  , 	null , false , false) ;
		//Ontology.encodeOntology("is_from" , "Mango" , "Fruit" ,  null  , 	null , false , false) ;
		//Ontology.updateOntology("newOntFile.owl");
		Ontology.printAllClasses();
	//	Ontology.printOntology();
		Ontology.printActiveOntology();
		
		
		
	}
}
