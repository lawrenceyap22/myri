package com.epicnoobz.myri.domain;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.epicnoobz.myri.MyriGame;
import com.epicnoobz.myri.screens.AbstractScreen;
import com.epicnoobz.myri.screens.graphics.ParallaxBackground;
import com.epicnoobz.myri.screens.graphics.ParallaxLayer;

public class Level implements Disposable{
	private final int id;
	private String name;
	private boolean completed;
	private ParallaxBackground parallaxBackground;
	private List<Texture> foreground, backgroundLayer1, backgroundLayer2,
			backgroundLayer3;
	private int foregroundWidth, backgroundLayer1Width, backgroundLayer2Width,
			backgroundLayer3Width = 0;
	private Texture background;
	private ParallaxLayer[] layers;

	public Level(int id) {
		this.id = id;
		foreground = new ArrayList<Texture>();
		backgroundLayer1 = new ArrayList<Texture>();
		backgroundLayer2 = new ArrayList<Texture>();
		backgroundLayer3 = new ArrayList<Texture>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed
	 *            the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public void addForeground(String layerName) {
		Texture texture = new Texture(Gdx.files.internal("levels/" + layerName));
		foregroundWidth += texture.getWidth();
		foreground.add(texture);
	}

	public void addBackgroundLayer1(String layerName) {
		Texture texture = new Texture(Gdx.files.internal("levels/" + layerName));
		backgroundLayer1Width += texture.getWidth();
		backgroundLayer1.add(texture);
	}

	public void addBackgroundLayer2(String layerName) {
		Texture texture = new Texture(Gdx.files.internal("levels/" + layerName));
		backgroundLayer2Width += texture.getWidth();
		backgroundLayer2.add(texture);

	}

	public void addBackgroundLayer3(String layerName) {
		Texture texture = new Texture(Gdx.files.internal("levels/" + layerName));
		backgroundLayer3Width += texture.getWidth();
		backgroundLayer3.add(texture);
	}

	public void setBackground(String layerName) {
		background = new Texture(Gdx.files.internal("levels/" + layerName));
	}

	public void setParallaxBackground() {
		int arrSize = foreground.size() + backgroundLayer1.size()
				+ backgroundLayer2.size() + backgroundLayer3.size();
		layers = new ParallaxLayer[arrSize];

		// parallax layer for 3rd background layer
		for (int i = 0; i < backgroundLayer3.size(); i++) {
			if (i == 0) {
				layers[i] = new ParallaxLayer(
						backgroundLayer3.get(i),
						new Vector2(
								(backgroundLayer3Width * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f)
										/ (foregroundWidth * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f),
								0), new Vector2(), new Vector2());
			} else {
				layers[i] = new ParallaxLayer(
						backgroundLayer3.get(i),
						new Vector2(
								(backgroundLayer3Width * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f)
										/ (foregroundWidth * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f),
								0), new Vector2(backgroundLayer3.get(i - 1)
								.getWidth(), 0), new Vector2());
			}

		}

		// parallax layer for 2nd background layer
		for (int i = 0; i < backgroundLayer2.size(); i++) {
			if (i == 0) {
				layers[i + backgroundLayer3.size()] = new ParallaxLayer(
						backgroundLayer2.get(i),
						new Vector2(
								(backgroundLayer2Width * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f)
										/ (foregroundWidth * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f),
								0), new Vector2(), new Vector2());
			} else {
				layers[i + backgroundLayer3.size()] = new ParallaxLayer(
						backgroundLayer2.get(i),
						new Vector2(
								(backgroundLayer2Width * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f)
										/ (foregroundWidth * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f),
								0), new Vector2(backgroundLayer2.get(i - 1)
								.getWidth(), 0), new Vector2());
			}
		}

		// parallax layer for 1st background layer
		for (int i = 0; i < backgroundLayer1.size(); i++) {
			if (i == 0) {
				layers[i + backgroundLayer3.size() + backgroundLayer2.size()] = new ParallaxLayer(
						backgroundLayer1.get(i),
						new Vector2(
								(backgroundLayer1Width * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f)
										/ (foregroundWidth * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f),
								0), new Vector2(), new Vector2());
			} else {
				layers[i + backgroundLayer3.size() + backgroundLayer2.size()] = new ParallaxLayer(
						backgroundLayer1.get(i),
						new Vector2(
								(backgroundLayer1Width * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f)
										/ (foregroundWidth * 1.0f - AbstractScreen.GAME_VIEWPORT_WIDTH * 1.0f),
								0), new Vector2(backgroundLayer1.get(i - 1)
								.getWidth(), 0), new Vector2());
			}
		}

		// parallax layer for foreground layer
		for (int i = 0; i < foreground.size(); i++) {
			if (i == 0) {
				layers[i + backgroundLayer3.size() + backgroundLayer2.size()
						+ backgroundLayer1.size()] = new ParallaxLayer(
						foreground.get(i), new Vector2(1, 0), new Vector2(),
						new Vector2());
			} else {
				layers[i + backgroundLayer3.size() + backgroundLayer2.size()
						+ backgroundLayer1.size()] = new ParallaxLayer(
						foreground.get(i), new Vector2(1, 0), new Vector2(
								foreground.get(i - 1).getWidth(), 0),
						new Vector2());
			}
		}

		parallaxBackground = new ParallaxBackground(layers);

	}

	public void render(float delta, SpriteBatch spriteBatch, Camera camera) {
		spriteBatch.draw(background, -AbstractScreen.GAME_VIEWPORT_WIDTH / 2,
				-AbstractScreen.GAME_VIEWPORT_HEIGHT / 2);
		parallaxBackground.render(delta, spriteBatch, camera);
	}

	/**
	 * @return the foregroundWidth
	 */
	public int getForegroundWidth() {
		return foregroundWidth;
	}

	/**
	 * @param foregroundWidth
	 *            the foregroundWidth to set
	 */
	public void setForegroundWidth(int foregroundWidth) {
		this.foregroundWidth = foregroundWidth;
	}

	/**
	 * @return the backgroundLayer1Width
	 */
	public int getBackgroundLayer1Width() {
		return backgroundLayer1Width;
	}

	/**
	 * @param backgroundLayer1Width
	 *            the backgroundLayer1Width to set
	 */
	public void setBackgroundLayer1Width(int backgroundLayer1Width) {
		this.backgroundLayer1Width = backgroundLayer1Width;
	}

	/**
	 * @return the backgroundLayer2Width
	 */
	public int getBackgroundLayer2Width() {
		return backgroundLayer2Width;
	}

	/**
	 * @param backgroundLayer2Width
	 *            the backgroundLayer2Width to set
	 */
	public void setBackgroundLayer2Width(int backgroundLayer2Width) {
		this.backgroundLayer2Width = backgroundLayer2Width;
	}

	/**
	 * @return the backgroundLayer3Width
	 */
	public int getBackgroundLayer3Width() {
		return backgroundLayer3Width;
	}

	/**
	 * @param backgroundLayer3Width
	 *            the backgroundLayer3Width to set
	 */
	public void setBackgroundLayer3Width(int backgroundLayer3Width) {
		this.backgroundLayer3Width = backgroundLayer3Width;
	}

	@Override
	public void dispose() {
		Gdx.app.log(MyriGame.TAG, "Disposing level " + id);
		for(Texture texture:foreground){
			texture.dispose();
		}
		for(Texture texture:backgroundLayer1){
			texture.dispose();
		}
		for(Texture texture:backgroundLayer2){
			texture.dispose();
		}
		for(Texture texture:backgroundLayer3){
			texture.dispose();
		}
		for(ParallaxLayer layer:layers){
			layer.dispose();
		}
		if(background != null)
			background.dispose();
	}

}
