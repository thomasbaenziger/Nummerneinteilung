public class StoreMatchingNumbers {

	public static int[][] storeMatchingNumbers(int[][] array, int prio, int matches, int i, int j) {
		int[][] tempArray = new int[5][5];
		for (int k = 0; k < array.length; k++) {
		    for (int l= 0; l < array[k].length; l++) {
		    	tempArray[k][l] = array[k][l];
		    }
		}
		
		// Store the matching numbers to the array
		array[prio - 1][0] = matches;
		//Store the row and column
		array[prio - 1][1] = i;
		array[prio - 1][2] = j;		
		
		// Move the other Values down
		for( int m = prio; m < (array.length - prio); m++ ) {
		
			// Move the current number one down
			array[m] = tempArray[m-1];
		}
		
		return array;
	}
}

