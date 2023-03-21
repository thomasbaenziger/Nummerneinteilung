import java.util.Arrays;
import java.util.List;

public class ResultPrinter {

	public static void resultPrinter(List<String> headerlistNLB, 
									List<String> headerlist1L, 
									int[][] dataarrayNLB, 
									int[][] dataarray1L, 
									int[] matchesarray,
									String priority ) {
		
		System.out.println("The two arrays that match on the " + priority + " values are:");
		System.out.println(headerlistNLB.get(matchesarray[1]) + " NLB und " + headerlist1L.get(matchesarray[2]) + " 1L" + 
				"\n" + Arrays.toString(dataarrayNLB[matchesarray[1]]) + 
				"\n" + Arrays.toString(dataarray1L[matchesarray[2]]));
		System.out.println("Matching on " + matchesarray[0] + " values.");
		System.out.println("\n" + "\n");
	}
}

