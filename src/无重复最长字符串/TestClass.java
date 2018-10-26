package 无重复最长字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestClass {
	
	
	
	
	
	public static int getBuchongfu(String a){
		int n = a.length();
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j <= n; j++) {
				if(chong(a,i,j)){
					ans = Math.max(ans, j-i);
				}
			}
		}
		return ans;
	}

	private static boolean chong(String a, int start, int end) {
		Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch =a.charAt(i);
            if (set.contains(ch)) 
            	return false;
            set.add(ch);
        }
        return true;

	}
	
	public static void main(String[] args) {
		
		System.out.println(lengthOfLongestSubstring("asasasashjhjhijijopiuy"));
		
	}
	public static int huadong(String a){
		int n = a.length();
		int ans = 0 ,i = 0,j = 0;
		HashSet<Character> s = new HashSet<>();

		while(i<n && j<n){
			char charAt2 = a.charAt(j);
			if(!s.contains(charAt2)){
				char charAt = a.charAt(j++);
				s.add(charAt);
				ans = Math.max(ans, j-i);
			}else{
				char charAt = a.charAt(i++);
				s.remove(charAt);
				
			}
		}
		return ans;
	}
	
	public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


}
