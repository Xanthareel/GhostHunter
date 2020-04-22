package com.example.ghosthunter;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class SplashScreen extends Activity {

	MediaPlayer song;

	/**
	 * The thread to process splash screen events
	 */
	private Thread sThread;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		song = MediaPlayer.create(SplashScreen.this, R.raw.song);
		song.start();

		final SplashScreen splash = this;

		sThread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(3000);
					}
				} catch (InterruptedException ex) {
				}

				Intent intent = new Intent();
				intent.setClass(splash, MainActivity.class);
				startActivity(intent);
				finish();

			}
		};

		sThread.start();
	}

	// if (savedInstanceState == null) {
	// getSupportFragmentManager().beginTransaction()
	// .add(R.id.container, new PlaceholderFragment()).commit();
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_splash_screen,
					container, false);
			return rootView;
		}
	}

}
