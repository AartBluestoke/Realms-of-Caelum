package com.briman0094.gameengine.render;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glNormal3d;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUseProgram;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.util.vector.Vector3f;


public class RenderHelper
{
	private static boolean ccw = false; // draw direction

	private static Texture topTexture; // textures
	private static Texture btmTexture;
	private static Texture lftTexture;
	private static Texture rgtTexture;
	private static Texture fntTexture;
	private static Texture bckTexture;

	private static int uRep = 1; // texture repetition
	private static int vRep = 1;
	private static int xRep = 1;
	private static int yRep = 1;
	private static int zRep = 1;

	private static int lRes = 1; // light resolution
	
	private static int shader = 0;

	public static void setCounterClockwise(boolean useCounterClockwise)
	{
		ccw = useCounterClockwise;
	}

	public static void setTopTexture(Texture texture)
	{
		topTexture = texture;
	}

	public static void setBottomTexture(Texture texture)
	{
		btmTexture = texture;
	}

	public static void setLeftTexture(Texture texture)
	{
		lftTexture = texture;
	}

	public static void setRightTexture(Texture texture)
	{
		rgtTexture = texture;
	}

	public static void setFrontTexture(Texture texture)
	{
		fntTexture = texture;
	}

	public static void setBackTexture(Texture texture)
	{
		bckTexture = texture;
	}

	public static void setAllTextures(Texture texture)
	{
		setTopTexture(texture);
		setBottomTexture(texture);
		setLeftTexture(texture);
		setRightTexture(texture);
		setFrontTexture(texture);
		setBackTexture(texture);
	}
	
	public static void setShader(int shader)
	{
		RenderHelper.shader = shader;
	}

	public static void setRepeat(int xRepeat, int yRepeat, int zRepeat)
	{
		xRep = xRepeat;
		yRep = yRepeat;
		zRep = zRepeat;
	}

