package com.briman0094.gameengine.render;

import static org.lwjgl.opengl.GL11.glGenTextures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Hashtable;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.opengl.GL11.*;

public class Texture
{
	private BufferedImage img;
	private int id;
	private ByteBuffer pixels;
	private int hRep, vRep;
	
	private ColorModel glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 8 }, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
	private ColorModel glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 0 }, false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
	
	public Texture(BufferedImage sourceImage)
	{
		img = sourceImage;
		pixels = convertImageData(img);
		id = createTextureID();
		bindTexture();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR/*_MIPMAP_LINEAR*/);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, img.getWidth(), img.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
		hRep = 1;
		vRep = 1;
	}
	
	public int getHRepeat()
	{
		return hRep;
	}
	
	public void setHRepeat(int hRepeat)
	{
		hRep = hRepeat;
	}
	
	public int getVRepeat()
	{
		return vRep;
	}
	
	public void setVRepeat(int vRepeat)
	{
		vRep = vRepeat;
	}
	
	/*private ByteBuffer convertImageData(BufferedImage image)
	{
		ByteBuffer buf;
		byte[] tData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		/*int[] data = new int[tData.length];
		for (int i = 0; i < tData.length; i++)
		{
			data[i] = (int) tData[i];
		}/
		buf = ByteBuffer.allocateDirect(image.getWidth() * image.getHeight() * 12);
		buf.put(tData);
		buf.rewind();
		return buf;
	}*/
	
	private ByteBuffer convertImageData(BufferedImage bufferedImage)
	{
		ByteBuffer imageBuffer;
		WritableRaster raster;
		BufferedImage texImage;
		
		int texWidth = bufferedImage.getWidth();
		int texHeight = bufferedImage.getHeight();

		// create a raster that can be used by OpenGL as a source
		// for a texture
		if (bufferedImage.getColorModel().hasAlpha())
		{
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 4, null);
			texImage = new BufferedImage(glAlphaColorModel, raster, false, new Hashtable<Object, Object>());
		} else
		{
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 3, null);
			texImage = new BufferedImage(glColorModel, raster, false, new Hashtable<Object, Object>());
		}

		// copy the source image into the produced image
		Graphics g = texImage.getGraphics();
		g.setColor(new Color(0f, 0f, 0f, 0f));
		g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
		g.drawImage(bufferedImage, 0, 0, null);

		// build a byte buffer from the temporary image
		// that be used by OpenGL to produce a texture.
		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();

		imageBuffer = ByteBuffer.allocateDirect(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		imageBuffer.flip();

		return imageBuffer;
	}
	
	private static int createTextureID()
	{
		IntBuffer textureIDBuffer = createIntBuffer(1);
		glGenTextures(textureIDBuffer);
		return textureIDBuffer.get(0);		
	}
	
	public void bindTexture()
	{
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public BufferedImage getImage()
	{
		return img;
	}
	
	public int getID()
	{
		return id;
	}
	
	public int getWidth()
	{
		return img.getWidth();
	}
	
	public int getHeight()
	{
		return img.getHeight();
	}
}
