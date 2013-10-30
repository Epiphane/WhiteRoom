package com.gilded.pokestyle;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {
	private int dir = PokeStyle.S;
	private int frame = 0;
	
	private TextureRegion[][] sheet;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		w = 16;
		h = 16;
		bounce = 0;
		
		this.sheet = Art.mainCharacterWalk;
	}
	
	public void tick() {
	}
	
	@Override
	public void render(Screen screen, Camera camera) {
		int xp = (int)x - (18 - w) / 2;
		int yp = (int)y;
		
		int stepFrame = frame / 10;
		int directionAnimStart = PokeStyle.DIRECTIONS[this.dir] * 3 / 2;
		screen.draw(this.sheet[directionAnimStart + stepFrame][0], xp, yp);
	}
	
	public void tick(Input input) {
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
