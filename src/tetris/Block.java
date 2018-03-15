
package tetris;

import java.util.ArrayList;

/**
 *
 * @author Mikolaj Klepacz
 */
public class Block {
    public enum DIR{VERTICAL, HORISONTAL};
    public enum BlockType{four, fourRev, letterL, letterLRev, square, rectangle};
    
    public DIR direction;
    public BlockType blockType;
    public ArrayList<Coords> body = new ArrayList<>();
    public Coords head = new Coords();
    
    
    
    public void addElement(int x, int y){
        Coords coords = new Coords(x,y);
        body.add(coords);
    }
    
    public void addElementBeg(int x, int y){
        Coords coords = new Coords(x,y);
        body.add(0, coords);
    }
    
    public void drawBlock(Coords head){
        this.head = head;
        direction = DIR.VERTICAL;
        body.add(head);
        blockType = BlockType.four;
        initializeFour();
    } 
    
    private void initializeFour(){
        addElement(head.x, head.y + 1);
        addElement(head.x + 1, head.y + 1);
        addElement(head.x + 1, head.y + 2);
        
    }
    
    private void initializeSquare(){
        addElement(head.x + 1, head.y);
        addElement(head.x + 1, head.y + 1);
        addElement(head.x, head.y + 1);
    }
    
    
}
