package WordNet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.IRAMDictionary;
import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

public class Wordnet {

	private static IDictionary dict;
	private static int wordNetSence = 0;
	private static List<List<String>> documents = new ArrayList<>();
	private static List<List<String>> ontDocuments = new ArrayList<>();
	private static List<Relevance> relevanceList = new ArrayList<>();
	private static StringBuilder wordBuilder = new StringBuilder();
	private static LinkedHashSet<String> ontTermList;

	public static void loadWordNet(File wordnetLocation) {

		IRAMDictionary dict = new RAMDictionary(wordnetLocation, ILoadPolicy.NO_LOAD);
		try {

			dict.load(true);
			System.out.println("Wordnet loaded successfully into memory");
		} catch (InterruptedException e) {

			e.printStackTrace();
			System.out.println("unable to load wordnet");
		}

	}// end of method

	public static void loadWordNet(String wordnetlocation) {

		URL url;
		try {

			url = new URL("file", null, wordnetlocation);
			dict = new Dictionary(url);
			dict.open();
			System.out.println("Wordnet loaded successfully");
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("unable to load wordnet");
		}

	}// end of method

	public static void searchWordNet(String word) {

		List<IIndexWord> indexWord = indexAllSubnet(word);
		// System.out.println("POS size " + indexWord.size());
		int totalSenses = 0;
		wordNetSence = 0;

		// loop throw all sub net and get appropriate senses
		for (IIndexWord w : indexWord) {

			// if part of speech is not found skip .....
			if (w == null)
				continue;

			List<IWord> senseWord = getSenses(w);
			totalSenses += senseWord.size();
			System.out.println("\n" + word.toUpperCase() + " HAS " + senseWord.size() + " SENCES FROM  "
					+ w.getPOS().toString().toUpperCase() + "  SEBNET \n");

			printWordNet(senseWord);
			getStemWord(word.toLowerCase());
		}

		wordNetSence = totalSenses;

		if (totalSenses <= 0) {

			findPrecision(word, false);

		} // end of outer if

	}// end of method

	/*
	 * helper methods to print the best semantic sense of the documents..
	 */

	public static void searchSemanticWordNet(String word) {

		List<IIndexWord> indexWord = indexAllSubnet(word);
		int totalSenses = 0;
		wordNetSence = 0;
		wordBuilder = new StringBuilder();

		// loop throw all sub net and get appropriate senses
		for (IIndexWord w : indexWord) {

			// if part of speech is not found skip .....
			if (w == null)
				continue;

			if (w.getPOS() != POS.NOUN)
				return;

			List<IWord> senseWord = getSenses(w);
			totalSenses += senseWord.size();
//			System.out.println("\n" + word.toUpperCase() + " HAS "
//					+ senseWord.size() + " SENCES FROM  "
//					+ w.getPOS().toString().toUpperCase() + "  SEBNET \n");

			wordBuilder.append("\n");
			wordBuilder.append(word.toUpperCase());
			wordBuilder.append(" HAS ");
			wordBuilder.append(senseWord.size());
			wordBuilder.append(" SENCES FROM ");
			wordBuilder.append(w.getPOS().toString().toUpperCase()).append(" SUBNET ");
			wordBuilder.append("\n");
			wordBuilder.append(printWordNet(senseWord));
			getStemWord(word.toLowerCase());
		}

		wordNetSence = totalSenses;

		if (totalSenses <= 0) {

			findPrecision(word, true);

		} // end of outer if

	} // end of methods...

	public static List<String> getSynonyms(String word) {

		List<IIndexWord> indexWord = indexAllSubnet(word);
		List<String> syn = new ArrayList<>();
		// loop throw all sub net and get appropriate senses
		for (IIndexWord w : indexWord) {
			// if part of speech is not found skip .....
			if (w == null)
				continue;

			if (w.getPOS() == POS.NOUN || w.getPOS() == POS.VERB) {
				List<IWord> senseWord = getSenses(w);

				if (senseWord.isEmpty())
					continue;

				IWord wrd = senseWord.get(0);
				syn.addAll(getSynset(wrd));

			} // end of if ...

		} // end of for loop ...

		return syn;

	}// end of method ..

