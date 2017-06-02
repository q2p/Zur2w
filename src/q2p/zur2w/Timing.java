package q2p.zur2w;

public class Timing {
	private static long lastTime;
	private static long nowTime;
	private static long lastDelta;
	private static long delta = 0;
	public static float deltaF = 0;
	private static long toSleep;
	private static short fps = 250;
	// TODO: 125 or 200 or 250 fps
	// TODO: stress test with 500 fps
	private static short milisecPerFrame = (short) (1000/fps);
	public static short curFps = 0;
	private static short fpsCount = 0;
	public static short thisFrameFPS = 0;
	private static long lastFps;
	
	public static void waitTime() {
		fpsCount++;
		
		nowTime = System.nanoTime() / 1000000;
		toSleep = lastTime + milisecPerFrame - nowTime;
		try {
			if(toSleep > 0) Thread.sleep(toSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		nowTime = System.nanoTime() / 1000000;
		delta = nowTime - lastDelta;
		lastDelta = nowTime;
		lastTime += milisecPerFrame;
		if(delta > 4*milisecPerFrame) lastTime = nowTime;
		
		// FPS counter
		thisFrameFPS = (short)(1000f / (float)delta);
		if(nowTime - lastFps >= 1000) {
			lastFps = nowTime;
			curFps = fpsCount;
			fpsCount = 0;
		}
		
		// Delta
		if(delta > 2 * milisecPerFrame) delta = 4 * milisecPerFrame;
		deltaF = (float)delta / 1000f;
	}

	public static void init() {
		lastTime = System.nanoTime() / 1000000;
		lastFps = lastTime;
		lastDelta = lastTime;
	}
}
