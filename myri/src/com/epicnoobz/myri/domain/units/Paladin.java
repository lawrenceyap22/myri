package com.epicnoobz.myri.domain.units;

import com.epicnoobz.myri.domain.Champion;
import com.epicnoobz.myri.domain.Skill;

public class Paladin extends Champion {

	public Paladin() {
		super();
		setMoveSpeed(120);
		setDamage(3);
		setRange(3);
		setHealth(1);
		setAtkSpeed(1.2);
		setSpawnTime(45);
		addSkill(Skill.HEAL);
		addSkill(Skill.DEVOTION);
		addSkill(Skill.SHIELD_CHARGE);
		addSkill(Skill.VANGUARD);
	}

}
