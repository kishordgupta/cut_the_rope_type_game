package com.kd.grabandcut.menu.level;

public class Level8 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
		pw.setball(145*x/320,340*y/480);
		
		pw.addrope(5*x/320,45*y/480);
		pw.addrope(-25*x/320,15*y/480);
		pw.addrope(15*x/320,-15*y/480);
		//pw.addrope(25*x,(20)*y);
		
		pw.addstar(170*x/320, 410*y/480);
		pw.addstar(170*x/320, 140*y/480);
		pw.addstar(90*x/320, 280*y/480);
		
		pw.addbubble(170*x/320, 420*y/480);
		pw.adddoor(190*x/320, 50*y/480);
		
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}