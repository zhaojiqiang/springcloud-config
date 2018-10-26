import java.util.Arrays;

public class FastP {

	
	private long[] theArray;
	
	private int nElems;

	public FastP(int max) {
		theArray = new long[max];
		nElems = 0;
	}
	
	public void insert(long value){
		theArray[nElems] = value;
		nElems++;
	}
	
	public void disPlay(){
		System.out.println(Arrays.toString(theArray));
	}
	
	public void quickSort(){
		recQuickSort(0,nElems-1);
	}

	public void recQuickSort(int left, int right) {
		if(right - left <= 0){
			return;
		}else{
			long pivot = theArray[right];
			int partitionIt = partitionIt(left,right,pivot);
			recQuickSort(left, partitionIt -1);
			recQuickSort(partitionIt+1, right);

		}
	}

	public int partitionIt(int left, int right, long pivot) {
		int leftPtr = left -1;
		int rightPtr = right;
		for(;;){
			while (theArray[++leftPtr] < pivot) {
			}
			while (rightPtr > 0 && theArray[--rightPtr] > pivot) {
			}
			if(leftPtr >= rightPtr){
				break;
			}else{
				swap(leftPtr,rightPtr);
			}
		}
		swap(leftPtr, right);
		return leftPtr;
	}

	public void swap(int leftPtr, int rightPtr) {
		long temp = theArray[leftPtr];
		theArray[leftPtr] = theArray[rightPtr];
		theArray[rightPtr] = temp;
	}
	
	
	public static void main(String[] args) {
		FastP fastP = new FastP(10);
		fastP.insert(8L);
		fastP.insert(12L);
		fastP.insert(41L);
		fastP.insert(0L);
		fastP.insert(2L);
		fastP.insert(90L);
		fastP.insert(33L);
		fastP.insert(5L);
		fastP.insert(98L);
		fastP.insert(56L);
		fastP.disPlay();
		fastP.quickSort();
		fastP.disPlay();

	}
	
	
		
}
