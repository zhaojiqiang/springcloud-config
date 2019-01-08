
public class BF {
	
	static String a = "pweaskdnkajsnpwendasndasjkldnpwe";
	
	public static void main(String[] args) {
		String l = "pwen";
		char[] n = a.toCharArray();
		char[] m = l.toCharArray();
		int j = 0;
		int y = 0;
		int i;
		String f = "";
		for (i = 0; i < n.length; i++) {
			if(n[i] == m[j]){
				y = i;
				System.out.println(n[i]);
				j++;
				f+=n[i];
			}else{
				if(j!=0){
					j = 0;
					i--;
					f = "";
				}
			}
			if(j == m.length){
				System.out.println((i-j+1) + " ---"+(i));
				System.out.println(f);
				return;
			}
		}
		
	}

}
