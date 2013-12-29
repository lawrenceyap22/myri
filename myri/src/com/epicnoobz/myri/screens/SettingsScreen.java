package com.epicnoobz.myri.screens;

import java.util.Locale;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.epicnoobz.myri.MyriGame;

public class SettingsScreen extends AbstractScreen {
	private static final int SOUND = 1;
	private static final int MUSIC = 2;

	private Label soundVolumeValue;
	private Label musicVolumeValue;

	public SettingsScreen(MyriGame game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();
		Table table = super.getTable();
		table.defaults().spaceBottom(30);
		table.columnDefaults(0).padRight(20);
		table.add("Settings").colspan(3);

		final CheckBox sfxCheckBox = new CheckBox("", getSkin());
		sfxCheckBox.setChecked(game.getPreferencesManager().isSoundEnabled());
		sfxCheckBox.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = sfxCheckBox.isChecked();
				game.getPreferencesManager().setSoundEnabled(enabled);
				game.getSoundManager().setEnabled(enabled);
				// TODO game.getSoundManager().play(MyriGameSound.CLICK);
			}

		});
		table.row();
		table.add("SFX");

		// range is [0.0,1.0]; step is 0.1f
		Slider soundVolumeSlider = new Slider(0f, 1f, 0.1f, false, getSkin());
		soundVolumeSlider.setValue(game.getPreferencesManager()
				.getSoundVolume());
		soundVolumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				float value = ((Slider) actor).getValue();
				game.getPreferencesManager().setSoundVolume(value);
				game.getSoundManager().setVolume(value);
				updateVolumeLabel(SOUND);
			}
		});

		// create the volume label
		soundVolumeValue = new Label("", getSkin());
		updateVolumeLabel(SOUND);

		table.add(soundVolumeSlider);
		table.add(soundVolumeValue).width(40);
		table.add(sfxCheckBox).colspan(2).left();
		table.row();

		final CheckBox musicCheckbox = new CheckBox("", getSkin());
		musicCheckbox.setChecked(game.getPreferencesManager().isMusicEnabled());
		musicCheckbox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = musicCheckbox.isChecked();
				game.getPreferencesManager().setMusicEnabled(enabled);
				game.getMusicManager().setEnabled(enabled);
				// TODO game.getSoundManager().play(MyriGameSound.CLICK);

				// if the music is now enabled, start playing the menu music
				if (enabled)
					;
				// TODO game.getMusicManager().play(MyriGameMusic.MENU);
			}
		});
		table.row();
		table.add("Music");

		// range is [0.0,1.0]; step is 0.1f
		Slider musicVolumeSlider = new Slider(0f, 1f, 0.1f, false, getSkin());
		musicVolumeSlider.setValue(game.getPreferencesManager()
				.getSoundVolume());
		musicVolumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				float value = ((Slider) actor).getValue();
				game.getPreferencesManager().setMusicVolume(value);
				game.getMusicManager().setVolume(value);
				updateVolumeLabel(MUSIC);
			}
		});

		// create the volume label
		musicVolumeValue = new Label("", getSkin());
		updateVolumeLabel(MUSIC);

		table.add(musicVolumeSlider);
		table.add(musicVolumeValue).width(40);
		table.add(musicCheckbox).colspan(2).left();
		table.row();

		// register the back button
		TextButton backButton = new TextButton("Back", getSkin());
		backButton.addListener(new EventListener() {

			@Override
			public boolean handle(Event event) {
				game.setScreen(new MenuScreen(game));
				return true;
			}
		});
		table.row();
		table.add(backButton).size(250, 60).colspan(3);
	}

	private void updateVolumeLabel(int type) {
		float volume;
		switch (type) {
		case SOUND:
			volume = (game.getPreferencesManager().getSoundVolume() * 100);
			soundVolumeValue.setText(String
					.format(Locale.US, "%1.0f%%", volume));
			break;
		case MUSIC:
			volume = (game.getPreferencesManager().getMusicVolume() * 100);
			musicVolumeValue.setText(String
					.format(Locale.US, "%1.0f%%", volume));
			break;
		}
	}

}
