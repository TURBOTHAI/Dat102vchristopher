package oppgave1;

import java.util.Random;

public class sortering {
	
	// Flettesorterings metoder
		public static <T extends Comparable<? super T>> void flettesortering(T[] a, int n) {
			flettesortering(a, 0, n - 1);
		}

		// sorterer heile tabellen
		public static <T extends Comparable<? super T>> void flettesortering(T[] a) {
			flettesortering(a, 0, a.length - 1);
		}

		public static <T extends Comparable<? super T>> void flettesortering(T[] a, int first, int last) {

			// The cast is safe because the new array contains null entries
			@SuppressWarnings("unchecked")
			T[] tempArray = (T[]) new Comparable<?>[a.length]; // unchecked cast
			flettesortering(a, tempArray, first, last);
		}

		private static <T extends Comparable<? super T>> void flettesortering(T[] a, T[] tempTab, int forste, int siste) {

			if (forste >= siste) {
				/*
				 * basis: Gjer ingenting Dette er eit triks for å vise at vi har tenkt på
				 * basistilfelle. Kunne i staden utelate dette og teste om første < siste og så
				 * gått rett på delen i else-blokka.
				 */
			} else {
				int midtpkt = (forste + siste) / 2;
				flettesortering(a, tempTab, forste, midtpkt);
				flettesortering(a, tempTab, midtpkt + 1, siste);
				flett(a, tempTab, forste, midtpkt, siste);
			}

		}

		private static <T extends Comparable<? super T>> void flett(T[] a, T[] tempTab, int forste, int midten, int siste) {

			// Flettar saman to deler som ligg ved sida av kvaranre
			// forste, ..., midten og midten + 1, ..., siste
			int fV = forste;
			int sluttV = midten;
			int fH = midten + 1;
			int sluttH = siste;

			// Så lenge det er element att i begge delane, flytt over den minste til tempTab
			int index = fV; // Next available location in tempArray
			for (; (fV <= sluttV) && (fH <= sluttH); index++) {
				if (a[fV].compareTo(a[fH]) < 0) {
					tempTab[index] = a[fV];
					fV++;
				} else {
					tempTab[index] = a[fH];
					fH++;
				}
			}

			// No vil ein av delane vere to. Kopierer over resten i den andre delen

			// Venstre del, kan vere tom
			for (; fV <= sluttV; fV++, index++) {
				tempTab[index] = a[fV];
			}

			// Høgre del, kan vere tom
			for (; fH <= sluttH; fH++, index++) {
				tempTab[index] = a[fH];
			}

			// Kopier den sorterte delen tilbake
			for (index = forste; index <= siste; index++) {
				a[index] = tempTab[index];
			}
		}
	
	
	public static <T extends Comparable<? super T>> void kvikksorter(T[] a) {
		kvikksorter(a, 0, a.length - 1);
		sorterVedInnsetting(a);
	}

	private static final int MIN_GRENSE = 3;

	private static <T extends Comparable<? super T>> void kvikksorter(T[] a, int forste, int siste) {

		if (siste - forste + 1 < MIN_GRENSE) {
			/*
			 * Basistilfelle: Gjer ingenting Boken kaller sortering ved innsetting på
			 * elementa som er att, men det er meir effektivt å kalle soertering ved
			 * innsetting på heile tabellen til slutt. Dette skjer i public-metoden over.
			 */
		} else {
			int delepunkt = partition(a, forste, siste);
			kvikksorter(a, forste, delepunkt - 1);
			kvikksorter(a, delepunkt + 1, siste);

		}
	}

	private static <T extends Comparable<? super T>> int partition(T[] a, int forste, int siste) {
		int midten = (forste + siste) / 2;

		// Ordnar første, midterse og siste element slik at dei står rett i forhold til
		// kvarandre
		sortFirstMiddleLast(a, forste, midten, siste);

		// Flyttar pivot til nest siste plass
		swap(a, midten, siste - 1);
		int pivotIndex = siste - 1;
		T pivotValue = a[pivotIndex];

		// Finn første i venstre del som er mindre større enn pivot
		// siste i høgre del som er mindre enn pivot

		int fraVenstre = forste + 1;
		int fraHogre = siste - 2;

		boolean ferdig = false;
		while (!ferdig) {

			while (a[fraVenstre].compareTo(pivotValue) < 0) {
				fraVenstre++;
			}

			while (a[fraHogre].compareTo(pivotValue) > 0) {
				fraHogre--;
			}

			if (fraVenstre < fraHogre) {
				swap(a, fraVenstre, fraHogre);
				fraVenstre++;
				fraHogre--;
			} else {
				ferdig = true;
			}
		}

		// Place pivotValue between the subarrays Smaller and Larger
		swap(a, pivotIndex, fraVenstre);
		pivotIndex = fraVenstre;

		return pivotIndex;
	}

