package com.briman0094.gameengine;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public abstract class Game
{
	private Canvas		dParent;
	private GameThread	gameThread;
	private boolean		running	= false;
	private static Game	instance;
	private String		resourceDirectory;
	
	public Game(Canvas displayParent, String resourceDirectory)
	{
		dParent = displayParent;
		instance = this;
		this.resourceDirectory = resourceDirectory;
	}
	
	protected void run() throws Exception
	{
		initializeOpenGL();
		running = true;
		initializeGame();
		log("Game sucessfully initialized");
		log("Entering game loop");
		while (running)
		{
			Display.sync(60);
			Display.update();
			tick();
			render();
		}
		shutdownOpenGL();
	}
	
	protected abstract void tick() throws Exception;
	
	protected abstract void render() throws Exception;
	
	protected abstract void initializeGame() throws Exception;
	
	public void startGame()
	{
		try
		{
			log("Creating game thread");
			gameThread = new GameThread(this, "Game Thread");
			gameThread.run();
			while (gameThread.isAlive())
				;
			if (gameThread.didEncounterError())
			{
				throw gameThread.getException();
			}
			dParent.getParent().setVisible(false);
			log("Shutting down");
			System.exit(0);
		}
		catch (Exception e)
		{
			String message = "";
			if (e.getMessage() != null)
				message = ": " + e.getMessage();
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
			catch (Exception ee) { }
			System.exit(0);
		}
	}
	
	private void initializeOpenGL() throws Exception
	{
		log("Initializing OpenGL");
		Display.setParent(dParent);
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
			glEnable(GL_DEPTH_TEST);
			glEnable(GL_DEPTH);
			glEnable(GL_ALPHA_TEST);
			glEnable(GL_ALPHA);
			glEnable(GL_BLEND);
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
		return dParent;
	}
	
	public boolean isRunning()
	{
		return running;
	}
	
	protected void setRunning(boolean running)
	{
		this.running = running;
	}
	
	public void stopGame()
	{
		setRunning(false);
		gameThread.interrupt();
	}
	
	public GameThread getGameThread()
	{
		return gameThread;
	}
	
	public String getResourceDirectory()
	{
		return resourceDirectory;
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
