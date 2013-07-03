package com.kd.grabandcut.menu.level;

public class Level6 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		pw.setball(160*x/320,180*y/480);
		pw.addrope(20*x/320,10*y/480);
		pw.addrope(-20*x/320,10*y/480);
		//pw.addbubble(40, 250);
		//pw.addrope(10*x,(-25)*y);
		pw.addstar(160*x/320, 60*y/480);
		pw.addstar(160*x/320, 280*y/480);
		pw.addstar(160*x/320, 360*y/480);
		pw.addbubble(160*x/320, 280*y/480);
		pw.adddoor(160*x/320, 440*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
