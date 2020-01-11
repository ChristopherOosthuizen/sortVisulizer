package Visualizer;

import java.util.ArrayList;

import javax.swing.JFrame;

public class main {
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame();
		frame.setSize( 500, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		ArrayList<Integer> data = new ArrayList<Integer>();
		int i=100;
		while(i-->0)
			data.add((int)(Math.random()*100));
		AnimationList list = new AnimationList(data);
		ExampleSorts.insetionSort(list);
		frame.add(list.getAnimation());
		list.setBounds(0, 0, 400, 400);
		list.play(10);
	}
}
