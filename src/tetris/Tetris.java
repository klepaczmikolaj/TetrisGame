
package tetris;

/**
 * Tetris Game
 * 
 * @author Mikolaj Klepacz
 */
public class Tetris {

    public static void main(String[] args) {
        int numberOfElements = 20;
        int elementSize = 20;
        
        GameLogic game = new GameLogic(numberOfElements, elementSize);
        game.gameLoop();
    }
    
}
