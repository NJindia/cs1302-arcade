

import javafx.scene.control.Label;

/**
 * represents one element on a grid
 */
public class Element{

    boolean isClear = false;



    /** creates a new empty element 
     */
    public Element()
        {
            
            isClear = true;
        }

    /**
     * fills  an element on a grid with a specific color
     */     
    public void fillElement(String color) {
        if (isClear) {

            //setcolor with color 
            
            isClear = false;
        }
    }
        public void emptyElement()
        {
            isClear = true;
        }
        
    }

