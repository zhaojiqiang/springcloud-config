package tree234;

public class Tree234 {

	Node root = new Node();// 首先创建一个跟节点

	// 查找数据项
	public int find(long key) {
		Node curNode = root;// 当前访问节点
		int childNumber;
		while (true) {
			if ((childNumber = curNode.findItem(key)) != -1) {// 如果当前节点查找数据返回的结果不是-1，说明找到了数据
				return childNumber;
			} else if (curNode.isLeaf()) {// 如果当前节点是叶节点返回-1，说明没有该数据
				return -1;
			} else {
				// 默认的情况下获取下一个节点
				curNode = getNextNode(curNode, key);
			}
		}
	}

	// 获取下一个节点，传入当前节点，还有一个要查找数据项的值
	public Node getNextNode(Node theNode, long theValue) {
		int i;
		int numItem = theNode.getNumItem();// 获取当前节点数据项个数
		for (i = 0; i < numItem; i++) {
			if (theValue < theNode.getItem(i).dData) {// 如果要查找的值小于当前节点数据项的值
				return theNode.getChild(i);// 返回当前数据项的左边节点
			}
		}
		// 如果找不到，返回最右边的一个子节点
		return theNode.getChild(i);
	}

	// 插入一个数据项
	public void insert(long dvalue) {
		Node curNdoe = root;// 找插入位置的时候表示当前节点的局部变量
		DataItem tempItem = new DataItem(dvalue);// 新建一个数据对象
		while (true) {
			if (curNdoe.isFull()) {// 如果当前节点满了的话
				split(curNdoe);// 拆分节点
				curNdoe = curNdoe.getParent();// 拆分结束之后，之前的节点变为子节点，所以先获取其父节点，然后重新开始查询
				curNdoe = getNextNode(curNdoe, dvalue);// 直接查找下一个节点
			} else if (curNdoe.isLeaf()) {// 如果当前节点是一个页节点并且未满
				break;// 找到了要插入的节点 直接进行插入操作
			} else {
				curNdoe = getNextNode(curNdoe, dvalue);// 没有找到的话，获取下一个子节点节点
			}
		}
		curNdoe.insertItem(tempItem);// 让当前节点插入新的数据项
	}
	// 拆分一个节点，传入一个需要拆分的节点
	private void split(Node thisNode) {
		DataItem itemB, itemC;
		Node parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem();// 删除节点中最大的数据项，（removeitem方法默认删除最大的数据项），并且已经清清除当前节点的数据项
		itemB = thisNode.removeItem();// 删除节点中间数据项，并且已经清空了当前节点的该数据项
		child2 = thisNode.disconnectChild(2);// 当前节点的2号子节点，并且已经断开了与2号子节点的连接
		child3 = thisNode.disconnectChild(3);// 当前节点的3号子节点，并且已经断开了与3号子节点的连接
		Node newRight = new Node();// 新建一个右边的子节点

		if (root == thisNode) {// 如国药拆分的是根节点
			root = new Node();// 新建一个根节点
			parent = root;// 父节点等于根节点
			root.connectChild(0, thisNode);// 然后让当前的节点和根节点相连，连载最左边的位置上，
		} else {// 不是的话
			parent = thisNode.getParent();// 先获取拆分节点的父节点
		}

		itemIndex = parent.insertItem(itemB);// 将要拆分的节点的中间数据插入到父节点中，并获取刀插入的索引
		int n = parent.getNumItem();// 获取到父节点的数据个数；

		for (int i = n - 1; i > itemIndex; i--) {
			Node temp = parent.disconnectChild(i);// 父节点与要拆分的节点断开连接，
			parent.connectChild(i + 1, temp);// 父节点和要拆分的源节点重新连接，位置为原要拆分节点的中间的数据项在父节点位置中的左边，
		}
		parent.connectChild(itemIndex + 1, newRight);// 然后在原要拆分的节点的新的位置插入新的右边节点。

		newRight.insertItem(itemC);// 源节点的最大数据项插入新的右节点中
		newRight.connectChild(0, child2);// 新节点右节点和元要拆分的右边两个子节点相连分别放在0,1的位置上
		newRight.connectChild(1, child3);
	}
	// 打印一棵树
	public void display() {
		recDisplayTree(root, 0, 0);
	}

	private void recDisplayTree(Node thisNode, int level, int childNumber) {
		System.out.print("level=" + level + " child=" + childNumber + " "); // 先打印当前节点的状况
		thisNode.displayNode();

		int numItems = thisNode.getNumItem();
		for (int j = 0; j < numItems + 1; j++) { // 遍历每一个子节点并打印 递归
			Node nextNode = thisNode.getChild(j);
			if (nextNode != null) // 如果
				recDisplayTree(nextNode, level + 1, j); // 向下层递归
			else
				return; // 递归结束
		}
	}

	public static void main2(String[] args) {
		Tree234 tree234 = new Tree234();

		for (int i = 0; i < 100; i++) {
			tree234.insert(i);
		}

		tree234.display();
	}

	public static void main(String[] args) {
		System.out.println("递归拉！！！！！！！！！！！！！！！！");
		new Thread().start();
		main(null);
	}
	
	
	
	/**
	 * 节点分裂：
	 * 	1。创建一个新节点，是即将分裂节点的兄弟节点在分裂节点的右边
	 * 	2.数据c移动到新的节点中，
	 * 	3.数据b移动到分裂节点的父节点，
	 * 	4.数据a保留在原来的位置
	 * 	5.最右边的两个子节点，从要分裂的节点处断开，练到新的节点上
	 */
}
