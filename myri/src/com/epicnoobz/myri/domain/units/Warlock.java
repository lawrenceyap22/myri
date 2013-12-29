package com.epicnoobz.myri.domain.units;

import com.epicnoobz.myri.domain.Skill;


public class Warlock extends Champion {
	public Warlock() {
		super(80,3.5,4,2,0.9,45);
		addSkill(Skill.RUNE_BOLT);
		addSkill(Skill.CHAIN_LIGHTNING);
		addSkill(Skill.QUICK_SAND);
		addSkill(Skill.ARMAGEDDON);
	}

}
