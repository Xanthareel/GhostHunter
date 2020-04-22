package com.example.ghosthunter;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Killer {
	private Bitmap bm;
	private int x;
	private int y;
	private Rect rectangle;

	public Rect getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rect rectangle) {
		this.rectangle = rectangle;
	}

	public Killer(Bitmap bm, int x, int y) {
		this.bm = bm;
		this.x = x;
		this.y = y;
		rectangle = new Rect(x - 15, y - 15, x + 15, y + 15);
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bm, x - (bm.getWidth() / 2),
				y - (bm.getHeight() / 2), null);
	}
}