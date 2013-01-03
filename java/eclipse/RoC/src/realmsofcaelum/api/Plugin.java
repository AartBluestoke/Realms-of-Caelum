
package realmsofcaelum.api;

/**
 * 
 * 
 * 
 * @author atrain99, briman0094, Elusivehawk, Kovu, ObsequiousNewt
 */
public @interface Plugin
{
	public String name();
	
	public String version() default "v1.0.0";
	
	public EnumReleaseType releaseType() default EnumReleaseType.RELEASE;
	
}
