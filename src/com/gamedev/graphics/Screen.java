package com.gamedev.graphics;

import java.util.Random;

public class Screen {

	private int width, height;
	private final int SIZE = 64;
	public int[] pixels;

	private Random random = new Random();

	public int[] tiles = new int[SIZE * SIZE];

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];

		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {

		for (int y = 0; y < height; y++) {
			if (y < 0 || y >= height) break;
			for (int x = 0; x < width; x++) {
				if (x < 0 || x >= width) break;
				pixels[x + y * width] = tiles[(x >> 4) + (y >> 4) * SIZE];
			}
		}

	}

}
