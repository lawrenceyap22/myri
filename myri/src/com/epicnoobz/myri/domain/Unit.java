package com.epicnoobz.myri.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Unit {

	public enum UnitAction {
		IDLE, MOVE, ATTACK;

		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}

	private UnitAction action;
	private double moveSpeed;
	private double damage;
	private double range;
	private double health;
	private double atkSpeed;
	private double spawnTime;
	private boolean alive;
	
	protected TextureRegion currentFrame;
	
	public Unit(){
		action = UnitAction.IDLE;
		alive = true;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public void setAction(UnitAction action){
		this.action = action;
	}
	
	public UnitAction getCurrentAction(){
		return action;
	}
	
	/**
	 * @return the moveSpeed
	 */
	public double getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * @param moveSpeed
	 *            the moveSpeed to set
	 */
	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * @return the damage
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * @param damage
	 *            the damage to set
	 */
	public void setDamage(double damage) {
		this.damage = damage;
	}

	/**
	 * @return the range
	 */
	public double getRange() {
		return range;
	}

	/**
	 * @param range
	 *            the range to set
	 */
	public void setRange(double range) {
		this.range = range;
	}

	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public void setHealth(double health) {
		this.health = health;
	}

	/**
	 * @return the atkSpeed
	 */
	public double getAtkSpeed() {
		return atkSpeed;
	}

	/**
	 * @param atkSpeed
	 *            the atkSpeed to set
	 */
	public void setAtkSpeed(double atkSpeed) {
		this.atkSpeed = atkSpeed;
	}

	/**
	 * @return the spawnTime
	 */
	public double getSpawnTime() {
		return spawnTime;
	}

	/**
	 * @param spawnTime
	 *            the spawnTime to set
	 */
	public void setSpawnTime(double spawnTime) {
		this.spawnTime = spawnTime;
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}

}
