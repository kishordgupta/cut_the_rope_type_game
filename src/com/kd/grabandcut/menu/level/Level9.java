package com.kd.grabandcut.menu.level;

public class Level9 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
		pw.setball(50*x/320,165*y/480);
		
		pw.addrope(0,20*y/480);
		pw.addrope(-30*x/320,20*y/480);
		pw.addrope(-50*x/320,20*y/480);
		pw.addrope(0*x/320,-20*y/480);
		
		pw.addstar(185*x/320, 260*y/480);
		pw.addstar(140*x/320, 220*y/480);
		pw.addstar(250*x/320, 320*y/480);
		pw.addcutter(210*x/320, 200*y/480, 70*x/320, 35*y/480,0,0);
		pw.addcutter(35*x/320, 285*y/480, 70*x/320, 35*y/480,0,0);
		
		//pw.addbubble(160*x, 440*y);
		pw.adddoor(280*x/320, 400*y/480);
		
		
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
