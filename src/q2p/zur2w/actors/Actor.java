package q2p.zur2w.actors;

import q2p.zur2w.map.Location;
import q2p.zur2w.map.Map;
import q2p.zur2w.map.Physics;
import q2p.zur2w.map.Vector;

public class Actor {
	public Location position = new Location();
	public Vector velocity = new Vector();
	public float rot = 0;
	public byte colidedWith = -1;
	public byte health = 0;
	private boolean dead;
	
	public void spawn(byte x, byte y) {
		position = new Location(x, y);
	}

	public void think() {
	}

	public boolean canSee(Location point) {
		byte[][] goesThrough = Physics.goesTrough(position.getX(),position.getY(),point.getX(),point.getY());
		for(byte[] g : goesThrough) if(Map.tiles[g[1]][g[0]].solid) return false;
		return true;
	}
	
	public void hitDamage(int damage) {
		if(dead) return;
		health -= (byte)damage;
		if(health < 0) health = 0;
		if(health == 0) dead = true;
	}
	
	public Actor() {
	}
}