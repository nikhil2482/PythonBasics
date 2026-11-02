/**
 * Execution: n/a
 *
 * Description: Has the methods and constructors for the Sudoku game. There's one
 * for creating the field, drawing the field, drawing the numbers, getter functions,
 * and checking if the new values break the Sudoku rules.
**/
public class Field {
    // Declare new private variables
    private int [][] field = new int[9][9];
    private boolean [][] completed = new boolean[9][9];
    private String filename;
    int n = 9; // Set one value for 9
    
    // Get the filename and convert into the field and need be throw an exception
    public Field(String filename) {
        In inStream = new In(filename);
        int columns = 0; // keep track of the columns
        // For loop to parse through the file
        for (int row = 0; !inStream.isEmpty(); row++) {
            String rows = inStream.readLine();
            // Exception for too many columns
            if (rows.length() != n) { // Rows.length is the number of columns
                throw new IllegalArgumentException("Incorrect amount of columns");
            }
            columns++;
            // For loop to get values of each index
            for (int col = 0; col < rows.length(); col++) {
                char digit = rows.charAt(col); 
                int asciiValue = (int) digit; // Convert into ASCII value
                
                // If the digit is a space then convert it to a 0
                if (digit == ' ') {
                    field[row][col] = 0;
                    // Check if the values are only between 1-9
                    } else if (digit != ' ' && 
                    (asciiValue < 49 || asciiValue > 57)) {
                    throw new IllegalArgumentException
                    ("There are not only whitespaces and numbers");
                    // Checks the amount of rows is not greater greater than 9
                    } else if (!(columns < 10)) {
                    throw new IllegalArgumentException("Incorrect amount of rows");
                    // If a regular value (between 1-9) then add into the field
                    } else if (digit != ' ') {
                    field[row][col] = asciiValue - 48;
                }
                PennDraw.setYscale(0.9, 0);
                PennDraw.setXscale(0, 0.9);
                drawDigits(row, col);
                // System.out.print(field[row][col]);
            } 
        }
        // Checks if the rows is not equal to 9
        if (columns != 9) {
            throw new IllegalArgumentException("Incorrect amount of rows");
        }  
    }

    /**
    * Inputs: None
    * Outputs: None
    * Description: Draws the Field with all the lines
    */
    public void drawField() {
        // Set scale in terms of the indexes where the top left is 0,
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setYscale(0.9, 0);
        PennDraw.setXscale(0, 0.9);
        // For loop to draw the lines
        for (double a = 0.1; a < 1.0; a = a + 0.1) {
            PennDraw.line(0, a, 0.9, a);
            PennDraw.line(a, 0, a, 0.9);
        }
    }
    
    /**
    * Inputs: int r and int c
    * Outputs: None
    * Description: Draws the digit in the Sudoku Field
    */
    public void drawDigits(int r, int c) {
        // Set scale in terms of the indexes where the top left is 0,0
        PennDraw.setYscale(0.9, 0);
        PennDraw.setXscale(0, 0.9);
        String hold = "";
        // 0 means that the field is blank
        if (field[r][c] == 0) {
            hold = " ";
        } else {
            hold += field[r][c];
        }
        PennDraw.text(c * 0.1 + 0.05, r * 0.1 + 0.05, hold); // Draws on the board
    }

    /**
    * Inputs: None
    * Outputs: int 2D array
    * Description: Getter method for the field
    */
    public int[][] getField() {
        return field;
    }

    /**
    * Inputs: None
    * Outputs: boolean 2D array
    * Description: Getter method for if the field is completed
    */
    public boolean[][] getCompleted() {
        return completed;
    }

    /**
    * Inputs: None
    * Outputs: String
    * Description: Getter method for if the field is completed
    */
    public String getFilename() {
        return filename;
    }

    /**
    * Inputs: int row and int col
    * Outputs: boolean wrong
    * Description: Returns whether the there are repeats in the columns
    */
    public boolean columns(int row, int col) {
        boolean wrong = false; // Start with false
        // For loop to go through each column
        for (int i = 0; i < field.length; i++) {
            // Check if there are repeats as long as not checking against itself
            if (field[row][col] == field[i][col] && field[i][col] != 0 && i != row) {
                wrong = true;
                // Penn Draw function to highlight that column
                PennDraw.setPenRadius(0.05);
                PennDraw.setPenColor(19, 251, 3);
                PennDraw.line(col * 0.1 + .05, 0, col * .1 + .05, .9);
                // Penn Draw function to write the wrong number in red
                PennDraw.setPenColor(PennDraw.RED);
                PennDraw.setPenRadius(0.008);
                String incorrect = "";
                incorrect += field[row][col];
                // Draw the number
                PennDraw.text(col * 0.1 + 0.05, row * 0.1 + 0.05, incorrect);
                incorrect = ""; // reset incorrect variable
            
            }
        }
        return wrong;
    }

