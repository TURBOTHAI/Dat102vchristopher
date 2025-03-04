package quicksort;

public class quicsort_int {
	
	public static void main(String[]args) {
		int[] array= {8,2,5,3,9,4,7,6,5};
		
		quickSort(array,0,array.length-1);//navnet på tabellen, start pos, slutt pos
		
		
		for (int i : array) {
			System.out.print(i+" ");
		}
	}
	
	private static void quickSort(int[] array, int start, int slutt) {
		
		if(slutt<=start) return; //base case
		
		int pivot = partition(array,start,slutt);
		quickSort(array,start,pivot-1);// sorterer venstre side iforhold til pivot
		quickSort(array,pivot+1, slutt); //sorterer høgre side til pivot
		
		
		
		
	}
	
	
	private static int partition(int[] array, int start, int slutt) {
		
		int pivot=array[slutt];//pivot starter på slutten
		int i = start-1;
		
		for(int j=start;j!=slutt;j++) {
			if(array[j]<pivot) {//viss j er mindre enn pivot på siste pos
				i++;//øker inkrement
				int temp= array[i];//lagrer verdien til verdien som er større en pivot
				array[i]=array[j];//verdien som var større en pivot får verdien til array[j]
				array[j]=temp;//verdien som var lavere en piv får verdien som er høgere en piv
				
			}
			
		}
			i++;//øker inkrement
			int temp= array[i];//lagrer verdien til verdien som er større en pivot
			array[i]=array[slutt];//bytter verdien som er større med pivot, pga pivot er mindre 
			array[slutt]=temp;//henter verdien til den lagrede verdien til temp og plasserer den på slutten pga den er større en pivot
			
		
		return i;
		
	}
	
}


