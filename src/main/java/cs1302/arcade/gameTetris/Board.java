package cs1302.arcade.gameTetris; 

import javafx.scene.shape.Rectangle;
import cs1302.arcade.gameTetris.*;

public class Board
{
//dimensions for now
    int distanceDown = 30;
    int blockSize = 30;
    int gameSizeX = 300;
    int gameSizeY = 600;
    
    int [][] grid = new int[gameSizeX/blockSize][gameSizeY/blockSize]; //main grid for game    















/********************************
 */
    private boolean gameOver = false;
//    private List<> points;//can also be an array? stored to find high score
    
    
    private Rectangle[][] board;

    //   public Board()
    //  {
    //  }       
/**
     *Generates a board
     */
    public static Element generateBoard()
        {
//           board = new Rectangle[10][24];   
           int shapeNum = 1;
           Rectangle e1 = new Rectangle(30, 30);
           Rectangle e2 = new Rectangle(30, 30);
           Rectangle e3 = new Rectangle(30, 30);
           Rectangle e4 = new Rectangle(30, 30);
           if(shapeNum == 1)
           {
               e1.setX(300/2+30);
               e2.setX(150-30);
               e2.setY(30);
               e3.setX(150);
               e3.setY(30);
               e4.setX(150+30);
               e4.setY(30);
           }
           return new Element(e1, e2, e3, e4);
        }

   
}
