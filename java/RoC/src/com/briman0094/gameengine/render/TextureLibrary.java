package com.briman0094.gameengine.render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.briman0094.gameengine.Game;

public class TextureLibrary
{
	public static HashMap<String, Texture>	textures	= new HashMap<String, Texture>();
	private static TextureLibrary			instance;
	
	public TextureLibrary()
	{
		instance = this;
	}
	
	public Texture getTexture(String path) throws IOException
	{
		if (textures.containsKey(path))
		{
			return textures.get(path);
		}
		else
		{
			Texture t = new Texture(getImageFromName(path));
			textures.put(path, t);
			return t;
		}
	}
	
	public BufferedImage getImageFromName(String resourceName) throws IOException
	{
		if (Game.getInstance() != null)
		{
			return ImageIO.read(Game.getInstance().getClass().getClassLoader().getResourceAsStream(Game.getInstance().getResourceDirectory() + resourceName));
		}
		return null;
	}
	
	public static TextureLibrary getInstance()
	{
		return instance;
	}
}
