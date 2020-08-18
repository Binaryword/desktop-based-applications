package WordNet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WordCorpus {

	private String path;
	List<String> spList = new ArrayList<>();
	List<String> words = new ArrayList<>();
	private static HashMap<String, String> spWords = new LinkedHashMap<>();

	public WordCorpus() {
		
		path = "SPCorpus";
		loadSPWords();
		loadWords();
		initDiction();

	}

	public void loadSPWords() {

		File file = new File(path);
		String spWord;

		try {

			FileReader reader = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(reader);
//			System.out.println("STOP WORD LIST");

			while ((spWord = bufferReader.readLine()) != null) {

				spList.add(spWord.toLowerCase());
			}


		} catch (FileNotFoundException e) {

			System.out.println(file.toString() + " not find");

		} catch (IOException e) {


		}

	}// end of method

	
	public void loadWords() {

		File file = new File("words_alpha.txt");
		String wd;

		try {

			FileReader reader = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(reader);

			while ((wd = bufferReader.readLine()) != null) {
				words.add(wd.toLowerCase());
			}

		} catch (FileNotFoundException e) {

			System.out.println(file.toString() + " not find");

		} catch (IOException e) {

			System.out.println("Unable to read file");

		}

	}// end of method
	
	
	
	
	
	
	
	// initialize the hash map with singlular and plural word....
	public void initDiction() {

		for (String word : spList) {

			List<String> singular_plural = Arrays.asList(word.split(","));
			spWords.put(singular_plural.get(0), singular_plural.get(1));
			
		} // loop

	}

	public List<String> getSPWords() {

		return spList;
	}

	public  boolean isSingular(String word , int wordNetSence) {
		

		/**
		 * check if word is correctly spell
		 * using senses when zero to check if singular....
		 * check singular plural corpus 
		 * 
		 */

		boolean found = false;

		
		
		// word has to be correctly spelt first
		boolean spelt  = spellChecker(word) ; 

		if(spelt){
				
			
			for (String singular : spWords.keySet()) {
				
				//System.out.println(singular);
				
				
				if ( word.equals(singular.trim()) ) {

					found = true;
				} // end of if

			}// end of for loops 
			
			
			if(!found && wordNetSence > 0 ){
				
				return true ; 
			}
			
		}// end of spell check if statement
		
		
		return found;
	}

	public  boolean isPlural(String word , int wordNetSence) {
		

		/**
		 * check if word is correctly spell
		 * using senses when zero to check if singular....
		 * check singular plural corpus 
		 * 
		 */

		boolean found = false;

		for (String plural : spWords.keySet()) {

			if (word.equals(spWords.get(plural.trim()))) {

				found = true;
			} // end of if

		}
		
		
		if(!found && wordNetSence <= 0 ){
			
			return true ; 
		}
		

		return found;
	}
	

	public  boolean spellChecker(String word) {
		

		boolean found = false;

		for (String wrd : words) {

			if (word.trim().equals(wrd.toLowerCase().trim())) {

				return true;
			} // end of if

		}

		return found;
	}
	
	
	public  String searchWord(String word) {
		

		for (String wrd : words) {

			if (word.trim().equals(wrd.toLowerCase().trim())) {

				return wrd;
			} // end of if

		}

		return null;
	}

	public String convertToSingular(String word) {

		

		/**
		 * check if word is correctly spell
		 * using senses when zero to check if singular....
		 * check singular plural corpus 
		 * 
		 */

		String w = null;

		boolean spelt  = spellChecker(word) ; 

		if(spelt){
				
			
			for (String singular : spWords.keySet()) {
				
				
				
				if ( word.equals(spWords.get(singular))) {

					w = singular.toLowerCase().trim();
					
				} // end of if

			}// end of for loops 
			
		}// end of spell check...... 
		
		return w ; 
	}

	public static void convertToPlural() {

	}

}
