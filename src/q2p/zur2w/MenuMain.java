package q2p.zur2w;

import org.lwjgl.opengl.GL11;

public class MenuMain {
	public static short menuScale = 2;
	public static short offX;
	public static short offY;
	public static short newOff;
	
	public static byte selected = -1;

	public static String[] items = new String[] {
		"Play",
		"Options",
		"Statistics",
		"Help",
		"Quit"
	};
	
	public static void init() {
		offX = (short)(2*Renderer.charHeight*Renderer.scale);
		offY = (short)(Renderer.height-Renderer.charHeight*2*Renderer.scale);
		newOff = (short)((Renderer.charHeight+2)*Renderer.scale*menuScale);
	}
	
	public static void think() {
		selected = -1;
		int toffY = offY;
		for(byte i = 0; i < items.length; i++) {
			if(toffY > Events.mouseY && (toffY-Renderer.charHeight*Renderer.scale*menuScale) <= Events.mouseY) selected = i;
			Renderer.drawString(items[i], offX, toffY, Renderer.scale*menuScale);
			toffY -= newOff;
		}
		
		if(Events.bindState[0][0] == 0) // TODO: управление стрелочками и enter
		switch(selected) {
		case 0:
			playPress();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			quitPress();
			break;
		}
	}
	
	private static void playPress() {
		MenuManager.menuWindow = MenuManager.WINDOW_MAIN;
		Logic.initGame();
	}

	private static void quitPress() {
		//TODO: 
		System.exit(1);
	}

	public static void render() {
		int toffY = offY - Renderer.charHeight*Renderer.scale*menuScale;
		for(byte i = 0; i < items.length; i++) {
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
			if(i == selected) GL11.glColor3f(1,1,1);
			Renderer.drawString(items[i], offX, toffY, Renderer.scale*menuScale);
			toffY -= newOff;
		}
	}
}
