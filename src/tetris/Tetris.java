
package tetris;

/**
 * Tetris Game
 * 
 * @author Mikolaj Klepacz
 */
public class Tetris {

    public static void main(String[] args) {
        int numberOfElementsY = 22;
        int numberOfElementsX = 10;
        int elementSize = 19;
        
        GameLogic game = new GameLogic(numberOfElementsX, numberOfElementsY, elementSize);
        game.gameLoop();
    }
    
}
