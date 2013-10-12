package com.me.mygdxgame;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

public abstract class Screen {
	private final String[] chars = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", ".,!?:;\"'+-=/\\< "};
	private SpriteBatch spriteBatch;

	protected static Random random = new Random();
	private WhiteRoom whiteRoom;
	
	public void removed() {
		spriteBatch.dispose();
	}
	
	public final void init(WhiteRoom whiteRoom) {
		this.whiteRoom = whiteRoom;
		
		Matrix4 projection = new Matrix4();
		projection.setToOrtho(0, WhiteRoom.GAME_WIDTH, WhiteRoom.GAME_HEIGHT, 0, -1, 1);
		
		spriteBatch = new SpriteBatch(100);
		spriteBatch.setProjectionMatrix(projection);
	}

	protected void setScreen(Screen screen) {
		whiteRoom.setScreen(screen);
	}
	
	public void draw(TextureRegion region, int x, int y) {
		int width = region.getRegionWidth();
		if(width < 0) width *= -1;
		spriteBatch.draw(region, x, y, width, region.getRegionHeight());
	}
	
//	public void drawString(String string, int x, int y) {
//		string = string.toUpperCase();
//		for(int i = 0; i < string.length(); i ++) {
//			char ch = string.charAt(i);
//			for(int ys = 0; ys < chars.length; ys ++) {
//				int xs = chars[ys].indexOf(ch);
//				if(xs >= 0) {
//					draw(Art.guys[xs][ys+9], x + i * 6, y);
//				}
//			}
//		}
//	}
	
	public abstract void render();
	
	public void tick(Input input) {
		
	}
	
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
}
