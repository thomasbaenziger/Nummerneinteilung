public class StoreMatchingNumbers {

	public static int[][] storeMatchingNumbers(int[][] array, int matches, int i, int j, int arraylenght) {
		// Loop the array and compare to the matches
		for(int k = 0; k < (arraylenght); k++) {
			if(matches > array[k][0]) {
				// create a temp array to store the current values
				int[][] tempArray = new int[arraylenght][3];
				
				for (int m = 0; m < array.length; m++) {
				    for (int l = 0; l < array[k].length; l++) {
				    	tempArray[m][l] = array[m][l];
				    }
				}
				
				// Store the matching numbers to the array
				array[k][0] = matches;
				//Store the row and column
				array[k][1] = i;
				array[k][2] = j;		
				
				// Move the other Values down
				for( int n = k; n < (arraylenght - 1); n++ ) {
				
					// Move the current number one down
					array[n + 1] = tempArray[n];
				}
			break;
			}
		}
		return array;
	}
}

