package q2p.zur2w;

import org.lwjgl.opengl.GL11;

public class MenuManager {
	public static final byte WINDOW_MAIN = 0;
	public static final byte WINDOW_OPTIONS = 1;
	public static final byte WINDOW_STATISTICS = 2;
	public static final byte WINDOW_HELP = 3;
	public static final byte WINDOW_QUIT = 4;
	// TODO then you dead open a statistics window
	public static byte menuWindow = WINDOW_MAIN;

	public static void init() {
		MenuMain.init();
	}
	
	public static void think() {
		switch(menuWindow) {
		case WINDOW_MAIN:
			MenuMain.think();
			break;
		}
	}

	public static void render() {
		switch(menuWindow) {
		case WINDOW_MAIN:
			MenuMain.render();
			break;
		}
		GL11.glColor3f(1,1,1);
		Renderer.drawCursor();
	}
}