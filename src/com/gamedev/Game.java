package com.gamedev;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final int WIDTH = 400;
	private static final int HEIGHT = WIDTH / 16 * 9;
	private static final int scale = 3;
	private static String title;
	private boolean running;
	
	private static final Game g = new Game();
	private Thread thread;
	
	private JFrame frame;
	
	private Game() {
	
		this.frame = new JFrame();
	
	}
	
	public static Game getInstance() {
		return g;
	}
	
	public synchronized void start() {
		this.running = true;
		this.thread = new Thread(this, "display");
		this.thread.start();
	}
	
	public synchronized void stop() {
		this.running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		while(running) {
			System.out.println("Playing..");
		}
		stop();
	}
	
	public static void main(String[] args) {
		Game g = Game.getInstance();
		g.frame.setTitle(g.title);
		g.frame.setResizable(false);
		g.frame.add(g);
		g.frame.pack();
		g.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.frame.setVisible(true);
		g.start();
	}
	
}
