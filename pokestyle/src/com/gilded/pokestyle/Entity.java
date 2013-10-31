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
	
	/**
	 * Try to move specified distance.
	 * s
	 * @param dx
	 * @param dy
	 */
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
				double xx = x / Art.TILESIZE;
				dx = -(xx - (int) xx) * Art.TILESIZE;
			}
			else {
				double xx = (x + w) / Art.TILESIZE;
				dx = Art.TILESIZE - (xx - (int) xx) / Art.TILESIZE;
			}
			dx *= -bounce;
		}
		
		// Next, move vertically
		if(level.canMove(this, x, y + dy, w, h, 0, dy)) {
			y += dy;
		}
		else {
			// Hit the wall
			hitWall(0, dy);
			if(dy < 0) {
				double yy = y / Art.TILESIZE;
				dy = -(yy - (int) yy) * Art.TILESIZE;
			}
			else {
				double yy = (y + h) / Art.TILESIZE;
				dy = Art.TILESIZE - (yy - (int) yy) / Art.TILESIZE;
			}
			dy *= -bounce;
		}
	}
	
	/**
	 * Called when you run into a wall
	 * s
	 * @param dx
	 * @param dy
	 */
	public void hitWall(double dx, double dy) {
		if(dx != 0) this.dx = 0;
		if(dy != 0) this.dy = 0;
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
