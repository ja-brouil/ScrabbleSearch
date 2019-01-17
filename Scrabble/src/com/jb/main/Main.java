package com.jb.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.jb.json.WordListFactory;

public class Main {

	public static final String  URL = "https://raw.githubusercontent.com/adambom/dictionary/master/dictionary.json";
	
	public static void main(String[] args) throws IOException, ParseException {
		
		// Create Dictionnary
		JSONObject dictionnaryJsonObject = WordListFactory.getJsonList(URL);
		
		// User Input
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Enter up to 7 characters...\nAny characters past 7 will not be counted.");
		String inputString = inputScanner.next().toLowerCase();
		inputScanner.close();
		
		// Get all letters/characters in the array
		int maxLength = inputString.length() < 7 ? inputString.length() : 7;
		char inputCharacters[] = new char[7];
		for (int i = 0; i < maxLength; i++) {
			inputCharacters[i] = inputString.charAt(i);
		}
		
		// Scan for words
		ArrayList<String> allPossibleWordStrings = new ArrayList<>();
		ArrayList<String> finalList = new ArrayList<>();
		
		for (Iterator<?> iterator = dictionnaryJsonObject.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			allPossibleWordStrings.add(key.toLowerCase());
		}
		
		for (String word : allPossibleWordStrings) {
			if (word.length() <= 7) {
				if (isWordDoable(inputCharacters, word)) {
					finalList.add(word);
				}
			}
		}

		// Final Answer
		if (finalList.size() == 0) {
			System.out.println("There are no words that can be made with this selection");
		} else {
			System.out.println("Here are all the possible words");
			System.out.println(finalList.size());
			finalList.forEach(System.out::println);
		}	
	}
	
	private static boolean isWordDoable(char[] letters, String word) {
		ArrayList<Character> inputCharacters = new ArrayList<>();
		for (int i = 0; i < letters.length; i++) {
			inputCharacters.add(letters[i]);
		}
		 
		ArrayList<Character> wordCharacters = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			wordCharacters.add(word.charAt(i));
		}
	
		
		// Take the first letter of the word we can trying to check
		for (int i = 0; i < wordCharacters.size(); i++) {
			if (inputCharacters.contains(wordCharacters.get(i))) {
				inputCharacters.remove(wordCharacters.get(i));
				wordCharacters.remove(i);
				i--;
			} else {
				return false;
			}
		}

		// Word can be made if we reach here
		return true;
	}
}
