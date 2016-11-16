
public class Key {
	
	private String word;
	private int type;
	
	public Key(String word, int type) {
		word = word.toLowerCase();
		this.word = word;
		if (type <= 3 || type >= 1) this.type = type;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getType() {
		return this.type;
	}
	
	public int compareTo(Key k) {
		if ( (k.getWord().compareTo(word) > 0) || (k.getType() > type)) return -1;
		if ( (k.getWord().compareTo(word) < 0) || (k.getType() < type)) return 1;
		return 0;
	}
}
