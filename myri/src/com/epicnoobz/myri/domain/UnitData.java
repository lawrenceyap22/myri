package com.epicnoobz.myri.domain;


public abstract class UnitData {

	private double x;
	private double y;
	private double movSpeed;
	private double damage;
	private double range;
	private double health;
	private double atkSpeed;
	private double spawnTime;
	private String texture;
	
	public UnitData(double movSpeed, double damage, double range,
			double health, double atkSpeed, double spawnTime) {
		this.x = 0;
		this.y = 0;
		this.movSpeed = movSpeed;
		this.damage = damage;
		this.range = range;
		this.health = health;
		this.atkSpeed = atkSpeed;
		this.spawnTime = spawnTime;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the movSpeed
	 */
	public double getMovSpeed() {
		return movSpeed;
	}

	/**
	 * @param movSpeed
	 *            the movSpeed to set
	 */
	public void setMovSpeed(double movSpeed) {
		this.movSpeed = movSpeed;
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
	
	public String getTexture(){
		return texture;
	}
	
	public void setTexture(String texture){
		this.texture = texture;
	}

}
