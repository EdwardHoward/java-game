package com.edward.game.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GFX {
	private BufferedImage image;
	private int[] pixels;
	
	public GFX(int width, int height) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public void setPixel(int x, int y, int r, int g, int b) {
		int color = 0xff << 24 | r << 16 | g << 8 | b;
		pixels[x + y * image.getWidth()] = color;
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
