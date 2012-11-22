
package implement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import core.Side;

/**
 * 
 * The annotation for valid RoC Plugins.
 * 
 * @author Elusivehawk
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin
{
	/**
	 * 
	 * @return The name of your plugin.
	 */
	public String name() default "A Plugin";
	
	/**
	 * 
	 * @return The version of your plugin.
	 */
	public String version() default "v1.0.0";
	
	/**
	 * 
	 * @return The description of your plugin; Use a String[] in order to have multiple lines.
	 */
	public String[] desc() default "A plugin. What else?";
	
	/**
	 * 
	 * @return The sides your plugin is designed for. If the side is not the active one, the plugin will still be loaded, but with a try/catch block.
	 */
	public Side[] sides() default Side.SERVER;
	
	/**
	 * 
	 * @return The version of Realms of Caelum your plugin is intended for. Mismatch versions will still work, albeit with a warning.
	 */
	public String RoCVersion() default "";
	
	/**
	 * 
	 * The annotation used for the loading method in your class file. NOTICE: One annotation per plugin.
	 * 
	 * @author Elusivehawk
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface Load{}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface OnButtonPush{}
	
}
