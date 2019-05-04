package cs1302.arcade.gameTetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * represents one element on a grid
 */
public class Element{

//    boolean isClear = false;

    // blocks for each type of shape
    public static Rectangle e1;
    public static Rectangle e2;
    public static Rectangle e3;
    public static Rectangle e4;
    boolean isClear;
    Color blockC;
    /** creates a new empty element 
     */    
    public Element()
        {
            
            isClear = true;
        }

    

    public Element(Rectangle e1, Rectangle e2, Rectangle e3, Rectangle e4)
        {
            //setting variables
            this.e1 = e1;
            this.e2 = e2;
            this.e3 = e3;
            this.e4 = e4;
            
        }




    

    /**
     * fills  an element on a grid with a specific color
     */     
    public void fillElement(String c){
   
        if(c.equalsIgnoreCase("blue")) 
           {
               blockC = Color.BLUE;
           }
        e1.setFill(blockC);
        e2.setFill(blockC);
        e3.setFill(blockC);
        e4.setFill(blockC);
    }
    
        
        public void emptyElement()
        {
            isClear = true;
        }
        
    }


