package ontology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.eq.model.TextPreprocessing;

public class Relation {

	private String domain;
	private String range;
	private String object;
	private List<String> doc = new ArrayList<>();
	private int relationId;

	public Relation() {

	}

	public Relation(String domain, String object, String range) {

		this.domain = domain;
		this.object = object;
		this.range = range;

	}

	public List<String> getDomainSub() {
		
		return  Ontology.getSubClass(getDomain());
	}


	public List<String> getRangeSub() {
		return Ontology.getSubClass(getRange());
	}


	public List<String> getDomainEqual() {
		//System.out.println(Ontology.getEqualClass(getDomain()));
		return Ontology.getEqualClass(getDomain());
	}

	public List<String> getRangeEqual() {
		return Ontology.getEqualClass(getRange());
	}
	
	public List<String> getDomainIntances(){
		return Ontology.getClassIndividuals(getDomain()) ; 
	}
	
	public List<String> getRangeIntances(){
		return Ontology.getClassIndividuals(getRange()) ; 
	}

	public int getRelationId() {
		return relationId;
	}

	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String toString() {

		return String.format("Relation [%s %s %s]", getDomain(), getObject(), getRange(), getRelationId());
	}

	public String getCombineString() {

		return String.format("%s%s%s", getDomain(), getObject(), getRange(), getRelationId());
	}

	public String getReadableString() {

		StringBuilder builder = new StringBuilder();
		builder.append(getDomain()).append(" ").append(getObject()).append(" ").append(getRange());
		String relation = builder.toString();
		List<String> list = Arrays.asList(relation.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])|(\\s+)|(_)"));
		doc.clear();
		doc.addAll(list);
		StringBuilder builder2 = new StringBuilder();

		for (String b : list) {

			builder2.append(b).append(" ");
		}

		return builder2.toString();

	}

	public List<String> getDocument() {

		getReadableString();
		List<String> document = new ArrayList<>();

		for (String eachword : doc) {
			if (eachword.trim().matches("\\s*"))
				continue;
			document.add(eachword.trim().toLowerCase());
		}

		return document;
	}
}
