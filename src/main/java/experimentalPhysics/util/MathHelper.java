package experimentalPhysics.util;

public class MathHelper
{
	public static int boolArrayToInt(boolean[] bits)
	{
		int result = 0;
		for (int e = 0; e < bits.length; e++)
		{
			result += bits[e] ? Math.pow(2, e) : 0;
		}
		return result;
	}
	

	/**Returns the difference of two integer values<br><br>
	 * <code>
	 * |a - b|
	 * </code>
	 * @param a
	 * @param b
	 */
	public static int diff(int a, int b)
	{	
		return Math.abs(a - b);
	}
}
