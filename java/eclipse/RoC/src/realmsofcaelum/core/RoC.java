
package realmsofcaelum.core;

import static org.lwjgl.opengl.GL11.*;
import java.awt.Canvas;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.lwjgl.input.Keyboard;
import com.briman0094.gameengine.Game;
import com.briman0094.gameengine.render.TextRenderer;
import com.briman0094.gameengine.render.TextureLibrary;

public class RoC extends Game implements WindowListener
{
	private TextureLibrary textureLibrary;
	private TextRenderer textRenderer;
	private boolean initialized;
	public static boolean[] keys;
	public static boolean[] lastKeys;
	
	public RoC(Canvas displayParent, String resourceDirectory)
	{
		super(displayParent, resourceDirectory);
		initialized = false;
		keys = new boolean[Keyboard.KEYBOARD_SIZE];
		lastKeys = new boolean[Keyboard.KEYBOARD_SIZE];
	}
	
	@Override
	protected void tick() throws Exception
	{
		updateKeys();
		
		if (getKey(Keyboard.KEY_ESCAPE))
		{
			stopGame();
		}
	}
	
	private void updateKeys()
	{
		for (int key = 0; key < keys.length; key++)
		{
			lastKeys[key] = keys[key];
			keys[key] = Keyboard.isKeyDown(key);
		}
	}
	
	public static boolean getKey(int key)
	{
		if (key < keys.length)
			return keys[key];
		return false;
	}
	
	public static boolean getKeyOnce(int key)
	{
		if (key < keys.length)
			return keys[key] && !lastKeys[key];
		return false;
	}
	
	@Override
	protected void render() throws Exception
	{
		glClearColor(0f, 0f, 0f, 1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glColor4f(1f, 1f, 1f, 1f); // TODO: placeholder code :D
		textRenderer.renderTextAt("Releams of Caelum",460, 280);
		textRenderer.renderTextAt("Load Level", 517, 330);
		textRenderer.renderTextAt("Multiplayer", 516, 380);
		textRenderer.renderTextAt("Options", 550, 430);
	}
	
	@Override
	protected void initializeGame() throws Exception
	{
		textureLibrary = new TextureLibrary();
		textRenderer = new TextRenderer("mainFont", 256, 2);
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		glOrtho(0, 1280, 720, 0, 100f, -100f); // TODO: make these sizes not hard-coded!
		glViewport(0, 0, 1280, 720);
		
		initialized = true;
	}
	
	@Override
	public void windowActivated(WindowEvent e)
	{
	}
	
	@Override
	public void windowClosed(WindowEvent e)
	{
	}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		stopGame();
	}
	
	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}
	
	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}
	
	@Override
	public void windowIconified(WindowEvent e)
	{
	}
	
	@Override
	public void windowOpened(WindowEvent e)
	{
	}
	
}