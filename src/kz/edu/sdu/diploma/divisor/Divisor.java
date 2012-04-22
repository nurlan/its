package kz.edu.sdu.diploma.divisor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.edu.sdu.diploma.domain.Syllable;
import kz.edu.sdu.diploma.domain.Text;
import kz.edu.sdu.diploma.domain.Word;

public class Divisor {

	private Text text;
	private char [] vowels = {'а','ә','е','о','ө','ұ','ү','і','ы','и','ю','я','1'};
	
	public Divisor() {
		
	}
	
	public Divisor(Text text) {
		this.text = text;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	
	public void yReplacement() {
		String outText = "";
		
		for(Word w : text.getWordList()) {
			String word = w.getWord(); 
		
			if ( word.length() != 0 ) {
			
				List<Integer> indexList = getYindex(word);
				int vowelArray[] = getVowelArray(word);
				
				if(!indexList.isEmpty()) {
					
					for(Integer i : indexList) {
						// y is 1st and word length 0
						if( i.intValue() == 0 && word.length() == 1 ) {
							word = word.replaceFirst("у", "1");
						}
						// y is 1st and before vowel
						else if( i.intValue() == 0 && vowelArray[i+1] == 1 ) {
							word = word.replaceFirst("у", "0");
						}
						// y is 1st and before consonant
						else if( i.intValue() == 0 && vowelArray[i+1] != 1 ) {
							word = word.replaceFirst("у", "1");
						}
						// y is last and before y vowel
						else if( i.intValue() == (word.length() - 1) && vowelArray[i-1] == 1 ) {
							word = word.replaceFirst("у", "0");
						}
						// y is last and before y consonant
						else if( i.intValue() == (word.length() - 1) && vowelArray[i-1] != 1 ) {
							word = word.replaceFirst("у", "1");
						}
						// y is between vowels
						else if( vowelArray[i-1] == 1 && vowelArray[i+1] == 1 ) { //уағда
							word = word.replaceFirst("у", "0");
						}
						// y is between consonant
						else if( vowelArray[i-1] != 1 && vowelArray[i+1] != 1 ) {
							word = word.replaceFirst("у", "1");
						}
						// y is after vowel and before consonant
						else if( vowelArray[i-1] == 1 && vowelArray[i+1] != 1 ) {
							word = word.replaceFirst("у", "0");
						}
						// y is after consonant and before vowel
						else if( vowelArray[i-1] != 1 && vowelArray[i+1] == 1 ) {
							word = word.replaceFirst("у", "1");
						}
						else {
							new Exception("java.gulnur.yReplacementOutOfCaseException").printStackTrace();
						}
					}
				}
				
				outText += word +" ";
			}
		}
		
		outText = outText.trim();
		this.text = new Text(outText);
	}
	
	public void divideBySyllables() {
		for(Word w : text.getWordList()) {
			String word = w.getWord();
			
			while (word.length() != 0) {
				int vowelArray[] = getVowelArray(word);
				
				// word length == 1
				if(word.length() == 1) {
					w.addSyllable(word);
				}
				// word length == 2 and 10 then 1 chunk
				else if(word.length() == 2 && vowelArray[0] == 1 && vowelArray[1] != 1) {
					w.addSyllable(word);
				}
				// word length == 2 and 01 then 1 chunk
				else if(word.length() == 2 && vowelArray[0] != 1 && vowelArray[1] == 1) {
					w.addSyllable(word);
				}
				// word length == 3 and 100 then 1 chunk
				else if(word.length() == 3 && vowelArray[0] == 1 && vowelArray[1] != 1 && vowelArray[2] != 1) {
					w.addSyllable(word);
				}
				// word length == 3 and 010 then 1 chunk
				else if(word.length() == 3 && vowelArray[0] != 1 && vowelArray[1] == 1 && vowelArray[2] != 1) {
					w.addSyllable(word);
				}
				// word length == 4 and 0100 then 1 chunk //қойы
				else if(word.length() == 4 && vowelArray[0] != 1 && vowelArray[1] == 1 && vowelArray[2] != 1 && vowelArray[3] != 1) {
					w.addSyllable(word);
				}
				
				
				// 11        then 1
				else if( vowelArray[0] == 1 && vowelArray[1] == 1 ) {
					w.addSyllable(word.substring(0,1));
				}
				// 011       then 01
				else if( vowelArray[0] != 1 && vowelArray[1] == 1 && vowelArray[2] == 1) {
					w.addSyllable(word.substring(0,2));
				}
				
				// ...101... then 1
				else if( vowelArray[0] == 1 && vowelArray[1] != 1 && vowelArray[2] == 1) {
					w.addSyllable(word.substring(0,1));
				}
				// ...1000... then 10 //өйткені
				else if( vowelArray[0] == 1 && vowelArray[1] != 1 && vowelArray[2] != 1 && vowelArray[3] != 1) {
					w.addSyllable(word.substring(0,3));
				}
				// ...100... then 10
				else if( vowelArray[0] == 1 && vowelArray[1] != 1 && vowelArray[2] != 1) {
					w.addSyllable(word.substring(0,2));
				}
				// ...001... then 001
				else if( vowelArray[0] != 1 && vowelArray[1] != 1 && vowelArray[2] == 1) {
					w.addSyllable(word.substring(0,3));
				}
				// ...0101.. then 01
				else if( vowelArray[0] != 1 && vowelArray[1] == 1 && vowelArray[2] != 1 && vowelArray[3] == 1) {
					w.addSyllable(word.substring(0,2));
				}
				// ...0100.. then 010
				else if( vowelArray[0] != 1 && vowelArray[1] == 1 && vowelArray[2] != 1 && vowelArray[3] != 1) {
					w.addSyllable(word.substring(0,3));
				}
				else {
					Exception e = new Exception("java.gulnur.SyllableOutOfCaseException");
					e.printStackTrace();
				}
				
				if(word.length() == w.getLastSyllableLength()) {
					word = "";
					w.getLastSyllable().setPosition(Syllable.Position.LAST);
				}
				else {
					word = word.substring(w.getLastSyllableLength());
					w.getLastSyllable().setPosition(Syllable.Position.FIRST);
				}
			}
		}
	}
	
	public Map<Syllable,Word> getSyllableMap() {
		Map<Syllable, Word> syllableMap = new HashMap<Syllable, Word>();
		
		for(Word word : text.getWordList()) {
			for(Syllable syllable : word.getSyllableList()) {
				syllableMap.put(syllable, word);
			}
		}
		
		return syllableMap;
	}
	
	private List<Integer> getYindex(String word) {
		List<Integer> indexList = new ArrayList<Integer>();
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == 'у') {
				indexList.add(i);
			}
		}
		
		return indexList;
	}
	
	private int[] getVowelArray(String word) {
		int vowelArray[] = new int[word.length()];
		for(int i = 0; i < word.length(); i++) {
			for(int j = 0; j < vowels.length; j++) {
				if(word.charAt(i) == vowels[j]) {
					vowelArray[i] = 1;
					break;
				}
			}
		}
		
		return vowelArray;
	}
}
