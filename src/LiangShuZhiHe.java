import java.util.HashMap;
import java.util.Map;

public class LiangShuZhiHe {
	
	
	static int [] p = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	static int o = 15;

	public static void main(String[] args) {
		baoli();
		System.out.println("****************************");
		liangbain();
		System.out.println("****************************");
		yibian();
	}

	
	//暴力法
	public static void baoli(){
		for (int i = 0; i < p.length; i++) {
			for (int j = i+1; j < p.length; j++) {
				if(p[i]+p[j]==o){
					System.out.println(i+"--------------"+j);
				}
			}
		}
	}
	

	//两边hash
	public static void liangbain(){
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		for (int i = 0; i < p.length; i++) {
			map.put(p[i], i);
		}
		
		for (int i = 0; i < p.length; i++) {
			if(map.containsKey(o-p[i])&&map.get(o-p[i]) != i){
				System.out.println(i+"----------"+map.get(o-p[i]));
			}
		}
		
	}
	//一便hash
	public static void yibian() {
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		for (int i = 0; i < p.length; i++) {
			int tar = o-p[i];
			if(map.containsKey(tar)){
				System.out.println(i+"-------------"+map.get(tar));
			}
			map.put(p[i], i);
		}
	}
	
	
}
