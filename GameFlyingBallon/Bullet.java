/**
 * Description: This Class represents the Bullet in 2D movement. The bullet fire from the gun 
 *              and move vertically in the y direction. The bullet x,y coordintae vary  depend 
 *              on the gunning position and the balloon. The bullet has vertical speed but its
 *              horizontal speed is set to zero because it is not fored using projectile motion.
 *              The bullet hold by array list to make easyily fired the gun as an authomatic. 
 *              
 * 
 * @author (Fentahun Reta) 
 * @version (05/2015)
 */

public class Bullet {
    // Instance variables  
    private double x, y, vy;
    //Constants
    private static final double BULLETRADIUS = 0.01;
    private final static double WIDTH = 600.0;
    private final static double HEIGHT = 500.0;
    /**
     * Constructor
     * @param radius unit radius of the bullet and the y coordinates
     */
    public Bullet(double x, double y)
    {
        setY(y);
        setX(x);
        setVy(0);     
    }

    /**
     * This method sets the the speed of the bullet in the vertical direction
     * @param vy it moves y units vertically at a unit time.
     * @return always true 
     */
    public boolean setVy( double vy ) {
        this.vy = vy;
        return true;
    }

    /**
     * This method sets the y coordinates of the bullet as the bullet moves vertically 
     * @param y units as the bullet moves vertically
     * @return always true
     */
    public boolean setY( double y ) {
        this.y = y;
        return true;
    }
    /**
     * This method sets the x coordinates of the bullet  as it moves vertically because
     * the bullet moves in the vertical direction only. 
     * @param x coordintes of the bullet as it moves vertically 
     * @return always true
     */
     public boolean setX( double x ) {
        this.x = x;
        return true;
    }    

    /** This method gets the horizontal postion of the bullet
     * @return x the x-coordinate of the bullet as it move vertically
     * 
     */
    public double getX() { 
        return x; 
    }

    /** This method gets the vertical postion of the bullet
     * @return y the y-coordinate of the bullet as it move vertically
     * 
     */

    public double getY() { 
        return y; } 

    /**This method gets the vertical speed of the bullet as it fired by the gun
     * @return vy the spped of the bullet in the y direction as it move vertically
     * 
     */

    public double getVy() { return vy; }

    /** This method gets the radius of the bullet as it fired by the gun
     * @return radius the unit magintude of the bullet radius.
     * 
     */

    public double getRadius() { return BULLETRADIUS; }

    /** This method allow the bullet tp moves  in the Y direction
     * or vertcally
     *  
     */
    public void move() {
        moveY();
    }

    /**
     * This method allow the bullet to move in the y direction by adjusting its vertical velocity 
     */
    public void moveY() {
        setY( getY() + getVy()); 
    }

    /**
     * Checks for an collision between the Balloon and the Bullet. The bullet is moving
     * in the y direction to hit the balloon. so, it will be be checked by considering their
     * distance separtion. As the distance between the two object lessthan or equal to their
     * radius sum there will be collision.
     * 
     * @param other  Balloon object type 
     * @return true if the balloon intersect/collide with the bullet, other wise false.
     */
    public boolean isColliding(Balloon other) {
        //the radius of the Balloon 
        double otherRadius =  other.getRadius();
        //the differnce between the x and y coordinates of the Balloon and Bullet position
		double  xDiff=  (this.getX()/WIDTH-  other.getX());
		double  yDiff =  (this.getY()/HEIGHT- other.getY());		
		xDiff *= xDiff;
		yDiff *= yDiff;
		// the square of the distance between the balloon and the bullet.
		double distance1 = xDiff + yDiff;		
		// The sum of radius of the Balloon and the radius of the Bullet
		double distance2 = (otherRadius + this.getRadius());		
		// The squared of the sum of Balloon and Bullet radius
		distance2 *= distance2;		
		boolean x = (distance1 <= distance2) ? true : false;		 
        return x;	
    }
}