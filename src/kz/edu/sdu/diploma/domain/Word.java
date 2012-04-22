package kz.edu.sdu.diploma.domain;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private String word;
	private List<Syllable> syllableList;

	public Word(String word) {
		this.word = word;
	}
	
	public Word(String word, List<Syllable> syllableList) {
		this.word = word;
		this.syllableList = syllableList;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Syllable> getSyllableList() {
		return syllableList;
	}

	public void setSyllableList(List<Syllable> syllableList) {
		this.syllableList = syllableList;
	}
	
	public void addSyllable(Syllable syllable) {
		if( this.syllableList == null ) {
			this.syllableList = new ArrayList<Syllable>();
		}
		
		this.syllableList.add(syllable);
	}
	
	public void addSyllable(String chuck) {
		Syllable syllable = new Syllable(chuck);
		addSyllable(syllable);
	}
	
	public int getLastSyllableLength() {
		if(this.syllableList != null)
			return this.syllableList.get(this.syllableList.size()-1).getChunk().length();
		
		return -1;
	}
	
	public Syllable getLastSyllable() {
		if(this.syllableList != null )
			return ((this.syllableList.size()!=0)?this.syllableList.get(this.syllableList.size()-1):null);
		
		return null;
	}
}
