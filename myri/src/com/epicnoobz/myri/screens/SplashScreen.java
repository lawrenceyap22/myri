package com.epicnoobz.myri.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.epicnoobz.myri.MyriGame;

public class SplashScreen extends AbstractScreen {

	private Image splashImage;

	public SplashScreen(MyriGame game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();
		AtlasRegion splashRegion = getAtlas().findRegion("splash_image");
		Drawable splashDrawable = new TextureRegionDrawable(splashRegion);

		splashImage = new Image(splashDrawable, Scaling.stretch);
		splashImage.setFillParent(true);

		splashImage.getColor().a = 0;

		splashImage.addAction(sequence(fadeIn(0.75f), delay(1.75f),
				fadeOut(0.75f), new Action() {
					@Override
					public boolean act(float delta) {
						game.setScreen(new MenuScreen(game));
						return true;
					}
				}));
		stage.addActor(splashImage);

	}

}
