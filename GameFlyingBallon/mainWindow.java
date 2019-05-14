import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Description: This Class represents the main window of the application. It interacts
 *              with the view and the model classes. It implements also the actionlistener.
 * 
 * @author (Fentahun Reta) 
 * @version (05/2015)
 */
public class mainWindow implements ActionListener
{
    //instance variables 
    private double gunX;
    private Timer timer;
    private ViewMotion view; 
    public mainWindow(){
        gunX = 0.5;

        view = new ViewMotion();
        view.setController(this);
        timer = new Timer(500, this);
        timer.start();
    }
    /** This method manage the movement of the gun on the window in percentage
     * It controls the gun not to move outside the boundary of the window on the
     * left side 
     * 
     */

    public void moveLeft(){
        gunX -=0.01;
        if(gunX < 0)
            gunX = 0;
    }
    /** his method manage the movement of the gun on the window in percentage. It
     * controls the gun not to move outside the boundary of the window on the
     * right side.
     */

    public void moveRight(){
        gunX +=0.01;
        if(gunX >1)
            gunX = 1;
    }
    /** This method amnge the initial position of the gun
     *  @return gunX half of the width of the JPanel window.  
     * 
     */
    public double getGunX(){
        return gunX;
    }
    /** This method perfroms to move the balloon as every time and check the
     * collision with the bullet.
     * 
     */

    public void actionPerformed(ActionEvent e){
        //update balloon position
        view.moveBalloon();
        view.checkCollision();        
    }
    /** The maian static method
     * 
     */

    public static void main(String[] args) {
        
        new mainWindow();
        System.out.println("The Previous high score is: " +HighScore.readHighScore());       

    }
}
