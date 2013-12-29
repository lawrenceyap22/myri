package com.epicnoobz.myri.domain.units;

import java.util.ArrayList;
import java.util.List;

import com.epicnoobz.myri.domain.Skill;
import com.epicnoobz.myri.domain.UnitData;


public abstract class Champion extends UnitData {

	private List<Skill> skills;
	
	public Champion(double movSpeed, double damage,
			double range, double health, double atkSpeed, double spawnTime) {
		super(movSpeed, damage, range, health, atkSpeed, spawnTime);
		skills = new ArrayList<Skill>();
	}

	public List<Skill> getSkills() {
		return skills;
	}
	
	public void addSkill(Skill skill){
		skills.add(skill);
	}

}