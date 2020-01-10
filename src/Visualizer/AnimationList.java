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
	public SortAnimation getAnitmation() {
		return ani;
	}

	

}
