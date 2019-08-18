package com.gamedev.levels;

import com.gamedev.graphics.Screen;

public class Level {

	protected int width, height;
	protected final int SIZE = 64;
	protected int pixels[];
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		generateLevel();
	}

	protected void generateLevel() {

	}

	public void render(Screen screen) {

	}

	public void update() {

	}

}
