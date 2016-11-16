public class BSTNode {

	private Record r;
	private BSTNode left;
	private BSTNode right;
	
	public BSTNode() {
		r = null;
	}
	
	public BSTNode(Record r) {
		this.r = r;
		BSTNode leftNode = new BSTNode();
		BSTNode rightNode = new BSTNode();
		this.left = leftNode;
		this.right = rightNode;
	}
	
	public BSTNode getLeft() {
		return left;
	}
	
	public BSTNode getRight() {
		return right;
	}
	
	public Record getRecord() {
		return r;
	}
	
}
