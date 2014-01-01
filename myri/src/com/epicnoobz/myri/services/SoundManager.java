package com.epicnoobz.myri.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.epicnoobz.myri.MyriGame;
import com.epicnoobz.myri.services.SoundManager.MyriGameSound;
import com.epicnoobz.myri.utils.LRUCache;
import com.epicnoobz.myri.utils.LRUCache.CacheEntryRemovedListener;

public class SoundManager implements CacheEntryRemovedListener<MyriGameSound, Sound>, Disposable{
	
	public enum MyriGameSound{
		CLICK("");//i.e. sound/click.wav
		
		private final String filename;
		
		private MyriGameSound(String filename){
			this.filename = filename;
		}
	}
	
	private float volume = 1f;
	private boolean enabled = true;
	private final LRUCache<MyriGameSound, Sound> soundCache;
	
	public SoundManager(){
		soundCache = new LRUCache<SoundManager.MyriGameSound, Sound>(10);
		soundCache.setEntryRemovedListener(this);
	}

	public void play(MyriGameSound sound){
		if(enabled){
			Sound soundToPlay = soundCache.get(sound);
			if(soundToPlay == null){
				FileHandle soundFile = Gdx.files.internal(sound.filename);
				soundToPlay = Gdx.audio.newSound(soundFile);
				soundCache.add(sound, soundToPlay);
				Gdx.app.log(MyriGame.TAG, "Sound to play: " + sound.name());
				soundToPlay.play(volume);
			}
		}
	}
	
	public void setVolume(float volume){
		Gdx.app.log(MyriGame.TAG, "Adjusting sound volume to " + volume);
		//volume range: [0,1]
		if(volume < 0){
			volume = 0;
		}else if(volume > 1f){
			volume = 1f;
		}else{
			this.volume = volume;
		}
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	@Override
	public void dispose() {
		Gdx.app.log( MyriGame.TAG, "Disposing sound manager" );
        for( Sound sound : soundCache.retrieveAll() ) {
            sound.stop();
            sound.dispose();
        }
	}

	@Override
	public void notifyEntryRemoved(MyriGameSound key, Sound value) {
		Gdx.app.log(MyriGame.TAG, "Disposing sound: " + key.name());
		value.dispose();
	}

}
