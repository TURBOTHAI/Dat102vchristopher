package oppgave2;

import java.util.Random;

public class sorterings_tid {
	
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
		
	
	public static void main(String[] args) {
	
			
		Random tilfeldig = new Random(1000);
		int n = 12; // 32000 var forslag antall tall i hver tabell
		int antall = 50; // antall rader nedover

		Integer[][] a1 = new Integer[antall][n];
		// set inn tilfeldige heiltal i alle rekker
		for (int i = 0; i < antall; i++) {
			for (int j = 0; j < n; j++) {
				a1[i][j] = tilfeldig.nextInt(1000); // hvis man tar tom parameterliste er random tall fra -int long til
													// +int long.
			}
		}
		
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a1[i].length; j++) {
//				sorterTab(a1[i], a1[i].length); // a[i] blir ein eindimensjonal tabell
				insertionSort(a1[i]);
			}
		}
		long slutt = System.currentTimeMillis();
		// slutt tidsmåling
		double tid = (slutt - start) / 1000;

		System.out.println("Det tok: " + tid + " sekunder");

		System.out.println("Sortert tabell: ");
		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a1[i].length; j++) {
				System.out.print(a1[i][j] + " ");
			}
			System.out.println();
		}
 }

}
