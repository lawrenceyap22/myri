package com.epicnoobz.myri.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GameTouchpad {
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private Touchpad touchpad;
	private static GameTouchpad instance = null;

	private GameTouchpad() {
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

	public static synchronized GameTouchpad getInstance() {
		if (instance == null) {
			instance = new GameTouchpad();
		}
		return instance;
	}
	
	public Touchpad getTouchpad(){
		return touchpad;
	}
}