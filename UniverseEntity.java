import java.awt.image.BufferedImage;


public class UniverseEntity extends Entity
	{
	public static int SIZEOFUNIVERSE = 512;
	public static int INFINITE = -1;
	public double scaleRatio = 1;

	public UniverseEntity()
	{
		name = "Universe 1";
	}

	public UniverseEntity(String string, BufferedImage i, int s, Coord c1)
		{
		super(string, i, s, c1);
		}

	}
