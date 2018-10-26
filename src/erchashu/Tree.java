package erchashu;

public class Tree {
	
	private Node root;
	
	public Node find(int key){
		Node current = root;
		while(current.p1.iData != key){
			if(key < current.p1.iData ){
				current = current.leftChild;
			}else{
				current = current.rightChild;
			}
			if(current == null){
				return null;
			}
		}
		return current;
	}
	
	public void insert(int id,Double dd){
		Node newNode = new Node();
		Person person = new Person();
		person.setiData(id);
		person.setfData(dd);
		newNode.setP1(person);
		
		if(null==root){
			root = newNode;
		}else{
			Node current = root;
			Node parent;
			while(true){
				parent = current;
				if(id < current.getP1().getiData()){
					current = current.getLeftChild();
					if(null == current){
						parent.leftChild = newNode;
						return;
					}
				}else{
					current = current.rightChild;
					if(null == current){
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	
	
	public boolean delete(int id){
		Node current = root;
		Node parent = root;
		boolean isleftNode = true;
		while(current.p1.getiData() != id ){
			parent = current;
			if(id < current.getP1().getiData()){
				isleftNode = true;
				current = current.getLeftChild();
			}else{
				isleftNode = false;
				current = current.getRightChild();
			}
			if(null == current){
				return false;
			}
		}
		
		if(null == current.getLeftChild() && current.getRightChild() == null){
			if(current == root){
				root = null;
			}else if(isleftNode){
				parent.setLeftChild(null);
			}else{
				parent.setRightChild(null);
			}
		}else if(current.getRightChild() == null){
			if(current == root){
				root = current.leftChild;
			}else if(isleftNode){
				parent.setLeftChild(current.getLeftChild());
			}else{
				parent.setRightChild(current.getLeftChild());
			}
		}else if(current.getLeftChild() == null){
			if(current == root){
				root = current.rightChild;
			}else if(isleftNode){
				parent.setLeftChild(current.getRightChild());
			}else{
				parent.setRightChild(current.getRightChild());
			}
		}else{
			Node successor = getSuccessor(current);
			if(current == root){
				root = successor;
			}else if(isleftNode){
				parent.leftChild = successor;
			}else{
				parent.rightChild = successor;
				successor.leftChild = current.leftChild;
			}
		}
			
		return true;
	}
	
	private Node getSuccessor(Node delNode){
		Node successorParent = delNode;
		Node successor = delNode;
		Node currnt = delNode.getRightChild();
		while(currnt != null){
			successorParent = successor;
			successor = currnt;
			currnt = currnt.leftChild;
		}
		
		if(successor != delNode.rightChild){
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		
		return successor;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	
	
	
	
	

}
