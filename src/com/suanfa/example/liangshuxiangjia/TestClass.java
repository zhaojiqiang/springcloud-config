package com.suanfa.example.liangshuxiangjia;

public class TestClass {
	
	public static void main(String[] args) {
		
		
		
		LinkedNum l1 = new LinkedNum();
		l1.insert(3);
		l1.insert(4);
		l1.insert(2);
		l1.disPlay();
		
		LinkedNum l2 = new LinkedNum();
	
		l2.insert(6);
		l2.insert(4);
		
		l2.insert(4);
		l2.insert(4);
		l2.insert(4);
		l2.insert(4);
		l2.disPlay();
		
		Node head = l1.head;
		Node cur1 = head;
		Node cur2 = l2.head;

		LinkedNum l3 = new LinkedNum();

		int jia1 ,jia2;
		int mod = 0 ; 
		while(null != cur1 || null != cur2){
			jia1 = cur1 == null ? 0 : cur1.getNum();
			cur1 = cur1 == null ? null : cur1.getNext();
			jia2 = cur2 == null ? 0 : cur2.getNum();
			cur2 = cur2 == null ? null : cur2.getNext();
			int newNode = mod + jia1 + jia2;
				mod = newNode/10;
			l3.insertEnd(newNode%10);
		}
		if(mod > 0){
			l3.insertEnd(mod);
		}
		l3.disPlay();
		
		
	}

}