	public static String getWordMeaningBuilder() {

		return wordBuilder.toString();
	}

	/*
	 * print semantic word net based on .... TF-IDF calculation...
	 */
	public static String printSementicWordNet(List<IWord> word) {

		documents.clear();
		StringBuilder builder = new StringBuilder();

		for (IWord w : word) {

			// printing sysnset associated to word
			for (String syn : getSynset(w)) {

				builder.append(syn + ", ");
			}

			builder.append("Defination = " + w.getSynset().getGloss());
			List<String> doc = TextPreprocessing.remove_stopword(
					TextPreprocessing.remove_punctuation(TextPreprocessing.tokenize_query(builder.toString())));
			documents.add(doc);

		}

		return builder.toString();
	}

	/*
	 * 
	 */

	public static void computeSementicRelevance(List<String> queryList) {

		TFIDFCalculator calculator = new TFIDFCalculator();
		relevanceList.clear();

		for (List<String> doc : documents) {

			for (String candidateTerm : queryList) {

				double tfidf = calculator.tfIdf(doc, documents, candidateTerm);
				System.out.println("TF-IDF OF (" + candidateTerm + ")  = " + tfidf);

			}

		} // outer for loop

		processValideRelevance();

	}// end of method.....

	public static void processValideRelevance() {

		// List<Relevance> pro_relevance = new ArrayList<>();
		double max = 0.0;
		Relevance rel = new Relevance();

		for (Relevance relevance : relevanceList) {

			// checking if user query is 50 percent relevance to document...
			if (relevance.getDigit() >= max) {
				max = relevance.getDigit();
				rel = relevance;
			}

			System.out.println(rel);

		} // end of for loop ....

		System.out.println("REL --------> " + rel);

	}// end of method

	/*
	 * 
	 */

	public static boolean checkWordSense(String word) {

		List<IIndexWord> indexWord = indexAllSubnet(word);
		// System.out.println("POS size " + indexWord.size());
		int totalSenses = 0;
		wordNetSence = 0;

		// loop throw all sub net and get appropriate senses
		for (IIndexWord w : indexWord) {

			// if part of speech is not found skip .....
			if (w == null)
				continue;

			List<IWord> senseWord = getSenses(w);
			totalSenses += senseWord.size();
		}

		wordNetSence = totalSenses;

		if (totalSenses > 0)
			return true;
		else
			return false;

	}

	public static List<IWord> getSenses(IIndexWord sences) {

		List<IWordID> wordIDList = sences.getWordIDs();
		List<IWord> senseList = new ArrayList<>();

		// for(IWordID wid : wordIDList)
		// System.out.println("word" + wid);

		for (IWordID wid : wordIDList)
			senseList.add(dict.getWord(wid));

		return senseList;
	}

	public static String printWordNet(List<IWord> word) {

		int counter = 0;
		StringBuilder builder = new StringBuilder();

		for (IWord w : word) {

			// System.out.print("( " + ++counter + " ). ");
			builder.append("( " + ++counter + " ).  ");

			// printing sysnset associated to word
			for (String syn : getSynset(w)) {

				// System.out.print(syn + ", ");
				builder.append(syn + ", ");
			}

			// System.out.println();
			builder.append("\n");

			// System.out.println("Id = " + w);
			// System.out.println("Lemma = " + w.getLemma());
			// System.out.println("Defination = " + w.getSynset().getGloss());
			builder.append("Defination = " + w.getSynset().getGloss());
			builder.append("\n");
		}

		return builder.toString();
	}

	// helper method to get the set of synonyms associated to a sense
	public static List<String> getSynset(IWord w) {

		List<String> syn = new ArrayList<>();
		ISynset synset = w.getSynset();

		for (IWord word : synset.getWords())
			syn.add(word.getLemma());

		return syn;
	}

	public static List<IIndexWord> indexAllSubnet(String word) {

		List<POS> part_of_speach = Arrays.asList(POS.values());
		List<IIndexWord> indexwordList = new ArrayList<>();

		for (POS pos : part_of_speach) {
			// checking all available part of speech
			IIndexWord iIndexWord = dict.getIndexWord(word, pos);
			// System.out.println(pos.toString());
			indexwordList.add(iIndexWord);
		}

		return indexwordList;
	}

