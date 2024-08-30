package com.qqhouse.dungeon18plus;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {

		//Settings settings = new Settings();
		//settings.maxWidth = 512;
		//settings.maxHeight = 512;
		TexturePacker.processIfModified(/*settings,*/ "assets/image", "assets/", "game");

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Dungeon 18+");
		//config.setWindowedMode(375, 667); // iPhone SE 3
		config.setWindowedMode(330, 510); // desktop
		//config.setWindowedMode(Game.Size.WIDTH, Game.Size.HEIGHT);

		// break fps-limit
		//config.useVsync(false);
		//config.setForegroundFPS(0);
		//config.setIdleFPS(0);

		// normal setting
		//config.useVsync(true);
		//config.setForegroundFPS(60);
		//config.setIdleFPS(10);

		//config.setDecorated(false);
		config.setResizable(false);
		//new Lwjgl3Application(new Dungeon18Plus(), config);
		//Main main = new Main();
		//main.purchaseManager = new FakePurchaseManager();
		new Lwjgl3Application(new Main(), config);
	}
}
