package com.suckyblowfish.isoblocks;

import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Block {
	public static int isoVertMove = 4;
	public static int isoHorizMove = 4;
	
	private static Texture tile;
	private int _x = 0;
	private int _y = 0;
	private int _z = 0;
	
	Block(int x, int y, int z){
		_x = x;
		_y = y;
		_z = z;
		
		try {
			tile=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/GrassBlock.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(){
		int x=Display.getDisplayMode().getWidth()/2-8*_z+8*_x;
		int y=Display.getDisplayMode().getHeight()/2+4*_z+4*_x-8*_y;
		Color.white.bind();
		tile.bind();
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+tile.getTextureWidth(),y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+tile.getTextureWidth(),y+tile.getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x,y+tile.getTextureHeight());
		GL11.glEnd();
	}
}
