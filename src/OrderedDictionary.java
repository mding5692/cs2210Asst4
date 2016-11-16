import java.util.*;

public class OrderedDictionary implements OrderedDictionaryADT {

	private BSTNode root;
	private BSTNode smallest;
	private BSTNode largest;
	
	public OrderedDictionary() {
		this.root = null;
		this.smallest = null;
		this.largest = null;
	}

	@Override
	public Record find(Key k) {
		if (root == null) return null;
		return findHelper(root, k);
	}
	
	private Record findHelper(BSTNode node, Key k) {
		if (node.getRecord() == null) return null;
		if (node.getRecord().getKey().compareTo(k) == 0) return node.getRecord();
		if (node.getRecord().getKey().compareTo(k) < 0) {
			return findHelper(node.getLeft(), k);
		} else {
			return findHelper(node.getRight(), k);
		}
	}

	@Override
	public void insert(Record r) throws DictionaryException {
		BSTNode insertedNode = new BSTNode(r);
		if (root == null) {
			largest = smallest = root = insertedNode;
		} else if (root.getRecord().getKey().compareTo(r.getKey()) == 0) {
			throw new DictionaryException("Key already exists");
		} else {
			if(r.getKey().compareTo(smallest.getRecord().getKey()) < 0) smallest = insertedNode;
			if(r.getKey().compareTo(largest.getRecord().getKey()) > 0) largest = insertedNode;
			insertHelper(root, insertedNode);
		}
	}

	// remember setParent
	private void insertHelper(BSTNode node, BSTNode insert) throws DictionaryException {
		
	}
	
	@Override
	public void remove(Key k) throws DictionaryException {
		if (root == null) throw new DictionaryException("Empty Tree");
		removeHelper(root, k);
	}

	private void removeHelper(BSTNode node, Key k) throws DictionaryException {
		
	}
	
	@Override
	public Record successor(Key k) {
		if (root == null) return null;
		return sucHelper(root, k);
	}
	
	private Record sucHelper(BSTNode node, Key k) {
		
	}

	@Override
	public Record predecessor(Key k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record smallest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record largest() {
		// TODO Auto-generated method stub
		return null;
	}
}
