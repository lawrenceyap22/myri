package com.epicnoobz.myri.domain;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

public abstract class Champion extends Unit {

	private Map<String, Skill> skills;
	private Skill activeSkill;
	
	public Champion() {
		skills = new TreeMap<String,Skill>();
		activeSkill = null;
	}

	public List<Skill> getSkills() {
		return new ArrayList<Skill>(skills.values());
	}
	
	public void addSkill(Skill skill){
		skills.put(skill.toString(), skill);
	}
	
	public void setActiveSkill(String key){
		if(skills.containsKey(key))
			activeSkill = skills.get(key);
		else
			activeSkill = null;
	}
	
	public Skill getActiveSkill(){
		return activeSkill;
	}


}