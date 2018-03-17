
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
                gameBoard.deleteFullLinesAndUpdateBed();
                gameBoard.initializeBlock();
                gameBoard.updateBoard();
                if(gameBoard.isGameOver()){
                    globalTimer.purge();
                    globalTimer.cancel();
                }
                window.repaint();
            }
            else{
                gameBoard.moveBlockDown();
                gameBoard.updateBoard();
                window.repaint();
            }
        }
    }  
    
    private final DisplayWindow window;
    private final int numberOfElementsX;
    private final int numberOfElementsY;
    private final int elementSize;
    private final GameBoard gameBoard;
    Timer globalTimer = new Timer();
    MyTimerTask timerTask = new MyTimerTask();
    
    public GameLogic(int numberOfElementsX, int numberOfElementsY, int elementSize){
        this.numberOfElementsX = numberOfElementsX;
        this.numberOfElementsY = numberOfElementsY;
        this.elementSize = elementSize;
        gameBoard = new GameBoard(this.numberOfElementsX, this.numberOfElementsY, this.elementSize);
        window = new DisplayWindow("Tetris",gameBoard);
    }
    
    public void gameLoop(){
        while(true){
            if(gameBoard.gameStarted)
                break;
            window.repaint();
        }
        
        gameBoard.initializeBlock();
        gameBoard.updateBoard();
        window.repaint();
        globalTimer.schedule(timerTask, window.delay, window.delay);
    }
    
    
}