	public static void getPartOfSpeech(String word) {

	}// end of method.....

	// ADDITTIONAL FUNCTIONALITY CAN BE ADDED..........

	public static void findPrecision(String word, boolean printCondition) {

		/*
		 * for word net sense to be zero its either 1. word not spell correctly ..... 2.
		 * it is a plural word ....... 3. or inflated words e.g goat ssssss ........
		 * 
		 * 
		 * if word not spell and sense is zero.... based on word net is either a plural
		 * word or inflected word....
		 */

		WordCorpus wordCorpus = WordPresicion.getWordCorpus();
		boolean wdNet = false;
		@SuppressWarnings("unused")
		boolean wdCorpus = false;
		String foundWordNet = null;
		String foundWordCorpus = null;
		WordPresicion.runOnce = false;

		// checking if word is spell correctly .. 1. first if word found in word
		// net...
		boolean spelling = wordCorpus.spellChecker(word);

		// System.out.println("WORD SPELT CORRECTLY..... ");

		// check for irregular pattern ..
		boolean valid_for_searching = WordPresicion.wordStemming(word);

		while (valid_for_searching) {

			System.out.println("LOOPS FROM WORD NET");

			valid_for_searching = WordPresicion.wordStemming(WordPresicion.getWord());

			System.out.println("ChECKING FAVLID FOR SEARCHING " + valid_for_searching);

			if (WordPresicion.getWord().length() <= 3) {
				System.out.println("word size is equal 3");
				break;
			}

			if (checkWordSense(WordPresicion.getWord().toLowerCase().trim())) {

				// if word is found .... in word net after stemming
				wdNet = true;
				foundWordNet = WordPresicion.getWord().toLowerCase().trim();
				break;

			} else {

				// findPrecision(WordPresicion.getWord().toLowerCase().trim());
				System.out.println("LOOP GOING TO BREAK..... ");
			}
			// if word is found in wordnet

		} // while loop bracket

		if (!wdNet) {

			String sWord = wordCorpus.convertToSingular(word);
			System.out.println("FIRST WORD FROM WORD CORPUSE " + sWord);

			if (sWord == null) {

				WordPresicion.runOnce = false;
				boolean condition = WordPresicion.wordStemming(word);

				while (condition) {

					System.out.println("LOOPS FROM WORD CORPUS");
					condition = WordPresicion.wordStemming(WordPresicion.getWord());
					System.out.println();
					sWord = wordCorpus.convertToSingular(WordPresicion.getWord());
					System.out.println("NEXT WORD FORM WORD CORPUS " + sWord);

					if (sWord != null) {
						wdCorpus = true;
						foundWordCorpus = sWord.toLowerCase().trim();
						break;

					} // end of first layer if

				} // while condition

			} // end of second layer if ...
			else {

				wdCorpus = true;
				foundWordCorpus = sWord.toLowerCase().trim();

			} // end of second layer if

		} // end of third layer if statement....

		System.out.println("From word net :::::::   " + foundWordNet);
		System.out.println("From word corpus :::::: " + foundWordCorpus);
		System.out.println("IS WORD SPELT CORRECTLY  " + spelling);

		if (printCondition == true) {

			if (foundWordNet != null)
				searchSemanticWordNet(foundWordNet);
			else if (foundWordCorpus != null)
				searchSemanticWordNet(foundWordCorpus);
			else
				System.out.println("Word suggestion alogrithm is required....");
		} else {

			if (foundWordNet != null)
				searchSemanticWordNet(foundWordNet);
			else if (foundWordCorpus != null)
				searchSemanticWordNet(foundWordCorpus);
			else
				System.out.println("Word suggestion alogrithm is required....");
		}

	}

	public static void getStemWord(String text) {

	}

	public static int getWordNetTotalSence() {

		return wordNetSence;
	}

