
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
        if(ch == 37 && !isTouchingLeftWall() && !isTouchingBedFromLeft()){
            gameBoard.moveBlockLeft();
            gameBoard.updateBoard();
            repaint();
        }
        else if(ch == 39 && !isTouchingRightWall() && !isTouchingBedFromRight()){
            gameBoard.moveBlockRight();
            gameBoard.updateBoard();
            repaint();
        }
    }
    
    private boolean isTouchingLeftWall(){
        for(Coords iter : gameBoard.block.body){
            if(iter.x == 0)
                return true;
        }
        return false;
    }
    
    private boolean isTouchingRightWall(){
        for(Coords iter : gameBoard.block.body){
            if(iter.x == gameBoard.numberOfElements - 1)
                return true;
        }
        return false;
    }
    
    private boolean isTouchingBedFromLeft(){
        for(Coords iter : gameBoard.block.body)
            if(gameBoard.bed[iter.x - 1][iter.y] == true)
                return true;
        return false;
    }
    
    private boolean isTouchingBedFromRight(){
        for(Coords iter : gameBoard.block.body)
            if(gameBoard.bed[iter.x + 1][iter.y] == true)
                return true;
        return false;
}
}