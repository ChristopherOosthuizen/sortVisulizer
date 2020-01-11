package Visualizer;

import java.util.Collections;

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
	public static void bubbleSort(AnimationList list) {
		int swaps =1;
		for(int ii=0;ii<list.size()-1;ii++) {
			swaps=0;
			for(int i=0;i< list.size()-ii-1;i++) {
				if(list.get(i) > list.get(i+1)) {
					list.swap(i+1, i);
					swaps++;
				}
					
			}
		}
			
		}
	
	
	//please don't O((n+1)!)
	public static void BogoSort(AnimationList list) {
		boolean isSorted =false;
		while(!isSorted) {
		Collections.shuffle(list);
		isSorted = true;
		
		for(int i=1; i< list.size();i++) {
			if(list.get(i) <list.get(i-1)) {
				isSorted=false;
				break;
			}
		}
		}
	}
}
