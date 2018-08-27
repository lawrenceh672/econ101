import java.util.Random;

class Coord
{
	//An infintely extensible multidimensional coordinate class, double precision with an array
	//Dimension array coodinates
	public static int X = 0;
	public static int Y = 1;
	public static int Z = 2;
	public static int T = 3;

	//Hard code dimensions for now
	private static int DIMENSIONS = 4; //Can be set dynamic later
	private double[] dimension;

	static Random r = new Random();
	public String asString() {return "(" + dimension[X] + ","+ dimension[Y] + ")"; }

	public Coord()
	{
		dimension = new double[DIMENSIONS];
		dimension[X] = 0;
		dimension[Y] = 0;
		dimension[Z] = 0;
		dimension[T] = 0;
	}
	public Coord(int xin, int yin)
	{
		dimension = new double[DIMENSIONS];
		dimension[X] = xin;
		dimension[Y] = yin;
		dimension[Z] = 0;
		dimension[T] = 0; //time starts at zero.
	}

	//Dimension value getters
	public double dim(int dimin) { return dimension[dimin]; }
	public double getX() { return dimension[X]; }
	public double getY() { return dimension[Y]; }
	public double getZ() { return dimension[Z]; }
	public double getT() { return dimension[T]; }

	public void increment(int dimin) { dimension[dimin]++; }
	public void setDimensionValue(double val, int dimin) {dimension[dimin] = val;}

	public int translateX(int sw, int ratio) {
		int res;
		double xd;
		double midX;

		midX = sw / 2;
		xd = dimension[X]  + midX;
		xd = xd * ratio;
		res = (int)xd;
		return res;
	}

	public int translateY(int sh, int ratio) {
		int res;
		double yd;
		double midY;

		midY = sh / 2;
		yd = dimension[Y]  + midY;
		yd = yd * ratio;
		res = (int)yd;
		return res;
	}
}
