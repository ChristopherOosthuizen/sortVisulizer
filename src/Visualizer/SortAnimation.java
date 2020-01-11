package Visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SortAnimation extends JPanel {
	private boolean isFirst;
	private ArrayList<Integer> list;
	private ArrayList<ArrayList<Integer>> changes;
	private ArrayList<Integer> changesSpots;
	private int selectedSpot;
	private Timer forwardTimer;
	private Timer backwardTimer;
	private AtomicInteger times;

	public void paint(Graphics g) {
		super.paint(g);
		int height =this.getHeight();
		int width =this.getWidth();
		int spaceY = (int) (this.getHeight() * 0.99);
		
		
		int spaceX = (int) ((this.getWidth())-spaceY)/(list.size());
		int spacersX = this.getWidth() / list.size();
		int oneUnit = 1+ ((int) (height- (height * 0.07))) / list.stream().max((o1, o2) -> o1 - o2).get();
		
		for (int i = 0; i < list.size(); i++) {

			int size = list.get(i) * oneUnit * (int) (height * 0.003);
			if (i != selectedSpot)
				g.drawRect((spaceX)+i*spacersX, spaceY - size, spacersX, size);
			else
				g.fillRect((spaceX)+i*spacersX, spaceY - size, spacersX, size);
			
			
		
		}
		
		
	}

	public SortAnimation(AnimationList list) {
		this.selectedSpot = 0;
		this.isFirst =true;
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
				if(forwardTimer != null||!backwardTimer.isRunning()) {
				if (times.get() == changes.size() ) {
					forwardTimer.stop();
					
				}
				else
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
				
				if(backwardTimer !=null||!forwardTimer.isRunning()) {
				if (times.get() == 0) {
					backwardTimer.stop();

				}else
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
		backwardTimer.start();
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

	private void swap(int[] a) {
		int temp = (int) list.get(a[0]);
		list.set(a[0], list.get(a[1]));
		list.set(a[1], temp);
	}
	public boolean isPlayRunning() {
		return forwardTimer.isRunning();
	}
	
	public boolean isRewindRunning() {
		return backwardTimer.isRunning();
	}
	public ArrayList<Integer> changes(int change) {
		ArrayList<Integer> changers = new ArrayList<Integer>();
		ArrayList<Integer> setOne = changes.get(change);
		ArrayList<Integer> setTwo = changes.get(change-1);
		for(int i=0; i< changes.get(change).size();i++) {
			if(!setOne.get(i).equals(setTwo.get(i)))
				changers.add(i);
		}
		return changers;
		
	}
	public void documentChange(AnimationList lister, int selectedSpot) {
		changes.add(new ArrayList(lister));
		changesSpots.add(selectedSpot);

	}
}
