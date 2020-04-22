package com.example.ghosthunter;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Repellent {
	private Bitmap bm;
	private int x;
	private int y;
	private Rect rectangle;

	public Repellent(Bitmap bm, int x, int y) {
		this.bm = bm;
		this.x = x;
		this.y = y;
		rectangle = new Rect(x - 20, y + 20, x + 20, y - 20);
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bm, x - (bm.getWidth() / 2),
				y - (bm.getHeight() / 2), null);
	}
}