package com.epicnoobz.myri.domain.units2d;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.epicnoobz.myri.domain.Champion;
import com.epicnoobz.myri.domain.GameTouchpad;
import com.epicnoobz.myri.domain.Skill;
import com.epicnoobz.myri.domain.Unit.UnitAction;
import com.epicnoobz.myri.screens.GameScreen;

public abstract class Champion2D extends Unit2D {
	private Map<Skill, Animation> skillAnimations;
	private Champion champion;
	private static TextureAtlas atlas;

	public Champion2D(Champion champion) {
		super(champion);
		this.champion = champion;
	}

	@Override
	protected synchronized TextureAtlas getAtlas() {
		if (atlas == null) {
			atlas = new TextureAtlas(
					Gdx.files
							.internal("image-atlases/champions-images-packed.atlas"));
		}
		return atlas;
	}

	public void addSkill(Skill skill, int frameRows, int frameColumns) {
		Animation animation = setAnimation(skill.toString(), frameRows,
				frameColumns);
		skillAnimations.put(skill, animation);
	}

	@Override
	public void move(float delta) {
		GameTouchpad touchpad = GameTouchpad.getInstance();
		Camera camera = GameScreen.getCamera();
		int levelSize = GameScreen.getLevelSize();

		if (touchpad.getDirectionX() > 0) { // move right
			champion.setAction(UnitAction.MOVE);
			if (camera.position.x == 0
					&& getX() < camera.viewportWidth / 2 - getWidth() / 2) {
				setX(Math.min(getX() + delta * 1000, camera.viewportWidth / 2
						- getWidth() / 2));
			} else if (camera.position.x == levelSize - camera.viewportWidth
					&& getX() >= camera.viewportWidth / 2 - getWidth() / 2) {
				setX(Math.min(getX() + delta * 1000, camera.viewportWidth
						- getWidth()));
			} else {
				camera.position.x = Math.min(camera.position.x + delta * 1000,
						levelSize - camera.viewportWidth);
			}
		} else if (touchpad.getDirectionX() < 0) { // move left
			champion.setAction(UnitAction.MOVE);
			if (camera.position.x == levelSize - camera.viewportWidth
					&& getX() > camera.viewportWidth / 2 - getWidth() / 2) {
				setX(Math.max(getX() - delta * 1000, camera.viewportWidth / 2
						- getWidth() / 2));
			} else if (camera.position.x == 0
					&& getX() <= camera.viewportWidth / 2 - getWidth() / 2) {
				setX(Math.max(getX() - delta * 1000, 0));
			} else {
				camera.position.x = Math.max(camera.position.x - delta * 1000,
						0);
			}
		} else {
			champion.setAction(UnitAction.IDLE);
		}
	}

	@Override
	public void attack(float delta) {

	}

	@Override
	public void animate(float delta) {
		TextureRegion currentFrame = null;
		Skill activeSkill = champion.getActiveSkill();
		if (activeSkill != null) {
			currentFrame = skillAnimations.get(activeSkill).getKeyFrame(
					stateTime += delta);
			setDrawable(animationDrawables.get(currentFrame));
		} else {
			super.animate(delta);
		}
	}

}
