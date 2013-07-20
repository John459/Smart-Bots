import java.util.ConcurrentModificationException;
import java.util.List;

import world.World;
import world.creatures.Critter;
import world.creatures.Food;
import world.graphics.View;

/**
 * Main.java
 * @author John Zavidniak
 * Manage the running and drawing of
 * the simulation
 */

public class Main implements Runnable
{
    
    private World world;
    private View view;
    
    /**
     * Begin the simulation
     * @param world the world to use
     * @param view the graphical display
     */
    public Main(World world, View view)
    {
        this.world = world;
        this.view = view;
        new Thread(this).start();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        World world = new World();
        List<Critter> critters = world.getCritters();
        List<Food> food = world.getFood();
        for (int i = 0; i < 16; i++)
        {
            critters.add(new Critter((int) (Math.random() * World.WORLD_WIDTH),
                    (int) (Math.random() * World.WORLD_HEIGHT)));
        }
        for (int i = 0; i < 20; i++)
        {
            food.add(new Food ((int) (Math.random() * World.WORLD_WIDTH), (int) (Math.random() * World.WORLD_HEIGHT),
                    true));
        }
        for (int i = 0; i < 10; i++)
        {
            food.add(new Food ((int) (Math.random() * World.WORLD_WIDTH), (int) (Math.random() * World.WORLD_HEIGHT),
                    false));
        }
        View view = new View(world, World.WORLD_WIDTH, World.WORLD_HEIGHT);
        new Main(world, view);
    }

    /**
     * Update the world,
     * then draw the graphics
     */
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                world.update();
            }
            catch (ConcurrentModificationException e)
            {
                try
                {
                    Thread.sleep(5);
                }
                catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
                continue;
            }
            view.repaint();
        }
    }
    
}
