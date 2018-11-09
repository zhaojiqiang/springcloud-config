package hongheishu.TEST2;

import hongheishu.RBTree;

public class RBTREE<T extends Comparable<T>> {
	boolean		RED	= true;
	boolean		BLACK	= false;
	public class RBNode<T extends Comparable<T>>{
		
		boolean		color;		// 颜色
		T			key;		// 关键字(键值)
		RBNode<T>	left;		// 左子节点
		RBNode<T>	right;		// 右子节点
		RBNode<T>	parent;		// 父节点

		public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
			this.key = key;
			this.color = color;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		public T getKey() {
			return key;
		}

		public String toString() {
			return "" + key + (this.color == RED ? "R" : "B");
		}
	}

	RBNode<T> root;
	
	

	/**
	 *******************对红黑树节点X进行左旋
	 * 左旋示意图
	 * 		p					p
	 * 	   /				   / 
	 * 	  x					  y
	 * 	 / \	----->       / \
	 * 	lx	y               x   ry
	 * 	   / \             / \
	 * 	  ly ry           lx ly
	 * 
	 * 左旋主要做了三件事
	 * 1.将y的左子节点，付给x的右子节点，并将y付给x的左子节点的父节点（y左子节点非空时）
	 * 2.将x的父节点p（p非空时）付给y的父节点，并将更新p的子节点为y
	 * 3.将y的左子节点设置为x，并将x的父节点设置为y
	 */
	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private void leftRotate(RBNode<T> x) {
		// 1.将y的左子节点，付给x的右子节点，并将y付给x的左子节点的父节点（y左子节点非空时）
		RBNode y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		// 2.将x的父节点p（p非空时）付给y的父节点，并将更新p的子节点为y
		y.parent = x.parent;
		if (x.parent == null) {
			root = y;
		} else {
			if (x.parent.left == x) {
				x.parent.left = y;
			} else {
				x.parent.right = y;
			}
		}
		// 3.将y的左子节点设置为x，并将x的父节点设置为y
		y.left = x;
		x.parent = y;
	}
	/**
	 * *****************对红黑树节点y向右旋转
	 * 右旋示意图：对y进行右旋
	 * 			p					p
	 * 		   /				   /
	 * 		  y				      x
	 * 		 / \				 / \
	 * 		x	ry --------->   lx  y
	 * 	   / \					   / \
	 * 	  lx rx					  rx ry
	 * 右旋做了三件事：
	 * 	1.将x的右子节点付给y的左子节点，并将x左子节点的父节点设置为y（x右子节点非空时）
	 * 	2.将y的父节点p（非空时）付给x的父节点，并将p的子节点设置为x
	 * 	3.将x的右子节点设置为y，并将y的父节点设置为x
	 * 
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private void rightRotate(RBNode<T> y) {
		// 1.将x的右子节点付给y的左子节点，并将x左子节点的父节点设置为y（x右子节点非空时）
		RBNode x = y.left;
		y.left = x.right;
		if (x.right != null) {
			x.right.parent = y;
		}
		// 2.将y的父节点p（非空时）付给x的父节点，并将p的子节点设置为x
		if (y.parent == null) {
			root = x;
		} else {
			if (y.parent.left == y) {
				y.parent.left = x;
			} else {
				y.parent.right = x;
			}
		}
		// 3.将x的右子节点设置为y，并将y的父节点设置为x
		x.right = y;
		y.parent = x;
	}

	/************************************向红黑树中插入节点*************************************************/
	public void insert(T key){
		RBNode<T> node = new RBNode<>(key, RED, null, null, null);
		if(null != node){
			insert(node);
		}
	}
	//将节点插入红黑树
	private void insert(RBNode<T> node) {
		RBNode<T> current = null;//表示node的父节点
		RBNode<T>  x = root;//用于向下搜索用的
		//1.找到插入的位置
		while(x != null){
			current = x;
			int cmp = node.key.compareTo(x.key);
			if(cmp < 0){
				x = x.left;
			}else{
				x = x.right;
			}
		}
		
		node.parent = current;//找到了位置，将node的parent设置为current；
		//2.接下来判断node是左节点还是右节点
		if(current == null){
			root = node;
		}else{
			int cmp = node.key.compareTo(current.key);
			if(cmp < 0){
				current.left = node;
			}else{
				current.right = node;
			}
		}
		//3.将它重新修正为一颗红黑树
		insertFixUp(node);
	}

