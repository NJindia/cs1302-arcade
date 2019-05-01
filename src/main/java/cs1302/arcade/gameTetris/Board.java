package cs1302.arcade.game2048; 

import javafx.scene.shape.Rectangle;


public class Board
{

    private boolean gameOver = false;
//    private List<> points;//can also be an array? stored to find high score
    
    
    private Rectangle[][] board;

    //   public Board()
    //  {
    //  }       
/**
     *Generates a board
     */
    public void generateBoard()
        {
           board = new Rectangle[10][24];   
           
        }

   
}
