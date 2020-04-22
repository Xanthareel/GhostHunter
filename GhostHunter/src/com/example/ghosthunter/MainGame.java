package com.example.ghosthunter;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGame extends SurfaceView implements SurfaceHolder.Callback {

	private GameLoop gL;
	private GH gH;
	private ArrayList<Ghost> ghosts;
	private Ghost ghost;
	private Bombs bomb;
	protected int time = 0;
	private int cash = 4000;
	private Random q;
	private Money m;
	private ArrayList<Money> monay;
	private MediaPlayer play;
	private boolean rep = false;
	private Repellent repell;
	private boolean killer = false;
	private Killer k;

	public MainGame(Context context) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);
		q = new Random();
		gL = new GameLoop(getHolder(), this);
		gH = new GH(BitmapFactory.decodeResource(getResources(),
				R.drawable.ghosty), 200, 200);
		ghosts = new ArrayList<Ghost>();
		bomb = new Bombs(BitmapFactory.decodeResource(getResources(),
				R.drawable.bomb), 450, 25);
		repell = new Repellent(BitmapFactory.decodeResource(getResources(),
				R.drawable.repel), 620, 25);
		monay = new ArrayList<Money>();
		play = MediaPlayer.create(context, R.raw.mainsong);

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gL.setRunning(true);
		gL.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean game = true;
		while (game) {
			try {
				gL.join();
				game = false;
			} catch (InterruptedException e) {

			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (event.getX() <= 450 && event.getX() >= 400
					&& event.getY() <= 50 && event.getY() >= 0) {
				bombMethod();
			} else if (event.getX() <= 640 && event.getX() >= 600
					&& event.getY() <= 40 && event.getY() >= 0) {
				repellent();
				rep = true;
			}
			gH.handleActionDown((int) event.getX(), (int) event.getY());
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (gH.isTouched()) {
				gH.setX((int) event.getX());
				gH.setY((int) event.getY());
			}
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (gH.isTouched()) {
				gH.setTouched(false);
				gH.setSpeed(new Velocity(0, 0));
			}
			rep = false;
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		Paint myPaint = new Paint();
		myPaint.setColor(Color.CYAN);
		add();
		intersectStuff();
		bomb.draw(canvas);
		repell.draw(canvas);
//		if(time > 1000)
//		k.draw(canvas);
		gH.draw(canvas);
		for (Ghost g : ghosts) {
			if (!rep)
				updateMove(g);
			else
				repellent();
			g.draw(canvas);
		}
		for (Money m : monay) {
			m.draw(canvas);
		}
		myPaint.setTextSize(15);
		canvas.drawText("Score: " + (int) (time * 0.8), 10, 20, myPaint);
		canvas.drawText("Cash: " + (int) (cash * 0.2), 10, 40, myPaint);
	}

	public void update() {
		time++;
		cash++;
		if (gH.getSpeed().getxDir() == Velocity.Right
				&& gH.getX() + gH.getBm().getWidth() / 2 >= getWidth()) {
			gH.getSpeed().changeXDir();
		}
		if (gH.getSpeed().getxDir() == Velocity.Left
				&& gH.getX() - gH.getBm().getWidth() / 2 <= 0) {
			gH.getSpeed().changeXDir();
		}
		if (gH.getSpeed().getyDir() == Velocity.Down
				&& gH.getY() + gH.getBm().getHeight() / 2 >= getHeight()) {
			gH.getSpeed().changeYDir();
		}
		if (gH.getSpeed().getyDir() == Velocity.Up
				&& gH.getY() - gH.getBm().getHeight() / 2 <= 0) {
			gH.getSpeed().changeYDir();
		}

		playAudio();

	}

	public void updateMove(Ghost r) {
		gH.setRectangle(new Rect(gH.getX() - 10, gH.getY() - 10,
				gH.getX() + 10, gH.getY() + 10));
		r.move();
		r.setRectangle(new Rect(r.getX() - 25, r.getY() - 25, r.getX() + 25, r
				.getY() + 25));

	}

	public void add() {
		if (time < 500) {
			if (time % 100 == 0) {
				Ghost scary = new Ghost(BitmapFactory.decodeResource(
						getResources(), R.drawable.ghost1),
						q.nextInt(getWidth()), q.nextInt(getHeight()));
				ghosts.add(scary);
			}
		} else if (time < 1500 && time > 500) {
			if (time % 80 == 0) {
				Ghost scary = new Ghost(BitmapFactory.decodeResource(
						getResources(), R.drawable.ghost1),
						q.nextInt(getWidth()), q.nextInt(getHeight()));
				ghosts.add(scary);
			}
		} else if (time > 1500) {
			if (time % 50 == 0) {
				Ghost scary = new Ghost(BitmapFactory.decodeResource(
						getResources(), R.drawable.ghost1),
						q.nextInt(getWidth()), q.nextInt(getHeight()));
				ghosts.add(scary);
			}
		}
		if (time % 1000 == 0) {
			Money m = new Money(BitmapFactory.decodeResource(getResources(),
					R.drawable.coin), q.nextInt(getWidth()),
					q.nextInt(getHeight()), q.nextInt(100));
			monay.add(m);
		}
//		if (time == 1000) {
//			k = new Killer((BitmapFactory.decodeResource(getResources(),
//					R.drawable.scissors)), q.nextInt(getWidth()),
//					q.nextInt(getHeight()));
//		}
	}

	public void intersectStuff() {
//		if (killer) {
			for (int i = 0; i < ghosts.size(); i++) {
				if (Rect.intersects(gH.getRectangle(), ghosts.get(i)
						.getRectangle())) {
					Money n = new Money(BitmapFactory.decodeResource(
							getResources(), R.drawable.coin), ghosts.get(i)
							.getX(), ghosts.get(i).getY(), q.nextInt(100));
					ghosts.remove(i);
					monay.add(n);

				}
			}
//		} else {
//			for (int i = 0; i < ghosts.size(); i++) {
//				if (Rect.intersects(gH.getRectangle(), ghosts.get(i)
//						.getRectangle())) {
//					gL.setRunning(false);
//
//				}
//			}
			for (int i = 0; i < monay.size(); i++) {
				if (Rect.intersects(gH.getRectangle(), monay.get(i).getR())) {
					cash += monay.get(i).getValue();
					monay.remove(i);
				}

			}
			
//			if(time > 1000) {
//			if (Rect.intersects(gH.getRectangle(), k.getRectangle()))
//				killer = true;
//			}
		}
	

	public void bombMethod() {
		if (cash >= 2000) {
			cash -= 2000;
			// for(int i=0; i<ghosts.size(); i++) {
			// ghosts.get(i).setBm(null);
			// ghosts.remove(i);
			// }
			ghosts.clear();
		}
	}

	public void repellent() {
		if (cash >= 500) {
			cash -= 500;
			for (Ghost h : ghosts) {
				h.repel(gH);
				h.setRectangle(new Rect(h.getX() - 25, h.getY() + 25,
						h.getX() + 25, h.getY() - 25));
			}
		}
	}

	public void playAudio() {
		play.setLooping(true);
		play.start();
	}

}