
public class Record {

	private Key k;
	private String data;
	
	public Record(Key k, String data) {
		this.k = k;
		this.data = data;
	}
	
	public Key getKey() {
		return k;
	}
	
	public String getData() {
		return data;
	}
	
}
