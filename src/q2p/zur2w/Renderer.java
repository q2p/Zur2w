package q2p.zur2w;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import q2p.zur2w.actors.Actor;
import q2p.zur2w.actors.Enemy;
import q2p.zur2w.map.Location;
import q2p.zur2w.map.Map;

public class Renderer {
	public static short width;
	public static short height;
	public static short scale;
	private static short scaleDiv = 256;
	private static short tileSize;
	
	private static final byte charWidth = 5;
	public static final byte charHeight = 11; 
	public static final float textureCharXSize = (float)charWidth/1024f; 
	public static final float textureCharY = (float)charHeight/1024f; 
		
	private static boolean fullscreen = false;
	
	private static int textureID;
	
	public static ArrayList<float[]> staticBloodBursts = new ArrayList<float[]>(); // {x0,y0,x1,y1,tx0,ty0,tx1,ty1}
	public static ArrayList<float[]> dynamicBloodBursts = new ArrayList<float[]>(); // {x0,y0,x1,y1,tx0,ty0,tx1,ty1,timeLeft}
	public static ArrayList<float[]> dynamicShells = new ArrayList<float[]>(); // {cx,cy,tx0,tx1,timeleft,velx,vely,flyrot}
	public static ArrayList<float[]> staticShells = new ArrayList<float[]>(); // {cx,cy,tx0,tx1,flyrot}
	public static ArrayList<float[]> deathSprites = new ArrayList<float[]>(); // {x0,y0,x1,y1,tx0,tx1}
	private static final float fallingTime = 0.2f;
	
	public static void init() {
		if(fullscreen) {
			// TODO: add switch multiscreen support
			width = (short) Display.getDesktopDisplayMode().getWidth();
			height = (short) Display.getDesktopDisplayMode().getHeight();
			//TODO: add support to rotated screens (not 16:9 but 9:16)
			try {
				Display.setDisplayMode(Display.getDesktopDisplayMode());
				Display.setFullscreen(true);
			} catch (LWJGLException e) {
				//TODO: catch
				e.printStackTrace();
				System.exit(1);
			}
		} else {
			height = 512;
			width = (short)(3*height/2);
			try {
				Display.setDisplayMode(new DisplayMode(width, height));
			} catch (LWJGLException e) {
				//TODO: catch
				e.printStackTrace();
				System.exit(1);
			}
		}
		try {
			Display.setTitle("zur2w");
			Display.create();
		} catch (LWJGLException e) {
			//TODO: catch
			e.printStackTrace();
			System.exit(1);
		}
		Mouse.setGrabbed(true);
		scale = (short) (height / scaleDiv);
		tileSize = (short)(16*scale);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		textureID = Loader.loadTexture("/textures.png");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}
	
