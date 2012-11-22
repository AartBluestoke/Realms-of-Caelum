
package core;

/**
 * 
 * The object used to represent a button.
 * 
 * @author Elusivehawk
 */
public class Button
{
	public final String name;
	public float xPos;
	public float zPos;
	public float scale;
	
	public Button(String name)
	{
		this.name = name;
		
	}
	
	public Button setPosAndScale(float x, float z, float scale)
	{
		this.xPos = x;
		this.zPos = z;
		this.scale = scale;
		return this;
	}
	
}
