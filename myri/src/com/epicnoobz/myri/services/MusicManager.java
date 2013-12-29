package com.epicnoobz.myri.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.epicnoobz.myri.MyriGame;

public class MusicManager implements Disposable{
	
	public enum MyriGameMusic{
		MENU(""),//i.e. music/sample.mp3
		LEVEL("");
		
		private String filename;
		private Music musicResource;
		
		private MyriGameMusic(String filename){
			this.filename = filename;
		}
		
	}
	
	private MyriGameMusic musicBeingPlayed;
	private float volume = 1f;
	private boolean enabled = true;
	
	public void play(MyriGameMusic music){
		if(enabled && musicBeingPlayed != music){
			Gdx.app.log(MyriGame.TAG, "Playing music: " + music.name());
			stop();
			
			FileHandle musicFile = Gdx.files.internal(music.filename);
			Music musicResource = Gdx.audio.newMusic(musicFile);
			musicResource.setVolume(volume);
			musicResource.setLooping(true);
			musicResource.play();
			
			musicBeingPlayed = music;
			musicBeingPlayed.musicResource = musicResource;
		}
	}
	
	public void stop(){
		if(musicBeingPlayed != null){
			Gdx.app.log(MyriGame.TAG, "Stopping current music");
			Music musicResource = musicBeingPlayed.musicResource;
			musicResource.stop();
			musicResource.dispose();
			musicBeingPlayed = null;
		}
	}
	
	public void setVolume(float volume){
		Gdx.app.log(MyriGame.TAG, "Adjusting music volume to " + volume);
		//volume range: [0,1]
		if(volume < 0){
			volume = 0;
		}else if(volume > 1f){
			volume = 1f;
		}else{
			this.volume = volume;
			if(musicBeingPlayed != null){
				musicBeingPlayed.musicResource.setVolume(this.volume);
			}
		}
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
		if(!enabled){
			stop();
		}
	}
	
	@Override
	public void dispose() {
		Gdx.app.log(MyriGame.TAG, "Disposing music manager");
		stop();
	}

}
