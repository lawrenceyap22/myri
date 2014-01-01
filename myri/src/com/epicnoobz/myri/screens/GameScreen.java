package com.epicnoobz.myri.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicnoobz.myri.MyriGame;
import com.epicnoobz.myri.domain.GameTouchpad;
import com.epicnoobz.myri.domain.Level;
import com.epicnoobz.myri.domain.units.Paladin;
import com.epicnoobz.myri.domain.units2d.Champion2D;
import com.epicnoobz.myri.domain.units2d.Paladin2D;

public class GameScreen extends AbstractScreen {

	private static Level level;
	private static Camera camera;

	private GameTouchpad touchpad;
	private SpriteBatch batch;
	private Champion2D champion;

	public GameScreen(MyriGame game, int levelId) {
		super(game);
		level = game.getLevelManager().findLevelById(levelId);
		camera = new OrthographicCamera(GAME_VIEWPORT_WIDTH,
				GAME_VIEWPORT_HEIGHT);
		batch = getBatch();
	}

	public static synchronized Camera getCamera() {
		return camera;
	}

	public static int getLevelSize() {
		return level.getForegroundWidth();
	}

	@Override
	protected boolean isGameScreen() {
		return true;
	}

	@Override
	public void show() {
		super.show();
		touchpad = GameTouchpad.getInstance();
		stage.addActor(touchpad.getTouchpad());
		champion = Paladin2D.getPaladin(new Paladin()); //paladin should be from profile(saved)
		champion.setOrigin(0, 0);
		champion.setPosition(GAME_VIEWPORT_WIDTH / 2 - champion.getWidth() / 2,
				GAME_VIEWPORT_HEIGHT / 2 - champion.getHeight() - 140);
		stage.addActor(champion);

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
		stage.act(delta);
	}

}
