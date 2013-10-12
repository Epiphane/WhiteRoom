package com.me.mygdxgame;

public class WhiteRoomScreen extends Screen {
	private final Camera camera = new Camera(WhiteRoom.GAME_WIDTH, WhiteRoom.GAME_HEIGHT);
	
	private Level level = new Level(this, 64, 36, 32, 30);
	
	public WhiteRoomScreen() {
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
