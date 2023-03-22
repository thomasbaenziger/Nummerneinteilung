import java.util.Arrays;
import java.util.List;

public class ResultPrinter {

	public static void resultPrinter(List<String> headerlistNLB, 
									List<String> headerlist1L, 
									int[][] dataarrayNLB, 
									int[][] dataarray1L, 
									int[][] matchesarray ) {
		for( int i = 0; i < matchesarray.length; i++) {
			System.out.println("The two arrays that match on the " + i + ". values are:");
			System.out.println(headerlistNLB.get(matchesarray[i][1]) + " NLB und " + headerlist1L.get(matchesarray[i][2]) + " 1L" + 
					"\n" + Arrays.toString(dataarrayNLB[matchesarray[i][1]]) + 
					"\n" + Arrays.toString(dataarray1L[matchesarray[i][2]]));
			System.out.println("Matching on " + matchesarray[i][0] + " values.");
			System.out.println("\n" + "\n");		
		}

	}
}

