package valli.test.browse.amazon;

import java.util.Properties;

import valli.test.browse.amazon.PropertyFactory.PropertySource;

public class Props {
	public Properties properties = null;
	public static Props self = null;
	private static PropertySource source = null;
	
	private Props(PropertySource source)
	{
		if (null == properties)
		{
			properties = PropertyFactory.getProperties(source);
		}
	}
	
	public static void setPropertySource(PropertySource _source)
	{
		source = _source;
	}
	
	public static Props instance() throws Exception
	{
		if (null == self)
		{
			self = new Props(source);
		}
		return self;
	}
	
	/*
	 * Get the value from the properties.
	 * throw exception if property is not presents
	 */
	public static String getStringValue(String key) throws Exception
	{
		String op = Props.instance().properties.getProperty(key);
		if (null == op)
		{
			throw new IllegalArgumentException("Property not found");
		}
		return op;
	}
	
	/*
	 * Get the value from the properties.
	 * return empty string if property not set
	 */
	public static String getOptionalStringValue(String key) throws Exception
	{
		String op = Props.instance().properties.getProperty(key);
		if (null == op)
		{
			return "";
		}
		return op;
	}
	
	/*
	 * Get the integer value from the properties.
	 * throw exception if property is not presents
	 */
	public static int getIntValue(String key) throws Exception
	{
		String op = Props.instance().properties.getProperty(key);
		if (null == op)
		{
			throw new IllegalArgumentException("Property not found");
		}
		return Integer.parseInt(op);
	}
	
	/*
     * Get the long value from the properties.
     * throw exception if property is not presents
     */
    public static long getLongValue(String key) throws Exception
    {
        String op = Props.instance().properties.getProperty(key);
        if (null == op)
        {
            throw new IllegalArgumentException("Property not found");
        }
        return Long.parseLong(op);
    }
}
