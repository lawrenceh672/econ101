import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Entity
{
	String name;
	Coord c;
	//Dimension array coodinates
	public static int X = Coord.X;
	public static int Y = Coord.Y;
	public static int Z = Coord.Z;
	public static int T = Coord.T;


	//User interface stuff
	public BufferedImage img;
	public mapPanel icon;
	ArrayList<Entity> entities = new ArrayList();
	static double max = 1; //The maximum dimensional radii
	public boolean keyPressed() { return icon.keyPressed; }


	//Size is radius from a given coordinate, -1 means infinite
	int size;
	Coord coord; //Where in space is it in relation to the center of the universe?
	ArrayList<Coord> coords = new ArrayList(); //The history of coords
	NComponent_Vector v;

	public Entity() { name = "Rolo"; }

	public Entity(String string, BufferedImage i, int s, Coord c1)
	{
		name = string;
		img = i;
		c = c1;
		size = s;
		if(c.dim(X) > max) //Every new entity tests the maximum radius of the universe.
			max = c.dim(X);

		if(c.dim(Y) > max)
			max = c.dim(Y);
	}

	public String print()
	{
		return c.dim(X) + " " + c.dim(Y) + " " + name;
	}

	public Coord getCoord()
	{
		return c;
	}

	//dimension value return
	public double dim(int dimin) { return  c.dim(dimin); }
	//Some human readable names
	public double x() { return c.dim(X); }
	public double y() { return c.dim(Y); }
	public double z() { return c.dim(Z); }
	public double t() { return c.dim(T); }
	public String getName() { return name; }
	public mapPanel getIcon( ) { return icon; }


    //Manipulating the entities
	public int count() { return entities.size(); } //For how many entities exist inside it
	public Entity getEntity(int i) { return entities.get(i); }



	public double getMax() { return max; } //Maximim distance from the center

	public void incrementAll(int value, int dimin) {
		int loops = entities.size();
		for(int i = 0; i < loops; i++)
		{
			entities.get(i).incrementAll(value, dimin);
		}
	}
	public void addEntity(Entity e) {
		entities.add(e); //Check for max size everytime something is added, for scaling purposes
		if(e.x() > max)
			max = e.x();

		if(e.y() > max)
			max = e.y();
	}

	public void setPanel(mapPanel mp) {
		icon = mp;
	}

	//Interface elements
	public mapPanel getPanel() { return icon; }
	public BufferedImage getImage() { return img; }


	public int scaleX(int height, int width) {
		double ratio;
		double x;

		if(height > width)
			ratio = (double)max/(double)height;
		else
			ratio = (double)max/(double)width;
		if(ratio > 1) ratio = 1; //
		ratio = 1; //scaling looks weird
		x = (double)c.dim(X) * ratio + (width /2); //move the 0,0 to the middle of the screen

		return (int)x;
	}

	public int scaleY(int height, int width) {
		double ratio;
		double y;

		if(height > width)
			ratio = (double)max/(double)height;
		else
			ratio = (double)max/(double)width;
		if(ratio > 1) //Dont want to zoom out let is sink in size
			ratio = 1;
		ratio = 1; //scaling looks weird

		y = (double)c.dim(Y) * ratio + (height /2);

		return (int)y;
	}

}
