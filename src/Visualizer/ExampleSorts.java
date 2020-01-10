package Visualizer;

public class ExampleSorts {
	public static void insetionSort(AnimationList list) {
		for (int i = 1; i < list.size(); i++) {
			int temp = list.get(i);
			int index = i;
			while (index > 0 && temp < list.get(index - 1)) {
				list.set(index, list.get(index - 1));
				index--;
			}
			list.set(index, temp);
		}
	}

	public static void selectionSort(AnimationList list) {
		for (int i = 0; i < list.size() - 1; i++) {
			int index = i;
			for (int ii = i + 1; ii < list.size(); ii++) {
				if (list.get(ii) < list.get(index))
					index = ii;
			}
			int temp = list.get(i);
			list.set(i, list.get(index));
			list.set(index, temp);
		}
	}

	public static void mergeSort(AnimationList list) {
	}
}
