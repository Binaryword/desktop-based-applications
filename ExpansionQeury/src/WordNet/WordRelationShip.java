package WordNet;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class WordRelationShip {

	private WordNetRelation relationConstant;
	private List<String> relationships;

	public WordRelationShip(WordNetRelation relationConstant, List<String> relationList) {
		super();
		this.relationConstant = relationConstant;
		this.relationships = relationList;
	}

	public WordNetRelation getRelationConstant() {
		return relationConstant;
	}

	public void setRelationConstant(WordNetRelation relationConstant) {
		this.relationConstant = relationConstant;
	}

	public List<String> getRelationships() {
		return relationships;
	}

	public List<String> getFilteredRelationships(List<String> fL) {

		Set<String> list = new LinkedHashSet<>();

		if (getRelationConstant() == WordNetRelation.HYPERNYMS) {
			for (String w : fL) {
				for (String r : getRelationships()) {
					if (r.equals(w.toLowerCase()) || r.endsWith(w)) {
						list.add(r);
						list.add(w); 
					} // for inner
				} // for outer
			}

		} else {

			for (String w : fL) {
				for (String r : getRelationships()) {
					if (r.equals((w.toLowerCase()))) {
						list.add(r);
					} // for inner
				} // for outer
			}
		}
		relationships.clear();
		relationships.addAll(list);
		return relationships;
	}

	public void setRelationList(List<String> relationList) {

		this.relationships = relationList;
	}

	@Override
	public String toString() {
		return "WordRelationShip [relationConstant = " + relationConstant + ", relationList = " + relationships + "]";
	}

}
