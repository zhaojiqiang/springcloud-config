package erchashu;

public class TreeApp {

	
	
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(50,1.5);
		tree.insert(45,1.7);
		tree.insert(69,1.9);
		tree.insert(39,2.1);
		tree.insert(75,2.1);
		tree.insert(36,1.7);
		tree.insert(42,1.7);
		tree.insert(74,1.7);
		tree.insert(79,1.7);
		tree.insert(32,1.7);
		tree.insert(41,1.7);
		tree.insert(72,1.7);
		tree.insert(82,1.7);
		tree.insert(34,1.7);
		tree.insert(81,1.7);
		tree.insert(85,1.7);
		tree.insert(83,1.7);

		
		Node find = tree.find(25);
		Node root = tree.getRoot();
		System.out.println(tree.delete(2341));;
		
		bianli(root);
		
	}

	private static void bianli(Node root) {
		if(null == root){
			return;
		}
		bianli(root.getLeftChild());
		System.out.println(root.getP1().getiData());
		bianli(root.getRightChild());

	}
}
