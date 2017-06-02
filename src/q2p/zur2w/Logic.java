package q2p.zur2w;

import java.util.ArrayList;
import java.util.Random;
import q2p.zur2w.actors.Enemy;
import q2p.zur2w.actors.Player;
import q2p.zur2w.map.Map;
import q2p.zur2w.map.Physics;

public class Logic {
	public static Player player;
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static final Random random = new Random();

	public static final byte STATE_GAME = 0;
	public static final byte STATE_MENU = 1;
	public static byte gameState = STATE_MENU;
	
	private static void initWave() {
		for(short i = 0; i < Map.spawnPoints.length*4; i++) {
			Enemy ce = new Enemy();
			ce.spawn(Map.spawnPoints[i%Map.spawnPoints.length][0], Map.spawnPoints[i%Map.spawnPoints.length][1]);
			enemies.add(ce);
		}
	}

	private static void doLogic() {
		switch (gameState) {
		case STATE_GAME:
			thinkGame();
			break;
		case STATE_MENU:
			MenuManager.think();
			break;
		}
	}
	
	private static void thinkGame() {
		player.think();
		if(player == null) return;
		for(Enemy en : enemies) en.think();
		Physics.think();
	}

	public static void initGame() {
		Loader.loadMap();
		player = new Player();
		player.spawn((byte)(Map.size/2), (byte)(Map.size/2));
		initWave();
		gameState = STATE_GAME;
	}
	
	public static void init() {
		MenuManager.init();
		Timing.init();
		while(true) {
			Events.processEvents();
			doLogic();
			Renderer.render();
			Timing.waitTime();
		}
	}

	public static void gameOver() {
		gameState = STATE_MENU;
		MenuManager.menuWindow = MenuManager.WINDOW_MAIN; // TODO: gameOver screen
		deInitGame();
	}

	private static void deInitGame() {
		player = null;
		enemies.clear();
		Renderer.staticBloodBursts.clear();
		Renderer.dynamicBloodBursts.clear();
		Renderer.dynamicShells.clear();
		Renderer.staticShells.clear();
		Renderer.deathSprites.clear();
	}
}
