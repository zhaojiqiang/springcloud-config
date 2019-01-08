package kmp;

import java.util.Arrays;

public class KMP {
	
	
	
	  
	static String t = "asdasoasdasd";
	  public static int[] initNext(String p){
		  int [] next= new int[p.length()];
		  int k = -1,j = 0;
		  char[] ps = p.toCharArray();
		  next[0] = -1;
		  
		  while(j < ps.length-1){
			  if(k == -1 || ps[j] == ps[k]){
				  next[++j] = ++k;
			  }else{
				  k = next[k];
				  t.contains("ssasd");
			  }
		  }
		  System.out.println(Arrays.toString(next));
		  return next;

		  
	  }
	  public static void main(String[] args) {
		System.out.println(KMP("abcdabcdabcdabcp","abcdabcl")); 
	}
	  public static int KMP(String ts, String ps) {

		    char[] t = ts.toCharArray();

		    char[] p = ps.toCharArray();

		    int i = 0; // 主串的位置

		    int j = 0; // 模式串的位置

		    int[] next = initNext(ps);

		    while (i < t.length && j < p.length) {

		       if (j == -1 || t[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0

		           i++;

		           j++;

		       } else {

		           // i不需要回溯了

		           // i = i - j + 1;

		           j = next[j]; // j回到指定位置

		       }

		    }

		    if (j == p.length) {

		       return i - j;

		    } else {

		       return -1;

		    }

		}
   
}