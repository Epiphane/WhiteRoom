package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WhiteRoom implements ApplicationListener {
	public static final int GAME_WIDTH = 640;
	public static final int GAME_HEIGHT = 360;
	public static final int SCREEN_SCALE = 3;
	
	private final Input input = new Input();
	private Screen screen;
	
	private float accumulatedTime = 0;
	
	private boolean running = false;
	private final boolean started = false;
	
	@Override
	public void create() {
		Art.load();
		Gdx.input.setInputProcessor(input);
		running = true;
		setScreen(new WhiteRoomScreen());
	}
	
	public void setScreen(Screen newScreen) {
		if(screen != null) screen.removed();
		screen = newScreen;
		if(screen != null) screen.init(this);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		accumulatedTime += Gdx.graphics.getDeltaTime();
		while(accumulatedTime > 1.0f / 60.0f) {
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			screen.tick(input);
			input.tick();
			accumulatedTime -= 1.0f / 60.0f;
			
			screen.render();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		running = false;
	}

	@Override
	public void resume() {
		running = true;
	}
}
