package com.kd.grabandcut.menu;

import java.util.ArrayList;


import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.kd.grabandcut.menu.R;
import com.kd.grabandcut.menu.level.BitmapProvider;
import com.kd.grabandcut.menu.level.DBHistory;
import com.kd.grabandcut.menu.level.GameHistory;
import com.kd.grabandcut.menu.level.PhysicsWorld;
import com.kd.grabandcut.menu.level.SoundManager;
import com.kd.grabandcut.menu.level.SynchronizeMusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;

public class MainScreen extends Activity {
	public static DBHistory dbh;
	public static Context ctx = null;
	public static boolean loadflag = false;

	public static final int MUSIC_STATE_MENU = 0;
	public static final int MUSIC_STATE_GAME = 1;
	public static final int MUSIC_STATE_NONE = 2;
	public static final int MUSIC_STATE_FINISHED = 3;
	public static int musicHandlerState = MUSIC_STATE_MENU;
	public static boolean musicFlag = true;

	public static Thread musicHandler;
	public static boolean musicChanged = false;

	public static MediaPlayer mediaPlayer;
	public static SynchronizeMusic synchronizeMusic;
	
	

	public static Handler h = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(!musicFlag){
				try {
					if (mediaPlayer != null){
						mediaPlayer.release();
						mediaPlayer = null;				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
						
			if (MainScreen.musicHandlerState == MUSIC_STATE_GAME) {
				if (mediaPlayer != null)
					mediaPlayer.release();
				mediaPlayer = MediaPlayer.create(ctx, R.raw.play);
				mediaPlayer.setVolume(5f, 5f);
				mediaPlayer.setLooping(true);
				mediaPlayer.start();
				Log.d("MainScreen", "Playing game sournd");
			} else if (MainScreen.musicHandlerState == MUSIC_STATE_MENU) {
				if (mediaPlayer != null)
					mediaPlayer.release();
				mediaPlayer = MediaPlayer.create(ctx, R.raw.menu);
				mediaPlayer.setVolume(5f, 5f);
				mediaPlayer.setLooping(true);
				mediaPlayer.start();
				Log.d("MainScreen", "Playing menu sound");
			} else if (MainScreen.musicHandlerState == MUSIC_STATE_NONE) {
				try {
					if (mediaPlayer != null)
						mediaPlayer.release();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		
	};

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ctx = this;
		setContentView(R.layout.mainscreen);
		AdRequest ad = new AdRequest();
		  //  ad.setTesting(true);
		    AdView adView = (AdView)findViewById(R.id.ad);
		    adView.loadAd(ad);
		synchronizeMusic = new SynchronizeMusic();
		BitmapProvider.res = getResources();
		dbh = new DBHistory(MainScreen.this);
		dbh.createRow();
		if (loadflag == false) {
			setpic();
			setLevelThumbs();
			// SoundManager.loadSounds();
		}

		// startMediaPlayer();

		// Create, Initialize and then load the Sound manager
		SoundManager.getInstance();
		SoundManager.initSounds(this);
		SoundManager.loadSounds();

		// initialize music variables
		this.musicHandlerState = MUSIC_STATE_MENU;
		this.musicChanged = true;
    	MainScreen.h.sendEmptyMessage(0);

		Button startGame = (Button) findViewById(R.id.startGame);
		startGame.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				SoundManager.playSound(3, 1);
				Intent myIntent = new Intent(view.getContext(),
						LevelScreen.class);
				startActivityForResult(myIntent, 0);
				// finish();
			}

		});

		Button options = (Button) findViewById(R.id.options);
		options.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				SoundManager.playSound(3, 1);
				Intent myIntent = new Intent(view.getContext(),
						OptionMenuScreen.class);
				startActivityForResult(myIntent, 0);
				// finish();
			}

		});

		Button exit = (Button) findViewById(R.id.exit);
		exit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				SoundManager.playSound(3, 1);
				finish();
				// moveTaskToBack(true);
			}

		});
	}

	public void startMediaPlayer() {
		if (mediaPlayer != null) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
			}
		}
		mediaPlayer = MediaPlayer.create(MainScreen.this, R.raw.menu);
		mediaPlayer.setLooping(true);
		try {
			mediaPlayer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setpic() {
		PhysicsWorld.Backgorund = new Bitmap[1];
		PhysicsWorld.Backgorund[0] = BitmapFactory.decodeResource(
				getResources(), R.drawable.moon2);
		PhysicsWorld.Backgorund[0] = Bitmap.createBitmap(
				PhysicsWorld.Backgorund[0], 0, 0, PhysicsWorld.Backgorund[0]
						.getWidth(), PhysicsWorld.Backgorund[0].getHeight(),
				null, true);

		PhysicsWorld.vootdestroyedpic = new Bitmap[1];
		PhysicsWorld.vootdestroyedpic[0] = BitmapFactory.decodeResource(
				getResources(), R.drawable.voote1);
		PhysicsWorld.vootdestroyedpic[0] = Bitmap.createBitmap(
				PhysicsWorld.vootdestroyedpic[0], 0, 0,
				PhysicsWorld.vootdestroyedpic[0].getWidth(),
				PhysicsWorld.vootdestroyedpic[0].getHeight(), null, true);


		PhysicsWorld.character = new Bitmap[5];

		PhysicsWorld.character[0] = BitmapFactory.decodeResource(
				getResources(), R.drawable.untitled1);
		PhysicsWorld.character[0] = Bitmap.createBitmap(
				PhysicsWorld.character[0], 0, 0, PhysicsWorld.character[0]
						.getWidth(), PhysicsWorld.character[0].getHeight(),
				null, true);
		PhysicsWorld.character[1] = BitmapFactory.decodeResource(
				getResources(), R.drawable.untitled2);
		PhysicsWorld.character[1] = Bitmap.createBitmap(
				PhysicsWorld.character[1], 0, 0, PhysicsWorld.character[1]
						.getWidth(), PhysicsWorld.character[1].getHeight(),
				null, true);
		PhysicsWorld.character[2] = BitmapFactory.decodeResource(
				getResources(), R.drawable.untitled3);
		PhysicsWorld.character[2] = Bitmap.createBitmap(
				PhysicsWorld.character[2], 0, 0, PhysicsWorld.character[2]
						.getWidth(), PhysicsWorld.character[2].getHeight(),
				null, true);
		PhysicsWorld.character[3] = BitmapFactory.decodeResource(
				getResources(), R.drawable.untitled4);
		PhysicsWorld.character[3] = Bitmap.createBitmap(
				PhysicsWorld.character[3], 0, 0, PhysicsWorld.character[3]
						.getWidth(), PhysicsWorld.character[3].getHeight(),
				null, true);
		PhysicsWorld.character[4] = BitmapFactory.decodeResource(
				getResources(), R.drawable.untitled5);
		PhysicsWorld.character[4] = Bitmap.createBitmap(
				PhysicsWorld.character[4], 0, 0, PhysicsWorld.character[4]
						.getWidth(), PhysicsWorld.character[4].getHeight(),
				null, true);

		PhysicsWorld.torch = new Bitmap[4];

		PhysicsWorld.torch[0] = BitmapFactory.decodeResource(getResources(),
				R.drawable.t1);
		PhysicsWorld.torch[0] = Bitmap.createBitmap(PhysicsWorld.torch[0], 0,
				0, PhysicsWorld.torch[0].getWidth(), PhysicsWorld.torch[0]
						.getHeight(), null, true);
		PhysicsWorld.torch[1] = BitmapFactory.decodeResource(getResources(),
				R.drawable.t2);
		PhysicsWorld.torch[1] = Bitmap.createBitmap(PhysicsWorld.torch[1], 0,
				0, PhysicsWorld.torch[1].getWidth(), PhysicsWorld.torch[1]
						.getHeight(), null, true);
		PhysicsWorld.torch[2] = BitmapFactory.decodeResource(getResources(),
				R.drawable.t3);
		PhysicsWorld.torch[2] = Bitmap.createBitmap(PhysicsWorld.torch[2], 0,
				0, PhysicsWorld.torch[2].getWidth(), PhysicsWorld.torch[2]
						.getHeight(), null, true);
		PhysicsWorld.torch[3] = BitmapFactory.decodeResource(getResources(),
				R.drawable.t4);
		PhysicsWorld.torch[3] = Bitmap.createBitmap(PhysicsWorld.torch[3], 0,
				0, PhysicsWorld.torch[3].getWidth(), PhysicsWorld.torch[3]
						.getHeight(), null, true);

		PhysicsWorld.cutterpic = new Bitmap[6];

		PhysicsWorld.cutterpic[0] = BitmapFactory.decodeResource(
				getResources(), R.drawable.voot1);
		PhysicsWorld.cutterpic[0] = Bitmap.createBitmap(
				PhysicsWorld.cutterpic[0], 0, 0, PhysicsWorld.cutterpic[0]
						.getWidth(), PhysicsWorld.cutterpic[0].getHeight(),
				null, true);
		PhysicsWorld.cutterpic[1] = BitmapFactory.decodeResource(
				getResources(), R.drawable.voot2);
		PhysicsWorld.cutterpic[1] = Bitmap.createBitmap(
				PhysicsWorld.cutterpic[1], 0, 0, PhysicsWorld.cutterpic[1]
						.getWidth(), PhysicsWorld.cutterpic[1].getHeight(),
				null, true);
		PhysicsWorld.cutterpic[2] = BitmapFactory.decodeResource(
				getResources(), R.drawable.voot3);
		PhysicsWorld.cutterpic[2] = Bitmap.createBitmap(
				PhysicsWorld.cutterpic[2], 0, 0, PhysicsWorld.cutterpic[2]
						.getWidth(), PhysicsWorld.cutterpic[2].getHeight(),
				null, true);
		PhysicsWorld.cutterpic[3] = BitmapFactory.decodeResource(
				getResources(), R.drawable.voot4);
		PhysicsWorld.cutterpic[3] = Bitmap.createBitmap(
				PhysicsWorld.cutterpic[3], 0, 0, PhysicsWorld.cutterpic[3]
						.getWidth(), PhysicsWorld.cutterpic[3].getHeight(),
				null, true);
		PhysicsWorld.cutterpic[4] = BitmapFactory.decodeResource(
				getResources(), R.drawable.voot1);
		PhysicsWorld.cutterpic[4] = Bitmap.createBitmap(
				PhysicsWorld.cutterpic[4], 0, 0, PhysicsWorld.cutterpic[4]
						.getWidth(), PhysicsWorld.cutterpic[4].getHeight(),
				null, true);
		PhysicsWorld.cutterpic[5] = BitmapFactory.decodeResource(
				getResources(), R.drawable.voot2);
		PhysicsWorld.cutterpic[5] = Bitmap.createBitmap(
				PhysicsWorld.cutterpic[5], 0, 0, PhysicsWorld.cutterpic[5]
						.getWidth(), PhysicsWorld.cutterpic[5].getHeight(),
				null, true);

		PhysicsWorld.buuble = BitmapFactory.decodeResource(getResources(),
				R.drawable.bubble);
		PhysicsWorld.buuble = Bitmap.createBitmap(PhysicsWorld.buuble, 0, 0,
				PhysicsWorld.buuble.getWidth(),
				PhysicsWorld.buuble.getHeight(), null, true);

		PhysicsWorld.maindoor = BitmapFactory.decodeResource(getResources(),
				R.drawable.coffin);
		PhysicsWorld.maindoor = Bitmap.createBitmap(PhysicsWorld.maindoor, 0,
				0, PhysicsWorld.maindoor.getWidth(), PhysicsWorld.maindoor
						.getHeight(), null, true);
		PhysicsWorld.maindoor1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coffin_open);
		PhysicsWorld.maindoor1 = Bitmap.createBitmap(PhysicsWorld.maindoor1, 0,
				0, PhysicsWorld.maindoor1.getWidth(), PhysicsWorld.maindoor1
						.getHeight(), null, true);

		PhysicsWorld.ballbubble = BitmapFactory.decodeResource(getResources(),
				R.drawable.bubblewithchar);
		PhysicsWorld.ballbubble = Bitmap.createBitmap(PhysicsWorld.ballbubble,
				0, 0, PhysicsWorld.ballbubble.getWidth(),
				PhysicsWorld.ballbubble.getHeight(), null, true);

		PhysicsWorld.resetpic = BitmapFactory.decodeResource(getResources(),
				R.drawable.restart);
		PhysicsWorld.resetpic = Bitmap.createBitmap(PhysicsWorld.resetpic, 0,
				0, PhysicsWorld.resetpic.getWidth(), PhysicsWorld.resetpic
						.getHeight(), null, true);

		PhysicsWorld.resetpic1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.sample_0);
		PhysicsWorld.resetpic1 = Bitmap.createBitmap(PhysicsWorld.resetpic1, 0,
				0, PhysicsWorld.resetpic1.getWidth(), PhysicsWorld.resetpic1
						.getHeight(), null, true);
		
		PhysicsWorld.pausepic = BitmapFactory.decodeResource(getResources(),
				R.drawable.pause_game);
		PhysicsWorld.pausepic = Bitmap.createBitmap(PhysicsWorld.pausepic, 0,
				0, PhysicsWorld.pausepic.getWidth(), PhysicsWorld.pausepic
						.getHeight(), null, true);
		PhysicsWorld.pausepic1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.play_game);
		PhysicsWorld.pausepic1 = Bitmap.createBitmap(PhysicsWorld.pausepic1, 0,
				0, PhysicsWorld.pausepic1.getWidth(), PhysicsWorld.pausepic1
						.getHeight(), null, true);

		PhysicsWorld.Doormoverpic = new Bitmap[1];
		PhysicsWorld.Doormoverpic[0] = BitmapFactory.decodeResource(
				getResources(), R.drawable.coffin);
		PhysicsWorld.Doormoverpic[0] = Bitmap.createBitmap(
				PhysicsWorld.Doormoverpic[0], 0, 0,
				PhysicsWorld.Doormoverpic[0].getWidth(),
				PhysicsWorld.Doormoverpic[0].getHeight(), null, true);

		PhysicsWorld.starpic = new Bitmap[4];

		PhysicsWorld.starpic[0] = BitmapFactory.decodeResource(getResources(),
				R.drawable.t11);
		PhysicsWorld.starpic[0] = Bitmap.createBitmap(PhysicsWorld.starpic[0],
				0, 0, PhysicsWorld.starpic[0].getWidth(),
				PhysicsWorld.starpic[0].getHeight(), null, true);
		PhysicsWorld.starpic[1] = BitmapFactory.decodeResource(getResources(),
				R.drawable.t12);
		PhysicsWorld.starpic[1] = Bitmap.createBitmap(PhysicsWorld.starpic[1],
				0, 0, PhysicsWorld.starpic[1].getWidth(),
				PhysicsWorld.starpic[1].getHeight(), null, true);
		PhysicsWorld.starpic[2] = BitmapFactory.decodeResource(getResources(),
				R.drawable.t13);
		PhysicsWorld.starpic[2] = Bitmap.createBitmap(PhysicsWorld.starpic[2],
				0, 0, PhysicsWorld.starpic[2].getWidth(),
				PhysicsWorld.starpic[2].getHeight(), null, true);
		PhysicsWorld.starpic[3] = BitmapFactory.decodeResource(getResources(),
				R.drawable.t14);
		PhysicsWorld.starpic[3] = Bitmap.createBitmap(PhysicsWorld.starpic[3],
				0, 0, PhysicsWorld.starpic[3].getWidth(),
				PhysicsWorld.starpic[3].getHeight(), null, true);

		PhysicsWorld.ropecutterpic = new Bitmap[4];

		PhysicsWorld.ropecutterpic[0] = BitmapFactory.decodeResource(
				getResources(), R.drawable.cloud1);
		PhysicsWorld.ropecutterpic[0] = Bitmap.createBitmap(
				PhysicsWorld.ropecutterpic[0], 0, 0,
				PhysicsWorld.ropecutterpic[0].getWidth(),
				PhysicsWorld.ropecutterpic[0].getHeight(), null, true);
		PhysicsWorld.ropecutterpic[1] = BitmapFactory.decodeResource(
				getResources(), R.drawable.cloud2);
		PhysicsWorld.ropecutterpic[1] = Bitmap.createBitmap(
				PhysicsWorld.ropecutterpic[1], 0, 0,
				PhysicsWorld.ropecutterpic[1].getWidth(),
				PhysicsWorld.ropecutterpic[1].getHeight(), null, true);
		PhysicsWorld.ropecutterpic[2] = BitmapFactory.decodeResource(
				getResources(), R.drawable.cloud3);
		PhysicsWorld.ropecutterpic[2] = Bitmap.createBitmap(
				PhysicsWorld.ropecutterpic[2], 0, 0,
				PhysicsWorld.ropecutterpic[2].getWidth(),
				PhysicsWorld.ropecutterpic[2].getHeight(), null, true);
		PhysicsWorld.ropecutterpic[3] = BitmapFactory.decodeResource(
				getResources(), R.drawable.cloud4);
		PhysicsWorld.ropecutterpic[3] = Bitmap.createBitmap(
				PhysicsWorld.ropecutterpic[3], 0, 0,
				PhysicsWorld.ropecutterpic[3].getWidth(),
				PhysicsWorld.ropecutterpic[3].getHeight(), null, true);

		loadflag = true;

	}

	public static void setLevelThumbs() {

		try {
			if (dbh == null) {
				dbh = new DBHistory(ctx);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<GameHistory> levelStates = new ArrayList<GameHistory>();

		levelStates = dbh.fetchAllRows();
		for (GameHistory g : levelStates) {
			// Log.d("MainScreen", "Level id: " + g.levelId + " Status: " +
			// g.status + " star: " + g.starNumber);
			if (g.status != 1) {
				try {
					LevelScreen.mThumbIds[g.levelId - 1] = R.drawable.level_locked;
					// Log.d("MainScreen", "Level id: " + g.levelId +
					// " Status: " + g.status + " Locked!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					if (g.starNumber == 1)
						LevelScreen.mThumbIds[g.levelId - 1] = R.drawable.level_1_star;

					else if (g.starNumber == 2)
						LevelScreen.mThumbIds[g.levelId - 1] = R.drawable.level_2_star;

					else if (g.starNumber == 3)
						LevelScreen.mThumbIds[g.levelId - 1] = R.drawable.level_3_star;
					else if (g.starNumber == 0)
						LevelScreen.mThumbIds[g.levelId - 1] = R.drawable.level_unlocked;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * @Override protected void onActivityResult(int requestCode, int
	 * resultCode, Intent data) { super.onActivityResult(requestCode,
	 * resultCode, data); startMediaPlayer(); }
	 */

	@Override
	public void onDestroy() {
		super.onDestroy();
		SoundManager.cleanup();

		MainScreen.musicHandlerState = MainScreen.MUSIC_STATE_FINISHED;

		if (mediaPlayer != null)
			mediaPlayer.release();

	}

}