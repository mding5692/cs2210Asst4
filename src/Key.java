
public class Key {
	
	/* Data stored in each key as attributes */
	private String word;
	private int type;
	
	// Constructor takes in string and integer
	public Key(String word, int type) {
		word = word.toLowerCase();
		this.word = word;
		if (type <= 3 || type >= 1) this.type = type;
	}
	
	// returns word
	public String getWord() {
		return this.word;
	}
	
	// returns type
	public int getType() {
		return this.type;
	}
	
	// Returns 0 if this key is equal to k, returns -1 if this key is
	// smaller than k, and it returns 1 otherwise
	public int compareTo(Key k) {
		if ( (k.getWord().compareTo(word) > 0) || (k.getType() > type)) return -1;
		if ( (k.getWord().compareTo(word) < 0) || (k.getType() < type)) return 1;
		return 0;
	}
}
