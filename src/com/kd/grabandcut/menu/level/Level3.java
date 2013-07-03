package com.kd.grabandcut.menu.level;

public class Level3 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		pw.setball(250*x/320,140*y/480);
		pw.addrope(0,15*y/480);
		pw.addrope(28*x/320,15*y/480);
		//pw.addbubble(40, 250);
		pw.addrope(10*x/320,(-25)*y/480);
		pw.addstar(100*x/320, 190*y/480);
		pw.addstar(100*x/320, 270*y/480);
		pw.addstar(200*x/320, 415*y/480);
		pw.adddoor(280*x/320, 440*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
