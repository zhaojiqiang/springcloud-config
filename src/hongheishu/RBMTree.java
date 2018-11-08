package hongheishu;

import java.util.ArrayList;
import java.util.List;

public class RBMTree<T extends Comparable<T>> {
	private static final boolean	RED		= true;
	private static final boolean	BLACK	= false;

	private TreeNode<T>				root;

	public class TreeNode<T extends Comparable<T>> {
		T			data;
		boolean		color;		// 颜色RED true BLACK false
		boolean		isDelete;
		TreeNode<T>	left;		// 左子节点
		TreeNode<T>	right;		// 右子节点
		TreeNode<T>	parent;
		public TreeNode(T data, boolean color) {
			this.data = data;
			this.color = color;
		}
		public TreeNode(T data) {
			this.color = RED;

			this.data = data;
		}
		@Override
		public String toString() {
			return "TreeNode [data=" + data + ", color=" + color + ", isDelete=" + isDelete + ", left=" + left
					+ ", right=" + right + ", parent=" + parent + "]";
		}

	}

	public void insertNode(T data) {// 插入一个节点
		TreeNode<T> curr = null;
		TreeNode<T> parent = toTargetparent(data);// 1.找到插入节点的父节点
		if (parent == null) {
			curr = root = new TreeNode<T>(data);// 第一个节点，即根节点
		} else {
			// 2.找到插入节点的位置
			if (data.compareTo(parent.data) < 0) {
				curr = parent.left = new TreeNode<T>(data);
				curr.parent = parent;
			} else {
				curr = parent.right = new TreeNode<T>(data);
				curr.parent = parent;
			}
		}
		// 处理冲突
		fixupTree(curr);
	}

	private void fixupTree(TreeNode<T> node) {
		// 父节点 祖父节点
		TreeNode parent = null, grandParent = null;
		// 父节点颜色 叔叔节点颜色
		boolean parentColor = false, uncleColor = false;
		// 是否根节点
		if (node != root) {
			parent = node.parent;// 3.找到父节点
			grandParent = node.parent.parent;// 3.找到祖父节点
			parentColor = node.parent.color;// 3.找到父节点的颜色
			uncleColor = getuncleColor(node);// 3.找到叔叔节点的颜色
		}
		// 4.判断是否是根节点
		if (parent == null && node.color == RED) {
			node.color = BLACK;
			// 5.父节点是红色，叔叔节点是红色
		} else if (parentColor == RED && uncleColor == RED) {
			// 6.改变颜色 传入祖父节点
			chageColor(grandParent);
			// 7.再次解决冲突
			fixupTree(grandParent);
			// 8.叔叔节点是黑色，父节点是红色
		} else if (uncleColor == BLACK && parentColor == RED) {
			// 再次判断旋转
			dispatchRotation(grandParent, parent, node);
		}

	}

	private void dispatchRotation(TreeNode grandParent, TreeNode parent, RBMTree<T>.TreeNode<T> node) {
		// 9.父节点在祖父节点的左侧
		if (parent == grandParent.left) {
			// 10.插入节点在父节点左侧
			if (node == parent.left) {
				// 祖父右旋
				rightRotation(grandParent);
			} else {
				// 父节点左旋
				leftRotation(parent);
				// 祖父右旋
				rightRotation(grandParent);
			}
		} else {// 11.父节点在祖父节点的右侧
				// 12.插入节点在父节点左侧
			if (node == parent.left) {
				// 父节点右旋
				rightRotation(parent);
				// 祖父节点左旋
				leftRotation(grandParent);
			} else {
				// 祖父左旋
				leftRotation(grandParent);
			}
		}
	}
	/*
	 * 将传入节点的右子节点提升为父节点，将其降为左子节点
	 */
	private void leftRotation(TreeNode node) {
		TreeNode tParent = node.right;
		tParent.parent = node.parent;
		tParent.color = BLACK;
		// 新父节点的左子节点放到传入节点的右边
		node.right = tParent.left;
		if (tParent.left != null) {
			tParent.left.parent = node;
		}
		// 降位子节点钱的数据处理
		node.color = RED;
		node.parent = tParent;
		tParent.left = node;
		setChild(node, tParent);
	}