	/**
	 * 如果是第一次插入，由于原树是空的，所以只会违反红黑树规则2，所以只要把根节点图黑即可，如果插入的节点的父节点是黑色的，那么不会未被任何规则，什么也不用做，但是遇到以下三种情况就要变色或者旋转了
	 * 情况1.插入的节点和其叔叔节点都是红色     									       ------>变色，父节点叔叔节点变黑，祖父变红（在用祖父递归）
	 * 情况2.插入节点的父节点是红色，叔叔节点是黑色，且当前节点是父节点的右子节点 --->将当前节点的父节点作为新的节点，以当前节点作为支点进行左旋操作，
	 * 情况3.插入节点的父节点是红色，叔叔节点是黑色，且当前节点是父节点的左子节点 --->当前节点的父节点图黑，祖父节点图红，祖父节点进行右旋操作，最后把根节点图黑。
	 * 
	 */
	private void insertFixUp(RBNode<T> node) {
		RBNode<T> parent,gparent;//定义父节点与祖父节点
		//需要修正的条件：父节点存在，而且父节点是红色-------->此时肯定有祖父节点
		while ((parent = parentOf(node)) != null && isRed(parent)) {
			//获取祖父节点
			gparent = parentOf(parent);
			//若父节点是祖父节点的左子节点
			if(parent == gparent.left){
				RBNode<T> uncle = gparent.right;//获取叔叔节点
				//case1: 叔叔节点也是红色
				if(uncle != null && isRed(uncle)){
					setBlack(parent);//将叔叔和父节点涂黑
					setBlack(uncle);
					setRed(gparent);//祖父节点图红
					node = gparent;//将节点放置祖父节点
					continue;
				}
				
				//case2:叔叔是黑色且当前节点是父节点的右子节点
				if(node == parent.right){
					leftRotate(parent);//父节点左旋
					RBNode<T> tmp = parent;//将父节点和自己对调一下，为下边的右旋做准备
					parent = node;
					node = tmp;
				}
				//case3:叔叔节点是黑色的且当前节点是父节点的左子节点
				setBlack(parent);
				setRed(gparent);
				rightRotate(gparent);
			}else{//父节点是祖父节点的右子节点
				RBNode<T> uncle = gparent.left;//获取叔叔节点
				//case1: 叔叔节点也是红色
				if(uncle != null && isRed(uncle)){
					setBlack(parent);//将叔叔和父节点涂黑
					setBlack(uncle);
					setRed(gparent);//祖父节点图红
					node = gparent;//将节点放置祖父节点
					continue;
				}
				
				//case2:叔叔是黑色且当前节点是父节点的左子节点
				if(node == parent.left){
					rightRotate(parent);//父节点右旋
					RBNode<T> tmp = parent;//将父节点和自己对调一下，为下边的右旋做准备
					parent = node;
					node = tmp;
				}
				//case3:叔叔节点是黑色的且当前节点是父节点的右子节点
				setBlack(parent);
				setRed(gparent);
				leftRotate(gparent);
			}
		}
		
		setBlack(root);//将根节点设置为黑色
	}
	
	
	
	private void setRed(RBNode<T> node) {
		if(node != null){
			node.color = RED;
		}	
	}

	private void setBlack(RBNode<T> node) {
		if(node != null){
			node.color = BLACK;
		}
	}

