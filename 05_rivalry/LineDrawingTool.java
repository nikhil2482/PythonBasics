/*
*
* Execution: java LineDrawingTool
* 
* Draw a line wherever the mouse is clicked that connects to the previous line.
*
*/

public class LineDrawingTool {
    public static void main(String[] args) {
        PennDraw.setPenRadius(0.01); // set the pen radius to 0.01
        PennDraw.enableAnimation(30); // turn on animation

        /* Create two variables that store the coordinates 
        * and set them equal to the center of the screen
        */
        double xCoordinate = 0.5;
        double yCoordinate = 0.5;

        // Create iteration to change screen
        while (true) {
            // Draw the line if the mouse was pressed
            if (PennDraw.mousePressed()) {
                // Create variables for new cordinates of mouse press 
                double newXCoordinate = PennDraw.mouseX();
                double newYCoordinate = PennDraw.mouseY();

                /* Set pen color to red if mouse press is in the top half
                * And black if mouse press is in the lower half
                */
                if (newYCoordinate > 0.5) {
                    PennDraw.setPenColor(PennDraw.RED);
                } else {
                    PennDraw.setPenColor(PennDraw.BLACK);
                }

                // Draw the line with new coordinates
                PennDraw.line(xCoordinate, yCoordinate, newXCoordinate, newYCoordinate);
                // Update the original coordinates with the new coordinates
                xCoordinate = newXCoordinate;
                yCoordinate = newYCoordinate;
            }

            // Advance the frame after everything has been drawn
            PennDraw.advance();
        }
    }
}
