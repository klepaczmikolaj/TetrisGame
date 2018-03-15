
package tetris;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Mikolaj Klepacz
 */
public class GameLogic {
    
    private class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            if(gameBoard.isTouchingBedDown()){
                gameBoard.updateBedWithCurrentBlock();
                gameBoard.initializeBlock();
                gameBoard.updateBoard();
                window.repaint();
                if(isGameOver())
                    System.exit(0);
            }
            else{
                gameBoard.moveBlockDown();
                gameBoard.updateBoard();
                window.repaint();
            }
        }
    }  
    
    private final DisplayWindow window;
    private final int numberOfElements;
    private final int elementSize;
    private final GameBoard gameBoard;
    Timer globalTimer = new Timer();
    MyTimerTask timerTask = new MyTimerTask();
    int delay;
    
    public GameLogic(int numberOfElements, int elementSize){
        this.numberOfElements = numberOfElements;
        this.elementSize = elementSize;
        gameBoard = new GameBoard(this.numberOfElements, this.elementSize);
        window = new DisplayWindow("Tetris",gameBoard);
    }
    
    public void gameLoop(){
        delay = 400;
        gameBoard.initializeBlock();
        gameBoard.updateBoard();
        globalTimer.schedule(timerTask, delay, delay);
    }
    
    private boolean isGameOver(){
        for(int i = 0; i<numberOfElements; i++)
            if(gameBoard.bed[i][0] == true)
                return true;
        return false;
    }
}
