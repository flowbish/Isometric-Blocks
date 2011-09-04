package com.suckyblowfish.isoblocks;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.*;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Isoblocks {
	public static final String GAME_TITLE = "My Game";
	private static final int FRAMERATE = 60;
	private static boolean finished;
	private int a=0;
	private Level level;
	
	public static void main(String[] args) {
		boolean fullscreen = false;
		Isoblocks game = new Isoblocks();
		try {
            game.init(fullscreen);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			Sys.alert(GAME_TITLE, "An error occured and the game will exit.");
		} finally {
			game.cleanup();
		}
		System.exit(0);
	}
	
	private void init(boolean fullscreen) throws Exception {
		Display.setTitle(GAME_TITLE);
		Display.setFullscreen(fullscreen);
		Display.setVSyncEnabled(false);
		Display.create();
		
		Mouse.setGrabbed(false);
		
		level = new Level();
		level.load("world1");
		
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glEnable(GL11.GL_LIGHTING);
//		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		run();
	}
 
	private void run() {
		while (!finished) {			
			Display.update();
			if (Display.isCloseRequested())finished = true;
			else if (Display.isActive()) {
				logic();
				render();
				Display.sync(FRAMERATE);
			}else{
				try{
					Thread.sleep(100);
				}catch (InterruptedException e) {}
				logic();
				if (Display.isVisible() || Display.isDirty()) {
					render();
				}
			}
		}
	}
 
	private void cleanup() {
		Display.destroy();
	}

	private void logic() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)||
			Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			finished = true;
		}
		a++;
	}
  
	private void render() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
	    	GL11.glLoadIdentity();
	    	GLU.gluOrtho2D(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0);
	    	
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    	GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT |
		    		     GL11.GL_DEPTH_BUFFER_BIT);
//		    GL11.glClearDepth(1);
		    GL11.glPushMatrix();
		    	level.render();
		    GL11.glPopMatrix();
	}
}
