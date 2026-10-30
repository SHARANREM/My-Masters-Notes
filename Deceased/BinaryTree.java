class Node{
	int data;
	Node left;
	Node right;
	Node(int data){
		this.data = data;
	}
}

class BT{
	Node root;

	void insert(Node node){
		root = insertHelper(root, node);
	}

	Node insertHelper(Node root, Node node){
		int data = node.data;
		if(root == null){
			root = node;
			return root;
		}
		else if(data<root.data){
			root.left = insertHelper(root.left,node);
		}
		else{
			root.right = insertHelper(root.right,node);
		}
		return root;
	}
	void inorder(){
		inorder(root);
	}
	void inorder(Node root){
		if(root == null){return;}
		inorder(root.left);
		System.out.print(root.data+"->");
		inorder(root.right);
	}
}

class BinaryTree{

	public static void main(String[] args){
		BT tree = new BT();
		tree.insert(new Node(5));
		tree.insert(new Node(6));
		tree.insert(new Node(1));
		tree.insert(new Node(3));
		tree.insert(new Node(7));
		tree.inorder();
	}
}