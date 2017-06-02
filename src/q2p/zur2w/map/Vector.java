package q2p.zur2w.map;

public class Vector {
	private float x, y;
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector() {
		x = 0;
		y = 0;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public Vector setX(float x) {
		return this;
	}
	
	public Vector setY(float y) {
		return new Vector(x, this.y+y);
	}
	
	public float angle(Vector vector) {
		return (float)Math.toDegrees(Math.atan2(vector.y, vector.x) - Math.atan2(y, x)); 
	}
	
	public float angle() {
		return angle(new Vector(1, 0));
	}
	
	public float length(){
	    return (float)Math.sqrt(x*x+y*y);
	}
	
	public static Vector byDirection(float direction, float length) {
		return new Vector((float)Math.cos(Math.toRadians(direction))*length, (float)Math.sin(Math.toRadians(direction))*length);
	}
	
	public Vector add(Vector vector) {
		return new Vector(x+vector.x, y+vector.y);
	}
	
	public Vector subtract(Vector vector) {
		return new Vector(x-vector.x, y-vector.y);
	}
	
	public Vector multiply(float scale) {
		return new Vector(x*scale, y*scale);
	}
	
	public Vector normalize() {
		Vector v = new Vector();
		float len = length();
	    if(len != 0){
	        v.x /= len;
	        v.y /= len;
	    }
	    return v;
	}
}
