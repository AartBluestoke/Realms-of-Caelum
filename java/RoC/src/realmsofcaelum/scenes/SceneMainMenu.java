package realmsofcaelum.scenes;

import static org.lwjgl.opengl.GL11.glColor4f;
import realmsofcaelum.core.RoC;

public class SceneMainMenu implements IScene
{
	private RoC game;
	
	public SceneMainMenu(RoC game)
	{
		this.game = game;
	}
	
	@Override
	public void tick()
	{
		
	}
	
	@Override
	public void render()
	{
		glColor4f(0.9f, 0.9f, 0.9f, 1f);
		this.game.textRenderer.renderTextAt("Releams of Caelum", 460, 280);
		
		glColor4f(1f, 1f, 1f, 1f);
		this.game.textRenderer.renderTextAt("Load Level", 517, 330);
		this.game.textRenderer.renderTextAt("Multiplayer", 516, 380);
		this.game.textRenderer.renderTextAt("Options", 550, 430);
	}
	
}
