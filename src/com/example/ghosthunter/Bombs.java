package com.example.ghosthunter;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Bombs {
	private Bitmap bm;
	private int x;
	private int y;
	private Rect rectangle;

	public Bombs(Bitmap bm, int x, int y) {
		this.bm = bm;
		this.x = x;
		this.y = y;
		rectangle = new Rect(x - 25, y + 25, x + 25, y - 25);
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bm, x - (bm.getWidth() / 2),
				y - (bm.getHeight() / 2), null);
	}
}
