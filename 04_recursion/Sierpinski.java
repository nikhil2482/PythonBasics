/**
* Execution: java Sierpinski
*
* Program Description: Print out a Sierpinski design in which each carpet
* produces another 8 smaller carpets around it every time the value increases.
*/

public class Sierpinski {

    /*
    * Description: takes in the input string and uses that information to call the 
    *              function sierpinski.
    * Input:  a String
    * Output: call sierpinski with the numLevels, halfSideLength, x, and y
    */
    public static void main(String[] args) {
        int numLevels = Integer.parseInt(args[0]); // command line argument
        double halfSideLength = 1.0 / 6.0; // this value stays constant and is given
        double x = 0.5; // center the first square
        double y = 0.5; // center the first square
        PennDraw.filledSquare(0.5, 0.5, 1.0 / 6.0);
        sierpinski(numLevels, halfSideLength, x, y); // call the function below 
    }

    /*
    * Description: takes in the numLevels, halfSideLength, x and y and draws the
    *              squares depending on the numLevels given
    * Input:  int numLevels, double halfSideLength, double x and y
    * Output: drawn sierpinski carpets
    */
    public static void sierpinski(int numLevels, double halfSideLength, double x
    , double y) {
        // Base case: is the num levels if below one then no carpets can be drawn
        if (numLevels < 1) {
            return;
        } else {
            // System.out.println(numLevels + " " + halfSideLength);
            // Recursive case: draw eight different smaller squares around each
            // current square numLevels - 1
            PennDraw.filledSquare(x, y, halfSideLength); // actual drawing
            sierpinski(numLevels - 1, halfSideLength / 3, x + (2 * halfSideLength)
            , y + (2 * halfSideLength));
            sierpinski(numLevels - 1, halfSideLength / 3, x + (2 * halfSideLength)
            , y - (2 * halfSideLength));
            sierpinski(numLevels - 1, halfSideLength / 3, x - (2 * halfSideLength)
            , y + (2 * halfSideLength));
            sierpinski(numLevels - 1, halfSideLength / 3, x - (2 * halfSideLength)
            , y - (2 * halfSideLength));
            sierpinski(numLevels - 1, halfSideLength / 3, x
            , y - (2 * halfSideLength));
            sierpinski(numLevels - 1, halfSideLength / 3, x
            , y + (2 * halfSideLength));
            sierpinski(numLevels - 1, halfSideLength / 3, x + (2 * halfSideLength)
            , y);
            sierpinski(numLevels - 1, halfSideLength / 3, x - (2 * halfSideLength)
            , y);

        }
    }
    
}