	/*
	 * THE COMMENT ON USING WORDNET OR ONTOLOGY LEARNING
	 * =============================================================================
	 * ===== below are helper methods to get word hypernyms, holonyms , meronyms and
	 * the like 1. we need to compute the sense in which the term is closely related
	 * to the domain currently working on. 2. secondly we need to get the associated
	 * holonyms etc to specify some rule (thus onto learning aspect) 3. the used of
	 * TF-IDF to compute word relevance
	 * =============================================================================
	 * ===
	 */

	// search if word is noun
	public static boolean isNoun(String word) {

		IIndexWord iIndexWord = dict.getIndexWord(word, POS.NOUN);

		if (!iIndexWord.getWordIDs().isEmpty())
			return true;

		return false;
	}

	// search if word is verb
	public static boolean isVerb(String word) {

		IIndexWord iIndexWord = dict.getIndexWord(word, POS.VERB);

		if (!iIndexWord.getWordIDs().isEmpty())
			return true;

		return false;
	}

	// search if word is adjective
	public static boolean isAdjective(String word) {

		IIndexWord iIndexWord = dict.getIndexWord(word, POS.ADJECTIVE);

		if (!iIndexWord.getWordIDs().isEmpty())
			return true;

		return false;
	}

	// search if word is adverb
	public static boolean isAdverb(String word) {

		IIndexWord iIndexWord = dict.getIndexWord(word, POS.ADVERB);

		if (!iIndexWord.getWordIDs().isEmpty())
			return true;

		return false;
	}

	// computing best sense related agric domain using the domain onto

	// GETTING ONTOLOGY TERMS AND SIZE

	public static void getOntologyTerm(Set<String> ontTerm) {

		System.out.println("Size of ont term ==> " + ontTerm.size());
		ontTermList = new LinkedHashSet<>();

		for (String term : ontTerm) {
			System.out.println(term);
			if (term == null)
				continue;
			ontTermList.add(term.toLowerCase());
		}

	}// end of method

	public static void setOntologyDocument(List<List<String>> ontDoc) {

		ontDocuments = ontDoc;
	}

	public static List<List<String>> getOntologyDocument() {

		return ontDocuments;
	}

	public static void learnWordNetOntologyRelevance(String word) {

		Set<String> wordNetDocument = new LinkedHashSet<>();
		List<Document> documentList = new LinkedList<>();
		Document document = null;
		List<IIndexWord> indexWord = indexAllSubnet(word);
		// loop throw all sub net and get appropriate senses
		for (IIndexWord w : indexWord) {
			// if part of speech is not found skip .....
			if (w == null)
				continue;

			List<IWord> senseWord = getSenses(w);
			int senseIndex = 0;
			if (senseWord.isEmpty())
				continue;

			// loop throw each senses
			for (IWord wrd : senseWord) {

				wordNetDocument.addAll(getSynset(wrd));
				List<String> token = TextPreprocessing.tokenize_query(wrd.getSynset().getGloss());
				List<String> punct = TextPreprocessing.remove_punctuation(token);
				List<String> cleanWord = TextPreprocessing.remove_stopword(punct);
				wordNetDocument.addAll(cleanWord);
				/*
				 * Iterator<String> it = wordNetDocument.iterator(); while(it.hasNext()) {
				 * it.next().toLowerCase() ; }
				 */

				senseIndex++;

				document = new Document(wordNetDocument, senseIndex, w);
				documentList.add(document);
				// document.computeRelevanceToOntology(getOntologyDocument());
				wordNetDocument.clear();
			} // end of for

		} // end of for loop ...

		/*
		 * helper method to set or map all word net document to individual word net
		 * document
		 */
		initAllWordnetDocument(documentList);

		for (Document d : documentList) {

			 d.computeRelevanceToOntology(new ArrayList<>(ontTermList));
			 
		}
		
	}// end of method....

	public static void initAllWordnetDocument(List<Document> wNdoc) {

		List<List<String>> documents = new LinkedList<>();

		for (Document doc : wNdoc) {

			documents.add(doc.getDocument());

		}

		for (Document doc : wNdoc) {

			doc.setAllDocument(documents);

		}

	}// end of method

	public static void getHypernyms(int wordSense) {

	}

}// end of class
