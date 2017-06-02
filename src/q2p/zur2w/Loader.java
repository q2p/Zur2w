package q2p.zur2w;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import q2p.zur2w.map.Map;
import q2p.zur2w.map.Tile;

public class Loader {
	public static void loadMap() {
		try {
			DataInputStream dis = new DataInputStream(Loader.class.getResourceAsStream("/map.dat"));
			Map.size = dis.readByte();
			Map.tiles = new Tile[Map.size][Map.size];
			
			for(byte y = 0; y < Map.size; y++) {
				for(short x = 0; x < Map.size; x++) {
					Map.tiles[y][x] = new Tile();
					Map.tiles[y][x].solid = dis.readByte()==1;
				}
			}
			for(byte y = 0; y < Map.size; y++) {
				for(short x = 0; x < Map.size; x++) {
					//TODO: почему требуется отнять 4 в конвертере???
					Map.tiles[y][x].gfx = dis.readShort();
				}
			}
			Map.mapStuff = new short[dis.readShort()][3];
			for(short i = 0; i < Map.mapStuff.length; i++) {
				for(byte j = 0; j < 3; j++) {
					Map.mapStuff[i][j] = dis.readShort();
				}
			}
			Map.mapOver = new short[dis.readShort()][3];
			for(short i = 0; i < Map.mapOver.length; i++) {
				for(byte j = 0; j < 3; j++) {
					Map.mapOver[i][j] = dis.readShort();
				}
			}
			Map.spawnPoints = new byte[dis.readShort()][2];
			for(short i = 0; i < Map.spawnPoints.length; i++) {
				for(byte j = 0; j < 2; j++) {
					Map.spawnPoints[i][j] = dis.readByte();
				}
			}
			Map.dropPoints = new byte[dis.readShort()][2];
			for(short i = 0; i < Map.dropPoints.length; i++) {
				for(byte j = 0; j < 2; j++) {
					Map.dropPoints[i][j] = dis.readByte();
				}
			}
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//TODO:
			Map.size = -1;
			Map.tiles = null;
			e.printStackTrace();
		}
	}
	
	public static int loadTexture(String path) {
		BufferedImage img = null;
		try {
			// TODO: make static path
			img = ImageIO.read(Loader.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			//TODO Cahtch crash dispose
			System.exit(1);
		}
		
		// Спизжено с [http://www.java-gaming.org/index.php?topic=25516.0]
		int[] pixels = new int[img.getWidth() * img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(img.getWidth() * img.getHeight() * 4);
		
		for(int y = 0; y < img.getHeight(); y++){
			for(int x = 0; x < img.getWidth(); x++){
				int pixel = pixels[y * img.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				buffer.put((byte) ((pixel >> 24) & 0xFF));
			}
		}
		buffer.flip();
		
		int id = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, img.getWidth(), img.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		
		return id;
	}
}