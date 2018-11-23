package tree234;

public class Node {

	private static final int	ORDER		= 4;

	private int					numItems;								// 数据项个数

	private Node				parent;									// 父节点

	private Node[]				childArray	= new Node[ORDER];			// 子节点

	private DataItem[]			itemArray	= new DataItem[ORDER - 1];	// 节点数据项

	public Node getChild(int childNum) {
		return childArray[childNum];// 获取指定的下标子节点
	}

	public Node getParent() {
		return parent;// 获取父节点
	}

	public boolean isLeaf() {
		return childArray[0] == null ? true : false;// 判断是否是叶节点
	}

	public int getNumItem() {
		return numItems;// 获取数据项个数
	}

	public DataItem getItem(int index) {
		return itemArray[index];// 获取数据项,通过传入的索引值获取
	}

	public boolean isFull() {
		return numItems == ORDER - 1;// 判断该节点数据项是否满了
	}

	// 连接自及诶单，输入要插入节点的位置和节点
	public void connectChild(int childNum, Node child) {
		childArray[childNum] = child;// 将要插入的子节点放到当前节点的子节点数组对应的索引位子
		if (child != null) {// 判断子节点是否为kong，设置子节点的父节点为当前节点
			child.parent = this;
		}
	}

	// 拆分子节点，输入要拆分子节点的位置
	public Node disconnectChild(int childNum) {
		Node tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}
	// 当前节点查找一个数据的位置
	public int findItem(long key) {
		for (int i = 0; i < ORDER - 1; i++) {// 遍历当前节点的所有数据项
			if (null == itemArray[i]) {// 如果当前位置没有数据了，以后的位置也不会有
				break;
			} else if (itemArray[i].dData == key) {
				return i;
			}
		}
		return -1;
	}

	// 从当前节点插入一个数据项，输入要插入的新的数据项
	// 调用该方法的一个前提：当前节点的数据项只有两个或者是更少的时候才可以继续插入数据项，（在插入之前会进行一定的检查和判断）
	public int insertItem(DataItem newItem) {
		numItems++;// 首先是数据项个数加一
		long newKey = newItem.dData;// 获取到数据项的值
		for (int i = ORDER - 2; i >= 0; i--) {// 遍历
			if (itemArray[i] == null) {// 如果当前位置为空
				continue;// 继续查找
			} else {
				long itsKey = itemArray[i].dData;// 获取当前位置数据项的值
				if (newKey < itsKey) {// 如果新数据项小于当前数据项的值
					itemArray[i + 1] = itemArray[i];// 将当前位置的数据项放到当前位置的后一个位置
				} else {
					itemArray[i + 1] = newItem;// 将新数据项当如当前位置的后一个位置
					return i + 1;// 返回新数据项的位置
				}
			}
		}
		// 如果当前节点的数据项为空直接放到第一位
		itemArray[0] = newItem;
		return 0;
	}

	// 删除数据项，返回被删除的数据项，没有指定要删除的位置，删除最后一个
	public DataItem removeItem() {
		DataItem temp = itemArray[numItems - 1];
		itemArray[numItems - 1] = null;
		numItems--;
		return temp;
	}

	// 打印当前节点
	public void displayNode() {
		for (DataItem item : itemArray) {
			if (null != item) {
				item.disPlayItem();
			}

		}
		System.out.println("-----");
	}

}
