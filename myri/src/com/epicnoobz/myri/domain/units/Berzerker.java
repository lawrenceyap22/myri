package com.epicnoobz.myri.domain.units;

import com.epicnoobz.myri.domain.Skill;


public class Berzerker extends Champion {

	public Berzerker() {
		super(100,4,3.5,2,1.5,45);
		addSkill(Skill.BERSERK);
		addSkill(Skill.BATTLECRY);
		addSkill(Skill.BAD_AXE);
		addSkill(Skill.BLOODLUST);
	}
}
