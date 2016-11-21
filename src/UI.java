import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UI {
	
	// handles main UI program and throws IO, Dictionary and Multimedia exceptions
	public static void main(String[] args) throws IOException, DictionaryException, MultimediaException {
		
		// Validates that there is only one input file
		if (args.length  > 1) {
			System.out.println("Too many files, please limit to one");
			System.exit(0); 
		}
		
		// Grabs filename and reads file contents into strings
		String fileName = args[0];
        String fileContents = new String(Files.readAllBytes(Paths.get(fileName)));
        String[] records = fileContents.split("\\n"); // splits file by newlines
        // if input file is of wrong format, exit
        if ( (records.length % 2) != 0) {
        	System.out.println("Input file incorrect format");
        	System.exit(0);
        }
        // Creates new ordered dictioanry to store data
        OrderedDictionary db = new OrderedDictionary();
        for (int i = 0; i < (records.length/2) - 1; i+=2) {
        	String word = records[i];
        	String data = records[i+1];
        	int type = 1;
        	// figures out file type using file extension
        	String fileType = data.substring(data.length() - 4);
        	if ( fileType.equals(".wav") || fileType.equals(".mid") ) {
        		type = 2;
        	} else if (fileType.equals(".jpg") || fileType.equals(".gif")) {
        		type = 3;
        	}
        	Key newKey = new Key(word, type);
        	Record newRecord = new Record(newKey, data);
        	db.insert(newRecord); // stores new Record into db
        }
        
        // declares StringReader to read user input
        StringReader strReader = new StringReader();
        
        // Keeps asking for input unless user uses end command
        String input = "";
        do {
        	input = strReader.read("Enter next command... ");
        	interpret(input, db);
        } while ( !input.equals("end") );
        	
	}
	
	// method used to understand user input
	private static void interpret(String input, OrderedDictionary db) throws MultimediaException, DictionaryException {
		String[] inputStrArr = input.split(" ");
		// handles the three letter commands
		if (inputStrArr.length == 3) {
			String command = inputStrArr[0];
			String word = inputStrArr[1];
			int type = Integer.parseInt(inputStrArr[2]);
			Key k = new Key(word, type);
			// removes from db command
			if (command.equals("remove")) {
				db.remove(k);
			} else if ((command).equals("next")) { // gets next key command
				Record r = db.successor(k);
				if(r != null) {
					System.out.println("Next key is " + r.getKey());
				} else {
					System.out.println("No next key");
				}
			} else if ((command).equals("prev")) { // gets prev key command
				Record r = db.predecessor(k);
				if (r != null) {
					System.out.println("Prev key is " + r.getKey());
				} else {
					System.out.println("No previous key");
				}
			}
		} else if (inputStrArr.length == 4) {
			if (inputStrArr[0].equals("insert")) { // inserts record into db command
				String word = inputStrArr[1];
				int type = Integer.parseInt(inputStrArr[2]);
				String data = inputStrArr[3];
				Key k = new Key(word, type);
				Record r = new Record(k, data);
				db.insert(r);
			}
		} else if (inputStrArr.length == 2) {
			if (inputStrArr[0].equals("search")) { // finds key command
				String word = inputStrArr[1];
				// uses 1 as default for key, but find method for dictionary uses word only to search
				Key k = new Key(word, 1); 
				Record result = db.find(k);
				if (result != null) { // plays result if not null
					int type = result.getKey().getType();
					String data = result.getData();
					if (type == 1) {
						System.out.println(data);
					} else if (type == 2) {
						SoundPlayer player = new SoundPlayer();
						player.play(data);
					} else if (type == 3) {
						PictureViewer picViewer = new PictureViewer();
						picViewer.show(data);
					}
					
				} else { // if cannot find key
					System.out.println("Cannot find " + word + ", please try again.");
				}
			}
		} else if (inputStrArr.length == 1) {
			String cmd = inputStrArr[0]; // prints out largest or smallest
			if (cmd.equals("first")) System.out.println(db.smallest());
			if (cmd.equals("last")) System.out.println(db.largest());
		}
	}

}
