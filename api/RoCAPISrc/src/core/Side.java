
package core;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public enum Side
{
	/**
	 * The client side, mainly used to handle rendering and GUIs.
	 */
	CLIENT,
	
	/**
	 * The (integrated) server side, handles most logic.
	 */
	SERVER,
	
	/**
	 * The part of the game that actually launches it. Handles logging in, fetching the changelog and checking for updates.
	 */
	LAUNCHER,
	
	/**
	 * The (Dedicated) server side, does everything the integrated version does, but it has it's own console, making it much better suited for dedicated machines.
	 */
	DEDISERVER;
	
}
