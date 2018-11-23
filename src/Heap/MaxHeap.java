package Heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {

	private List<T> mHeap;// 队列（实际上由动态数组arraylist实现）

	public MaxHeap() {
		this.mHeap = new ArrayList<T>();
	}

	public void insert(T data) {
		int size = mHeap.size();
		mHeap.add(data);// 将数据插入表尾部
		filterUp(size);// 向上调整堆
	}

	private void filterUp(int start) {
		int c = start;
		int p = (c - 1) / 2; // 找到父节点

		T tmp = mHeap.get(c);

		while (c > 0) {
			int cmp = mHeap.get(p).compareTo(tmp);
			if (cmp >= 0) {
				break;
			} else {
				mHeap.set(c, mHeap.get(p));
				c = p;
				p = (p - 1) / 2;
			}
		}
		mHeap.set(c, tmp);
	}

	/**
	 * 删除堆中最大的元素 -1 失败 0成功
	 */
	public int remove(T data) {
		// 如果堆已空返回 -1
		if (mHeap.isEmpty()) {
			return -1;
		}
		// 获取data在数组中的索引
		int index = mHeap.indexOf(data);
		if (index == -1) {
			return index;
		}

		int size = mHeap.size();
		// 用最后一个元素填补
		mHeap.set(index, mHeap.get(size - 1));
		// 删除最后的元素
		mHeap.remove(size - 1);

		if (mHeap.size() > 1) {
			filterDown(index, mHeap.size() - 1);// 向下调整
		}
		return 0;
	}

	public void filterDown(int start, int end) {
		int c = start; // 当前节点的位置
		int l = 2 * c + 1;// 当前节点左子节点位置

		T tmp = mHeap.get(c);// 当前节点的元素

		while (l < end) {
			int cmp = mHeap.get(l).compareTo(mHeap.get(l + 1));
			// l 是左子节点，l+1是右子节点
			if (l < end && cmp < 0) {
				l++;// 左右子节点取最大的 l++就变成右节点了
			}
			int cmp2 = tmp.compareTo(mHeap.get(l));
			if (cmp2 > 0) {
				break;
			} else {
				mHeap.set(c, mHeap.get(l));
				c = l;
				l = 2 * l + 1;
			}
		}
		mHeap.set(c, tmp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mHeap.size(); i++)
			sb.append(mHeap.get(i) + " ");

		return sb.toString();
	}

	public static void main(String[] args) {
		int i;
		int a[] = { 10, 40, 30, 60, 90, 70, 20, 50, 80 };
		MaxHeap<Integer> tree = new MaxHeap<Integer>();

		System.out.printf("== 依次添加: ");
		for (i = 0; i < a.length; i++) {
			System.out.printf("%d ", a[i]);
			tree.insert(a[i]);
		}
		System.out.printf("\n== 最 大 堆: %s", tree);
		System.out.printf("\n");
		
		dupai(tree);
	}

	private static void dupai(MaxHeap<Integer> tree) {
		for (int i = 1; i < tree.mHeap.size(); i++) {
			Integer root = tree.mHeap.get(0);
			Integer last = tree.mHeap.get(tree.mHeap.size() -i);
			tree.mHeap.set(0, last);
			tree.mHeap.set(tree.mHeap.size() -i, root);
			tree.filterDown(0, tree.mHeap.size()-i-1);
		}
		System.out.println(tree);
	}
	
	
	/**
	 * 	最大堆
	 * 	找左子节点：2*N-1  右子节点：2*N+2(左子节点+1)
	 * 	找父节点：（N-1）/2;
	 * 
	 * 	插入：直接插入到最后位置 ，向上调整，换位子
	 * 	删除：删除最前位置，用最后一个元素顶替，向下调整，换位子
	 */

}
