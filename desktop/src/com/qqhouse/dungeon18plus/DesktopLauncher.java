package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.qqhouse.dungeon18plus.Dungeon18Plus;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Dungeon 18+");
		config.setWindowedMode((int) (G.WIDTH+60), (int) (G.HEIGHT-100));
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setIdleFPS(10);
		//new Lwjgl3Application(new Dungeon18Plus(), config);
		new Lwjgl3Application(new Main(), config);
	}
}
