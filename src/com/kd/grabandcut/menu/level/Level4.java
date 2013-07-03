package com.kd.grabandcut.menu.level;

public class Level4 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		pw.setball(160*x/320,180*y/480);
		pw.addrope(0,25*y/480);
		pw.addrope(-25*x/320,0);
		//pw.addbubble(40, 250);
		pw.addrope(25*x/320,0);
		pw.addrope(-5*x/320,-20*y/480);
		pw.addstar(160*x/320, 260*y/480);
		pw.addstar(200*x/320, 390*y/480);
		pw.addstar(85*x/320, 330*y/480);
		//pw.addbubble(110, 360);
		pw.adddoor(220*x/320, 440*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
