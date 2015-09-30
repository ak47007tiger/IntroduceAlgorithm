package section7_quicksort;

public class SimpleQuickSort {
	int[] array;

	public void setInputArray(int[] array) {
		this.array = array;
	}

	public void quickSortUp(int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int q = pointUp(startIndex, endIndex);
			quickSortUp(startIndex, q - 1);
			quickSortUp(q + 1, endIndex);
		}
	}

	int pointUp(int startIndex, int endIndex) {
		int x = array[endIndex];
		int i = startIndex - 1;
		for (int j = startIndex; j < endIndex; j++) {
			if (array[j] <= x) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		int temp = array[endIndex];
		array[endIndex] = array[i + 1];
		array[i + 1] = temp;
		return i + 1;
	}

	public void quickSortDown(int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int q = pointDown(startIndex, endIndex);
			quickSortDown(startIndex, q - 1);
			quickSortDown(q + 1, endIndex);
		}
	}

	int pointDown(int startIndex, int endIndex) {
		int x = array[endIndex];
		int i = startIndex - 1;
		for (int j = startIndex; j < endIndex; j++) {
			if (array[j] >= x) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		int temp = array[endIndex];
		array[endIndex] = array[i + 1];
		array[i + 1] = temp;
		return i + 1;
	}

	public void printArray() {
		for (int v : array) {
			System.out.print(v + " ");
		}
		System.out.println();
	}

	public void printArray(int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] array = new int[] { 2, 4, 88, 7, 11, 9,9, 23, 34, 65, 18 };
		SimpleQuickSort simpleQuickSort = new SimpleQuickSort();
		simpleQuickSort.setInputArray(array);
		simpleQuickSort.quickSortUp(0, array.length - 1);
		simpleQuickSort.printArray();
	}
}
