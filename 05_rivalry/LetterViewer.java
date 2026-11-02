/*
*
* Execution: java LetterViewer
* 
* Display the character that was last pressed.
*
*/

public class LetterViewer {
    public static void main(String[] args) {
        PennDraw.setFontSize(64); // Set the font size to 64

        PennDraw.enableAnimation(30); // Turn on animation

        // Create while iteration loop to update screen
        while (true) {
            // Create if statement to draw last typed character on the screen
            if (PennDraw.hasNextKeyTyped()) {
                PennDraw.clear(); // Clear screen to reset
                char keyPressed = PennDraw.nextKeyTyped(); // Variable for last key
                String keyPress = "" + keyPressed; // Convert char to string
                PennDraw.text(0.5, 0.5, keyPress); // Draw most recently pressed key
            }

            PennDraw.advance(); // Display drawing that's stored in the memory
        }
    }
}