package world;

/**
 * Vector.java
 * @author John Zavidniak
 * A two dimensional vector
 */

public class Vector
{
    
    private double dir;
    private double mag;
    
    /**
     * Create a new vector with the given direction and magnitude
     * @param dir the direction of the new vector
     * @param mag the magnitude of the new vector
     */
    public Vector(double dir, double mag)
    {
        this.dir = dir;
        this.mag = mag;
    }
    
    /**
     * Get a normalized version of this vector
     * @return a new vector whose components have been divided by this vector's magnitude
     */
    public Vector normalized()
    {
        double x = this.getX() / mag;
        double y = this.getY() / mag;
        double mag = Math.sqrt(x*x + y*y);
        return new Vector(dir, mag);
    }
    
    /**
     * Get the angle been this vector and another vector
     * @param v the other vector to use to calculate the vector
     * @return the angle between this vector and vector v
     */
    public double angleBetween(Vector v)
    {
        double dotProduct = this.getX()*v.getX() + this.getY()*v.getY();
        return Math.acos(dotProduct / (this.mag * v.mag));
    }
    
    /**
     * Get the direction this vector is pointing
     * @return the direction this vector is pointing
     */
    public double getDir()
    {
        return dir;
    }
    
    /**
     * Get the magnitude of this vector
     * @return the magnitude of this vector
     */
    public double getMag()
    {
        return mag;
    }
    
    /**
     * Get the x-component of this vector
     * @return the x-component of this vector
     */
    public double getX()
    {
        return mag * Math.cos(dir);
    }
    
    /**
     * Get the y-component of this vector
     * @return the y-component of this vector
     */
    public double getY()
    {
        return mag * Math.sin(dir);
    }
    
    /**
     * Set the x-component of this vector
     * @param x the new x-component of this vector
     */
    public void setX(double x)
    {
        double y = this.getY();
        this.mag = Math.sqrt(x*x + y*y);
        this.dir = Math.atan2(y, x);
    }
    
    /**
     * Set the y-component of this vector
     * @param y the new y-component of this vector
     */
    public void setY(double y)
    {
        double x = this.getX();
        this.mag = Math.sqrt(x*x + y*y);
        this.dir = Math.atan2(y, x);
    }
    
    /**
     * Set the direction of this vector
     * @param dir the new direction of this vector
     */
    public void setDir(double dir)
    {
        this.dir = dir;
    }
    
    /**
     * Set the magnitude of this vector
     * @param mag the new magnitude of this vector
     */
    public void setMag(double mag)
    {
        this.mag = mag;
    }
    
}