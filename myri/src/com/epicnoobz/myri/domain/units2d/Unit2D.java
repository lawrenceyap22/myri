package com.epicnoobz.myri.domain.units2d;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.myri.domain.Unit;
import com.epicnoobz.myri.domain.Unit.UnitAction;

public abstract class Unit2D extends Image {
	private static TextureAtlas atlas;
	protected float stateTime;
	protected Map<TextureRegion, Drawable> animationDrawables;

	private Animation idleAnimation;
	private Animation moveAnimation;
	private Animation attackAnimation;
	private Unit unit;

	public Unit2D(Unit unit) {
		super.setTouchable(Touchable.disabled);
		this.unit = unit;
		animationDrawables = new HashMap<TextureRegion, Drawable>();
	}

	protected synchronized TextureAtlas getAtlas() {
		if (atlas == null) {
			atlas = new TextureAtlas(
					Gdx.files
							.internal("image-atlases/units-images-packed.atlas"));
		}
		return atlas;
	}

	protected Animation setAnimation(String type, int frameRows,
			int frameColumns) {
		AtlasRegion region = getAtlas().findRegion(
				unit.toString().toLowerCase() + "/" + type);
		TextureRegion[][] tmp = region.split(region.getRegionWidth()
				/ frameColumns, region.getRegionHeight() / frameRows);
		TextureRegion[] frames = new TextureRegion[frameColumns * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameColumns; j++) {
				frames[index] = tmp[i][j];
				animationDrawables.put(frames[index],
						new TextureRegionDrawable(frames[index++]));
			}
		}
		// set default image
		if (type.equals(UnitAction.IDLE.toString()) && getDrawable() == null){
			setDrawable(animationDrawables.get(frames[0]));
			setWidth(frames[0].getRegionWidth());
			setHeight(frames[0].getRegionHeight());
		}
		return new Animation(1.0f / frames.length, frames);
	}

	protected void setAttackAnimation(int frameRows, int frameColumns) {
		attackAnimation = setAnimation(UnitAction.ATTACK.toString(), frameRows,
				frameColumns);
	}

	protected void setMoveAnimation(int frameRows, int frameColumns) {
		moveAnimation = setAnimation(UnitAction.MOVE.toString(), frameRows,
				frameColumns);
	}

	protected void setIdleAnimation(int frameRows, int frameColumns) {
		idleAnimation = setAnimation(UnitAction.IDLE.toString(), frameRows,
				frameColumns);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		move(delta);
		attack(delta);
		animate(delta);
	}

	public void move(float delta) {
	}

	public void attack(float delta) {

	}

	public void animate(float delta) {
		TextureRegion currentFrame = null;
		switch (unit.getCurrentAction()) {
		case IDLE:
			currentFrame = idleAnimation.getKeyFrame(stateTime += delta, true);
			break;
		case ATTACK:
			currentFrame = attackAnimation
					.getKeyFrame(stateTime += delta, true);
			break;
		case MOVE:
			currentFrame = moveAnimation.getKeyFrame(stateTime += delta, true);
			break;
		}
		setDrawable(animationDrawables.get(currentFrame));
	}

	public Unit getUnit() {
		return unit;
	}
}