	/*
	 * 将传入的左子节点提升为父节点，将其降为右子节点 注意颜色，父节点需要处理，务必记住要清除传入节点的坐左子节点，因为已经升为父节点了
	 */
	private void rightRotation(TreeNode node) {// 右旋
		// 新的祖父节点
		TreeNode tParent = node.left;
		// 新父节点的父节点 为 旧父节点的 父节点
		tParent.parent = node.parent;
		// 新父节点设置为黑色
		tParent.color = BLACK;
		// 旧腹父节点的左子节点 设置为 新父节点的右子节点
		node.left = tParent.right;
		if (tParent.right != null) {
			// 设置新父节点的右自子节点的父节点为旧的父节点
			tParent.right.parent = node;
		}
		// 将输入节点做为新子节点前就行数据清理
		// 旧福父节点设置为红色
		node.color = RED;
		// 旧父节点的父节点设置为新父节点
		node.parent = tParent;
		// 新父节点的右子节点设置为 旧父节点
		tParent.right = node;
		// 设置旧父节点的父节点
		setChild(node, tParent);
	}

	/*
	 * 使旋转在树中生效 node 被旋转的节点 newParent 旋转之后的新的父节点
	 */
	private void setChild(TreeNode node, TreeNode newParent) {
		// 获取原父节点的父节点
		TreeNode roNodeParent = newParent.parent;
		if (roNodeParent == null) {
			root = newParent;// 根节点
		} else if (roNodeParent.left == node) {
			roNodeParent.left = newParent;
		} else {
			roNodeParent.right = newParent;
		}
	}

	private void chageColor(TreeNode grandParent) {
		// 将祖父节点变为红色
		grandParent.color = RED;
		// 父节点与叔叔节点变为黑色
		if (grandParent.left != null) {
			grandParent.left.color = BLACK;
		}
		if (grandParent.right != null) {
			grandParent.right.color = BLACK;
		}

	}

	private boolean getuncleColor(TreeNode node) {
		// 插入节点的父节点
		TreeNode parent = node.parent;
		// 插入节点的父节为空说明是根节点
		return getBortherColor(parent.parent == null ? node : parent);
	}

	private boolean getBortherColor(@SuppressWarnings("rawtypes") TreeNode chile) {
		// 传入插入节点父节点
		// 祖父节点
		TreeNode parent = chile.parent;
		// 找到叔叔节点的位置，如果没有叔叔则是 黑色
		if (parent.left == chile && parent.right != null) {
			return parent.right.color;
		} else if (parent.right == chile && parent.left != null) {
			return parent.left.color;
		} else {
			// 空节点
			return BLACK;
		}
	}

	private TreeNode<T> toTargetparent(T data) {
		TreeNode<T> curr = root;
		TreeNode<T> parent = root;
		while (curr != null) {
			parent = curr;
			if (data.compareTo(curr.data) < 0) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		return parent;

	}

	public static void main(String[] args) {

		/// *
		int[] testNum = new int[] { 15, 1, 3, 6, 8, 20, 22, 43, 67 };
		// RBTree<Integer> fuck = new RBTree<Integer>();
		/*
		 * for (int i = 0; i < testNum.length; i++) { fuck.insertNode(testNum[i]); } System.out.println(fuck.root.data);
		 */
		// */

		RBMTree<Integer> fuck = new RBMTree<Integer>();
		fuck.insertNode(50);
		fuck.insertNode(16);
		fuck.insertNode(66);
		fuck.insertNode(12);
		fuck.insertNode(11);
		fuck.insertNode(52);
		fuck.insertNode(68);
		fuck.insertNode(19);
		fuck.insertNode(15);
		List<String> inorderTraversal = inorderTraversal(fuck.root);
		System.out.println(fuck.root.data);
		for (String string : inorderTraversal) {
			System.out.println(string);
		}
	}
	public static List<String> inorderTraversal(RBMTree.TreeNode root) {
		List<String> result = new ArrayList<String>();
		recurse(root, result);
		return result;
	}

	private static void recurse(RBMTree.TreeNode root, List<String> result) {
		if (root == null)
			return;
		String color = root.color == true ? "red" : "black";
		recurse(root.left, result);
		result.add(root.data + " -------" + color);
		recurse(root.right, result);
	}
}
