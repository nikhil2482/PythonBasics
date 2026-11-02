/**
 * Execution: java Sudoku filename
 *
 * Description: Takes in the arguments, creates a new object, can add to the field,
 * and end the game. Essentially the head file for the Sudoku game.
**/
public class Sudoku {
    public static void main(String [] args) {
        // Declare and Read Command Line arguments
        String filename = args[0];
        // Create a new object named game
        Field game = new Field(filename); 
        PennDraw.enableAnimation(50);
        game.drawField(); // Draw the field for the background
        PennDraw.advance();
        
        boolean inGame = true; // boolean for whether currently in game

        double xValue = 0.0;
        double yValue = 0.0;

        // While loop for when game is going on
        while (inGame) {
            // Get the cordinates of the mouse press
            if (PennDraw.mousePressed()) {
                xValue = PennDraw.mouseX();
                yValue = PennDraw.mouseY();
            }
            // Check if the game is completed and follows sudoku rules
            if (!completedField(game) && game.sudokuRules()) {
                game = addValue(xValue, yValue, game); // Add value into board
            } else {
                inGame = false;
            }
        }
        
        // Check whether the gamee is completed and if so display a winning message
        if (completedField(game)) {
            PennDraw.setPenColor(PennDraw.GREEN);
            PennDraw.setPenRadius(0.01);
            PennDraw.text(0.5, 0.5, "YOU WIN"); // Message to display
        }
        PennDraw.advance();

 
    }
    /**
     * Inputs: double xCord, double yCord, and Field game
     * Outputs: return the game
     * Description: This is the method to add and draw a value into the field if
     * it satisfies the requirements
    */
    public static Field addValue(double xCord, double yCord, Field game) {
        boolean c = true;
        // Convert the row and column into a cordinates that can be drawn
        int row = (int) (yCord * 10);
        int col = (int) (xCord * 10);
        // If a new key is typed check to make sure its 1-9 and then draw it
        if (PennDraw.hasNextKeyTyped()) {
            char newValue = PennDraw.nextKeyTyped(); // Get key value
            int asciiNew = (int) newValue; // Convert to ASCII value
            // Extra credit to clear the board if space is pressed
            if (asciiNew == 32) {
                // Draw white rectangle over everything
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledSquare(0.45, 0.45, 0.45);
                PennDraw.setPenColor(PennDraw.BLACK);
                game.drawField();
                PennDraw.advance();
                // Create new object for the game
                Field game2 = new Field(game.getFilename());
                game = game2;
                PennDraw.advance();

            }
            // Check if the value is a number 1-9
            else if (newValue != ' ' && asciiNew < 58 && asciiNew > 48) {
                game.getField()[row][col] = asciiNew - 48;
                game.drawDigits(row, col); // Draw the digit in the field
                PennDraw.advance();
            }
        }
        return game;
        
    }
    
    /**
     * Inputs: Field game
     * Outputs: boolean named correct
     * Description: Check if the field is complete with values and if sudokuRules
     * are followed. If so, return true.
    */
    public static boolean completedField(Field game) {
        boolean filledBoard = true;
        boolean correct = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (game.getField() [i][j] == 0) { // 0 is not a number in Sudoku
                    filledBoard = false;
                }
            }
        }
        if (filledBoard && game.sudokuRules()) { // Check the rules as well
            correct = true;
        }
        return correct;
    }
}
