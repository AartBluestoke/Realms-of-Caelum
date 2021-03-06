
package com.briman0094.gameengine;

import java.awt.Canvas;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public abstract class Game
{
	private Canvas dParent;
	private GameThread gameThread;
	private boolean	isRunning = false;
	private static Game	instance;
	private String resourceDirectory;
	
	public Game(Canvas displayParent, String resourceDirectory)
	{
		dParent = displayParent;
		instance = this;
		this.resourceDirectory = resourceDirectory;
		
	}
	
	protected void run() throws Exception
	{
		this.initializeOpenGL();
		this.isRunning = true;
		this.initializeGame();
		log("Game sucessfully initialized");
		log("Entering game loop");
		
		try
		{
			while (this.isRunning)
			{
				Display.sync(60);
				Display.update();
				this.tick();
				this.render();
				
			}
			
		}
		catch (Exception e)
		{
			//TODO Report crash.
		}
		finally
		{
			this.shutdownOpenGL();
			
		}
		
	}
	
	protected abstract void tick() throws Exception;
	
	protected abstract void render() throws Exception;
	
	protected abstract void initializeGame() throws Exception;
	
	public boolean startGame()
	{
		boolean crashed = true;
		
		try
		{
			log("Creating game thread");
			this.gameThread = new GameThread(this, "Game Thread");
			this.gameThread.run();
			while (this.gameThread.isAlive())
			{
				//TODO Add stuff.
			}
			
			if (this.gameThread.didEncounterError())
			{
				throw this.gameThread.getException();
			}
			
			this.dParent.getParent().setVisible(false);
			log("Shutting down");
			crashed = false;
			
		}
		catch (Exception e)
		{
			String message = "";
			if (e.getMessage() != null) message = ": " + e.getMessage();
			
			String stackTrace = "Game Crashed! Error log:\n" + e.toString() + message;
			
			for (StackTraceElement ste : e.getStackTrace())
			{
				stackTrace += "\nat " + ste.toString();
				
			}
			
			log(stackTrace);
			
			try
			{
				shutdownOpenGL();
				
			}
			catch (Exception ee)
			{
				
			}
			
		}
		
		return crashed;
	}
	
	private void initializeOpenGL() throws Exception
	{
		log("Initializing OpenGL");
		Display.setParent(this.dParent);
		log("Creating Display");
		Display.create();
		if (Display.isCreated())
		{
			log("Display successfully created");
			log("Creating input devices");
			Keyboard.create();
			Mouse.create();
			
			if (!(Keyboard.isCreated() && Mouse.isCreated()))
			{
				throw new Exception("An error occurred while creating the input devices!");
				
			}
			
			log("Enabling OpenGL features: DEPTH_TEST, DEPTH, ALPHA_TEST, ALPHA, BLEND");
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_DEPTH);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
			Display.update();
			
		}
		else
		{
			throw new Exception("An error occurred while creating the Display!");
			
		}
		
	}
	
	private void shutdownOpenGL() throws Exception
	{
		log("Shutting down OpenGL");
		
		if (Keyboard.isCreated())
		{
			log("Destroying keyboard");
			Keyboard.destroy();
			
		}
		
		if (Mouse.isCreated())
		{
			log("Destroying mouse");
			Mouse.destroy();
			
		}
		
		if (Display.isCreated())
		{
			log("Destroying display");
			Display.destroy();
			
		}
		
	}
	
	public Canvas getDisplayParent()
	{
		return this.dParent;
	}
	
	public boolean isRunning()
	{
		return this.isRunning;
	}
	
	protected void setRunning(boolean running)
	{
		this.isRunning = running;
	}
	
	public void stopGame()
	{
		setRunning(false);
		this.gameThread.interrupt();
	}
	
	public GameThread getGameThread()
	{
		return this.gameThread;
	}
	
	public String getResourceDirectory()
	{
		return this.resourceDirectory;
	}
	
	public static void log(String message)
	{
		String lines[] = message.split("\n");
		
		for (String l : lines)
		{
			String temp = "";
			String date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ssa").format(Calendar.getInstance().getTime());
			temp = date + ": " + l;
			System.out.println(temp);
			
		}
		
	}
	
	public static Game getInstance()
	{
		return instance;
	}
	
}
