package com.epicnoobz.myri.domain.units;

import com.epicnoobz.myri.domain.Skill;


public class Paladin extends Champion {
	public Paladin() {
		super(120,3,3,1,1.2,45);
		addSkill(Skill.HEAL);
		addSkill(Skill.DEVOTION);
		addSkill(Skill.SHIELD_CHARGE);
		addSkill(Skill.VANGUARD);
	}

}
