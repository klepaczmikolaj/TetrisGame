
package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Mikolaj Klepacz
 */
public class DisplayWindow extends JFrame implements KeyListener, ActionListener{
    
    String windowName;
    GraphicsPanel panel;
    GameBoard gameBoard;
    int xSize, ySize;
    int delay = 500;
    boolean levelKeyDisabled = false;
    
    public DisplayWindow(String windowName, GameBoard gameBoard){
        this.gameBoard = gameBoard;
        this.windowName = windowName;
        setTitleSizeVisibilityExit();
        panel = new GraphicsPanel(gameBoard);
        add(panel);
        addKeyListener(this);
    }
    private void setTitleSizeVisibilityExit(){
        setTitle(windowName);
        xSize = 220;
        ySize = 480;
        setSize(xSize,ySize);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int ch = e.getKeyCode();
        if(ch == 37 && gameBoard.gameStarted && !gameBoard.isTouchingLeftWall() && !gameBoard.isTouchingBedFromLeft()){
            gameBoard.moveBlockLeft();
            gameBoard.updateBoard();
            repaint();
        }
        else if(ch == 39 && gameBoard.gameStarted && !gameBoard.isTouchingRightWall() && !gameBoard.isTouchingBedFromRight()){
            gameBoard.moveBlockRight();
            gameBoard.updateBoard();
            repaint();
        }
        else if(ch == 38 && gameBoard.gameStarted && !unableToRotateWall()){
            rotateBlock();
            gameBoard.updateBoard();
            repaint();
        }
        else if(ch == 40 && gameBoard.gameStarted && !gameBoard.isTouchingBedDown()){
            gameBoard.moveBlockDown();
            gameBoard.updateBoard();
            repaint();
        }
        else if(ch == 49 && !levelKeyDisabled){
            delay = 500;
            levelKeyDisabled = true;
            gameBoard.gameStarted = true;
        }
        else if(ch == 50 && !levelKeyDisabled){
            delay = 300;
            levelKeyDisabled = true;
            gameBoard.gameStarted = true;
        }
        else if(ch == 51 && !levelKeyDisabled){
            delay = 100;
            levelKeyDisabled = true;
            gameBoard.gameStarted = true;
        }
    }
    
    private void rotateBlock(){
        gameBoard.block.bodyCopy = gameBoard.block.copyBody(gameBoard.block.body);
            gameBoard.block.rotate();
            if(gameBoard.isBlockInsideOfBed())
                gameBoard.block.body = gameBoard.block.copyBody(gameBoard.block.bodyCopy);
            else
                gameBoard.block.updateDirection();
    }
    //TODO
    private boolean unableToRotateWall(){
        if(gameBoard.isTouchingRightWall())
            return true;
        else if(gameBoard.isTouchingLeftWall())
            return true;
        else if(gameBoard.block.blockType == Block.BlockType.rectangle && 
                (gameBoard.block.direction == Block.DIR.UP || gameBoard.block.direction == Block.DIR.DOWN) &&
                gameBoard.block.head.x == gameBoard.numberOfElementsX - 2)
            return true;
        return false;
    }
    
}