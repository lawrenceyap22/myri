package com.epicnoobz.myri.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.epicnoobz.myri.MyriGame;

public class AbstractScreen implements Screen {

	public static final int GAME_VIEWPORT_WIDTH = 1920;
	public static final int GAME_VIEWPORT_HEIGHT = 1080;

	protected final MyriGame game;
	protected final Stage stage;

	private BitmapFont font;
	private SpriteBatch batch;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;

	public AbstractScreen(MyriGame game) {
		this.game = game;
		this.stage = new Stage(GAME_VIEWPORT_WIDTH, GAME_VIEWPORT_HEIGHT, true);
	}

	protected boolean isGameScreen() {
		return false;
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

	public SpriteBatch getBatch() {
		if (batch == null) {
			batch = new SpriteBatch();
		}
		return batch;
	}

	public BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont();
		}
		return font;
	}

	public TextureAtlas getAtlas() {
		if (atlas == null) {
			atlas = new TextureAtlas(Gdx.files.internal("image-atlases/screens-images-packed.atlas"));
		}
		return atlas;
	}

	protected Skin getSkin() {
		if (skin == null) {
			FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
			skin = new Skin(skinFile);
		}
		return skin;
	}

	protected Table getTable() {
		if (table == null) {
			table = new Table(getSkin());
			table.setFillParent(true);
			if (MyriGame.DEV_MODE) {
				table.debug();
			}
			stage.addActor(table);
		}
		return table;
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(MyriGame.TAG, "Resizing screen: " + getName() + " to: "
				+ width + " x " + height);
		stage.setViewport(width, height, true);

	}

	@Override
	public void show() {
		Gdx.app.log(MyriGame.TAG, "Showing screen: " + getName());
	}

	@Override
	public void hide() {
		Gdx.app.log(MyriGame.TAG, "Hiding screen: " + getName());
	}

	@Override
	public void pause() {
		Gdx.app.log(MyriGame.TAG, "Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(MyriGame.TAG, "Resuming screen: " + getName());
	}

	@Override
	public void dispose() {
		Gdx.app.log(MyriGame.TAG, "Disposing screen: " + getName());
		if (batch != null)
			batch.dispose();
		if (font != null)
			font.dispose();
		if (atlas != null)
			atlas.dispose();
		if (skin != null)
			skin.dispose();
		if (stage != null)
			stage.dispose();
	}

}
