import java.io.*;
/**
 * Description: This method allows to write and and read the high score result
 *              in the file and displays to the player at the end of the game.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HighScore
{
    /** This method write the score in the given file name and save
     *  it.
     * 
     */
    public static void saveHighScore(int score)
    {
        try{
        PrintStream output = new PrintStream(new File("highscore.txt"));      
        output.println(score);
        }
    catch(Exception e){
    }
       
    }
    /** This method read the score from the given file and display 
     * when it asked to dispaly
     * @return score the highest scored
     * 
     * 
     */
    public static int readHighScore()
     {
         int score = 0;
         try{
        //Scanner input = new Scanner(new File("highscore.txt"));
       // score = input.nextInt();
         
    }
    catch(Exception e){
    }
    return score;
    }
}

