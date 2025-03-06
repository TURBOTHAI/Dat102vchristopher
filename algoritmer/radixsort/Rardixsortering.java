package radixsort;

public class Rardixsortering {
	
	
	public static void main(String[]args) {
		int[] array= {8,2,5,3,9,4,7,6,5};
		
		radixSort(array);
		
		 for (int i : array) {
			System.out.print(i+" ");
		}
		
	}
	
	private static int getMaks(int[]array) {
		int maks=array[0];//starter med at siste posisjon er den største
		for(int i:array) {
			if(i>maks) {
				maks=i;
			}
		}
		return maks;
	}
	
	private static void tellerSort(int[] array, int exp) {
		int n = array.length;
		int[] teller=new int[10]; //teller array for sifre (0,9)	
		
		for(int i:array) {
			teller[(i/exp)%10]++;
		}
		
		for(int i=1;i<10;i++) {
			teller[i]+=teller[i-1];
			
		}
		
		
		for(int i =n-1;i>=0;i--) {
			int siffer=(array[i]/exp%10);
			while(teller[siffer]-1!=i) {
				int korrektindeks=teller[siffer]-1;				
				int temp=array[korrektindeks];
				array[korrektindeks]=array[i];
				array[i]=temp;
				teller[siffer]--;
				siffer=(array[i]/exp)%10;
			}
			
			teller[siffer]--;
		}
	}
	
	public static void radixSort(int[] array) {
		
		int maks= getMaks(array);
		
		for( int exp=1;maks/exp>0;exp*=10) {
			tellerSort(array,exp);
			
		}
		
				
	}
	
}
		

