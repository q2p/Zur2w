package q2p.zur2w.engine;

import java.util.Random;

public class Assist {
	private static final Random random = new Random();
	
	public static int rand(int bounds) {
		return random.nextInt(bounds);
	}
}
