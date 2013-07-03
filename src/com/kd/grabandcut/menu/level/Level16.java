package com.kd.grabandcut.menu.level;

public class Level16 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
	
		
		pw.setball(150*x/320,140*y/480);
		
		pw.addrope(-25*x/320,20*y/480);
		pw.addrope(25*x/320,15*y/480);
		//pw.addrope(25*x,-15*y);
		pw.addrope(-15*x/320,(-25)*y/480);
		
		pw.addstar(70*x/320, 210*y/480);
		pw.addstar(210*x/320, 80*y/480);
		pw.addstar(40*x/320, 350*y/480);
		
		pw.addropecutter(260*x/320, 125*y/480, 25*x/320, 50*y/480);
		pw.addropecutter(245*x/320, 230*y/480, 25*x/320, 50*y/480);
		//pw.addcutter(200*x, 100*y, 75*x, 2*y,0,0);
		//pw.addcutter(200*x, 230*y, 75*x, 2*y,0,0);
		//pw.add
		
		pw.addbubble(70*x/320, 210*y/480);
		
		pw.adddoor(30*x/320, 440*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
