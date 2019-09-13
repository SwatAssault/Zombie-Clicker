package com.awprecords.zombieclicker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 800;
		config.width = 540;
//		TexturePacker.process("C://Users//Gustav//Desktop//ZC//icons_for_magaz//130",
//				"C://Users//Gustav//Desktop//ZC//icons_for_magaz//130//res", "icons_for_shop");

		new LwjglApplication(new ZombieClicker(), config);
	}
}