	public RBNode<T> parentOf(RBNode<T> node) { //获得父节点
		return node != null? node.parent : null;
	}
	
	
	public boolean isRed(RBNode<T> node) { //判断节点的颜色
		return (node != null)&&(node.color == RED)? true : false;
	}
	public void setParent(RBNode<T> node, RBNode<T> parent) { //设置父节点
		if(node != null) 
			node.parent = parent;
	}

	/***前序遍历红黑树*/
	public void preOrder(){
		preOrder(root);
	}
	
	private void preOrder(RBTREE<T>.RBNode<T> treeNode) {
		if(null != treeNode){
			System.out.println(treeNode.key);
			preOrder(treeNode.left);
			preOrder(treeNode.right);
		}
	}
	public boolean colorOf(RBNode<T> node) { //获得节点的颜色
		return node != null? node.color : BLACK;
	}
	public boolean isBlack(RBNode<T> node) {
		return !isRed(node);
	}
	public void setColor(RBNode<T> node, boolean color) {
		if(node != null)
			node.color = color;
	}

	/**********中序遍历红黑树*/
	public void inOrder(){
		inOrder(root);
	}

	public void inOrder(RBTREE<T>.RBNode<T> tree) {
		if(null != tree){
			inOrder(tree.left);
			System.out.println(tree.key);
			inOrder(tree.right);
		}
	}

	public void postOrder(){
		postOrder(root);
	}
	private void postOrder(RBTREE<T>.RBNode<T> tree) {
		if(null != tree){
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.println(tree.key);
		}
	}
	
	public RBTREE<T>.RBNode<T> search(T key){
		return search(root,key);
	}
	public RBTREE<T>.RBNode<T> search(RBNode<T> x,T key){
		while(x != null){
			int cmp = key.compareTo(x.key);
			if(cmp < 0){
				x = x.left;
			}else if(cmp > 0){
				x = x.right;
			}else{
				return x;
			}
		}
		return null;
	}
	
	/********递归*/
	public RBTREE<T>.RBNode<T> search2(RBNode<T> x,T key){
		while(x != null){
			int cmp = key.compareTo(x.key);
			if(cmp < 0){
				x = search2(x.left, key);
			}else if(cmp > 0){
				x = search2(x.right, key);
			}else{
				return x;
			}
		}
		return null;
	}
	
	public T minValue(){
		RBNode<T> node = minNode(root);
		if(null != node){
			return node.key;
		}
		return null;
	}
	
	private RBTREE<T>.RBNode<T> minNode(RBTREE<T>.RBNode<T> tree) {
		if(null == tree){
			return null;
		}
		while(tree != null){
			tree = tree.left;
		}
		return tree;
	}

	public T maxValue(){
		RBNode<T> node = maxValue(root);
		if(null != node){
			return node.key;
		}
		return null;
	}
	
	private RBTREE<T>.RBNode<T> maxValue(RBTREE<T>.RBNode<T> tree) {
		if(null == tree){
			return null;
		}
		while(tree != null){
			tree = tree.right;
		}
		return tree;
	}
	
	/******查找x的后继节点，即大于x的最小节点*/
	public RBNode<T> successor(RBNode<T> x){
		//如果x有右子节点，那么后几点即是 以x右子节点为根的子树的最小节点
		if(null != x.right){
			return minNode(x.right);
		}
		//如果x没有右子节点，会出现以下两种情况
		//1.x是其父节点的左子节点，则x的后继节点是他的父节点
		//2.x是其父节点的右子节点，则县查找x节点的父节点p，在对p进行这两步的判断
		RBTREE<T>.RBNode<T> p = x.parent;
		while(p != null && p.right == x){//对应情况2
			x = p;
			p = p.parent;
		}
		return p;//对应情况1
	}
	
	
	/******查找x的前驱节点，即小于x的最大节点*/
	public RBNode<T> predecrssor(RBNode<T> x){
		//如果x有左子节点，那么前驱节点即是 以x左子节点为根的子树的最大节点
		if(null != x.left){
			return maxValue(x.right);
		}
		//如果x没有左子节点，会出现以下两种情况
		//1.x是其父节点的右子节点，则x的前驱节点是他的父节点
		//2.x是其父节点的左子节点，则先查找x节点的父节点p，在对p进行这两步的判断
		RBTREE<T>.RBNode<T> p = x.parent;
		while(p != null && p.left == x){//对应情况2
			x = p;
			p = p.parent;
		}
		return p;//对应情况1
	}
	
