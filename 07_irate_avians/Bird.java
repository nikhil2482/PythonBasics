/*
*
*  A class that represents the bird projectile in
*  Irate Avians. Can update its own position based
*  on velocity and time, and can compute whether
*  it overlaps a given Target.
*
*/

public class Bird {
    // The position, velocity, and radius members of the bird.
    private double xPos, yPos, xVel, yVel, radius;
    
    /**
    * How many more times the player can throw the bird
    * before losing the game.
    */
    private int numThrowsRemaining;
    
    /**
    * Inputs: double xPos, yPos, radius, int numThrows
    * Outputs: none
    * Description: Initialize the bird's member variables
    * with the same names as the inputs to those values.
    * Initializes the bird's velocity components to 0.
    */
    public Bird(double xPos, double yPos, double radius, int numThrows) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.numThrowsRemaining = numThrows;
        xVel = 0.0;
        yVel = 0.0;
    }
    
    /**
    * Input: none
    * Output: void (draws something)
    * Description: Draws a circle centered at the bird's position
    * with a radius equal to the bird's radius.
    * Additionally, draws a triangular beak and two
    * circular eyes somewhere on the circle to make
    * the bird look more like a bird. Additional details
    * are up to your discretion.
    * Also draws the bird's remaining throws 0.1 units
    * above its circular body.
    */
    public void draw() {
        // Outer circle
        PennDraw.setPenColor(PennDraw.CYAN);
        PennDraw.filledCircle(xPos, yPos, radius);
        // Draw the eyes
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.point(xPos - 0.04, yPos + 0.04);
        PennDraw.point(xPos + 0.04, yPos + 0.04);
        // Draw the bird beak
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledPolygon(xPos + 0.23, yPos + 0.18, xPos + 0.23, yPos - 0.18,
        xPos + 0.4, yPos);
        // Draw the number of remaining throws above the bird
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(xPos, yPos + radius + 0.1, 
        Integer.toString(numThrowsRemaining));

    }
    
    /**
    * Input: none
    * Output: void (draws something)
    * Description: Draw the line representing the bird's initial velocity
    * when the player is clicking and dragging the mouse.
    */
    public void drawVelocity() {
        // Velocity line goes from original position to new position which is
        // position plus the velocity 
        PennDraw.line(xPos, yPos, xPos + xVel, yPos + yVel);
    }
    
    /**
    * Input: none
    * Output: void
    * Description: Set xPos and yPos to 1.0,
    * set xVel and yVel to 0.0,
    * and clear the list of targets hit this launch.
    */
    public void reset() {
        xPos = 1.0;
        yPos = 1.0;
        xVel = 0.0;
        yVel = 0.0;
    }
    
    /**
    * Input: none
    * Output: void
    * Description: Compute the bird's initial velocity as the
    * vector from the mouse's current position to
    * the bird's current position. This will be used
    * in mouse listening mode to update the launch
    * velocity.
    */
    public void setVelocityFromMousePos() {
        // Gets the position of the mouse and stores as a double
        double xMouse = PennDraw.mouseX();
        double yMouse = PennDraw.mouseY();
        // Velocity is the position minus the mouse position
        xVel = xPos - xMouse;
        yVel = yPos - yMouse;
    }
    
    /**
    * Input: double timeStep
    * Output: void
    * Description: Given the change in time, compute the bird's
    * new position and new velocity.
    */
    public void update(double timeStep) {
        // Position is the position plus (velocity * time)
        xPos += xVel * timeStep;
        yPos += yVel * timeStep;
        yVel -= 0.25 * timeStep; // Represents gravity
    }
    
    /**
    * Inputs: double x1, y1, x2, y2
    * Outputs: double distance
    * Description: A helper function used to find the distance
    * between two 2D points. Remember to use the
    * Pythagorean Theorem.
    */
    private static double distance(double x1, double y1, double x2, double y2) {
        //  Pythagorean Theorem Equation for the distance
        double distance = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        return distance;
    }
    
    /**
    * Input: Target t
    * Output: void
    * Description: Given a Target, determine if the bird should
    * test for collision against it. If the bird
    * *should* see if it collides with the target,
    * then perform that test. If the bird collides,
    * then decrease the target's HP by 1 and add
    * the target to the bird's list of targets hit
    * during this launch.
    */
    public void testAndHandleCollision(Target t) {
        // Only implement is hitpoints is above 0 and the bird and target overlap
        if (t.getHitPoints() > 0) {
            if (distance(t.getXpos(), t.getYpos(), xPos, yPos) < radius + 
            t.getRadius()) {
                t.setHitThisShot(true); // Set boolean to true because they hit
            }
        }
    }
    
    /**
     * Inputs: none
     * Outputs: void
     * Description: Reduces the remaining throws by 1 when the method is called
    */
    public void decrementThrows() {
        // Subtract remaining throws by 1
        numThrowsRemaining = numThrowsRemaining - 1;
    }
    
    /**
    * Inputs: none
    * Outputs: double xPos
    * Description: Getter functions that return a copy
    * of the indicated member variable.
    */
    public double getXpos() {
        return xPos;
    }

    /**
     * Inputs: none
     * Outputs: double yPos
     * Description: Getter function for yPos
    */
    public double getYpos() {
        return yPos;
    }

    /**
     * Inputs: none
     * Outputs: double radius
     * Description: Getter function for the radius
    */
    public double getRadius() {
        return radius;
    }

    /**
     * Inputs: none
     * Outputs: int numThrowsRemaining
     * Description: Getter function for numThrowsRemaining
    */
    public int getNumThrowsRemaining() {
        return numThrowsRemaining;
    }
}
