import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;

/**
 * Description: This class represents the view of the class it displays the collision of the Balloon and the
 *              Bullet. It also shows and control the motion of the Balloon. It has a paint method that displays
 *              the image of the Balloon, Bullets and the crashed balloon when the Balloon and the Bullet collides.
 *              It extends the JPanel and implements KeyListener and ActionListener. 
 * 
 * @author (Fentahn Reta) 
 * @version (05/2015)   
 */
@SuppressWarnings("serial")
public class ViewMotion extends JPanel implements KeyListener, ActionListener{           

    // instance variables 
    private Timer timer;           
    private mainWindow controller;
    private BufferedImage image;  
    private ArrayList <Bullet> bullet;
    private static int ballonsHit;
    private Timer countDownTimer;     
    private JLabel timx;         
    private Balloon balloon;
    private boolean isCrash;
    private int shDist;
    private boolean gameOver = false;
    private Gun gun = new Gun();
    private int seconds = 4000;
    

    //Constants
    public static final int TIMELIMIT = 60;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500; 
    private static final int SET = 15;
    private static final int SPACEKEY = 32;
    private static final int RIGHTKEY = 39;    
    private static final int LEFTKEY = 37;

    /** The constructor it initialize the instance varibles and construct
     * 
     */

    public ViewMotion() {
        JOptionPane.showMessageDialog (null,"Welcome to Balloon shooting Game!\nThe time limit is 60 seconds to play.  "
            + "With in this time you can break as many balloons as you can and get high score.\n\nDirections to play this game.\nThe Left"
            + " arrow key moves the gun to the left." + "\nRight arrow key moves the gun to the right."
            + "\nSpace key used to fire the bullet or for shooting.\n\nWhen the time is up the game will over. Thus the gun can not fire and the balloon stop moving. "
            + "\npress ok to start the game \n\nThe Previous highest score is: " +HighScore.readHighScore());
        try{    
            image = ImageIO.read(new File("imageBackground.jpg"));
            
        }
        catch(IOException e){
        }

        bullet= new ArrayList <Bullet> ();
        //balloon = new ArrayList <Balloon> ();
        balloon = new Balloon();
        countDownTimer = new Timer(4000, this);
        countDownTimer.start();
        timx = new JLabel(); 
        this.add(timx);        
        JFrame frame = new JFrame("Shooting game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        //Set the background color Green. This actually does not show up because there is a background image.
        this.setBackground(Color.GREEN);
        //add the key listener in the the Jpanel
        this.addKeyListener (this);
        // Checks wheather the key board can send input to the component and it makes focusable.
        this.setFocusable(true);
        //update the image every 5 milliseconds
        timer = new Timer(5, this);
        //add the JPanel in to the JFrame
        frame.add(this);
        //resize to fit the content
        frame.pack();
        //make visible the frame
        frame.setVisible(true);             
    }

    /** This method custom and paint the Balloon, Bullet, and the Gun on to the panel
     *  It also paint the fagmented ball like object after the bullet hit the balloon.
     * 
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0, null);
        if(gameOver)
            return;

        // Drwaing the gun and set its color Blue.        
        g.setColor(Color.BLUE);
        //dimension of the panel
        Dimension size = getSize(); 
        timer.start(); // paints every 5 millisecond
        double gunX = (controller!=null? controller.getGunX():0.5);
        //cast to int the x and y coordinates of the the gun
        int y = (int)(size.height - gun.getHeight());
        int x = (int) (size.width*gunX - gun.getWidth()/2);        
        g.fillRect(x, y, (int)gun.getWidth(), (int)gun.getHeight());

        // paint the balloon and set its color red at the given random coordinate.       
        int ballonPositionY = (int)(size.height*balloon.getY());
        int ballonPositionX = (int) (size.width*balloon.getX());                 
        g.setColor(Color.RED);       
        g.fillOval(ballonPositionX-SET, ballonPositionY, (int)(balloon.getRadius()*2*size.width-SET), (int)(balloon.getRadius()*2*size.height));        
        //Hold the bullet in arraylist and allow the play to shoot two or more bullet at a time
        for(Bullet z: bullet){ 
            //paint and draw the bullet and set its color red
            g.setColor(Color.BLACK);             
            g.fillOval((int) z.getX(),(int) z.getY(), (int)(z.getRadius()*2*size.width),(int)(z.getRadius()*2*size.height));
            if (  z.getX() > size.width || z.getY() > size.height){
                bullet.remove(z);
            }            
        }
        //This if statement check whether the balloon crashed or not. If it is crashed it paints the fragments of the balloon in four parts
        if(isCrash){
            g.setColor(Color.RED);         
            g.fillOval(ballonPositionX-SET+shDist, ballonPositionY,(int)(balloon.getRadius()*2*size.width-SET)/2,(int)(balloon.getRadius()*2*size.height)/2);
            g.fillOval(ballonPositionX-SET+shDist, ballonPositionY,(int)(balloon.getRadius()*2*size.width-SET)/2,(int)(balloon.getRadius()*2*size.height)/2);
            g.fillOval(ballonPositionX-SET, ballonPositionY+shDist,(int)(balloon.getRadius()*2*size.width-SET)/2,(int)(balloon.getRadius()*2*size.height)/2);
            g.fillOval(ballonPositionX-SET, ballonPositionY-shDist,(int)(balloon.getRadius()*2*size.width-SET)/2,(int)(balloon.getRadius()*2*size.height)/2);
            g.fillOval(ballonPositionX-SET-shDist, ballonPositionY, (int)(balloon.getRadius()*2*size.width-SET)/2, (int)(balloon.getRadius()*2*size.height)/2);
            shDist +=2; // it helps to adjust the coordination of each fragment in different direction.
        }

    }

    /**  This method makes the balloon move as the balloon created in each random location. 
     * 
     */

    public void moveBalloon(){
        if(balloon == null)
            return;
        balloon.move();
    }

    /** This method check if there is a collision between the balloon and the bullet
     *  It update to create new balloon if the balloon hit by th bullet. It counts the 
     *  number of hits.
     * 
     */
    public void checkCollision(){        

        for(Bullet z: bullet){
            if(z.isColliding(balloon)){
                balloon = new Balloon();
                ballonsHit++;
                //make the boolean isCrash true if there is cllision
                isCrash = true;
                //make the scale for the coordination of fragmented  set to zero 
                shDist = 0;
            }

        }
    }   

    /** Starts the animation timer for this panel
     * 
     */
    public void start(){
        timer.start();
    }

    /**
     * stops the animation timer for this panel (pauses)
     */
    public void stop() {
        timer.stop();
    }    

    /** This method capture the key event. It considers the right, left, and space key.
     *  The left key makes the gun to move to the left, the right key to the right, the space key
     *  to fire 
     * 
     */

    public void keyPressed( KeyEvent e ) {
        if (e.getKeyCode() == LEFTKEY){// left key to move left
            if(controller == null)
                return;
            // gun.keyPressed(e);
            controller.moveLeft();

        }
        if (e.getKeyCode() == RIGHTKEY){// right key to move right 
            if(controller == null)
                return;
            controller.moveRight();

        }
        if (e.getKeyCode() == SPACEKEY){// Space key to fire the bullet
            if(controller == null)
                return;
            Dimension size = getSize();
            double y = size.height - gun.getHeight();
            double gunX = (controller!=null? controller.getGunX():0.5); 
            int x = (int)(size.width*gunX - gun.getWidth()/2); 
            bullet.add(new Bullet(x,y)); 

        }
        repaint();

    }

    /** This method checks the events when key released 
     * 
     */
    public void keyReleased( KeyEvent e ) {
        if (e.getKeyCode() == SPACEKEY){// a fire key
            if(controller == null)
                return;
            

        }

    }

    /** This method checks and help to get the typed key  
     */
    public void keyTyped( KeyEvent e ) {          

    }
    /** This method sets the controller 
     * 
     */
    public void setController(mainWindow controller){
        this.controller = controller;
    }

    /** This methods performs a timer movement.  Moves the balloon, the bullet fired and 
     * checks for collisions.  
     */

    public void actionPerformed( ActionEvent e ) {
        if(gameOver)
            return;
        int score = 10;
        for(Bullet x:bullet){
            x.setVy(-5);
            x.move(); 
        }
        //balloon.move();
        if(seconds > 0) {
            seconds-=60/TIMELIMIT; 
            timx.setText("Time left: " +seconds*TIMELIMIT/4000 +"                                                                                                        "+
                "Number of hits: " + ballonsHit+ "   Score: "+score*ballonsHit);
        }
        if(seconds <= 0){
            timx.setText("Time is up!\n Game Over"  + "                                                                                       "+
                "Number of hits: " + ballonsHit +  "  Score: "+score*ballonsHit);
            HighScore.saveHighScore(score*ballonsHit);

            //JOptionPane.showMessageDialog(null, "Your scor is: " + score*ballonsHit);
            gameOver = true;
            JOptionPane.showMessageDialog(null, "you score is: " + score*ballonsHit);

            int newScore = score*ballonsHit;
            if (newScore > HighScore.readHighScore()){
                JOptionPane.showMessageDialog(null, "Congratulation!! You get highest score ever, and improved by: " +  (score*ballonsHit - HighScore.readHighScore()));
            }

        }
        //JOptionPane.showMessageDialog(null, "The Previous high score is: " +HighScore.readHighScore());

        checkCollision();

        repaint();
    }
}
