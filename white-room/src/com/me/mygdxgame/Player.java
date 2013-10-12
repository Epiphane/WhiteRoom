package com.me.mygdxgame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {
	private int dir = 1;
	private int frame = 0;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		w = 17;
		h = 22;
		bounce = 0;
	}
	
	public void tick() {
	}
	
	@Override
	public void render(Screen screen, Camera camera) {
		int xp = (int)x - (18 - w) / 2;
		int yp = (int)y;
		
		int stepFrame = frame;
		
		TextureRegion[][] sheet = Art.player1;
		screen.draw(sheet[stepFrame / 20][0], xp, yp);
	}
	
	public void tick(Input input) {
		double speed = 0.5;
		boolean walk = false;
		if(input.buttons[Input.LEFT]) {
			walk = true;
			dx -= speed;
			dir = -1;
		}
		if(input.buttons[Input.RIGHT]){
			walk = true;
			dx += speed;
			dir = 1;
		}
		if(walk) {
			frame ++;
			if(frame > 39) frame = 0;
		}
		else {
			frame = 0;
		}
	
		if(input.buttons[Input.JUMP] && !input.oldButtons[Input.JUMP] && onGround) {
			// Sound.jump.play();
			dy -= 4;
		}
		
		tryMove(dx, dy);
		
		dx *= 0.7;
//		if(dy < 0 && input.buttons[Input.JUMP]) {
//			dy *= 0.992;
//			dy += Level.GRAVITY * 0.5;
//		}
//		else {
			dy *= Level.FRICTION;
			dy += Level.GRAVITY;
//		}
	}
	
	public void outOfBounds() {
		// Override default delete
	}
}
