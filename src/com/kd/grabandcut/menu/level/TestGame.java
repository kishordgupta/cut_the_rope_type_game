package com.kd.grabandcut.menu.level;

import java.util.LinkedList;

import com.kd.grabandcut.menu.LevelScreen;
import com.kd.grabandcut.menu.MainScreen;
import com.kd.grabandcut.menu.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

public class TestGame extends Activity {
	public static PhysicsWorld mWorld;
	public Handler mHandler;
	private Screen screen;
	private SurfaceHolder mHolder;
	protected Canvas canvas;
	DisplayMetrics dm;

	public static int STATE_FINISHED = 1;
	public static int STATE_PLAYING = 0;
	public static int STATE_OVER = 100;
	public static int STATE_END = 3;
	public static int state = STATE_PLAYING;

	public Level currentLevel;
	public static int currentLevelIndex = 0;

	LinkedList<Level> levels;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		initLevels();
		currentLevel = levels.get(LevelScreen.level);
		currentLevelIndex = LevelScreen.level;

		mWorld = null;
		mWorld = new PhysicsWorld(this, dm.widthPixels, dm.heightPixels,
				currentLevel);
		System.gc();

		// this.setContentView(this.mWorld);

		this.screen = new Screen(this, Color.CYAN);
		mHolder = screen.getHolder();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(this.screen);

		// mWorld.add();
		// Add 12 Balls

		// Start Regular Update
		mHandler = new Handler();
		mHandler.post(update);
	}

	private void initLevels() {
		levels = new LinkedList<Level>();
		levels.add(new Level1());
		levels.add(new Level2());
		levels.add(new Level3());
		levels.add(new Level4());
		levels.add(new Level5());
		levels.add(new Level6());
		levels.add(new Level7());
		levels.add(new Level8());
		levels.add(new Level9());
		levels.add(new Level10());
		levels.add(new Level11());
		levels.add(new Level12());
		levels.add(new Level13());
		levels.add(new Level14());
		levels.add(new Level15());
		levels.add(new Level16());
		levels.add(new Level17());
		levels.add(new Level18());
	}

	@Override
	protected void onPause() {
		super.onPause();

		PhysicsWorld.pause = true;

		MainScreen.musicFlag = false;
		MainScreen.h.sendEmptyMessage(0);

		SoundManager.stopAllSound();
		SoundManager.musicFlag = false;

		mHandler.removeCallbacks(update);
	}

	private Runnable update = new Runnable() {
		public void run() {

			if (state == STATE_FINISHED) {
				if (currentLevelIndex == levels.size() - 1) {
					// Log.d("TestGame", "Game Finished");
					return;
				}
				currentLevelIndex++;
				currentLevel = levels.get(currentLevelIndex);
				mWorld = new PhysicsWorld(getApplicationContext(),
						dm.widthPixels, dm.heightPixels, currentLevel);
				state = STATE_PLAYING;
			} else if (state == STATE_OVER) {
				PhysicsWorld.score = 0;

				currentLevel = levels.get(currentLevelIndex);
				mWorld = new PhysicsWorld(getApplicationContext(),
						dm.widthPixels, dm.heightPixels, currentLevel);
				state = STATE_PLAYING;
			} else if (state == STATE_END) {
				state = STATE_PLAYING;
				finish();
			}
			//			
			if (!Screen.screenReady) {
				System.out.println("Not ready yet");
			}
			// System.out.println("updating");
			if (Screen.screenReady) {
				if (!PhysicsWorld.pause) {
					// if (mWorld.gameState != 0)
					// mWorld = new PhysicsWorld(getApplicationContext(),
					// dm.widthPixels, dm.heightPixels, currentLevel);
					canvas = mHolder.lockCanvas();
					mWorld.Update(canvas);
					try {
						mHolder.unlockCanvasAndPost(canvas);
					} catch (Exception e) {

					}
				}
			}

			mHandler.postDelayed(update, 0);
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

		MainScreen.musicFlag = true;
		MainScreen.h.sendEmptyMessage(0);
		SoundManager.musicFlag = true;

		 PhysicsWorld.pause = false;
		mHandler.postDelayed(update, 0);
	}

	@Override
	protected void onStop() {
		super.onStop();
		PhysicsWorld.pause = true;
	}

}
