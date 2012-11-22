
package core;

import implement.Plugin;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import core.ConsoleHelper.Defcon;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class RoCAPICore
{
	private static RoCAPICore INSTANCE = new RoCAPICore();
	private HashMap<Button, Plugin> buttonList = new HashMap<Button, Plugin>();
	
	public void registerButtons(Plugin plugin, Button... buttons)
	{
		for (Button button : buttons)
		{
			this.buttonList.put(button, plugin);
			
		}
		
	}
	
	protected void handleButtonPush(Button button)
	{
		Object plugin = this.buttonList.get(button);
		
		if (plugin != null)
		{
			for (Method method : plugin.getClass().getMethods())
			{
				for (Annotation annotation : method.getAnnotations())
				{
					if (annotation instanceof Plugin.OnButtonPush)
					{
						try
						{
							method.invoke(plugin.getClass(), button);
							return;
						}
						catch (IllegalArgumentException e)
						{
							ConsoleHelper.toConsole("The annotated method " + method.toGenericString() + " in " + plugin.toString() + " does not have the proper arguments. Full log:", Defcon.ERROR);
							e.printStackTrace();
						}
						catch (IllegalAccessException e)
						{
							e.printStackTrace();
						}
						catch (InvocationTargetException e)
						{
							e.printStackTrace();
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public static RoCAPICore instance()
	{
		return INSTANCE;
	}
	
}
