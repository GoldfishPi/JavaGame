package com.gfp.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public String Path;
	public int Width;
	public int Height;
	
	public int[] pixels;
	
	public SpriteSheet(String Path){
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(Path));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		if(image == null){
			return;
			}
		
		this.Path = Path;
		this.Width = image.getWidth();
		this.Height = image.getHeight();
		
		pixels = image.getRGB(0, 0, Width, Height, null, 0, Width);
		
		for(int i = 0; i <pixels.length; i++){
			pixels[i] = (pixels[i] & 0xff)/64;
		}
		
		for (int i = 0; i < 8; i ++){
			System.out.println(pixels[i]);
		}
		
	}

}
