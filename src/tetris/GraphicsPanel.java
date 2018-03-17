
package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Mikolaj Klepacz
 */
public class GraphicsPanel extends JPanel {

    private final GameBoard gameBoard;
    private final int xSize;
    private final int ySize;
    
    public GraphicsPanel(GameBoard board){
        this.gameBoard = board;
        this.xSize = (board.elementSize + 1) * board.numberOfElementsX + 1;
        this.ySize = (board.elementSize + 1) * board.numberOfElementsY + 1;
    }
    @Override
    public void paintComponent(Graphics g) {
        setSize(xSize,ySize);
        this.setBackground(Color.black);
        super.paintComponent(g);
        if(!gameBoard.gameStarted)
            drawGameMenu(g);
        else if(gameBoard.isGameOver())
            drawGameOverScreen(g);
        else
            drawBoard(g);
    }
    private void drawBoard(Graphics g){
        g.setColor(Color.GREEN);
        for(int i = 0; i<gameBoard.numberOfElementsX; i++)
            for(int j = 0; j<gameBoard.numberOfElementsY; j++)
                if(gameBoard.board[i][j] == true)
                    g.fillRect(1 + i*(gameBoard.elementSize + 1), 
                            1+j*(gameBoard.elementSize + 1), 
                            gameBoard.elementSize, gameBoard.elementSize);
    }
    private void drawGameOverScreen(Graphics g){
        g.setFont(new Font(Font.DIALOG, Font.PLAIN, 35));
        g.setColor(Color.white);
        g.drawString("Game Over", xSize/17, ySize/2);
    }
    private void drawGameMenu(Graphics g){
        g.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
        g.setColor(Color.white);
        g.drawString("Select Level:", xSize/15, ySize/2);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        g.drawString("1-easy, 2-medium, 3-hard", 5, ySize/2 + 25);
    }
}
