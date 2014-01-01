package com.epicnoobz.myri.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Champion extends Unit {

	private List<Skill> skills;
	private Skill activeSkill;
	
	public Champion() {
		skills = new ArrayList<Skill>();
		activeSkill = null;
	}

	public List<Skill> getSkills() {
		return skills;
	}
	
	public void addSkill(Skill skill){
		skills.add(skill);
	}
	
	public void setActiveSkill(Skill skill){
		if(skills.contains(skill))
			activeSkill = skill;
		else
			activeSkill = null;
	}
	
	public Skill getActiveSkill(){
		return activeSkill;
	}


}