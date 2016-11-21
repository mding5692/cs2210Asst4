/* Class created to create the Binary Search Tree for the database */
public class BSTNode {

	/* Attributes stored in each node */
	private Record r;
	private BSTNode left;
	private BSTNode right;
	private BSTNode parent;
	
	// Constructor initializes empty node
	public BSTNode() {
		r = null;
	}
	
	// Constructor initializes node with content
	public BSTNode(Record r) {
		this.r = r;
		BSTNode leftNode = new BSTNode();
		BSTNode rightNode = new BSTNode();
		this.left = leftNode;
		this.right = rightNode;
	}
	
	// returns left child
	public BSTNode getLeft() {
		return left;
	}
	
	// returns right child
	public BSTNode getRight() {
		return right;
	}
	
	// returns record
	public Record getRecord() {
		return r;
	}
	
	// sets new record
	public void setRecord(Record r) {
		this.r = r;
	}
	
	// set left child
	public void setLeft(BSTNode node) {
		this.left = node;
	}
	
	// set right child
	public void setRight(BSTNode node) {
		this.right = node;
	}
	
	// removes saved record
	public void remove() {
		this.r = null;
	}
	
	// sets parent node
	public void setParent(BSTNode p) {
		this.parent = p;
	}
	
	// returns parent node
	public BSTNode getParent() {
		return this.parent;
	}
}
