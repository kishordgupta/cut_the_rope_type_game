package com.kd.grabandcut.menu.level;

public class Level18 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
	
		
		pw.setball(160*x/320,110*y/480);
		
		pw.addrope(-25*x/320,0*y/480);
		pw.addrope(25*x/320,0*y/480);
		pw.addrope(-25*x/320,-15*y/480);
		pw.addrope(25*x/320,-15*y/480);		
		pw.addrope(-25*x/320,-30*y/480);
		pw.addrope(25*x/320,-30*y/480);
		
		pw.addstar(160*x/320, 230*y/480);
		pw.addstar(160*x/320, 310*y/480);
		pw.addstar(70*x/320, 340*y/480);
		
		pw.addcutter(90*x/320, 120*y/480, 60*x/320, 30*y/480,0,0);
		pw.addcutter(210*x/320, 120*y/480, 60*x/320, 30*y/480,0,0);
		pw.addcutter(90*x/320, 180*y/480, 60*x/320, 30*y/480,0,0);
		pw.addcutter(210*x/320, 180*y/480, 60*x/320, 30*y/480,0,0);
		pw.addcutter(90*x/320, 240*y/480, 60*x/320, 30*y/480,0,0);
		pw.addcutter(210*x/320, 240*y/480, 60*x/320, 30*y/480,0,0);
		//pw.addcutter(200*x, 100*y, 75*x, 2*y,0,0);
		//pw.addcutter(200*x, 230*y, 75*x, 2*y,0,0);
		
		pw.addbubble(150*x/320, 400*y/480);
		
		pw.adddoor(160*x/320, 30*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}