	// Sorts the first, middle, and last entries of an array into ascending order.
	// Parameters:
	// a An array of Comparable objects.
	// first The integer index of the first array entry;
	// first >= 0 and < a.length.
	// mid The integer index of the middle array entry.
	// last The integer index of the last array entry;
	// last - first >= 2, last < a.length.
	private static <T extends Comparable<? super T>> void sortFirstMiddleLast(T[] a, int first, int mid, int last) {
		order(a, first, mid); // Make a[first] <= a[mid]
		order(a, mid, last); // Make a[mid] <= a[last]
		order(a, first, mid); // Make a[first] <= a[mid]
	} // end sortFirstMiddleLast

	// Orders two given array elements into ascending order so that a[i] <= a[j].
	private static <T extends Comparable<? super T>> void order(T[] a, int i, int j) {
		if (a[i].compareTo(a[j]) > 0) {
			swap(a, i, j);
		}
	}

	public static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static <T extends Comparable<? super T>> void sorterVedInnsetting(T[] a) {
		sorterVedInnsetting(a, 0, a.length - 1);
	}

	public static <T extends Comparable<? super T>> void sorterVedInnsetting(T[] a, int forste, int siste) {

		for (int i = forste + 1; i <= siste; i++) {

			T temp = a[i];
			int j = i - 1;

			// finn rett plass
			while (j >= forste && temp.compareTo(a[j]) < 0) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = temp;
		}

	}
	
		
	public static void utvalgssortering(Integer[] a) {

		for (int i = 0; i < a.length; i++) {

			// finne indeks for minste element i usortert del
			Integer min = a[i];
			int minIndeks = i;

			for (int j = i + 1; j < a.length; j++) {
				if (a[j].compareTo(min) < 0) {
					min = a[j];
					minIndeks = j;
				}
			}

			swap(a, i, minIndeks);
		}
	}
	
