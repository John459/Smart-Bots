package world.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Food.java
 * @author John Zavidniak
 * The Food which Critters eat
 */

public class Food extends Rectangle
{
    
    public static final Color HEALTHY_FOOD_COLOR = Color.RED;
    public static final Color UNHEALTHY_FOOD_COLOR = Color.GRAY;
    public static final int FOOD_RADIUS = 8;
    
    /*
     * Critters will learn to eat healthy food,
     * and to avoid unhealthy food.
     */
    private boolean healthy;
    
    /**
     * Create a new Food object with the given position and health status
     * @param x the x-position of the new Food object
     * @param y the y-position of the new Food object
     * @param healthy the health status of the new Food object
     */
    public Food(int x, int y, boolean healthy)
    {
        super(x, y, Food.FOOD_RADIUS, Food.FOOD_RADIUS);
        this.healthy = healthy;
    }
    
    /**
     * Determine whether or not this Food is healthy
     * @return true if this Food is healthy, false if not
     */
    public boolean isHealthy()
    {
        return healthy;
    }
    
    /**
     * Set the health status of this food
     * @param healthy the health status of this food
     */
    public void setHealthy(boolean healthy)
    {
        this.healthy = healthy;
    }
    
    /**
     * Draw this Food
     * @param g the Graphics object used to draw
     */
    public void draw(Graphics g)
    {
        if (healthy)
        {
            g.setColor(Food.HEALTHY_FOOD_COLOR);
        }
        else
        {
            g.setColor(Food.UNHEALTHY_FOOD_COLOR);
        }
        g.fillOval(this.x - Food.FOOD_RADIUS, this.y - Food.FOOD_RADIUS,
                Food.FOOD_RADIUS*2, Food.FOOD_RADIUS*2);
    }
    
}