package com.epicnoobz.myri.screens.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParallaxBackground {

	private ParallaxLayer[] layers;

	/**
	 * @param layers
	 *            The background layers
	 */
	public ParallaxBackground(ParallaxLayer[] layers) {
		this.layers = layers;
	}

	public void render(float delta, SpriteBatch spriteBatch, Camera camera) {
		for (ParallaxLayer layer : layers) {
			float currentX = -camera.position.x * layer.getParallaxRatio().x;
			spriteBatch.draw(layer.getTexture(), -camera.viewportWidth / 2
					+ currentX + layer.getStartPosition().x,
					-camera.viewportHeight / 2);
		}
	}
}