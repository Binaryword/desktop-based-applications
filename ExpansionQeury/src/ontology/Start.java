package ontology;

import java.io.File;

public class Start {

	public static void main(String[] args) {
		
		Ontology.readOntology(new File("maizeFarmingOntology_MODIFY3_VALIDATION.owl"));
		//Ontology.readOntology(new File("agrovoc_2020-06-01_core.owl"));
		//Ontology.printOntology();
		Ontology.initOntology();
	   // Ontology.printAllClasses();
	// Ontology.printAllObjectProperty();
	//   Ontology.printAllSubClasses();
		Ontology.printActiveOntology();
		//Ontology.printRelation();
		
		
		
	}
}
