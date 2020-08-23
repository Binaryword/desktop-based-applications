package WordNet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordPresicion {

	private static List<String> newWordList = new ArrayList<>();
	private static String initWord;
	public static boolean runOnce = false;

	public static boolean wordStemming(String word) {

		List<String> wordList = Arrays.asList(word.split(""));

		if (!runOnce) {
			newWordList.clear();
			runOnce = true;
			newWordList.addAll(wordList);
//			System.out.println("PRINTING ONCE CONDITION.") ; 

		} else {

			newWordList.clear();

//			System.out.println((wordList));

			for (int i = 0; i < (wordList.size() - 1); i++) {
				newWordList.add(wordList.get(i));
			}

			//System.out.println("new wordList = " + newWordList);
		}
		

		if (wordList.size() > 2)
			return true;
		else
			return false;

	}// end of method.....

	public static void setInitWord(String word) {

		initWord = null;
		initWord = word;
	}

	public static void resetWordList() {
		newWordList.clear();
	}

	public static String getWord() {

		StringBuilder word = new StringBuilder();

		if (newWordList.isEmpty())
			return word.append(initWord).toString().toLowerCase().trim();

		for (String w : newWordList) {
			word.append(w);
		}

		return word.toString().trim().toLowerCase();
	}

	public static WordCorpus getWordCorpus() {

		return new WordCorpus();
	}



}
