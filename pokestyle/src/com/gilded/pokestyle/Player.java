package com.gilded.pokestyle;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {
	private int dir = PokeStyle.S;
	private int frame = 0;
	
	private TextureRegion[][] sheet;
	
	/**
	 * Sets the player to a default spot and sets up its sprite sheet.
	 * 
	 * @param x
	 * @param y
	 */
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		w = Art.TILESIZE;
		h = Art.TILESIZE;
		bounce = 0;
		
		this.sheet = Art.mainCharacterWalk;
	}
	
	/**
	 * Renders the player to the screen. Doesn't take into account the camera
	 * offset, since it's transformed automatically by the level
	 */
	@Override
	public void render(Screen screen, Camera camera) {
		int xp = (int)x;
		int yp = (int)y;
		
		int stepFrame = frame / 10;
		int directionAnimStart = PokeStyle.DIRECTIONS[this.dir] * 3 / 2;
		screen.draw(this.sheet[directionAnimStart + stepFrame][0], xp, yp);
	}
	
	public void tick(Input input) {
<<<<<<< HEAD
		if((this.x % Art.TILESIZE != 0 || this.y % Art.TILESIZE != 0) && (dx != 0 || dy != 0)) {
			frame ++;
			if(frame > 29) frame = 0;
		
=======
		if((dy != 0 && y % Art.TILESIZE != 0) || (dx != 0 && x % Art.TILESIZE != 0)) {
			frame ++;
			if(frame > 29) frame = 0;
>>>>>>> c81d2af1f14db6615b5689fe864b558ba149ff4a
			tryMove(dx, dy);
			return;
		}
		dx = dy = 0;
		boolean walk = false;
		switch(input.buttonStack.peek()) {
		case Input.LEFT:
			walk = true;
			dir = PokeStyle.W;
			dx = -1;
			break;
		case Input.RIGHT:
			walk = true;
			dir = PokeStyle.E;
			dx = 1;
			break;
		case Input.UP:
			walk = true;
			dir = PokeStyle.N;
			dy = -1;
			break;
		case Input.DOWN:
			walk = true;
			dir = PokeStyle.S;
			dy = 1;
			break;
		}
		
		if(walk) {
			frame ++;
			if(frame > 29) frame = 0;
		}
		else {
			frame = 0;
		}
		
		tryMove(dx, dy);
	}
	
	public void outOfBounds() {
		// Override default delete
	}
}
