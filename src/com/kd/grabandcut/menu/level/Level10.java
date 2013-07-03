package com.kd.grabandcut.menu.level;

public class Level10 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		pw.setball(100*x/320,80*y/480);
		pw.addrope(12*x/320,5*y/480);
		pw.addrope(-12*x/320,5*y/480);
		pw.addrope(-12*x/320,-18*y/480);

		pw.addcutter(20*x/320, 100*y/480, 75*x/320, 35*y/480,0,0);
		pw.addcutter(140*x/320, 100*y/480, 75*x/320, 35*y/480,0,0);
		pw.addcutter(190*x/320, 40*y/480, 30*x/320, 15*y/480,0,0);
		pw.addcutter(210*x/320, 40*y/480, 30*x/320, 15*y/480,0,0);
		pw.addcutter(230*x/320, 40*y/480, 30*x/320, 15*y/480,0,0);
		pw.addcutter(250*x/320, 40*y/480, 30*x/320, 15*y/480,0,0);
		pw.addcutter(270*x/320, 40*y/480, 30*x/320, 15*y/480,0,0);
		pw.addcutter(290*x/320, 40*y/480, 30*x/320, 15*y/480,0,0);

		pw.addstar(90*x/320, 220*y/480);
		pw.addstar(90*x/320, 280*y/480);
		pw.addstar(255*x/320, 120*y/480);
		pw.addbubble(250*x/320, 320*y/480);
		pw.adddoor(250*x/320, 440*y/480);
		
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
