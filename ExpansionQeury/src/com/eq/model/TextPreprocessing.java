package com.eq.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import WordNet.WordCorpus;
import WordNet.WordPresicion;
import WordNet.Wordnet;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;

public class TextPreprocessing {
	
	private static WordCorpus wordCorpus ; 
	
	public static void initCorpus() {
		initStopWord() ; 
		wordCorpus = WordPresicion.getWordCorpus() ; 
		
	}
	
	public static List<String> tokenize_query(String text){
		
		return Arrays.asList(text.split("\\s"));
	
	}
	
	public static List<String> remove_punctuation(List<String> token){
	
		List<String> clean_token = new ArrayList<>(); 
		
		for(String tok : token){
			
//			System.out.println(tok.matches("\\w*[.,?]"));
//			System.out.println(tok.matches("[.,?]\\w*"));
			
			if(tok.matches("\\w+[.,?]") || tok.matches("[.,?]\\w+")){
				
				clean_token.add(tok.replaceAll("\\W+" , " ").trim()) ;
			
			}else if(tok.matches("\\w+")){
				
				clean_token.add(tok.trim()); 
			}
			
		}
		
		System.out.println("clean token size = " + clean_token );
	
		return clean_token ; 
	}
	
	public static List<String> remove_stopword(List<String> token){
		
		List<String> stopWordsList = initStopWord(); 
		System.out.println("stop word size " + stopWordsList.size());
		List<String> clean_token = new ArrayList<>(); 
		boolean found = false ; 
		
		for(String tok : token){
			
			found = false ; 
			
			for(String stopword : stopWordsList)
			{
				
				if(tok.matches(stopword))
					found = true ; 
					
			}// inner loop
			
				if(!found)
					clean_token.add(tok); 
			
		}// end of for loop 
		
		
		System.out.println("stop words remove " + clean_token);
		
		return clean_token ; 
	}
	
	
	public static String lemmatize_word(String word){
		
		
		boolean wdNet = false;
		boolean wdCorpus = false;
		String foundWordNet = null;
		String foundWordCorpus = null;
		WordPresicion.runOnce = false;

		// checking if word is spell correctly .. 1. first if word found in word
		// net...
			boolean spelling  = wordCorpus.spellChecker(word) ; 

			//System.out.println("WORD SPELT CORRECTLY..... ");

		if(Wordnet.checkWordSense(word)) {
			
			wdNet = true ; 
			foundWordNet = word ; 
			
		}else {
			
				
            boolean valid_for_searching = WordPresicion.wordStemming(word);
			
			while (valid_for_searching) {

				//System.out.println("LOOPS FROM WORD NET");
				
				valid_for_searching = WordPresicion.wordStemming(WordPresicion.getWord());
				
				//System.out.println("ChECKING FAVLID FOR SEARCHING " + valid_for_searching);

				if(WordPresicion.getWord().length() <= 2)
					break ; 
				
				if (Wordnet.checkWordSense(WordPresicion.getWord().toLowerCase().trim())) {

					// if word is found .... in word net after stemming
					wdNet = true;
					foundWordNet = WordPresicion.getWord().toLowerCase().trim();
					break;

				} else {

					// findPrecision(WordPresicion.getWord().toLowerCase().trim());
					//System.out.println("LOOP GOING TO BREAK..... ");
				}
				// if word is found in wordnet

			} // while loop bracket

			if (!wdNet) {

				String sWord = wordCorpus.convertToSingular(word);
				//System.out.println("FIRST WORD FROM WORD CORPUSE " + sWord);

				if (sWord == null) {

					WordPresicion.runOnce = false;
					boolean condition = WordPresicion.wordStemming(word);

					while (condition) {

					//	System.out.println("LOOPS FROM WORD CORPUS");
						condition = WordPresicion.wordStemming(WordPresicion.getWord());
						System.out.println();
						sWord = wordCorpus.convertToSingular(WordPresicion.getWord());
					//	System.out.println("NEXT WORD FORM WORD CORPUS " + sWord);

						if (sWord != null) {
							wdCorpus = true;
							foundWordCorpus = sWord.toLowerCase().trim();
							break;

						}// end of first layer if

					}// while condition

				}// end of second layer if ...
				else {

					wdCorpus = true;
					foundWordCorpus = sWord.toLowerCase().trim();

				}// end of second layer if

			}// end of third layer if statement....
			
			
			
		} // fourth layer of if statement ...... checking for init word sense.... 
			
			
			// check for irregular pattern ..
			

			System.out.println("From word net :::::::   " + foundWordNet);
			System.out.println("From word corpus :::::: " + foundWordCorpus);
			System.out.println("IS WORD SPELT CORRECTLY  " + spelling);
			
			
			if(foundWordNet != null)
				return foundWordNet;
			
			else if(foundWordCorpus != null)
				return foundWordCorpus; 
			else 
				return word ; 
	}
	
	
	/*
	 *     BELLOW CONTAIN LIST OF HELPER METHOD TO 
	 *     ACHIVE FUNCTIONALITY ABOVE .....
	 */
	
	
	// getting resources ready
	
	public static List<String> initStopWord(){
		
		File file = new File("stopwords");
		String stopword ; 
		List<String> stopwordList  = new ArrayList<>();
		
		try {
			
			FileReader reader = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(reader);
			//System.out.println("STOP WORD LIST");
			
			while((stopword = bufferReader.readLine()) != null)
			{
				//System.out.println(stop word);
				stopwordList.add(stopword.trim());
			}
				
			
		} catch (FileNotFoundException e) {
			
			System.out.println(file.toString() + " not find");
			
		} catch (IOException e) {
			
			System.out.println("Unable to read file");
			
		}
		
		return stopwordList ; 
	}// end  of method 

}
