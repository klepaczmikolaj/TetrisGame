
package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Mikolaj Klepacz
 */
public class DisplayWindow extends JFrame implements KeyListener{
    
    String windowName;
    GraphicsPanel panel;
    GameBoard gameBoard;
    int xSize, ySize;
    
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
        xSize = 545;
        ySize = 575;
        setSize(xSize,ySize);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        if(ch == 37 && !gameBoard.isTouchingLeftWall() && !gameBoard.isTouchingBedFromLeft()){
            gameBoard.moveBlockLeft();
            gameBoard.updateBoard();
            repaint();
        }
        else if(ch == 39 && !gameBoard.isTouchingRightWall() && !gameBoard.isTouchingBedFromRight()){
            gameBoard.moveBlockRight();
            gameBoard.updateBoard();
            repaint();
        }
        else if(ch == 38 && !unableToRotate()){
            gameBoard.block.rotate();
            gameBoard.updateBoard();
            repaint();
        }
        else if(ch == 40 && !gameBoard.isTouchingBedDown()){
            gameBoard.moveBlockDown();
            gameBoard.updateBoard();
            repaint();
        }
    }
    //TODO
    private boolean unableToRotate(){
        return gameBoard.isTouchingRightWall() && 
                (gameBoard.block.direction == Block.DIR.UP || gameBoard.block.direction == Block.DIR.DOWN);
    }
    
    
}