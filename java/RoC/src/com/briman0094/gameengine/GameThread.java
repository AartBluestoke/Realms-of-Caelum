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
		return didEncounterError;
	}
	
	public Exception getException()
	{
		return exception;
	}

	@Override
	public void run()
	{
		try
		{
			Game.log("Game thread sucessfully started");
			game.run();
		} catch (Exception e)
		{
			didEncounterError = true;
			exception = e;
			this.interrupt();
		}
	}
}
