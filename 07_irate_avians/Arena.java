/*
*
*  A class representing the arena in which the Irate Avians
*  game takes place. Keeps track of the game's Bird and
*  Targets and receives the player's input to control the bird.
*
*/

public class Arena {
    // The width and height of the PennDraw screen
    private int width, height;
    
    // All the targets in the Arena
    private Target[] targets;
    
    // The one and only bird in the game
    private Bird bird;
    
    /**
    * Whether the game is currently listening for
    * the player's mouse input, or letting the bird
    * fly. Begins as true.
    */
    private boolean mouseListeningMode;
    
    /**
    * Tells the program if the user was pressing
    * the mouse in the previous update call. Lets
    * the program know if the user has just released
    * the mouse because mouseWasPressedLastUpdate will
    * be true, but PennDraw.mousePressed() will be false
    * in the current update call. This enables the game
    * to transition from the mouse listening state into
    * the bird flight state.
    */
    private boolean mouseWasPressedLastUpdate;
    
    /**
    * Input: String filename
    * Output: none (gets the data)
    * Description: Given a file that describes the contents of the
    * Arena, parse the file and initialize all member
    * variables of the Arena.
    * The file will be in the following format:
    * numTargets width height
    * bird.numThrows
    * target0.xPos target0.yPos target0.radius target0.xVel target0.yVel 
    * target0.hitPoints
    * target1.xPos... etc.
    */
    public Arena(String filename) {
        In in = new In(filename);
        // Parse given file and init all member variables.
        int numTargets = in.readInt();
        targets = new Target[numTargets]; // create the correct amount of targets
        width = in.readInt();
        height = in.readInt();
        // Set scale of the game
        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);
        // Initialize position to 1.0
        double xPos = 1.0;
        double yPos = 1.0;
        double radius = 0.25;
        mouseListeningMode = true;
        mouseWasPressedLastUpdate = false;
        int numThrowsRemaining = in.readInt();
        // Iterate through the data and assign each value with a target
        for (int i = 0; i < targets.length; i++) {
            targets[i] = new Target(width, height, in.readDouble(), in.readDouble(),
            in.readDouble(), in.readDouble(), in.readDouble(), in.readInt());
        }
        // Create new 
        bird = new Bird(xPos, yPos, radius, numThrowsRemaining);
        in.close();
    }
    
    /**
    * Input: none
    * Output: boolean
    * Description: Returns true when all targets' hit points are 0.
    * Returns false in any other scenario.
    */
    private boolean didPlayerWin() {
        // iterate through the targets and make sure that all higPoints are 0
        // in order to win the game
        for (int i = 0; i < targets.length; i++) {
            if (targets[i].getHitPoints() != 0) {
                return false;
            }
        }
        return true;    
    }
    
    /**
    * Input: none
    * Output: boolean
    * Description: Returns true when the bird's remaining throw count is 0
    * when the game is in mouse-listening mode.
    * Returns false in any other scenario.
    */
    private boolean didPlayerLose() {
        // Check if the mouse is in listening mode and the number of throws = 0
        if (mouseListeningMode && bird.getNumThrowsRemaining() == 0) {
            return true;
        }
        return false;
    }
    
    /**
    * Input: none
    * Output: boolean
    * Description: Returns true when either the win or lose
    * condition is fulfilled.
    * Win: All targets' hit points are 0.
    * Lose: The bird's remaining throw count reaches 0.
    * Additionally, the game must be in mouse listening
    * mode for the player to have lost so that the bird
    * can finish its final flight and potentially hit
    * the last target(s).
    */
    public boolean gameOver() {
        // If either the player lost or won then the game is over
        if (didPlayerLose() || didPlayerWin()) {
            return true;
        }
        return false;
    }
    
    /**
    * Input: double timeStep
    * Output: void
    * Description: Update each of the entities within the arena.
    * 1. Call each Target's update function
    * 2. Check the game state (mouse listening or bird moving)
    * and invoke the appropriate functions for the bird.
    */
    public void update(double timeStep) {
        // Iterate through the targets and update each one
        for (int i = 0; i < targets.length; i++) {
            targets[i].update(timeStep);
        }

        // Mouse Listening Mode
        if (mouseListeningMode) {           
            /**
            * If the mouse is currently pressed, then
            * set mouseWasPressedLastUpdate to true, and
            * call bird.setVelocityFromMousePos.
            */
            if (PennDraw.mousePressed()) {
                mouseWasPressedLastUpdate = true;
                bird.setVelocityFromMousePos();
            }
            
            /**
            * If the mouse is NOT currently pressed, AND
            * mouseWasPressedLastUpdate is currently true,
            * that means the player has just released the
            * mouse button, and the game should transition
            * from mouse-listening mode to bird-flight mode.
            */
            if (PennDraw.mousePressed() == false && mouseWasPressedLastUpdate) {
                bird.decrementThrows(); // reduce throws
                mouseWasPressedLastUpdate = false;
                mouseListeningMode = false;
            }            
        }
        else {
            // Handle bird-flight mode
            bird.update(timeStep); // update the bird movement
            // Iterate throught the targets and test for collision
            for (int i = 0; i < targets.length; i++) {
                bird.testAndHandleCollision(targets[i]);
            }

            // If the bird if off screen then iterate and update values
            if (birdIsOffscreen()) {
                // Iterate through the targets
                for (int i = 0; i < targets.length; i++) {
                    // Check if the target was hit and then decreaseHP and set 
                    // hitThisShot to false in order to restart
                    if (targets[i].isHit()) {
                        targets[i].decreaseHP();
                        targets[i].setHitThisShot(false);
                    }
                }
                bird.reset();
                mouseListeningMode = true;
            }
        }
    }
    
    /**
    * Input: none
    * Output: boolean
    * Description: A helper function for the Arena class that lets
    * it know when to reset the bird's position and velocity
    * along with the game state.
    * Returns true when the bird is offscreen to the left, right,
    * or bottom. However, the bird is allowed to go above the top
    * of the screen without resetting.
    */
    private boolean birdIsOffscreen() {
        // Check if the entire bird if offscreen
        return bird.getYpos() + bird.getRadius() < 0 || bird.getXpos() + 
        bird.getRadius() < 0 || bird.getXpos() - bird.getRadius() > width;
    }
    
    /**
    * Input: none
    * Output: void (draws something)
    * Description: 1. Clear the screen
    * 2. Draw each target
    * 3. Draw the bird
    * 4. If in mouse listening mode and
    *    the mouse was pressed last update,
    *    draw the bird's velocity as a line.
    * 5. Advance PennDraw.
    */
    public void draw() {
        PennDraw.clear();
        // Iterate over each target and draw each one
        for (Target target : targets) {
            target.draw();
        }
        bird.draw(); // draw the bird
        // If the mouse was pressed during the last update and listening mode is one
        // then draw the velocity
        if (mouseWasPressedLastUpdate && mouseListeningMode) {
            bird.drawVelocity();
        }
        PennDraw.advance();
    }
    
    /**
    * Input: none
    * Output: void
    * Description: Draws either the victory or loss screen.
    * If all targets have 0 hit points, the player has won.
    * Otherwise they have lost.
    */
    public void drawGameCompleteScreen() {
        PennDraw.clear();
        // Draw the appropriate screen depending on win or lose
        if (didPlayerWin()) { // for winning the game
            PennDraw.text(width / 2, height / 2, "You Win!");
        } else if (didPlayerLose()) { // for losing the game
            PennDraw.text(width / 2, height / 2, "You have lost...");
        }
        PennDraw.advance();
    }
}
