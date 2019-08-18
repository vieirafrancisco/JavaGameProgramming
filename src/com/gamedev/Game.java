package com.gamedev;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.gamedev.graphics.Screen;
import com.gamedev.levels.Level;
import com.gamedev.levels.RandomLevel;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 300;
	private static final int HEIGHT = WIDTH / 16 * 9;
	private static final int SCALE = 3;
	private boolean running;
	private String title = "Game";

	private static final Game game = new Game();
	private Thread thread;

	private JFrame frame;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int pixels[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	private Level level;
	
	private Game() {

		this.frame = new JFrame();
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		
		setPreferredSize(size);
		setFocusable(true);

		this.screen = new Screen(WIDTH, HEIGHT);
		this.level = new RandomLevel(WIDTH, HEIGHT);
	}

	public static Game getInstance() {
		return game;
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

		while (running) {
			render();
			update();
		}
		stop();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		screen.render();
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public void update() {

	}

	public static void main(String[] args) {
		Game g = Game.getInstance();
		g.frame.setTitle(g.title);
		g.frame.setResizable(false);
		g.frame.add(g);
		g.frame.pack();
		g.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.frame.setLocationRelativeTo(null);
		g.frame.setVisible(true);
		g.start();
	}

}
