package com.gilded.pokestyle;

import java.util.Random;

public abstract class Entity {
	protected boolean onGround = false;
	protected static Random random = new Random();
	
	public double dx, dy;
	public double x, y;
	public int xSlot, ySlot;
	protected double bounce = 0.05;
	public int w = 10, h = 10;
	
	private Level level;
	
	public boolean removed = false;
	
	public boolean interactsWithWorld = false;
	
	public void init(Level level) {	
		this.level = level;
	}
	
	public void tryMove(double dx, double dy) {
		onGround = false;
		// First, try to move horizontally
		if(level.canMove(this, x + dx, y, w, h, dx, 0)) {
			x += dx;
		}
		else {
			// Hit a wall
			hitWall(dx, 0);
			if(dx < 0) {
				double xx = x / 10;
				dx = -(xx - (int) xx) * 10;
			}
			else {
				double xx = (x + w) / 10;
				dx = 10 - (xx - (int) xx) / 10;
			}
			dx *= -bounce;
		}
		
		// Next, move vertically
		if(level.canMove(this, x, y + dy, w, h, 0, dy)) {
			y += dy;
		}
		else {
			// Hit the ground
			if(dy > 0) onGround = true;
			hitWall(0, dy);
			
			// Bounce back off the wall
			if(dy < 0) {
				double yy = y / 10;
				dy = -(yy - (int)yy) * 10;
			}
			else {
				double yy = (y + h) / 10;
				dy = 10 - (yy - (int)yy) * 10;
			}
			
			// Try to move now that we've slowed down
			if(level.canMove(this, x, y + dy, w, h, 0, dy)) {
				y += dy;
			}
			this.dy = dy;
			//this.dy *= bounce;
		}
	}
	
	public void hitWall(double dx, double dy) {
	}
	
	public void tick() {
	}
	
	public abstract void render(Screen screen, Camera camera);
	
	public void outOfBounds() {
		if(y < 0) return;
		removed = true;
	}
	
	public void remove() {
		removed = true;
	}

	public boolean removed() {
		return removed;
	}
}
