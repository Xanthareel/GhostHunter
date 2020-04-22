package com.example.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Money {

	private int x;
	private int y;
	private int value;
	private Bitmap q;
	private Rect r;
	
	public Bitmap getQ() {
		return q;
	}

	public void setQ(Bitmap q) {
		this.q = q;
	}

	public Money(Bitmap q, int x, int y, int value) {
		this.q = q;
		this.x = x;
		this.y = y;
		this.value = value;
		r = new Rect(x-15, y-15, x+15, y+15);
	}

	public Rect getR() {
		return r;
	}

	public void setR(Rect r) {
		this.r = r;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(q, x - (q.getWidth() / 2),
				y - (q.getHeight() / 2), null);
	}
}