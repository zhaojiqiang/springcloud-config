import java.util.Arrays;

public class DiGui {

	
	
		public static void main(String[] args) {
			int a [] = {5,9,15,19,59};
			int b [] = {1,10,12,18,21,63};
			int c[] = new int[a.length+b.length];
			guibing(a,a.length,b,b.length,c);
			System.out.println(Arrays.toString(c));
		}

		private static void guibing(int[] a, int length, int[] b, int length2, int[] c) {
			int aDex=0,bDex=0,cDex=0;
			while(aDex<length&&bDex<length2){
				if(a[aDex]<b[bDex]){
					c[cDex++] = a[aDex++];
				}else {
					c[cDex++] = b[bDex++];
				}
			}
			while(aDex<length){
				c[cDex++]=a[aDex++];
			}
			while(bDex<length2){
				c[cDex++]=b[bDex++];
			}
		}
}
