import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import sun.tools.jconsole.inspector.XDataViewer;

class Resource
{
	String name;
	int amount;
	Coord c = new Coord();

	public Resource(String s)
	{
		name = s;
		amount = 10;
	}
}

class Product
{
	String name;
	Resource resources[];

	public Product(String iname)
	{
		name = iname;
	}
}




public class econ101
{
	//Lets make a class to draw the grid
	public static JFrame frame = new JFrame("econ101");

	//Why more than one Random?
	static Random random = new Random();

	//Populate the world a bit
	public static ArrayList<Entity> farmers = new ArrayList();
	public static ArrayList<Entity> resources = new ArrayList();
	public static Entity universe;
	public static mapPanel[][] grid = new mapPanel[10][10];

	//Preset image drawn from storage
	public static BufferedImage imgFarmer;
	public static BufferedImage imgResource;
	public static BufferedImage imgUniverse;

	private static void createAndShowGUI()
	{
		//Display the window.
		frame.pack();
			System.out.println("SetVisible");
		frame.setVisible(true);
	}

	public static void main(String args[])
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
		//Setup color values
		//Create and set up the window.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Open the graphic files I so masterfully created.
		try {
			imgFarmer = ImageIO.read(new File("house.bmp"));
			imgResource = ImageIO.read(new File("resource.bmp"));
			imgUniverse = ImageIO.read(new File("universe.bmp"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		//Make the universe
		UniverseEntity universe = new UniverseEntity("Universe", imgUniverse, UniverseEntity.INFINITE, new Coord(0,0));
		universe.setPanel(new mapPanel(universe.getCoord(), universe.getImage(), universe));
		frame.add(universe.getPanel());


		//Initialize the towns and farmers, in 4 corners of the universe
		//Name, imagefile, size, then the coordinate
		Entity e;
		e = new Entity("Farmer 1", imgFarmer, 100, new Coord(512,0));
		farmers.add(e);

		e = new Entity("Farmer 2", imgResource, 100, new Coord(-512,0));
		resources.add(e);

		e = new Entity("Resource 1", imgResource, 100, new Coord(0,200));
		resources.add(e);
		e = new Entity("Resource 1", imgResource, 100, new Coord(3,56));
		resources.add(e);
		e = new Entity("Resource 1", imgResource, 100, new Coord(321,99));
		resources.add(e);
		e = new Entity("Resource 1", imgResource, 100, new Coord(678,440));
		resources.add(e);
		e = new Entity("Resource 1", imgResource, 100, new Coord(199,345));
		resources.add(e);

		e = new Entity("Resource 2", imgFarmer, 100, new Coord(0,-200));
		farmers.add(e);

		//Populate the map with 1 starting point, then seed it with members
		for(int i = 0;i < farmers.size(); i++)
			universe.addEntity(farmers.get(i));
		for(int i = 0;i < resources.size(); i++)
			universe.addEntity(resources.get(i));

		/* old way to add entities
		grid[farmer1.getCoord().getX()][farmer1.getCoord().getY()].addEntity(farmer1);
		grid[farmer2.getCoord().getX()][farmer2.getCoord().getY()].addEntity(farmer2);
		grid[town1.getCoord().getX()][town1.getCoord().getY()].addEntity(town1);
		grid[town2.getCoord().getX()][town2.getCoord().getY()].addEntity(town2);
		*/

		//Start the universe loop, advance one in the z dimension to represent time in the 2d map
		//Go for a max of 100 seconds to begin with
		double t; //t for time
		int TIME = Coord.T; //The time dimension as defined by a coordinate
		int Xaxis = Coord.X;
		int Yaxis = Coord.Y;

		t = universe.t();

		//Calculate movement for the entities
		double x;
		double y;
		Coord c;
		int entitycount = universe.count();
		String userInput;


		//Simulate some action, move the entities around and time stamp them
		for(int i = 0;i < 100;i++)
		{
			System.out.println(universe); //Take a snapshot of the universe
			t++;
			universe.incrementAll(1, TIME); //Increment time for all entities within UniverseEntity
			for(int index = 0;index< entitycount;index++)
			{
				e = universe.entities.get(index);
				c = e.c;

				//Do a subtraction
				x = c.dim(Xaxis);
				x--;

				y = c.dim(Yaxis);
				y--;

				c.setDimensionValue(x, Xaxis);
				c.setDimensionValue(y, Yaxis);
				c.setDimensionValue(t, TIME);

			}
			universe.toString();
		}
		//Now implement the slider, as a swing slider
		createAndShowGUI();
	}
}
