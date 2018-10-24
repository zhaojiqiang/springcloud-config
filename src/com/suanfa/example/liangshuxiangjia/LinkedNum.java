package com.suanfa.example.liangshuxiangjia;

public class LinkedNum {

	public Node	head;
	public Node	end;

	private int	size;

	public boolean insert(int num) {
		Node node = new Node(num, head);
		if (end == null) {
			end = node;
		}
		head = node;
		size++;
		return true;
	}

	public boolean insertEnd(int num){
		
		Node node = new Node(num,null);
		if(null == head){
			head = node;
		}
		if(end==null){
			end =  node;
		}else{
			end.setNext(node);
			end = node;
		}
		
		size ++;
		return true;
	}
	public void disPlay() {
		String s = "";
		Node cur = head;
		while (cur != null) {
			s += cur.getNum();
			cur = cur.getNext();
		}
		System.out.println(s);
	}

}
