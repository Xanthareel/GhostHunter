package com.example.ghosthunter;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

	// flag to hold game state
	private boolean running;
	private SurfaceHolder sh;
	private MainGame mg;
	
	public GameLoop(SurfaceHolder sh, MainGame mg) {
		super();
		this.sh = sh;
		this.mg = mg;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		Canvas canvas;
		while (running) {
			canvas = null;
			try {
				canvas = this.sh.lockCanvas();
				synchronized (sh) {
					this.mg.update();
					this.mg.onDraw(canvas);
					
				}
			} finally {
				if(canvas != null) {
					sh.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
}
