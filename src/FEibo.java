
public class FEibo {
	
	
	
	
		
		public static void main(String[] args) {
			int p = FEiboD(12);
			System.out.println(p);
		}

		private static int FEiboD(int i) {
			if(i<=1){
				return i;
			}else{
				return FEiboD((i-1))+FEiboD((i-2));
			}
		}

}
