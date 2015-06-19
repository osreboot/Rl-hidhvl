package com.osreboot.ridhvl.display.collection;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.HvlRenderFrameProfile;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlDisplayModeScalable extends HvlDisplayMode{
	
	private HvlRenderFrame renderFrame;
	
	@Override
	public void configureDisplay(){
		Display.setResizable(true);
	}
	
	@Override
	public void initialize(){
		super.initialize();
		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, getCoordinateWidth(), getCoordinateHeight());
	}
	
	@Override
	public void preUpdate(float delta){
		resizePerspective(getCoordinateWidth(), getCoordinateHeight(), Display.getWidth(), Display.getHeight());//TODO resize check
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		HvlRenderFrame.setCurrentRenderFrame(renderFrame.getID());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void postUpdate(float delta){
		HvlRenderFrame.setCurrentRenderFrame(0);
		
		HvlPainter2D.hvlDrawQuad(0, Display.getHeight(), Display.getWidth(), -Display.getHeight(), renderFrame.getTextureID());
	}

}