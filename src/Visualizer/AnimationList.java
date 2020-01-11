package Visualizer;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Timer;

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

	public void play(int delay) throws InterruptedException {

		ani.documentChange(this, -1);
		ani.play(delay);

	}
	public boolean isRunning() {
		return ani.isRewindRunning()||ani.isPlayRunning();
	}
	public boolean isPlaying() {
		return ani.isPlayRunning();
	}
	public boolean isRewindRunning() {
		return ani.isRewindRunning();
	}

	public void rewind(int delay) throws InterruptedException {

		ani.documentChange(this, this.size());
		ani.rewind(delay);

	}

	@Override
	public Integer get(int index) {
		// ani.documentChange(this, index);
		return super.get(index);
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

	public SortAnimation getAnimation() {
		return ani;
	}

	public void setBounds(int x, int y, int width, int height) {
		ani.setBounds(x, y, width, height);
	}

	public void pause() {
		ani.pause();
	}

	public void frameUp() {
		ani.frameUp();
	}

	public void frameDown() {

	}

}
