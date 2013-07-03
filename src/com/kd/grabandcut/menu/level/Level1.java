package com.kd.grabandcut.menu.level;

public class Level1 extends Level {

	@Override
	public void init(PhysicsWorld pw) {
		super.init(pw);
		int y = pw.World_H;
		int x = pw.World_W;
		pw.setball(x/2,3*y/8);
		pw.addrope(0,4*y/96);
		//pw.addrope(-11,-20);5
		//pw.addrope(11,15);
		//pw.addrope(-15,-13);
		pw.addstar(x/2, 25*y/48);
		pw.addstar(x/2, 5*y/8);
		pw.addstar(x/2, 35*y/48);
		//pw.addstar(60, 120);
		//pw.addcutter((int)x/2,0,0,10);
		pw.adddoor(x/2, 15*y/16);
//		pw.addtimestar(x/2, 10, 80);
		//pw.addcutter(10, 40, 20, 40);
	//	pw.addtimestar(160, 50, 150);//.addrope(0,6*y/96);
	}
	
	@Override
	public void finish() {
		// TODO write things that are to be executed when the level is finished

	}
}
