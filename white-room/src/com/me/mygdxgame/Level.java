package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Matrix4;

public class Level {
	public static final double FRICTION = 0.99;
	public static final double GRAVITY = 0.10;
	public List<Entity> entities = new ArrayList<Entity>();
	
	public byte[] walls;
	public List<Entity>[] entityMap;
	private final int width, height;
	
	private Screen screen;
	
	private Player player;
	private int ySpawn;
	private int xSpawn;
	
	/**
	 * Creates the white room level
	 * 
	 * @param screen Screen calling this constructor
	 * @param width width of level
	 * @param height height of level
	 * @param xSpawn X-Coordinate of the spawn
	 * @param ySpawn Y-Coordinate of the spawn
	 */
	public Level(Screen screen, int width, int height, int xSpawn, int ySpawn) {
		super();
		// Set level size and the screen it is in
		this.width = width;
		this.height = height;
		this.screen = screen;
		
		// Spawn point for main character
		this.xSpawn = xSpawn;
		this.ySpawn = ySpawn;
		
		// Initialize array for each tile
		walls = new byte[width * height];

		// Initialize list of entities for each tile
		entityMap = new ArrayList[width * height];
		
		// Load the image/tile map into the walls array
		for(int y = 0; y < height; y ++) {
			for(int x = 0; x < width; x ++) {
				// Create empty Entity list here
				entityMap[x + y*width] = new ArrayList<Entity>();

				// Load pixel's RGB into an integer
				int col = (Art.level.getPixel(x, y) & 0xffffff00) >>> 8;
				byte wall = 0; // Default nothingness
				
				
				if(col == 0xffffff)	// Default wall
					wall = 1;
				
				// Set wall in byte array
				walls[x + y * width] = wall;
			}
		}
		
		// Create player
		player = new Player(this.xSpawn, this.ySpawn);
		add(player);
	}
	
	public void add(Entity e) {
		entities.add(e);
		e.init(this);
		
		// Determine 'slot' that Entity is in in world
		e.xSlot = (int)((e.x + e.w / 2.0) / 10);
		e.ySlot = (int)((e.y + e.h / 2.0) / 10);
		
		// If that's within the viewport, add the entity
		if(e.xSlot >= 0 && e.ySlot >= 0 && e.xSlot < width && e.ySlot < width)
			entityMap[e.xSlot + e.ySlot * width].add(e);
	}
	
	public void tick() {
		for(int i = 0; i < entities.size(); i ++) {
			Entity e = entities.get(i);
			int xSlotOld = e.xSlot;
			int ySlotOld = e.ySlot;
			
			// Perform calculations on Entity
			if(!e.removed) e.tick();

			// Determine 'slot' that Entity is in in world
			e.xSlot = (int)((e.x + e.w / 2.0) / 10);
			e.ySlot = (int)((e.y + e.h / 2.0) / 10);
			
			// Entity has been removed for whatever reason
			if(e.removed) {
				// If it was once within the viewport, remove it
				if(xSlotOld >= 0 && ySlotOld >= 0 && xSlotOld < width && ySlotOld < width) {
					entityMap[xSlotOld + ySlotOld * width].remove(e);
				}
				entities.remove(i--);
			}
			else {
				// Only make changes if it moved slots
				if(e.xSlot != xSlotOld || e.ySlot != ySlotOld) {
					// If it was once within the viewport, remove it from that spot
					if(xSlotOld >= 0 && ySlotOld >= 0 && xSlotOld < width && ySlotOld < width)
						entityMap[xSlotOld + ySlotOld * width].remove(e);

					// If it's still within the level boundaries, add it
					if(e.xSlot >= 0 && e.ySlot >= 0 && e.xSlot < width && e.ySlot < width)
						entityMap[e.xSlot + e.ySlot * width].add(e);
					else
						e.outOfBounds();
				}
			}
		}
	}
	
	Matrix4 matrix = new Matrix4();
	public void render(Screen screen, Camera camera) {
		matrix.idt();
		matrix.setToTranslation(camera.x, camera.y, 0);
		screen.getSpriteBatch().setTransformMatrix(matrix);
		screen.getSpriteBatch().begin();
		
		int xo = 0;
		int yo = 0;
		for(int x = xo; x < xo + camera.width / 10; x ++) {
			for(int y = yo; y < yo + camera.height / 10; y ++) {
				if(x >= 0 && y >= 0 && x < width && y < height) {
					int ximg = 0;
					int yimg = 0;
					byte w = walls[x + y * width];
					
					if(w == 1) yimg = 1;
					
					screen.draw(Art.walls[ximg][yimg], x * 10, y * 10);
				}
			}
		}
		for(int i = entities.size() - 1; i >= 0; i --) {
			Entity e = entities.get(i);
			e.render(screen, camera);
		}
		
		screen.getSpriteBatch().end();
	}

	public boolean canMove(Entity entity, double xc, double yc, int w, int h,
			double dx, double dy) {

		// Buffer
		double e = 0;
		
		// Set initial and goal values
		int x0 = (int)(xc / 10);
		int y0 = (int)(yc / 10);
		int x1 = (int)((xc + w - e) / 10);
		int y1 = (int)((yc + h - e) / 10);
		
		// Good so far...
		boolean ok = true;
		
		for(int x = x0; x <= x1; x ++)
			for(int y = y0; y <= y1; y ++) {
				if(x >= 0 && y >= 0 && x < width && y < height) {
					byte ww = walls[x + y * width];
					if(ww != 0) ok = false;
				}
			}
		
		return ok;
	}

	public Player getPlayer() {
		return player;
	}
}
