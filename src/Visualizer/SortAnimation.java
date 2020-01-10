package Visualizer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;
import javax.swing.Timer;

public class SortAnimation extends JFrame{
	private ArrayList<Integer> list;
	private ArrayList<ArrayList<Integer>> changes;
	private ArrayList<Integer> changesSpots;
	private int selectedSpot ;
	private Timer timer;
	private AtomicInteger times;
	public void paint(Graphics g) {
		super.paint(g);
		int spaceY = (int)(this.getHeight()*0.99);
		
		int spaceX=(int)(this.getWidth()*0.05);
		int spacersX=this.getWidth()/list.size();
		int oneUnit = ((int)(this.getHeight()-(this.getHeight()*0.07)))/list.stream().max((o1,o2)-> o1-o2).get();
		
		for (int i = 0; i < list.size();i++) {
			
			int size = list.get(i)*oneUnit*(int)(this.getHeight()*0.003);
			if(i != selectedSpot)
			g.drawRect((spaceX), spaceY-size, spacersX, size);
			else
			g.fillRect((spaceX), spaceY-size, spacersX, size);
			spaceX +=spacersX;
		}
		if(times.get() == changes.size()-1) {
			timer.stop();
			
		}
	}
	public SortAnimation(AnimationList list) {
		this.selectedSpot=0;
		this.list = new ArrayList<Integer>(list);
		this.changes = new ArrayList<ArrayList<Integer>>();
		this.changesSpots = new ArrayList<Integer>();
	}
	public void go(int delay) throws InterruptedException {
		
		times = new AtomicInteger();
			
			timer = new Timer(delay,new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					list = changes.get(times.get());
					selectedSpot = changesSpots.get(times.get());
					repaint();
					times.incrementAndGet();
				}
			});
			timer.start();
		
	}
	private void swap(int[] a) {
		int temp = (int) list.get(a[0]);
		list.set(a[0], list.get(a[1]));
		list.set(a[1], temp);
	}
	public void documentChange(AnimationList lister,int selectedSpot) {
		changes.add(new ArrayList(lister));
		changesSpots.add(selectedSpot);
		
	}
	
}
