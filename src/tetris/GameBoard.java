
package tetris;


/**
 *
 * @author Mikolaj Klepacz
 */
public class GameBoard {
    public boolean [][] board;
    public boolean [][] bed;
    public int numberOfElements;
    public int elementSize;
    public Block block;
    
    GameBoard(int numberOfElements, int elementSize){
        this.numberOfElements = numberOfElements;
        this.elementSize = elementSize;
        board = new boolean[numberOfElements][numberOfElements];
        bed = new boolean[numberOfElements][numberOfElements];
        clearBoard();
        initializeBed();
    }
    
    public void clearBoard(){
        for(int i = 0; i<numberOfElements; i++)
            for(int j = 0; j<numberOfElements; j++)
                board[i][j] = false;
    }
    
    public void setElementTrue(int x, int y){
        board[x][y] = true;
    }
    
    public void initializeBed(){
        for(int i = 0; i<numberOfElements; i++)
            for(int j = 0; j<numberOfElements; j++)
                bed[i][j] = false;
    }
    
    public void initializeBlock(){
        block = new Block();
        Coords head;
        head = drawHead();
        block.drawBlock(head);
    }
    
    public void updateBoard(){
        updateBoardBed();
        updateBoardBlock();
    }
    
    public void updateBoardBlock(){
        for (Coords iter : block.body) {
            setElementTrue(iter.x, iter.y);
        }
    }
    
    public void updateBoardBed(){
        clearBoard();
        for(int i = 0; i<numberOfElements; i++)
            for(int j = 0; j<numberOfElements; j++)
                if(bed[i][j] == true)
                    board[i][j] = true;
    }
    
    
    public void updateBedWithCurrentBlock(){
        for (Coords iter : block.body) {
            bed[iter.x][iter.y] = true;
        }
    }
    
    public boolean isTouchingBedDown(){
        if(isTouchingFloor()){
            return true;
        }
        for(int i = 0; i<numberOfElements; i++)
            for(int j = 0; j<numberOfElements; j++)
                for (Coords iter : block.body) 
                    if(bed[iter.x][iter.y + 1] == true)
                        return true;
        return false;
        
    }
    
    private boolean isTouchingFloor(){
        for (Coords iter : block.body)
            if(iter.y == numberOfElements - 1)
                return true;
        return false;
    }
    
    private Coords drawHead(){
        Coords coords = new Coords(numberOfElements/2 - 1, 0);
        return coords;
    }
    
    public void moveBlockDown(){
        for (Coords iter : block.body)
            iter.y++;
    }
    
    public void moveBlockLeft(){
        for (Coords iter : block.body)
            iter.x--;
    }
    
    public void moveBlockRight(){
        for (Coords iter : block.body)
            iter.x++;
    }
    
    public boolean isTouchingLeftWall(){
        for(Coords iter : block.body){
            if(iter.x == 0)
                return true;
        }
        return false;
    }
    
    public boolean isTouchingRightWall(){
        for(Coords iter : block.body){
            if(iter.x == numberOfElements - 1)
                return true;
        }
        return false;
    }
    
    public boolean isTouchingBedFromLeft(){
        for(Coords iter : block.body)
            if(bed[iter.x - 1][iter.y] == true)
                return true;
        return false;
    }
    
    public boolean isTouchingBedFromRight(){
        for(Coords iter : block.body)
            if(bed[iter.x + 1][iter.y] == true)
                return true;
        return false;
    }
    
}


