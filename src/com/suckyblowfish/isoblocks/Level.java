package com.suckyblowfish.isoblocks;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.*;
import org.lwjgl.util.vector.Vector3f;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.sun.net.httpserver.Filter;

public class Level {
	
	Texture tile;
	
	private int length;
	private int width;
	private int depth;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	
	public Level(){
		length = 300;
		width = 150;
		depth = 25;
		try {
			tile = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/GrassBlock.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(String levelName){
		byte levelData[];
		String levelPath = "res/"+levelName;
		
		try { 
			FileInputStream fin=new FileInputStream(levelPath);
			byte readBuf[] = new byte[512*1024];
			
	    	ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    
	    	int readCnt = fin.read(readBuf);
	    	while (0 < readCnt) {
	    			bout.write(readBuf, 0, readCnt);
	    			readCnt = fin.read(readBuf);
	    	}
	    	
	    	levelData=bout.toByteArray();
	    	
	    	fin.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	levelData=new byte[0];
	    }
	    for (int y=0;y<8;y++){
	    	for (int z=0;z<8;z++){
	    		for (int x=0;x<8;x++){
	    			if (levelData[x+4*z+16*y]==49){
	    				this.putBlock(new Block(x,y,z));
	    			}
	    	    }
		    }
	    }
	}
	
	public void putBlock(Block b){
		blocks.add(b);
	}
	
	public void render(){
		GL11.glPushMatrix();
			for (Block b:blocks){
				b.render();
			}
		GL11.glPopMatrix();
	}
}
