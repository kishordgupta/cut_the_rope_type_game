package com.kd.grabandcut.menu.level;

public abstract class Level {
	protected PhysicsWorld pw;
	
	public abstract void finish();
	public void init(PhysicsWorld pw){
		this.pw = pw;
	}
	
}
