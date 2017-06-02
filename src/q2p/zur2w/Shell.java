package q2p.zur2w;

public class Shell {
	public float x0;
	public float y0;
	public float x1;
	public float y1;
		
	public Shell(float x, float y, float angle) {
		x0 = x;
		y0 = y;
		x1 = (float)(x0 + Math.cos(Math.toRadians(angle))*2);
		y1 = (float)(y0 + Math.sin(Math.toRadians(angle))*2);
	}
}
