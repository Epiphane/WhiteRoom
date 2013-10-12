package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "white-room";
		cfg.useGL20 = false;
		cfg.width = 1900;
		cfg.height = 1000;
		
		new LwjglApplication(new WhiteRoom(), cfg);
	}
}
