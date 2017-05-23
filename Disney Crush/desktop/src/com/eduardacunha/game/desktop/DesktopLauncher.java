package com.eduardacunha.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eduardacunha.game.DisneyCrush;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DisneyCrush.V_WIDTH;
		config.height = DisneyCrush.V_HEIGHT;
		new LwjglApplication(new DisneyCrush(), config);
	}
}
