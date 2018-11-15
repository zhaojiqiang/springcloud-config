package sort;

import java.util.Arrays;

public class MaoPao {
	static Integer a [] = {6,9,8,4,2,3,7,1};
	
	public static void mainMaoPao(Integer[] a ) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length-i-1; j++) {
				if(a[j]>a[j+1]){
					int t = a[j];
					a[j]= a[j+1];
					a[j+1] = t;
				}
			}
		}
	}
	
	public static void mainChaRu(String[] args) {
		int n = a.length;
		int temp ,i ,j;
		for (i = 1; i < n; i++) {
			temp = a[i];
			for (j = i; j >0 && a[j-1] > temp; j--) {
				a[j]=a[j-1];
			}
			a[j] = temp;
		}
		System.out.println(Arrays.toString(a));
	}
	
	public static void mainGuibing(String[] args) {
		merg_sort(a,0,a.length);
	}

	private static void merg_sort(Integer[] a2, int p, int r) {
		if(p>=r){
			return;
		}
		int q=(r+p)/2;
		merg_sort(a2, p, q);
		merg_sort(a2, q+1, r);
	}
	
	
	public static void main(String[] args) {
		Integer[] pl = new Integer[100000000];
		for (int i = 0; i < 100000000; i++) {
			pl[i] = new Integer((int) ((Math.random()*9+1)*100000000));
		}
		long currentTimeMillis = System.currentTimeMillis();
		quickSort(pl,0,pl.length-1);
		System.out.println(System.currentTimeMillis() - currentTimeMillis+":ss");
//		System.out.println(Arrays.toString(pl));
	}

	private static void quickSort(Integer[] a2, int s, int e) {
		if(s >= e){
			return;
		}
		int i = s,j = e;
		int tmp = a2[i];
		while(i < j){
			while(i < j && a2[j] >= tmp){
				j--;
			}
			a2[i] = a2[j];
			while(i < j && a2[i] <= tmp){
				i++;
			}
			a2[j] = a2[i];
		}
		a2[i] = tmp;
		quickSort(a2, s, i-1);
		quickSort(a2, i+1, e);
	}
	
}
