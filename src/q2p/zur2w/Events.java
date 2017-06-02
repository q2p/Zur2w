package q2p.zur2w;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Events {
	public static enum Type {KeyPress, KeyRelease, MouseMoved, MousePress, MouseRelease, MouseWheel, WindowClose, WindowResize}
	public static short mouseX = 0;
	public static short mouseY = 0;
	public static float mouseDX = 0;
	public static float mouseDY = 0;
	public static int wheelD = 0;
	public static int bindState[][] = { // state, type[key,mouse,wheel] , keycode
			// States:
			//0 released
			//1 free
			//2 pressed
			//3 hold
			{1, 1, 0},// lmb
			{1, 1, 1},// rmb
			{1, 2, 1},// wheel up
			{1, 2, -1},// wheel down
			{1, 0, Keyboard.KEY_F11}, //f11
			{1, 0, Keyboard.KEY_ESCAPE},// escape
			{1, 0, Keyboard.KEY_W},// w
			{1, 0, Keyboard.KEY_A},// a
			{1, 0, Keyboard.KEY_S},// s
			{1, 0, Keyboard.KEY_D},// d
			{1, 0, Keyboard.KEY_SLASH},// shift
			{1, 0, Keyboard.KEY_LCONTROL},// ctrl
			{1, 0, Keyboard.KEY_TAB},// tab
			{1, 0, Keyboard.KEY_E},// e
	};
	
	private static void updateBinds() {
		for(byte i = 0; i < bindState.length; i++) {
			if(bindState[i][1] == 2) {
				bindState[i][0] = 1;
			} else {
				if(bindState[i][0] == 0) bindState[i][0] = 1;
				if(bindState[i][0] == 2) bindState[i][0] = 3;
			}
			mouseDX = 0;
			mouseDY = 0;
			wheelD = Mouse.getDWheel();
			if(wheelD < 0) wheelD = -1;
			if(wheelD > 0) wheelD = 1;
		}
		for(byte i = 0; i < bindState.length; i++) {
			switch(bindState[i][1]) {
			case 0: // Key
				if(bindState[i][0] < 2 && Keyboard.isKeyDown(bindState[i][2])) bindState[i][0] = 2;
				if(bindState[i][0] > 1 && !Keyboard.isKeyDown(bindState[i][2])) bindState[i][0] = 0;
				break;
			case 1: // mouse
				if(bindState[i][0] < 2 && Mouse.isButtonDown(bindState[i][2])) bindState[i][0] = 2;
				if(bindState[i][0] > 1 && !Mouse.isButtonDown(bindState[i][2])) bindState[i][0] = 0;
				break;
			case 2: // wheel
				if(bindState[i][0] < 2 && bindState[i][2] == wheelD) bindState[i][0] = 2;
				break;
			}
		}
		if(Display.isCloseRequested()) bindState[4][0] = 2;
		mouseDX = Mouse.getDX();
		mouseDY = Mouse.getDY();
		mouseX += mouseDX;
		mouseY += mouseDY;
		if(mouseX < 0) mouseX = 0;
		if(mouseY < 0) mouseY = 0;
		if(mouseX >= Renderer.width) mouseX = (short)(Renderer.width-1);
		if(mouseY >= Renderer.height) mouseY = (short)(Renderer.height-1);
	}
	
	private static void processBinds() {
		if(bindState[4][0] > 1) System.exit(0); // Exit on f11		
	}
	
	public static void processEvents() {
		updateBinds();
		processBinds();
	}
}
