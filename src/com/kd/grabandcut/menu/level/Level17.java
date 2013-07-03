package com.kd.grabandcut.menu.level;

public class Level17 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		
		int y = pw.World_H;
		int x = pw.World_W;
		
		pw.setball(260*x/320,190*y/480);
		pw.addrope(0*x/320,15*y/480);		
		pw.addrope(20*x/320,7*y/480);
		
		pw.adddoormover(100*x/320, 190*y/480, -3*x/320, 0);
		pw.adddoormover(100*x/320, 50*y/480, 0*x/320, 0);		
		
		pw.addcutter(17*x/320, 300*y/480, 60*x/320, 30*y/480,0,0);		

		pw.addstar(130*x/320, 240*y/480);
		pw.addstar(100*x/320, 320*y/480);
		pw.addstar(100*x/320, 150*y/480);
		pw.addbubble(100*x/320, 320*y/480);
		pw.adddoor(210*x/320, 400*y/480);	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
