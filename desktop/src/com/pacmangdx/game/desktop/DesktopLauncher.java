package com.pacmangdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pacmangdx.game.PacmanGDX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "PacmanGDX";
		config.height = 750; 
		config.width = 685;
		new LwjglApplication(new PacmanGDX(), config);
	}
}