	public static void insertionSort(Integer[] a) {
		if(a==null || a.length==0) {
			return;
					
		}

		for(int i =1;i<a.length;i++) {//går igjennom løkken
			int temp=a[i];//tar ut elementet vi skal plassere riktig
			int j =i-1;// Starter sammenligning med forrige element
			while (j>=0 && a[j]>temp) {
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=temp;
			
		}
	}
	
	public static void insertionSort2(Integer[] a) {
	    int n = a.length;
	    
	    //går i gjennomtabellen
	    for (int i = 1; i < n - 1; i += 2) {
	        int min_temp = a[i];//setter min og maks 
	        int maks_temp = a[i + 1];

	        // finner den største og minste verdien 
	        if (min_temp > maks_temp) { //lagrer bare verdien ikkje endrer selve ideks verdien, den gjør det i while løkkene
	            int temp = min_temp;
	            min_temp = maks_temp;
	            maks_temp = temp;
	        }

	        int j = i - 1;//sette verdien til j en indeks mindre enn i 
	        // viss indeksen finner en verdi son er større enn eller lik maks verdien den lagrede maks_tempen
	        while (j >= 0 && a[j] >= maks_temp) {//
	            a[j + 2] = a[j];
	            j--;
	        }
	        a[j + 2] = maks_temp;//setter inn verdien

	        // gjør det samme med min_temp
	        while (j >= 0 && a[j] >= min_temp) {
	            a[j + 1] = a[j];
	            j--;
	        }
	        a[j + 1] = min_temp;//setter inn verdien
	    }


	    //her må vi være forsiktig når det gjeøder selve listen, fordi vi når n er 13 så er den egt 0-12, 
	    //og viss vi hadde brukt for eksempel n % 2 == 0 så hadde vi aktivert if setningn på feil tid altså at partal  blir behandla som oddetal	    
	 
	    if (n % 2 == 0) {
	        int siste = a[n - 1];
	        int j = n - 2;

	        while (j >= 0 && a[j] > siste) {
	            a[j + 1] = a[j];
	            j--;
	        }

	        a[j + 1] = siste;
	    }
	    
	  
	}
	
	 public static double tidsmåler(Integer[][] tabell, String sorteringType) {
	        long start = System.currentTimeMillis();
	        
	        // Velg hvilken sortering som skal brukes
	        for (int i = 0; i < tabell.length; i++) {
	            for (int j = 0; j < tabell[i].length; j++) {
	                if ("insertionSort".equals(sorteringType)) {
	                    insertionSort(tabell[i]);
	                } else if ("insertionSort2".equals(sorteringType)) {
	                	insertionSort2(tabell[i]);
	                }else if ("utvalgssortering".equals(sorteringType)) {
	                    utvalgssortering(tabell[i]);
	                } else if ("Kvikksortering".equals(sorteringType)) {
	                	kvikksorter(tabell[i]);
	                } else if ("Flettesortering".equals(sorteringType)) {
	                	flettesortering(tabell[i]);	                	
	                } else {
	                	System.out.println("du har ikkje valgt en metode");
	                }
	            }
	        }
	        
	        long slutt = System.currentTimeMillis();
	        return (slutt - start) / 1000.0; // Returnerer tiden i sekunder
	    
	 }
	
	
	
	public static void utskriver(Integer[][] tabell, String tabellNavn) {
	    System.out.println("Sortert tabell: " + tabellNavn);
	    for (int i = 0; i < tabell.length; i++) {
	        for (int j = 0; j < tabell[i].length; j++) {
	            System.out.print(tabell[i][j] + " ");
	        }
	        System.out.println();
	    }
	    
	
	
	
}
	
public static void main(String[] args) {
		
		Random tilfeldig = new Random(1000);
		int n = 10; // 32000 var forslag antall tall i hver tabell
		int antall = 3; // antall rader nedover

		Integer[][] a1 = new Integer[antall][n];
		Integer[][] a2 = new Integer[antall][n];
		Integer[][] a3 = new Integer[antall][n];
		Integer[][] a4 = new Integer[antall][n];
		Integer[][] a5 = new Integer[antall][n];
		// set inn tilfeldige heiltal i alle rekker
		for (int i = 0; i < antall; i++) {
			for (int j = 0; j < n; j++) {
				a1[i][j] = tilfeldig.nextInt(1000); // hvis man tar tom parameterliste er random tall fra -int long til
				a2[i][j] = tilfeldig.nextInt(1000);			
				a3[i][j] = tilfeldig.nextInt(1000); // hvis man tar tom parameterliste er random tall fra -int long til
				a4[i][j] = tilfeldig.nextInt(1000); // hvis man tar tom parameterliste er random tall fra -int long til
				
				// +int long.
			} 
		}
		
		// Time each sorting method separately and display the results
	    double tidInnsättning = tidsmåler(a1, "insertionSort");
	    System.out.println("Tid for InsertionSort: " + tidInnsättning + " sekunder");

	    double tidInnsättning2 = tidsmåler(a2, "insertionSort2");
	    System.out.println("Tid for InsertionSort2: " + tidInnsättning2 + " sekunder");

	    double tidUtvalg = tidsmåler(a3, "utvalgssortering");
	    System.out.println("Tid for Utvalgssortering: " + tidUtvalg + " sekunder");

	    double tidKvikksortering = tidsmåler(a4, "kvikksorter");
	    System.out.println("Tid for kvikksorter: " + tidKvikksortering + " sekunder");

	    double tidFlettesortering = tidsmåler(a5, "flettesortering");
	    System.out.println("Tid for flettesortering: " + tidFlettesortering + " sekunder");
		
		

		utskriver(a1, "Tabell a1 - InsertionSort");
	    utskriver(a2, "Tabell a2 - InsertionSort2");
	    utskriver(a3, "Tabell a3 - Utvalgssortering");
	    utskriver(a4, "Tabell a4 - Kvikksortering");
	    utskriver(a5, "Tabell a5 - Flettesortering");
		
		}
		
		
		
}
//	while(j>=0 && a[j].compare(temp)<0)
	

