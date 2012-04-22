package kz.edu.sdu.diploma.domain;

public class Syllable {
	
	private String chunk;
	private Position position;
	
	public enum Position {
		FIRST, LAST
	}
	
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chunk == null) ? 0 : chunk.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Syllable other = (Syllable) obj;
		if (chunk == null) {
			if (other.chunk != null)
				return false;
		} else if (!chunk.equals(other.chunk))
			return false;
		if (position != other.position)
			return false;
		return true;
	}
}
