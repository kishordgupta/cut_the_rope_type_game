package com.kd.grabandcut.menu.level;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.collision.Shape;
import org.jbox2d.collision.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.ContactFilter;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.JointEdge;
import org.jbox2d.dynamics.joints.JointType;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import com.kd.grabandcut.menu.MainScreen;
import com.kd.grabandcut.menu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Paint.Style; //import android.graphics.drawable.shapes.Shape;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class PhysicsWorld extends SurfaceView {

	protected static final int GUIUPDATEIDENTIFIER = 0x231;
	public int gameState = 0;

	public int targetFPS = 20;
	public float timeStep = 1.0f / targetFPS;
	public int iterations = 1;

	private AABB worldAABB;
	public static World world;
	private PolygonDef groundShapeDef;

	public float ballpositionx = 0f;
	public float ballpositiony = 0f;

	public ArrayList<Body>[] rope;// = new List<Body>();
	public int Ropenumber = 0;

	public Rect[] star;
	public int Starnumber = 0;

	public Rect[] bubble;
	public int bubblenumber = 0;

	private Rect[] cutter = null;
	int cutternumber = 0;

	private Rect[] ropecutter = null;
	int ropecutternumber = 0;

	private Rect[] timestar = null;
	int timestarnumber = 0;;
	int[] time;
	private Rect mainmenu = null;
	private Rect main = null;
	private Rect[] doormover = null;
	int doormovernumber;
	int[] doormoveratex;
	int[] doormoveratey;
	int[] cuttermoveratex;
	int[] cuttermoveratey;
	int doorratex = 0;
	int doorratey = 0;
	int cutterpicnumber = 0;
	private Rect restart = null;

	public DBHistory dbh;
	public GameHistory gs;

	public Rect door;

	static public int World_W, World_H;
	public RevoluteJointDef j;
	private Paint paint;
	private Body body;
	private Body link;
	private Body Finalbody;
	private Body[] cellings;
	private int cell = 0;
	private float radius = 10;

	boolean ballbubblestate = false;
	public Body bod;
	boolean flag = true;

	final public int DOORSTATE_NORMAL = 1;
	final public int DOORSTATE_NEAR = 2;
	final public int DOORSTATE_FINISHED = 3;

	public int DOORSTATE = DOORSTATE_NORMAL;
	static public Bitmap[] Backgorund = null;
	static public Bitmap[] torch = null;
	static public Bitmap[] character = null;
	static public Bitmap maindoor = null;
	static public Bitmap maindoor1 = null;
	static public Bitmap resetpic = null;
	static public Bitmap resetpic1 = null;
	static public Bitmap pausepic = null;
	static public Bitmap pausepic1 = null;
	static public Bitmap[] flow = null;
	static public Bitmap buuble = null;
	static public Bitmap ballbubble = null;
	static public Bitmap[] starpic = null;
	static public Bitmap[] cutterpic = null;
	static public Bitmap[] ropecutterpic = null;
	static public Bitmap[] Doormoverpic = null;
	static public Bitmap[] vootdestroyedpic = null;
	int doormoverpicnum = 0;
	int ropecutterpicnum = 0;
	int charracternumber = 0;
	int torchnumber = 0;
	int glownumber = 0;
	int starpicnum = 0;
	int vootdestroynum = 0;
	int starcollection = 0;
	static int score = 0;
	int backnumber = 0;
	public float ratiox = 0;
	public float ratioy = 0;
	int xx = 0;
	int yx = 0;
	public float balldensity = 9000f;
	public float ropedensity = 7500f;
	public float mousex = 0f;
	public float mousey = 0f;
	Vec2 Gravity = new Vec2((float) 0.0, (float) 80.0);
	Vec2 ResturnGravity = new Vec2((float) 0.0, (float) -50.0);

	int starflag = 1;
	int star2flag = 1;
	int rs[] = null;
	int xs[] = null;
	int ys[] = null;
	int finished = 0;
	boolean vootdestroyed = false;
	int ropeattached = 0;
	boolean ropebool = true;
	public static boolean pause = false;
	private SoundPool sounds;
	private int sExplosion;
	private int srarcollect;
	int redcolor = 0;
	int greencolor = 0;
	int bluecolor = 0;

	public PhysicsWorld(Context context, int W, int H, Level level) {
		super(context);
		gameState = 0;
		World_W = W;
		World_H = H;
		ratiox = W / 320;
		ratioy = H / 480;
		// Step 1: Create Physics World Boundaries
		worldAABB = new AABB();

		Vec2 min = new Vec2(-50, -50);
		Vec2 max = new Vec2(World_W + 50, World_H + 50);

		worldAABB.lowerBound.set(min);
		worldAABB.upperBound.set(max);
		// Step 2: Create Physics World with Gravity

		boolean doSleep = true;
		if (world != null) {
			// Body[] bodyList = new Body[world.getBodyCount()];
			Body currentBody = world.getBodyList();
			// int i = 0;
			while (currentBody != null) {
				world.destroyBody(currentBody);
				// bodyList[i++] = currentBody;
				currentBody = world.getBodyList().getNext();
			}

			// for(Body body: bodyList)
			// world.destroyBody(body);
			// bodyList = null;
			currentBody = null;
			// world = null;
			System.gc();
			world.setGravity(Gravity);

		} else
			world = new World(worldAABB, Gravity, doSleep);

		world.setContinuousPhysics(false);

		// collision hanlder
		world.setContactFilter(new ContactFilter() {

			@Override
			public boolean shouldCollide(Shape shape1, Shape shape2) {
				return false;
			}
		});
		world.setPositionCorrection(false);
		// step 4: initialize
		// bodies = new Body[50];
		rope = new ArrayList[8];
		cellings = new Body[6];
		star = new Rect[8];
		bubble = new Rect[4];
		cutter = new Rect[8];
		door = new Rect();
		ropecutter = new Rect[12];
		doormover = new Rect[8];
		doormoveratex = new int[8];
		doormoveratey = new int[8];
		timestar = new Rect[4];
		time = new int[4];
		cuttermoveratex = new int[12];
		cuttermoveratey = new int[12];

		level.init(this);

		paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.RED);

		restart = new Rect();
		restart.set((int) (200 * World_W / 320), (int) (5 * World_H / 480),
				(int) (240 * World_W / 320), (int) (35 * World_H / 480));
		mainmenu = new Rect();
		mainmenu.set((int) (260 * World_W / 320), (int) (5 * World_H / 480),
				(int) (300 * World_W / 320), (int) (35 * World_H / 480));
		main = new Rect();
		main.set((int) (140 * World_W / 320), (int) (5 * World_H / 480),
				(int) (180 * World_W / 320), (int) (35 * World_H / 480));
		xx = (int) (Math.random() * (World_W - 90));
		yx = (int) (Math.random() * (World_H - 90));

		rs = new int[40];
		xs = new int[40];
		;
		ys = new int[40];
		;
		for (int i = 0; i < 40; i++) {

			rs[i] = (int) (Math.random() * 2);
			xs[i] = (int) (Math.random() * World_W);
			ys[i] = (int) (Math.random() * World_H);

		}
		sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		sExplosion = sounds.load(context, R.raw.bubble, 1);
		srarcollect = sounds.load(context, R.raw.starcollect, 1);

		MainScreen.musicHandlerState = MainScreen.MUSIC_STATE_GAME;
		MainScreen.musicChanged = true;
		// MainScreen.synchronizeMusic.notifyAll();
		MainScreen.h.sendEmptyMessage(0);
	}

	public void setball(float positionx, float positiony) {
		ballpositionx = positionx;
		ballpositiony = positiony;
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.x = positionx;
		bodyDef.position.y = positiony;
		bodyDef.userData = "Ball";

		PolygonDef boxDef = new PolygonDef();
		boxDef.setAsBox(10f * World_W / 320, 10f * World_H / 480);
		boxDef.density = balldensity;
		boxDef.friction = 0.5f;
		boxDef.restitution = 0.5f;
		boxDef.isSensor = true;
		Finalbody = world.createBody(bodyDef);
		Finalbody.createShape(boxDef);
		Finalbody.setMassFromShapes();

		// link = Finalbody;
	}

	public void addrope(float positionx, float positiony) {
		//	
		float ratex = positionx;
		float ratey = positiony;
		BodyDef bodyDef = new BodyDef();
		float fh = ballpositionx;
		float fl = ballpositiony;
		bodyDef.position.x = ballpositionx;
		bodyDef.position.y = ballpositiony;
		PolygonDef boxDef = new PolygonDef();
		boxDef.setAsBox(2f * World_W / 320, 25f * World_H / 480);
		boxDef.density = ropedensity;
		boxDef.friction = 0.0f;
		boxDef.restitution = 0.0f;
		Body bod = world.createBody(bodyDef);
		bod.createShape(boxDef);
		// Vec2 a = new Vec2(0, 0);
		bod.createShape(boxDef);

		// joint
		Vec2 anchor = new Vec2(fh, fl);
		RevoluteJointDef jd = new RevoluteJointDef();
		jd.initialize(Finalbody, bod, anchor);
		jd.enableLimit = false;
		world.createJoint(jd);
		bod.setMassFromShapes();
		link = bod;
		rope[Ropenumber] = new ArrayList<Body>(6);
		rope[Ropenumber].add(bod);
		// ////////////

		for (int i = 1; i <= 5; i++) {
			// rope segment
			bodyDef = new BodyDef();
			fh = fh - ratex; // intersection(ballpositionx,positionx,i+1);
			bodyDef.position.x = fh;
			fl = fl - ratey;// intersection(ballpositiony,positiony,i+1);
			bodyDef.position.y = fl;
			boxDef = new PolygonDef();
			boxDef.setAsBox(2f * World_W / 320, 25f * World_H / 480);
			boxDef.density = ropedensity;
			;
			// boxDef.friction = 0f;
			// boxDef.restitution = 0f;

			bod = world.createBody(bodyDef);

			bod.createShape(boxDef);

			anchor = new Vec2(fh, fl);
			jd = new RevoluteJointDef();
			jd.initialize(link, bod, anchor);
			jd.enableLimit = false;
			world.createJoint(jd);
			bod.setMassFromShapes();
			link = bod;
			rope[Ropenumber].add(bod);
		}
		// ///////////create celling
		bodyDef = new BodyDef();
		bodyDef.position.x = fh - ratex;
		bodyDef.position.y = fl - ratey;

		boxDef = new PolygonDef();
		boxDef.setAsBox(0.1f * World_W / 320, 0.1f * World_H / 480);
		boxDef.isSensor = true;
		boxDef.density = 0.0f;
		boxDef.friction = 0f;
		boxDef.restitution = 0f;
		bod = new Body(bodyDef, world);

		anchor = new Vec2(fh, fl);
		bod = world.createBody(bodyDef);
		bod.createShape(boxDef);
		bod.setMassFromShapes();
		jd = new RevoluteJointDef();
		jd.type = JointType.REVOLUTE_JOINT;
		jd.enableLimit = false;
		jd.initialize(link, bod, anchor);
		world.createJoint(jd);
		bod.setMassFromShapes();
		rope[Ropenumber].add(bod);

		// //////////////
		Ropenumber++;
	}

	public void addstar(int x, int y) {
		star[Starnumber] = new Rect();
		star[Starnumber].set(x - (int) (22 * World_W / 320), y
				- (int) (20 * World_H / 480), x + (int) (20 * World_W / 320), y
				+ (int) (22 * World_H / 480));

		Starnumber++;
	}

	public void addtimestar(int x, int y, int timemillisecond) {
		timestar[timestarnumber] = new Rect();
		timestar[timestarnumber].set(x - (int) (10 * World_W / 320), y
				- (int) (10 * World_H / 480), x + (int) (10 * World_W / 320), y
				+ (int) (10 * World_H / 480));
		time[timestarnumber] = timemillisecond;
		timestarnumber++;
	}

	public void addcutter(int x, int y, int height, int width) {
		cutter[cutternumber] = new Rect();
		cutter[cutternumber].set(x, y, x + width, y + height);

		cutternumber++;
	}

	public void addropecutter(int x, int y, int height, int width) {
		ropecutter[ropecutternumber] = new Rect();
		ropecutter[ropecutternumber].set(x, y, x + width, y + height);

		ropecutternumber++;
	}

	public void addcutter(int x, int y, int height, int width, int movex,
			int movey) {
		cutter[cutternumber] = new Rect();
		cutter[cutternumber].set(x, y, x + width, y + height);
		cuttermoveratex[cutternumber] = movex;
		cuttermoveratey[cutternumber] = movey;

		cutternumber++;
	}

	public void addbubble(int x, int y) {
		bubble[bubblenumber] = new Rect();
		bubble[bubblenumber].set(x - (int) (25 * World_W / 320), y
				- (int) (25 * World_H / 480), x + (int) (25 * World_W / 320), y
				+ (int) (25 * World_H / 480));

		bubblenumber++;

	}

	public void adddoormover(int x, int y, int moveinx, int moviny) {
		doormover[doormovernumber] = new Rect();
		doormover[doormovernumber].set(x - (int) (10 * World_W / 320), y
				- (int) (10 * World_H / 480), x + (int) (10 * World_W / 320), y
				+ (int) (10 * World_H / 480));
		doormoveratex[doormovernumber] = moveinx;
		doormoveratey[doormovernumber] = moviny;
		doormovernumber++;

	}

	public void adddoor(int x, int y) {

		door.set(x - (int) (30 * World_W / 320),
				y - (int) (35 * World_H / 480), x + (int) (30 * World_W / 320),
				y + (int) (35 * World_H / 480));
		;

	}

	public void drawdoor(Canvas canvas) {
		if (door.top < 0 || door.bottom > (int) (480 * World_H / 480)) {
			doorratey = doorratey * (-1);
		}
		if (door.left < 0 || door.right > (int) (320 * World_W / 320)) {
			doorratex = (-1) * doorratex;
		}
		door.top = door.top + doorratey;
		door.bottom = door.bottom + doorratey;
		door.left = door.left - doorratex;
		door.right = door.right - doorratex;
		Rect r = new Rect();
		r.top = (int) door.top - (int) (15 * World_H / 480);
		r.bottom = (int) door.bottom + (int) (15 * World_H / 480);
		r.left = (int) door.left - (int) (25 * World_W / 320);
		r.right = (int) door.right + (int) (25 * World_W / 320);
		if (DOORSTATE == DOORSTATE_NORMAL) {
			canvas.drawBitmap(maindoor1, null, door, null);
		}
		if (DOORSTATE == DOORSTATE_NEAR) {
			// Rect r = new Rect();
			// r.top = (int) door.top - (int) (20 * World_H/480);
			// r.bottom = (int) door.bottom + (int) (20 * World_H/480);
			// r.left = (int) door.left - (int) (20 * World_W/320);
			// r.right = (int) door.right + (int) (20 * World_W/320);
			canvas.drawBitmap(maindoor, null, r, null);
		} else if (DOORSTATE == DOORSTATE_FINISHED) {
			canvas.drawBitmap(maindoor, null, r, null);
		}

	}

	public void drawball(Canvas canvas) {
		paint.setColor(Color.BLACK);
		if (Finalbody != null) {
			Rect r = new Rect();
			r.top = (int) Finalbody.getPosition().y
					- (int) (25 * World_H / 480);
			r.bottom = (int) Finalbody.getPosition().y
					+ (int) (25 * World_H / 480);
			r.left = (int) Finalbody.getPosition().x
					- (int) (25 * World_W / 320);
			r.right = (int) Finalbody.getPosition().x
					+ (int) (25 * World_W / 320);

			if (vootdestroyed == false) {
				if (ballbubblestate == false) {

					canvas.drawBitmap(character[charracternumber], null,
							new Rect(r), null);
					if (starflag > 8) {
						charracternumber++;
						starflag = 1;
					}
					starflag++;
					if (charracternumber == 5)
						charracternumber = 0;
				} else {
					canvas.drawBitmap(ballbubble, null, new Rect(r), null);
				}
			} else {
				canvas.drawBitmap(vootdestroyedpic[0], null, new Rect(r), null);
			}
		} else
			finished();

	}

	public void drawrope(Canvas canvas, int index) {
		paint.setColor(Color.YELLOW);
		flag = true;
		Body boj = null;
		for (Body body : rope[index]) {
			if (body == null) {
				flag = false;
				boj = rope[index].get(5);
				break;
			} else if (body.getJointList() == null) {
				boj = rope[index].get(5);
				flag = false;
				break;
			}
		}
		if (flag) {
			paint.setStrokeWidth(1);
			float startx = Finalbody.getPosition().x;
			float starty = Finalbody.getPosition().y;
			for (int i = 1; i < 6; i++) {
				body = rope[index].get(i);
				paint
						.setColor(Color.argb(100, redcolor, greencolor,
								bluecolor));
				canvas.drawLine(startx - 2, starty, body.getPosition().x - 2,
						body.getPosition().y, paint);
				paint
						.setColor(Color.argb(200, redcolor, greencolor,
								bluecolor));
				canvas.drawLine(startx - 1, starty, body.getPosition().x - 1,
						body.getPosition().y, paint);
				paint
						.setColor(Color.argb(255, redcolor, greencolor,
								bluecolor));
				canvas.drawLine(startx, starty, body.getPosition().x, body
						.getPosition().y, paint);
				canvas.drawLine(startx, starty, body.getPosition().x, body
						.getPosition().y, paint);
				paint
						.setColor(Color.argb(200, redcolor, greencolor,
								bluecolor));
				canvas.drawLine(startx + 1, starty, body.getPosition().x + 1,
						body.getPosition().y, paint);
				paint
						.setColor(Color.argb(100, redcolor, greencolor,
								bluecolor));
				canvas.drawLine(startx + 2, starty, body.getPosition().x + 2,
						body.getPosition().y, paint);
				startx = body.getPosition().x;
				starty = body.getPosition().y;

				// boj=rope[index].get(5);;;
				boj = body;
				paint.setColor(Color.RED);
				canvas.drawCircle(body.getPosition().x, body.getPosition().y,
						4, paint);
			}
			ropeattached++;

		}
		if (boj != null) {
			Rect r = new Rect();
			r.top = (int) boj.getPosition().y - (int) (20 * World_H / 480);
			r.bottom = (int) boj.getPosition().y + (int) (20 * World_H / 480);
			r.left = (int) boj.getPosition().x - (int) (20 * World_W / 320);
			r.right = (int) boj.getPosition().x + (int) (20 * World_W / 320);
			;
			canvas.drawBitmap(torch[torchnumber], null, new Rect(r), null);
			// canvas.drawRect(r, paint);
			// torchnumber++;

		}
	}

	public void drawstar(Canvas canvas) {
		// paint.setColor(Color.RED);
		for (int i = 0; i < Starnumber; i++) {
			if (star[i].right != 0) {

				canvas.drawBitmap(starpic[starpicnum], null, star[i], null);

				if (star2flag > 4) {
					starpicnum++;
					star2flag = 1;
				}
				if (starpicnum > 3)
					starpicnum = 0;
				star2flag++;

			}
		}

	}

	public void drawtimestar(Canvas canvas) {
		paint.setColor(Color.GREEN);

		for (int i = 0; i < timestarnumber; i++) {
			if (timestar[i].right != 0) {
				if (time[i] < 0) {

					if (timestar[i].right < (int) (320 * World_W / 320)) {

						timestar[i].top = (int) (timestar[i].top + 3 * World_H / 480);
						timestar[i].bottom = (int) (timestar[i].bottom + 3 * World_H / 480);
						canvas.drawBitmap(starpic[starpicnum], null,
								timestar[i], null);

					}
				} else {
					canvas.drawBitmap(starpic[starpicnum], null, timestar[i],
							null);
					time[i]--;
				}
				;

			}
		}

	}

	public void drawbubble(Canvas canvas) {

		for (int i = 0; i < bubblenumber; i++) {

			if (bubble[i].right != 0) {
				canvas.drawBitmap(buuble, null, bubble[i], null);

			}

		}

	}

	public void drawcutter(Canvas canvas) {
		// paint.setColor(Color.CYAN);
		for (int i = 0; i < cutternumber; i++) {

			if (cutter[i].top < 0
					|| cutter[i].bottom > (int) (480 * World_H / 480)) {
				cuttermoveratey[i] = cuttermoveratey[i] * (-1);
			}
			if (cutter[i].left < 0
					|| cutter[i].right > (int) (320 * World_W / 320)) {
				cuttermoveratex[i] = (-1) * cuttermoveratex[i];
			}
			cutter[i].top = cutter[i].top + cuttermoveratey[i];
			cutter[i].bottom = cutter[i].bottom + cuttermoveratey[i];
			cutter[i].left = cutter[i].left
					- (int) (cuttermoveratex[i] * World_W / 320);
			cutter[i].right = cutter[i].right
					- (int) (cuttermoveratex[i] * World_W / 320);
			// canvas.drawRect(cutter[i], paint);
			canvas
					.drawBitmap(cutterpic[cutterpicnumber], null, cutter[i],
							null);

		}
		cutterpicnumber++;
		if (cutterpicnumber > 4) {
			cutterpicnumber = 0;
		}

	}

	public void drawropecutter(Canvas canvas) {
		paint.setColor(Color.YELLOW);
		for (int i = 0; i < ropecutternumber; i++) {
			// canvas.drawRect(ropecutter[i], paint);
			// canvas.drawBitmap(buuble, null, bubble[i], null);

			canvas.drawBitmap(ropecutterpic[ropecutterpicnum], null,
					ropecutter[i], null);

		}
		ropecutterpicnum++;
		if (ropecutterpicnum > 3) {
			ropecutterpicnum = 0;
		}

	}

	public void drawdoormover(Canvas canvas) {
		paint.setColor(Color.YELLOW);
		for (int i = 0; i < doormovernumber; i++) {
			if (doormover[i].right != 0) {
				// canvas.drawRect(doormover[i], paint);
				canvas.drawBitmap(Doormoverpic[0], null, doormover[i], null);
			}

		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();
		pause = false;
		this.cutPoints.add(new cutPoint(new Point((int) x, (int) y)));

		mousex = x;
		mousey = y;
		finished = 1;
		AABB aabb = new AABB();
		aabb.lowerBound.set((float) (x - (5 * World_W / 320)),
				(float) (y - (5 * World_H / 480)));
		aabb.upperBound.set((float) (x + (5 * World_W / 320)),
				(float) (y + (5 * World_H / 480)));
		Shape[] s = new Shape[10];
		s = world.query(aabb, 10);

		for (int j = 0; j < s.length; j++) {
			bod = s[j].getBody();
			if (bod != null) {
				JointEdge jj;
				jj = bod.getJointList();
				if (jj != null && bod.m_userData != "Ball") {
					world.destroyJoint(jj.joint);
					world.destroyBody(bod);
					bod = null;
					SoundManager.playSound(11, 1);
				} else if (bod.m_userData == "Ball") {
					if (ballbubblestate) {

						world.setGravity(Gravity);
						ballbubblestate = false;
						SoundManager.playSound(2, 1);

					}

				}
			}
		}
		Rect r = new Rect();
		r.top = (int) y - (int) (5 * World_H / 480);
		r.bottom = (int) y + (int) (5 * World_H / 480);
		r.left = (int) x - (int) (5 * World_W / 320);
		r.right = (int) x + (int) (5 * World_W / 320);
		if (r.intersect(restart))
			finished();
		if (r.intersect(mainmenu)) {
			// SoundManager.playSound(9, 1);
			TestGame.state = 3;
		}
		// sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);
		if (r.intersect(main)) {
			if (pause) {
				pause = false;
			} else {
				pause = true;
			}
		}

		return true;
	}

	public void collision() {
		int x = (int) Finalbody.getPosition().x;
		int y = (int) Finalbody.getPosition().y;
		Rect r = new Rect();
		r.set(x - (int) (20 * World_W / 320), y - (int) (20 * World_H / 480), x
				+ (int) (20 * World_W / 320), y + (int) (20 * World_H / 480));
		for (int i = 0; i < Starnumber; i++) {
			if (star[i].right != 0) {
				if (star[i].intersect(r)) {
					star[i].right = 0;
					;
					score++;
					starcollection++;
					SoundManager.playSound(13, 1);
				}
			}
		}
		for (int i = 0; i < bubblenumber; i++) {
			if (bubble[i].intersect(r)) {
				bubble[i].right = 0;
				;
				ballbubblestate = true;

				world.setGravity(ResturnGravity);
				SoundManager.playSound(1, 1);
			}
		}
		for (int i = 0; i < cutternumber; i++) {
			if (cutter[i].intersect(r)) {
				SoundManager.playSound(5, 1);
				vootdestroyed = true;
				finished();
			}
		}
		for (int i = 0; i < timestarnumber; i++) {
			if (timestar[i].intersect(r)) {
				timestar[i].right = 0;
				;
				SoundManager.playSound(5, 1);
				finished();
			}
		}

		for (int i = 0; i < doormovernumber; i++) {
			if (doormover[i].intersect(r)) {
				doormover[i].right = 0;
				;
				doorratex = doormoveratex[i];
				doorratey = doormoveratey[i];
				SoundManager.playSound(6, 1);
			}
		}

		Rect doornear = new Rect();
		doornear.set(door.left - (int) (20 * World_W / 320), door.top
				- (int) (20 * World_H / 480), door.right
				+ (int) (20 * World_W / 320), door.bottom
				+ (int) (20 * World_H / 480));

		if (doornear.intersect(r)) {
			DOORSTATE = DOORSTATE_NEAR;
		} else {
			DOORSTATE = DOORSTATE_NORMAL;
		}
		if (door.intersect(r)) {
			DOORSTATE = DOORSTATE_FINISHED;
			// vootdestroyed=true;
			SoundManager.playSound(4, 1);
			reset();
		}

		for (int i = 0; i < ropecutternumber; i++) {
			// canvas.drawRect(ropecutter[i], paint);
			// canvas.drawBitmap(buuble, null, bubble[i], null);
			AABB aabb = new AABB();
			aabb.lowerBound.set((float) (ropecutter[i].right),
					(float) (ropecutter[i].bottom));
			aabb.upperBound.set((float) (ropecutter[i].left),
					(float) (ropecutter[i].top));
			Shape[] s = new Shape[10];
			s = world.query(aabb, 10);

			for (int j = 0; j < s.length; j++) {
				bod = s[j].getBody();
				if (bod != null) {
					JointEdge jj;
					jj = bod.getJointList();
					if (jj != null && bod.m_userData != "Ball") {
						world.destroyJoint(jj.joint);
						world.destroyBody(bod);
						bod = null;
						SoundManager.playSound(12, 1);
					}

				}
			}

		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (canvas == null)
			return;
		canvas.drawColor(Color.argb(255, 0, 35, 70));
		drawbackground(canvas);
		// canvas.drawColor(Color.BLACK);

		canvas.drawBitmap(Backgorund[0], null, new Rect(xx, yx, xx + 90,
				yx + 90), null);

		// canvas.drawBitmap(Backgorund[0], null,new Rect(0,0,World_W,World_H),
		// null);
		// backnumber++;
		// if(backnumber>8)
		// {backnumber=0;}
		// drawdoor(canvas);

		for (int i = 0; i < Ropenumber; i++) {
			drawrope(canvas, i);
		}
		if (ropeattached == 0) {
			ropebool = false;
		}
		ropeattached = 0;
		torchnumber++;
		if (torchnumber > 3) {
			torchnumber = 0;

		}
		drawdoor(canvas);
		drawball(canvas);
		drawstar(canvas);
		drawbubble(canvas);
		drawdoor(canvas);
		drawcutter(canvas);
		drawtimestar(canvas);
		drawdoormover(canvas);
		drawropecutter(canvas);
		paint.setColor(Color.CYAN);

		for (int i = 0; i < Starnumber; i++) {
			if (i < starcollection) {
				canvas.drawBitmap(starpic[starpicnum], null, new Rect(
						5 + i * 20, 5, 25 + i * 20, 25), null);
			} else {
				canvas.drawBitmap(torch[torchnumber], null, new Rect(
						5 + i * 20, 5, 25 + i * 20, 25), null);
			}
		}
		drawtouch(canvas);
		// canvas.drawText(" TOTAL SCORE "
		// + score, (int) (70 * ratiox), (int) (20 * ratioy), paint);
		canvas.drawBitmap(resetpic, null, restart, null);
		canvas.drawBitmap(resetpic1, null, mainmenu, null);
		if (pause == false) {
			canvas.drawBitmap(pausepic, null, main, null);
		} else {
			canvas.drawBitmap(pausepic1, null, main, null);
		}
		// canvas.drawText(""+(TestGame.currentLevelIndex+1), 100, 40, paint);
	}

	class cutPoint {
		private final int maxIteration = 15;
		public Point p;
		public int iterationLeft;

		public cutPoint(Point p) {
			this.p = p;
			this.iterationLeft = maxIteration;
		}

	}

	private ArrayList<cutPoint> cutPoints = new ArrayList<cutPoint>();

	public void drawtouch(Canvas canvas) {
		ArrayList<cutPoint> toRemovePoints = new ArrayList<cutPoint>();
		paint.setColor(0x77777777);
		for (cutPoint c : cutPoints) {
			if (c.iterationLeft-- != 0) {
				float f = c.iterationLeft / c.maxIteration * 2000;
				// paint.setAlpha((int)f);
				canvas.drawCircle(c.p.x, c.p.y, 5, paint);
			} else {
				toRemovePoints.add(c);
			}
		}
		if (!toRemovePoints.isEmpty())
			for (cutPoint c : toRemovePoints)
				cutPoints.remove(c);

		// paint.setAlpha(255);
	}

	public void drawbackground(Canvas canvas) {
		paint.setColor(Color.WHITE);

		paint.setColor(Color.WHITE);
		for (int i = 0; i < 40; i++) {

			canvas.drawCircle(xs[i], ys[i], rs[i] + (int) (Math.random() * 2),
					paint);

		}
		paint.setColor(Color.WHITE);
		starflag++;
		if (starflag == 12)
			starflag = 0;

	}

	public void Update(Canvas canvas) {
		// TODO Auto-generated method stub
//		if (!pause) {
			world.step(timeStep, 1);
			collision();
			onDraw(canvas);
			// Log.d("LevelScreen"+Finalbody.getPosition().y,
			// "Reset Level"+Finalbody.getPosition().x);
			if (Finalbody != null) {
				if (ropebool == false
						&& Finalbody.getPosition().y > (int) ((World_H))) {
					finished();
				}
				if (Finalbody.getPosition().y < (int) (-30)) {
					finished();
				}
				if (ropebool == false
						&& Finalbody.getPosition().x > (int) (World_W + 30)) {
					finished();
				}
				if (ropebool == false
						&& Finalbody.getPosition().x < (int) ((-30))) {
					finished();
				}
			}
//		}
	}

	private void reset() {
		// /db write
		gs = new GameHistory(TestGame.currentLevelIndex + 1, 1, starcollection,
				score);

		MainScreen.dbh.updatelevel(gs);
		MainScreen.dbh.updateStatus(gs);
		TestGame.state = 1;
		// SoundManager.playSound(8, 1);
	}

	private void finished() {

		TestGame.state = 100;
		SoundManager.playSound(5, 1);
	}
}
