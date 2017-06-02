package q2p.zur2w.map;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import q2p.zur2w.Logic;
import q2p.zur2w.Timing;
import q2p.zur2w.actors.Actor;
import q2p.zur2w.actors.Enemy;

public class Physics {
	public static final byte bodyHitBoxRes = 9;
	public static final float[][] bodyHitBoxVert = generateHitBox();
	
	public static float[] lineXaabbLine(float line1[], float line2[]) { // lines: {x0,y0,x1,y1}
		float s1_x = line1[2] - line1[0];
		float s1_y = line1[3] - line1[1];
		float s2_x = line2[2] - line2[0];
		float s2_y = line2[3] - line2[1];

		float s = (-s1_y * (line1[0] - line2[0]) + s1_x * (line1[1] - line2[1])) / (-s2_x * s1_y + s1_x * s2_y);
		float t = (s2_x * (line1[1] - line2[1]) - s2_y * (line1[0] - line2[0])) / (-s2_x * s1_y + s1_x * s2_y);

		if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
			return new float[]{line1[0] + (t * s1_x), line1[1] + (t * s1_y)};
		}
		return null;
	}
	
	private static float[][] generateHitBox() {
		float[][] ret = new float[bodyHitBoxRes][2];
		float step = 360/(bodyHitBoxRes-1);
		for(byte i = 0; i < bodyHitBoxRes-1; i++) {
			ret[i][0] = (float)(Math.cos(Math.toRadians(step*i))*0.5);
			ret[i][1] = (float)(Math.sin(Math.toRadians(step*i))*0.5);
		}
		ret[bodyHitBoxRes-1][0] = ret[0][0];
		ret[bodyHitBoxRes-1][1] = ret[0][1];
		return ret;
	}

	public static float[][] lineXaabbBox(float[] line, float[] box) {
		boolean hasColl = false;
		float[][] ret = new float[2][2];

		ret[0][0] = -1;
		ret[0][1] = -1;

		float checkLines[][] = new float[4][4];

		checkLines[0][0] = box[0];			//
		checkLines[0][1] = box[1];			//|[]
		checkLines[0][2] = box[0];			// 
		checkLines[0][3] = box[1] + box[3];

		checkLines[1][0] = box[0];
		checkLines[1][1] = box[1] + box[3];	// --
		checkLines[1][2] = box[0] + box[2];	// []
		checkLines[1][3] = box[1] + box[3];	//

		checkLines[2][0] = box[0] + box[2];	//
		checkLines[2][1] = box[1];			// []|
		checkLines[2][2] = box[0] + box[2];	//
		checkLines[2][3] = box[1] + box[3];

		checkLines[3][0] = box[0];			//
		checkLines[3][1] = box[1];			// []
		checkLines[3][2] = box[0] + box[2];	// --
		checkLines[3][3] = box[1];

		for (byte i = 0; i < 4; i++)
		{
			float[] nRet = lineXaabbLine(line, checkLines[i]);
			if (nRet == null) continue;
			if (!hasColl) {
				ret[0] = nRet;
				ret[1][0] = i;
				hasColl = true;
			}
			else if (Math.abs(nRet[0] - line[0]) < Math.abs(ret[0][0] - line[0])) ret[0] = nRet;
		}
		if (!hasColl) return null;
		return ret;
	}
	
	public static float[][] lineXaabbHitBox(float[] line, float[] hitBoxCenter) {
		boolean hasColl = false;
		float[][] ret = new float[2][2];

		ret[0][0] = -1;
		ret[0][1] = -1;

		for(byte i = 0; i < bodyHitBoxRes-1; i++) {
			GL11.glVertex2f(bodyHitBoxVert[i][0], bodyHitBoxVert[i][1]);
			GL11.glVertex2f(bodyHitBoxVert[i+1][0], bodyHitBoxVert[i+1][1]);
		}

		for(byte i = 0; i < bodyHitBoxRes-1; i++) {
			float[] nRet = lineXaabbLine(line, new float[]{bodyHitBoxVert[i][0]+hitBoxCenter[0],bodyHitBoxVert[i][1]+hitBoxCenter[1],bodyHitBoxVert[i+1][0]+hitBoxCenter[0],bodyHitBoxVert[i+1][1]+hitBoxCenter[1]});
			if (nRet == null) continue;
			if (!hasColl) {
				ret[0] = nRet;
				ret[1][0] = i;
				hasColl = true;
			}
			else if (Math.abs(nRet[0] - line[0]) < Math.abs(ret[0][0] - line[0])) ret[0] = nRet;
		}
		if (!hasColl) return null;
		return ret;
	}
	
	private static void actorPhysics(Actor act) {
		act.colidedWith = -1;
		//TODO: optimize this, cuz its horrible as FUCK!
		Vector p = act.velocity.multiply(Timing.deltaF);
		float px = p.getX();
		float py = p.getY();
		float pdist = p.length();
		byte xbeg = (byte) act.position.getX();
		byte ybeg = (byte) act.position.getY();
		byte xend = (byte) (act.position.getX()+px);
		byte yend = (byte) (act.position.getY()+py);
		
		if(xbeg > xend) {
			byte i = xend;
			xend = xbeg;
			xbeg = i;
		}
		if(ybeg > yend) {
			byte i = yend;
			yend = ybeg;
			ybeg = i;
		}
		xbeg -= 2;
		ybeg -= 2;
		xend += 2;
		yend += 2;
		
		if(xbeg < 0) xbeg = 0;
		if(ybeg < 0) ybeg = 0;
		if(xend > Map.size) xend = Map.size;
		if(yend > Map.size) yend = Map.size;
		
		float[][] coll = null;
		for(byte y = ybeg; y < yend; y++) {
			for(byte x = xbeg; x < xend; x++) {
				if(!Map.tiles[y][x].solid)
					continue;
				float[][] ncoll = lineXaabbBox(new float[]{act.position.getX(),act.position.getY(),act.position.getX() + act.velocity.multiply(Timing.deltaF).getX()*Timing.deltaF, act.position.getY() + act.velocity.multiply(Timing.deltaF).getY()},new float[]{(float) (x-0.4),(float) (y-0.4),(float) 1.8,(float) 1.8});
				if(ncoll == null) continue;
				if(coll == null) coll = ncoll;
				else {
					float xd = coll[0][0] - act.position.getX();
					float yd = coll[0][1] - act.position.getY();
					float oDist = xd*xd + yd*yd;
					xd = ncoll[0][0] - act.position.getX();
					yd = ncoll[0][1] - act.position.getY();
					float nDist = xd*xd + yd*yd;
					if (nDist < oDist) coll = ncoll;
				}
			}
		}
				
		if(coll != null) {
			Vector v = new Vector(coll[0][0]-act.position.getX(), coll[0][1]-act.position.getY());
			switch ((int)(coll[1][0])) {
				case 0:
					v = v.setX(v.getX()-0.02f);
					break;
				case 1:
					v = v.setY(v.getY()+0.02f);
					break;
				case 2:
					v = v.setX(v.getX()+0.02f);
					break;
				case 3:
					v = v.setY(v.getY()-0.02f);
					break;
			}
			act.velocity = v;
			
			//float dist = (float) ((pdist - Math.sqrt(act.velX*Timing.deltaF*act.velX*Timing.deltaF+act.velY*Timing.deltaF*act.velY*Timing.deltaF)) / pdist);
			float dist = (pdist - act.velocity.multiply(Timing.deltaF).length()) / pdist;
			
			px *= dist;
			py *= dist;
			
			if(coll[1][0] == 0 || coll[1][0] == 2) {
				px = 0;
			} else {
				py = 0;
			}
		}
		if(coll != null){
			act.colidedWith = (byte)Math.round(coll[1][0]);
		}
		
		act.position.add(act.velocity.multiply(Timing.deltaF));
		
		if(coll == null) return;
		
		xbeg = (byte)(act.position.getX());
		ybeg = (byte)(act.position.getY());
		xend = (byte)(act.position.getX()+px);
		yend = (byte)(act.position.getY()+px);
		
		if(xbeg > xend) {
			byte i = xend;
			xend = xbeg;
			xbeg = i;
		}
		if(ybeg > yend) {
			byte i = yend;
			yend = ybeg;
			ybeg = i;
		}
		xbeg -= 2;
		ybeg -= 2;
		xend += 2;
		yend += 2;
		
		if(xbeg < 0) xbeg = 0;
		if(ybeg < 0) ybeg = 0;
		if(xend > Map.size) xend = Map.size;
		if(yend > Map.size) yend = Map.size;
		
		coll = null;
		for(byte y = ybeg; y < yend; y++) {
			for(byte x = xbeg; x < xend; x++) {
				if(!Map.tiles[y][x].solid)
					continue;
				float[][] ncoll = lineXaabbBox(new float[]{act.position.getX(),act.position.getY(),act.position.getX() + px, act.position.getY() + py},new float[]{(float) (x-0.4),(float) (y-0.4),(float) 1.8,(float) 1.8});
				if(ncoll == null) continue;
				if(coll == null) coll = ncoll;
				else {
					float xd = coll[0][0] - act.position.getX();
					float yd = coll[0][1] - act.position.getY();
					float oDist = xd*xd + yd*yd;
					xd = ncoll[0][0] - act.position.getX();
					yd = ncoll[0][1] - act.position.getY();
					float nDist = xd*xd + yd*yd;
					if (nDist < oDist) coll = ncoll;
				}
			}
		}
		
		if(coll != null) {
			px = coll[0][0]-act.position.getX();
			py = coll[0][1]-act.position.getY();
			switch ((byte)coll[1][0]) {
				case 0:
					px -= 0.02;
					break;
				case 1:
					py += 0.02;
					break;
				case 2:
					px += 0.02;
					break;
				case 3:
					py -= 0.02;
					break;
			}
			
			if(coll[1][0] == 0 || coll[1][0] == 2) {
				px = 0;
			} else {
				py = 0;
			}
		}
		if(coll != null){
			act.colidedWith = (byte)Math.round(coll[1][0]);
		}
		
		act.position = act.position.add(new Vector(px, py));
	}
	
	public static void think() {
		actorPhysics(Logic.player);
		for(Enemy e : Logic.enemies) actorPhysics(e);
	}

	public static byte[][] goesTrough(float x0, float y0, float x1, float y1) {
		short xbeg = (short) x0;
		short ybeg = (short) y0;
		short xend = (short)(x1+1);
		short yend = (short)(y1+1);
		
		if(xbeg > xend) {
			short i = xend;
			xend = xbeg;
			xbeg = i;
		}
		if(ybeg > yend) {
			short i = yend;
			yend = ybeg;
			ybeg = i;
		}
		
		if(xbeg < 0) xbeg = 0;
		if(ybeg < 0) ybeg = 0;
		if(xend > Map.size) xend = Map.size;
		if(yend > Map.size) yend = Map.size;

		ArrayList<short[]> xColls = new ArrayList<short[]>();
		ArrayList<short[]> yColls = new ArrayList<short[]>();
				
		float[] shotLine = new float[]{x0,y0,x1,y1};
		
		float[] coll = null;
		
		for(short x = xbeg; x <= xend; x++) {
			coll = lineXaabbLine(shotLine, new float[]{x,0,x,Map.size});
			if(coll!= null) xColls.add(new short[]{x,(short)coll[1]});
		}
		for(short y = ybeg; y <= yend; y++) {
			coll = lineXaabbLine(shotLine, new float[]{0,y,Map.size,y});
			if(coll!= null) yColls.add(new short[]{(short)coll[0], y});
		}
		
		ArrayList<short[]> totalColls = new ArrayList<short[]>();
		
		for(short x = 0; x < xColls.size(); x++) {
			totalColls.add(new short[]{			xColls.get(x)[0],			xColls.get(x)[1]});
			totalColls.add(new short[]{	(short)(xColls.get(x)[0]-1),		xColls.get(x)[1]});
		}
		for(short y = 0; y < yColls.size(); y++) {
			totalColls.add(new short[]{			yColls.get(y)[0],			yColls.get(y)[1]});
			totalColls.add(new short[]{			yColls.get(y)[0],	(short)(yColls.get(y)[1]-1)});
		}
		xColls = null;
		yColls = null;
		for(int j = 0; j < totalColls.size(); j++) {
			for(int i = j+1; i < totalColls.size(); i++) {
				short[] curr = totalColls.get(i);
				if(totalColls.get(j)[0] == curr[0] && totalColls.get(j)[1] == curr[1]) {
					totalColls.remove(i);
				} else if(curr[0] < 0 || curr[1] < 0 || curr[0] >= Map.size || curr[1] >= Map.size) {
					totalColls.remove(i);
				} else {
					i++;
				}
			}
		}
		
		byte[][] totalCollsArr = new byte[totalColls.size()][2];
		
		for(int i = 0; i < totalCollsArr.length; i++) {
			totalCollsArr[i][0] = (byte)totalColls.get(i)[0];
			totalCollsArr[i][1] = (byte)totalColls.get(i)[1];
		}
				
		return totalCollsArr;
	}
}