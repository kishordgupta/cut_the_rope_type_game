package com.kd.grabandcut.menu.level;

public class Level7 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
		pw.setball(300*x/320,130*y/480);
		
		pw.addrope(0,15*y/480);
		pw.addrope(50*x/320,-10*y/480);
		pw.addrope(5*x/320,-30*y/480);
		pw.addrope(25*x/320,(20)*y/480);
		
		pw.addstar(220*x/320, 100*y/480);
		pw.addstar(125*x/320, 200*y/480);
		pw.addstar(160*x/320, 340*y/480);
		
		pw.addbubble(160*x/320, 340*y/480);
		pw.adddoor(180*x/320, 440*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}