    /**
    * Inputs: int row and int col
    * Outputs: Boolean wrong
    * Description: Returns whether there are repeats in the rows
    */
    public boolean rows(int row, int col) {
        boolean wrong = false;
        // For loop to go through each row
        for (int i = 0; i < field.length; i++) {
            if (field[row][col] == field[row][i] && field[row][col] != 0 && i != col)
             {
                wrong = true;
                // Penn Draw function to highlight that column
                PennDraw.setPenRadius(0.05);
                PennDraw.setPenColor(19, 251, 3);
                PennDraw.line(0, row * 0.1 + 0.05, .9, row * 0.1 + 0.05);
                // Penn Draw function to write the wrong number in red
                PennDraw.setPenColor(PennDraw.RED);
                PennDraw.setPenRadius(0.008);
                String incorrect = "";
                incorrect += field[row][col];
                // Draw the number
                PennDraw.text(col * 0.1 + 0.05, row * 0.1 + 0.05, incorrect);
                incorrect = ""; // reset the incorrect variable
            
            }
        
        }
        return wrong;
    }

    /**
    * Inputs: int row and int col
    * Outputs: boolean wrong
    * Description: Returns whether there are repeats in the 3X3 grid
    */
    public boolean box(int row, int col) {
        boolean wrong = false;

        // Declare starting row and column
        int startingRow = 0;
        int startingCol = 0;

        // Determine the starting row given the row of the element
        if (0 <= row && row <= 2) {
            startingRow = 0;
        } else if (3 <= row && row <= 5) {
            startingRow = 3;
        } else {
            startingRow = 6;
        }
    
        // Determine the starting column given the row of the element
        if (0 <= col && col <= 2) {
            startingCol = 0;
            } else if (3 <= col && col <= 5) {
            startingCol = 3;
            } else {
            startingCol = 6;
        }

        int count = 0;
        // Determine if there are repeats in the 3x3 grid using 2 for loops
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Changes in the row and column
                int deltaX = startingRow + i;
                int deltaY = startingCol + j;
                // If similar value is found then highlight that grid
                if (field[row][col] != 0 && field[row][col] == field[deltaX][deltaY]
                 && deltaX != row && deltaY != col) {
                    wrong = true;
                    // Highlight that specific grid
                    PennDraw.setPenColor(19, 251, 3);
                    PennDraw.square(startingCol * 0.1 + .15, 
                    startingRow * 0.1 + .15, .15);
                    // Draw that number in red
                    String incorrect = "";
                    if (field[row][col] == 0) {
                    } else {
                        incorrect += field[row][col];
                    }
                    
                    PennDraw.setPenColor(PennDraw.RED);
                    PennDraw.setPenRadius(0.008);
                    // Draw the number
                    PennDraw.text(col * 0.1 + 0.05, row * 0.1 + 0.05, incorrect);
                    incorrect = ""; // reset the incorrect variable
                }
            }
        }
        return wrong;
    }

    /**
    * Inputs: None
    * Outputs: Boolean finalCorrect
    * Description: returns whether the rules of Sudoku are followed with the columns,
    * rows and 3x3 grids.
    */
    public boolean sudokuRules() {
        boolean finalCorrect = true;
        // Create 2 forloops to go through each index and check if all the 
        // conditions are met.
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (columns(i, j)) {
                    PennDraw.text(0.5, 0.45, "Column is wrong. Try Again!");
                    finalCorrect = false; 
                } else if (rows(i, j)) {
                    PennDraw.text(0.5, 0.45, "Row is wrong. Try Again!");
                    finalCorrect = false; 
                } else if (box(i, j)) {
                    PennDraw.text(0.5, 0.45, "Box is wrong. Try Again!");
                    finalCorrect = false; 
                }
            }
        }
        return finalCorrect;
    }
}