	public static void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    switch(Logic.gameState) {
	    case Logic.STATE_GAME: renderGame(); break;
	    case Logic.STATE_MENU: MenuManager.render(); break;
	    }
		Display.update();
	}
	
	private static void renderGame() {
		drawGameWorld();
		drawSideBar();
		drawFPS();
	}

	private static void drawMiniMap() {
		short mapBlockSize = (short)((width-height)/Map.size);
		GL11.glBegin(GL11.GL_QUADS);
			for(byte y = 0; y < Map.size; y++) {
				for(byte x = 0; x < Map.size; x++) {
					byte cl = (byte)(Map.tiles[y][x].solid?0:1);
					GL11.glColor3f(cl,cl,cl);
					GL11.glVertex2i(mapBlockSize*x, mapBlockSize*y+height/2);
					GL11.glVertex2i(mapBlockSize*(x+1), mapBlockSize*y+height/2);
					GL11.glVertex2i(mapBlockSize*(x+1), mapBlockSize*(y+1)+height/2);
					GL11.glVertex2i(mapBlockSize*x, mapBlockSize*(y+1)+height/2);
				}
			}
			GL11.glColor3f(0,0,1);
			GL11.glVertex2i((int)Math.round((mapBlockSize*(Logic.player.position.getX()-0.45))), (int)Math.round((mapBlockSize*(Logic.player.position.getY())-0.45)+height/2));
			GL11.glVertex2i((int)Math.round((mapBlockSize*(Logic.player.position.getX()+0.45))), (int)Math.round((mapBlockSize*(Logic.player.position.getY())-0.45)+height/2));
			GL11.glVertex2i((int)Math.round((mapBlockSize*(Logic.player.position.getX()+0.45))), (int)Math.round((mapBlockSize*(Logic.player.position.getY())+0.45)+height/2));
			GL11.glVertex2i((int)Math.round((mapBlockSize*(Logic.player.position.getX()-0.45))), (int)Math.round((mapBlockSize*(Logic.player.position.getY())+0.45)+height/2));
			for(Enemy e : Logic.enemies) {
				if(e.canSee(Logic.player.position)) GL11.glColor3f(1,0,0);
				else GL11.glColor3f(0,1,0);
				GL11.glVertex2i((int)Math.round((mapBlockSize*(e.position.getX()-0.45))), (int)Math.round((mapBlockSize*(e.position.getY())-0.45)+height/2));
				GL11.glVertex2i((int)Math.round((mapBlockSize*(e.position.getX()+0.45))), (int)Math.round((mapBlockSize*(e.position.getY())-0.45)+height/2));
				GL11.glVertex2i((int)Math.round((mapBlockSize*(e.position.getX()+0.45))), (int)Math.round((mapBlockSize*(e.position.getY())+0.45)+height/2));
				GL11.glVertex2i((int)Math.round((mapBlockSize*(e.position.getX()-0.45))), (int)Math.round((mapBlockSize*(e.position.getY())+0.45)+height/2));
			}
		GL11.glEnd();
	}
	
	private static void drawSideBar() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(0,0,0);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2i(0, 0);
			GL11.glVertex2i(width-height, 0);
			GL11.glVertex2i(width-height, height);
			GL11.glVertex2i(0, height);
		GL11.glEnd();
		drawMiniMap();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	private static void drawGameWorld() {
		GL11.glPushMatrix();
			initViewPort();
			drawFloor();
			drawStaticBulletShells();
			drawStaff();
			drawDeathSprites();
			drawStaticBloodBursts();
			drawDynamicBloodBursts();
			//drawStaticBulletShells();
			drawDynamicBulletShells();
			drawActors();
			drawWalls();
			drawOver();
			drawRay();
			//drawFOV();
		GL11.glPopMatrix();
	}

	private static void initViewPort() {
		//GL11.glMatrixMode(GL11.GL_VIEWPORT);
		GL11.glLoadIdentity();
		GL11.glTranslatef(width-height, 0, 0);
		GL11.glTranslatef(height/2, tileSize, 0);
		GL11.glRotatef(-Logic.player.rot+90, 0, 0, 1);
		GL11.glTranslatef(-Logic.player.position.getX()*tileSize, -Logic.player.position.getY()*tileSize, 0);
		GL11.glScalef(tileSize, tileSize, 1);
		//GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glColor3f(1,1,1);
	}

	private static void drawFloor() {
		GL11.glBegin(GL11.GL_QUADS);
		for(byte y = 0; y != Map.size; y++) {
			for(byte x = 0; x != Map.size; x++) {
				if(Map.tiles[y][x].solid)
					continue;
				GL11.glTexCoord2f(tileSize*Map.tiles[y][x].gfx/1024f, 32f/1024f);
				GL11.glVertex2i(x*tileSize, y*tileSize);

				GL11.glTexCoord2f(tileSize*(Map.tiles[y][x].gfx+1)/1024f, 32f/1024f);
				GL11.glVertex2i((x+1)*tileSize, y*tileSize);

				GL11.glTexCoord2f(tileSize*(Map.tiles[y][x].gfx+1)/1024f, 48f/1024f);
				GL11.glVertex2i((x+1)*tileSize, (y+1)*tileSize);
					
				GL11.glTexCoord2f(tileSize*Map.tiles[y][x].gfx/1024f, 48f/1024f);
				GL11.glVertex2i(x*tileSize, (y+1)*tileSize);
			}
		}
		GL11.glEnd();
		/*
		// TODO: optimization
		for(short i = 0; i < Map.tiles[].length; i++) {
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(1f/1024f*16f*(Map.mapGfx[Map.mapFloor[i][1]][Map.mapFloor[i][0]]), 1f/1024f*32);
				GL11.glVertex2i(Map.mapFloor[i][0], Map.mapFloor[i][1]);
				
				GL11.glTexCoord2f(1f/1024f*16*(Map.mapGfx[Map.mapFloor[i][1]][Map.mapFloor[i][0]]+1), 1f/1024f*32);
				GL11.glVertex2i(Map.mapFloor[i][0]+1, Map.mapFloor[i][1]);
				
				GL11.glTexCoord2f(1f/1024f*16*(Map.mapGfx[Map.mapFloor[i][1]][Map.mapFloor[i][0]]+1), 1f/1024f*48);
				GL11.glVertex2i(Map.mapFloor[i][0]+1, Map.mapFloor[i][1]+1);
				
				GL11.glTexCoord2f(1f/1024f*16*(Map.mapGfx[Map.mapFloor[i][1]][Map.mapFloor[i][0]]), 1f/1024f*48);
				GL11.glVertex2i(Map.mapFloor[i][0], Map.mapFloor[i][1]+1);
			GL11.glEnd();
		}*/
	}
	
	private static void drawWalls() {
		GL11.glBegin(GL11.GL_QUADS);
		for(byte y = 0; y != Map.size; y++) {
			for(byte x = 0; x != Map.size; x++) {
				if(!Map.tiles[y][x].solid)
					continue;
				GL11.glTexCoord2f(tileSize*Map.tiles[y][x].gfx/1024f, 32f/1024f);
				GL11.glVertex2i(x*tileSize, y*tileSize);

				GL11.glTexCoord2f(tileSize*(Map.tiles[y][x].gfx+1)/1024f, 32f/1024f);
				GL11.glVertex2i((x+1)*tileSize, y*tileSize);

				GL11.glTexCoord2f(tileSize*(Map.tiles[y][x].gfx+1)/1024f, 48f/1024f);
				GL11.glVertex2i((x+1)*tileSize, (y+1)*tileSize);
					
				GL11.glTexCoord2f(tileSize*Map.tiles[y][x].gfx/1024f, 48f/1024f);
				GL11.glVertex2i(x*tileSize, (y+1)*tileSize);
			}
		}
		GL11.glEnd();
		/*
		// TODO: optimization
		for(short i = 0; i < Map.mapWalls.length; i++) {
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(1f/1024f*16f*(Map.mapGfx[Map.mapWalls[i][1]][Map.mapWalls[i][0]]), 1f/1024f*32);
				GL11.glVertex2i(Map.mapWalls[i][0], Map.mapWalls[i][1]);
				
				GL11.glTexCoord2f(1f/1024f*16*(Map.mapGfx[Map.mapWalls[i][1]][Map.mapWalls[i][0]]+1), 1f/1024f*32);
				GL11.glVertex2i(Map.mapWalls[i][0]+1, Map.mapWalls[i][1]);
				
				GL11.glTexCoord2f(1f/1024f*16*(Map.mapGfx[Map.mapWalls[i][1]][Map.mapWalls[i][0]]+1), 1f/1024f*48);
				GL11.glVertex2i(Map.mapWalls[i][0]+1, Map.mapWalls[i][1]+1);
				
				GL11.glTexCoord2f(1f/1024f*16*(Map.mapGfx[Map.mapWalls[i][1]][Map.mapWalls[i][0]]), 1f/1024f*48);
				GL11.glVertex2i(Map.mapWalls[i][0], Map.mapWalls[i][1]+1);
			GL11.glEnd();
		}*/
	}
	
	private static void drawStaff() {
		for(short i = 0; i < Map.mapStuff.length; i++) {
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(16f/1024f*Map.mapStuff[i][0], 64f/1024f);
				GL11.glVertex2i(Map.mapStuff[i][1], Map.mapStuff[i][2]);
				
				GL11.glTexCoord2f(16f/1024f*(Map.mapStuff[i][0]+1), 64f/1024f);
				GL11.glVertex2i(Map.mapStuff[i][1]+1, Map.mapStuff[i][2]);
				
				GL11.glTexCoord2f(16f/1024f*(Map.mapStuff[i][0]+1), 80f/1024f);
				GL11.glVertex2i(Map.mapStuff[i][1]+1, Map.mapStuff[i][2]+1);
				
				GL11.glTexCoord2f(16f/1024f*Map.mapStuff[i][0], 80f/1024f);
				GL11.glVertex2i(Map.mapStuff[i][1], Map.mapStuff[i][2]+1);
			GL11.glEnd();
		}
	}
	
	private static void drawOver() {
		for(short i = 0; i < Map.mapOver.length; i++) {
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(16f/1024f*Map.mapOver[i][0], 48f/1024f);
				GL11.glVertex2i(Map.mapOver[i][1], Map.mapOver[i][2]);
				
				GL11.glTexCoord2f(16f/1024f*(Map.mapOver[i][0]+1), 48f/1024f);
				GL11.glVertex2i(Map.mapOver[i][1]+1, Map.mapOver[i][2]);
				
				GL11.glTexCoord2f(16f/1024f*(Map.mapOver[i][0]+1), 64f/1024f);
				GL11.glVertex2i(Map.mapOver[i][1]+1, Map.mapOver[i][2]+1);
				
				GL11.glTexCoord2f(16f/1024f*Map.mapOver[i][0], 64f/1024f);
				GL11.glVertex2i(Map.mapOver[i][1], Map.mapOver[i][2]+1);
			GL11.glEnd();
		}
	}
	
	private static void drawActors() {
		for(Enemy ce : Logic.enemies) {
			drawActor(ce);
		}
		drawActor(Logic.player);
	}
	
	private static void drawActor(Actor actor) {
		GL11.glTranslatef(actor.position.getX(), actor.position.getY(), 0);
		GL11.glRotatef(actor.rot-90,0,0,1);
		drawActorBody(actor);
		drawWeapon(actor);
		GL11.glRotatef(-actor.rot+90,0,0,1);
		GL11.glTranslatef(-actor.position.getX(), -actor.position.getY(), 0);
	}
	
	private static void drawActorBody(Actor actor) {
		GL11.glTranslatef(-0.5f, -0.5f,0);
		GL11.glBegin(GL11.GL_QUADS);
			float tx1 = 0*16f/1024f;
			float ty1 = 9*16f/1024f;
			float tx2 = 1*16f/1024f;
			float ty2 = 10*16f/1024f;
			GL11.glTexCoord2f(tx1, ty2); GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(tx2, ty2); GL11.glVertex2f(1, 0);
			GL11.glTexCoord2f(tx2, ty1); GL11.glVertex2f(1, 1);
			GL11.glTexCoord2f(tx1, ty1); GL11.glVertex2f(0, 1);
		GL11.glEnd();
		GL11.glTranslatef(0.5f, 0.5f,0);
	}
	
	private static void drawWeapon(Actor actor) {
		/*if(actor.currentWeaponSlot == null) return;
		GL11.glTranslatef(-0.5f, -0.5f,0);
		if(actor.currentWeaponSlot.leftMuzzleFlash > 0) {
			GL11.glBegin(GL11.GL_QUADS);
				float tx1 = 3*16f/1024f;
				float ty1 = 10*16f/1024f;
				float tx2 = 4*16f/1024f;
				float ty2 = 12*16f/1024f;
				GL11.glTexCoord2f(tx1, ty2); GL11.glVertex2f(0, 0);
				GL11.glTexCoord2f(tx2, ty2); GL11.glVertex2f(1, 0);
				GL11.glTexCoord2f(tx2, ty1); GL11.glVertex2f(1, 2);
				GL11.glTexCoord2f(tx1, ty1); GL11.glVertex2f(0, 2);
			GL11.glEnd();
		}*/
		GL11.glBegin(GL11.GL_QUADS);
			float tx1 = 0*16f/1024f;
			float ty1 = 10*16f/1024f;
			float tx2 = 1*16f/1024f;
			float ty2 = 12*16f/1024f;
			GL11.glTexCoord2f(tx1, ty2); GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(tx2, ty2); GL11.glVertex2f(1, 0);
			GL11.glTexCoord2f(tx2, ty1); GL11.glVertex2f(1, 2);
			GL11.glTexCoord2f(tx1, ty1); GL11.glVertex2f(0, 2);
		GL11.glEnd();
		GL11.glTranslatef(0.5f, 0.5f,0);
	}
		
	private static void drawRay() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(1, 0, 0);
		GL11.glTranslatef(Logic.player.position.getX(), Logic.player.position.getY(), 0);
		GL11.glRotatef(Logic.player.rot-90,0,0,1);
		
		GL11.glBegin(GL11.GL_LINES);
			GL11.glVertex2f(0, 0);
			GL11.glVertex2f(0, Map.size);
		GL11.glEnd();
		
		GL11.glRotatef(-Logic.player.rot+90,0,0,1);
		GL11.glTranslatef(-Logic.player.position.getX(), -Logic.player.position.getY(), 0);
		GL11.glColor3f(1, 1, 1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	private static void drawFPS() {
		GL11.glColor3f(1, 1, 1);
		drawString(""+Timing.curFps, width-70, 40, scale);
		drawString(""+Timing.thisFrameFPS, width-70, 60, scale);
		drawString(""+Logic.player.currentWeaponSlot.bulletsInMagazine, width-70, 50, scale);
	}
	
	private static void drawDynamicBloodBursts() {
		GL11.glBegin(GL11.GL_QUADS);
		for(int i = 0; i < dynamicBloodBursts.size();) {
			float[] bl = dynamicBloodBursts.get(i);
			GL11.glTexCoord2f(bl[2], bl[5]); GL11.glVertex2f(bl[0]-0.5f, bl[1]-0.5f); //00
			GL11.glTexCoord2f(bl[4], bl[5]); GL11.glVertex2f(bl[0]+0.5f, bl[1]-0.5f); //10
			GL11.glTexCoord2f(bl[4], bl[3]); GL11.glVertex2f(bl[0]+0.5f, bl[1]+0.5f); //11
			GL11.glTexCoord2f(bl[2], bl[3]); GL11.glVertex2f(bl[0]-0.5f, bl[1]+0.5f); //01
			bl[6] -= Timing.deltaF;
			bl[0] += bl[7]*Timing.deltaF;
			bl[1] += bl[8]*Timing.deltaF;
			if(bl[6] <= 0) {
				staticBloodBursts.add(new float[]{bl[0]-0.5f,bl[1]-0.5f,bl[0]+0.5f,bl[1]+0.5f,bl[2],bl[3],bl[4],bl[5]});
				dynamicBloodBursts.remove(i);
			}
			else i++;
		}
		GL11.glEnd();
	}
	
	private static void drawStaticBloodBursts() {
		GL11.glBegin(GL11.GL_QUADS);
		for(float[] bl : staticBloodBursts) {
			GL11.glTexCoord2f(bl[4], bl[7]); GL11.glVertex2f(bl[0], bl[1]); //00
			GL11.glTexCoord2f(bl[6], bl[7]); GL11.glVertex2f(bl[2], bl[1]); //10
			GL11.glTexCoord2f(bl[6], bl[5]); GL11.glVertex2f(bl[2], bl[3]); //11
			GL11.glTexCoord2f(bl[4], bl[5]); GL11.glVertex2f(bl[0], bl[3]); //01
		}
		GL11.glEnd();
	}
	
	private static void drawDeathSprites() {
		float ty1 = 5*16f/1024f;
		float ty2 = 7*16f/1024f;
		GL11.glBegin(GL11.GL_QUADS);
		for(float[] bl : deathSprites) {
			GL11.glTexCoord2f(bl[4], ty2); GL11.glVertex2f(bl[0], bl[1]); //00
			GL11.glTexCoord2f(bl[5], ty2); GL11.glVertex2f(bl[2], bl[1]); //10
			GL11.glTexCoord2f(bl[5], ty1); GL11.glVertex2f(bl[2], bl[3]); //11
			GL11.glTexCoord2f(bl[4], ty1); GL11.glVertex2f(bl[0], bl[3]); //01
		}
		GL11.glEnd();
	}
	
	public static void addBloodBurst(float x, float y, byte hard, float rot) {
		for(short i = 0; i < 4; i++) {
			int offTx = Logic.random.nextInt(5);
			float dir = (float)Math.toRadians(rot-45+Logic.random.nextFloat()*90);
			float scl = Logic.random.nextFloat()*12;
			dynamicBloodBursts.add(new float[]{x,y,(float)offTx*16f/1024f,(float)(7+hard)*16f/1024f,(float)(offTx+1)*16f/1024f,(float)(8+hard)*16f/1024f, fallingTime, (float)Math.cos(dir)*scl, (float)Math.sin(dir)*scl}); // {x0,y0,x1,y1,tx0,ty0,tx1,ty1,timeLeft,velx,vely}
		}
	}
	
	public static void addShell(float x, float y, byte type, float rot, float velX, float velY) {
			float dir = Logic.random.nextFloat()*360;
			float speedScale = 4+Logic.random.nextFloat()*8;
			dynamicShells.add(new float[]{
					x,y,
					(float)type*1f/1024f,(float)(type+1)*1f/1024f,
					fallingTime,
					(float)(Math.cos(Math.toRadians(rot))*speedScale + velX), (float)(Math.sin(Math.toRadians(rot))*speedScale + velY),
					dir});
	}
	
	public static void addDeathSprite(Location location) {
		for(byte i = 0; i < 8; i++) {
			addBloodBurst(location.getX(), location.getY(), (byte)1, (float)i*360f/8f);
		}
		int offTx = Logic.random.nextInt(3);
		deathSprites.add(new float[]{location.getX()-1,location.getY()-1,location.getX()+1,location.getY()+1,(float)offTx*2f*16f/1024f,(float)(offTx+1)*2f*16f/1024f}); // {x0,y0,x1,y1,tx0,tx1}
	}
	
	private static void drawDynamicBulletShells() {
		for(int i = 0; i < dynamicShells.size();) {
			float[] bl = dynamicShells.get(i);
			GL11.glTranslatef(bl[0],bl[1],0);
			GL11.glRotatef(bl[7],0,0,1);
				GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(bl[2], 196f/1024f);	GL11.glVertex2f(-0.5f/16f,	-2f/16f);	//00
					GL11.glTexCoord2f(bl[3], 196f/1024f);	GL11.glVertex2f(0.5f/16f,	-2f/16f);	//10
					GL11.glTexCoord2f(bl[3], 192f/1024f);	GL11.glVertex2f(0.5f/16f,	2f/16f);	//11
					GL11.glTexCoord2f(bl[2], 192f/1024f);	GL11.glVertex2f(-0.5f/16f,	2f/16f);	//01
				GL11.glEnd();
			GL11.glRotatef(-bl[7],0,0,1);
			GL11.glTranslatef(-bl[0],-bl[1],0);
			bl[7] += 180*Timing.deltaF;
			bl[4] -= Timing.deltaF;
			bl[0] += bl[5]*Timing.deltaF;
			bl[1] += bl[6]*Timing.deltaF;
			if(bl[4] <= 0) {
				// TODO: Оптимизиировать: подсчитать позиции вершин гильзы заранее для повышения производительности
				staticShells.add(new float[]{bl[0],bl[1],bl[2],bl[3],bl[7]});
				dynamicShells.remove(i);
			} else i++;
		}
	}
	
	private static void drawStaticBulletShells() {
		while(staticShells.size() > 1024) {
			staticShells.remove(0);
		}
		for(float[] bl : staticShells) {
			GL11.glTranslatef(bl[0],bl[1],0);
			GL11.glRotatef(bl[4],0,0,1);
				GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(bl[2], 196f/1024f);	GL11.glVertex2f(-0.5f/16f,	-2f/16f);	//00
					GL11.glTexCoord2f(bl[3], 196f/1024f);	GL11.glVertex2f(0.5f/16f,	-2f/16f);	//10
					GL11.glTexCoord2f(bl[3], 192f/1024f);	GL11.glVertex2f(0.5f/16f,	2f/16f);	//11
					GL11.glTexCoord2f(bl[2], 192f/1024f);	GL11.glVertex2f(-0.5f/16f,	2f/16f);	//01
				GL11.glEnd();
			GL11.glRotatef(-bl[4],0,0,1);
			GL11.glTranslatef(-bl[0],-bl[1],0);
		}
	}
	
	public static void drawCursor() {
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,		16f/1024f);	GL11.glVertex2i(Events.mouseX,			Events.mouseY+1);
			GL11.glTexCoord2f(16f/1024f,16f/1024f);	GL11.glVertex2i(Events.mouseX+tileSize,	Events.mouseY+1);
			GL11.glTexCoord2f(16f/1024f,32f/1024f);	GL11.glVertex2i(Events.mouseX+tileSize,	Events.mouseY-tileSize+1);
			GL11.glTexCoord2f(0,		32f/1024f);	GL11.glVertex2i(Events.mouseX,			Events.mouseY-tileSize+1);
		GL11.glEnd();
	}

	public static void drawString(String string, int x, int y, int size) {
		short[] str = TextProc.convertString(string);
		for(int i = 0; i < str.length; i++) {
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(textureCharXSize*str[i],		textureCharY);	GL11.glVertex2i(x,					y);
				GL11.glTexCoord2f(textureCharXSize*(str[i]+1),	textureCharY);	GL11.glVertex2i(x+charWidth*size,	y);
				GL11.glTexCoord2f(textureCharXSize*(str[i]+1),	0);				GL11.glVertex2i(x+charWidth*size,	y+(charHeight*size));
				GL11.glTexCoord2f(textureCharXSize*str[i],		0);				GL11.glVertex2i(x,					y+(charHeight*size));
			GL11.glEnd();
			x += (charWidth+1)*size;
		}
	}
}