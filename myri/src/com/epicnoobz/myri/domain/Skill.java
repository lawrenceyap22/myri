package com.epicnoobz.myri.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public enum Skill {
	HEAL(20, 3, 0, 0),
	DEVOTION(15, 3, 0, 5), 
	SHIELD_CHARGE(7, 1, 4, 0), 
	VANGUARD(30, 0, 0, 7),
	RUNE_BOLT(2, 7, 4, 0),
	CHAIN_LIGHTNING(7, 7, 6, 0),
	QUICK_SAND(20, 5, 0, 7),
	ARMAGEDDON(45, 100, 10, 0),
	BERSERK(8, 0, 0, 3),
	BATTLECRY(15, 3, 0, 5),
	BAD_AXE(10, 5, 6, 0),
	BLOODLUST(23, 0, 0, 8);
	
	private int level;
	private double cd;
	private double range;
	private double damage;
	private double duration;
	private static TextureAtlas atlas;

	private Skill(double cd, double range, double damage, double duration) {
		level = 1;
		this.cd = cd;
		this.range = range;
		this.damage = damage;
		this.duration = duration;
	}
	
	private static TextureAtlas getAtlas(){
		if(atlas == null){
			atlas = new TextureAtlas(Gdx.files.internal("image-atlases/skills-images-packed.atlas"));
		}
		return atlas;
	}
	
	public static Image getSkillIcon(Skill skill){
		AtlasRegion region = getAtlas().findRegion(skill.toString().toLowerCase());
		Drawable drawable = new TextureRegionDrawable(region);
		return new Image(drawable);	
	}

	/**
	 * @return the cool down
	 */
	public double getCd() {
		return cd;
	}

	/**
	 * @return the range
	 */
	public double getRange() {
		return range * level;
	}

	/**
	 * @return the damage
	 */
	public double getDamage() {
		return damage * level;
	}

	/**
	 * @return the duration
	 */
	public double getDuration() {
		return duration;
	}

	public void levelUp() {
		level++;
	}

	public int getLevel() {
		return level;
	}
	
	@Override
	public String toString(){
		return super.toString().toLowerCase();
	}

}
