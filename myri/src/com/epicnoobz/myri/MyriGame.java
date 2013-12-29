package com.epicnoobz.myri;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.epicnoobz.myri.screens.GameScreen;
import com.epicnoobz.myri.screens.SplashScreen;
import com.epicnoobz.myri.services.LevelManager;
import com.epicnoobz.myri.services.MusicManager;
import com.epicnoobz.myri.services.PreferencesManager;
import com.epicnoobz.myri.services.SoundManager;

public class MyriGame extends Game {
	public static final String TAG = MyriGame.class.getSimpleName();
	public static final boolean DEV_MODE = true;

	private FPSLogger fpsLogger;
	private MusicManager musicManager;
	private SoundManager soundManager;
	private PreferencesManager preferencesManager;
	private LevelManager levelManager;
	
	
	/**
	 * @return the musicManager
	 */
	public MusicManager getMusicManager() {
		return musicManager;
	}

	/**
	 * @return the soundManager
	 */
	public SoundManager getSoundManager() {
		return soundManager;
	}

	/**
	 * @return the preferencesManager
	 */
	public PreferencesManager getPreferencesManager() {
		return preferencesManager;
	}
	
	/*
	 * @return the levelManager
	 */
	public LevelManager getLevelManager(){
		return levelManager;
	}

	@Override
	public void create() {
		fpsLogger = new FPSLogger();
        // create the preferences manager
        preferencesManager = new PreferencesManager();

        // create the music manager
        musicManager = new MusicManager();
        musicManager.setVolume( preferencesManager.getMusicVolume() );
        musicManager.setEnabled( preferencesManager.isMusicEnabled() );

        // create the sound manager
        soundManager = new SoundManager();
        soundManager.setVolume( preferencesManager.getSoundVolume() );
        soundManager.setEnabled( preferencesManager.isSoundEnabled() );
        
        levelManager = new LevelManager();
	}

	@Override
	public void dispose() {
		// batch.dispose();
		// texture.dispose();

		super.dispose();
		Gdx.app.log(TAG, "Disposing game");
	}

	@Override
	public void render() {

		super.render();
		if (DEV_MODE) {
			fpsLogger.log();
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log(MyriGame.TAG, "Resizing game to: " + width + " x " + height);
		if (DEV_MODE) {
			//TODO setScreen(new MenuScreen(this));
			setScreen(new GameScreen(this, 0));
		} else {
			setScreen(new SplashScreen(this));
		}
	}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.log(MyriGame.TAG, "Pausing game");
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log(MyriGame.TAG, "Resuming game");
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		Gdx.app.log(MyriGame.TAG, "Setting screen to "
				+ screen.getClass().getSimpleName());
	}
}
