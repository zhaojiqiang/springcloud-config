package com.suanfa.example.liangshuxiangjia;

public class Node {

	private int num;

	private Node next;
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node() {
	}

	public Node(int num) {
		this.num = num;
	}

	public Node(int num, Node next) {
		this.num = num;
		this.next = next;
	}
	
	
}
