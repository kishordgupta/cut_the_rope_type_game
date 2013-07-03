package com.kd.grabandcut.menu.level;

public class Level2 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		pw.setball(5*x/32,3*y/8);
		pw.addrope(0,5*y/96);
		pw.addrope(-(11*x/160),5*y/96);
		//pw.addbubble(40, 250);
		pw.addrope(-(11*x/80),5*y/96);
		pw.addstar(9*x/32, 13*y/24);
		pw.addstar(3*x/4, 220*y/480);
		pw.addstar(5*x/8, 390*y/480);
		pw.addcutter(0, 330*y/480, 60*x/320, 40*y/480, 1*x/320, 0);
		pw.adddoor(9*x/16, 15*y/16);
		
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
