
package tetris;


/**
 *
 * @author Mikolaj Klepacz
 */
public class GameBoard {
    public boolean [][] board;
    public boolean [][] bed;
    public int numberOfElementsX;
    public int numberOfElementsY;
    public int elementSize;
    public Block block;
    public boolean gameStarted = false;
    
    GameBoard(int numberOfElementsX, int numberOfElementsY, int elementSize){
        this.numberOfElementsX = numberOfElementsX;
        this.numberOfElementsY = numberOfElementsY;
        this.elementSize = elementSize;
        board = new boolean[numberOfElementsX][numberOfElementsY];
        bed = new boolean[numberOfElementsX][numberOfElementsY];
        clearBoard();
        initializeBed();
    }
    //isNecessary?
    public GameBoard(GameBoard g){
         board = g.board;
         bed = g.bed;
         numberOfElementsX = g.numberOfElementsX;
         numberOfElementsY = g.numberOfElementsY;
         elementSize = g.elementSize;
         block = g.block;
    }
    
    public void clearBoard(){
        for(int i = 0; i<numberOfElementsX; i++)
            for(int j = 0; j<numberOfElementsY; j++)
                board[i][j] = false;
    }
    
    public void setElementTrue(int x, int y){
        board[x][y] = true;
    }
    
    public void initializeBed(){
        for(int i = 0; i<numberOfElementsX; i++)
            for(int j = 0; j<numberOfElementsY; j++)
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
        for(int i = 0; i<numberOfElementsX; i++)
            for(int j = 0; j<numberOfElementsY; j++)
                if(bed[i][j] == true)
                    board[i][j] = true;
    }
    
    
    public void updateBedWithCurrentBlock(){
        for (Coords iter : block.body) {
            bed[iter.x][iter.y] = true;
        }
    }
    //TODO
    public boolean isTouchingBedDown(){
        if(isTouchingFloor()){
            return true;
        }
        for (Coords iter : block.body) 
            if(bed[iter.x][iter.y + 1] == true)
                return true;
        return false;
    }
    
    private boolean isTouchingFloor(){
        for (Coords iter : block.body)
            if(iter.y == numberOfElementsY - 1)
                return true;
        return false;
    }
    
    private Coords drawHead(){
        Coords coords = new Coords(numberOfElementsX/2 - 1, 0);
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
            if(iter.x == numberOfElementsX - 1)
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
    
    public void deleteFullLinesAndUpdateBed(){
        int counter;
        for(int j = 0; j<numberOfElementsY; j++){
            counter = 0;
            for(int i = 0; i<numberOfElementsX; i++){
                if (bed[i][j] == true)
                    counter++;
            }
            if(counter == numberOfElementsX){
                deleteRow(j);
                shiftUpperBlocksDown(j);
            }
                
        }
    }
    
    private void deleteRow(int row){
        for(int i = 0; i<numberOfElementsX; i++)
            bed[i][row] = false;
    }
    
    private void shiftUpperBlocksDown(int row){
        for(int j = row; j>0; j--){
            for(int i = 0; i<numberOfElementsX; i++)
                bed[i][j] = bed[i][j-1];
            
        }
    }
    
    public boolean isBlockInsideOfBed(){
        for (Coords iter : block.body)
            if(bed[iter.x][iter.y] == true)
                return true;
        return false;
    }
   
    public boolean isGameOver(){
        for(int i = 0; i<numberOfElementsX; i++)
            if(bed[i][0] == true)
                return true;
        return false;
    }
}


