package com.example.ghosthunter;

public class Velocity {
	
	public static final int Right = 1;
	public static final int Left = -1;
	public static final int Up = -1;
	public static final int Down = 1;
	
	private float xvel = 1;
	private float yvel = 1;
	
	private int xDir = Right;
	private int yDir = Down;
	
	public Velocity() {
		this.xvel = 1;
		this.yvel = 1;
	}
	
	public Velocity(float xvel, float yvel) {
		this.xvel = xvel;
		this.yvel = yvel;
	}

	public float getXvel() {
		return xvel;
	}

	public void setXvel(float xvel) {
		this.xvel = xvel;
	}

	public float getYvel() {
		return yvel;
	}

	public void setYvel(float yvel) {
		this.yvel = yvel;
	}

	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}
	
	public void changeXDir() {
		xDir = xDir * -1;
	}
	
	public void changeYDir() {
		yDir = yDir * -1;
	}
}
