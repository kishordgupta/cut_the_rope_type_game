package com.kd.grabandcut.menu.level;

public class Level13 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
		pw.setball(220*x/320,100*y/480);
		
		pw.addrope(15*x/320,10*y/480);
		pw.addrope(2*x/320,-20*y/480);
		//pw.addrope(8*x,-30*y);
		//pw.addrope(-25*x,-40*y);
		
		pw.addstar(140*x/320, 260*y/480);
		pw.addstar(140*x/320, 310*y/480);
		pw.addstar(260*x/320, 340*y/480);
		pw.addcutter(80*x/320, 140*y/480, 60*x/320, 30*y/480,0,0);
		pw.adddoormover(160*x/320,180*y/480, 5*x/320, 0);
		
		
		//pw.addbubble(160*x, 240*y);
		pw.adddoor(280*x/320, 440*y/480);
		
		
		
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
