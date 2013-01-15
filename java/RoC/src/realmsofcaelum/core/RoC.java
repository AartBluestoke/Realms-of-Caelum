
package realmsofcaelum.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;
import java.awt.Canvas;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import org.lwjgl.input.Keyboard;
import realmsofcaelum.scenes.IScene;
import realmsofcaelum.scenes.SceneMainMenu;
import com.briman0094.gameengine.Game;
import com.briman0094.gameengine.render.TextRenderer;
import com.briman0094.gameengine.render.TextureLibrary;

public class RoC extends Game implements WindowListener
{
	private TextureLibrary textureLibrary;
	public TextRenderer textRenderer;
	private boolean initialized;
	private static boolean[] keys;
	private static boolean[] lastKeys;
	private IScene currentScene;
	
	public RoC(Canvas displayParent, String resourceDirectory)
	{
		super(displayParent, resourceDirectory);
		this.initialized = false;
		keys = new boolean[Keyboard.KEYBOARD_SIZE];
		lastKeys = new boolean[Keyboard.KEYBOARD_SIZE];
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
		return (key < keys.length) ? keys[key] : false;
	}
	
	public static boolean getKeyOnce(int key)
	{
		return (key < keys.length) ? keys[key] && !lastKeys[key] : false;
	}
	
	@Override
	protected void tick() throws Exception
	{
		this.updateKeys();
		
		if (getKey(Keyboard.KEY_ESCAPE))
		{
			this.stopGame();
		}
		
		if (this.currentScene != null)
			this.currentScene.tick();
	}
	
	@Override
	protected void render() throws Exception
	{
		glClearColor(0f, 0f, 0f, 1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		if (this.currentScene != null)
			this.currentScene.render();
	}
	
	@Override
	protected void initializeGame() throws Exception
	{
		this.textureLibrary = new TextureLibrary();
		this.textRenderer = new TextRenderer("mainFont", 256, 2);
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		glOrtho(0, 1280, 720, 0, 100f, -100f); // TODO: make these sizes not hard-coded!
		glViewport(0, 0, 1280, 720);
		
		this.currentScene = new SceneMainMenu(this);
		
		this.initialized = true;
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
		this.stopGame();
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
