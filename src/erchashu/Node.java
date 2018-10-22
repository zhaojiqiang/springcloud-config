package erchashu;

public class Node {
	
		Person p1;
		Node leftChild;
		Node rightChild;
		public Person getP1() {
			return p1;
		}
		public void setP1(Person p1) {
			this.p1 = p1;
		}
		public Node getLeftChild() {
			return leftChild;
		}
		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}
		public Node getRightChild() {
			return rightChild;
		}
		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
		@Override
		public String toString() {
			return "Node [p1=" + p1 + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
		}
		
		
		
		

}
