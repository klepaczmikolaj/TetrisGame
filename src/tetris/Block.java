
package tetris;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mikolaj Klepacz
 */
public class Block {
    public enum DIR{LEFT, RIGHT, UP, DOWN};
    public enum BlockType{four, fourRev, square, rectangle, letterL, letterLRev, letterT};
    
    public DIR direction;
    public BlockType blockType;
    public ArrayList<Coords> body = new ArrayList<>();
    public ArrayList<Coords> bodyCopy;
    public Coords head = new Coords();
    private final Random ran = new Random();   
    
    
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
        direction = DIR.UP;
        body.add(head);
        drawBlockType();
        initializeBlock();
    } 
    
    private void drawBlockType(){
        BlockType[] blockTypeVal = BlockType.values();
        blockType = blockTypeVal[ran.nextInt(blockTypeVal.length)];
    }
    
    private void initializeBlock(){
        if(blockType == BlockType.four)
            initializeFour();
        else if(blockType == BlockType.fourRev)
            initializeFourRev();
        else if(blockType == BlockType.rectangle)
            initializeRectangle();
        else if(blockType == BlockType.square)
            initializeSquare();
        else if(blockType == BlockType.letterL)
            initializeLetterL();
        else if(blockType == BlockType.letterLRev)
            initializeLetterLRev();
        else if(blockType == BlockType.letterT)
            initializeLetterT();
    }
        
    private void initializeFour(){
        addElement(head.x, head.y + 1);
        addElement(head.x + 1, head.y + 1);
        addElement(head.x + 1, head.y + 2);
    }
    
    private void initializeFourRev(){
        addElement(head.x, head.y + 1);
        addElement(head.x - 1, head.y + 1);
        addElement(head.x - 1, head.y + 2);
    }
    
    private void initializeLetterL(){
        addElement(head.x, head.y + 1);
        addElement(head.x, head.y + 2);
        addElement(head.x + 1, head.y + 2);
    }
    
    private void initializeLetterLRev(){
        addElement(head.x, head.y + 1);
        addElement(head.x, head.y + 2);
        addElement(head.x - 1, head.y + 2);
    }
    
    private void initializeSquare(){
        addElement(head.x + 1, head.y);
        addElement(head.x + 1, head.y + 1);
        addElement(head.x, head.y + 1);
    }
    
    private void initializeRectangle(){
        addElement(head.x, head.y + 1);
        addElement(head.x, head.y + 2);
        addElement(head.x, head.y + 3);
    }
    
    private void initializeLetterT(){
        addElement(head.x, head.y + 1);
        addElement(head.x, head.y + 2);
        addElement(head.x + 1, head.y + 1);
    }
    
    public void rotate(){
        if(blockType == BlockType.four)
            rotateFour();
        else if(blockType == BlockType.fourRev)
            rotateFourRev();
        else if(blockType == BlockType.rectangle)
            rotateRectangle();
        else if(blockType == BlockType.square)
            rotateSquare();
        else if(blockType == BlockType.letterL)
            rotateLetterL();
        else if(blockType == BlockType.letterLRev)
            rotateLetterLRev();
        else if(blockType == BlockType.letterT)
            rotateLetterT();
    }
    public void updateDirection(){
        if(direction == DIR.UP)
            direction = DIR.LEFT;
        else if(direction == DIR.LEFT)
            direction = DIR.DOWN;
        else if(direction == DIR.DOWN)
            direction = DIR.RIGHT;
        else
            direction = DIR.UP;
    }
    
    public ArrayList<Coords> copyBody(ArrayList<Coords> original){
        ArrayList<Coords> copy = new ArrayList<>();
        for(Coords iter : original){
            Coords coords = new Coords(iter.x, iter.y);
            copy.add(coords);
        }
        return copy;
    }
    
    private void rotateFour(){
        if(direction == DIR.UP || direction == DIR.DOWN){
            setCoordsBody(0, 0, 2);
            setCoordsBody(1, 1, 1);
            setCoordsBody(3, 1, -1);
        }
        else{
            setCoordsBody(0, 0, -2);
            setCoordsBody(1, -1, -1);
            setCoordsBody(3, -1, 1);
        }
    }
    
    private void rotateFourRev(){
        if(direction == DIR.UP || direction == DIR.DOWN){
            setCoordsBody(0, -2, 0);
            setCoordsBody(1, -1, -1);
            setCoordsBody(3, 1, -1);
        }
        else{
            setCoordsBody(0, 2, 0);
            setCoordsBody(1, 1, 1);
            setCoordsBody(3, -1, 1);
        }
    }
    
    private void rotateLetterL(){
        if(direction == DIR.UP){
            setCoordsBody(0, -1, 1);
            setCoordsBody(2, 1, -1);
            setCoordsBody(3, 0, -2);
        }
        else if(direction == DIR.LEFT){
            setCoordsBody(0, 1, 1);
            setCoordsBody(2, -1, -1);
            setCoordsBody(3, -2, 0);
        }
        else if(direction == DIR.DOWN){
            setCoordsBody(0, 1, -1);
            setCoordsBody(2, -1, 1);
            setCoordsBody(3, 0, 2);
        }
        else{
            setCoordsBody(0, -1, -1);
            setCoordsBody(2, 1, 1);
            setCoordsBody(3, 2, 0);
        }    
    }
    
    private void rotateLetterLRev(){
        if(direction == DIR.UP){
            setCoordsBody(0, -1, 1);
            setCoordsBody(2, 1, -1);
            setCoordsBody(3, 2, 0);
        }
        else if(direction == DIR.LEFT){
            setCoordsBody(0, 1, 1);
            setCoordsBody(2, -1, -1);
            setCoordsBody(3, 0, -2);
        }
        else if(direction == DIR.DOWN){
            setCoordsBody(0, 1, -1);
            setCoordsBody(2, -1, 1);
            setCoordsBody(3, -2, 0);
        }
        else{
            setCoordsBody(0, -1, -1);
            setCoordsBody(2, 1, 1);
            setCoordsBody(3, 0, 2);
        }
               
    }
    
    private void rotateSquare(){
    }
    
    private void rotateRectangle(){
        if(direction == DIR.UP || direction == DIR.DOWN){
            setCoordsBody(0, -1, 1);
            setCoordsBody(2, 1, -1);
            setCoordsBody(3, 2, -2);
        }
        else{
            setCoordsBody(0, 1, -1);
            setCoordsBody(2, -1, 1);
            setCoordsBody(3, -2, 2);
        }
    }
    
    private void rotateLetterT(){
        if(direction == DIR.UP){
            setCoordsBody(0, -1, 1);
            setCoordsBody(2, 1, -1);
            setCoordsBody(3, -1, -1);
        }
        else if(direction == DIR.LEFT){
            setCoordsBody(0, 1, 1);
            setCoordsBody(2, -1, -1);
            setCoordsBody(3, -1, 1);
        }
        else if(direction == DIR.DOWN){
            setCoordsBody(0, 1, -1);
            setCoordsBody(2, -1, 1);
            setCoordsBody(3, 1, 1);
        }
        else{
            setCoordsBody(0, -1, -1);
            setCoordsBody(2, 1, 1);
            setCoordsBody(3, 1, -1);
        }
    }
    
    private void setCoordsBody(int index, int addToX, int addToY){
        Coords temp = body.get(index);
        temp.setCoords(temp.x + addToX, temp.y + addToY);
        body.set(index, temp);
    }
    
}
