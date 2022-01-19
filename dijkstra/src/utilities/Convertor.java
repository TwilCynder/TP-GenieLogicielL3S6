package utilities;

public class Convertor {
	/**
	 * Convert a Long[] to a int[]
	 * @param parents
	 * @return
	 */
	public static int[] longToInt(long[] parents) {
		int size = parents.length;
		int i = 0;
		int[] iTab = new int[size];
		for(Long l : parents) {
			iTab[i++] = l.intValue();
		}
		return iTab;
	}
	
	
	
	/**
	 * Convert a double[] to a float[]
	 * @param fTab
	 * @return
	 */
	public static float[] doubleToFloat(double[] distances) {
		int size = distances.length;
		int i = 0;
		float[] fTab = new float[size];
		for(Double d : distances) {
			fTab[i++] = d.floatValue();
		}
		return fTab;
	}
}