	public void remove(T key){
		RBTREE<T>.RBNode<T> node = search(key);
		
		if(node != null){
			remove(node);
		}
	}
	
	

	private void remove(RBTREE<T>.RBNode<T> node) {//60
		RBNode<T> chile ,parent;
		boolean color;
		//被删除的节点左右都不为空的情况下
		//				56				65				
		if(null != node.left && null != node.right){
			//先找到被删除节点的后继节点，用它来代替被删除的节点
			RBNode<T> replace = node;//60
			//1)获取后继节点
			replace = replace.right;//65 //end 62
			while(replace.left != null){
				replace = replace.left;//end 62
			}
			//2）处理被删除节点和后继节点的父子关系
			if(parentOf(node) != null){//要删除的节点不是根节点 //50
				if(node == parentOf(node).left){
					parentOf(node).left = replace;
				}else{
					parentOf(node).right = replace;//62
				}
			}else{
				root = replace;
			}
			
			//3)处理后继节点的子节点和被删除节点的子节点之间的关系
			chile = replace.right; //null
			parent = parentOf(replace);//64
			color = colorOf(replace);//black
			if(parent == node){//后继节点是被删除节点的子节点
				parent = replace;
			}else{
				if(chile != null){
					setParent(chile, parent);
				}
				parent.left = chile;//null
				replace.right = node.right;
				setParent(node.right, replace);
			}
			
			replace.parent = node.parent;
			replace.color = node.color;//保持原来位置的颜色
			replace.left = node.left;
			node.left.parent = replace;
			if(color == BLACK){//4)如果后继节点是黑色，重新修正红黑树
				removeFixUp(chile,parent);//将后继节点的chile 和parent 传进去
			}
			node = null;
			return;
		}
	}


	//node表示后继节点的子节点，因为后继节点被挪到删除的位置上去了
	private void removeFixUp(RBTREE<T>.RBNode<T> node, RBTREE<T>.RBNode<T> parent) {
		RBNode<T> other = null;
		while((node == null || isBlack(node)) && node != root){
			if(node == parent.left){//node是左子节点
				other = parent.right;//node 的兄弟节点
				if(isRed(other)){//case1:的兄弟节点是红色的
					setBlack(other);
					setRed(parent);
					other = parent.right;
				}
				
				//case2:node的兄弟节点是黑色的，并且other的两个子节点也是黑色的
				if((other.left == null || isBlack(other.left)) && other.right == null || isBlack(other.right)){
					setRed(other);
					node = parent;
					parent = parentOf(node);
				}else{
					//case3:node 兄弟节点other黑色，并且other左子节点是红色，右子节点是黑色的
					if(other.right == null || isBlack(other.right)){
						setBlack(other.left);
						setRed(other);
						rightRotate(other);
						other = parent.right;
					}
					//case4：node的兄弟节点other是黑色的，且other的右子节点红色，左子节点任意色
					setColor(other, colorOf(parent));
					setBlack(parent);
					setBlack(other.right);
					leftRotate(parent);
					node = root;
					break;
				}
			}else{
				other = parent.left;
			}
			
			if(isRed(other)){
				//case1:兄弟节点other是红色的
				setBlack(other);
				setRed(parent);
				rightRotate(parent);
				other = parent.left;
			}
			if ((other.left==null || isBlack(other.left)) &&
	                (other.right==null || isBlack(other.right))) {
	                // Case 2: node的兄弟other是黑色，且other的俩个子节点都是黑色的  
	                setRed(other);
	                node = parent;
	                parent = parentOf(node);
	            } else {
 
	                if (other.left==null || isBlack(other.left)) {
	                    // Case 3: node的兄弟other是黑色的，并且other的左子节点是红色，右子节点为黑色。  
	                    setBlack(other.right);
	                    setRed(other);
	                    leftRotate(other);
	                    other = parent.left;
	                }
 
	                // Case 4: node的兄弟other是黑色的；并且other的左子节点是红色的，右子节点任意颜色
	                setColor(other, colorOf(parent));
	                setBlack(parent);
	                setBlack(other.left);
	                rightRotate(parent);
	                node = this.root;
	                break;
	            }
		}
		if (node!=null){
	        setBlack(node);
		}
	        
	}

