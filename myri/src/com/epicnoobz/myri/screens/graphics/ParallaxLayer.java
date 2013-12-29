package com.epicnoobz.myri.screens.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class ParallaxLayer {
	private Texture texture;
	private Vector2 parallaxRatio;
	private Vector2 startPosition;
	private Vector2 padding;

	public ParallaxLayer(Texture texture, Vector2 parallaxRatio, Vector2 padding) {
		this(texture, parallaxRatio, new Vector2(0, 0), padding);
	}

	/**
	 * @param region
	 *            the TextureRegion to draw , this can be any width/height
	 * @param parallaxRatio
	 *            the relative speed of x,y
	 *            {@link ParallaxBackground#ParallaxBackground(ParallaxLayer[], float, float, Vector2)}
	 * @param startPosition
	 *            the init position of x,y
	 * @param padding
	 *            the padding of the region at x,y
	 */
	public ParallaxLayer(Texture texture, Vector2 parallaxRatio,
			Vector2 startPosition, Vector2 padding) {
		this.texture = texture;
		this.parallaxRatio = parallaxRatio;
		this.startPosition = startPosition;
		this.padding = padding;
	}

	/**
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @return the parallaxRatio
	 */
	public Vector2 getParallaxRatio() {
		return parallaxRatio;
	}

	/**
	 * @return the startPosition
	 */
	public Vector2 getStartPosition() {
		return startPosition;
	}

	/**
	 * @return the padding
	 */
	public Vector2 getPadding() {
		return padding;
	}
	
	
}