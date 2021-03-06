package world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import world.creatures.Critter;

/**
 * Genetics.java
 * @author John Zavidniak
 * The class in charge of controlling the,
 * reproduction, swapping of genes, and mutation of genes
 * for all Critters
 */

public class Genetics
{
    
    private static final int STEP_TIME = 1000;
    private static final double CROSSOVER = 0.7;
    private static final double MUTATE = 0.1;
    
    private World world;
    private long lastMutation;
    
    /**
     * Create a new Genetics object which operates on a given World
     * @param world the world to operate on
     */
    public Genetics(World world)
    {
        this.world = world;
        this.lastMutation = System.currentTimeMillis();
    }
    
    /**
     * Swap the genes of two Critters
     * @param a the first Critter
     * @param b the second Critter
     */
    private void swap(Critter a, Critter b)
    {
        String aBits = Integer.toBinaryString(a.getFoodEaten());
        String bBits = Integer.toBinaryString(b.getFoodEaten());
        
        int len = (aBits.length() > bBits.length()) ? bBits.length() : aBits.length();
        int swapPos = (int) (Math.random() * len);
        
        String aSubstr = aBits.substring(0, swapPos);
        String bSubstr = bBits.substring(0, swapPos);
        
        int aFoodEaten = Integer.parseInt(aSubstr + bBits.substring(swapPos), 2);
        int bFoodEaten = Integer.parseInt(bSubstr + aBits.substring(swapPos), 2);
        
        a.setFoodEaten(aFoodEaten);
        b.setFoodEaten(bFoodEaten);
    }
    
    /**
     * Mutate the genes of the given Critter
     * @param c the Critter whose genes should be mutated
     */
    private void mutate(Critter c)
    {
        if (Math.random() >= MUTATE || Math.random() >= MUTATE)
        {
            return;
        }
        System.out.println("mutate");
        String bits = Integer.toBinaryString(c.getFoodEaten());
        int pos = (int) (Math.random() * bits.length());
        char bit = bits.charAt(pos);
        bit = (bit == '0') ? '1' : '0';
        
        String newBits = bits.substring(0, pos) + bit;
        if (pos < bits.length() - 1)
        {
            newBits = newBits + bits.substring(pos+1);
        }
        
        int foodEaten = Integer.parseInt(newBits, 2);
        c.setFoodEaten(foodEaten);
    }
    
    /**
     * Select a Critter from the list of all Critters.
     * A Critter's chance of being selected is directly
     * proportional to the amount off food they've eaten
     * @return the Critter who has been selected
     */
    private Critter selectCritter(List<Critter> critters)
    {
        List<Critter> potCritters = new ArrayList<Critter>();
        for (Critter c : critters)
        {
            int count = Math.round(c.getFoodEaten() / 100.0f);
            for (int i = 0; i < count; i++)
            {
                potCritters.add(c);
            }
        }
        if (potCritters.size() == 0)
        {
            return critters.get((int) (Math.random() * critters.size()));
        }
        int index = (int) (Math.random() * potCritters.size());
        return potCritters.get(index);
    }
    
    /**
     * Create a new population of Critters
     */
    public void reproduce()
    {
        if (System.currentTimeMillis() - lastMutation < STEP_TIME)
        {
            return;
        }
        List<Critter> critters = new ArrayList<Critter>(world.getCritters());
        List<Critter> newCritters = new LinkedList<Critter>();
        int size = critters.size();
        while (newCritters.size() < size - size % 2)
        {
            Critter a = this.selectCritter(critters);
            critters.remove(a);
            Critter b = this.selectCritter(critters);
            critters.remove(b);
            
            if (Math.random() < CROSSOVER)
            {
                swap(a, b);
            }
            
            mutate(a);
            mutate(b);
            
            newCritters.add(a);
            newCritters.add(b);
        }
        world.setCritters(newCritters);
        lastMutation = System.currentTimeMillis();
    }
    
}