package com.myflappybird.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myflappybird.game.FlappyBirdGameDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=FlappyBirdGameDemo.WIDTH;
		config.height=FlappyBirdGameDemo.HEIGHT;
		config.title=FlappyBirdGameDemo.TITLE;

		new LwjglApplication(new FlappyBirdGameDemo(), config);
	}
}