	public static void drawRectPrism(float x1, float x2, float y1, float y2, float z1, float z2)
	{

		Vector3f frontNormal = new Vector3f(0.0f, -1.0f, 0.0f);
		Vector3f backNormal = new Vector3f(0.0f, 1.0f, 0.0f);
		Vector3f rightNormal = new Vector3f(1.0f, 0.0f, 0.0f);
		Vector3f leftNormal = new Vector3f(-1.0f, 0.0f, 0.0f);
		Vector3f topNormal = new Vector3f(0.0f, 0.0f, 1.0f);
		Vector3f bottomNormal = new Vector3f(0.0f, 0.0f, -1.0f);

		Vector3f topFrontLeft = new Vector3f(x1, y1, z2);
		Vector3f topFrontRight = new Vector3f(x2, y1, z2);
		Vector3f topBackLeft = new Vector3f(x1, y2, z2);
		Vector3f topBackRight = new Vector3f(x2, y2, z2);
		Vector3f bottomFrontLeft = new Vector3f(x1, y1, z1);
		Vector3f bottomFrontRight = new Vector3f(x2, y1, z1);
		Vector3f bottomBackLeft = new Vector3f(x1, y2, z1);
		Vector3f bottomBackRight = new Vector3f(x2, y2, z1);

		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		if (topTexture == null)
			glBindTexture(GL_TEXTURE_2D, 0);
		else
			topTexture.bindTexture();
		uRep = xRep;
		vRep = yRep;
		drawPlane(topFrontLeft, topFrontRight, topBackLeft, topBackRight, topNormal); // top face
		
		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		if (btmTexture == null)
			glBindTexture(GL_TEXTURE_2D, 0);
		else
			btmTexture.bindTexture();
		drawPlane(bottomBackLeft, bottomBackRight, bottomFrontLeft, bottomFrontRight, bottomNormal); // bottom face
		
		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		if (lftTexture == null)
			glBindTexture(GL_TEXTURE_2D, 0);
		else
			lftTexture.bindTexture();
		uRep = yRep;
		vRep = zRep;
		drawPlane(topFrontLeft, topBackLeft, bottomFrontLeft, bottomBackLeft, leftNormal); // left face
		
		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		if (rgtTexture == null)
			glBindTexture(GL_TEXTURE_2D, 0);
		else
			rgtTexture.bindTexture();
		drawPlane(topBackRight, topFrontRight, bottomBackRight, bottomFrontRight, rightNormal); // right face
		
		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		if (bckTexture == null)
			glBindTexture(GL_TEXTURE_2D, 0);
		else
			bckTexture.bindTexture();
		uRep = xRep;
		vRep = zRep;
		drawPlane(topBackLeft, topBackRight, bottomBackLeft, bottomBackRight, backNormal); // back face
		
		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		if (fntTexture == null)
			glBindTexture(GL_TEXTURE_2D, 0);
		else
			fntTexture.bindTexture();
		drawPlane(topFrontRight, topFrontLeft, bottomFrontRight, bottomFrontLeft, frontNormal); // front face
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public static void drawXYPlane(Vector3f pointTopLeft, Vector3f pointTopRight, Vector3f pointBottomLeft, Vector3f pointBottomRight, Vector3f faceNormal)
	{
		float ix = pointTopLeft.x;
		float iy = pointTopLeft.y;
		float fx = pointBottomRight.x;
		float fy = pointBottomRight.y;
		float z = (pointTopLeft.z + pointBottomRight.z) / 2f; // for now just take the average
		float xInc = (fx - ix) / lRes;
		float yInc = (fy - iy) / lRes;
		for (float x = ix; x < fx; x += xInc)
		{
			for (float y = iy; y < fy; y += yInc)
			{
				drawPlane(new Vector3f(x, y, z), new Vector3f(x + xInc, y, z), new Vector3f(x, y + yInc, z), new Vector3f(x + xInc, y + yInc, z), faceNormal);
			}
		}
	}

	public static void drawYZPlane(Vector3f pointTopLeft, Vector3f pointTopRight, Vector3f pointBottomLeft, Vector3f pointBottomRight, Vector3f faceNormal)
	{
		float iy = pointTopLeft.y;
		float iz = pointTopLeft.z;
		float fy = pointBottomRight.y;
		float fz = pointBottomRight.z;
		float x = (pointTopLeft.x + pointBottomRight.x) / 2f; // for now just take the average
		float yInc = (fy - iy) / lRes;
		float zInc = (fz - iz) / lRes;
		for (float y = iy; y < fy; y += yInc)
		{
			for (float z = iz; z < fz; z += zInc)
			{
				drawPlane(new Vector3f(x, y, z), new Vector3f(x, y + yInc, z), new Vector3f(x, y, z + zInc), new Vector3f(x, y + yInc, z + zInc), faceNormal);
			}
		}
	}

	public static void drawXZPlane(Vector3f pointTopLeft, Vector3f pointTopRight, Vector3f pointBottomLeft, Vector3f pointBottomRight, Vector3f faceNormal)
	{
		float ix = pointTopLeft.x;
		float iz = pointTopLeft.z;
		float fx = pointBottomRight.x;
		float fz = pointBottomRight.z;
		float y = (pointTopLeft.y + pointBottomRight.y) / 2f; // for now just take the average
		float xInc = (fx - ix) / lRes;
		float zInc = (fz - iz) / lRes;
		for (float x = ix; x < fx; x += xInc)
		{
			for (float z = iz; z < fz; z += zInc)
			{
				drawPlane(new Vector3f(x, y, z), new Vector3f(x + xInc, y, z), new Vector3f(x, y, z + zInc), new Vector3f(x + xInc, y, z + zInc), faceNormal);
			}
		}
	}

	public static void drawPlane(Vector3f pointTopLeft, Vector3f pointTopRight, Vector3f pointBottomLeft, Vector3f pointBottomRight, Vector3f faceNormal)
	{
		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		// glPushMatrix();
		glBegin(GL_QUADS);
		{
			if (ccw)
			{
				glTexCoord2d(0.0f, 0.0f);
				glNormal3d(faceNormal.x, faceNormal.y, faceNormal.z);
				glVertex3d(pointTopLeft.x, pointTopLeft.y, pointTopLeft.z);

				glTexCoord2d(0.0f, vRep);
				glNormal3d(faceNormal.x, faceNormal.y, faceNormal.z);
				glVertex3d(pointBottomLeft.x, pointBottomLeft.y, pointBottomLeft.z);

				glTexCoord2d(uRep, vRep);
				glNormal3d(faceNormal.x, faceNormal.y, faceNormal.z);
				glVertex3d(pointBottomRight.x, pointBottomRight.y, pointBottomRight.z);

				glTexCoord2d(uRep, 0.0f);
				glNormal3d(faceNormal.x, faceNormal.y, faceNormal.z);
				glVertex3d(pointTopRight.x, pointTopRight.y, pointTopRight.z);
			} else
			{
				glTexCoord2d(0.0f, 0.0f);
				glNormal3d(faceNormal.x, faceNormal.y, faceNormal.z);
				glVertex3d(pointTopLeft.x, pointTopLeft.y, pointTopLeft.z);

				glTexCoord2d(uRep, 0.0f);
				glNormal3d(faceNormal.x, faceNormal.y, faceNormal.z);
				glVertex3d(pointTopRight.x, pointTopRight.y, pointTopRight.z);

				glTexCoord2d(uRep, vRep);
				glNormal3d(faceNormal.x, faceNormal.y, faceNormal.z);
				glVertex3d(pointBottomRight.x, pointBottomRight.y, pointBottomRight.z);

				glTexCoord2d(0.0f, vRep);
				glNormal3d(faceNormal.x, faceNormal.y, faceNormal.z);
				glVertex3d(pointBottomLeft.x, pointBottomLeft.y, pointBottomLeft.z);
			}
			/*
			 * if (ccw) { glTexCoord2f(0.0f, 0.0f); glNormal3f(faceNormal.x, faceNormal.y, faceNormal.z); glVertex3f(pointTopLeft.x, pointTopLeft.y, pointTopLeft.z);
			 * 
			 * glTexCoord2f(0.0f, vRep); glNormal3f(faceNormal.x, faceNormal.y, faceNormal.z); glVertex3f(pointBottomLeft.x, pointBottomLeft.y, pointBottomLeft.z);
			 * 
			 * glTexCoord2f(uRep, vRep); glNormal3f(faceNormal.x, faceNormal.y, faceNormal.z); glVertex3f(pointBottomRight.x, pointBottomRight.y, pointBottomRight.z);
			 * 
			 * glTexCoord2f(uRep, 0.0f); glNormal3f(faceNormal.x, faceNormal.y, faceNormal.z); glVertex3f(pointTopRight.x, pointTopRight.y, pointTopRight.z); } else { glTexCoord2f(0.0f, 0.0f); glNormal3f(faceNormal.x, faceNormal.y, faceNormal.z); glVertex3f(pointTopLeft.x, pointTopLeft.y, pointTopLeft.z);
			 * 
			 * glTexCoord2f(uRep, 0.0f); glNormal3f(faceNormal.x, faceNormal.y, faceNormal.z); glVertex3f(pointTopRight.x, pointTopRight.y, pointTopRight.z);
			 * 
			 * glTexCoord2f(uRep, vRep); glNormal3f(faceNormal.x, faceNormal.y, faceNormal.z); glVertex3f(pointBottomRight.x, pointBottomRight.y, pointBottomRight.z);
			 * 
			 * glTexCoord2f(0.0f, vRep); glNormal3f(faceNormal.x, faceNormal.y, faceNormal.z); glVertex3f(pointBottomLeft.x, pointBottomLeft.y, pointBottomLeft.z); }
			 */
		}
		glEnd();
		// glPopMatrix();
	}

	public static void drawPlane(Vector3f pointTopLeft, Vector3f pointTopRight, Vector3f pointBottomLeft, Vector3f pointBottomRight)
	{
		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		// glPushMatrix();
		glBegin(GL_QUADS);
		{
			if (ccw)
			{
				glTexCoord2f(0.0f, 0.0f);
				glVertex3f(pointTopLeft.x, pointTopLeft.y, pointTopLeft.z);
				glTexCoord2f(0.0f, vRep);
				glVertex3f(pointBottomLeft.x, pointBottomLeft.y, pointBottomLeft.z);
				glTexCoord2f(uRep, vRep);
				glVertex3f(pointBottomRight.x, pointBottomRight.y, pointBottomRight.z);
				glTexCoord2f(uRep, 0.0f);
				glVertex3f(pointTopRight.x, pointTopRight.y, pointTopRight.z);
			} else
			{
				glTexCoord2f(0.0f, 0.0f);
				glVertex3f(pointTopLeft.x, pointTopLeft.y, pointTopLeft.z);
				glTexCoord2f(uRep, 0.0f);
				glVertex3f(pointTopRight.x, pointTopRight.y, pointTopRight.z);
				glTexCoord2f(uRep, vRep);
				glVertex3f(pointBottomRight.x, pointBottomRight.y, pointBottomRight.z);
				glTexCoord2f(0.0f, vRep);
				glVertex3f(pointBottomLeft.x, pointBottomLeft.y, pointBottomLeft.z);
			}
		}
		glEnd();
		// glPopMatrix();
	}

	/*
	 * public static Vector3f calculateFaceNormal(Vector3f point1, Vector3f point2, Vector3f point3) { Vector3f vector1 = subtractVector(point1, point2); Vector3f vector2 = subtractVector(point1, point3); return calculateNormal(vector1, vector2); }
	 * 
	 * public static Vector3f calculateNormal(Vector3f vector1, Vector3f vector2) { Vector3f result = new Vector3f(); result.x = (vector1.y * vector2.z) - (vector1.z * vector2.y); result.y = (vector1.z * vector2.x) - (vector1.x * vector2.z); result.z = (vector1.x * vector2.y) - (vector1.y * vector2.x); return result; }
	 */

	public static Vector3f subtractVector(Vector3f vector1, Vector3f vector2)
	{
		Vector3f result = new Vector3f();
		result.x = vector2.x - vector1.x;
		result.y = vector2.y - vector1.y;
		result.z = vector2.z - vector1.z;
		return result;
	}

	public static Vector3f normalizeVector(Vector3f vector)
	{
		float vecMag = (float) sqrt((vector.x * vector.x) + (vector.y * vector.y) + (vector.z * vector.z));
		vector.x /= vecMag;
		vector.y /= vecMag;
		vector.z /= vecMag;
		return vector;
	}

	public static void drawString(int x, int y, String text, Texture font, int tileWidth, int tileHeight, int fontStartChar, float zIndex)
	{
		int totalXTiles = (int) ((float) font.getWidth() / (float) tileWidth);
		int totalYTiles = (int) ((float) font.getHeight() / (float) tileHeight);

		for (int chr = 0; chr < text.length(); chr++)
		{
			char character = text.toCharArray()[chr];
			int characterValue = (int) character - fontStartChar;
			int dX = x + (tileWidth * chr);
			int dY = y;
			double temp = (double) characterValue / (double) totalYTiles;
			int yTile = (int) temp;
			int xTile = characterValue % totalYTiles;
			double xCoord = (double) xTile / (double) totalXTiles;
			double yCoord = (double) yTile / (double) totalYTiles;
			double texWidth = 1.0f / (double) totalXTiles;
			double texHeight = 1.0f / (double) totalYTiles;

			glPushMatrix();
			glLoadIdentity();
			glTranslatef((float) dX, (float) dY, 0.0f);

			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glEnable(GL_TEXTURE_2D);
			font.bindTexture();

			glBegin(GL_QUADS);
			{
				glTexCoord2d(xCoord, yCoord);
				glVertex3f(0.0f, 0.0f, zIndex);

				glTexCoord2d(xCoord + texWidth, yCoord);
				glVertex3f((float) tileWidth, 0.0f, zIndex);

				glTexCoord2d(xCoord + texWidth, yCoord + texHeight);
				glVertex3f((float) tileWidth, (float) tileHeight, zIndex);

				glTexCoord2d(xCoord, yCoord + texHeight);
				glVertex3f(0.0f, (float) tileHeight, zIndex);
			}
			glEnd();

			glPopMatrix();
		}
	}
	
	public static void drawRectangle(int x, int y, int z, int width, int height, boolean outlineOnly)
	{
		if (outlineOnly)
		{
			glPushMatrix();
			glLoadIdentity();
			glBegin(GL_LINE_STRIP);
			{
				glVertex3f(x, y, z);
				glVertex3f(x + width, y, z);
				glVertex3f(x + width, y + height, z);
				glVertex3f(x, y + height, z);
				glVertex3f(x, y, z);
			}
			glEnd();
			glPopMatrix();
		}
		else
		{
			glPushMatrix();
			glLoadIdentity();
			glBegin(GL_QUADS);
			{
				glVertex3f(x, y, z);
				glVertex3f(x + width, y, z);
				glVertex3f(x + width, y + height, z);
				glVertex3f(x, y + height, z);
			}
			glEnd();
			glPopMatrix();
		}
	}

	public static void drawSphere(int dtheta, int dphi, float radius)
	{
		if (shader != 0)
		{
			glUseProgram(shader);
			int loc = glGetUniformLocation(shader, "s_texture");
			glUniform1i(loc, 0);
			ARBShaderObjects.glUseProgramObjectARB(shader);
		}
		int n;
		int theta, phi;
		Vector3f[] p = new Vector3f[] { new Vector3f(), new Vector3f(), new Vector3f(), new Vector3f() };
		float DTOR = (float) Math.PI / 180f;

		for (theta = -90; theta <= 90 - dtheta; theta += dtheta)
		{
			for (phi = 0; phi <= 360 - dphi; phi += dphi)
			{
				n = 0;
				p[n].x = radius * (float) (cos(theta * DTOR) * cos(phi * DTOR));
				p[n].y = radius * (float) (cos(theta * DTOR) * sin(phi * DTOR));
				p[n].z = radius * (float) sin(theta * DTOR);
				n++;
				p[n].x = radius * (float) (cos((theta + dtheta) * DTOR) * cos(phi * DTOR));
				p[n].y = radius * (float) (cos((theta + dtheta) * DTOR) * sin(phi * DTOR));
				p[n].z = radius * (float) sin((theta + dtheta) * DTOR);
				n++;
				p[n].x = radius * (float) (cos((theta + dtheta) * DTOR) * cos((phi + dphi) * DTOR));
				p[n].y = radius * (float) (cos((theta + dtheta) * DTOR) * sin((phi + dphi) * DTOR));
				p[n].z = radius * (float) sin((theta + dtheta) * DTOR);
				n++;
				if (theta > -90 && theta < 90)
				{
					p[n].x = radius * (float) (cos(theta * DTOR) * cos((phi + dphi) * DTOR));
					p[n].y = radius * (float) (cos(theta * DTOR) * sin((phi + dphi) * DTOR));
					p[n].z = radius * (float) sin(theta * DTOR);
					n++;
				}

				Vector3f normal = calculateNormal(p[0].x, p[0].y, p[0].z, p[2].x, p[2].y, p[2].z, p[3].x, p[3].y, p[3].z);
				if (ccw)
				{
					glBegin(GL_QUADS);
					{
						glNormal3f(normal.x, normal.y, normal.z);
						glVertex3f(p[0].x, p[0].y, p[0].z);
						glVertex3f(p[1].x, p[1].y, p[1].z);
						glVertex3f(p[2].x, p[2].y, p[2].z);
						glVertex3f(p[3].x, p[3].y, p[3].z);
					}
					glEnd();
				}
				else
				{
					glBegin(GL_QUADS);
					{
						glNormal3f(normal.x, normal.y, normal.z);
						glVertex3f(p[3].x, p[3].y, p[3].z);
						glVertex3f(p[2].x, p[2].y, p[2].z);
						glVertex3f(p[1].x, p[1].y, p[1].z);
						glVertex3f(p[0].x, p[0].y, p[0].z);
					}
					glEnd();
				}
			}
		}
	}

	public static Vector3f calculateNormal(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3)
	{
		Vector3f a = new Vector3f();
		Vector3f b = new Vector3f();
		Vector3f normal = new Vector3f();

		a.x = x1 - x2;
		a.y = y1 - y2;
		a.z = z1 - z2;

		b.x = x2 - x3;
		b.y = y2 - y3;
		b.z = z2 - z3;

		normal.x = (a.y * b.z) - (a.z * b.y);
		normal.y = (a.z * b.x) - (a.x * b.z);
		normal.z = (a.x * b.y) - (a.y * b.x);

		float combined = (normal.x * normal.x) + (normal.y * normal.y) + (normal.z * normal.z);
		float length = (float) Math.sqrt(combined);

		normal.x /= length;
		normal.y /= length;
		normal.z /= length;

		return normal;
	}
}
