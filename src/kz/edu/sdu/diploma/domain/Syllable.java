package kz.edu.sdu.diploma.domain;

public class Syllable {
	
	private String chunk;

	public Syllable(String chunk) {
		setChunk(chunk);
	}

	public String getChunk() {
		return chunk;
	}

	public void setChunk(String chunk) {
		chunk = chunk.replaceAll("0", "у");
		this.chunk = chunk.replaceAll("1", "у");
	}
	
}
