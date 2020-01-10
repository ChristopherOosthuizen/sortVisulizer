package Visualizer;
import java.util.ArrayList;
import java.util.Collection;

public class AnimationList extends ArrayList<Integer> {
	SortAnimation ani;

	public AnimationList() {
		super();
		ani = new SortAnimation(this);
	}

	public AnimationList(Collection<Integer> c) {
		super(c);
		ani = new SortAnimation(this);
	}

	public void show(int width, int height, int delay) throws InterruptedException {
		ani.setSize(width, height);
		ani.show();
		ani.go(delay);

	}

	@Override
	public boolean add(Integer e) {
		super.add(e);
		ani.documentChange(this, this.size() - 1);
		return (true);
	}

	@Override
	public Integer set(int index, Integer e) {
		super.set(index, e);
		ani.documentChange(this, index);
		return e;
	}

	@Override
	public Integer remove(int index) {
		Integer temp = this.get(index);
		super.remove(index);
		ani.documentChange(this, 0);
		return temp;

	}

	public void swap(int a, int b) {
		int temp = this.get(a);
		this.set(a, this.get(b));
		this.set(b, temp);
		int[] change = { a, b };
		ani.documentChange(this, b);
	}

	static class exampleSorts {
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

}
