package com.gilded.pokestyle;


public class InGameScreen extends Screen {
	private Level level;
	
	public InGameScreen() {
		camera = new Camera(PokeStyle.GAME_WIDTH, PokeStyle.GAME_HEIGHT);
		level = new Level(this, 20, 18, Art.TILESIZE * 2, Art.TILESIZE * 2, Art.level);
	}
	
	public void tick(Input input) {
		if(!level.getPlayer().removed())
			level.getPlayer().tick(input);
	}
	
	@Override
	public void render() {
		level.render(this, camera);
	}
}
