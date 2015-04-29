package com.cubic.autohome.common.view.image.core.assist;

public class ImageSize {
	private final int height;
	private final int width;
	
	public ImageSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public ImageSize(int width, int height, int degress) {
		if (degress % 180 == 0) {
			this.width = width;
			this.height = height;
		} else {
			this.width = height;
			this.height = width;
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public ImageSize scale(float scale) {
		return new ImageSize((int)(this.width * scale), (int)(this.height * scale));
	}
	
	public ImageSize scaleDown(int divisor) {
		return new ImageSize(this.width / divisor, this.height / divisor);
	}
	
	public String toString() {
		return 9 + this.width + "x" + this.height;
	}
}
