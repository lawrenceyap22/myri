package com.epicnoobz.myri.domain.units2d;

import com.epicnoobz.myri.domain.Champion;

public class Paladin2D extends Champion2D{

	private static Paladin2D instance = null;
	
	public Paladin2D(Champion champion) {
		super(champion);
		setIdleAnimation(1, 1);
		setMoveAnimation(1, 6);
	}
	
	public static synchronized Paladin2D getPaladin(Champion champion){
		if(instance == null){
			instance = new Paladin2D(champion);
		}
		return instance;
	}

}
