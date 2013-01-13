
package com.briman0094.gameengine;

public class GameThread extends Thread
{
	private Game game;
	private boolean didEncounterError = false;
	private Exception exception;
	
	public GameThread(Game game, String name)
	{
		super(name);
		this.game = game;
	}
	
	public boolean didEncounterError()
	{
		return this.didEncounterError;
	}
	
	public Exception getException()
	{
		return this.exception;
	}
	
	@Override
	public void run()
	{
		try
		{
			Game.log("Game thread sucessfully started");
			this.game.run();
			
		}
		catch (Exception e)
		{
			this.didEncounterError = true;
			this.exception = e;
			this.interrupt();
			
		}
		
	}
	
}
