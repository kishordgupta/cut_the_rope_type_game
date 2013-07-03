package com.kd.grabandcut.menu.level;

public class Level5 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		pw.setball(70*x/320,280*y/480);
		pw.addrope(0,40*y/480);
		pw.addrope(-25*x/320,-3*y/480);
		//pw.addbubble(40, 250);
		//pw.addrope(10*x,(-25)*y);
		pw.addstar(220*x/320, 50*y/480);
		pw.addstar(220*x/320, 200*y/480);
		pw.addstar(80*x/320, 340*y/480);
		pw.addbubble(80*x/320, 380*y/480);
		pw.adddoor(240*x/320, 440*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
