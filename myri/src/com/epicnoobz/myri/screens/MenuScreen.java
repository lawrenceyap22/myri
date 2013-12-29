package com.epicnoobz.myri.screens;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.epicnoobz.myri.MyriGame;

public class MenuScreen extends AbstractScreen {

	public MenuScreen(MyriGame game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();

		Table table = super.getTable();
		table.add("MyriGame").spaceBottom(50);
		table.row();

		TextButton playButton = new TextButton("Play", getSkin());
		playButton.addListener(new EventListener() {

			@Override
			public boolean handle(Event event) {
				game.setScreen(new MapScreen(game));
				return true;
			}
		});
		table.add(playButton).size(300, 60).uniform().spaceBottom(10);
		table.row();

		TextButton settingsButton = new TextButton("Settings", getSkin());
		settingsButton.addListener(new EventListener() {

			@Override
			public boolean handle(Event event) {
				game.setScreen(new SettingsScreen(game));
				return true;
			}
		});
		table.add(settingsButton).uniform().fill().spaceBottom(10);
		table.row();

	}

}
