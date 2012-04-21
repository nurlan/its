package kz.edu.sdu.diploma.domain;

import java.util.ArrayList;
import java.util.List;

public class Text {

	private String text;
	private List<Word> wordList;
	
	public Text(String text) {
		text = ignoreSpecialChars(text); // ignoreSpecialChars method should be implemented

		this.text = text;
		
		if(wordList == null) {
			wordList = new ArrayList<Word>();
		}
		
		if(this.text.indexOf(" ") > -1) {
			String wordArray[] = this.text.trim().replaceAll(" +", " ").split(" ");
			
			for(int i = 0; i < wordArray.length; i++) {
				Word word = new Word(wordArray[i]);
				wordList.add(word);
			}
		}
		else {
			wordList.add(new Word(this.text));
		}
	}

	public Text(String text, List<Word> wordList) {
		this.text = text;
		this.wordList = wordList;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Word> getWordList() {
		return wordList;
	}

	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}
	
	public void addWord(Word word) {
		this.wordList.add(word);
	}
	
	private String ignoreSpecialChars(String text) {
		char specialChars[] = {',','.','!','–'};
		
		text = text.toLowerCase();
		for(int i = 0; i < specialChars.length; i++) {
//			text = text.replaceAll(""+specialChars[i], "");
//			text = text.replaceAll(" "+specialChars[i]+" ", " ");
//			text = text.replaceAll(" "+specialChars[i], " ");
//			text = text.replaceAll(specialChars[i]+" ", " ");
		}
		
		return text;
	}
}
