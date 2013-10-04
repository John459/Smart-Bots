package world.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import world.World;
import world.creatures.Critter;
import world.creatures.Food;

/**
 * View.java
 * @author John Zavidniak
 * The class in charge of displaying all the graphics
 */

public class View extends JFrame
{
    
    private World world;
    
    /**
     * Create a new View object with the given world and dimensions
     * @param world the world to draw to the screen
     * @param width the width of the JFrame
     * @param height the height of the JFrame
     */
    public View(World world, int width, int height)
    {
        super("Smart Bots");
        this.world = world;
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    /**
     * Using double-buffering, draw the graphics to the screen
     * @param g the Graphics object used to draw
     */
    @Override
    public void paint(Graphics g)
    {
        Graphics gOffScreen;
        Image offScreen = null;
        Dimension view = new Dimension(this.getWidth(), this.getHeight());
        offScreen = createImage(view.width, view.height);
        gOffScreen = offScreen.getGraphics();
        gOffScreen.setColor(this.getBackground());
        gOffScreen.fillRect(0, 0, view.width, view.height);
        gOffScreen.setColor(getForeground());
        draw(gOffScreen);
        g.drawImage(offScreen, 0, 0, this);
    }
    
    /**
     * Draw the graphics to the screen
     * @param g the Graphics object used to draw
     */
    private void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (Food f : world.getFood())
        {
            f.draw(g);
        }
        
        for (Critter c : world.getCritters())
        {
            c.draw(g);
        }
    }
    
}