package q2p.zur2w.map;

public class Location {
	private float x, y;

	public Location(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Location() {
		x = 0;
		y = 0;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public Location add(Vector vector) {
		return new Location(x+vector.getX(), y+vector.getY());
	}
	
	public float distance(Location to) {
		float dx = x - to.x;
		float dy = y - to.y;
	    return (float)Math.sqrt(dx*dx+dy*dy);
	}
	
	public float direction(Location to) {
		return (float)Math.toDegrees(Math.atan2(y - to.y, x - to.x));
	}
}
