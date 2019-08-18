package com.gamedev.levels;

import java.util.Random;

import com.gamedev.graphics.Screen;

public class RandomLevel extends Level {

	private int[] tiles;

	private static Random random = new Random();

	public RandomLevel(int width, int height) {
		super(width, height);

		tiles = new int[SIZE * SIZE];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = 0xff00ff;
				//pixels[x + y * width] = tiles[((x >> 16) & 15) + ((y >> 16) & 15) * SIZE];
			}
		}
	}
	
	public void render(Screen screen) {
		
		for(int i = 0; i < screen.pixels.length; i++) {
			screen.pixels[i] = pixels[i];
			System.out.println(pixels[i]);
		}
	}

}
