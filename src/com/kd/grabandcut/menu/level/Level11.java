package com.kd.grabandcut.menu.level;

public class Level11 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
		pw.setball(140*x/320,100*y/480);
		
		pw.addrope(20*x/320,0*y/480);
		pw.addrope(-20*x/320,0*y/480);
		pw.addrope(8*x/320,-30*y/480);
		pw.addrope(-25*x/320,-40*y/480);
		
		pw.addstar(190*x/320, 260*y/480);
		pw.addstar(160*x/320, 30*y/480);
		pw.addstar(180*x/320, 380*y/480);
		//pw.addcutter(80*x/320, 140*y/480, 60*x/320, 30*y/480,0,0);
		pw.addcutter(35*x/320, 150*y/480, 60*x/320, 30*y/480,0,0);
		pw.addropecutter(200*x/320, 120*y/480, 30*x/320,50*y/480);
		
		pw.addbubble(190*x/320, 260*y/480);
		pw.adddoor(180*x/320, 440*y/480);
		
		
		
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
