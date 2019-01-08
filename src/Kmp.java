import java.util.Arrays;

public class Kmp {
	static int l = 0;

	
	public static int[] cal_next(String ptr){
		char[] p = ptr.toCharArray();
		int[] next = new int[p.length];
		next[0] = -1;
		int k = -1;
		for (int q = 1; q < next.length; q++) {
			l ++;
			while(k > -1 && p[k+1] != p[q]){
				l++;
				k = next[k];
			}
			if(p[k+1] == p[q]){
				k +=1;
			}
			next[q] = k;
		}
		return next;
	}
	
	public static void main(String[] args) {
		int pat = pat("abcdefgfd","de");
		System.out.println(pat);
	}
	
	public static int pat(String s,String p){
		int[] next = cal_next(p);
		char[] sa = s.toCharArray();
		char[] pa = p.toCharArray();
		int k = -1;
		for (int i = 0; i < sa.length; i++) {
			while (k > -1 &&  pa[k+1] != sa[i]) {
				k = next[k];
			}
			if(pa[k+1] == sa[i]){
				k+=1;
			}
			if(k == pa.length-1){
				return  i - k;
			}
		}
		return -1;
	}

}
