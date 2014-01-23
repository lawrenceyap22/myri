package com.epicnoobz.myri.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.epicnoobz.myri.MyriGame;
import com.epicnoobz.myri.domain.Champion;
import com.epicnoobz.myri.domain.GameTouchpad;
import com.epicnoobz.myri.domain.Level;
import com.epicnoobz.myri.domain.Skill;
import com.epicnoobz.myri.domain.units.Paladin;
import com.epicnoobz.myri.domain.units2d.Champion2D;
import com.epicnoobz.myri.domain.units2d.Paladin2D;

public class GameScreen extends AbstractScreen implements GestureListener{

	private static Level level;
	private static Camera camera;

	private GameTouchpad touchpad;
	private SpriteBatch batch;
	private Champion2D champion2D;
	private Champion champion;
	private InputMultiplexer inputMultiplexer;

	public GameScreen(MyriGame game, int levelId) {
		super(game);
		level = game.getLevelManager().findLevelById(levelId);
		camera = new OrthographicCamera(GAME_VIEWPORT_WIDTH,
				GAME_VIEWPORT_HEIGHT);
		batch = getBatch();
		inputMultiplexer = new InputMultiplexer();
		champion = new Paladin();
	}

	public static synchronized Camera getCamera() {
		return camera;
	}

	public static int getLevelSize() {
		return level.getForegroundWidth();
	}

	@Override
	protected boolean isGameScreen() {
		return true;
	}

	@Override
	public void show() {
		super.show();
		setupStage();
		setupHud();
	}
	
	private void setupStage(){
		touchpad = GameTouchpad.getInstance();
		stage.addActor(touchpad.getTouchpad());
		champion2D = Paladin2D.getPaladin(champion); //paladin should be from profile(saved)
		champion2D.setOrigin(0, 0);
		champion2D.setPosition(GAME_VIEWPORT_WIDTH / 2 - champion2D.getWidth() / 2,
				GAME_VIEWPORT_HEIGHT / 2 - champion2D.getHeight() - 140);
		stage.addActor(champion2D);
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	private void setupHud(){
		Image skillIcon;
		float posX = GAME_VIEWPORT_WIDTH / 2 + 80;
		for(Skill skill : champion.getSkills()){
			skillIcon = Skill.getSkillIcon(skill);
			skillIcon.setName(skill.toString());
			skillIcon.setTouchable(Touchable.enabled);
			skillIcon.setOrigin(0, 0);
			skillIcon.setScale(150f/skillIcon.getWidth(), 150f/skillIcon.getHeight());
			skillIcon.setPosition(posX, 0);
			posX += 175;
			skillIcon.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,int pointer, int button) {
					//TODO
					//champion.setActiveSkill(event.getTarget().getName());
					System.out.println("SKILL: " + event.getTarget().getName());
				}
				
			});
			stage.addActor(skillIcon);
		}
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		level.render(delta, batch, camera);
		batch.end();
		stage.draw();
	}

	private void update(float delta) {
		touchpad.toggleTouchpad(GAME_VIEWPORT_WIDTH);
		stage.act(delta);
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {

		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {

		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {

		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		System.out.println("ATTACK");
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
}
