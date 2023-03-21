public class CountMatches {

	public static int countMatches(int[] array1, int[] array2) {
		int count = 0;

		for (int value : array1) {
			for (int i = 0; i < array2.length; i++) {
				if (array2[i] == value) {
					count++;
				}
			}
		}

		return count;
	}
}

