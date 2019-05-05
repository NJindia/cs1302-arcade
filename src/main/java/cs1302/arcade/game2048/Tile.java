package cs1302.arcade.game2048;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


/** Custom ImageView that represents an individual tile in the 2048 game board. */
public class Tile extends ImageView {
    /** Value of the tile */
    private int value;

    /** X position in 2D array where tile belongs */
    public int xIndex;
    /** Y position in 2D array where tile belongs */
    public int yIndex;
    
    /** True if tile is done moving following a move operation */
    public boolean moved = false;

    /** True if another tile will merge into this one */
    public boolean merge = false;

    /** True if this tile will merge into another tile and thus needs to be removed */
    public boolean remove = false;
    
    /** Creates a new {@code Tile} and randomly sets its value to 2 or 4. */ 
    public Tile() {
        super();
        this.setFitWidth(100);
        this.setPreserveRatio(true);
        setValue((int)Math.pow(2, (int)((Math.random()*2) + 1)));
    } //Tile

    /**
     * Sets the value of the tile and changes its image to correspond to the new value.
     * @param v the specified number that the tile's value will be set to
     */
    public void setValue(int v) {
        value = v;
        this.setImage(new Image("2048tiles/" + v + ".png"));
    } //setValue

    /**
     * Returns the tile's value.
     * @return the tile's value
     */
    public int getValue() {
        return value;
    } //getValue

    /**
     * Returns true if the specified tile can merge with this tile.
     * @param t the specified tile
     * @return true if this and the specified tile have the same value.
     */
    public boolean canMerge(Tile t) {
        if(t != null && value == t.getValue() && merge == false) {
            return true;
        } //if
        return false;
    } //canMerge

    /** Multiplies the tile's value by 2 to mimic a merge with a tile of the same value. */
    public void merge() {
        setValue(value * 2);
    } //merge
    
} //Tile
