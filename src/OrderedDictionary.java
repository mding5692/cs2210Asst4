import java.util.*;

public class OrderedDictionary implements OrderedDictionaryADT {
	
	/** Records root, smallest and largest Node in BST manner**/
	private BSTNode root;
	private BSTNode smallest;
	private BSTNode largest;
	
	/* Constructor, creates empty BST */
	public OrderedDictionary() {
		this.root = null;
		this.smallest = null;
		this.largest = null;
	}

	@Override
	/* Returns the Record object with key k, or it returns null if such 
    a record is not in the dictionary. */
	public Record find(Key k) {
		if (root == null) return null;
		return findHelper(root, k);
	}
	// uses findHelper method to help find in subtrees
	private Record findHelper(BSTNode node, Key k) {
		if (node.getRecord() == null) return null;
		int compare = k.compareTo(node.getRecord().getKey());
		String kWord = k.getWord();
		String nodeWord = node.getRecord().getKey().getWord();
		if (compare == 0 || kWord.equals(nodeWord)) return node.getRecord();
		if (compare < 0) {
			return findHelper(node.getLeft(), k);
		} else {
			return findHelper(node.getRight(), k);
		}
	}

	@Override
	/* Inserts r into the ordered dictionary. It throws a DictionaryException 
    if a record with the same key as r is already in the dictionary. */
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
	// uses insertHelper private method to search through subtrees
	private void insertHelper(BSTNode node, BSTNode insert) throws DictionaryException {
		if (node.getRecord() == null) return;
		int comparison = insert.getRecord().getKey().compareTo(node.getRecord().getKey());
		if (comparison == 0) throw new DictionaryException("Node already exists");
		if (comparison == -1) {
			if (node.getLeft().getRecord() != null) {
				insertHelper(node.getLeft(), insert);
			} else {
				node.setLeft(insert);
				node.getLeft().setParent(node);
			}
		} else {
			if (node.getRight().getRecord() != null) {
				insertHelper(node.getRight(), insert);
			} else {
				node.setRight(insert);
				node.getRight().setParent(node);
			}
		}
	
	}
	
	@Override
	/*  Removes the record with Key k from the dictionary. It throws a 
    DictionaryException if the record is not in the dictionary. */
	public void remove(Key k) throws DictionaryException {
		if (root == null) throw new DictionaryException("Empty Tree");
		if (find(k) == null) throw new DictionaryException("Node doesnt exist");
		removeHelper(root, k);
	}

	// handles general case for when node is removed in subtrees
	private void removeHelper(BSTNode node, Key k) {
		int compare = k.compareTo(node.getRecord().getKey());
		if (compare == -1) {
			removeHelper(node.getLeft(), k);
		} else if (compare == 1) {
			removeHelper(node.getRight(), k);
		} else {
			if (node.getLeft().getRecord() == null && node.getRight().getRecord() == null) {
				node.remove();
				node.setLeft(null);
				node.setRight(null);
			} else if (node.getLeft().getRecord() != null && node.getRight().getRecord() == null) {
				BSTNode parent = node.getParent();
				parent.setLeft(node.getLeft());
			} else if (node.getRight().getRecord() != null && node.getLeft().getRecord() == null) {
				BSTNode parentNode = node.getParent();
				parentNode.setRight(node.getRight());
			} else {
				removeWhenAllChildrenPresent(node);
			}
		}
	}
	// handles scenario where all children is present for a node
	private void removeWhenAllChildrenPresent(BSTNode node) {
		BSTNode leftNode = node.getLeft();
		BSTNode minNode = leftNode;
		Stack<BSTNode> minStack = new Stack<BSTNode>();
		minStack.push(leftNode);
		while (!minStack.empty()) {
			BSTNode sNode = minStack.pop();
			if (sNode.getLeft().getRecord() == null && sNode.getRight().getRecord() == null) {
				if (minNode.getRecord().getKey().compareTo(sNode.getRecord().getKey()) == 1) minNode = sNode;
			}
			if (sNode.getLeft().getRecord() != null) minStack.push(sNode.getLeft());
			if (sNode.getRight().getRecord() != null) minStack.push(sNode.getRight());
		}
		Record newRecord = minNode.getRecord();
		node.setRecord(newRecord);
		minNode.remove();
	}

	@Override
	/* Returns the successor of k (the record from the ordered dictionary 
    with smallest key larger than k); it returns null if the given key has
    no successor. The given key DOES NOT need to be in the dictionary. */
	public Record successor(Key k) {
		if (root == null) return null;
		int compare = k.compareTo(root.getRecord().getKey());
		return sucHelper(root,k);
	}
	// uses helper method to search through subtrees
	private Record sucHelper(BSTNode node, Key k) {
		Stack<BSTNode> stack = new Stack<BSTNode>();
		stack.push(node);
		Record successor = node.getRecord();
		while (!stack.empty()) {
			BSTNode sNode = stack.pop();
			if ( (k.compareTo(sNode.getRecord().getKey()) == -1) && (sNode.getRecord().getKey().compareTo(successor.getKey()) == -1)) {
				successor = sNode.getRecord();
			}
			if (sNode.getLeft().getRecord() != null) stack.push(sNode.getLeft());
			if (sNode.getRight().getRecord() != null) stack.push(sNode.getRight());
		}
		return successor;
	}

	@Override
	/* Returns the predecessor of k (the record from the ordered dictionary 
    with largest key smaller than k; it returns null if the given key has 
    no predecessor. The given key DOES NOT need to be in the dictionary.  */
	public Record predecessor(Key k) {
		if (root == null) return null;
		return preHelper(root,k);
	}
	// uses helper method to search through subtrees
	private Record preHelper(BSTNode node, Key k) {
		Stack<BSTNode> stack = new Stack<BSTNode>();
		stack.push(node);
		Record pre = node.getRecord();
		while (!stack.empty()) {
			BSTNode sNode = stack.pop();
			if (sNode.getRecord() != null) {
				if ( (k.compareTo(sNode.getRecord().getKey()) == 1) && (sNode.getRecord().getKey().compareTo(pre.getKey()) == 1)) {
						pre = sNode.getRecord();
					}
			}
			if (sNode.getLeft().getRecord() != null) stack.push(sNode.getLeft());
			if (sNode.getRight().getRecord() != null) stack.push(sNode.getRight());
		}
		return pre;
	}

	@Override
	/* Returns the record with smallest key in the ordered dictionary. 
    Returns null if the dictionary is empty.  */
	public Record smallest() {
		return smallest.getRecord();
	}

	@Override
	/* Returns the record with largest key in the ordered dictionary. 
    Returns null if the dictionary is empty.  */
	public Record largest() {
		return largest.getRecord();
	}
}
