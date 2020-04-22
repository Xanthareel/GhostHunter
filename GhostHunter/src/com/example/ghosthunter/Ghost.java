package com.example.ghosthunter;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Ghost {

	private Bitmap bm;
	private int x;
	private int y;
	private Rect rectangle;
	private Velocity speed;
	private Random m;
	private int t = 0;

	public Ghost(Bitmap bm, int x, int y) {
		this.bm = bm;
		this.x = x;
		this.y = y;
		rectangle = new Rect(x - 25, y - 25, x + 25, y + 25);
		speed = new Velocity(20, 20);
		m = new Random();
	}

	public Bitmap getBm() {
		return bm;
	}

	public void setBm(Bitmap bm) {
		this.bm = bm;
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

	public Rect getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rect rectangle) {
		this.rectangle = rectangle;
	}

	public Velocity getSpeed() {
		return speed;
	}

	public void setSpeed(Velocity speed) {
		this.speed = speed;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bm, x - (bm.getWidth() / 2),
				y - (bm.getHeight() / 2), null);
	}

	public void move() {
		x += m.nextInt(10);
		y += m.nextInt(10);
		x -= m.nextInt(10);
		y -= m.nextInt(10);
	}

	public void repel(GH gh) {
		while (t < 10) {
			if (gh.getX() > this.x) {
				x -= 20;
			} else if (gh.getX() < this.x) {
				x += 20;
			}
			if (gh.getY() > this.y) {
				y -= 20;
			} else if (gh.getY() < this.y) {
				y += 20;
			}
			t++;
		}
	}
}
