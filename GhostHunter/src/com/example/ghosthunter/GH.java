package com.example.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class GH {
	
	private Bitmap bm;
	private int x;
	private int y;
	private Rect rectangle;
	private boolean touched;
	private Velocity speed;
	
	public GH(Bitmap bm, int x, int y) {
		this.bm = bm;
		this.x = x;
		this.y = y;
		rectangle = new Rect(x - 10, y - 10, x + 10, y + 10);
		this.speed = new Velocity(0, 0);
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
	
	public boolean isTouched() {
		return touched;
	}
	
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public Velocity getSpeed() {
		return speed;
	}

	public void setSpeed(Velocity speed) {
		this.speed = speed;
	}
	
	 public void draw(Canvas canvas) {
		  canvas.drawBitmap(bm, x - (bm.getWidth() / 2), y - (bm.getHeight() / 2), null);
		 }
	
	public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (x - bm.getWidth() / 2) && (eventX <= (x + bm.getWidth()/2))) {
			   if (eventY >= (y - bm.getHeight() / 2) && (y <= (y + bm.getHeight() / 2))) {
			    setTouched(true);
			   } else {
			    setTouched(false);
			   }
			  } else {
			   setTouched(false);
			  }
	}
	
	public void update() {
		if(!touched) {
			x += speed.getXvel() * speed.getxDir();
			y += speed.getYvel() * speed.getyDir();
		}
	}
	

}
