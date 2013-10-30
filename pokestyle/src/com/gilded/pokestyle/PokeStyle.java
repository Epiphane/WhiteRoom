package com.gilded.pokestyle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class PokeStyle implements ApplicationListener {
	public static final int GAME_WIDTH = 160;
	public static final int GAME_HEIGHT = 144;
	
	public static final int N = 1;
	public static final int NE = 3;
	public static final int E = 2;
	public static final int SE = 6;
	public static final int S = 4;
	public static final int SW = 12;
	public static final int W = 8;
	public static final int NW = 9;
	
	/**
	 * DIRECTIONS holds a mapping of the directional byte
	 * to parts of an array (ex. N, which is 1, is mapped
	 * to the 0th element of an array). The default value
	 * is 4, or South (because it looks good).
	 * 
	 * Numbers:
	 * 7 0 1
	 * 6 x 2
	 * 5 4 3
	 */
	// Mapped values                                  -   N   E  NE   S   -  SE   -   W  NW   -   -  SW 
	public static final int[] DIRECTIONS = new int[] {4,  0,  2,  1,  4,  4,  3,  4,  6,  7,  4,  4,  5};
	
	/**
	 * Keeps track of delay in order to run updates possibly multiple times
	 */
	private float accumulatedTime = 0;
	
	// Is the program running? (or paused?)
	private boolean running = false;
	
	/**
	 * Keeps track of all inputs
	 */
	private final Input input = new Input();
	
	/**
	 * Saves the current screen for use
	 */
	private Screen screen;
	
	@Override
	public void create() {		
		Art.load();
		Gdx.input.setInputProcessor(input);
		running = true;
		setScreen(new InGameScreen());
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
	
	public void setScreen(Screen newScreen) {
		if(screen != null) screen.removed();
		screen = newScreen;
		if(screen != null) screen.init(this);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
