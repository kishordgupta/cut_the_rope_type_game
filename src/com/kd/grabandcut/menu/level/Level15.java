package com.kd.grabandcut.menu.level;

public class Level15 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		
		
	
		
		pw.setball(160*x/320,100*y/480);
		
		pw.addrope(-25*x/320,5*y/480);
		pw.addrope(25*x/320,5*y/480);
		pw.addrope(25*x/320,-15*y/480);
		pw.addrope(-25*x/320,(-15)*y/480);
		
		pw.addstar(160*x/320, 40*y/480);
		pw.addstar(160*x/320, 250*y/480);
		pw.addstar(160*x/320, 320*y/480);
		
		pw.addcutter(87*x/320, 100*y/480, 75*x/320, 35*y/480,0,0);
		pw.addcutter(87*x/320, 230*y/480, 75*x/320, 35*y/480,0,0);
		pw.addcutter(200*x/320, 100*y/480, 75*x/320, 35*y/480,0,0);
		pw.addcutter(200*x/320, 230*y/480, 75*x/320, 35*y/480,0,0);
		
		pw.addbubble(160*x/320, 320*y/480);
		
		pw.adddoor(160*x/320, 440*y/480);
	
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
