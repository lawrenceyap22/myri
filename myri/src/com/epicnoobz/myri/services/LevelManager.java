package com.epicnoobz.myri.services;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.epicnoobz.myri.MyriGame;
import com.epicnoobz.myri.domain.Level;

public class LevelManager implements Disposable{
	private final List<Level> levels;
	
	public LevelManager(){
		Texture.setEnforcePotImages(false);
		levels = new ArrayList<Level>();
		Level level0 = new Level(0);
		level0.setName("Prologue");
		level0.addBackgroundLayer3("lvl0_3rdlayer1.png");
		level0.addBackgroundLayer3("lvl0_3rdlayer2.png");
		level0.addBackgroundLayer2("lvl0_2ndlayer.png");
		level0.addBackgroundLayer1("lvl0_1stlayer1.png");
		level0.addBackgroundLayer1("lvl0_1stlayer2.png");
		level0.addForeground("lvl0_foreground1.png");
		level0.addForeground("lvl0_foreground2.png");
		level0.setBackground("skybg.png");
		level0.setBackgroundLayer2Width(level0.getForegroundWidth());
		level0.setParallaxBackground();
		levels.add(level0);
	}
	
	public List<Level> getLevels(){
		return levels;
	}
	
	public Level findLevelById(int id){
		if(id < 0 || id >= levels.size()){
			return null;
		}
		return levels.get(id);
	}

	@Override
	public void dispose() {
		Gdx.app.log(MyriGame.TAG, "Disposing level manager");
		for(Level level : levels){
			level.dispose();
		}
	}
}
