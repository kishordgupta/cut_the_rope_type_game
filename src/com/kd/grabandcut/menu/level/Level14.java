package com.kd.grabandcut.menu.level;

public class Level14 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
	
		
		pw.setball(180*x/320,130*y/480);
		
		pw.addrope(-20*x/320,5*y/480);
		pw.addrope(5*x/320,10*y/480);
		pw.addrope(5*x/320,-17*y/480);
		//pw.addrope(25*x,(20)*y);
		
		pw.addstar(280*x/320, 250*y/480);
		pw.addstar(170*x/320, 350*y/480);
		pw.addstar(80*x/320, 140*y/480);
		
		pw.addbubble(80*x/320, 340*y/480);
		
		pw.adddoor(60*x/320, 440*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}