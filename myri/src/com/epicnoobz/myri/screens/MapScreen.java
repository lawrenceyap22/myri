package com.epicnoobz.myri.screens;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.epicnoobz.myri.MyriGame;

public class MapScreen extends AbstractScreen{

	public MapScreen(MyriGame game) {
		super(game);
	}
	
	@Override
	public void show(){
		super.show();
		Table table = super.getTable();
		table.add("Select Location").spaceBottom(50);
		table.row();
		
		TextButton proceedButton = new TextButton("Proceed", super.getSkin());
		proceedButton.addListener(new EventListener(){

			@Override
			public boolean handle(Event event) {
				game.setScreen(new TroopSelectScreen(game));
				return true;
			}
			
		});
		
		table.add(proceedButton).size(100, 60);
		table.row();
		
	}
	

}
