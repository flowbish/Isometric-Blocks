package com.suckyblowfish.isoblocks.blocks;

import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.suckyblowfish.isoblocks.Type;

public class Block {
	public static int isoVertMove = 3;
	public static int isoHorizMove = 6;
	public static Texture tiles;
	
	private int _x = 0;
	private int _y = 0;
	private int _z = 0;
	private Type type;
	
	Block(int x, int y, int z, Type type){
		_x = x;
		_y = y;
		_z = z;
		
		this.type = type;
		
		try {
			tiles=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/blocks.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public float getTextureX(){
		return 0;
	}
	
	public float getTextureY(){
		return 0;
	}
	
	public float getTextureWidth(){
		return 16;
	}
	
	public float getTextureHeight(){
		return 16;
	}
	
	public void render(){
		int x=Display.getDisplayMode().getWidth()/2-12*_z+12*_x;
		int y=Display.getDisplayMode().getHeight()/2+6*_z+6*_x-12*_y;
		Color.white.bind();
		tiles.bind();
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(getTextureX(),getTextureY());
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(getTextureX()+(1f/16f),getTextureY());
			GL11.glVertex2f(x+getTextureWidth(),y);
			GL11.glTexCoord2f(getTextureX()+(1f/16f),getTextureY()+(1f/16f));
			GL11.glVertex2f(x+getTextureWidth(),y+getTextureHeight());
			GL11.glTexCoord2f(getTextureX(),getTextureY()+(1f/16f));
			GL11.glVertex2f(x,y+getTextureHeight());
		GL11.glEnd();
		System.out.println(getTextureHeight()+"*"+getTextureWidth()+" - "+getTextureX()+"*"+getTextureY());
	}
}
