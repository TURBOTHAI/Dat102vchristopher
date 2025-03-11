package radixsort;

public class Rardixsortering {
	
	
	public static void main(String[]args) {//lager array med tall i ikkje riktig rekke følge
		int[] array= {8,2,5,3,9,4,7,6,5};
		
		radixSort(array);
		
		 for (int i : array) {
			System.out.print(i+" ");
		}// printer listen ut
		
	}
	
	private static int getMaks(int[]array) {
		int maks=array[0];//starter med at siste posisjon er den største
		for(int i:array) {
			if(i>maks) {
				maks=i;
			}
		}
		return maks;//finner maks verdien i listen
	}
	
	private static void tellerSort(int[] array, int exp) {
	    int n = array.length;
	    int[] output = new int[n]; // Hjelpearray for riktig rekkefølge
	    int[] teller = new int[10]; // Teller for sifre (0-9)

	    // Fyll teller-arrayet (hvor mange ganger hvert siffer forekommer)
	    for (int i = 0; i < n; i++) {
	        int siffer = (array[i] / exp) % 10;
	        teller[siffer]++;
	    }

	    // Endre teller slik at den viser posisjon i output-arrayet
	    for (int i = 1; i < 10; i++) {
	        teller[i] += teller[i - 1];
	    }

	    // Bygg output-arrayet ved å plassere elementene i riktig rekkefølge
	    for (int i = n - 1; i >= 0; i--) {
	        int siffer = (array[i] / exp) % 10;
	        output[teller[siffer] - 1] = array[i]; // Plasser riktig i output
	        teller[siffer]--; // Flytt en posisjon bakover
	    }

	    // Kopier sorterte tall tilbake til originalt array
	    for (int i = 0; i < n; i++) {
	        array[i] = output[i];
	    }
	}

	
	public static void radixSort(int[] array) {
		
		int maks= getMaks(array);
		
		for( int exp=1;maks/exp>0;exp*=10) {
			tellerSort(array,exp);
			
		}
		
				
	}
	
}
		

