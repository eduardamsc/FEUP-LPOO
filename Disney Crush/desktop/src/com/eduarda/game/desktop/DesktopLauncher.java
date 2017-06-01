package com.eduarda.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eduarda.game.DisneyCrushDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DisneyCrushDemo.WIDTH;
		config.height = DisneyCrushDemo.HEIGHT;
		config.title = DisneyCrushDemo.title;
		new LwjglApplication(new DisneyCrushDemo(), config);
	}
}
