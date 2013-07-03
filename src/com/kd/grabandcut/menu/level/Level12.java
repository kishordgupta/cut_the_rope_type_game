	package com.kd.grabandcut.menu.level;

public class Level12 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
		pw.setball(160*x/320,200*y/480);
		
		pw.addrope(25*x/320,0*y/480);
		pw.addrope(-25*x/320,0*y/480);
		//pw.addrope(8*x,-30*y);
		//pw.addrope(-25*x,-40*y);
		
		pw.addstar(100*x/320, 260*y/480);
		pw.addstar(25*x/320, 310*y/480);
		pw.addstar(140*x/320, 360*y/480);
		
		pw.adddoormover(100*x/320,310*y/480, 2*x/320, 0);
		//pw.addropecutter(240*x, 120*y, 5*x,20*y);
		
//		pw.addbubble(160*x/320, 240*y/480);
		pw.adddoor(280*x/320, 440*y/480);
		
		
		
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
