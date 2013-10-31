package com.gilded.pokestyle;


public class InGameScreen extends Screen {
	private final Camera camera = new Camera(PokeStyle.GAME_WIDTH, PokeStyle.GAME_HEIGHT);
	
<<<<<<< HEAD
	private Level level = new Level(this, 20, 18, 32, 32, Art.level);
=======
	private Level level = new Level(this, 20, 18, Art.TILESIZE * 2, Art.TILESIZE * 2, Art.level);
>>>>>>> c81d2af1f14db6615b5689fe864b558ba149ff4a
	
	public InGameScreen() {
		// TODO: Initialize basic level stats
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
