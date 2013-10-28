package com.gilded.pokestyle;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class Input implements InputProcessor {
	// Static key values
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public static final int JUMP = 4;
	
	// Button arrays
	public boolean[] buttons = new boolean[32];
	public boolean[] oldButtons = new boolean[32];
	
	/**
	 * Sets button in the array to state of the key
	 * 
	 * @param key
	 * @param down
	 */
	public void set(int key, boolean down) {
		// Defaults to nothing pressed in case it's not a recognized keystroke
		int button = -1;
		
		// Go through key possibilities for recognized input
		if (key == Keys.DPAD_UP) button = UP;
		if (key == Keys.DPAD_DOWN) button = DOWN;
		if (key == Keys.DPAD_LEFT) button = LEFT;
		if (key == Keys.DPAD_RIGHT) button = RIGHT;
		
		if (key == Keys.SPACE) button = JUMP;
		
		// If it's recognized, set the state in the array
		if(button >= 0)
			buttons[button] = down;
	}
	
	/**
	 * Set the button inputs to the past
	 */
	public void tick() {
		for (int i = 0; i < buttons.length; i ++)
			oldButtons[i] = buttons[i];
	}
	
	public void releaseAllKeys() {
		buttons = new boolean[32];
	}
	
	public boolean keyDown(int keycode) {
		set(keycode, true);
		return false;
	}

	public boolean keyUp(int keycode) {
		set(keycode, false);
		return false;
	}
	
	/**
	 * Not useful right now but I gotta implement it
	 */
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
