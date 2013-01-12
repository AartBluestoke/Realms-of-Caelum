package com.briman0094.gameengine.render;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector4f;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.briman0094.gameengine.Game;

public class TextRenderer
{
	private Texture		font;
	private Vector4f[]	charCoords;
	private int			charOffset;
	private int			padding;
	
	public TextRenderer(String fontName, int charCount, int padding) throws Exception
	{
		font = TextureLibrary.getInstance().getTexture("fonts/" + fontName + ".png");
		charCoords = new Vector4f[charCount];
		this.padding = padding;
		loadMetrics("fonts/" + fontName + ".xml");
	}
	
	public void renderTextAt(String text, int x, int y)
	{
		char[] chars = text.toCharArray();
		int curX = x;
		int key;
		for (char c : chars)
		{
			key = (int) c;
			if (charCoords != null)
			{
				if (key >= 0 && key < charCoords.length)
				{
					Vector4f charLoc = charCoords[key];
					if (charLoc != null)
					{
						renderCharacterAt(charLoc, curX, y);
						curX += charLoc.z; // add width of character
						curX += padding;
					}
					else
					{
						Game.log("Invalid character encountered while rendering!");
					}
				}
				else
				{
					Game.log("Invalid character encountered while rendering!");
				}
			}
			else
			{
				Game.log("A strange error occurred while rendering! Font renderer not initialized!");
			}
		}
	}
	
	private void renderCharacterAt(Vector4f charLoc, int x, int y)
	{
		
		int pxX = (int) charLoc.x;
		int pxY = (int) charLoc.y;
		int pxW = (int) charLoc.z;
		int pxH = (int) charLoc.w;
		float tcX = ((float) pxX) / ((float) font.getWidth());
		float tcY = ((float) pxY) / ((float) font.getHeight());
		float tcW = ((float) pxW) / ((float) font.getWidth());
		float tcH = ((float) pxH) / ((float) font.getHeight());
		
		glPushMatrix();
		glTranslatef(x, y, 0);
		{
			font.bindTexture();
			glEnable(GL_TEXTURE_2D);
			glBegin(GL_QUADS);
			{
				glTexCoord2f(tcX, tcY);
				glVertex2i(0, 0);
				
				glTexCoord2f(tcX + tcW, tcY);
				glVertex2i(pxW, 0);
				
				glTexCoord2f(tcX + tcW, tcY + tcH);
				glVertex2i(pxW, pxH);
				
				glTexCoord2f(tcX, tcY + tcH);
				glVertex2i(0, pxH);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	private void loadMetrics(String filename) throws Exception
	{
		if (Game.getInstance() != null)
		{
			InputStream xml = Game.getInstance().getClass().getClassLoader().getResourceAsStream(Game.getInstance().getResourceDirectory() + filename);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			NodeList characters = doc.getElementsByTagName("character");
			int key;
			int x, y;
			int w, h;
			Node node;
			Element character;
			for (int chr = 0; chr < characters.getLength(); chr++)
			{
				node = characters.item(chr);
				character = (Element) node;
				try
				{
					key = Integer.parseInt(character.getAttribute("key"));
					x = Integer.parseInt(getTagValue("x", character));
					y = Integer.parseInt(getTagValue("y", character));
					w = Integer.parseInt(getTagValue("width", character));
					h = Integer.parseInt(getTagValue("height", character));
					if (key >= 0 && key < charCoords.length && charCoords != null)
					{
						charCoords[key] = new Vector4f(x, y, w, h);
					}
				}
				catch (Exception ex)
				{
					Game.log("XML parse error: invalid key for character at " + chr);
				}
			}
		}
	}
	
	private String getTagValue(String tagName, Element element)
	{
		NodeList nodes = element.getElementsByTagName(tagName).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}
