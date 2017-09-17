package valli.test.browse.amazon;

public class Utils {

    /*
     * Wrapper for thread.sleep.
     */
	public static void hold(long mills)
	{
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Set system property indicated by 'key' if not already set.
	 */
	public static void overlaySystemProperty(String key, String value)
	{
		String op = System.getProperty(key);
		if (null == op || op.equals(""))
		{
			System.setProperty(key, value);
		}
	}
	
}
