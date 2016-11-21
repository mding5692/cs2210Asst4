
public class Record {

	/* Uses private attributes to store key and data */
	private Key k;
	private String data;
	
	// Constructor takes key and string
	public Record(Key k, String data) {
		this.k = k;
		this.data = data;
	}
	
	// Returns the key
	public Key getKey() {
		return k;
	}
	
	// Returns the data
	public String getData() {
		return data;
	}
	
}
