package com.epicnoobz.myri.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.myri.MyriGame;
import com.epicnoobz.myri.domain.Level;

public class GameScreen extends AbstractScreen {

	private class GameTouchpad {
		private TouchpadStyle touchpadStyle;
		private Skin touchpadSkin;
		private Drawable touchBackground;
		private Drawable touchKnob;
		private Touchpad touchpad;

		public GameTouchpad() {
			touchpadSkin = new Skin();
			touchpadSkin.add("touchBackground", new Texture(
					"skin/touchpadBackground.png"));
			touchpadSkin.add("touchKnob", new Texture("skin/touchpadKnob.png"));
			touchpadStyle = new TouchpadStyle();
			touchBackground = touchpadSkin.getDrawable("touchBackground");
			touchKnob = touchpadSkin.getDrawable("touchKnob");
			touchpadStyle.background = touchBackground;
			touchpadStyle.knob = touchKnob;
			touchpad = new Touchpad(10, touchpadStyle);
			touchpad.setBounds(15, 15, 200, 200);
		}

		public Touchpad getTouchpad() {
			return touchpad;
		}
	}

	private final Level level;

	private OrthographicCamera camera;
	private GameTouchpad touchpad;
	private SpriteBatch batch;
	private Image hero;

	public GameScreen(MyriGame game, int levelId) {
		super(game);
		level = game.getLevelManager().findLevelById(levelId);
		batch = getBatch();
	}

	@Override
	protected boolean isGameScreen() {
		return true;
	}

	@Override
	public void show() {
		super.show();
		camera = new OrthographicCamera(GAME_VIEWPORT_WIDTH,
				GAME_VIEWPORT_HEIGHT);
		touchpad = new GameTouchpad();
		stage.addActor(touchpad.getTouchpad());

		TextureAtlas atlas = new TextureAtlas(
				Gdx.files
						.internal("image-atlases/champions-images-packed.atlas"));
		AtlasRegion heroRegion = atlas.findRegion("Sample");
		Drawable heroDrawable = new TextureRegionDrawable(heroRegion);
		hero = new Image(heroDrawable);
		hero.setPosition(10, GAME_VIEWPORT_HEIGHT / 2 - hero.getHeight() - 70);
		hero.setOrigin(0, 0);
		stage.addActor(hero);

	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		level.render(delta, batch, camera);
		batch.end();
		stage.draw();
	}

	private void update(float delta) {
		moveChampion();
		stage.act(delta);
	}

	private void moveChampion() {
		double x = Math.abs(touchpad.getTouchpad().getKnobPercentX());
		double y = Math.abs(touchpad.getTouchpad().getKnobPercentY());
		double rad = Math.atan(y / x);
		double degrees = Math.toDegrees(rad);

		if (degrees <= 65) {
			if (touchpad.getTouchpad().getKnobPercentX() > 0) { // move right
				if (camera.position.x == 0
						&& hero.getX() < GAME_VIEWPORT_WIDTH / 2
								- hero.getWidth() / 2) {
					hero.setX(Math.min(
							hero.getX() + Gdx.graphics.getDeltaTime() * 1000,
							GAME_VIEWPORT_WIDTH / 2 - hero.getWidth() / 2));
				} else if (camera.position.x == level.getForegroundWidth()
						- GAME_VIEWPORT_WIDTH
						&& hero.getX() >= GAME_VIEWPORT_WIDTH / 2
								- hero.getWidth() / 2) {
					hero.setX(Math.min(
							hero.getX() + Gdx.graphics.getDeltaTime() * 1000,
							GAME_VIEWPORT_WIDTH - hero.getWidth()));
				} else {
					camera.position.x = Math.min(camera.position.x
							+ Gdx.graphics.getDeltaTime() * 1000,
							level.getForegroundWidth() - GAME_VIEWPORT_WIDTH);
				}
			} else { // move left
				if (camera.position.x == level.getForegroundWidth()
						- GAME_VIEWPORT_WIDTH
						&& hero.getX() > GAME_VIEWPORT_WIDTH / 2
								- hero.getWidth() / 2) {
					hero.setX(Math.max(
							hero.getX() - Gdx.graphics.getDeltaTime() * 1000,
							GAME_VIEWPORT_WIDTH / 2 - hero.getWidth() / 2));
				} else if (camera.position.x == 0
						&& hero.getX() <= GAME_VIEWPORT_WIDTH / 2
								- hero.getWidth() / 2) {
					hero.setX(Math.max(
							hero.getX() - Gdx.graphics.getDeltaTime() * 1000, 0));
				} else {
					camera.position.x = Math.max(camera.position.x
							- Gdx.graphics.getDeltaTime() * 1000, 0);
				}
			}

		}
	}

}
