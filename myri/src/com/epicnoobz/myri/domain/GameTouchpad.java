package com.epicnoobz.myri.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GameTouchpad{

	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private Touchpad touchpad;
	private InputEvent fakeTouchdownEvent;
	private static GameTouchpad instance = null;
	private Vector2 screenPos;
	private Vector2 localPos;

	private GameTouchpad() {
		touchpadSkin = new Skin();
		touchpadSkin.add("touchBackground", new TextureRegion(new Texture("skin/touchpadBackground.png")));
		touchpadSkin.add("touchKnob", new TextureRegion(new Texture("skin/touchpadKnob.png")));
		touchpadStyle = new TouchpadStyle();
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		
		touchpad = new Touchpad(10, touchpadStyle);
		touchpad.setColor(0, 0, 0, 0.5f);
		
		fakeTouchdownEvent = new InputEvent();
		fakeTouchdownEvent.setType(Type.touchDown);
		
		TextureRegion backgroundRegion = touchpadSkin.getRegion("touchBackground");
		touchpad.setSize(backgroundRegion.getRegionWidth(), backgroundRegion.getRegionHeight());
		
		screenPos = new Vector2();
		localPos = new Vector2();
	}
	
	public static synchronized GameTouchpad getInstance() {
		if (instance == null) {
			instance = new GameTouchpad();
		}
		return instance;
	}

	public Touchpad getTouchpad() {
		return touchpad;
	}
	
	public int getDirectionX(){
		double x = Math.abs(touchpad.getKnobPercentX());
		double y = Math.abs(touchpad.getKnobPercentY());
		double rad = Math.atan(y / x);
		double degrees = Math.toDegrees(rad);
		
		if(degrees <= 65){
			if(touchpad.getKnobPercentX() > 0)
				return 1;
			else
				return -1;
		}
		return 0;
	}
	
	public void toggleTouchpad(int screenSize){
		if(Gdx.input.justTouched() && Gdx.input.getX() < screenSize/2){
			screenPos.set(Gdx.input.getX(), Gdx.input.getY());
			localPos.set(screenPos);
			localPos = touchpad.getParent().screenToLocalCoordinates(localPos);
			touchpad.setPosition(localPos.x - touchpad.getWidth() / 2, localPos.y - touchpad.getHeight() / 2);
			touchpad.setVisible(true);
			
			Vector2 stagePos = touchpad.getStage().screenToStageCoordinates(screenPos);
			fakeTouchdownEvent.setStageX(stagePos.x);
			fakeTouchdownEvent.setStageY(stagePos.y);
			touchpad.fire(fakeTouchdownEvent);
		}else if(!Gdx.input.isTouched()){
			touchpad.setVisible(false);
		}
	}
	
}