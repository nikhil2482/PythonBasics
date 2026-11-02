/*
*
* Execution: java Rivalry
* 
* Race between two electric vehicles, a Ford Mache and a Tesla, to see who would win
* Note that the Tesla is naturally faster and has an advantage
*
*/

public class Rivalry {    
    public static void main(String[] args) {
        // Create two boolean values for both cars
        boolean macheWins = false;  // mache has not won
        boolean teslaWins = false;  // tesla has not won

        /* the width of 1 pixel in window coordinate
         * use this variable when you get to the movement portion of the program 
         */
        double ONE_PIXEL = 1.0 / 512;   

        // Create the x cordinate variable for both cars
        double macheXCordinate = 0.1;
        double teslaXCordinate = 0.1;

        // Enable animation to 60 frames/second
        PennDraw.enableAnimation(60);
        
        // The loop in which the race takes place
        while (!macheWins && !teslaWins) {
             PennDraw.clear(); // Clear screen
             // Draw Start line and Finish line
             PennDraw.line(0.17, 0.1, 0.17, 0.85);
             PennDraw.line(0.85, 0.1, 0.85, 0.85);
             PennDraw.text(0.17, 0.9, "Start");
             PennDraw.text(0.85, 0.9, "Finish");
             // Set picture to original position and size
             PennDraw.picture(macheXCordinate, 0.7, "mache.png", 65, 65);
             PennDraw.picture(teslaXCordinate, 0.3, "tesla1.png", 65, 65);

             double macheCanMove = Math.random(); // pick random number and store it
             // Determine how much the Mache moves by
             if (macheCanMove <= .48) {
                 macheXCordinate = macheXCordinate + ONE_PIXEL;
             }
             
             // Determine how much the Tesla move by
             double teslaCanMove = Math.random();
             if (teslaCanMove <= .56) {
                 teslaXCordinate = teslaXCordinate + ONE_PIXEL;
             }
             
             /* Change boolean or booleans to true if picture 
             * reaches a certain x value
             */
             if (macheXCordinate >= .85) {
                 macheWins = true;
                 macheXCordinate = .85;
             }

             if (teslaXCordinate >= .85) {
                 teslaWins = true;
                 teslaXCordinate = .85;
             }

            // Show this frame and go on to the next one
            PennDraw.advance(); 
        }
       
        // The race is over so turn off animation
        PennDraw.disableAnimation(); 

        // Draw the text containing a victory message in the center of the screen
        if (macheWins) {
            PennDraw.text(.5, .5, "Mache Wins!");    
        } else if (teslaWins || macheWins && teslaWins) { // If tie then Tesla wins
                PennDraw.text(.5, .5, "Tesla Wins!");
            }
    }
}