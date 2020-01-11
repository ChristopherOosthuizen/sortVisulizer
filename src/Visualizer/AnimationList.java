package Visualizer;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationList extends ArrayList<Integer> {
	SortAnimation animation;

	public AnimationList() {
		super();
		animation = new SortAnimation(this);
	}

	public AnimationList(Collection<Integer> c) {
		super(c);
		animation = new SortAnimation(this);
	}
	
	
	//reintroduced basic arrayList methods with documentChanges
	@Override
	public Integer get(int index) {
		animation.documentChange(this, index);
		return super.get(index);
	}

	@Override
	public boolean add(Integer e) {
		super.add(e);
		animation.documentChange(this, this.size() - 1);
		return (true);
	}

	@Override
	public Integer set(int index, Integer e) {
		super.set(index, e);
		animation.documentChange(this, index);
		return e;
	}

	@Override
	public Integer remove(int index) {
		Integer temp = this.get(index);
		super.remove(index);
		animation.documentChange(this, 0);
		return temp;

	}

	
	
	public void swap(int a, int b) {
		int temp = this.get(a);
		this.set(a, this.get(b));
		this.set(b, temp);
		
		animation.documentChange(this, b);
	}

	
	
	public SortAnimation getAnimation() {
		return animation;
	}

	public void setBounds(int x, int y, int width, int height) {
		animation.setBounds(x, y, width, height);
	}
	//tests if the play forward or rewind is on
	public boolean isRunning() {
		return animation.isRewindRunning() || animation.isPlayRunning();
	}
	//returns is is playing forward
	public boolean isPlaying() {
		return animation.isPlayRunning();
	}
	//checks if rewind is playing
	public boolean isRewindRunning() {
		return animation.isRewindRunning();
	}

	public void play(int delay) throws InterruptedException {

		animation.documentChange(this, -1);
		animation.play(delay);

	}

	public void rewind(int delay) throws InterruptedException {

		animation.documentChange(this, this.size());
		animation.rewind(delay);

	}

	public void pause() {
		animation.pause();
	}

	public void frameUp() {
		animation.frameUp();
	}

	public void frameDown() {
		animation.frameDown();
	}
		//main animation class
		private class SortAnimation extends JPanel {

			private ArrayList<Integer> list;
			private ArrayList<ArrayList<Integer>> changes;
			private ArrayList<Integer> changesSpots;
			private int selectedSpot;
			private Timer forwardTimer;
			private Timer backwardTimer;
			private AtomicInteger times;

			public void paint(Graphics g) {
				super.paint(g);
				int height = this.getHeight();

				int spaceY = (int) (this.getHeight());

				int spaceX = (int) ((this.getWidth()) - spaceY) / (list.size() - 1);
				int spacersX = this.getWidth() / list.size();
				int oneUnit = 1 + ((int) (height - (height * 0.07))) / list.stream().max((o1, o2) -> o1 - o2).get();

				for (int i = 0; i < list.size(); i++) {

					int size = list.get(i) * oneUnit * (int) (height * 0.003);
					if (i != selectedSpot)
						g.drawRect((spaceX) + i * spacersX, spaceY - size, spacersX, size);
					else
						g.fillRect((spaceX) + i * spacersX, spaceY - size, spacersX, size);

				}

			}

			public SortAnimation(AnimationList list) {
				this.selectedSpot = 0;

				times = new AtomicInteger();
				this.list = new ArrayList<Integer>(list);
				this.changes = new ArrayList<ArrayList<Integer>>();
				this.changesSpots = new ArrayList<Integer>();

			}

			public synchronized void play(int delay) throws InterruptedException {

				forwardTimer = new Timer(delay, new ActionListener() {

					@Override

					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (forwardTimer != null || !backwardTimer.isRunning()) {
							if (times.get() == changes.size()) {
								forwardTimer.stop();

							} else
								frameUp();
						}

					}
				});
				forwardTimer.start();

			}

			public synchronized void rewind(int delay) {

				backwardTimer = new Timer(delay, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						if (backwardTimer != null || !forwardTimer.isRunning()) {
							if (times.get() == 0) {
								backwardTimer.stop();

							} else
								frameDown();
						}
					}
				});

				backwardTimer.start();

			}

			public void pause() {
				backwardTimer.stop();
				forwardTimer.stop();

			}

			public void start() {
				forwardTimer.start();
			}

			public void frameUp() {
				list = changes.get(times.get());
				selectedSpot = changesSpots.get(times.get());
				repaint();
				times.incrementAndGet();

			}

			public void frameDown() {
				list = changes.get(times.get());
				selectedSpot = changesSpots.get(times.get());
				repaint();
				times.decrementAndGet();
			}

			

			public boolean isPlayRunning() {
				return forwardTimer.isRunning();
			}

			public boolean isRewindRunning() {
				return backwardTimer.isRunning();
			}


			public void documentChange(AnimationList lister, int selectedSpot) {
				changes.add(new ArrayList<Integer>(lister));
				changesSpots.add(selectedSpot);

			}
		}
	}

