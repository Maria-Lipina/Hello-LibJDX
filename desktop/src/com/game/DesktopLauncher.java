package com.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.game.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1280, 720);
		config.setWindowPosition(100, 100);
		config.setResizable(false);
		config.setForegroundFPS(60);
		config.setTitle("My GDX Game");
		config.setWindowIcon("D:\\IT\\Java\\test\\assets\\icon.png");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