	/****************** 销毁红黑树 *********************/
	public void clear() {
		destroy(root);
		root = null;
	}
 
	private void destroy(RBNode<T> tree) {
		if(tree == null) 
			return;
		if(tree.left != null) 
			destroy(tree.left);
		if(tree.right != null) 
			destroy(tree.right);
		tree = null;
	}
 
	/******************* 打印红黑树 *********************/
	public void print() {
		if(root != null) {
			print(root, root.key, 0);
		}
	}
	/*
	 * key---节点的键值
	 * direction--- 0:表示该节点是根节点
	 *              1:表示该节点是它的父节点的左子节点
	 *              2:表示该节点是它的父节点的右子节点
	 */
	private void print(RBNode<T> tree, T key, int direction) {
		if(tree != null) {
			if(0 == direction) 
				System.out.printf("%2d(B) is root\n", tree.key);
			else
				System.out.printf("%2d(%s) is %2d's %6s child\n", 
						tree.key, isRed(tree)?"R":"b", key, direction == 1?"right":"left");
				print(tree.left, tree.key, -1);
				print(tree.right, tree.key, 1);
		}
	}

	private static final int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
	private static final boolean mDebugInsert = true;    // "插入"动作的检测开关(false，关闭；true，打开)
        private static final boolean mDebugDelete = true;    // "删除"动作的检测开关(false，关闭；true，打开)
 
	public static void main(String[] args) {
		int i, ilen = a.length;
                RBTREE<Integer> tree = new RBTREE<Integer>();
 
                System.out.printf("== 原始数据: ");
                for(i=0; i<ilen; i++)
                    System.out.printf("%d ", a[i]);
                System.out.printf("\n");
 
                for(i=0; i<ilen; i++) {
                   tree.insert(a[i]);
                    // 设置mDebugInsert=true,测试"添加函数"
                    if (mDebugInsert) {
                        System.out.printf("== 添加节点: %d\n", a[i]);
                        System.out.printf("== 树的详细信息: \n");
                        tree.print();
                        System.out.printf("\n");
                    }
                }
 
                System.out.printf("== 前序遍历: ");
                tree.preOrder();
 
                System.out.printf("\n== 中序遍历: ");
                tree.inOrder();
 
                System.out.printf("\n== 后序遍历: ");
                tree.postOrder();
                System.out.printf("\n");
 
                System.out.printf("== 最小值: %s\n", tree.minValue());
                System.out.printf("== 最大值: %s\n", tree.maxValue());
                System.out.printf("== 树的详细信息: \n");
                tree.print();
                System.out.printf("\n");
        
                // 设置mDebugDelete=true,测试"删除函数"
                if (mDebugDelete) {
                    for(i=0; i<ilen; i++)
                    {
                        tree.remove(a[i]);
 
                        System.out.printf("== 删除节点: %d\n", a[i]);
                        System.out.printf("== 树的详细信息: \n");
                        tree.print();
                        System.out.printf("\n");
                    }
                }
        }

	
}
