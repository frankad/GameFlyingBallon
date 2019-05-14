
/**
 * Description: This Class represents the Gun in 2D movement. The gun moves along the
 *              horizontal direction and pointed upward to shoot the balloon.It is controlled
 *              by the left, right and upward arrow keys.
 * 
 * @author (Fentahun reta) 
 * @version (05/2015)
 */
public class Gun 
{
    //instance variables 
    private double x, y; // gun coordibtaes
    private double gunX;
    //constants
    private static final double GUN_WIDTH = 20;
    private static final double GUN_HEIGHT = 30 ;

    public Gun(){
        // initially set the gun at the middle of the frame window
        gunX = 0.5;        
        setX(x);
    }
    /** This method gets the x coordinates of the gun as it moves 
     * horizontal drection
     * @return x the x-coordinate of the gun as it move horizontally
     * 
     */
    public double getX(){
        return x;
    }
    /** This method gets the y coordinates of the gun as it moves 
     * vertical drection
     * @return y the y-coordinate of the gun as it move vertically
     * 
     */    
    public double getY(){
        return y;
    }
    /** This method sets the y coordinates of the gun as it moves 
     *  vertical drection
     *  @return y the y-coordinate of the gun as it move vertically
     * 
     */    
     public double setY(double y){
        return y;
    }
    /** This method sets the x coordinates of the gun as it moves 
     * horizontal drection
     * @return x the x-coordinate of the gun as it move horizontall
     * 
     */
     public double setX(double x){
        return y;
    }
    /** This method gets the width of the gun      
     * @return GUN_WIDTH the gun width
     * 
     */

    public double getWidth(){
        return GUN_WIDTH;
    }
    /**  This method gets the height of the gun      
     *  @return GUN_HEIGHT the gun height
     * 
     */
    public double getHeight(){
        return GUN_HEIGHT;
    }
    /** This method allows the gun to move in the left direction 
     *  as the left key arrow pressed
     * 
     */
    public void moveLeft(){
        gunX -=0.01;
        if(gunX < 0)
        gunX = 0;
    }
    /** This method allows the gun to move in to right direction 
     *  as the right key arrow pressed
     * 
     */
    public void moveRight(){
        gunX +=0.01;
        if(gunX >1)
        gunX = 1;
    }
}

