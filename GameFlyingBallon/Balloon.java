/**
 * Description: This Class represents the Balloon in 2D movement. The Balloon move
 *              horizontally in random x coordinate position but fixed y coordinqate 
 *              to make the balloon move at fixed height from the gun but its distance
 *              vary from one point to an other coordinte. The coordinte taken in percentage
 *              with the borad width and height.
 * 
 * @author (Fentahun Reta) 
 * @version (05/2015)
 */
public class Balloon
{
    // instance variables
    private double x, y; // the current position of the ballon.
    private double vx; // speed of the balloon       
    private int steps; // steps taken by the balloon movement    
    private boolean direction; 
    private static final double BALLONRADIUS = 0.05;
    private static final boolean RIGHT = true;
    private static final boolean LEFT = false;
    private static final int MAXSTEPS = 8;
    private static final double STEPSIZE = 0.01;

   // Constructor
    public Balloon(){
        // make the y coordintes for the balloon position is fixed at this point.
        y = 0.1;  
        setX(x); 
        // the x coordinate generated randomly
        x = Math.random();       
        setVx(0);
        //calling the change in direction method
        changeDirection();

    }
    /** This method allows the ball to move either half way to the left or
     * to the right of the window. If the random value lessthan 0.5 its
     * direction is in the right side other wise in the left side
     * 
     */
    private void changeDirection(){
        if(Math.random() >  0.5){
            direction = RIGHT;
        }else{
            direction = LEFT;
        }
        //steps start at least from 1. because random gives from 0 to 1. 
        steps = (int)(MAXSTEPS*Math.random()+1);
    }

    /**
     * This method sets the the speed of the Balloon in the horizontal direction
     * @param vx units to move horizontally at a time of unit
     * @return always true 
     */
    public boolean setVx( double vx ) {
        this.vx = vx;
        return true;
    }

    /**
     * This method sets the vertical postion (y coordinates) of the balloon
     * @param y units to move vertically 
     * @return always true
     */
    public boolean setY( double y ) {
        this.y = y;
        return true;
    }

    /**
     * This method sets the x coordinates of the balloon  on the horizontal direction
     * @param x units to move horizontally
     * @return always true
     */
    public boolean setX( double x ) {
        this.x = x;
        return true;
    }
    
    /** This method gets the horizontal postion of the balloon 
     * @return x the x-coordinate of the balloon as it movehorizontally
     * 
     */
    public double getX(){
        return x;
    }

    /** This method gets the vertical postion of the balloon
     * @return y the y-coordinate of the balloon as it move vertically
     * 
     */
    public double getY(){
        return y;
    }
    /** This method gets the horizontal speed of the balloon as it move x unit horizontally
     * at a unit of time.
     * @return vx the speed of the balloon in the x direction.
     * 
     */
    public double getVx() {
        return vx; 
    }
    /** This method gets the radius of the balloon 
     * @return radius the unit magintude of the balloon radius.
     * 
     */
    public double getRadius(){
        return BALLONRADIUS;
    }
     /**
     *  This method makes the ball to move in the X direction by calling
     *  the changeDirection method. That is it may move either to the left 
     *  or to right direction
     * 
     */
    public void move() {
        if(steps < 0)
            changeDirection();
        moveX();
    }
    
    /**
     * This method make the balloon to move in the x direction by its horizontal speed
     * by adjusting the steps taken by the ball
     */
    public void moveX() {
        if(direction == RIGHT){

            x +=STEPSIZE;
            if(x > 1)
                x=0.8;

        }else{
            x -=STEPSIZE;
            if(x<0)
                x=0.1;
        }
        steps--;        
    }  
}
