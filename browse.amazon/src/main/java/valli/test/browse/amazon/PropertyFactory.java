package valli.test.browse.amazon;

import java.util.Properties;

public class PropertyFactory
{
	public enum PropertySource
	{
		Default,
		File /* Not implemented yet, just a thought */
	}
	
	public static Properties getProperties(PropertySource source)
	{
		switch(source)
		{
		case Default:
			return new PropertyFactory.DefaultProperyBuilder().getProperties();
		default:
			return new Properties();
		}
	}


	
	/*
	 * Property Builders.
	 * 
	 * For any new PropertySource a new builder has to be
	 * implemented.
	 */
	interface PropertyBuilderInterface
	{
		public Properties getProperties();
	}
	
	/*
	 * DefaultPropertyBuilder
	 */
	static class DefaultProperyBuilder implements PropertyBuilderInterface
	{
		public Properties getProperties()
		{
			Properties props = new Properties();
			
			/*
			 * Datastructure
			 * test
			 *    |-conf
			 *    |     |- config1
			 *    |     |- config2
			 *    |
			 *    |- data
			 *    |      |-data1
			 *    |      |-data2
			 *    |        
			 */
			
			props.setProperty("test.conf.implicitwait", "3000");
			
			return props;
		}
	}
}
