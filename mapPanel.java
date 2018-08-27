import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

class mapPanel extends JPanel implements KeyListener
{
	/**
	 * The panel with the UniverseEntity that encompasses all
	 */
	private static final long serialVersionUID = 1L;
	Coord coord;
	//load a default farm house
	File imgFile;
	BufferedImage img;
	UniverseEntity entity; //The entity its going to draw
	boolean isNull;
	public boolean keyPressed;
	//Define Infinity

	mapPanel() {
		imgFile = null;
		img = null;
		entity = null;
		isNull = true;
		addKeyListener(this);
	}

	mapPanel(Coord c, BufferedImage bi, UniverseEntity e)
	{
		isNull = false;
		coord = c;
		img = bi;
		entity = e;
		addKeyListener(this);
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setEntity(UniverseEntity e) {
		entity = e;
	}

	//KeyListener implementation
	public void keyPressed(KeyEvent e) {
		//char c = e.getKeyChar();
		keyPressed = true;
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
        int screenHeight = (int)this.getBounds().getHeight(); //For translating coord to put 0,0 at center of screen
        int screenWidth = (int)this.getBounds().getWidth();
		int x; //The final non decimal pixel location
		int y;

		int size; //The size of the entity
		//Draw yourself first
		Entity e = this.entity;
		String title = e.getName();


		//Draw the plane first
		g.drawLine(screenWidth/2, 0, screenWidth /2, screenHeight); // y axis
		g.drawLine(0, screenHeight / 2, screenWidth, screenHeight / 2); //x axis

		g.drawString("(" + screenWidth /2 + "),("+ screenHeight/2 + ")", screenWidth / 2, 20); //top middle
		g.drawString("(" + screenWidth /2 + "),(-"+ screenHeight/2 + ")", screenWidth / 2, screenHeight - 20); //bottom middle
		g.drawString("(-" + screenWidth /2 + ",0)", 0, screenHeight / 2); //left horizontal
		g.drawString("(" + screenWidth /2 + ",0)", screenWidth - 60, screenHeight / 2); //right horizontal


		//Do the main universe first
		size = e.size;
		if(size == -1)
			size = (int)e.getMax();

		x = e.scaleX(screenHeight-(size), screenWidth-(size)); //Get the scaling ratio from the entity
		y = e.scaleY(screenHeight-(size), screenWidth-(size)); //Get the scaling ratio from the entity


		//Cant draw null stuff
    	if(!isNull)
    	{
			g.drawString(title,x,y);
			//g.drawImage(e.img, x, y, null); //Icon Image
			//Draw a painted circle at it coordinate. center coord to 0,0 for middle of screen.
			//Add half the width and half the height to translate all coordinates to 0,0 to middle of screen
			g.drawOval(x, y, size, size);


			//Then draw the remaining sub entities
        	for(int i=0;i<entity.count();i++)
        	{
        		//e is local shorthand for Entity
        		e = entity.getEntity(i); //Start going through the array and draw each one
        		size = e.size;
        		x = e.scaleX(screenHeight-size, screenWidth - size); //Get the scaling ratio from the entity
        		y = e.scaleY(screenHeight-size, screenWidth - size); //Get the scaling ratio from the entity

        		//Call out the specific entity items needed to draw it onscreen
        		title = e.getName();

        		//Draw a painted circle at it coordinate. center coord to 0,0 for middle of screen.
        		//Add half the width and half the height to translate all coordinates to 0,0 to middle of screen
        		g.drawOval(x, y, size, size); //Center the size circle too
        		g.drawImage(e.img, x, y, null);
        		g.drawString(title + "\r\n" + e.c.asString(),x,y);
        	}

        }

    }

}
