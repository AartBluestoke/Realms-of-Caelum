
package core;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class ConsoleHelper
{
	public enum Defcon
	{
		BSoD, //How the...?
		CRASH, //You idiot...
		ERROR, //Something went wrong, but nothing's been killed yet.
		WARNING, //Things are gonna go wrong.
		OKAY, //Still fine, but someone might be trying Java-based oddities.
		FINE; //Everything is copasedic.
		
	}
	
	public static void toConsole(String message, Defcon severity)
	{
		//TODO Add Python wrapper.
		System.out.println("[" + severity.toString() + "] " + message);
		
	}
	
}
